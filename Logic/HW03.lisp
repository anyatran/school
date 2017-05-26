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
CS 2800 Homework 3 - Spring 2014
Student names: Anh Tran, Tyler Kaminsky
For this homework you will need to use ACL2s.
Technical instructions:
- open this file in ACL2s as hw03.lisp
- set the session mode to "Beginner" (NOT "Bare Bones" !)
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
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; ACL2 Programming
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; You are allowed to write the following functions in program mode. This
; means that ACL2 does not try to prove all of the properties that it
; normally proves before admitting a function. We skip this here since
; proving some of these properties requires some non-trivial interaction
; with ACL2.
; However, to receive full credit your function definitions must still be
; correct, and all check= tests must pass.
; Program mode is turned on using :program, as done below. Feel free to
; comment out this line and see whether ACL2 admits your functions after
; rigorous static checking.
:program
; The following four functions define recognizers for lists of natural
; numbers, and lists of rational numbers. Read but do not modify these
; functions. Try to understand why the main functions natlisp and
; rationalp, which you will need later on in this homework, are defined
; using auxiliary functions ...-aux .
(defunc natlistp-aux (l)
  :input-contract (listp l)
  :output-contract (booleanp (natlistp-aux l))
  (cond ((endp l) t)
        ((not (natp (first l))) nil)
        (t (natlistp-aux (rest l)))))
; a recognizer for list of natural numbers
(defunc natlistp (l)
  :input-contract t
  :output-contract (booleanp (natlistp l))
  (if (listp l)
    (natlistp-aux l)
    nil))
(defunc rationallistp-aux (l)
  :input-contract (listp l)
  :output-contract (booleanp (rationallistp-aux l))
  (cond ((endp l) t)
        ((not (rationalp (first l))) nil)
        (t (rationallistp-aux (rest l)))))
; a recognizer for list of rational numbers
(defunc rationallistp (l)
  :input-contract t
  :output-contract (booleanp (rationallistp l))
  (if (listp l)
    (rationallistp-aux l)
    nil))
; Define
; rem: Nat x Nat-{0} -> Nat
; (rem x y) returns the remainder of the integral division of x by y.
; Hint: write a recursive function. The base case will be that x is
; divisible by y. If it is not: how do (rem 11 3) and (rem 10 3) differ?
(defunc divides (x y)
  :input-contract (and (natp x) (natp y))
  :output-contract (booleanp (divides x y))
  (if (equal y 0)
    nil
    (if (natp (* x (unary-/ y))) t nil)))

(defunc rem (x y)
  :input-contract (and (natp x) (and (natp y) (> y 0)))
  :output-contract (natp (rem x y))
    (if (divides x y)
      0
      (+ 1 (rem (- x 1) y))))
(check= (rem 2 4) 2)
(check= (rem 4 2) 0)
(check= (rem 11 3) 2)
(check= (rem 20 10) 0)

; Define
; nat/: Nat x Nat-{0} -> Nat
; (nat/ x y) returns the result of integer division of x by y.
; That is, it returns the integral part (floor) of x/y,
; which is a natural number. See the examples below.
; Hint: this is a non-recursive function. Use rem from above.
(defunc nat/ (x y)
  :input-contract (and (natp x) (and (natp y) (not (equal 0 y))))
  :output-contract (natp (nat/ x y))
    (* (- x (rem x y)) (unary-/ y)))
    
(check= (nat/ 10 2) 5)
(check= (nat/ 11 2) 5)
(check= (nat/ 16 4) 4)
(check= (nat/ 19 4) 4)

; Define
; add-digits: Nat -> Nat
; (add-digits x) returns the sum of the decimal digits in x,
; which is a natural number.
; Hint: Write a recursive definition that sums up the digits in x from the
; least significant to the most significant. The function rem will be
; helpful.
(defunc add-digits (x)
  :input-contract (natp x)
  :output-contract (natp (add-digits x))
  (if (<= x 9)
    x
    (add-digits (+ (nat/ x 10) (rem x 10)))))
(check= (add-digits 000) 0)
(check= (add-digits 123) 6)
(check= (add-digits 801) 9)
(check= (add-digits 041) 5)
; Define
; nat2list: Nat -> Natlist
; (nat2list x) takes a natural number and returns its digits as a list of naturals.
; Hint: Write an auxiliary, recursive definition that traverses the digits
; in x from the least significant to the most significant. The rem function
; will be helpful.
; Then write the non-recursive main function that calls the auxiliary
; function and does some post processing. Beginner mode has a built-in
; function (rev x). Use :doc to find out about it.
(defunc nat2list-aux (x)
  :input-contract (natp x)
  :output-contract (natlistp (nat2list-aux x))
  (if (<= x 9)
    (cons x ())
    (cons (rem x 10) (nat2list-aux (nat/ x 10)))))

(defunc nat2list (x)
  :input-contract (natp x)
  :output-contract (natlistp (nat2list x))
  (rev (nat2list-aux x)))
