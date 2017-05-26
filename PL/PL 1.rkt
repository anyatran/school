;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname |PL 1|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;; Anya Tran
;; username: anya

;; PRoblem 2:
;; Int Int Int Int -> Number
;; takes 4 numbers (either 1 or 0) and 
;; converts a 4-digits binary number into a decimal number
;;
(define (bin4-to-num n1 n2 n3 n4)
  (+ (* n1 1) (* n2 2) (* n3 4) (* n4 8)))


(bin4-to-num 1 0 1 1)

;; P3
;; Int Int -> Int
;; calculates GCD of 2 non-negative integers
(define (gcd2 a b)
  (cond 
    [(equal? a 0) b]
    [(equal? b 0) a]
    [(and (even? a) (even? b)) (* 2 (gcd2 (/ a 2) (/ b 2)))]
    [(and (even? a) (odd? b)) (gcd2 (/ a 2) b)]
    [(and (odd? a) (even? b)) (gcd2 a (/ b 2))]
    [(and (odd? a) (odd? b))
     (cond
       [(>= a b) (gcd2 (/ (- a b) 2) b)]
       [else (gcd2 (/ (- b a) 2) a)])]))

(equal? (gcd2 378 144) 18)
(equal? (gcd2 216 612) 36)
(equal? (gcd2 12 0) 12)
(equal? (gcd2 0 12) 12)
(equal? (gcd2 9 5) 1)
(equal? (gcd2 15 33) 3)
(equal? (gcd2 36 15) 3)
(equal? (gcd2 15 12) 3)

;; P4
;; LoInt -> Boolean
;; checks if all integers in the list are even
(define (all-even? l)
  (or (empty? l) (and (even? (first l)) (all-even? (rest l)))))

(all-even? (list 2 4 6 8))
(not (all-even? (list 1 3 5 7)))

;; P5
;; LoNum LoNum -> LoNum
;; takes two sorted lists and creates a new sorted list by merging them
(define (merge-lists l1 l2)
  (cond
    [(empty? l1) l2]
    [(empty? l2) l1]
    [(> (first l1) (first l2)) 
     (merge-lists (cons (first l2) l1) (rest l2))] 
    [else 
     (cons (first l1) (merge-lists (rest l1) l2))]))


(equal? (list 1 2 3 5 6 7) (merge-lists (list 1 3 7) (list 2 5 6)))
(equal? (list 2 3 4) (merge-lists '() (list 2 3 4)))

;; P6:
;; my picture from 
(define my-picture 41)

(define my-other-pictures null)

;; P7:
;; total time spent on the assignment
(define minutes-spent 70)
