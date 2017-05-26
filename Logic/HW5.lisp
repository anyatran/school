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
CS 2800 Homework 5 - Spring 2014
Student names: Anh Tran, Tyler Kaminsky
Technical instructions:
- open this file in ACL2s as hw05.lisp
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
; Completeness of sets of Boolean connectives
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
A set C of Boolean connectives is called a "complete base" (or "complete",
for short) if every Boolean function (no matter what arity) can be
described by a Boolean formula involving only connectives from C,
and the constants T and F.
For each of the following sets of connectives, decide whether it is
complete.
If yes, explain how you can go about proving that claim, and do it.
If no, give an example of a Boolean function that you think *cannot* be
represented using solely your connectives. For *extra credit*, provide a
proof of the "cannot" claim. We have seen an example of such a proof in
class.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
{/\,=>}
it's a complete boolean base because you can represent ~:
~a = (a => F)
Since this set already has /\ in it and can also represent ~,
the set is complete because {/\, ~} is a complete boolean base.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
{\/,<>}
~a = a <> T
Since this set already has \/ in it and can also represent ~,
the set is complete because {\/, ~} is a complete boolean base.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
{%}
where % is a new binary connective called *projection* and defined as
a % b = a.
no, its impossible to define ~, /\ or \/ using %
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Normal Forms
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
1. Convert the following Boolean formula into Conjunctive Normal Form (CNF)
and into Disjunctive Normal Form (DNF).
(x1 = y1) /\ (x2 = y2) /\ (x3 = y3) -- 
CNF: (~x1 \/ y1) /\ (x1 \/ ~y1)  /\  (~x2 \/ y2) /\ (x2 \/ ~y2) /\(~x3 \/ y3) /\ (x3 \/ ~y3)  
DNF: ~((~x1 \/ y1) /\ (x1 \/ ~y1) /\ (~x2 \/ y2) /\ (x2 \/ ~y2) /\ (~x3 \/ y3) /\ (x3 \/ ~y3))  = 
       = (x1 /\ ~y1) \/ (~x1 /\ y1) \/ (x2 /\ ~y2) \/ (~x2 /\ y2) \/  (x3 /\ ~y3) \/ (~x3 /\ y3)
What can you say about the sizes of the two normal forms, compared to the
given formula? Make a statement for a "parameterized" formula, that is one
where the number of conjuncts in the input is a parameter n, as follows:
(x1 = y1) /\ (x2 = y2) /\ ... /\ (xn = yn)
They are longer
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
2. A formula is said to be in Negation Normal Form (NNF) if it contains
only the connectives /\ , \/ , ~ , and the unary connective ~ appears only
in front of propositional variables.
Examples:
 (x /\ (y \/ ~z)) \/ (~x /\ y) is in negation normal form, while
~(x /\ (y \/ ~z)) \/ (~x /\ y) is not.
(a) Can every formula be converted into an equivalent NNF?
    If so, how? If not, given an example of a formula that has no
    equivalent NNF representation.
every formula can be converted into an equivalent NNF.
If ~ is in front of parenthesis, we can get rid of it by using de-morgan's law
(b) Given a formula g over only the connectives /\ , \/ , ~ , such as both
    formulas shown as examples above. Suppose g has an equivalent NNF.
    Compare the sizes of g and the smallest possible NNF equivalent to g.
    What can we say in general?
