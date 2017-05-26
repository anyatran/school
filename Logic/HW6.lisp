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
CS 2800 Homework 6 - Spring 2014
Student names: Anh Tran, Tyler Kaminsky
Technical instructions:
- open this file in ACL2s as hw06.lisp
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
 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Part A: Admissible
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
For each of the definitions below, check whether it is admissible, i.e. it
satisfies the definitional principle. You can assume that f is a new
function symbol in each case.
If yes,
1. Provide a measure function that can be used to show termination.
2. Write down the definitional axiom that ACL2 will add to its database.
3. Write down the contract theorem that ACL2 will add to its database. Explain
   in English why it holds.
4. Explain why the body contracts hold.
If no,
1. Identify which of the 6 conditions in Sec 4.1 of the notes are violated.
2. (a) If IV is satisfied, provide a measure function.
2. (b) If IV is violated, provide a valid input on which the function does
       not terminate.
Note that some of the conditions of admissibility make no sense if certain
others are violated. For example, if the function does not terminate on all
inputs (IV is violated), there is no point checking the contract theorem
(V). Similarly, if the body contains free variable symbols other than the
xi, it cannot be evaluated, so the remaining conditions make no sense.
;;to prevent us from going back and forth between computer and notes
Definitional Principle of ACL2
***SYNTACTIC***
1. F is a new function symbol
2. The variables Xi in (F X1 X2...) are distinct
3. The body does not contain free variables or functions
***BEHAVIORAL***
4. F is terminating
5. Input-contract implies the output-contract
6. Body contracts hold.]
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
a)
(defunc f (l n)
  :input-contract (and (listp l) (natp n))
  :output-contract (listp (f l n))
  (cond ((equal n 0) nil)
    ((equal n 1) (list l))
        ((equal n 5) (list (list l)))
        ((endp l)    (list n))
        (t           (f (cons n l) (- n 1)))))
Yes.
1. A measure function that returns n should work.  The recursive call always 
   utilizes n-1, which is less than n.
   Eventually, the recursive call that decrements n by 1 will satisfy the 
   condition statements when n is equal to 5, 1, or 0. 
   (defunc m (l n)
     :input-contract (and (listp l) (natp n))
     :output-contract (listp (f l n))
      n )
   
2. (and (listp l) (natp n)) = > (f l n) = 
   (cond ((equal n 0) nil)
    ((equal n 1) (list l))
        ((equal n 5) (list (list l)))
        ((endp l)    (list n))
        (t           (f (cons n l) (- n 1))))
3. (and (listp l) (natp n)) = > (listp (f l n))
4. The body contracts all hold.  endp is only ever given a list, equal can handle to
   arguments of any type, cons is only ever given a natural and a list
   , and - takes two naturals.  In order for f to be accepted,
   n must be a natural and one is already a natural.
b)
(defunc f (x)
  :input-contract (natp x)
  :output-contract (integerp (f x))
  (if (equal x 0)
      3
    (- 10 (f (- f (f 1))))))
No.
1. f will never terminate.  The recursive call to f with an input
   of 1 will infinitely make more calls to f unless f starts with an input
   of 0.
2.(f 2)
c)
(defunc f (x)
  :input-contract (integerp x)
  :output-contract (integerp (f x))
  (if (equal x 0)
      3
    (- 10 (f (- x 1)))))
No.
1. May not terminate because the function may recur with non-natural input.
2. (f -3)
d)
(defunc f (x y)
  :input-contract (and (listp x) (integerp y))
  :output-contract (listp (f x y))
  (if (equal y 0)
      x
    (f (rest x) (- y 1))))
No.
1. May not terminate, may recur with non-natural input.
2. (f -1)
    
    
e)
(defunc f (x y)
  :input-contract (and (listp x) (integerp y))
  :output-contract (natp (f x y))
  (if (endp x)
      y
    (+ 1 (f (rest x) y))))
No.
1. May not return a natural number as output. 
2. A measure function that returns the rest of x.
   If y is a negative number with an absolute value
   greater than the length of x, the statement 
   (endp x) will return a number that is not a natural, which
   would break the output contract of f.
f)
(defunc f (x)
  :input-contract (natp x)
  :output-contract (integerp (f x))
  (if (equal x 0)
      3
    (- 10 (f x 1))))
No.
1. F is given two arguments in the recursive call when it only takes
   one argument as input.  Body contract does not hold.
2. (f 5)
g)
(defunc f (x y)
  :input-contract (and (listp x) (integerp y))
  :output-contract (listp (f x y))
  (if (equal y 0)
      x
    (f (rest x) (- y 1))))