(check= (nat2list 000) '(0))
(check= (nat2list 123) '(1 2 3))
(check= (nat2list 103) '(1 0 3))
(check= (nat2list 2311) '(2 3 1 1))
; Define
; has-dups: List -> Boolean
; (has-dups l) returns t if l contains any element (at least) twice; nil otherwise.
(defunc elem (a X)
  :input-contract (and t (listp X))
  :output-contract (booleanp (elem a X))
  (if (endp X)
    nil
    (if (equal a (first X))
      t
      (elem a (rest X)))))

(defunc has-dups (l)
  :input-contract (listp l)
  :output-contract (booleanp (has-dups l))
  (if (endp l)
    nil
    (if (elem (first l) (rest l))
      t
      (has-dups (rest l)))))
(check= (has-dups '(1))   nil)
(check= (has-dups '(1 1)) t)
(check= (has-dups '( 1 2 4 3 2)) t)
(check= (has-dups '( 1 9 8 4 3 2)) nil)
; Define
; how-many: All x List -> Nat
; (how-many e l) returns the number of occurrences of e in l.
(defunc how-many (e l)
  :input-contract (and t (listp l))
  :output-contract (natp (how-many e l))
  (if (endp l)
    0
    (if (equal e (first l))
      (+ 1 (how-many e (rest l)))
      (how-many e (rest l)))))
(check= (how-many  1 ())     0)
(check= (how-many  1 '(1 1)) 2)
(check= (how-many  1 '(1 0 8)) 1)
(check= (how-many  1 '(1 10)) 1)
; Define
; minimum: Rationallist-{0} -> Rational
; (minimum l) returns the minimum among the numbers in l. Note that l is
; required to be non-empty.
; Hint: first write a binary function min : Rational x Rational -> Rational
; with the obvious meaning. Then define minimum recursively.

(defunc minimum-aux (x y)
  :input-contract (and (rationalp x) (rationalp y))
  :output-contract (rationalp (minimum-aux x y))
  (if (< x y) x y))

(check= (minimum-aux 3 7) 3)

(defunc minimum (l)
  :input-contract (and (rationallistp l) (not (endp l)))
  :output-contract (rationalp (minimum l))
  (if (endp (rest l)) (first l)
    (minimum-aux (first l) (minimum (rest l)))))
  
(check= (minimum '(4 2 5 -2/3)) -2/3)
(check= (minimum '(1)) 1)
(check= (minimum '(1 0 22)) 0)
(check= (minimum '(99 1 22)) 1)
;Define
; del-el : Nat x Natlist -> Natlist
; (del-el n l) removes the first occurrence of n in l, if any.
(defunc del-el (n l)
  :input-contract (and (natp n) (natlistp l))
  :output-contract (natlistp (del-el n l))
  (if (endp l) ()
    (if (equal n (first l)) (rest l)
      (cons (first l) (del-el n (rest l)))))
)
(check= (del-el 1 '( 1 1 1 4)) '( 1 1 4))
(check= (del-el 1 nil) nil)
(check= (del-el 2 '( 4 1 1 4)) '( 4 1 1 4))
(check= (del-el 1 '( 4 1 1 4)) '( 4 1 4))
(check= (del-el 4 '( 3 2 5)) '( 3 2 5))

;Define
; scale-natlist : Nat x Natlist -> Natlist
; (scale-natlist s l ) takes a list of natural numbers as input and
; scales it by the factor of s, i.e. each element is multiplied by s.
(defunc scale-natlist (s l)
  :input-contract (and (natp s) (natlistp l))
  :output-contract (natlistp (scale-natlist s l))
  (if (endp l) ()
    (cons (* s (first l)) (scale-natlist s (rest l))))
)
(check= (scale-natlist 3 '( 1 2 3)) '( 3 6 9))
(check= (scale-natlist 3 nil) nil)
(check= (scale-natlist 4 '( 3 0 5)) '( 12 0 20))
(check= (scale-natlist 2 '( 2 1 )) '( 4 2))
; Define
; rem-rep-list : Natlist -> Natlist
; (rem-rep-list l) replaces double consecutive occurrences of a number in
; the Natlist l by a single occurrence.
(defunc rem-rep-list (l)
  :input-contract (natlistp l)
  :output-contract (natlistp (rem-rep-list l))
  (cond ((endp l) l)
        ((endp (rest l)) l)
        ((equal (first l) (second l)) (rest l))
        (t (cons (first l) (rem-rep-list (rest l))))))
         
   
(check= (rem-rep-list '( 1 2 3 4)) '( 1 2 3 4))
(check= (rem-rep-list '( 1 1 3 4)) '( 1 3 4))
(check= (rem-rep-list '( 1 3 1 4)) '( 1 3 1 4))#|ACL2s-ToDo-Line|#

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Propositional logic
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
We use the following ascii character combinations to represent the Boolean
operators:
  NOT     ~
  AND     /\
  OR      \/
  IMPLIES =>
  EQUIV   =
  XOR     <>
The binding powers of these functions are listed from highest to lowest
in the above table. Within one group (no blank line), the binding powers
are equal. This is the same as in class.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Construct the truth table for the following Boolean formulas. Use
alphabetical order for the variables in the formula.
  (a => b) /\ (b => c) /\ (a <> c)
  
  1 = (a => b) /\ (b => c)
  2 = (a => b) /\ (b => c) /\ (a <> c)
  
  
a    b     c    a=>b    b=>c   a<>c    1      2
----------------------------------------------------------
T    T     T     T      T       F      T      F
T    F     F     F      T       T      F      F
T    T     F     T      F       F      F      F
T    F     T     F      T       T      F      F
F    F     T     T      T       F      T      F
F    T     T     T      T       T      T      T
F    T     F     T      F       T      F      F
F    F     F     T      T       F      T      F

  (p => r) /\ (q => r) /\ (~p => ~r)
  
  1 = (p => r) /\ (q => r)
  2 = (p => r) /\ (q => r) /\ (~p => ~r)
  
  p    r     q     p=>r  q=>r   ~p=>~r    1    2
  ----------------------------------------------------------
  T    T     T     T      T      T        T    T
  T    F     F     F      T      T        F    F
  T    T     F     T      T      T        T    T
  T    F     T     F      F      T        F    F
  F    F     T     T      F      T        F    F
  F    T     T     T      T      F        T    F
  F    T     F     T      T      F        T    F
  F    F     F     T      T      T        T    T
  
  (p <> q) <> r
  p    q     r   p<>q      (p <> q) <> r
 ----------------------------------------------------------
  T    T     T    F          T
  T    F     F    T          T
  T    T     F    F          F
  T    F     T    T          F
  F    F     T    F          T
  F    T     T    T          F
  F    T     F    T          T
  F    F     F    F          F
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Garry suggests to introduce a new Boolean connective, denoted *, and has
defined its meaning using a truth table. Unfortunately, coffee stains have
smudged the table to the extent that some entries are unreadable. These are
marked below with ? .
  a  b  a * b
  =============
  T  T    ?
  T  F    T
  F  T    ?
  F  F    F
Garry remembers that he was able to prove that
  a /\ b => (a * b)
is a tautology, i.e. it evaluates to T for all a,b. He also remembers that
* is indeed a "new" binary connective. That is, a * b is not equivalent to
any one of a /\ b, a \/ b, a => b, a <> b, and a = b.
(a) Prove that these data uniquely define *, and replace the ugly coffee
stains in the table with the correct truth values.
  a  b   a*b     a /\ b      a /\ b => (a * b)
  ==============================================
  T  T    T      T                  T
  T  F    T      F                  T
  F  T    F      F                  T
  F  F    F      F                  T
  
  
(b) Is this connective really new? How can you simplify a * b ?
a * b = a, but then it's now a connective

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Someone suggests to introduce a new Boolean connective, *, such that a * b
abbreviates the expression ~(~a /\ b).
(a) Formally define this connective, using a truth table.
a   b   a * b
-------------
T   T    T
T   F    T
F   T    F
F   F    T
(b) Do we really need this new connective, or can you express a * b using a
single connective that we already know?
a * b = b => a
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(a) Show that there is a unique Boolean function h over two variables a and
b such that the following conditions hold. Do this by building a truth
table for h, show this truth table, and explain how you obtained each entry.

1. h is reflexive: for all Boolean values a, h(a,a) = T
2. h is symmetric: for all Boolean values a,b, h(a,b) = h(b,a)
3. h is not valid, i.e. at least one input (a,b) makes h false.

a   b    h(a,b)       1) h(a,a): h(T,T) of h(F,F) = T
===============       2) h(a,b) = h(b,a): h(T, F) = h(F, T): should be either T of F
T   T     T           3) at least one input makes h F: that means h(a,b) is F
T   F     F
F   T     F
F   F     T


(b) Which well-known Boolean connective * satisfies h(a,b) = a * b ?

= (equivalence)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Show that the connectives = (equivalence) and <> (xor) are *equally
expressive*. That is:
- determine a propositional logic formula involving variables x and y, the
connective <>, the constants T and F, and parentheses, but NOTHING ELSE,
that is equivalent to the formula x = y.
  (x = y) = (x <> y) <> T
  
- determine a propositional logic formula involving variables x and y, the
connective =, the constants T and F, and parentheses, but NOTHING ELSE,
that is equivalent to the formula x <> y.
  (x <> y) = (x = y) = F
  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
A connective c is called *T-reproducing* if there exists a Boolean formula
only involving c and Boolean variables (no other connectives and *no
constants*) that is equivalent to T. For example, = is T-reproducing since
x = x is equivalent to T (i.e. x = x is T for every x).
Which of the following connectives are T-reproducing? For each that is,
show how it reproduces T. For each that is not, clearly state so, and add
any comments you may have why you think it is not.
~  : no because ~ always outputs the opposite of x, so if x = T, then ~x is F
\/ : x \/ x - no because if there are 2 F inputs the output is going to be F
=> : x => x - yes
|#
