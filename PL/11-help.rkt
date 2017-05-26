#lang pl un



#;(define (interleave el l x)
  (cond
    [(null? l) (list (append el (list x)))]
    [else
     (cons (append el (cons x l)) 
           (interleave (append el (list (first l))) (rest l) x))]))

(define interleave
  (lambda (el)
    (lambda (l)
      (lambda (x)
        (cond
          [(null? l) (list (append el (list x)))]
          [else
           (cons (append el (cons x l)) 
                 (interleave (append el (list (first l)))) (rest l) x))])))))

(define l123 (list 1 2 3))
(define n "x")
(((interleave '()) l123) n)