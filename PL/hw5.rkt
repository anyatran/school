#lang pl 05

#|   <PROGRAM> ::= { program <FUN> ... }
     <FUN>     ::= { fun <id> { <id> } <ALGAE> }
   BNF for the ALGAE language:
     <ALGAE> ::= <num>
               | { + <ALGAE> ... }
               | { * <ALGAE> ... }
               | { - <ALGAE> <ALGAE> ... }
               | { / <ALGAE> <ALGAE> ... }
               | { with { <id> <ALGAE> } <ALGAE> }
               | <id>
               | True
               | False
               | { < <ALGAE> <ALGAE> }
               | { = <ALGAE> <ALGAE> }
               | { <= <ALGAE> <ALGAE> }
               | { if <ALGAE> <ALGAE> <ALGAE> }
               | { not <ALGAE> }
               | { and <ALGAE> ... }
               | { or <ALGAE> ... }
|#
;; PROGRAM abstract tree
(define-type PROGRAM
  [Funs Function])

;; FUN abstract tree
(define-type FUN ; 
  [Fun Symbol (Listof Symbol) ALGAE])




;; ALGAE abstract syntax trees
(define-type ALGAE
  [Num    Number]
  [Bool   Boolean]
  [Add    (Listof ALGAE)]
  [Mul    (Listof ALGAE)]
  [Sub    ALGAE (Listof ALGAE)]
  [Div    ALGAE (Listof ALGAE)]
  [Id     Symbol]
  [With   Symbol ALGAE ALGAE]
  [Less   ALGAE ALGAE]
  [Equal  ALGAE ALGAE]
  [LessEq ALGAE ALGAE]
  [If     ALGAE ALGAE ALGAE])

