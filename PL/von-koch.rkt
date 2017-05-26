#lang pl 11

#|
  Skeleton code for the Von-Koch problem.

  Reminder: in Schlac all values are one-argument functions, so you'll
  never get any kind of a "type error".  You can only get such errors
  when you use one of the special conversion-to-racket with a bad
  value if you're lucky -- and a meaningless result if you're not.
  This means that you need to be extremely careful about the types:
  they're only comments, but make sure that you use the right types.
  Use the test cases as much as possible to make your life easier.  If
  you get stuck, you can try to implement the problem in the course
  language, then translate the Racket code back to Schlac.  See the
  homework text for details.
|#

;; a convenient rewrite for local bindings
(rewrite (with [x E1] E2) => ???)
;; tests
(test (->nat (with [x (* 2 4)] (+ x x))) => '16)
(test (->nat (with [x 3] (with [x (* x 3)] (+ x x)))) => '18)

;; ==================== Numeric utilities ====================

;; diff : Nat Nat -> Nat
;; computes the difference between two numbers: |x-y|
(define diff
  (lambda (x y)
    ;; reminder: x-y=0 if y>x
    ???))
;; tests
(test (->nat (diff 0 0)) => '0)
(test (->nat (diff 3 3)) => '0)
(test (->nat (diff 2 4)) => '2)
(test (->nat (diff 4 2)) => '2)

;; = : Nat Nat -> Bool
;; comparison for natural numbers
???
;; tests
(test (->bool (= 0 0)) => '#t)
(test (->bool (= 1 1)) => '#t)
(test (->bool (= (* (* 2 3) 2) (* 3 4))) => '#t)

;; ==================== List utilities ====================

;; define this to test list functions
(define l12  (cons 1 (cons 2 null)))
(define l123 (cons 1 (cons 2 (cons 3 null))))

;; ref : Nat (Listof A) -> A
;; returns the nth item of the given list (note: this doesn't have to be
;; a recursive function, but in a way that doesn't correspond to plain
;; racket)
(define/rec ref
  (lambda (n list)
    ???))
;; tests
(test (->nat (ref 0 l123)) => '1)
(test (->nat (ref 1 l123)) => '2)
(test (->nat (ref 2 l123)) => '3)

;; map : (A -> B) (Listof A) -> (Listof B)
;; maps the given function on the list, return a list of the results
(define/rec map
  ???)
;; tests
(test (->listof ->nat (map add1 null)) => '())
(test (->listof ->nat (map add1 l123)) => '(2 3 4))

;; append : (Listof A) (Listof A) -> (Listof A)
;; appends the two input lists
(define/rec append
  (lambda (l1 l2)
    ???))
;; tests
(test (->listof ->nat (append null null)) => '())
(test (->listof ->nat (append null l123)) => '(1 2 3))
(test (->listof ->nat (append l123 null)) => '(1 2 3))
(test (->listof ->nat (append l12  l123)) => '(1 2 1 2 3))

;; append* : (Listof (Listof A)) -> (Listof A)
;; consumes a list of lists, and appends them all to a single list
(define/rec append*
  (lambda (lists)
    ???))
;; tests
(test (->listof ->nat (append* null)) => '())
(test (->listof ->nat (append* (cons null null))) => '())
(test (->listof ->nat (append* (cons l123
                                     (cons null
                                           (cons l12
                                                 (cons l123 null))))))
      => '(1 2 3 1 2 1 2 3))

;; Note: the following definitions can be hard to fill-in, it will be
;; much easier if you do this first in the course language -- see the
;; template file that is included with the homework.

;; interleave : A (Listof A) -> (Listof (Listof A))
;; consumes an item and a list, and returns a list of lists where the
;; item is inserted at all possible places in the original list.
(define/rec interleave
  (lambda (x list)
    (if (null? list)
      (cons (cons x null) null)
      ???)))
;; tests
(test (->listof (->listof ->nat) (interleave 0 null)) => '((0)))
(test (->listof (->listof ->nat) (interleave 0 l123))
      => '((0 1 2 3) (1 0 2 3) (1 2 0 3) (1 2 3 0)))

;; permutations : (Listof A) -> (Listof (Listof A))
;; returns a list of all possible permutations of the input list
(define/rec permutations
  (lambda (list)
    (if (null? list)
      (cons null null)
      ;; use `append*', `interleave', and a recursive call to
      ;; `permutations'
      ???)))
;; tests
(test (->listof (->listof ->nat) (permutations null))
      => '(()))
(test (->listof (->listof ->nat) (permutations (cons 1 null)))
      => '((1)))
;; Note that this test relies on a specific implementation; a proper
;; test would need to compare the output as an unordered set of
;; values.  In your case, you might have a different result, but it is
;; likely that you will get the same.
(test (->listof (->listof ->nat) (permutations l123))
      => '((1 2 3) (2 1 3) (2 3 1) (1 3 2) (3 1 2) (3 2 1)))

;; filter : (A -> Bool) (Listof A) -> (Listof A)
;; given a predicate and a list, return a list of the items that
;; satisfy the predicate
(define/rec filter
  ???)
;; tests
(test (->listof ->nat (filter (lambda (n) #t) l123))
      => '(1 2 3))
(test (->listof ->nat (filter (lambda (n) #f) l123))
      => '())
(test (->listof ->nat (filter (lambda (n) (zero? (- n 2))) l123))
      => '(1 2))

;; member? : Nat (Listof Nat) -> Bool
;; determines whether the given number is included in the given list
;; (note: unlike Racket's `member', this is limited to numbers, and
;; returns a boolean)
???
;; tests
(test (->bool (member? 2 l123)) => '#t)
(test (->bool (member? 4 l123)) => '#f)

;; unique? : (Listof Nat) -> Bool
;; determines if the given list of numbers is unique
(define/rec unique?
  (lambda (list)
    (or ???
        (and ???))))
;; tests
(test (->bool (unique? l123)) => '#t)
(test (->bool (unique? (cons 2 l123))) => '#f)

;; from-to : Nat Nat -> (Listof Nat)
;; returns a list of numbers from lo (inclusive) to hi (exclusive)
???
;; tests
(test (->listof ->nat (from-to 2 2)) => '())
(test (->listof ->nat (from-to 2 5)) => '(2 3 4))

;; range : Nat -> (Listof Nat)
;; returns a list of numbers from 0 (inclusive) to N (exclusive)
???
;; tests
(test (->listof ->nat (range 0)) => '())
(test (->listof ->nat (range 1)) => '(0))
(test (->listof ->nat (range 5)) => '(0 1 2 3 4))

;; ==================== Main code ====================

;; graceful? : (Listof (Pair Nat Nat)) (Listof Nat) -> Bool
;; Helper for the von-koch function below.  Consumes a graph
;; representation iin the same form, and a labeling assignment as a
;; list of numbers, and determines whether the assignment represents a
;; graceful labeling.
(define graceful?
  (lambda (edges assignment)
    (unique? (map (lambda (edge)
                    ;; use `ref' with the car and cdr of the edge
                    ???)
                  edges))))

;; von-koch : (Listof (Pair Nat Nat)) -> (Listof (Listof Nat))
;; The main function consumes a tree graph represented as a list of
;; cons pairs, each one represents an edge, and returns a list of
;; solutions, each is a list that maps node labels in the input to
;; node label in a gracful labeling.  See the homework text for more
;; details.  Note that this function does not check its input; for
;; example, it assumes that if there are N edges, then the labels are
;; from 0 to N (inclusive).  (This should be obvious, given that we
;; have no way of throwing an error.)
(define von-koch
  (lambda (edges)
    (with [n (add1 (length edges))]
      (with [assignments (permutations (range n))]
        ???))))

;; ==================== Main test ====================

;; we'll need a 6
(define 6 (add1 5))

;; This is a useful utility to construct a long list without the
;; hassle of nested `cons' calls.  The idea is to make a `list'-like
;; function of a variable number of arguments.  It is limited to pairs
;; only, since it uses `null' to mark the end of the arguments (it
;; needs to be able to identify an end value).  Note that there is no
;; type for this function -- since its type will be weird.  (You're
;; not required to do anything with this function, but just
;; understanding how it works can be enlightening.)
(define/rec pair-list*
  (lambda (k x)
    (if (null? x)
      (k null)
      (pair-list* (lambda (r) (k (cons x r)))))))
(define pair-list (pair-list* identity))
;; test the testing utility...
(test (->listof ->nat
       (with [xs (pair-list (cons 1 2) (cons 3 4) (cons 5 6) null)]
         (append (map car xs) (map cdr xs))))
      => '(1 3 5 2 4 6))

;; This is an encoding of the simple graph from the homework:
;;
;;    0   5---3
;;    |   |
;;    |   |
;;    |   |
;;    6---4---2
;;    |
;;    |
;;    |
;;    1
;;
(define simple-graph
  (pair-list (cons 0 6)
             (cons 6 1)
             (cons 6 4)
             (cons 4 5)
             (cons 5 3)
             (cons 4 2)
             null))

;; Here we compute and test the node labeling (note that like the test
;; for `permutations', this test is not a good one!)
(define simple-solution (car (von-koch simple-graph)))
(test (->listof ->nat simple-solution) => '(3 4 5 2 0 6 1))
;; This labeling corresponds to this graph, shown with the differences
;;
;;    3   6---2
;;    |   | 4
;;   2|  6|
;;    |   |
;;    1---0---5
;;    | 1   5
;;   3|
;;    |
;;    4

#| Finally, this is John's graph.
   Warning: this can take a long time to run -- it is here only if you
   want to try it for yourself, do not submit with it.

(define  7 (add1  6))
(define  8 (add1  7))
(define  9 (add1  8))
(define 10 (add1  9))
(define 11 (add1 10))
(define 12 (add1 11))
(define 13 (add1 12))

(define johns-graph
  (pair-list (cons  0  3)
             (cons  1  3)
             (cons  2  3)
             (cons  3  4)
             (cons  3  6)
             (cons  5  6)
             (cons  6  7)
             (cons  6  9)
             (cons  8  9)
             (cons  9 10)
             (cons 10 11)
             (cons 10 13)
             (cons 12 13)
             null))

(define johns-solution (car (von-koch johns-graph)))

|#
