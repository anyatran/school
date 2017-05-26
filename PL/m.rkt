#lang pl un

#|

<X3Y> ::= (nothing)
          | X <X3Y> Y
          | X <X3Y> YY
          | X <X3Y> YYY


|#
(define blank1 (* 660 615243))
(/ blank1 615243) 
;impossible???
;(or (and) (blank))
(define (blank3 n) (lambda (n) 660))
((blank3 blank3) blank3)
(define (blank4 n) 660)
;(blank4 (/ 1 0))
(define blank5 (lambda (m n) 660))
(let ([+ blank5]) (+ blank5 blank5))

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