(: parse-sexpr : Sexpr -> ALGAE)
;; to convert s-expressions into ALGAEs
(define (parse-sexpr sexpr)
  ;; utility for parsing a list of expressions
  (: parse-sexprs : (Listof Sexpr) -> (Listof ALGAE))
  (define (parse-sexprs sexprs) (map parse-sexpr sexprs))
  (match sexpr
    [(number: n) (Num n)]
    ['True  (Bool #t)] ; \ check these before the next
    ['False (Bool #f)] ; / case turns them into identifiers
    [(symbol: name) (Id name)]
    [(cons 'with more)
     (match sexpr
       [(list 'with (list (symbol: name) named) body)
        (With name (parse-sexpr named) (parse-sexpr body))]
       [else (error 'parse-sexpr "bad `with' syntax in ~s" sexpr)])]
    [(list '+ (sexpr: args) ...) (Add (parse-sexprs args))]
    [(list '* (sexpr: args) ...) (Mul (parse-sexprs args))]
    [(list '- fst (sexpr: args) ...)
     (Sub (parse-sexpr fst) (parse-sexprs args))]
    [(list '/ fst (sexpr: args) ...)
     (Div (parse-sexpr fst) (parse-sexprs args))]
    [(list '<  lhs rhs) (Less   (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '=  lhs rhs) (Equal  (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '<= lhs rhs) (LessEq (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list 'if cond then else)
     (If (parse-sexpr cond) (parse-sexpr then) (parse-sexpr else))]
    [(list 'and (sexpr: args) ...) (And (parse-sexprs args))]
    [(list 'or  (sexpr: args) ...) (Or  (parse-sexprs args))]
    [(list 'not arg)     (Not (parse-sexpr arg))]
    [else (error 'parse-sexpr "bad syntax in ~s" sexpr)]))

(: Not : ALGAE -> ALGAE)
;; Translates `{not E}' syntax to core Algae.
(define (Not expr)
  (If expr (Bool #f) (Bool #t)))

(: And : (Listof ALGAE) -> ALGAE)
;; And
(define (And args)
  (cond
    [(equal? 0 (length args)) (Bool #t)]
    [(not (eval-boolean (first args))) (Bool #f)]
    [else (And (rest args))]))

(: Or : (Listof ALGAE) -> ALGAE)
;; Or
(define (Or args)
  (cond
    [(equal? 0 (length args)) (Bool #f)]
    [(eval-boolean (first args)) (Bool #t)]
    [else (Or (rest args))]))

(: parse : String -> ALGAE)
;; parses a string containing an ALGAE expression to an ALGAE AST
(define (parse str)
  (parse-sexpr (string->sexpr str)))

#| Formal specs for `subst':
   (`N' is a <num>, `B' is True/False, `E1', `E2' are <ALGAE>s, `x' is
   some <id>, `y' is a *different* <id>)
      N[v/x]                = N
      B[v/x]                = B
      {+ E ...}[v/x]        = {+ E[v/x] ...}
      {* E ...}[v/x]        = {* E[v/x] ...}
      {- E1 E ...}[v/x]     = {- E1[v/x] E[v/x] ...}
      {/ E1 E ...}[v/x]     = {/ E1[v/x] E[v/x] ...}
      y[v/x]                = y
      x[v/x]                = v
      {with {y E1} E2}[v/x] = {with {y E1[v/x]} E2[v/x]}
      {with {x E1} E2}[v/x] = {with {x E1[v/x]} E2}
      {<  E1 E2}[v/x]       = {<  E1[v/x] E2[v/x]}
      {=  E1 E2}[v/x]       = {=  E1[v/x] E2[v/x]}
      {<= E1 E2}[v/x]       = {<= E1[v/x] E2[v/x]}
      {if E1 E2 E3}[v/x]    = {if E1[v/x] E2[v/x] E3[v/x]}
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
    [(Id name)      (if (eq? name from) to expr)]
    [(With bound-id named-expr bound-body)
     (With bound-id
           (subst* named-expr)
           (if (eq? bound-id from)
               bound-body
               (subst* bound-body)))]
    [(Less   lhs rhs) (Less   (subst* lhs) (subst* rhs))]
    [(Equal  lhs rhs) (Equal  (subst* lhs) (subst* rhs))]
    [(LessEq lhs rhs) (LessEq (subst* lhs) (subst* rhs))]
    [(If cond then else)
     (If (subst* cond) (subst* then) (subst* else))]))

#| Formal specs for `eval':
     eval(N)             = N
     eval(B)             = B
     eval({+ E ...})     = evalN(E) + ...
     eval({* E ...})     = evalN(E) * ...
     eval({- E})         = -evalN(E)
     eval({/ E})         = 1/evalN(E)
     eval({- E1 E ...})  = evalN(E1) - (evalN(E) + ...)
     eval({/ E1 E ...})  = evalN(E1) / (evalN(E) * ...)
     eval(id)            = error!
     eval({with {x E1} E2}) = eval(E2[eval(E1)/x])
     eval({< E1 E2})     = evalN(E1) < evalN(E2)
     eval({= E1 E2})     = evalN(E1) = evalN(E2)
     eval({<= E1 E2})    = evalN(E1) <= evalN(E2)
     eval({if E1 E2 E3}) = eval(E2)  if evalB(E1) is true
                         = eval(E3)  otherwise
     eval({not E})       = eval({if E False True})
     eval({and E1 E2})   = eval({if E1 E2 False})
     eval({or E1 E2})    = eval({if E1 True E2})
     evalN(E) = eval(E) if it is a number, error otherwise
     evalB(E) = eval(E) if it is a boolean, error otherwise
|#

(: eval-number : ALGAE -> Number)
;; helper for `eval': verifies that the result is a number
(define (eval-number expr)
  (let ([result (eval expr)])
    (if (number? result)
        result
        (error 'eval-number
               "need a number when evaluating ~s, but got ~s"
               expr result))))

(: eval-boolean : ALGAE -> Boolean)
;; helper for `eval': verifies that the result is a boolean
(define (eval-boolean expr)
  (let ([result (eval expr)])
    (if (boolean? result)
        result
        (error 'eval-boolean
               "need a boolean when evaluating ~s, but got ~s"
               expr result))))

(: value->algae : (U Number Boolean) -> ALGAE)
;; converts a value to an ALGAE value (so it can be used with `subst')
(define (value->algae val)
  (cond [(number?  val) (Num val)]
        [(boolean? val) (Bool val)]
        ;; Note: since we use Typed Racket, the type checker makes
        ;; sure that this function is never called with something that
        ;; is not in its type, so there's no need for an `else' branch
        ;; [else (error 'value->algae "unexpected value: ~s" val)]
        ))
;; The following test is also not needed.  In the untyped version, it
;; was needed because the error could not be achieved through `eval'
;; -- which is exactly why the above type works.
;; ;; test for an otherwise unreachable error:
;; (test (value->algae null) =error> "unexpected value")

(: eval : ALGAE -> (U Number Boolean))
;; evaluates ALGAE expressions by reducing them to numbers or booleans
(define (eval expr)
  ;; convenient helper
  (: fold-evals : (Number Number -> Number) Number (Listof ALGAE)
     -> Number)
  (define (fold-evals f init exprs)
    (foldl f init (map eval-number exprs)))
  (cases expr
    [(Num  n) n]
    [(Bool b) b]
    [(Add args) (fold-evals + 0 args)]
    [(Mul args) (fold-evals * 1 args)]
    [(Sub fst args)
     (let ([x (eval-number fst)])  ; need to evaluate in both cases
       (if (null? args) (- x) (- x (fold-evals + 0 args))))]
    [(Div fst args)
     (let ([x   (eval-number fst)] ; need to evaluate in both cases
           [div (fold-evals * 1 args)])
       (cond [(zero? (if (null? args) x div))
              (error '/ "division by zero error")]
             [(null? args) (/ x)]
             [else         (/ x div)]))]
    [(With bound-id named-expr bound-body)
     (eval (subst bound-body
                  bound-id
                  ;; see the above `value->algae' helper
                  (value->algae (eval named-expr))))]
    [(Id name) (error 'eval "free identifier: ~s" name)]
    [(Less   lhs rhs) (<  (eval-number lhs) (eval-number rhs))]
    [(Equal  lhs rhs) (=  (eval-number lhs) (eval-number rhs))]
    [(LessEq lhs rhs) (<= (eval-number lhs) (eval-number rhs))]
    [(If cond then else) (eval (if (eval-boolean cond) then else))]))

(: run : String -> (U Number Boolean))
;; evaluate an ALGAE program contained in a string
(define (run str)
  (eval (parse str)))

;; tests (for simple expressions)
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

;; additional tests for complete coverage (part 0)
(test (run "x") =error> "free identifier")
(test (run "{with {x 2} {/ 12 {* x 3}}}") => 2)
(test (run "{with}") =error> "bad `with' syntax")
(test (run "{foo}")  =error> "bad syntax")
(test (run "{}")     =error> "bad syntax")
(test (run "{/}")    =error> "bad syntax")

;; test Racket-like arithmetics
(test (run "{+}") => 0)
(test (run "{*}") => 1)
(test (run "{+ 10}") => 10)
(test (run "{* 10}") => 10)
(test (run "{- 10}") => -10)
(test (run "{/ 10}") => 1/10)
(test (run "{+ 1 2 3 4}") => 10)
(test (run "{* 1 2 3 4}") => 24)
(test (run "{- 10 1 2 3 4}") => 0)
(test (run "{/ 24 1 2 3 4}") => 1)
(test (run "{/ 1 0}") =error> "division by zero")

;; test boolean comparators and `if'
(test (run "{< 1 2}"))
(test (not (run "{= 1 2}")))
(test (run "{if {<= 4 4} 5 6}") => 5)
(test (run "{if True False 6}") => #f)
(test (run "{+ {< 1 2}}") =error> "need a number")
(test (run "{if 1 2 3}") =error> "need a boolean")
(test (run "{with {b {<= 4 5}} {if b b b}}") => #t)
(test (run "{with {x 5} {if {< x 5} {= x 4} {<= x 7}}}"))
(test (run "{with {b {= 3 4}} {with {x 5} {if b x x}}}") => 5)

;; test boolean extensions
;; (note how new tests use previously tested features)
(test (run "{not {< 2 1}}"))
(test (not (run "{not {not {< 2 1}}}")))
(test (run "{and True True}"))
(test (run "{not {and True False}}"))
(test (run "{not {and False True}}"))
(test (run "{not {and False False}}"))
(test (run "{and {and {or True True}
                      {or True False}}
                 {and {or False True}
                      {not {or False False}}}}"))
(test (run "{and False True False}") => #f)
(test (run "{and {not False} True {or False True}}") => #t)

;; test proper short-circuiting
(test (run "{or {/ 1 0} {< 1 2}}") =error> "division by zero")
(test (run "{or {< 1 2} {/ 1 0}}"))
(test (run "{not {and {/ 1 0} {< 2 1}}}") =error> "division by zero")
(test (run "{not {and {< 2 1} {/ 1 0}}}"))