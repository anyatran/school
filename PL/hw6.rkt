;; ---< The BRANG interpreter, using environments >---

#lang pl 06

#|
The grammar:
  <BRANG> ::= <num>
            | { + <BRANG> <BRANG> }
            | { - <BRANG> <BRANG> }
            | { * <BRANG> <BRANG> }
            | { / <BRANG> <BRANG> }
            | { with { <id> <BRANG> } <BRANG> }
            | <id>
            | { fun { <id> ... } <BRANG> }
            | { call <BRANG> { <BRANG> ... } }

Evaluation rules:
  eval(N,env)                = N
  eval({+ E1 E2},env)        = eval(E1,env) + eval(E2,env)
  eval({- E1 E2},env)        = eval(E1,env) - eval(E2,env)
  eval({* E1 E2},env)        = eval(E1,env) * eval(E2,env)
  eval({/ E1 E2},env)        = eval(E1,env) / eval(E2,env)
  eval(x,env)                = lookup(x,env)
  eval({with {x E1} E2},env) = eval(E2,extend(x,eval(E1,env),env))
  eval({fun {x} E},env)      = <{fun {x} E}, env>
  eval({call E1 E2},env1)
           = eval(Ef,extend(x,eval(E2,env1),env2))
                             if eval(E1,env1) = <{fun {x} Ef}, env2>
           = error!          otherwise
  eval(CRef(N),env) = list-ref(env,N)
|#

#|
The grammar:
  <CORE> ::= <cnum>
            | { + <CORE> <CORE> }
            | { - <CORE> <CORE> }
            | { * <CORE> <CORE> }
            | { / <CORE> <CORE> }
            | <cref>
            | { cfun  <CORE> }
            | { ccall <CORE> <CORE> }


|#

(define-type BRANG
  [Num  Number]
  [Add  BRANG BRANG]
  [Sub  BRANG BRANG]
  [Mul  BRANG BRANG]
  [Div  BRANG BRANG]
  [Id   Symbol]
  [With Symbol BRANG BRANG]
  [Fun  (Listof Symbol) BRANG]
  [Call BRANG (Listof BRANG)])


(define-type CORE
  [CNum  Number]
  [CAdd  CORE CORE]
  [CSub  CORE CORE]
  [CMul  CORE CORE]
  [CDiv  CORE CORE]
  [CRef  Natural]
  [CFun  CORE] 
  [CCall CORE CORE])