No.
1. May not terminate, may recur with non-natural input.
2. (f '(1 2 3 4 5) -1)
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Part B: Body Contracts and Contract Theorem
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;a) Consider the following function definition:
;(defunc accord (i j c)
;  :input-contract (and (natp i) (natp j) (natp c) (< i j))
;  :output-contract (natp (accord i j c))
;  (cond ((and (equal i 0) (equal j 1)) c)
;        ((> i 0) (accord (- i 1) j (+ c 1)))
;        (t       (accord (- j 2) (- j 1) (+ c 1)))))
;(i) Show that the constraint (< i j) in the input contract of accord holds
;for each recursive call of accord, under the assumption that the full input
;contract holds for the current call.
; Since i must be smaller than j to begin with, and i can never be less than zero,
; the final clause of the conditional will never be reached.  The final clause is 
; the only way that j could be made to be less than i, but since that case is never
; reached, j is never less than i.
;(ii) Suppose we weaken the input contract to
;    (and (natp i) (natp j) (natp c))
;Provide an input (i j c) that satisfies the weaker input contract, but one
;of the recursive calls in the body of accord causes a contract violation.
;Clearly indicate where in the body that contract violation happens.
;Hint: Your input must violate (< i j) !
; (accord 3 1 7)
; Running accord with these inputs will reach the final clause of the conditional,
; which will recur on accord with inputs of -1, 0, and 8.  Since -1 is not a nat,
; the input contract would be violated
;b) Consider the following function definition:
;(defunc f (x y z)
;  :input-contract (and (natp x) (natp y) (listp z))
;  :output-contract (natp (f x y z))
;  (cond ((equal x 0) (+ y (len z)))
;        ((endp z)    (+ x y))
;        ((<= y 1)    (+ x (f (- x 1) 0 (rest z))))
;        ((< x y)     (f x (- y 2) z))
;        (t           (f x y (rest z)))))
;(i) Formulate the contract theorem for the above function definition in
;propositional logic. You may use ACL2 expressions such as (equal x 0) as
;propositional atoms in this formula. (You do not need to prove anything in
;this part.)
; (and (natp x) (natp y) (listp z)) => (natp (f x y z))

;(ii) "The body contracts of all functions must hold, provided the input
;  contract of f holds."
;Formalize this condition in propositional logic, but only for the
;expression (f x (- y 2) z) , which is guarded by (< x y). To help you get
;started, part of this condition is provided; fill in the rest. Even if the
;parts you need to fill in appear trivial or are already present in earlier
;parts of the condition, write them down. (You do not need to prove anything
;in this part.)
;  (natp x) /\ (natp y) /\ (listp z) /\
;  /\ ~(equal x 0) /\ ~(endp z) /\ ~(<= y 1) /\ (< x y)
;  => (y >= 2)
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Part C: Termination
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;(a) Albert is considering the following function:
;    (defunc wicked (a b c)
;      :input-contract (and (integerp a) (posp b) (integerp c))
;      :output-contract (integerp (wicked a b c))
;      (cond ((>= a b) a)
;            ((>= b c) b)
;            ((< c 0)  (wicked (+ a b) b c))
;            (t        (wicked a b (+ a b)))))
;(i) He claims that this function will be admitted by ACL2s since its body is a
;legal expression satisfying all body contracts, and the output contract is
;trivially satisfied as well. But he forgot something! Show that the
;function does not (always) terminate, by giving an input in the form of
;concrete values a,b,c that satisfies the input contract but causes an
;infinite execution.

; (wicked 1 10 11)

;(ii) Albert admits there were several typos in his definition. His new
;proposal is (read carefully!):
(defunc wicked (a b c)
  :input-contract (and (posp a) (posp b) (integerp c))
  :output-contract (integerp (wicked a b c))
  (cond ((>= a b) a)
        ((< c 0)  (wicked (+ a b) b c))
        (t        (wicked a b (- a b)))))

(check= (wicked 10 8 6) 10)
(check= (wicked 8 10 -9) 18)
(check= (wicked 8 10 6) 18)
(check= (wicked 19 10 -1) 19)

;Prove that this function terminates, by defining a measure function in the
;template below.
;Hint: First run function wicked on a few well-chosen test cases (exercising
;all clauses of the cond) and see what happens. How long can chains of
;recursive calls to this function actually be?
(defunc m (a b c)
  :input-contract (and (posp a) (posp b) (integerp c))
  :output-contract (natp (m a b c))
  (cond 
   ((>= a b) 0)
   ((< c 0) 1)
   (t 2))
)
;Be sure that m would be admitted by ACL2s. Then prove that, on every
;recursive call of wicked, the value of m decreases, under the conditions of
;that recursive call.

; original: (m 9 10 6) = 3 recursives
; after 1st recursive: (m 9 10 -1) = 1 recursive
; after 2nd recursive: (m 19 10 -1) = 0 recursive
; (m 9 10 6) > (m 9 10 -1) > (m 19 10 -1)

;(b) Consider the following function definition:
;(defunc count (i j c)
;  :input-contract (and (natp i) (natp j) (natp c))
;  :output-contract (natp (count i j c))
;  (cond ((and (equal i 0) (equal j 0)) c)
;        ((> j 0) (count i (- j 1) (+ c 1)))
;        (t       (count (- i 1) 9 (+ c 1)))))
;(i) Consider the call (count 2 3 0). Below are the first three argument
;tuples that appear in recursive calls to count. Show the next three:
;(2 3 0)  (2 2 1)  (2 1 2)  
; (2 0 3)  (1 9 4)  (1 8 5)
;What does (count 2 3 0) evaluate to? 
;23
;(ii) Using the insights generated in (a), show that function count
;terminates, by defining a measure function. Be sure that m would be
;admitted by ACL2s. Then prove that, on every recursive call of count, the
;value of m decreases, under the conditions of that recursive call.
(defunc m2 (i j c)
  :input-contract (and (natp i) (natp j) (natp c))
  :output-contract (natp (m2 i j c))
  (+ (* 10 i) j))
;Proof that the value of m decreases on recursive calls.
(check= (m2 2 3 0) 23) ; original
(check= (m2 2 2 1) 22) ; first recursive
(check= (m2 2 1 2) 21) ; second recursive
(check= (m2 2 0 3) 20) ; third recursive
(check= (m2 1 9 4) 19) ; fourth recursive
; m on original > m on first recursive > m on second recursive > m on third recursive > m on fourth recursive
