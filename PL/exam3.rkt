#lang pl lazy
;(: make-averager : (-> Number) -> Number)
#|(define (make-averager)
  (let ([sum (box (list 0 0))])
    (lambda (num)
      (set-box! sum (list (+ (first (unbox sum)) num) 
                          (add1 (second (unbox sum)))))
      (/ (first (unbox sum)) (second (unbox sum))))))



(define avg1 (make-averager))
(define avg2 (make-averager))
(test (avg1 1.0) => 1.0)
(test (avg1 2.0) => 1.5)
(test (avg1 3.0) => 2.0)
(test (avg2 0.0) => 0.0) ; independent of the first

|#
(define (list-averages l)
  (define (helper acc sum leng)
    (if (null? l) 
        '() 
        (helper
         (cons (list (/ (+ sum (first l)) (add1 leng))) acc)
         (+ sum (first l))
         (add1 leng))))
  (helper l 0 0))


(test (list-averages '())            => '())
(test (list-averages '(1.0))         => '(1.0))
(test (list-averages '(1.0 2.0 3.0)) => '(1.0 1.5 2.0))

;(define ones (cons 1.0 ones))
;(define nats (cons 1.0 (map add1 nats)))
;(test (take 5 (list-averages ones)) => '(1.0 1.0 1.0 1.0 1.0))
;(test (take 5 (list-averages nats)) => '(1.0 1.5 2.0 2.5 3.0))

#lang racket
(define-syntax test
  (syntax-rules (=>)
    [(test E => R)
     (let ([value  E]
           [expect R])
       (unless (equal? value expect)
         (error 'test "failure in ~s, expected ~s, got ~s"
                (SHOW E) expect value)))]))

(define-syntax ? 
  (syntax-rules()
    [(? E) E]))

(define-syntax SHOW
  (syntax-rules (?)
    [(SHOW (? E) E]
[(SHOW E more) (
                
                (define x (box 10))
                (test (+ 1 (unbox x)) => 21))])

[(Or p1 p2) 
 (do-match xs
           p1
           success
           (lambda ()
             (do-match xs p2 success fail)))]


[(With bound-id named-expr bound-body)
     (let ([type2 (?T (box #f))]) ; and here
       (typecheck named-expr type2 type-env)
       (typecheck bound-body type
                  (ExtendTypeEnv bound-id type2 type-env)))]
[(Rec id named body)
 
 
           
