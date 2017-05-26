; **************** BEGIN INITIALIZATION FOR ACL2s B MODE ****************** ;
; (Nothing to see here!  Your actual file is after this initialization code);

#|
Pete Manolios
Fri Jan 27 09:39:00 EST 2012
----------------------------

Made changes for spring 2012.


Pete Manolios
Thu Jan 27 18:53:33 EST 2011
----------------------------

The Beginner level is the next level after Bare Bones level.

|#
#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading the TRACE* book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
; only load for interactive sessions: 
#+acl2s-startup (include-book "trace-star" :uncertified-okp nil :dir :acl2s-modes :ttags ((:acl2s-interaction)) :load-compiled-file nil);v4.0 change

#+acl2s-startup (assign evalable-printing-abstractions nil)

;arithmetic book
#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading arithmetic-5/top book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "arithmetic-5/top" :dir :system)

;basic thms/lemmas about lists
#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading coi/lists book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "coi/lists/basic" :dir :system)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading ACL2's lexicographic-ordering-without-arithmetic book.~%This indicates that either your ACL2 installation is missing the standard books are they are not properly certified.") (value :invisible))
(include-book "ordinals/lexicographic-ordering-without-arithmetic" :dir :system)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading the CCG book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "ccg" :uncertified-okp nil :dir :acl2s-modes :ttags ((:ccg)) :load-compiled-file nil);v4.0 change

;; #+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading DataDef+RandomTesting book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
;; (include-book "countereg-gen/top" :uncertified-okp nil :dir :system :load-compiled-file :comp)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading ACL2s customizations book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "custom" :dir :acl2s-modes :uncertified-okp nil
                                         :load-compiled-file
                                         :comp :ttags :all)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem setting up ACL2s Beginner mode.") (value :invisible))


;Settings common to all ACL2s modes
(acl2s-common-settings)

; Non-events:
(acl2::set-guard-checking :all)

(defconst *testing-upper-bound* 1000)  

(defun nth-small-pos-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-pos n-small)))

(defun nth-small-integer-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-integer n-small)))

(defun nth-small-nat-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-nat n-small)))

(defun nth-small-neg-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-neg n-small)))

(defun nth-small-positive-ratio-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-positive-ratio n-small)))

(defun nth-small-negative-ratio-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-negative-ratio n-small)))

(defun nth-small-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-rational n-small)))

(defun nth-small-positive-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-positive-rational n-small)))

(defun nth-small-negative-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-negative-rational n-small)))

(defun nth-small-acl2-number-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-acl2-number n-small)))

(defun nth-small-complex-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-complex-rational n-small)))

