;; ---< The PUWAEAE interpreter >---

#lang pl 03

#| BNF for the PUWAE language:
     <PUWAE> ::= <num>
             | { + <PUWAE> <PUWAE> }
             | { - <PUWAE> <PUWAE> }
             | { * <PUWAE> <PUWAE> }
             | { / <PUWAE> <PUWAE> }
             | { with { <id> <PUWAE> } <PUWAE> }
             | <id>
             | { post <POST> }
|#

#| BNF for POST language
     <POST> ::= <PUWAE> 
            |  <POST> <POST> + 
            |  <POST> <POST> - 
            |  <POST> <POST> / 
            |  <POST> <POST> * 

|#

#| BNF for the PUWAE language:
     <PUWAE> ::= <num>
             | { + <PUWAE> <PUWAE> }
             | { - <PUWAE> <PUWAE> }
             | { * <PUWAE> <PUWAE> }
             | { / <PUWAE> <PUWAE> }
             | { with { <id> <PUWAE> } <PUWAE> }
             | <id>
             | { post <POST> ...}
|#

#| BNF for POST language
     <POST> ::= <PUWAE> 
            |  + 
            |  - 
            |  / 
            |  * 

|#


;; POST abstract syntax
(define-type PostfixItem = (U PUWAE '+ '- '* '/))


;; PUWAE abstract syntax trees
;; DONE******************
(define-type PUWAE
  [Num  Number]
  [Add  PUWAE PUWAE]
  [Sub  PUWAE PUWAE]
  [Mul  PUWAE PUWAE]
  [Div  PUWAE PUWAE]
  [Id   Symbol]
  [With Symbol PUWAE PUWAE]
  [Post (Listof PostfixItem)])


(: parse-post-item : Sexpr -> PostfixItem)
;; parse an s-expression to a post-item
(define (parse-post-item x)
  (match x 
    ['+ '+] 
    ['- '-] 
    ['* '*] 
    ['/ '/] 
    [else (parse-sexpr x)]))


(: parse-sexpr : Sexpr -> PUWAE)
;; to convert s-expressions into PUWAEAEs
;; ELSE FOR POSTTTTTTT
(define (parse-sexpr sexpr)
  (match sexpr
    [(number: n)    (Num n)]
    [(symbol: name) (Id name)]
    [(cons 'with more)
     (match sexpr
       [(list 'with (list (symbol: name) named) body)
        (With name (parse-sexpr named) (parse-sexpr body))]
       [else (error 'parse-sexpr "bad `with' syntax in ~s" sexpr)])]
    [(list '+ lhs rhs) (Add (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '- lhs rhs) (Sub (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '* lhs rhs) (Mul (parse-sexpr lhs) (parse-sexpr rhs))]
    [(list '/ lhs rhs) (Div (parse-sexpr lhs) (parse-sexpr rhs))]
    [(cons 'post more) (Post (map parse-post-item more))] ;;;;;;;;;;;;;;;;;
    [else (error 'parse-sexpr "bad syntax in ~s" sexpr)]))

(: parse : String -> PUWAE)
;; parses a string containing a PUWAE expression to a PUWAE AST
(define (parse str)
  (parse-sexpr (string->sexpr str)))

#| Formal specs for `subst':
   (`N' is a <num>, `E1', `E2' are <PUWAE>s, `x' is some <id>, `y' is a
   *different* <id>)
      N[v/x]                = N
      {+ E1 E2}[v/x]        = {+ E1[v/x] E2[v/x]}
      {- E1 E2}[v/x]        = {- E1[v/x] E2[v/x]}
      {* E1 E2}[v/x]        = {* E1[v/x] E2[v/x]}
      {/ E1 E2}[v/x]        = {/ E1[v/x] E2[v/x]}
      y[v/x]                = y
      x[v/x]                = v
      {with {y E1} E2}[v/x] = {with {y E1[v/x]} E2[v/x]}
      {with {x E1} E2}[v/x] = {with {x E1[v/x]} E2}
    fgjkdhsfkjghsdkfbrshtgb rufkgjchgkjdfchxktdfgjx
|#

(: subst : PUWAE Symbol PUWAE -> PUWAE)
;; substitutes the second argument with the third argument in the
;; first argument, as per the rules of substitution; the resulting
;; expression contains no free instances of the second argument
(define (subst expr from to)
  (: post-subst : PUWAE -> PUWAE)
  (define (post-subst item)
    (if (symbol? item)
        (subst item from to)
        item))
  
  (cases expr
    [(Num n) expr]
    [(Add l r) (Add (subst l from to) (subst r from to))]
    [(Sub l r) (Sub (subst l from to) (subst r from to))]
    [(Mul l r) (Mul (subst l from to) (subst r from to))]
    [(Div l r) (Div (subst l from to) (subst r from to))]
    [(Id name) (if (eq? name from) to expr)]
    [(With bound-id named-expr bound-body)
     (With bound-id
           (subst named-expr from to)
           (if (eq? bound-id from)
               bound-body
               (subst bound-body from to)))]
    [(Post post-body) (map post-subst post-body)]))
 

#| Formal specs for `eval':
     eval(N)         = N
     eval({+ E1 E2}) = eval(E1) + eval(E2)
     eval({- E1 E2}) = eval(E1) - eval(E2)
     eval({* E1 E2}) = eval(E1) * eval(E2)
     eval({/ E1 E2}) = eval(E1) / eval(E2)
     eval(id)        = error!
     eval({with {x E1} E2}) = eval(E2[eval(E1)/x])
|#

(: eval : PUWAE -> Number)
;; evaluates PUWAE expressions by reducing them to numbers
(define (eval expr)
  (: post-eval : (Listof PUWAE) (Listof PUWAE) -> Number)
     ;; evaluates a postfix sequence of items, using a stack
     (define (post-eval items stack)
       (if (null? items) ;; empty list of postfixes
           (match stack 
             [(number: l) (error 'err "bullshit")] ;; the stack is full
             [else (error 'post-eval "fuck")])
           (let ([1st  (first items)]
                 [more (rest items)])
             (: pop2-and-apply : (Number Number -> Number) -> Number)
             (define (pop2-and-apply б)
               (match stack 
                 [(list a b) (б (eval a) (eval b))]
                 [else (error 'ывраплоыфрвпалорыфвпалофры "asdjfhkasjdhf")]))
             
             (cond 
               [(eq? '+ 1st) (pop2-and-apply +)]
               [(eq? '- 1st) (pop2-and-apply -)]
               [(eq? '/ 1st) (pop2-and-apply /)]
               [(eq? '* 1st) (pop2-and-apply *)]
               [else (post-eval more '())]))))
     (cases expr
       [(Num n) n]
       [(Add l r) (+ (eval l) (eval r))]
       [(Sub l r) (- (eval l) (eval r))]
       [(Mul l r) (* (eval l) (eval r))]
       [(Div l r) (/ (eval l) (eval r))]
       [(With bound-id named-expr bound-body)
        (eval (subst bound-body
                     bound-id
                     (Num (eval named-expr))))]
       [(Id name) (error 'eval "free identifier: ~s" name)]
       [(Post post-body) (post-eval post-body '())]))
  ;; 
  (: run : String -> Number)
  ;; evaluate a PUWAE program contained in a string
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
  (test (run "{with {x 1} y}") =error> "free identifier")
  