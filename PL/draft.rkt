#lang pl un
(define l (list (box 1) (box 2) (box 3) (box 4)))
(define a (list 9 8 7 6))
(for-each (lambda (x y) (set-box! x y)  3) l a)

(define b (list 1 2 3 4 5 6))
(for-each (lambda (z) (set-box! (box z) (+ z 10))) b)

(define c (list "a" "b" "q" "S"))
(andmap (lambda (x) (number->string x)) b)

(last b)