the NNF is either the same size or longer a little bit
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Word Problems
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
The problems below consist of some assumptions followed by a conclusion.
Formalize and analyze the statements using propositional logic, as follows:
(i) assign propositional variables to the atomic facts in the statements,
(ii) formalize the entire statement into a propositional logic formula, and
then (iii) determine whether the conclusion follows from the assumptions.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
If Arthur pulled a sword from stone, then Arthur is King. Arthur is
King. Therefore, Arthur pulled a sword from stone.
a - Arthur pulled a sword from stone
k - Arthur is King
((a => k) /\ k) => a
((~a \/ k) /\ k) => a
k => a
it doesn't follow the assumption because uf k = T, and a = F, 
then (T => F) = F
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Tom takes the advanced course in Logic only if CS2800 is interesting. Tom
gets a good grade in CS2800 and Tom takes the advanced course in Logic.
Therefore, CS 2800 is interesting.
t - Tom takes the advanced course in Logic
i - CS2800 is interesting
g - Tom gets a good grade in CS2800
((t => i) /\ g /\ t) => i
~((~t \/ i) /\ g /\ t) \/ i
((t /\ ~i) \/ ~g \/ ~t) \/ i
(((t \/ ~t) /\ (~i \/ ~t)) \/ ~g) \/ i
((T /\ (~i \/ ~t) \/ ~g)) \/ i
~i \/ ~t \/ ~g \/ i
T
it follows the assumption
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Being rich is not the same thing as being happy. However, if I'm rich, I am
also happy. Therefore, I am not rich but happy.
r - Being rich
h - being happy
HOWEVER
((r <> h) /\ (r => h)) => ~r /\ h
it follows the assumption 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Computers are smart, or humans are dumb. If humans are not dumb, they beat
the Martians in the solar race. The humans did not beat the Martians in the
solar race. Therefore, computers are smart.
c - Computers are smart
h - humans are dumb
b - beat the Martians in the solar race
((c \/ h) /\ (~h => b) /\ ~b) => c
((c \/ h) /\ (h \/ b) /\ ~b) => c
~((h \/ (c /\ b) /\ ~b) \/ c
(~h /\ (~c \/ ~b) \/ b) \/ c
(~h /\ ~c \/ T) \/ c
~h \/ c
It doesn't follow the assumption because if h = T and c = F then the output is F
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
If Natasha is a spy, then exactly one of following holds: Natasha works for
USA or Natasha works for USSR. Natasha is a spy. Therefore, Natasha works
for USSR and Natasha works for USA.
s - Natasha is a spy
u - Natasha works for USA
r - Natasha works for USSR
((s => (u \/ r)) /\ s) => u /\ r
((~s \/ u \/ r) /\ s) => u /\ r
~((~s \/ u \/ r) /\ s) \/ u /\ r
((s /\ ~u /\ ~r) \/ ~s) \/ u /\ r
((s \/ ~s) /\ (~u \/ ~s) /\ (~r \/ ~s) \/ u /\ r
(~u \/ ~s) /\ (~r \/ ~s) \/ u /\ r
It does follow the assumption because in any cases the output will be T
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem modeling using SAT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
You are given three labeled boxes: two are empty, one contains money; but
you don't know which one. The labels on boxes 1 and 2 read, "This box is
empty"; the label on box 3 reads "The money is in box 2". You also know
that exactly one label tells the truth. The question is: where is the
money?
Formalize the puzzle as a satisfiability problem, and solve it, as follows.
(a) Define propositional variables to represent relevant atomic facts.
f = 1st box
s = 2nd box
t = 3rd box
(b) Using these variables, formulate the knowledge given in the problem as
propositional formulas; be careful with the statement that exactly one
label tells the truth.
~f /\ s /\ ~t
In order to make this statement T, f = F, s = T, and t = F.
Following this assumption, the 1st box has money
(c) Show that the formula you have found in (b) is satisfiable. State where
the money is, and which label tells the truth, according to the satisfying
assignment. You may use simplifications, or truth tables.
f   s   t   ~f  ~t   ~f/\s/\~t
====================================
T   T   T   F   F         F
T   T   F   F   T         F
T   F   T   F   F         F
T   F   F   F   T         F
F   T   T   T   F         F
F   T   F   T   T         T
F   F   T   T   F         F
F   F   F   T   T         F
SATISFIABLE
(d) Suppose we now want to find out whether the solution you found in (c)
is unique. How do we do that? The idea is to check whether the formula is
satisfiable even under the additional constraint that the solution must
differ from the one already found.
For example, suppose your formula f is over variables x,y,z, and you found
that x=y=T, z=F is a satisfying solution. The next step is then to check
whether the formula
f' = f /\ ~(x /\ y /\ ~z)
is satisfiable. The subformula ~(x /\ y /\ ~z) is called a *blocking
clause* : it blocks the solution x=y=T,z=F from being reported again.
Build such a formula f' for your model of the 3-boxes problem, and use any
means to check whether f' is satisfiable. You do not need to find a second
satisfying solution, if one exists. Describe, in one sentence, how these
ideas can be used to find ALL satisfying solutions to any given Boolean
formula.
f' = (~f /\ s /\ ~t) /\ ~(~f /\ s /\ ~t) 
f' = F
NOT SATISFIABLE
if after blocking the satisfiable solution f' still satisfiable, that means
it has another satisfiable solution as well
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Encryption using XOR
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
The Boolean xor function can be used to implement a simple
encryption/decryption scheme. In this homework you are asked to implement
that scheme and put an ASCII-like encoder/decoder on top of that, so that
you can encrypt actual English sentences, not just sequences of bits.
|#
;; Data definitions for lists of booleans, symbols, letters:
(defdata booleanlist (listof boolean))
(defdata symbollist  (listof symbol))
(defdata letterlist  (enum '(a b c d e f g h i j k l m n o p q r s t u v w x y z)))
#|
The definition of the letterlist enumeration type automatically gives rise
to a list representation of the elements of the type, via the constant
*letterlist-values*
For example:
|#
(check= *letterlist-values* '(a b c d e f g h i j k l m n o p q r s t u v w x y z))
; Our encryption scheme is based on bitwise xor-ing the input message
; (after translating it into a bitstring) against a key that only the
; intended communicators know. We need to define such a key.
; Replace the boolean list below with your own secret key if you like. Make
; sure it is at least a few dozen booleans long: the shorter, the weaker
; the encryption mechanism, since keys shorter than the message will be
; filled up with nil (which is easy to guess and exploit by an attacker).
(defdata key (enum
              '(  t nil t t nil t nil nil nil t t nil nil
                    nil t nil t t nil t nil t nil t t nil t nil
                    t t nil t nil nil t nil t t nil t t t nil nil
                    nil t t nil t nil t nil t t nil nil t nil t)))
; Note that key is an explicit enumeration of boolean values. You can
; get access to those values in the form of a list using the constant
; expression
;  *key-values*
; for instance:
(check= (listp *key-values*) T)
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
;;; Auxiliary functions ;;;
;; Define the following simple functions in ACL2. To avoid confusion,
;; contracts are provided.
; Prefix of l of length n. If l doesn't have n elements, return l
(defunc prefix (l n)
  :input-contract (and (listp l) (natp n))
  :output-contract (listp (prefix l n))
    (if (>= n (len l))
      l
      (if (equal n 0)
        '()
        (cons (first l) (prefix (rest l) (- n 1)))
        )))
  
(check= (prefix '(1 2 3) 2) '(1 2))
(check= (prefix '(1 2 3) 4) '(1 2 3))
(check= (prefix '() 4) '())
(check= (prefix '(1 2 3 4 5 6) 444) '(1 2 3 4 5 6))
(check= (prefix '(1) 4) '(1))
;;;;;;;;
; Define
; elem: All x List -> Boolean
; (elem a X) returns t iff a is an element of list X.
(defunc elem (a X)
  :input-contract (and t (listp X))
  :output-contract (booleanp (elem a X))
  (if (endp X)
    nil
    (if (equal a (first X))
      t
      (elem a (rest X)))))
(check= (elem 3 '( 1 2 3 4)) t)
(check= (elem 2 '(1 2 3 4)) t)
(check= (elem 2 '(1 3 3 4)) nil)
(check= (elem 2 '()) nil)
;;;;;
; Define
; pos-help: All x List x nat -> Boolean
; returns the position of an element in a given list.
(defunc pos-help ( x l acc)
  :input-contract (and (listp l) (natp acc))
  :output-contract (natp (pos-help x l acc))
  (if (endp l)
    acc
    (if (not (elem x l))
      acc
      (if (equal x (first l))
        acc
        (pos-help x (rest l) (+ acc 1))))))
(check= (pos-help 't '(a b t) 0) 2)
(check= (pos-help 't '(a b c) 0) 0)
(check= (pos-help 't '() 0) 0)
; position of x in l (counting from 0) if x occurs in l, (len l) otherwise
(defunc pos (x l)
  :input-contract (listp l)
  :output-contract (natp (pos x l))
  (if (elem x l)
    (pos-help x l 0)
    (len l)))
    
(check= (pos 'x '( 1 2 3 4)) 4)
(check= (pos 't '(a b t)) 2)
(check= (pos 't '()) 0)
(check= (pos 'c *letterlist-values*) 2)
(check= (pos '@ *letterlist-values*) (len *letterlist-values*))
;To identify whether a given input is a list of naturals
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
(check= (natlistp '( 1 2 3 4 5)) t)
(check= (natlistp '(t sdf sdf saew sgtrb)) nil)
(check= (natlistp '()) t)
;multiplies every number in a given list
(defunc mult-list (l)
  :input-contract (natlistp l)
  :output-contract (natp (mult-list l))
  (if (endp l)
    1
    (* (first l) (mult-list (rest l)))))
(check= (mult-list '(2 3 4)) 24)
(check= (mult-list '()) 1)
;builds a list of n that is e elements long
(defunc power-help ( n e)
  :input-contract (and (natp n) (natp e))
  :output-contract (listp (power-help n e))
  (if (equal e 0)
    '()
  (cons n (power-help n (- e 1)))))
(check= (power-help 2 3) '(2 2 2))
(check= (power-help 2 0) '())
; n raised to power e. We define 0^0 = 1
(defunc power (n e)
  :input-contract (and (natp n) (natp e))
  :output-contract (natp (power n e))
  (mult-list (power-help n e)))
(check= (power 3 1) 3)
(check= (power 3 3) 27)
(check= (power 0 1) 0)
(check= (power 1 0) 1)
(check= (power 3 0) 1)
;; We will implement our encoding scheme in three steps: (i) functions to
;; encrypt "sentences" into boolean lists, (ii) functions to decrypt boolean
;; lists into "sentences", and (iii) the actual encryption scheme, using
;; xor.
;;; Conversion from sentences to boolean lists ;;;
; Function that converts symbols to a simplified ASCII code. Your function
; takes an arbitrary symbol as input and returns 0..25 if the symbol is a
; letter, 26 otherwise. See tests below.
; Hint: this is a one-liner. Use the pos function defined above
; and *letterlist-values*.
(defunc symbol2dec (s)
  :input-contract (symbolp s)
  :output-contract (natp (symbol2dec s))
  (pos s *letterlist-values*))
(check= (symbol2dec 'z) 25)
(check= (symbol2dec '@) 26)
(check= (symbol2dec 'a) 0)
; is x evenly divisible by y
(defunc divides (x y)
  :input-contract (and (natp x) (natp y))
  :output-contract (booleanp (divides x y))
  (if (equal y 0)
    nil
    (if (natp (* x (unary-/ y))) t nil)))
(check= (divides 26 2) t)
(check= (divides 13 2) nil)
(check= (divides 2 0) nil)
;Returns the remainder of x divided by y
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
;floor rounding
(defunc floor-by-two (x)
  :input-contract (natp x)
  :output-contract (natp (floor-by-two x))
  (if (equal (rem x 2) 0)
    (/ x 2)
    (- (/ x 2) 1/2)))
(check= (floor-by-two 6) 3)
(check= (floor-by-two 7) 3)
    
; Function that converts a natural number n to a boolean list, as follows.
; If n >= 26, return nil. Otherwise, return the binary representation of n,
; where we interpret nil as 0 and t as 1. Leading zeros must be included as
; leading nil's in the result! See tests below.
; Since 2^5 = 32 > 26, the result is going to be a boolean list of length
; exactly 5. What is the condition for the left-most (highest-order) bit to
; be 1? Use the same reasoning to determine the other
; digits.
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
;d2b-help
(defunc d2b-help (x y)
  :input-contract (and (natp x) (natp y))
  :output-contract (booleanp (d2b-help x y))
  (if (natp (- x y))
    t
    nil))
(check= (d2b-help 2 5) nil)
(check= (d2b-help 6 5) t)
;d2b-help2
(defunc d2b-help2 (x y)
  :input-contract (and (natp x) (natp y))
  :output-contract (natp (d2b-help2 x y))
  (if (natp (- x y))
    (- x y)
    x))
(check= (d2b-help2 2 5) 2)
(check= (d2b-help2 9 5) 4)
    
  
; Hints: this function should be non-recursive and let* is your friend.
(defunc dec2booleanlist (n)
  :input-contract (natp n)
  :output-contract (booleanlistp (dec2booleanlist n))
  (if (>= n 26)
    nil
  (let* 
    (
    (a1 (d2b-help n 16))
    (r1 (d2b-help2 n 16))
    (a2 (d2b-help r1 8))
    (r2 (d2b-help2 r1 8))
    (a3 (d2b-help r2 4))
    (r3 (d2b-help2 r2 4))
    (a4 (d2b-help r3 2))
    (r4 (d2b-help2 r3 2))
    (a5 (d2b-help r4 1))
    (r5 (d2b-help2 r4 1)))
    (list a1 a2 a3 a4 a5))))
  
(check= (dec2booleanlist  8) '(nil t nil nil nil)) ; 8 = 01000. Do not skip leading nil's (zeros)
(check= (dec2booleanlist 32) nil)
(check= (dec2booleanlist 17) '(t nil nil nil t))
(check= (dec2booleanlist 13) '(nil t t nil t))
(check= (dec2booleanlist 1000000000000000000) nil)
; Function that converts a symbol s into a boolean list. First convert s
; into a decimal number, then convert that into boolean list. No
; recursion needed.
(defunc symbol2booleanlist (s)
  :input-contract (symbolp s)
  :output-contract (booleanlistp (symbol2booleanlist s))
  (dec2booleanlist (symbol2dec s)))
(check= (symbol2booleanlist 'f) '(nil nil t nil t))
(check= (symbol2booleanlist 'z) '(t t nil nil t))
(check= (symbol2booleanlist 'kjhg) nil)
; Function that converts a symbol list sl into a boolean list. See the
; tests below: the result must be the *concatenation* (use append) of all
; boolean lists obtained by converting the symbols in sl into boolean
; lists.
(defunc symbollist2booleanlist (sl)
  :input-contract (symbollistp sl)
  :output-contract (booleanlistp (symbollist2booleanlist sl))
  (if (endp sl) '()
  (append (symbol2booleanlist (first sl)) 
          (symbollist2booleanlist (rest sl)))))
; Notice that you must put spaces between symbols, but the number
; of spaces are irrelevant.
(check= (symbollist2booleanlist '(i   a m   s a m))
    '(NIL T NIL NIL NIL ;i
          NIL NIL NIL NIL NIL ;a
          NIL T T NIL NIL ;m
          T NIL NIL T NIL ;s
          NIL NIL NIL NIL NIL ;a
          NIL T T NIL NIL)) ;m
;;; Conversion from booleanlist to symbollist ;;;
; Function that takes a boolean list (any length), interprets nil as 0 and
; t as 1 and the list as a number in binary representation. Output is the
; decimal value of the number. Hint: use power!
(defunc booleanlist2dec (l)
  :input-contract (booleanlistp l)
  :output-contract (natp (booleanlist2dec l))
  (if (endp l)
    0
    (if (equal t (first l))
      (+ (power 2 (- (len l) 1))
         (booleanlist2dec (rest l)))
      (+  0 
       (booleanlist2dec (rest l))))))
(check= (booleanlist2dec '(t t nil t)) 13)
(check= (booleanlist2dec '(t t nil t t nil t nil t)) 437)
(check= (booleanlist2dec '(t nil t nil)) 10) 
; Function that takes a decimal number dec. If dec >= 26, return the
; symbol - (dash). Otherwise, return the dec'th letter of the alphabet (counting
; starts from 0).
; Hint: use *letterlist-values* and nth.
(defunc dec2symbol (dec)
  :input-contract (natp dec)
  :output-contract (symbolp (dec2symbol dec))
  (if (>= dec 26)
    '-
    (nth dec *letterlist-values*)))
(check= (dec2symbol  0) 'a)
(check= (dec2symbol 10) 'k)
; Function that converts a boolean list to a symbol. First convert the
; boolean list into a decimal number, then convert that into symbol via our
; ASCII code.
(defunc booleanlist2symbol (l)
  :input-contract (booleanlistp l)
  :output-contract (symbolp (booleanlist2symbol l))
  (dec2symbol(booleanlist2dec l)))
(check= (booleanlist2symbol '(nil t nil t nil)) 'k) ; 01010 (bin) = 10 (dec)
(check= (booleanlist2symbol '(nil)) 'a)
(check= (booleanlist2symbol '(t t nil nil t)) 'z)
; Function that converts a boolean list into a symbol list. This function
; takes the first 5 booleans in the lists, interprets them as a binary
; number and converts them into a symbol, using previously defined
; functions. Then the function takes the next 5 booleans in the list, and
; so on. The result must be the list of all symbols obtained this
; way.
; Hint: use nthrest: (nthrest n l) applies rest to l n times, e.g.,
; (nthrest 0 '(1 2 3)) = (1 2 3) and (nthrest 2 '(1 2 3)) = (3)
(defunc booleanlist2symbollist (l)
  :input-contract (booleanlistp l)
  :output-contract (symbollistp (booleanlist2symbollist l))
  (if (endp l)
    '()
  (cons (booleanlist2symbol (prefix l 5))
        (booleanlist2symbollist (nthrest  5 l )))))
                        
(check= (booleanlist2symbollist '(nil nil nil nil nil nil nil nil nil t nil
                      nil nil t nil)) '(a b c))
(check= (booleanlist2symbollist '(t t nil nil t nil nil nil nil t nil
                      nil nil t nil)) '(z b c))
(check= (booleanlist2symbollist '()) '())
;;; Now the Encoding and Decoding ;;;
; Remember the xor function:
(defunc xor (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (xor a b))
  (not (iff a b)))
;; bitwise xor. This function takes two boolean lists and xor's
;; corresponding elements. If one of the lists has fewer elements than the
;; other, assume nil for the missing elements; see tests below
(defunc bitwise-xor (l1 l2)
  :input-contract (and (booleanlistp l1) (booleanlistp l2))
  :output-contract (booleanlistp (bitwise-xor l1 l2))
  ;l1 is empty and l2 is not
  (if (and (endp l1) (not (endp l2))) 
    (cons (xor nil (first l2)) (bitwise-xor '() (rest l2)))
    ;l1 is not empty, but l2 is.
    (if (and (endp l2) (not (endp l1)))
      (cons (xor nil (first l1)) (bitwise-xor '() (rest l1)))
      ;l1 and l2 are both empty
      (if (and (endp l1) (endp l2))
        '()
        ;neither l1 nor l2 is empty
        (cons (xor (first l1) (first l2)) (bitwise-xor (rest l1) (rest l2)))))))
(check= (bitwise-xor '(nil t t) '(t nil nil)) '(t t t))
(check= (bitwise-xor '(nil t t) '(t nil))     '(t t t)) ; same as previous, by convention
(check= (bitwise-xor '(t t t) '(t nil))     '(nil t t))
; Function to encrypt a message. A "message" is a list of symbols. Convert
; that list into a boolean list, then encrypt that against the secret key,
; using xor.
(defunc encrypt (message)
  :input-contract (symbollistp message)
  :output-contract (booleanlistp (encrypt message))
  (bitwise-xor *key-values* (symbollist2booleanlist message)))
        
              
; The result of this function depends on your choice of key. Show the
; output of this function on a few test cases.
; Function that decrypts an encrypted message. An "encrypted message" is a
; boolean list. xor that list bitwise against the secret key, and convert
; the result into a list of symbols.
(defunc decrypt (cryptic-message)
  :input-contract (booleanlistp cryptic-message)
  :output-contract (symbollistp (decrypt cryptic-message))
  (booleanlist2symbollist (bitwise-xor *key-values* cryptic-message)))
; The result of this function depends on your choice of key. Show the
; output of this function on a few test cases.
; Now test whether encryption followed by decryption reproduces the
; original message. The following test will pass with the key defined
; above.
(check=
 (let ((m '(m e e t     y o u     u n d e r     t h e     b r i d g e)))
   (equal (decrypt (encrypt m))
          m))
 t)
; Does decrypting the encrypted message always reproduce the original? Or
; can you think of cases that do NOT cause a contract violation, yet the
; encryption and decryption are not inverses of each other? Create such an
; example in the test below (note: we check= against nil, not against t):
(check=
 (let ((m '(aa hg saldf)))
   (equal (decrypt (encrypt m))
          m))
 nil)
;We couldn't find a case in which decrypting the encrypted message
;would not return the original message.
; Hint: here is how you can get more output for a failing check= (other
; than just the info that it failed). Replace the two ... with the SAME
; string. This check= may fail, thus it is placed in comments.
#|
(acl2::without-evisc
 (check=
  (decrypt (encrypt '(...)))
  '(...)))
|#