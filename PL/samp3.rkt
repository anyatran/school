;; ---< The DANG interpreter, using environments >---

#lang pl

#|
The grammar:
  <DANG::= <num>
            | { + <DANG> <DANG> }
            | { - <DANG> <DANG> }
            | { * <DANG> <DANG> }
            | { / <DANG> <DANG> }
            | { with { <id> <DANG> } <DANG> }
            | { dynamic { <id> <DANG> } <DANG> }
            | <id>
            | { fun { <id> } <DANG> }
            | { call <DANG> <DANG> }

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
|#

(define-type DANG
  [Num  Number]
  [Add  DANG DANG]
  [Sub  DANG DANG]
  [Mul  DANG DANG]
  [Div  DANG DANG]
  [Id   Symbol]
  [With Symbol DANG DANG]
  [Dynamic Symbol DANG DANG]
  [Fun  Symbol DANG]
  [Call DANG DANG])

(: parse-sexpr : Sexpr -> DANG)
;; to convert s-expressions into DANGs
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
       [(list 'fun (list (symbol: name)) body)
        (Fun name (parse-sexpr body))]
       [else (error 'parse-sexpr "bad `fun' syntax in ~s" sexpr)])]
    [(list '+ lhs rhs) (Add (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '- lhs rhs) (Sub (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '* lhs rhs) (Mul (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '/ lhs rhs) (Div (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list 'call fun arg) (Call (parse-sexpr fun) (parse-sexpr arg))]
    [else (error 'parse-sexpr "bad syntax in ~s" sexpr)]))

(: parse : String -> DANG)
;; parses a string containing a DANG expression to a DANG AST
(define (parse str)
  (parse-sexpr (string->sexpr str)))

;; Types for environments, values, and a lookup function

(define-type ENV
  [EmptyEnv]
  [Extend Symbol VAL ENV])

(define-type VAL
  [NumV Number]
  [FunV Symbol DANG ENV])

(: lookup : Symbol ENV (U #f ENV) -> VAL)
;; extended with an optional second fallback environment
(define (lookup name env other-env)
  (cases env
    [(EmptyEnv)
     (if other-env
       (lookup name other-env #f)
       (error 'lookup "no binding for ~s" name))]
    [(Extend id val rest-env)
     (if (eq? id name) val (lookup name rest-env other-env))]))

(: NumV->number : VAL -> Number)
;; convert a DANG runtime numeric value to a Racket one
(define (NumV->number v)
  (cases v
    [(NumV n) n]
    [else (error 'arith-op "expected a number, got: ~s" v)]))

(: arith-op : (Number Number -> Number) VAL VAL -> VAL)
;; gets a Racket numeric binary operator, and uses it within a NumV
;; wrapper
(define (arith-op op val1 val2)
  (NumV (op (NumV->number val1) (NumV->number val2))))

(: eval : DANG ENV -> VAL)
;; evaluates DANGexpressions by reducing them to values
(define (eval expr env dyn-env)
  (cases expr
    [(Num n) (NumV n)]
    [(Add l r) (arith-op + (eval l env dyn-env) (eval r env dyn-env))]
    [(Sub l r) (arith-op - (eval l env dyn-env) (eval r env dyn-env))]
    [(Mul l r) (arith-op * (eval l env dyn-env) (eval r env dyn-env))]
    [(Div l r) (arith-op / (eval l env dyn-env) (eval r env dyn-env))]
    [(With bound-id named-expr bound-body)
     (eval bound-body
           (Extend bound-id (eval named-expr env dyn-env) env) dyn-env)]
    [(Id name) (lookup name env dyn-env)]
    [(Dynamic bound-id named-expr bound-body)
     (eval bound-body env
           (Extend bound-id (eval named-expr env dyn-env) dyn-env))]
    [(Fun bound-id bound-body)
     (FunV bound-id bound-body env)]
    [(Call fun-expr arg-expr)
     (let ([fval (eval fun-expr env)])
       (cases fval
         [(FunV bound-id bound-body f-env)
          (eval bound-body
                (Extend bound-id (eval arg-expr env) f-env))]
         [else (error 'eval "`call' expects a function, got: ~s"
                            fval)]))]))

(: subst-dyn-ref : DANG (Symbol -> DANG) -> DANG)
(define (subst-dyn-ref expr f)
   (cases expr
    [(Num n) n]
    [(Add l r) (arith-op + (subst-dyn-ref l f) (subst-dyn-ref r f))]
    [(Sub l r) (arith-op - (subst-dyn-ref l f) (subst-dyn-ref r f))]
    [(Mul l r) (arith-op * (subst-dyn-ref l f) (subst-dyn-ref r f))]
    [(Div l r) (arith-op / (subst-dyn-ref l f) (subst-dyn-ref r f))]
    [(With bound-id named-expr bound-body)
     (subst-dyn-ref bound-body
           (Extend bound-id (eval named-expr env dyn-env) env) dyn-env)]
    [(Id name) (lookup name env dyn-env)]
    [(Dynamic bound-id named-expr bound-body)
     (eval bound-body env
           (Extend bound-id (eval named-expr env dyn-env) dyn-env))]
    [(Fun bound-id bound-body)
     (FunV bound-id bound-body env)]
    [(Call fun-expr arg-expr)
     (let ([fval (eval fun-expr env)])
       (cases fval
         [(FunV bound-id bound-body f-env)
          (eval bound-body
                (Extend bound-id (eval arg-expr env) f-env))]
         [else (error 'eval "`call' expects a function, got: ~s"
                            fval)]))]))

(: run : String -> Number)
;; evaluate a DANG program contained in a string
(define (run str)
  (let ([result (eval (parse str) (EmptyEnv))])
    (cases result
      [(NumV n) n]
      [else (error 'run
                   "evaluation returned a non-number: ~s" result)])))

;; tests
(test (run "{dynamic {x 1} x}") => 1)
(test (run "{dynamic {x 1}
              {call {with {x 2} {fun {y} {+ x y}}}
                    10}}")
      => 12)
(test (run "{dynamic {x 1}
              {call {dynamic {x 2} {fun {y} {+ x y}}}
                    10}}")
      => 11)
(test (run "{with {x 1}
              {call {dynamic {x 2} {fun {y} {+ x y}}}
                    10}}")
      => 11)
(test (run "{dynamic {x 1}
              {with {f {with {x 2} {fun {y} {+ x y}}}}
                {dynamic {x 3}
                  {call f 10}}}}")
      => 12)
(test (run "{dynamic {x 1}
              {with {f {dynamic {x 2} {fun {y} {+ x y}}}}
                {dynamic {x 3}
                  {call f 10}}}}")
      => 13)
(test (run "{dynamic {x 1}
              {with {f {dynamic {x 2} {fun {y} {+ x y}}}}
                {with {x 3}
                  {call f 10}}}}")
      => 11)

(test (run "{with {x 1} {dynamic {x 2} x}}") => 1)

(test (run "{with {f {fun {y} {+ x y}}}
              {with {x 1}
                {dynamic {x 2} {call f 10}}}}")
      => 12)