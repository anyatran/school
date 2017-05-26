#lang pl 05

#| BNF for the ALGAE language:
     <ALGAE> ::= <num>
               | { + <ALGAE> ... }
               | { * <ALGAE> ... }
               | { - <ALGAE> <ALGAE> ... }
               | { / <ALGAE> <ALGAE> ... }
               | { with { <id> <ALGAE> } <ALGAE> }
               | { if <ALGAE> <ALGAE> <ALGAE> }
               | <id>
               | { < <ALGAE> <ALGAE>}
               | { = <ALGAE> <ALGAE> }
               | { <= <ALGAE> <ALGAE> }
               | { not <ALGAE> }
               | { and <ALGAE> <ALGAE> ... }
               | { or <ALGAE> <ALGAE> ... }
|#

;; ALGAE abstract syntax trees
(define-type ALGAE
  [Num  Number]
  [Bool Boolean]
  [Add  (Listof ALGAE)]
  [Mul  (Listof ALGAE)]
  [Sub  ALGAE (Listof ALGAE)]
  [Div  ALGAE (Listof ALGAE)]
  [Less ALGAE ALGAE]
  [Equal ALGAE ALGAE]
  [LessEq ALGAE ALGAE]
  [Id   Symbol]
  [With Symbol ALGAE ALGAE]
  [If ALGAE ALGAE ALGAE])

