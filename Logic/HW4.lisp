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
CS 2800 Homework 4 - Spring 2014
Student names: 
Anh Tran
Tyler Kaminsky
For this homework you will need to use ACL2s.
Technical instructions:
- open this file in ACL2s as hw04.lisp
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
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Boolean formula simplification
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
Simplify the following formulas, i.e. find a simpler formula that is
logically equivalent to the given formula. "Simpler" means: with as few
logical connectives as possible. For example, the formula
(p \/ (p => q))
is a tautology. The simplest equivalent formula is therefore T.
In general, there may be several solutions that can be viewed as
"equally simple", e.g., a \/ b and b \/ a are both "simplest".
Find the answer by applying a suitable selection of simplification and
rewrite rules, as discussed in class and listed in the Lecture Notes. You
need some creativity here. It is useful to think one step ahead when
applying a rule: sometimes it may be required to build an intermediate
formula that is more complex than the one you started from, in order to
then be able to simplify the result drastically.
If all else fails, use this strategy: rewrite the formula using only the
"elementary" connectives ~   \/   and /\   , and simplify that as much as
possible, using basic identities such as p /\ ~p = F. At the end see if you
can write the result using fewer connectives, e.g. by combining ~p \/ q to
p => q.
Write your simplification/rewrite steps in the following format:
  (~p \/ ~q) /\ T
=
  (~p \/ ~q)
= { de Morgan }
  ~(p /\ q)
As shown above, give the name of the rule you are using if possible; this
may help us give you partial credit if you make a mistake.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  (a \/ ~b) => (c /\ ~a) \/ ~b
  ** p => q = ~p \/ q
  ~(a \/ ~b) \/ ~b \/ (c /\ ~a)
  ** de-morgans
  (~a /\ b) \/ ~b \/ (c /\ ~a) 
  ** distributivity
  (~a \/ ~b) /\ (b \/ ~b) \/ (c /\ ~a)
  ** simplification
  (~a \/ ~b) /\ T \/ (c /\ ~a)
  ** T \/ (c /\ ~a) = T
  (~a \/ ~b) /\ T
  ** simplification
  (~a \/ ~b)
  
  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ~(p \/ (~p/\ q))
  **De Morgan's
  (~p /\ (p \/ ~q))
  
  **Distributive
  (~p /\ p) \/ (~p /\ ~q)
  
  (F \/ (~p /\ ~q))
  
  * Simplification
  (~p /\ ~q)
  
  ** De morgans
  ~(p \/ q)
  

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  (p /\ ( ~p \/ q)) \/ ((p \/ ~q) /\ q)
  **Distributive
  ((p /\ ~p) \/ (p /\ q)) \/ ((p /\ q) \/  (q /\ ~q))
  
  **(a /\ ~a) = F
  (F \/ (p /\ q)) \/ ((p /\ q) \/ F)
  
  **(a \/ a) = a
  ((p /\ q) \/ F)
  
  ** simplification
  (p /\ q)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ~(~h /\ i) => (i => (~h /\ j))
  **De Morgan's
  (h \/ ~i) => (i => (~h /\ j))
  
  ** (h \/ ~i) = (i => h)
  (i => h) => (i => (~h /\ j))
  
  ** De-morgans
  (i => h) => (i => ~(h \/ ~j))
  
  ** (h \/ ~j) = (j => h)
  (i => h) => (i => ~(j => h)) OR??????
  
  
  **(p => q) = (~p \/ q) 
  (~h /\ i) \/ (~i \/ (~h /\ j))
  
  **Distributive
  (~h /\ i) \/ (~h \/ ~i) /\ (~i \/ j)
  
  
...
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  (a => b) /\ (~a => c) = (a /\ b) \/ (~a /\ c) = T
  
  **(a => b) = (~a \/ b)
  (~a \/ b) /\ (a \/ c) = (a /\ b) \/ (~a /\ c)
  
  
  
  (~a \/ b) /\ (a \/ c) = 1
  (a /\ b) \/ (~a /\ c) = 2
  (~a \/ b) /\ (a \/ c) = (a /\ b) \/ (~a /\ c) = 3
  
  a  b  c  ~a  ~a\/b  a\/c  a/\b  ~a/\c  1  2    3