(defun nth-small-all (n)
  (declare (xargs ;:guard (natp n) ))
                  :verify-guards nil))
  (mv-let (choice seed) 
          (defdata::weighted-switch-nat 
            '(1  ;nil
              1  ;t
              1 ;0
              1 ;integer
              1  ;bool
              1  ;charlist
              1  ;sym
              1  ;string
              2  ;char
              1  ;acl2-num
              5 ;rational
              5 ;nat
              5 ;pos
              5  ;rational-list
              2  ;sym-list
              2  ;cons-atom ;changed Jan 15th 2013 from 20
              5  ;nat-list
              1  ;cons-cons-atom ;changed Jan 15th 2013 from 10
              1  ;stringlist
              10  ;atom-list
              ) n)
    (case choice
          (0 'nil)
          (1 't)
          (2 0)
          (3 (nth-small-integer-testing seed))
          (4 (nth (mod seed 2) *boolean-values*))
          (5 (nth-character-list seed))
          (6 (nth-symbol seed))
          (7 (nth-string seed))
          (8 (nth (mod seed (len *character-values*)) *character-values*))
          (9 (nth-small-acl2-number-testing seed))
          (10 (nth-small-rational-testing seed))
          (11 (nth-small-nat-testing seed))
          (12 (nth-small-pos-testing seed))
          (13 (nth-rational-list seed))
          (14 (nth-symbol-list seed))
          (15 (nth-cons-atom seed))
          (16 (nth-nat-list seed))
          (17 (nth-cons-ca-ca seed))
          (18 (nth-string-list seed))
          (19 (nth-atom-list seed))
          (t 'nil)))) ;this case should not come up


(defdata-testing pos :test-enumerator nth-small-pos-testing)
(defdata-testing integer :test-enumerator nth-small-integer-testing)
(defdata-testing nat :test-enumerator nth-small-nat-testing)
(defdata-testing neg :test-enumerator nth-small-neg-testing)
(defdata-testing positive-ratio :test-enumerator nth-small-positive-ratio-testing)
(defdata-testing negative-ratio :test-enumerator nth-small-negative-ratio-testing)
(defdata-testing rational :test-enumerator nth-small-rational-testing)
(defdata-testing positive-rational :test-enumerator nth-small-positive-rational-testing)
(defdata-testing negative-rational :test-enumerator nth-small-negative-rational-testing)
(defdata-testing acl2-number :test-enumerator nth-small-acl2-number-testing)
(defdata-testing complex-rational :test-enumerator nth-small-complex-rational-testing)
(defdata-testing all :test-enumerator nth-small-all)

(acl2s-defaults :set num-trials 50)

(defpkg "ACL2S B" ; beginner
  (union-eq '(t nil 
              ;if ; see macro below
              equal

              defun defunc ;for function definitions

              ; + * unary-- unary-/ < ; see definitions below
              numerator denominator
              rationalp integerp

              consp cons ; car cdr

              cond ; macro: explain
              list ; macro: explain

              lambda
              let let* ; macro: explain

              quote

              symbolp symbol-name symbol-package-name
              ;stringp
              ;charp

              check=

              and or iff implies not booleanp 
              ;+ * 
              / posp negp natp <= > >= zp - atom 
              ; true-listp 
              endp 
              ;caar cadr cdar cddr 
              ;caaar caadr cadar caddr cdaar cdadr cddar cdddr
              ;caaaar caaadr caadar caaddr cadaar cadadr caddar cadddr
              ;cdaaar cdaadr cdadar cdaddr cddaar cddadr cdddar cddddr
              
              
              defdata nat string pos rational integer boolean all neg
              acl2-number true-list char symbol oneof listof enum record
              ;; i need them for defdata why?
              
              trace*

              defthm thm defconst in-package
              test?
              acl2s-defaults testing-enabled 
              verbosity-level defunc-verbosity-level
              num-trials num-counterexamples num-witnesses
              subgoal-timeout defunc-timeout defunc-strict)
            '()))

(defthm natp-implies-acl2-numberp
  (implies (natp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm posp-implies-acl2-numberp
  (implies (posp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm integerp-implies-acl2-numberp
  (implies (integerp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm rationalp-implies-acl2-numberp2
  (implies (rationalp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm natp-implies-rationalp
  (implies (natp x)
           (rationalp x))
  :rule-classes ((:rewrite)))

(defthm posp-implies-rationalp
  (implies (posp x)
           (rationalp x))
  :rule-classes ((:rewrite)))

(defthm integerp-implies-rationalp
  (implies (integerp x)
           (rationalp x))
  :rule-classes ((:rewrite)))


(acl2::in-package "ACL2S B")

(defun acl2s-bb-identity-bool-guard (x)
  (acl2::declare (acl2::xargs :guard (acl2::booleanp x)))
  x)

(acl2::defmacro if (test tb fb)
  `(acl2::if (acl2s-bb-identity-bool-guard ,test) ,tb ,fb))

(acl2::defthm acl2s-bb-identity-bool-guard-backchain
  (acl2::implies (acl2::booleanp x)
                 (equal (acl2s-bb-identity-bool-guard x)
                        x)))

(acl2::defthm acl2s-bb-identity-bool-guard-equal
  (equal (acl2s-bb-identity-bool-guard (equal x y))
         (equal x y)))

(defunc first (x)
  :input-contract (consp x)
  :output-contract t
  (acl2::car x))

(defunc rest (x)
  :input-contract (consp x)
  :output-contract t
  (acl2::cdr x))

(defunc second (x)
  :input-contract (and (consp x) (consp (rest x)))
  :output-contract t
  (acl2::cadr x))

(defunc third (x)
  :input-contract (and (consp x) (consp (rest x)) (consp (rest (rest x))))
  :output-contract t
  (acl2::caddr x))

(defunc fourth (x)
  :input-contract (and (consp x) (consp (rest x)) 
                       (consp (rest (rest x)))
                       (consp (rest (rest (rest x)))))
  :output-contract t
  (acl2::cadddr x))

(defunc unary-- (x)
  :input-contract (rationalp x)
  :output-contract t
  (acl2::unary-- x))

(defunc unary-/ (x)
  :input-contract (acl2::and (rationalp x) (acl2::not (equal x 0)))
  :output-contract t
  (acl2::unary-/ x))

(defunc < (x y)
  :input-contract (acl2::and (rationalp x) (rationalp y))
  :output-contract (acl2::booleanp (< x y))
  (acl2::< x y))

(defunc + (x y)
  :input-contract (acl2::and (rationalp x) (rationalp y))
  :output-contract (rationalp (+ x y))
  (acl2::binary-+ x y))

(defunc * (x y)
  :input-contract (acl2::and (rationalp x) (rationalp y))
  :output-contract (rationalp (+ x y))
  (acl2::binary-* x y))

(defun my-preprocess (term wrld)
  (acl2::declare (acl2::ignore wrld))
  (acl2::cond ((acl2::and (consp term)
                          (acl2::or 
                           (equal (acl2::car term) 'acl2s-bb-identity-bool-guard)
                           (equal (acl2::car term) 'acl2s-bb-identity-consp-guard)
                           (equal (acl2::car term) 'acl2s-bb-identity-rationalp-guard)
                           (equal (acl2::car term) 'acl2s-bb-identity-rationalp-not-0-guard)))
               (acl2::cadr term))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::implies))
               (cons 'implies (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::/))
               (cons '/ (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::car))
               (cons 'first (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::cdr))
               (cons 'rest (acl2::cdr term)))
              ((acl2::and (consp term)
                          (consp (acl2::cdr term))
                          (equal (acl2::car term) 'acl2::not)
                          (equal (acl2::caadr term) 'acl2::>))
               (cons '<= (acl2::cdadr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::not))
               (cons 'not (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::*))
               (cons '* (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::+))
               (cons '+ (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::/))
               (cons '/ (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::-))
               (cons '- (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::<))
               (cons '< (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::>))
               (cons '> (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::acl2-number))
               (cons 'acl2-number (acl2::cdr term)))
              (t nil)))

; A hack to help proofs go through in this mode.
(acl2::in-theory (acl2::enable rest))

(acl2::table acl2::user-defined-functions-table
             'acl2::untranslate-preprocess
             'my-preprocess)

(defunc len (a) 
  :input-contract t 
  :output-contract (natp (len a))
  (if (atom a)
      0
    (+ 1 (len (rest a)))))

(defthm intp-len 
  (integerp (len x))
  :rule-classes ((:type-prescription) (:rewrite)))

(acl2::defmacro listp (a)
  `(acl2::true-listp ,a))

(defunc append (a b) 
  :input-contract (and (listp a) (listp b))
  :output-contract (and (listp (append a b))
                        (equal (len (append a b)) (+ (len a) (len b))))
  (acl2::append a b))

(defthm append-length
  (equal (len (acl2::append a b))
         (+ (len a) (len b))))

(defunc app (a b) 
  :input-contract (and (listp a) (listp b))
  :output-contract (and (listp (app a b))
                        (equal (len (app a b)) (+ (len a) (len b))))
  (acl2::append a b))

(defunc rev (a) 
  :input-contract (listp a) 
  :output-contract (and (listp (rev a))
                        ;(equal (len (rev a)) (len a))
                        )
  (if (endp a)
      nil
    (append (rev (rest a)) (list (first a)))))

(defunc in (a X) 
  :input-contract (listp x)
  :output-contract (booleanp (in a X))
  (if (endp x)
      nil
    (or (equal a (first X))
        (in a (rest X)))))

(defunc remove-dups (a) 
  :input-contract (listp a) 
  :output-contract (listp (remove-dups a))
  (if (endp a)
      nil
    (if (in (first a) (rest a))
        (remove-dups (rest a))
      (cons (first a) (remove-dups (rest a))))))

(defunc nth (n l)
  :input-contract (and (natp n) (listp l))
  :output-contract t
  (if (endp l)
      nil
    (if (zp n)
        (first l)
      (nth (- n 1) (rest l)))))

(defunc nthrest (n l)
  :input-contract (and (natp n) (listp l))
  :output-contract (listp (nthrest n l))
  (if (endp l)
      nil
    (if (zp n)
        l
      (nthrest (- n 1) (rest l)))))

(defthm natp-acl2-len-tp 
  (natp (acl2::len x))
  :rule-classes ((:type-prescription) (:rewrite)))

(defthm integerp-acl2-len-tp 
  (integerp (acl2::len x))
  :rule-classes ((:type-prescription) (:rewrite)))

#|
(defunc string-len (l)
  :input-contract (stringp l)
  :output-contract (natp (string-len l))
  (acl2::length l))
|#


; harshrc 29 March 2012 -- added nth-list for Pete
(defun nth-list (n)
  (acl2::nth-true-list n))



;;Settings specific to this mode(copied from R&I mode)
(acl2::in-package "ACL2")
(set-backchain-limit '(50 100))
(set-rewrite-stack-limit 500)
(acl2s-defaults :set subgoal-timeout 60)
(acl2s-defaults :set defunc-timeout 200) 

(set-irrelevant-formals-ok :warn)
(set-bogus-mutual-recursion-ok :warn)
(set-ignore-ok :warn)

;for beginner users dont be strict in admitting defunc
;(acl2::acl2s-defaults :set acl2::defunc-strict 0)  
(acl2s-defaults :set num-trials 500)

;(assign evalable-ld-printingp t)
;(assign evalable-printing-abstractions '(list cons))
;(assign triple-print-prefix "; ")

(cw "~@0Beginner mode loaded.~%~@1"
    #+acl2s-startup "${NoMoReSnIp}$~%" #-acl2s-startup ""
    #+acl2s-startup "${SnIpMeHeRe}$~%" #-acl2s-startup "")


(acl2::in-package "ACL2S B")

; ***************** END INITIALIZATION FOR ACL2s B MODE ******************* ;
;$ACL2s-SMode$;Beginner
#|
CS 2800 Homework 9 - Spring 2014
Student names: Anh Tran, Tyler Kaminsky
- open this file in ACL2s as hw09.lisp
- set the session mode to "Beginner"
- insert your solutions into this file where indicated (for instance as "...")
- only add to the file. Do not remove or comment out anything pre-existing.
- make sure the entire file is accepted by ACL2s in Beginner mode. In
  particular, there must be no "..." left in the code. If you don't finish
  some problems, comment them out. The same is true for any English text
  that you may add. This file already contains many comments, so you can
  see what the syntax is.
For each function definition, you must provide both contracts and a body.
You must also ALWAYS supply your own tests. This is in addition to the
tests sometimes provided. Make sure you produce sufficiently many new
test cases. This means: cover at least the possible scenarios according
to the data definitions of the involved types. For example, a function
taking two lists should have at least 4 tests (each list being empty and
non-empty).
Beyond that, the number of tests should reflect the difficulty of the
function. For very simple ones, the above coverage of the data definition
cases may be sufficient. For complex functions with numerical output, you
want to test whether it produces the correct output on a reasonable
number if inputs.
Use good judgment. For unreasonably few test cases we will deduct points.
We will use ACL2s' check= facility for tests. This function is similar to
the equal function, except that if the evaluations of the two arguments
passed to it are not equal, the function call results in an error message
(rather than returning nil, as in the case of equal). Thus, if any call to
check= results in "not equal", your file will be rejected.
About Beginner mode:
In contrast to the Bare Bones mode, many elementary functions are built in,
including many Boolean functions (and, or, not, recognizers), many
arithmetic functions (<= etc), and many list related functions (e.g. 'in'
for list membership). If in doubt, try it out. If you need a function that
does not seem to be built in to Beginner mode, you must define it.
You can type something like
:doc <name>
at the prompt, to get basic help about function <name>, provided it is defined.
Also note that some functions are defined in Beginner mode to be more
flexible than the way we defined them in Bare Bones mode. For example, the
Boolean functions and, or can now take any number of arguments:
(check= (or (booleanp (+ 1 2)) (symbolp nil) (equal (/ 1 3) 0)) t)
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; A. Induction Proofs - I
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Consider the sum
;1 + 4 + 7 + 10 + 13 + ...
;That is, starting from 1, the terms in the sum increase by 3.
;(a) Write a recursive ACL2 function sum-of-threes that computes the above sum.
(defunc sum-of-threes (n)
  :input-contract (natp n)
  :output-contract (natp (sum-of-threes n))
(cond ((equal n 0) 1)
      (t (+ (+ 1 (* 3 n)) (sum-of-threes (- n 1))))))
(check= (sum-of-threes 1) 5)
(check= (sum-of-threes 0) 1)
(check= (sum-of-threes 2) 12)
(check= (sum-of-threes 3) 22)
#|
(b) Use the induction scheme suggested by nind (also known as the induction
principle for natural numbers) to prove the following conjecture:
(natp n) => (sum-of-threes n) = 1 + (n * (3n+5)) / 2
0. ~(natp n) -> phi
  ~(natp n) -> (natp n) -> (sum-of-threes n) = 1 + (n * (3n+5)) / 2
  ~(nat p) /\ (natp n) -> (sum-of-threes n) = 1 + (n * (3n+5)) / 2
  F -> anything = T
-------------------------------------------------------------------------
-------------------------------------------------------------------------
1. (natp n) /\ (equal n 0) -> phi
(natp n) /\ (equal n 0) ->  (natp n) -> (sum-of-threes n) = 1 + (n * (3n+5)) / 2
(natp n) /\ (equal n 0) -> (sum-of-threes n) = 1 + (n * (3n+5)) / 2
c1. (natp n)
c2. (equal n 0)
(natp n) /\ (equal n 0) -> (sum-of-threes n) = 1 + (n * (3n+5)) / 2
(sum-of-threes n)
{expand sum-of-threes}
1
{arithmetic}
1 + (0 * (3(0) + 5)) / 2
{arithmetic}
1 + (0 * 5) / 2
{arithmetic}
1 + 0
{arithmetic}
1
-------------------------------------------------------------------------
-------------------------------------------------------------------------
2. (natp n) /\ ( >= n 1) /\ phi | (n (- n 1) -> phi
(natp n) /\ ( >= n 1) -> (natp n) -> (sum-of-threes n) = 1 + (n * (3n+5)) / 2
(natp n) /\ ( >= n 1) -> (sum-of-threes n) = 1 + (n * (3n+5)) / 2
Context:
C1. (natp n)
C2. (>= n 1)
C3. (natp (- n 1) -> (sum-of-threes (- n 1)) = 1 + ((- n 1) * (3(- n 1)+5)) / 2
C4. (sum-of-threes (- n 1)) = 1 + ((- n 1) * (3(- n 1)+5)) / 2
(sum-of-threes (n)) = 1 + (n * (3n+5)) / 2
(sum-of-threes (n)
= {definition sum-of-threes}
(3n + 1) + (sum-of-threes (- n 1))
= {expanding sum-of-threes}
(3n + 1) + 1 (((- n 1) (3n+2)) / 2)
= {arithmetic}
(3n + (((n - 1) (3n + 2)) / 2) + 2
= {arithmetic}
6n + (((n - 1) (3n + 2)) / 2) + 4
= {arithmetic}
(3(n^2) + 5n + 2) / 2
1 + ((n(3n+5)) / 2)
= {arithmetic}
(2 + n(3n + 5)) / 2
= {arithmetic}
(3(n^2) + 5n + 2) / 2
(3(n^2) + 5n + 2) / 2 = (3(n^2) + 5n + 2) / 2
-------------------------------------------------------------------------
-------------------------------------------------------------------------
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; B. Understanding Induction
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
Recall these definitions:
(defunc listp (x)
  :input-contract t
  :output-contract (booleanp (listp x))
  (or (equal x ())
      (consp x)))
(defunc len (x)
  :input-contract (listp x)
  :output-contract (natp (len x))
  (if (endp x)
    0
    (+ 1 (len (rest x)))))
1. Given some formula phi, write down the induction scheme len gives rise
to:
0. ~(listp x) -> phi
1. (listp x) /\ (endp x) -> phi
2. (listp x) /\ ~(endp x) /\ phi | (x (rest x)) -> phi


2.  Someone claims  that the  induction  scheme derived  from the  len
function above can be simplified: in  order to prove any claim phi, it
is sufficient to prove
(i)  (listp x) /\ (endp x) => phi
(ii) (listp x) /\ (not (endp x)) /\ phi|((x (rest x))) => phi
Show that this is a dangerous mistake to make: give an _invalid_ claim phi
that can nevertheless be "proved" using the above wrong induction scheme:
give a counterexample for phi, and prove by propositional reasoning that
nevertheless both (i) and (ii) hold for your choice of phi.
phi : ~(listp x) => (natp x)
substitution sigma such that phi|sigma is equivalent to nil :
Assume sigma is a rational number

~(listp sigma) => phi
~(listp sigma) => ~(listp sigma) => (napt sigma)
~(listp sigma) => (natp sigma)
C1: ~(listp sigma)
(natp sigma)
F

(i) is equivalent to T:
(listp sigma)) /\ (endp sigma) => phi
F /\ F => phi
T

(ii) is equivalent to T:
(listp sigma) /\ (not (endp sigma) /\ phi|((x (rest sigma))) => phi
F /\ F /\ F => phi
T

3. Based on your convincing counterexample, the person apologizes and says
he really meant: in order to prove any claim phi, it is sufficient to prove
(i)  (not (listp x)) => phi
(ii) (listp x) /\ (not (endp x)) /\ phi|((x (rest x))) => phi
Show that this is not much better: again give an _invalid_ claim phi and a
counterexample for phi, and prove by propositional reasoning that
nevertheless both (i) and (ii) hold for your choice of phi.
phi : (listp x) => (> (len x) 0)
substitution sigma such that phi|sigma is equivalent to nil : 



Assume sigma is an empty list

(listp sigma) /\ (endp sigma) => phi
(listp sigma) /\ (endp sigma) => (listp sigma) => (> (len sigma) 0)
(listp sigma) /\ (endp sigma) => (> (len sigma) 0)
C1: (listp sigma)
C2: (enpt sigma)

(len sigma) = 0
0 > 0 = F

(i) is equivalent to T:
  
(not (listp sigma)) => phi
F => phi
T
  
(ii) is equivalent to T: prove this carefully, including identifying
     context, derived context, etc.
(listp sigma) /\ (not (endp sigma)) /\ phi|((sigma (rest sigma))) => phi
(listp sigma) /\ (not (endp sigma)) /\ phi|((sigma (rest sigma))) =>
  (listp sigma) => (> (len sigma) 0)
  
(listp sigma) /\ (not (endp sigma)) /\ phi|((sigma (rest sigma))) /\
  (listp sigma) => (> (len sigma) 0)
  
(listp sigma) /\ (not (endp sigma)) /\ phi|((sigma (rest sigma))) => (> (len sigma) 0)

C1: (listp sigma)
C2; (not (endp sigma))
C3: (listp (rest sigma)) => (> (len (rest sigma)) 0)
Derived context:
C4: (listp (Rest sigma))
C5: (> (len (Rest sigma)) 0)

(len (sigma))
= {axiom of cons, C4}
(len (cons (first sigma) (rest sigma)))
= {axiom of len}
(+ 1 (len (rest sigma)))
= {C5}
t


|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; C. Induction Schemes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
Review the  definition of an induction  scheme given in  class (or the
lecture notes).  Then, given an  arbitrary formula phi,  determine the
induction  scheme  for  each  of  the following  functions.  That  is,
instantiate  the general  induction scheme  template from  the lecture
notes to a concrete induction scheme for each function.
Hints:
- we defined induction schemes for functions whose body is one large
(cond ...) expression. If your function does not have such a body, you need
to rewrite it, at least in your head. You do not have to produce such
rewritten code, but your induction scheme must in the end be correct.
- if a  function is not admissible, it  does not give rise  to a valid
induction  scheme.  In  such  a  case, explain  why  the  function  is
inadmissible.
1.
(defunc f (a b c)
  :input-contract (and (rationalp a) (listp b) (natp c))
  :output-contract (booleanp (f a b c))
  t)
0. ~((rationalp a) /\ (listp b) /\ (natp c)) -> phi
1. (rationalp a) /\ (listp b) /\ (natp c) -> phi
2.
(defunc g (a b)
  :input-contract (and (rationalp a) nil)
  :output-contract (rationalp (g a b))
  t)
Inadmissible.  This function should be outputting a rational, but it always returns t.
(defunc exists-boolean (l)
  :input-contract (listp l)
  :output-contract (booleanp (exists-boolean l))
  (and (not (endp l))
       (or (booleanp (first l))
       (exists-boolean (rest l)))))
0. ~(listp l) -> phi
1. (listp l) /\ (~(endp l) /\ (booleanp (first l))) /\ phi | (l (rest l)) -> phi
2. (listp l) /\ (~(endp l) /\ (exists-booleanp (rest l))) /\ phi | (l (rest l)) -> phi
4.
(defunc k-th-element (k l)
  :input-contract (and (integerp k) (listp l))
  :output-contract t
  (if (endp l)
    nil
    (if (equal k 0)
      (first l)
      (k-th-element (- k 1) l))))
If the funtion is given -1 as input for k, the recursive calls will get further from 0.
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; D. Induction Proofs - II
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
Consider:
(defunc in (x l)
  :input-contract (listp l)
  :output-contract (booleanp (in x l))
  (cond ((endp l)            nil)
        ((equal x (first l)) t)
        (t                   (in x (rest l)))))
        
(defunc subsetp (l1 l2)
  :input-contract (and (listp l1) (listp l2))
  :output-contract (booleanp (subsetp l1 l2))
  (if (endp l1)
    t
     (and (in (first l1) l2)
         (subsetp (rest l1) l2))))
         
(defunc intersect (l1 l2)
  :input-contract (and (listp l1) (listp l2))
  :output-contract (listp (intersect l1 l2))
  (cond ((endp l1)          nil)
        ((in (first l1) l2) (cons (first l1) (intersect (rest l1) l2)))
        (t                  (intersect (rest l1) l2))))
        
(defunc append (l1 l2)
   :input-contract (and (listp l1) (listp l2))
   :output-conract (listp (append l1 l2))
   (cond ((endp l1) l2)
         (t (cons (first l1) (append (rest l1) l2)))))
   
   
(a) Using a suitable induction scheme, prove the following theorem.
phi_a: (listp l1) /\ (listp l2) => (subsetp l1 (append l1 l2))
0) ~(listp l1) /\ ~(listp l2) => phi_a
   ~(listp l1) /\ ~(listp l2) => (listp l1) /\ (listp l2) => (subsetp l1 (append l1 l2))
   ~(listp l1) /\ ~(listp l2) /\ (listp l1) /\ (listp l2) => (subsetp l1 (append l1 l2))
   F => (subsetp l1 (append l1 l2))
   T
   
1) (listp l1) /\ (listp l2) /\ (endp l1) => phi_a
   (listp l1) /\ (listp l2) /\ (endp l1) => (listp l1) /\ (listp l2) => (subsetp l1 (append l1 l2))
   (listp l1) /\ (listp l2) /\ (endp l1) /\ (listp l1) /\ (listp l2) => (subsetp l1 (append l1 l2))
   (listp l1) /\ (listp l2) /\ (endp l1) => (subsetp l1 (append l1 l2))
   C1: (listp l1)
   C2: (listp l2)
   C3: (endp l1)
   
   (subsetp l1 (append l1 l2))
   = {def. append}
   (subsetp l1 l2)
   = {def subsetp}
   t
   
2) (listp l1) /\ (listp l2) /\ (in (firstl l1) l2) /\ phi_a|(l1: rest l1) => phi_a
   C1: (listp l1)
   C2: (listp l2)
   C3: (in (first l1) l2)
   C4: (listp (rest l1)) /\ (listp l2) => (subsetp (rest l1) (append (rest l1) l2))
   Derived contexts:
   C5: (listp (rest l1))
   C6: (subsetp (rest l1) (append (rest l1) l2))
   
   (subsetp l1 (append l1 l2))
   = {def. append}
   (subset l1 (cons (first l1) (append (rest l1) l2)))
   = {def. subsetp}
   (and (in (first l1) (cons (first l1) (append (rest l1) l2)))
        (subset (rest l1) (cons (first l1) (append (rest l1) l2))))
   = {def. in; axioms of cons, first}
   (and t (subset (rest l1) (cons (first l1) (append (rest l1) l2))))
   = {def. 
   (and t (or (subset (rest l1) (list (first l1))) (subsetp (Rest l1) (append (rest l1) l2))))
   = {C6}
   (and t (or (subset (rest l1) (list (first l1))) t))
   = {axiom of or}
   (and t t)
   = {axiom of and}
   t
(b) Using a suitable induction scheme, prove the following theorem.
phi_b: (listp l1) /\ (listp l2) /\ (subsetp l1 l2) => (intersect l1 l2) = l1
0. ~((listp l1) /\ (listp l2)) -> phi
1. (listp l1) /\ (listp l2) /\ (endp l1) -> phi
2. (list l1) /\ (listp l2) /\ ~(endp l1) /\ (in (first l1) l2) /\ phi | (l1 (rest l1)) -> phi
3. (list l1) /\ (listp l2) /\ ~(endp l1) /\ ~(in (first l1) l2) /\ phi | (l1 (rest l1)) -> phi
                                                                         
                                                          
                                                                         
0.
~((listp l1) /\ (listp l2)) -> (listp l1) /\ (listp l2) /\ (subsetp l1 l2) => (intersect l1 l2) = l1
~(listp l1) /\ ~(listp l2) /\ (listp l1) /\ (listp l2) /\ (subsetp l1 l2) => (intersect l1 l2) = l1
F -> (intersect l1 l2) = l1
T
1.
(listp l1) /\ (listp l2) /\ (endp l1) -> (listp l1) /\ (listp l2) /\ (subsetp l1 l2) => (intersect l1 l2) = l1
(listp l1) /\ (listp l2) /\ (endp l1) /\ (sub l1 l2) -> (intersect l1 l2) = l1
Context:
c1. (listp l1)
c2. (listp l2)
c3. (endp l1)
c4. (sub l1 l2)
(inersect l1 l2)
{definition of intersect, c3}
nil
{c3}
nil = nil
2.
(listp l1) /\ (listp l2) /\ ~(endp l1) /\ (in (first l1) l2) /\ phi | (l1 (rest l1))
  /\ (sub l1 l2) -> (intersect l1 l2) = l1
Context:
c1. (listp l1)
c2. (listp l2)
c3. ~(endp l1)
c4. (intersect (first l1) l2)
c5. (listp (rest l1)) /\ (listp l2) /\ (subset (rest l1) l2) -> (intersect l1 l2) = l1
Derived
c6. (listp (rest l1) l2)
c7. (subset (rest l1) l2)
c8. (intersect (rest l1) l2) = (rest l1)
c9. (subset l1 l2)
(intersect l1 l2)
{definition of intersect}
(cons (first l1) (intersect (rest l1) l2))
{c8}
(cons (first l1) (rest l1))
{axioms of cons}
l1
l1 = l1
3.
(listp l1) /\ (listp l2) /\ ~(in (first l1) l2) /\ ~(endp l1) /\ phi | (l1 (rest l1))
/\ (subset l1 l2) -> (intersect l1 l2) = l1
c1. (listp l1)
c2. (listp l2)
c3. ~(intersect (first l1) l2)
c4. (~endp l1)
c5. (listp (rest l1)) /\ (listp l2) /\ (subset (rest l1) l2) -> (intersect (rest l1) l2) = (rest l1)
c6. (listp (rest l1))
c7. (subset (rest l1) l2)
c8. (intersect (rest l1) l2) = (rest l1)
c9. (subset l1 l2)
(subset l1 l2)
{definition of subset}
(and (in (first l1) l2) (subset (rest l1) l2))
{c8, c9}
(and nil t)
{axioms of and}
nil
nil -> phi
True
(c) Using a suitable induction scheme, prove the following theorem. You
might need to employ previously proved theorems as "lemmas".
phi_c: (listp l1) /\ (listp l2) /\ (subsetp l1 l2) => (intersect l1 (append l1 l2)) = l1
0. ~((listp l1) /\ (listp l2)) -> phi
1. (listp l1) /\ (listp l2) /\ (endp l1) -> phi
2. (list l1) /\ (listp l2) /\ ~(endp l1) /\ (in (first l1) l2) /\ phi | (l1 (rest l1)) -> phi
                                                                        (l2        l2)
3. (list l1) /\ (listp l2) /\ ~(endp l1) /\ ~(in (first l1) l2) /\ phi | (l1 (rest l1)) -> phi
                                                                         (l2        l2)
                                                                         
                                                                         
                                                                         
0. ~((listp l1) /\ (listp l2)) -> (listp l1) /\ (listp l2) /\ (subsetp l1 l2) 
     => (intersect l1 (append l1 l2)) = l1
     
~(listp l1) /\ ~(listp l2) /\ (listp l1) /\ (listp l2) /\ (subset l1 l2) 
  => (intersect l1 (append l1 l2)) = l1
 F -> (intersect l1 l2) = l2
 
 T
1. (listp l1) /\ (listp l2) /\ (endp l1) -> (listp l1) /\ (listp l2) /\ (subsetp l1 l2) 
     => (intersect l1 (append l1 l2)) = l1
Context;
c1. (listp l1)
c2. (listp l2)
c3. (endp l1)
c4. (subset l1 l2)
(intersect l1 (app l1 l2))
{definition of app}
(intersect l1 l2)
{definition of intersect}
nil = l1
{c3}
nil = nil
T
     
2. 
(listp l1) /\ (listp l2) /\ ~(endp l1) /\ (in (first l1) l2) /\
(listp (rest l1)) /\ (subset (rest l1) l2) -> (intersect (rest l1) (app (rest l1) l2) = (rest l1))
->(listp l1) /\ (listp l2) /\ (subset l1 l2) -> (intersect l1 (app l1 l2)) -> l1
Context;
c1. (listp l1)
c2. (listp l2)
c3. ~(endp l1)
c4. (in (first l1) l2)
Derived
c5. (listp (rest l1))
c6. (subset (rest l1) l2)
c7. (intersect (rest l1) (app (rest l1) l2) = (rest l1)
c8. (subset l1 l2)
(intersect l1 (app l1 l2)) 
{definition of intersect}
(cons (first c1) (intersect (rest l1) (app l1 l2)))
{C7}
(cons (first l1) (rest l1))
{axioms of cons}
l1
l1 = l1
T
3.
(listp l1) /\ (listp l2) /\ ~(in (first l1) l2) /\ ~(endp l1)
/\ phi | (l1 (rest l1)) -> (listp l1) /\ (listp l2) /\ (subset l1 l2)
=> (intersect l1 (app l1 l2)) = l1
Context:
c1. (listp l1)
c2. (listp l2)
c3. ~(in (first l1) l2)
c4. ~(endp l1)
c5. (listp (rest l1) /\ (listp l2) /\ (subset (rest l1) l2) 
    -> (intersect (rest l1) (app (rest l1) l2)) = (rest l1)
c6. (listp (rest l1))
c7. (subset (rest l1)  l2)
c8. (intersect (rest l1) (app (rest l1) l2)) = (rest l1)
c9. (subset l1 l2)
(subset l1 l2)
{definition of subset}
(and (in (first l1) l2) (subset (rest l1) l2))
{c8, c9}
(and nil t)
{axioms of and}
nil
nil -> phi
True
|#