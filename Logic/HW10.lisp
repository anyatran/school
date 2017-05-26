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
CS 2800 Homework 10 - Spring 2014
Student names: 
Anh Tran
Tyler Kaminsky
Technical instructions:
- open this file in ACL2s as hw10.lisp
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
; A. Induction Proofs for Tail-Recursive functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
In  this  problem you  will  prove that  a  recursive  function and  a
tail-recursive  version  of  this  function  always  return  the  same
result. The steps  this problem guides you through  are also described
in the  Lecture Notes, section 5.5  "Reasoning About Accumulator-Based
Functions", in case you wish to consult those.
Consider the following definitions:
(defunc delete (x l)
  :input-contract (listp l)
  :output-contract (listp (delete x l))
  (cond ((endp l)            nil)
        ((equal x (first l)) (delete x (rest l)))
        (t                   (cons (first l) (delete x (rest l))))))
(defunc delete-t (x l acc)
  :input-contract (and (listp l) (listp acc))
  :output-contract (listp (delete-t x l acc))
  (cond ((endp l)            acc)
        ((equal x (first l)) (delete-t x (rest l) acc))
        (t                   (delete-t x (rest l) (app acc (list (first l)))))))
(defunc delete* (x l)
  :input-contract (listp l)
  :output-contract (listp (delete* x l))
  (delete-t x l nil))
The goal is to prove the following conjecture:
phi : (listp l) => (delete* x l) = (delete x l)
That is, we want to show that the two functions compute the same result on
all inputs. We will prove this in several steps.
1. Formulate a lemma that relates delete-t to delete, by filling in the
... below:
phi_1 : (listp l) /\ (listp acc) => (delete-t x l acc) = (append acc (delete x l))
2. Prove phi_1. Use the induction scheme delete-t gives rise to. 
State and prove any lemmas you need. 
0. ~(listp l) /\ ~(listp acc) /\ (listp l) /\ (listp acc) => (delete-t x l acc) = (append acc (delete x l))
F => (delete-t x l acc) = (append acc (delete x l))
F => Anything = T
   
1. (listp l) /\ (listp acc) /\ (endp l) => (delete-t x l acc) = (append acc (delete x l))
Context:
c1. (listp l)
c2. (listp acc)
c3. (endp l)
LHS:
(delete-t x l acc)
{body of delete-t, c3}
acc
RHS:
(append acc (delete x l))
{body of delete, c3}
(append acc nil)
{body of append, c3}
acc
acc = acc 
T
2. (listp l) /\ (listp acc) /\ ~(endp l) /\ (x = (first l)) /\ phi | (l (rest l))
    => (delete-t x l acc) = (append acc (delete x l))
Context:
c1. (listp l)
c2. (listp acc)
c3. ~(endp l)
c4. (x = (first l))
c5. (listp (rest l))
    => (delete-t x l acc) = (append acc (delete x l))