------------------------------------------------------
  T  T  T   F     T     T     T     F    T  T    T
  T  F  F   F     F     T     F     F    F  F    T
  T  T  F   F     T     T     T     F    T  T    T
  T  F  T   F     F     T     F     F    F  F    T
  F  F  F   T     T     F     F     F    F  F    T
  F  T  T   T     T     T     F     T    T  T    T
  F  T  F   T     T     F     F     F    F  F    T
  F  F  T   T     T     T     F     T    T  T    T
  
Hint: for this one you may use a truth table if simplification rules get
you nowhere.



|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Boolean formula classification
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
Boolean functions can be classified as valid, satisfiable, unsatisfiable,
or falsifiable. These properties can overlap! For example, functions can be
both satisfiable and valid.
Classify the following Boolean formulas, according to these properties.
Several answer may be correct! You must state ALL of the four properties a
formula fulfills.
Give evidence for your decisions, as follows:
- simplify valid formulas to T, or show truth table,
- simplify unsatisfiable formulas to F, or show truth table,
- give a suitable satisfying or falsifying assignment for satisfiable and
  for falsifiable formulas.
When you simplify, use the guidelines given above for formula simplification.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  (x => y) => (~y => ~x) \/ z
  
  **(x => y) = (~x \/ y)
  (~ x \/ y) => (~x \/ y) \/ z
  ~(~ x \/ y) \/ (~x \/ y) \/ z
  
  ** ~(~ x \/ y) \/ (~x \/ y) = T
  T \/ z = T
  
  **Valid

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
   (x = ~z) /\ (~x <> z): UNSATISFIABLE
  1 =  (x = ~z)
  2 = (~x <> z)
  3 = (x = ~z) /\ (~x <> z)
  
  x   z   ~x   ~z    1    2    3
  ===================================
  T   T   F     F    F    T    F
  T   F   F     T    T    F    F
  F   T   T     F    T    F    F
  F   F   T     T    F    T    F
  
  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ((e /\ ~(g => (e \/ f))) \/ (~e /\ (f => e))) => (e \/ f)
      ** (g => (e \/ f)) = (~g \/ (e \/ f))
      ** (f => e) = (~f \/ e)
  ((e /\ ~(~g \/ (e \/ f))) \/ (~e /\ (~f \/ e)))  =>  (e \/ f)
      ** {de-morgans'}: ~(~g \/ (e \/ f)) = (g /\ (~e \/ ~f))
      ** {distributivity}: (~e /\ (~f \/ e)) = ((~e /\ ~f) \/ (~e /\ e)) = ((~e /\ ~f) \/ F) = (~e /\ ~f)
  ((e /\ (g /\ (~e \/ ~f))) \/ (~e /\ ~f)) => (e \/ f)
  ~ ((e /\ (g /\ (~e \/ ~f))) \/ (~e /\ ~f)) \/ (e \/ f)
      ** {de-morgans'}: ~((e /\ (g /\ (~e \/ ~f))) \/ (~e /\ ~f))   =   (~(e /\ (g /\ (~e \/ ~f))) /\ ~(~e /\ ~f)) =
           ((~e \/ ~(g /\ (~e \/ ~f))) /\ (e \/ f)) =  ((~e \/ (~g \/ ~(~e \/ ~f))) /\ (e \/ f))  = 
           ((~e \/ (~g \/ (e /\ f))) /\ (e \/ f))
  ((~e \/ (~g \/ (e /\ f))) /\ (e \/ f)) \/ (e \/ f)
      ** {absorption}: 
  (e \/ f)
  OR is both SATISFIABLE and FALSIFIABLE
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  (a => b) => z \/ ~(a /\ ~b) - VALID
  **(a => b) => z  = ~(~a \/ b) \/ z = (a /\ ~b) \/ z 
  
  (a /\ ~b) \/ z \/ (~a \/ b)
  
  ** if z = T, the output is T
  ** if z is F, the formula is (a /\ ~b) \/ (~a \/ b).
     In that case, (a /\ ~b) \/ (~a \/ b) = (a /\ ~b) \/ ~(a /\ ~b) {de morgan's}
     and the output is T
  ** if a = T, then the formula is  ~b \/ z \/ b, which equivalent to T \/ z = T
  ** if a = F, then the formula is F \/ z \/ T = T
  ** if b = T, then the formula is F \/ z \/ T = T
  ** if b = F, then the formula is a \/ z \/ ~a = T \/ z = T
  
  conclusion: this formula is VALID
  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  j /\ ~(~x => i) = (i \/ j) /\ (~j => x)
  ** (~x => i) = (x \/ i)
  ** (~j => x) = (j \/ x)
  
  j /\ ~(x \/i) = (i \/ j) /\ (j \/ x)
  ** ~(x \/i) = (~x /\ ~i)
  j /\ (~x /\ ~i) = (i \/ j) /\ (j \/ x)
  ** if j = T, and x, i = F - the output is T
  ** if j = F, then F = i /\ x. The output will be T if both i, x = T, otherwise the result is F
  conclusion: SATISFIED and FALSIFIABLE
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Equivalence checking
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
One of the most important logical tools in hardware design is an
equivalence checker, i.e. a machine EQUIV that takes two formulas f and g
as input and returns true exactly if f and g are equivalent. Describe a
procedure implementing an equivalence checker EQUIV(f,g), under the
following conditions:
1. All that is available to you is a *satisfiability checker*, i.e. a
machine SAT that takes a single formula as input and returns true exactly
if the formula is satisfiable.
2. In your procedure you may call SAT as often as you like, but *only* on
formulas built from f, g, and the propositional connectives ~ and /\.
For full credit you must clearly specify what your algorithm does, and how
it determines its answer (use English pseudo code). Writing down some
formulas is not enough.

~sat(a) /\ ~sat(b)
Two formulas can be seen as equivalent if ~Sat(a) /\ ~Sat(b) and only  if neither is
satisfiable, then both must be unsatisfiable.  This only works in the case that
both a and b are not satisfiable.  If one is satisfiable and one is valid, then 
there is no way to tell if they are equivalent because calling sat on both of them would return true
For example, /\ and \/ are both satisfiable, but they are not equivalent.
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Programming exercise: Ripple-carry Adder
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
An adder is a circuit that takes two sequences of bits, interprets them as
numbers in binary representation, adds them, and outputs the result,
including a possible overflow. In this homework you are asked to write such
an adder. As a result, using nothing but simple Boolean operations we can
add integers!
|#
; We begin by defining data types we will need.
; Binary digits {0,1}, and lists of binary digits:
(defdata digit (oneof 1 0))
(check= (digitp t) nil)
(check= (digitp 1) t)
(check= (digitp 2) nil)
(defdata digitlist (listof digit))
(check= (digitlistp '(1 0 t 0)) nil)
; booleanlist:
(defdata booleanlist (listof boolean))
(check= (booleanlistp '(t nil t)) t)
; Define the auxiliary function
; xor: Boolean x Boolean -> Boolean
(defunc xor (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (xor a b))
  (not (equal a b)))
(check= (xor t nil) t)
(check= (xor t t) nil)
(check= (xor nil nil) nil)
; Define two functions that implement a half-adder. Both functions take two
; Booleans a and b as input, interpreting nil as 0 and t as 1. Adding two
; single-digit binary numbers produces at most a 2-digit binary number as
; output. For instance:
; 1 + 1 = 10
; The first function, c, computes the left-most (most significant) bit of
; the sum. This is the carry bit. The second function, s, computes the
; right-most (least significant) bit. See tests below.
; First write truth tables for c and s and recognize which two-argument
; Boolean connectives they correspond to. Then write this in ACL2.
#|
a   b   c(a,b)   s(a,b)
-----------------------
T   T     T        F
T   F     F        T
F   T     F        T
F   F     F        F
c corresponds to /\
s corresponds to <>
|#
(defunc c (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (c a b))
  (if (and (and a b) t)
    t
    (if (xor a b)
      nil
      nil)))
(check= (c t t) t)
(check= (c nil t) nil)
(check= (c nil nil) nil)
(defunc s (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (c a b))
  (if (and (and a b) t)
    nil
    (if (xor a b)
      t
      nil)))
(check= (s t t) nil)
(check= (s nil t) t)
(check= (s t nil) t)
(check= (s nil nil) nil)
; Define two functions that implement a full-adder. Both functions take
; three Booleans a, b, c as input, interpreting nil as 0 and t as 1. The
; idea of these functions is that they add digits a and b, and a possible
; carry from adding preceding digits in a sequence, producing a new sum
; digit (s-fa) and a possible new carry (c-fa). That is, function c-fa
; returns the left-most bit of the sum of a, b, c, the second function,
; s-fa, returns the right-most bit of the sum. See tests.
; Again, first write truth tables for both functions.
(defunc c-full (a b c)
  :input-contract (and (booleanp a) ( and (booleanp b) (booleanp c)))
  :output-contract (booleanp (c-full a b c))
  (or (and a b) (and b c) (and c a)))
(check= (c-full t nil t)   t)
(check= (c-full t nil nil) nil)
(check= (c-full nil nil t) nil)
(defunc s-full (a b c)
  :input-contract (and (booleanp a) ( and (booleanp b) (booleanp c)))
  :output-contract (booleanp (s-full a b c))
  (or (and a b c) 
      (and a (not b) (not c)) 
      (and (not a) (not b) c)
      (and (not a) (not c) b)))
      
      
(check= (s-full t nil t)   nil)
(check= (s-full t nil nil) t)
; You are allowed to write the remaining functions in program mode. This
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
; Define a ripple-carry adder, a circuit that adds two same-length binary
; numbers. For now, our ripple-carry takes and produces numbers that are
; written left-side right, i.e. least significant bit first, most
; significant bit last. For example, 110 will represent the decimal number
; 1*2^0 + 1 * 2^1 + 0 * 2^2 = 3 (normally written as 011 in binary).
; This is cumbersome and unnatural -- we will fix this later.
; We will define ripple-carry in two steps. First define a function
; ripple-carry-helper that takes two lists al and bl of Booleans as input
; and a third argument, c, a carry (a Boolean). As always, we interpret nil
; as 0 and t as 1. The input and output contracts for this function are
; provided to you below, study them. The function steps through the lists
; al and bl and applies the full-adder to the current elements of al and
; bl, and the carry from the addition of the elements to the left. For
; adding the first two elements, the input c serves as the carry.
; Example: suppose the input is al = (a0 a1 a2) and bl = (b0 b1 b2) and
; c. The output of (ripple-carry-helper al bl c) is a list of the form (l0
; l1 l2 l3 (length 4) where
; - l0 = (s-fa a0 b0 c)
; - l1 = (s-fa a1 b1 c1) , where c1 = (c-fa a0 b0 c)
; - l2 = (s-fa a2 b2 c2) , where c2 = (c-fa a1 b1 c1)
; - l3 = c3              , where c3 = (c-fa a2 b2 c2)
; Hints:
; - think about the output in the base case: when al is empty (and hence so
; is bl, by input contract)
; - use let* to extract the current elements a of al and b of bl, and to
; compute the value of calling the full-adder functions on a, b, c
; - the body of ripple-carry-helper is a simple cons that puts the least
; significant bit of the current digit sum onto the front of the list
; obtained by recursively calling ripple-carry-helper with appropriate
; arguments.
;*****
(defunc ripple-carry-helper (al bl c)
  :input-contract (and (booleanlistp al) (booleanlistp bl) (equal (len al) (len bl)) (booleanp c))
  :output-contract (and (booleanlistp (ripple-carry-helper al bl c)) (equal (len (ripple-carry-helper al bl c)) (+ (len al) 1)))
  (if (endp al) 
    (list c)
    (cons (s-full (first al) (first bl)  c)
          (ripple-carry-helper (rest al) (rest bl) (c-full (first al) (first bl) c)))))
; The second step is the actual ripple-carry function. Given
; ripple-carry-helper, this is very easy: just call ripple-carry-helper
; with appropriate inputs. Think about proper input and output contracts
; (very similar to the ripple-carry-helper function);
;*****
(defunc ripple-carry (al bl)
  :input-contract (and (booleanlistp al) (booleanlistp bl) (equal (len al) (len bl)))
  :output-contract (and (booleanlistp (ripple-carry al bl)) (equal (len (ripple-carry al bl)) (+ (len al) 1)))
  (ripple-carry-helper al bl nil))
(check= (ripple-carry '(t nil t) '(t t nil)) '(nil nil nil t))
(check= (ripple-carry '(nil nil nil) '(nil nil nil)) '(nil nil nil nil))
(check= (ripple-carry '(t nil nil) '(t t t)) '(nil nil nil t))
(check= (ripple-carry '(t nil t t t nil t) '(t t nil nil t nil nil)) '(nil nil nil nil t t t nil))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Pretty-printing ripple-carry adder
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; The ripple-carry adder we defined requires us the read numbers from right
; to left. Also, it uses "t" and "nil" instead of "1" and "0". It is easy
; to fix these issues and to make both input and output human-readable
; (provided the human knows binary number representations).
; Write two conversion functions, one from Boolean to digit and one the
; other way. Think about the contracts, and write a few more tests.
(defunc boolean2digit (x)
  :input-contract (booleanp x)
  :output-contract (digitp (boolean2digit x))
  (if (equal x t)
    1
    0))
(check= (boolean2digit t) 1)
(check= (boolean2digit nil) 0)
(defunc digit2boolean (x)
  :input-contract (digitp x)
  :output-contract (booleanp (digit2boolean x))
  (if (equal x 1)
    t
    nil))
(check= (digit2boolean 0) nil)
(check= (digit2boolean 1) t)
; Generalize the above two conversion functions so that they take lists as
; input. Contracts are provided for you.
(defunc booleanlist2digitlist (l)
  :input-contract (booleanlistp l)
  :output-contract (and (digitlistp (booleanlist2digitlist l)) (equal (len (booleanlist2digitlist l)) (len l)))
  (if (endp l)
        l
    (cons (boolean2digit (first l)) 
                         (booleanlist2digitlist (rest l)))))
(check= (booleanlist2digitlist '(t nil t)) '(1 0 1))
(check= (booleanlist2digitlist '(t)) '(1))
(check= (booleanlist2digitlist '(nil)) '(0))
(check= (booleanlist2digitlist '(t t t)) '(1 1 1))
(check= (booleanlist2digitlist '(nil nil nil)) '(0 0 0))
(check= (booleanlist2digitlist '()) '())
(defunc digitlist2booleanlist (l)
  :input-contract (digitlistp l)
  :output-contract (and (booleanlistp (digitlist2booleanlist l)) (equal (len (digitlist2booleanlist l)) (len l)))
  (if (endp l)
    l
    (cons (digit2boolean (first l)) 
                         (digitlist2booleanlist (rest l)))))
(check= (digitlist2booleanlist '(1 0 1)) '(t nil t))
(check= (digitlist2booleanlist '(1)) '(t))
(check= (digitlist2booleanlist '(0)) '(nil))
(check= (digitlist2booleanlist '(1 1 1)) '(t t t))
(check= (digitlist2booleanlist '(0 0 0)) '(nil nil nil))
; We are finally ready to define our pretty-printing ripple-carry
; adder. This function takes two digit lists al and bl as input, reverses
; them, and converts the result to Boolean lists. Now we can pass it to the
; ripple-carry adder we defined above. The result needs to be
; post-processed, however -- how?
; Contracts are provided. Make sure the test cases pass, and add 10 tests
; on your own. You may reuse the tests from the ripple-carry function, but
; you need to rewrite them.
(defunc pretty-adder (al bl)
  :input-contract (and (digitlistp al) (digitlistp bl) (equal (len al) (len bl)))
  :output-contract (and (digitlistp (pretty-adder al bl)) (equal (len (pretty-adder al bl)) (+ (len al) 1)))
  
  (rev (booleanlist2digitlist 
   (ripple-carry (digitlist2booleanlist (rev al)) 
                 (digitlist2booleanlist (rev bl))))))
(check= (pretty-adder '(1 0 1) '(0 1 1)) '(1 0 0 0)) ; why? 101 + 011 = 1000 (5 + 3 = 8)
(check= (pretty-adder '(1 0 1) '(1 1 0)) '(1 0 1 1))
(check= (pretty-adder '(0 0 0) '(0 0 0)) '(0 0 0 0))
(check= (pretty-adder '(1 0 0) '(1 1 1)) '(1 0 1 1))
(check= (pretty-adder '(1 0 1 1 1 0 1) '(0 0 1 0 0 1 1)) '(0 1 1 1 0 0 0 0))#|ACL2s-ToDo-Line|#

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Feedback
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
;; Please fill out the following feedback form, not as a team, but each team
;; member individually:
;; https://docs.google.com/spreadsheet/viewform?formkey=dHprbHVTTmVSOUZQOTNCTUdidlB2dVE6MA
;; Confirm here whom of the team members filled out the form:
;; Anh Tran
;; Tyler Kaminsky
Filling out the feedback form is worth 10 homework points.
The form is anonymous, to encourage you to write what you think about this
class. (If you want to stay anonymous, do not reveal your name on the
feedback form.) On the other hand, we must rely on your honesty in stating
whether you filled out the form or not, and in not filling out the form
several times (why would you do that??). We do not want to find
discrepancies in the number of entries on the form, and the number of
people claiming to have filled out the form.
|#