(: parse-sexpr : Sexpr -> BRANG)
;; to convert s-expressions into BRANGs
(define (parse-sexpr sexpr)
  (match sexpr
    [(number: n)    (Num n)]
    [(symbol: name) (Id name)]
    [(cons 'with more)
     (match sexpr
       [(list 'with (list (symbol: name) named) body)
        (With name (parse-sexpr named) (parse-sexpr body))]
       [else (error 'parse-sexpr "bad `with' syntax in ~s" sexpr)])]
    [(cons 'fun more)
     (match sexpr
       [(list 'fun (list (symbol: more-args) ...) body)
        (Fun more-args (parse-sexpr body))]
       [else (error 'parse-sexpr "bad `fun' syntax in ~s" sexpr)])]
    [(list '+ lhs rhs) (Add (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '- lhs rhs) (Sub (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '* lhs rhs) (Mul (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '/ lhs rhs) (Div (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list 'call fun (list (sexpr: more-args) ...)) 
     (Call (parse-sexpr fun) (map parse-sexpr more-args))]
    [else (error 'parse-sexpr "bad syntax in ~s" sexpr)]))

(: parse : String -> BRANG)
;; parses a string containing a BRANG expression to a BRANG AST
(define (parse str)
  (parse-sexpr (string->sexpr str)))

;; Types for environments, values, and a lookup function
(define-type ENV = (Listof VAL))


;; Types for Values 
(define-type VAL
  [NumV Number]
  [FunV CORE ENV]) 

;; Types for definition env
(define-type DE-ENV = Symbol -> Natural)

(: env-extend : VAL ENV -> ENV)
;; Extend the DE-ENV
(define (env-extend v e)
  (cases v
    [(NumV n) (cons (NumV n) e)]
    [(FunV body e) (cons (FunV body e) e)]))


(: de-empty-env : Symbol -> Natural)
;; empty DE-ENV
;; always throw an error 
(define (de-empty-env x)
  (error 'de-empty-env "symbol does not exist"))


(: de-extend : DE-ENV Symbol -> (Symbol -> Natural))
;; Non-empty DE-ENV
(define (de-extend e x)
  (lambda (y)
    (if (symbol=? x y) 0 (add1 (e y)))))

(: super-extend : DE-ENV (Listof Symbol) -> DE-ENV)
;; Adding a Listof Symbols into DE-ENV
(define (super-extend e los)
  (if (null? los) e (super-extend (de-extend e (first los)) (rest los))))

(: fun-curry : (Listof Symbol) CORE -> CORE)
;; Currify for a function
(define (fun-curry los c)
  (if (null? los) c (CFun (fun-curry (rest los) c))))

(: currify : (Listof CORE) CORE -> CORE)
;; Currufy the function
(define (currify loc f)
  (if (null? loc) f (CCall (currify (rest loc) f) (first loc))))

(: preprocess : BRANG DE-ENV -> CORE)
;; Translate BRANG into CORE
(define (preprocess expr e)
  (cases expr
    [(Num n) (CNum n)]
    [(Add l r) (CAdd (preprocess l e) (preprocess r e))]
    [(Sub l r) (CSub (preprocess l e) (preprocess r e))]
    [(Mul l r) (CMul (preprocess l e) (preprocess r e))]
    [(Div l r) (CDiv (preprocess l e) (preprocess r e))]
    [(With bound-id named-expr bound-body)
     (CCall (preprocess (Fun (list bound-id) bound-body) e) 
            (preprocess named-expr e))]
    [(Id s) (CRef (e s))]
    [(Fun bound-ids bound-body)
     (fun-curry bound-ids (preprocess bound-body (super-extend e bound-ids)))]
    [(Call fun-expr arg-exprs) 
     (let ([p-arg-exprs (map (lambda ([ae : BRANG]) (preprocess ae e)) 
                             arg-exprs)]
           [p-fun-expr (preprocess fun-expr e)])
       (currify p-arg-exprs p-fun-expr))]))

(: NumV->number : VAL -> Number)
;; convert a BRANG runtime numeric value to a Racket one
(define (NumV->number v)
  (cases v
    [(NumV n) n]
    [else (error 'arith-op "expected a number, got: ~s" v)]))

(: arith-op : (Number Number -> Number) VAL VAL -> VAL)
;; gets a Racket numeric binary operator, and uses it within a NumV
;; wrapper
(define (arith-op op val1 val2)
  (NumV (op (NumV->number val1) (NumV->number val2))))

(: eval : CORE ENV -> VAL)
;; evaluates BRANG expressions by reducing them to values
(define (eval expr env)
  (cases expr
    [(CNum n) (NumV n)]
    [(CAdd l r) (arith-op + (eval l env) (eval r env))]
    [(CSub l r) (arith-op - (eval l env) (eval r env))]
    [(CMul l r) (arith-op * (eval l env) (eval r env))]
    [(CDiv l r) (arith-op / (eval l env) (eval r env))]
    [(CRef index) (list-ref env index)]
    [(CFun bound-body)
     (FunV bound-body env)]
    [(CCall fun-expr arg-expr)
     (let ([fval (eval fun-expr env)]
           [aval (eval arg-expr env)])
       (cases fval
         [(FunV bound-body f-env) (eval bound-body (env-extend aval f-env))]
         [(NumV n) (NumV n)]))]))

(: run : String -> Number)
;; evaluate a BRANG program contained in a string
(define (run str)
  (let ([result (eval (preprocess (parse str) de-empty-env) '())])
    (cases result
      [(NumV n) n]
      [else (error 'run
                   "evaluation returned a non-number: ~s" result)])))

;; tests
(test (run "{call {fun {x} {+ x 1}} {4}}")
      => 5)
(test (run "{with {add3 {fun {x} {+ x 3}}}
              {call add3 {1}}}")
      => 4)
(test (run "{with {add3 {fun {x} {+ x 3}}}
              {with {add1 {fun {x} {+ x 1}}}
                {with {x 3}
                  {call add1 {{call add3 {x}}}}}}}")
      => 7)
(test (run "{with {identity {fun {x} x}}
              {with {foo {fun {x} {+ x 1}}}
                {call {call identity {foo}} {123}}}}")
      => 124)
(test (run "{with {x 3}
              {with {f {fun {y} {+ x y}}}
                {with {x 5}
                  {call f {4}}}}}")
      => 7)
(test (run "{call {with {x 3}
                    {fun {y} {+ x y}}}
                  {4}}")
      => 7)
(test (run "{call {call {fun {x} {call x {1}}}
                        {{fun {x} {fun {y} {+ x y}}}}}
                  {123}}")
      => 124)
(test (run "{with {q x 3} q}") =error> "bad")
(test (run "{fun {6 x} x}") =error> "bad")
(test (run "{eli 3}") =error> "bad")
(test (run "{- 8 4}") => 4)
(test (run "{* 8 4}") => 32)
(test (run "{/ 8 4}") => 2)
(test (run "{with {x 5} {+ y 7}}") =error> "symbol")
(test (run "{with {add3 {fun {x} {+ x 3}}}
              {with {add1 {fun {x} {+ x 1}}}
                {+ add1 add3}}}") =error> "expected")
(test (run "{with {foo {fun {x} 5}}
                  {call foo {6}}}") => 5)
(test (run "{call 5 {6}}") => 5)
(test (run "{with {foo {fun {x y} {+ x y}}}
                  {call foo {2 3}}}") => 5)
(test (run "{with {foo {fun {x y} {+ x y}}} foo}") =error> "non-number")

(define minutes-spent 240)