c6. (delete-t x (rest l) acc) = (append acc (delete x (rest l))
LHS:
(delete x l acc)
{expand delete, c1, c3, c4}
(delete-t x (rest l) acc)
{c6}
(append acc (delete x (rest l)))
RHS:
(append acc (delete x l))
{expand delete, c3, c4}
(append acc (delete x (rest l)))
(append acc (delete x (rest l))) = (append acc (delete x (rest l)))
T
    
    
3. (listp l) /\ (listp acc) /\ ~(endp l) /\ ~(x = (first l)) /\ phi | (l (rest l))
    => (delete-t x l acc) = (append acc (delete x l))
Context:    
c1. (listp l)
c2. (listp acc)
c3. ~(endp l)
c4. ~(x = (first l))
c5. (listp (rest x)) /\ (listp (app acc (list (first l))) -> 
    => (delete-t x (rest l) (app acc (list (first l)))
    = (append acc (delete x l))
c6. (delete-t x (rest l) (app acc (list (first l)))
    = (append acc (delete x l))
    
LHS
(delete-t x l acc)
{expand delete-t}
(delete-t x (rest l) (app acc (list (first l)))))))
{c6}
(append acc (delete x l))
(append acc (delete x l)) = (append acc (delete x l))
T
3. Using only equational reasoning (no induction!), prove the main
   conjecture phi from above. Using phi_1 (which you just proved) this
   should be very simple.
phi:
(listp l) => (delete* x l) = (delete x l)
Context:
C1. (listp l)
C2. (delete-t x l acc) = (append acc (delete x l))
(delete-t x l acc)
{body of delete*}
(delete* x l nil)
{c6}
(append nil (delete x l))
{body of append}
(delete x l)
(delete x l) = (delete x l)
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; B. Recursion unwound and sped up
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; First reduce the amount of testing done by ACL2 while it admits a function:
(acl2::acl2s-defaults :set acl2::subgoal-timeout 1)
;; Here is the fibonacci function: 
(defunc fibonacci (n)
  :input-contract (natp n)
  :output-contract (natp (fibonacci n))
  (cond ((equal n 0) 0)
    ((equal n 1) 1)
    (t (+ (fibonacci (- n 1))
          (fibonacci (- n 2))))))
;; The Lucas  series 2, 1,  3, 4, 7,  ... is similar to  the Fibonacci
;; series except  that it start with  2, 1 instead  of 1, 1. Here  is the
;; definition of the function:
(defunc lucas (n)
  :input-contract (natp n)
  :output-contract (natp (lucas n))
  (cond ((equal n 0) 2)
    ((equal n 1) 1)
    (t (+ (lucas (- n 1))
          (lucas (- n 2))))))
#|
This is  an elegant and very easy  to read definition. But  it is also
very slow.  To see  why, we  trace the recursive  calls to  lucas when
processing input. This is done as follows:
|#
(acl2::trace! lucas)
(acl2::set-guard-checking :none)
#|
(Hint: you can use (acl2::untrace$ ...) to stop tracing a function.)
Now try lucas using some _small_ inputs. Start with n=1, n=2, to get a
feeling for the output trace produces. A line of the form
 > (LUCAS 1)
indicates that lucas was called recursively with argument 1, whereas
 < (LUCAS 1)
indicates that a recursive call to lucas with argument 1 was completed.
1. In the evaluation of (lucas  5), how many times is lucas called on argument 1 ? 15
   In the evaluation of (lucas 10), how many times is lucas called on argument 1 ? 55
Hint: you can use the Eclipse editor to count occurrences of certain text
strings, or you can copy the output of trace into your favorite alternative
editor.
|#
(acl2::untrace$ lucas)
(acl2::trace! fibonacci)
(acl2::set-guard-checking :none)
#|
Compare the above numbers with the values (fibonacci 5) and (fibonacci 10). What do you
find? (fibonacci 5)  = 15
      (fibonacci 10) = 193
2. You saw how long the trace output of (lucas 10) is -- for a fairly small
input of 10. Let's see whether we can make lucas more efficient. Our lucas-fast
function will be hard for ACL2 to prove terminating, so you are allowed to
write this function in program mode:
|#
#|
The idea is as follows. First write a function Lucas-help that, for input n,
computes the _list_ of Lucas numbers 2,1,3,4,7,11,18,... in _descending_
order from (lucas n) down to (lucas 0) = 2. See tests below, and also note the
output contract, which is provided for you. Provide 3 more tests.
To minimize the number of recursive calls required to evaluate (lucas-help n),
you MUST use (let ...)  whenever you need the result of a recursive call
several times. Your solution will be considered incorrect if your code contains
several calls to lucas-help with the same arguments.
|#
(acl2::acl2s-defaults :set acl2::testing-enabled nil)
(defdata natlist (listof nat))
(defdata set (listof all))
:program
(defunc lucas-help (n)
  :input-contract (natp n)
  :output-contract (and (natlistp (lucas-help n)) (equal (len (lucas-help n)) (+ n 1)))
  (cond ((equal n 0) '(2))
        ((equal n 1) '(1 2))
  (t (let ((l (lucas-help (- n 1))))
           (cons (+ (first l) (second l)) l)))))
     
(check= (lucas-help 0) '(2))
(check= (lucas-help 1) '(1 2))
(check= (lucas-help 3) '(4 3 1 2))
;; Now write a non-recursive function lucas-fast, with contracts as for the
;; (slow) lucas function, which calls lucas-help to compute (lucas n).
(defunc lucas-fast (n)
  :input-contract (natp n)
  :output-contract (natp (lucas n))
  (first (lucas-help n)))
;; Now let's see whether lucas-fast deserves that name. Turn on tracing for
;; the helper function (lucas-fast itself is not recursive):
(acl2::trace! lucas-help)
#|
In the evaluation of (lucas-fast  5), how many times is lucas-help called on argument 1 ? 1
In the evaluation of (lucas-fast 10), how many times is lucas-help called on argument 1 ? 1
Compare your results to those obtained with (lucas n).
You can also try lucas and lucas-fast on input 100. Hint: try lucas-fast first!
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; C. Vagueness of Specifications
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
In this exercise you will be using the acl2::test? mechanism. This function
takes a conjecture as input and runs a number of tests to see whether it
can find a counterexample. For example, let us conjecture that, for
positive natural numbers x and y, (x + y) and (x * y) are different. You
can test this conjecture like this:
(acl2::test? (implies (and (posp x) (posp y)) (not (equal (+ x y) (* x y)))))
Test? may report that it succeeded: no counterexamples were found. This
does not mean you have proved the conjecture! It just means test? hasn't
come across a test that falsifies it. In fact, the above conjecture is
wrong. You can try to find more counterexamples by increasing the number of
tests run, like this:
(acl2::acl2s-defaults :set acl2::num-trials 200)
Now try test? again!
In general, make sure to perform conjecture contract checking before
passing your conjecture to acl2::test? . For example,
(acl2::test? (equal (append x y) (append y x)))
will waste time running many useless tests in which x and y are not
lists, and report contract violations. In contrast,
(acl2::test? (implies (and (listp x) (listp y)) (equal (append x y) (append y x))))
will return a counterexample using lists immediately.
Suppose you are asked to write a function that computes the union of two
sets, where a set is represented using a list. The union of two sets
consists of all elements that are contained in at least one of the input
sets. If an element is contained in both input sets, it is also contained
in the union. Duplicates are allowed and ignored.
Consider the following definitions (ignore the :set directive and the
defthm).
|#
(acl2::acl2s-defaults :set acl2::defunc-strict 0)
(defthm setp-listp
  (equal (setp x) (listp x)))
(defunc union1 (s1 s2)
  :input-contract (and (setp s1) (setp s2))
  :output-contract (setp (union1 s1 s2))
  (append s1 s2))
(defunc union2 (s1 s2)
  :input-contract (and (setp s1) (setp s2))
  :output-contract (setp (union2 s1 s2))
  (append (rev s1) (rev s2)))
(defunc union3 (s1 s2)
  :input-contract (and (setp s1) (setp s2))
  :output-contract (setp (union3 s1 s2))
  (if (endp s2)
      s1
    (cons (first s2) (union3 s1 (rest s2)))))
(defunc union4 (s1 s2)
  :input-contract (and (setp s1) (setp s2))
  :output-contract (setp (union4 s1 s2))
  (if (or (endp s1) (endp s2))
      nil
    (cons (first s1) (cons (first s2) (union4 (rest s1) (rest s2))))))
(acl2::acl2s-defaults :set acl2::testing-enabled t)

(acl2::trace! lucas)

(acl2::untrace$ lucas)

(acl2::trace! lucas)
#|
Note that all definitions union[1-4] have the same signature and input and
output contracts.
(a) Prove that these four solutions are pairwise different. That is, for
each pair of functions union[1-4] , find one input on which the two
functions behave differently.
Use test? as an assistant. Write down the test? line that you used,
followed by the simplest counterexample you (or ACL2) found:
union1/union2:
test: (acl2::test?(implies (and (listp s1) (listp s2)) (equal (union1 s1 s2) (union2 s1 s2))))
pair: (S1 (NIL NIL NIL)) and (S2 (T NIL))
union1/union3:
test: (acl2::test?(implies (and (listp s1) (listp s2)) (equal (union1 s1 s2) (union3 s1 s2))))
pair: (S1 (T T)) and (S2 (T T NIL))
union1/union4:
test: (acl2::test?(implies (and (listp s1) (listp s2)) (equal (union1 s1 s2) (union4 s1 s2))))
pair: (S1 (NIL)) and (S2 (NIL NIL))
union2/union3:
test: (acl2::test?(implies (and (listp s1) (listp s2)) (equal (union2 s1 s2) (union3 s1 s2))))
pair: (S1 (NIL)) and (S2 (NIL NIL))
union2/union4:
test: (acl2::test?(implies (and (listp s1) (listp s2)) (equal (union2 s1 s2) (union4 s1 s2))))
pair: (S1 (T)) and (S2 (T NIL NIL))
union3/union4:
test: (acl2::test?(implies (and (listp s1) (listp s2)) (equal (union3 s1 s2) (union4 s1 s2))))
pair: (S1 (0 "a" NIL)) and (S2 (T 0 NIL))
(b) Function union1 satisfies the specification "union of two sets" given
above (you don't need to prove that). What about the others? For union2,
union3, union4, if you suspect it does, say so. If you suspect it doesn't,
give a counterexample (how you find it is up to you).
They all create a union of two sets.
(c) Compare each of the functions identified under (b) to _fail_ the union
specification to union1, which is known to satisfy the specification. To do
this, use test? to test the following two conjectures:
(in x (union1 s1 s2)) => (in x (union? s1 s2))
(in x (union? s1 s2)) => (in x (union1 s1 s2))
where union? is one of the functions identified in (b) to fail the
specification. Remember to perform conjecture contract checking, and add
proper hypotheses. We are not interested in contract violations.
If ACL2s can't find a counterexample, make it try harder by adjusting the
number of trials.
(acl2::test?(implies (and (listp s1) (listp s2)) (implies (in x (union1 s1 s2)) (in x (union? s1 s2)))))
(acl2::test?(implies (and (listp s1) (listp s2)) (implies (in x (union? s1 s2)) (in x (union1 s1 s2)))))

union1 + union2:
No counterexamples were found

union1 + union3:
No counterexamples were found

union1 + union4:
Counterexamples:
-- (S1 NIL), (S2 (NIL 0 NIL)) and (X NIL)
 -- (S1 (0 0)), (S2 (-2/3 -1/2 "a" T)) and (X "a")
 -- (S1 (NIL)), (S2 (0 T T)) and (X T)
 
union2 + union1:
No counterexamples were found

union3 + union1:
No counterexamples were found

union4 + union1:
No counterexamples were found
 
(d) You have identified in (b) a number of functions to _satisfy_ the union
specification, but according to (a) they are still all pairwise different.
Formulate a conjecture about the relationship of the result returned by
union1 (which is "correct") and any of the other functions found to satisfy
the spec. Of course, one such conjecture is that they all implement
"union". This is not a good conjecture, however, as we do not have a formal
specification of what "union" means, only an English specification. Come up
with a better -- more formal -- conjecture that makes a statement about the
list properties of these functions. You can write down the conjecture in
English, but it should be mathematically verifiable (which you do not have
to do, however).
(implies (implies (and (listp s1) (listp s2)) (equal (len (union1 s1 s2)) (len (union? s1 s2)))))
|#