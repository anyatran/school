#lang pl 02
; Anya Tran
; anya


; Problem 1
;; creates a list of values starts with first and ends with last
(: sequence : (All (A) (A -> A) A A -> (Listof A)))
(define (sequence f first last)
  (if (equal? first last) 
      (list last) 
      (cons first (sequence f (f first) last))))


(test (sequence add1 1 1) => (list 1))
(test (sequence add1 1 5) => (list 1 2 3 4 5))
(test (sequence sub1 5 1) => (list 5 4 3 2 1))
(test (sequence sqrt 65536 2) => (list 65536 256 16 4 2))
(test (sequence not #f #t) => (list #f #t))
(test (sequence (inst rest Number) (list 1 2 3 4) '()) => '((1 2 3 4) 
                                                            (2 3 4) 
                                                            (3 4) 
                                                            (4)
                                                            ()))


; Problem 2:
;; an INTSET is one of
;; - (make-Num Integer)
;; - (make-Range Integer Integer)
;; - (make-2Sets Intset Intset)
(define-type INTSET
  [Num   Integer]
  [Range Integer Integer]
  [2Sets INTSET INTSET])


(: intset-min/max : INTSET (Integer Integer -> Boolean) -> Integer)
;; Finds the minimal/maximal based on the comparator "<" or ">"
(define (intset-min/max set comparator)
  (cases set 
    [(Num set) set]
    [(Range min max) (if (comparator min max) min max)]
    [(2Sets first last) 
     (if (comparator 
          (intset-min/max first comparator)
          (intset-min/max last comparator))
         (intset-min/max first comparator)
         (intset-min/max last comparator))]))

(: intset-min : INTSET -> Integer)
;; Finds the minimal member of the given set.
(define (intset-min set) 
  (intset-min/max set <))

(: intset-max : INTSET -> Integer)
;; Finds the maximal member of the given set.
(define (intset-max set) 
  (intset-min/max set >))

;; checks if the set is normalized
(: intset-normalized? : INTSET -> Boolean)
(define (intset-normalized? set)
  (cases set
    [(Num set) #t]
    [(Range min max) (< min max)]
    [(2Sets first last) (< 1 (- (intset-min last) (intset-max first)))]))

(test (intset-normalized? (Num 9)) => #t)
(test (intset-normalized? (Range 4 1)) => #f)
(test (intset-normalized? (Range 1 5)) => #t)
(test (intset-normalized? (2Sets (Num 4) (Range 4 8))) => #f)
(test (intset-normalized? (2Sets (Num 2) (Range 4 8))) => #t)
(test (intset-normalized? (2Sets (Range 4 8) (Num 7))) => #f)
(test (intset-normalized? (2Sets (Range 1 3) (Range 5 8))) => #t)
(test (intset-normalized? (2Sets (2Sets (Range 1 3) (Range 4 8))
                                 (2Sets (Range 4 8) (Num 7)))) => #f)

;; Problem 3:
#|
BNF for <PAGES> language
<PAGES> ::= <int>
          | { <int> "-" <int> }
          | { <PAGES> "," <PAGES> }

|#

;; Problem 4:
;; minutes spent on this homework
(define minutes-spent  240)