(: Not : ALGAE -> ALGAE)
;; hack type for not
(define (Not expr)
  (If expr (Bool #f) (Bool #t)))

(: And : ALGAE ALGAE -> ALGAE)
;; hack type for and
(define (And expr1 expr2)
  (define (and-helper expr1 expr2 args)
    (If expr1 
        (If expr2 (Bool #t) (Bool #f))
        (Bool #f)))

(: Or : ALGAE ALGAE -> ALGAE)
;; hack type for or
(define (Or expr1 expr2)
  (If expr1 
      (Bool #t)
      (If expr2 (Bool #t) (Bool #f))))


(: parse-sexpr : Sexpr -> ALGAE)
;; to convert s-expressions into ALGAEs
(define (parse-sexpr sexpr)
  ;; utility for parsing a list of expressions
  (: parse-sexprs : (Listof Sexpr) -> (Listof ALGAE))
  (define (parse-sexprs sexprs) (map parse-sexpr sexprs))
  (match sexpr
    [(number: n)    (Num n)]
    [(symbol: name) 
     (cond
       [(symbol=? name 'True) (Bool #t)]
       [(symbol=? name 'False) (Bool #f)]
       [else (Id name)])]
    [(cons 'with more)
     (match sexpr
       [(list 'with (list (symbol: name) named) body)
        (With name (parse-sexpr named) (parse-sexpr body))]
       [else (error 'parse-sexpr "bad `with' syntax in ~s" sexpr)])]
    [(cons 'if more)
     (match sexpr
       [(list 'if pred texpr fexpr)
        (If (parse-sexpr pred) (parse-sexpr texpr) (parse-sexpr fexpr))]
       [else (error 'parse-sexpr "bad `if' syntax in ~s" sexpr)])]
    [(list '+ (sexpr: args) ...) (Add (parse-sexprs args))]
    [(list '* (sexpr: args) ...) (Mul (parse-sexprs args))]
    [(list '- fst (sexpr: args) ...)
     (Sub (parse-sexpr fst) (parse-sexprs args))]
    [(list '/ fst (sexpr: args) ...)
     (Div (parse-sexpr fst) (parse-sexprs args))]
    [(list '< fst snd)
     (Less (parse-sexpr fst) (parse-sexpr snd))]
    [(list '= fst snd)
     (Equal (parse-sexpr fst) (parse-sexpr snd))]
    [(list '<= fst snd)
     (LessEq (parse-sexpr fst) (parse-sexpr snd))]
    [(list 'not fst) (Not (parse-sexpr fst))]
    [(list 'and fst snd) (And (parse-sexpr fst) (parse-sexpr snd))]
    [(list 'or fst snd) (Or (parse-sexpr fst) (parse-sexpr snd))]
    [else (error 'parse-sexpr "bad syntax in ~s" sexpr)]))


(: parse : String -> ALGAE)
;; parses a string containing an ALGAE expression to an ALGAE AST
(define (parse str)
  (parse-sexpr (string->sexpr str)))

#| Formal specs for `subst':
   (`N' is a <num>, 'B' is a boolean `E1', `E2' are <ALGAE>s, `x' is some <id>, 
    `y' is a *different* <id>)
      N[v/x]                = N
      B[v/x]                = B
      {+ E ...}[v/x]        = {+ E[v/x] ...}
      {* E ...}[v/x]        = {* E[v/x] ...}
      {- E1 E ...}[v/x]     = {- E1[v/x] E[v/x] ...}
      {/ E1 E ...}[v/x]     = {/ E1[v/x] E[v/x] ...}
      {< E1 E2}[v/x]  = {< E1[v/x] E2[v/x] }
      {= E1 E2}[v/x]  = {= E1[v/x] E2[v/x] }
      {<= E1 E2}[v/x]  = {<= E1[v/x] E2[v/x] }
      y[v/x]                = y
      x[v/x]                = v
      {with {y E1} E2}[v/x] = {with {y E1[v/x]} E2[v/x]}
      {with {x E1} E2}[v/x] = {with {x E1[v/x]} E2}
      {if E1 E2 E3}[v/x] = {if E1[v/x] E2[v/x] E3[v/x]}
|#

(: subst : ALGAE Symbol ALGAE -> ALGAE)
;; substitutes the second argument with the third argument in the
;; first argument, as per the rules of substitution; the resulting
;; expression contains no free instances of the second argument
(define (subst expr from to)
  ;; convenient helper -- no need to specify `from' and `to'
  (: subst* : ALGAE -> ALGAE)
  (define (subst* x) (subst x from to))
  ;; helper to substitute lists
  (: substs* : (Listof ALGAE) -> (Listof ALGAE))
  (define (substs* exprs) (map subst* exprs))
  (cases expr
    [(Num n)        expr]
    [(Bool b)       expr]
    [(Add args)     (Add (substs* args))]
    [(Mul args)     (Mul (substs* args))]
    [(Sub fst args) (Sub (subst* fst) (substs* args))]
    [(Div fst args) (Div (subst* fst) (substs* args))]
    [(Less fst snd) (Less (subst* fst) (subst* snd))]
    [(Equal fst snd) (Equal (subst* fst) (subst* snd))]
    [(LessEq fst snd) (LessEq (subst* fst) (subst* snd))]
    [(Id name)      (if (eq? name from) to expr)]
    [(With bound-id named-expr bound-body)
     (With bound-id
           (subst* named-expr)
           (if (eq? bound-id from)
               bound-body
               (subst* bound-body)))]
    [(If pred texpr fexpr) (If (subst* pred) (subst* texpr) (subst* fexpr))]))

#| Formal specs for `eval':
     eval(N)            = N
     eval(B)            = B
     eval({+ E ...})    = evalN(E) + ...
     eval({* E ...})    = evalN(E) * ...
     eval({- E})        = -evalN(E)
     eval({/ E})        = 1/evalN(E)
     eval({- E1 E ...}) = evalN(E1) - (evalN(E) + ...)
     eval({/ E1 E ...}) = evalN(E1) / (evalN(E) * ...)
     eval(id)           = error!
     eval({with {x E1} E2}) = eval(E2[eval(E1)/x])
     evalN(E) = eval(E) if it is a number, error otherwise
     eval({if E1 E2 E3}) = if evalB(E1): evalN(E2) else evalN(E3)
     evalB(E) = eval(E) if it is a boolean, error otherwise
     eval({Not E}) = eval({if E False True})
     eval({And E1 E2} = eval({if E1 {if E2 True False} False})
     eval({Or E1 E2} = eval({if E1 True {if E2 True False}})
|#

(: eval-number : ALGAE -> Number)
;; helper for `eval': verifies that the result is a number
(define (eval-number expr)
  (let ([result (eval expr)])
    (if (number? result)
        result
        (error 'eval-number "need a number when evaluating ~s, but got ~s"
               expr result))))

(: eval-boolean : ALGAE -> Boolean)
;; helper for `eval': verifies that the result is a boolean
(define (eval-boolean expr)
  (let ([result (eval expr)])
    (if (boolean? result)
        result
        (error 'eval-boolean "need a boolean when evaluating ~s, but got ~s"
               expr result))))

(: value->algae : (U Number Boolean) -> ALGAE)
;; converts a value to an ALGAE value (so it can be used with `subst')
(define (value->algae val)
  (cond [(number? val) (Num val)]
        [(boolean? val) (Bool val)]
        ; Note: since we use Typed Racket, the type checker makes sure
        ;; that this function is never called with something that is not
        ;; in its type, so there's no need for an `else' branch.
        ;; (Strictly speaking, there's no need for the last predicate
        ;; (which is the only one here until you extend this), but it's
        ;; left in for clarity)
        ;; [else (error 'value->algae "unexpected value: ~s" val)]
        ))
;; The following test is also not needed.  In the untyped version, it
;; was needed because the error could not be achieved through `eval' --
;; which is exactly why the above type works.
;; ;; test for an otherwise unreachable error:
;; (test (value->algae null) =error> "unexpected value")


(: multneg1 : Number -> Number)
;; multiply the number by -1
(define (multneg1 n)
  (* n -1))

(: div1 : Number -> Number)
;; divide 1 by  the number
(define (div1 n)
  (/ 1 n))


(: eval : ALGAE -> (U Number Boolean))
;; evaluates ALGAE expressions by reducing them to numbers
(define (eval expr)
  (cases expr
    [(Num n) n]
    [(Bool b) b]
    [(Add args) (foldl + 0 (map eval-number args))]
    [(Mul args) (foldl * 1 (map eval-number args))]
    [(Sub fst args)
     (if (null? args)
         (multneg1 (eval-number fst))
         (foldl + 0 (cons (eval-number fst) 
                          (map multneg1 (map eval-number args)))))]
    [(Div fst args)
     (if (ormap zero? (map eval-number args))
         (error 'eval-number "division by 0")
         (if (null? args)
             (div1 (eval-number fst))
             (foldl * 1 (cons (eval-number fst) 
                              (map div1 (map eval-number args))))))]
    [(Less fst snd) (< (eval-number fst) (eval-number snd))]
    [(Equal fst snd) (= (eval-number fst) (eval-number snd))]
    [(LessEq fst snd) (<= (eval-number fst) (eval-number snd))]   
    [(With bound-id named-expr bound-body)
     (eval (subst bound-body
                  bound-id
                  ;; see the above `value->algae' helper
                  (value->algae (eval named-expr))))]
    [(If pred texpr fexpr)
     (if (eval-boolean pred) (eval texpr) (eval fexpr))]
    [(Id name) (error 'eval "free identifier: ~s" name)]))

(: run : String -> (U Number Boolean))
;; evaluate an ALGAE program contained in a string
(define (run str)
  (eval (parse str)))

;; tests
(test (run "5") => 5)
(test (run "{+ 5 5}") => 10)
(test (run "{with {x {+ 5 5}} {+ x x}}") => 20)
(test (run "{with {x 5} {+ x x}}") => 10)
(test (run "{with {x {+ 5 5}} {with {y {- x 3}} {+ y y}}}") => 14)
(test (run "{with {x 5} {with {y {- x 3}} {+ y y}}}") => 4)
(test (run "{with {x 5} {+ x {with {x 3} 10}}}") => 15)
(test (run "{with {x 5} {+ x {with {x 3} x}}}") => 8)
(test (run "{with {x 5} {+ x {with {y 3} x}}}") => 10)
(test (run "{with {x 5} {with {y x} y}}") => 5)
(test (run "{with {x 5} {with {x x} x}}") => 5)
(test (run "{with {2 5} {with {x x} x}}") 
      =error> "bad `with' syntax in (with (2 5) (with (x x) x))")
(test (run "{* 2 4}") => 8)
(test (run "{/ 8 4}") => 2)
(test (run "{/ 15 5}") => 3)
(test (run "{- 15 5}") => 10)
(test (run "{2 2 2}") =error> "bad syntax in (2 2 2)")
(test (run "{with {x 5} {* x x}}") => 25)
(test (run "{with {x 5} {/ x x}}") => 1)
(test (run "{with {x 3} {/ y x}}") =error> "free identifier: y")
(test (run "{/ 9 0}") =error> "division by 0")
(test (run "{+ 5 3 2}") => 10)
(test (run "{+}") => 0)
(test (run "{- 5}") => -5)
(test (run "{*}") => 1)
(test (run "{/ 3}") => 1/3)
(test (run "{if {< 3 4} 666 000}") => 666)
(test (run "{if {<= 8 4} 666 000}") => 000)
(test (run "{if {= 3 4} 666 000}") => 000)
(test (run "True") => #t)
(test (run "False") => #f)
(test (run "{< 2 0}") => #f)
(test (run "{<= 2 5}") => #t)
(test (run "{= 2 5}") => #f)
(test (run "{= 2 2}") => #t)
(test (run "{with {x 5} {= 2 x}}") => #f)
(test (run "{with {x 5} {< x x}}") => #f)
(test (run "{with {x 5} {<= x 2}}") => #f)
(test (run "{with {x 5} {if True 6 x}}") => 6)
(test (run "{+ 3 True}") 
      =error> "need a number when evaluating (Bool #t), but got #t")
(test (run "{if 2 3 4}") 
      =error> "need a boolean when evaluating (Num 2), but got 2")
(test (run "{with {x True} x}") => #t)
(test (run "{not True}") => #f)
(test (run "{and True True}") => #t)
(test (run "{or True False}") => #t)
(test (run "{if True 6}") =error> "bad `if' syntax in (if True 6)")

(define minutes-spent 180)