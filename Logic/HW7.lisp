; ****************** BEGIN INITIALIZATION FOR ACL2s MODE ****************** ;
; (Nothing to see here!  Your actual file is after this initialization code);

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

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading ACL2s customizations book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "custom" :dir :acl2s-modes :uncertified-okp nil :load-compiled-file :comp :ttags :all)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem setting up ACL2s mode.") (value :invisible))

;Settings common to all ACL2s modes
(acl2s-common-settings)

; Non-events:
(set-guard-checking :none)


; ******************* END INITIALIZATION FOR ACL2s MODE ******************* ;
;$ACL2s-SMode$;ACL2s
#|
CS 2800 Homework 7 - Spring 2014
Student names: 
Anh Tran
Tyler Kaminsky
Technical instructions:
- open this file in ACL2s as hw07.lisp
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
; Part A: Termination
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
Remember to switch from BEGINNER mode to (full-featured) ACL2s mode as
mentioned at the beginning of this problem set.
In this problem you will investigate the termination of a function that
operates on natural numbers _modulo n_ : this means we only consider
natural numbers i in the range 0 <= i < n (note that n is excluded); there
are exactly n such numbers. Arithmetic operations such as addition "wrap
around", as we will see.
We will implicitly introduce the datatype nat-mod-n of such restricted
natural numbers. This is a _parameterized_ datatype: it depends on the
parameter n.
|#
; Define a recognizer nat-mod-n-p for the datatype nat-mod-n. Since the
; type is parameterized by n, so is the recognizer, which hence takes not
; only some a of type All as input, but also n of type Nat:
; nat-mod-n-p : All x Nat -> Boolean
; (nat-mod-n-p a n) returns T iff a is a natural number less than n.
; Carefully think about the contracts; the signature given above says it all.
;
; Define 3 tests.
(defunc nat-mod-n-p (a n)
  :input-contract (and t (natp n))
  :output-contract (booleanp (nat-mod-n-p a n))
(if (and (natp a) (< a n))
  t
  nil))
(check= (nat-mod-n-p 4 5) t)
(check= (nat-mod-n-p 0 0) nil)
(check= (nat-mod-n-p 11 3) nil)
; For example, suppose we define the concrete type of numbers 0,1,2,3:
(defdata mod-4 (oneof 0 1 2 3))
; then the following theorem holds (and ACL2s should be able to prove it
; if you defined nat-mod-n-p correctly):
(thm (equal (mod-4p a) (nat-mod-n-p a 4)))
; What does the theorem state?
; Now define a function
; plus-mod-n : nat-mod-n x nat-mod-n x Nat -> nat-mod-n
; such that (plus-mod-n a b n) returns the sum of a and b modulo n. That is, it
; returns the sum a+b, unless that sum equals or exceeds n, in which case
; the result "wraps around": the result is the remainder of a+b when
; divided by n. See examples below.
; Hints: (i) There is a very simple way of defining this function; you
; don't even need any kind of remainder function. (ii) Use let to store the
; natural number a+b in a local variable.
; Define 3 more tests.
;
; Note the way the input contract is written. Make sure you understand why.
(defunc plus-mod-n (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (nat-mod-n-p (plus-mod-n a b n) n)
  (let* 
    (
    (sum1 (+ a b)))
    (if (< sum1 n)
      sum1
      (- sum1 n))))

(check= (plus-mod-n 2 3 10) 5)
(check= (plus-mod-n 2 3  5) 0)
(check= (plus-mod-n 2 3  4) 1)
(check= (plus-mod-n 5 5 6) 4)
(check= (plus-mod-n 5 5 9) 1)
(check= (plus-mod-n 5 5 20) 10)

; Now define an analogous function
; minus-mod-n : nat-mod-n x nat-mod-n x Nat -> nat-mod-n
; such that (minus-mod-n a b n) returns a-b modulo n. See tests below, and define 3 more.
; Think about what happens when a-b is negative.
(defunc minus-mod-n (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (nat-mod-n-p (minus-mod-n a b n) n)
(let* 
    (
    (sub1 (- a b)))
    (if (>= sub1 0)
      sub1
      (+ sub1 n))))

(check= (minus-mod-n 5 3 10) 2)
(check= (minus-mod-n 3 5 10) 8)
(check= (minus-mod-n 3 5  6) 4)
(check= (minus-mod-n 3 3  6) 0)
(check= (minus-mod-n 6 5  10) 1)
(check= (minus-mod-n 0 5  6) 1)
; We are now switching into program mode, as we are about to define a
; function that ACL2 has difficulties proving terminating (for good reasons):
:program
(acl2::acl2s-defaults :set acl2::testing-enabled nil)
; Write a function
; ring-rendezvous : nat-mod-n x nat-mod-n x Nat -> Boolean
; such that ring-rendezvous(a,b,n) implements the following loop:
; as long as a and b are not equal:
;   decrement a by 1 modulo n
;   increment b by 1 modulo n
; end of loop
; This can be accomplished as follows. Your function should return t if a
; and b are equal; this simply terminates the loop. Otherwise, the function
; is called recursively, replacing the arguments a and b according to the
; decrement/increment expressions shown above. Note that you have to use
; the plus-mod-n and minus-mod-n functions defined before to properly
; implement decrement and increment modulo n (do not use the built-in
; functions - and +). Parameter n always stays the same.
(defunc ring-rendezvous (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (booleanp (ring-rendezvous a b n))
  (if (= a b) t
    (ring-rendezvous (minus-mod-n a 1 n) (plus-mod-n b 1 n) n)))


; This function is accepted in program mode. (You may want to convince
; yourself that the function is not accepted without the :program switch.)
; Now turn off guard checking (which creates a warning in subsequent test
; cases that makes them unreadable):
(acl2::set-guard-checking :none)
; Now try out a few cases, including:
; (ring-rendezvous 2 3 7) ; T
; (ring-rendezvous 3 4 7) ; T
; (ring-rendezvous 2 4 8) ; T
; (ring-rendezvous 3 4 6) ; Not terminating
; (do not remove the comment sign in front of these tests, or your file may
; not be accepted).
; In the comments _behind_ the tests above, state your findings regarding
; what happens in these function calls.
; Now turn on function call tracing for ring-rendezvous:
(acl2::trace! ring-rendezvous)
; and run the above test cases again.
; Looking at these test cases, come up with conjectures for restrictions on
; the inputs (a b n) such that the function terminates.
; Conversely, what happens in the call (ring-rendezvous 3 4 6) ? Show that the
; function does not terminate on this input.
; Try to prove your conjectures from above using measure functions, or try
; to make your conjectures tight, i.e.: the function terminates exactly if

; ((natp n) => (and (nat-mod-n-p a n) (nat-mod-n-p b n))) /\ (~(natp n) => nil) /\ ~(equal (- (minus-mod-n a 1 n) (plus-mod-n b 1 n)) 1) =>
; => (ring-rendezvous a b n) = (ring-rendezvous b a n)
 (defunc m (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (natp (m a b n))
  (cond
   ((equal (- (minus-mod-n a 1 n) (plus-mod-n b 1 n)) 1) 0)
   ((<= a b) (- n b))
   (t (- n a))))
(check= (m 2 3 7) 4)
(check= (m 1 4 7) 3)
(check= (m 0 5 7) 2)
(check= (m 6 6 7) 1)
; for non terminating examples
(check= (m 3 4 6) 2)
(check= (m 2 5 6) 0)
(check= (m 1 0 6) 5)
; m on original is < m on recursive

; Now write another function
;
; ring-lap : nat-mod-n x nat-mod-n x Nat -> Boolean
;
; such that ring-lap(a,b,n) implements the following loop:
;
; as long as a and b are not equal:
;   increment a by 1 modulo n
;   increment b by 2 modulo n
; end of loop
; This can be accomplished as follows. Your function should return t if a
; and b are equal; this simply terminates the loop. Otherwise, the function
; is called recursively, replacing the arguments a and b by the increment
; expressions shown above. Note that you may reuse the plus-mod-n function
; defined before to properly implement increment modulo n (do not use the
; built-in functions - and +). Parameter n always stays the same.
(defunc ring-lap (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (booleanp (ring-lap a b n))
  (if (equal a b) t
    (ring-lap (plus-mod-n a 1 n) (plus-mod-n b 2 n) n))
)
; This function is accepted in program mode. (You may want to convince
; yourself that the function is not accepted without the :program switch.)
; Now turn off guard checking (which creates a warning in subsequent test
; cases that makes them unreadable):
(acl2::set-guard-checking :none)
; Now try out a few cases, including:
; (ring-lap 2 3 7) ; T
; (ring-lap 3 4 7) ; T
; (ring-lap 2 4 8) ; T
; (ring-lap 3 4 6) ; T
; (do not remove the comment sign in front of these tests, or your file may
; not be accepted).
; In the comments _behind_ the tests above, add your findings regarding
; what happens in these function calls.
; Now turn on function call tracing for ring-lap:
(acl2::trace! ring-lap)
; and run the above test cases again.
; Looking at these test cases, come up with conjectures for restrictions on
; the inputs (a b n) such that the function terminates. For what choice
; of inputs does ring-lap not terminate?
; Try to prove your conjectures from above using measure functions, or try
; to make your conjectures tight, i.e.: the function terminates exactly if

; ((natp n) => (and (nat-mod-n-p a n) (nat-mod-n-p b n))) /\ (~(natp n) => nil) =>
; => (ring-lap a b n) = (ring-lab b a n)

(defunc m-ring-lap (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (natp (m-ring-lap a b n))
  (cond
   ((equal a b) 0)
   ((< a b) (+ n (- a b)))
   (t (+ n (- b a)))))
(check= (m-ring-lap 3 4 7) 6)
(check= (m-ring-lap 4 6 7) 5)
(check= (m-ring-lap 5 1 7) 3)#|ACL2s-ToDo-Line|#

;...
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Part B: Equational reasoning 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
The following questions ask for "equational proofs" (the type of proofs we
have been doing to far) about ACL2s programs. When writing your proofs be
sure to justify each step in the style shown in class. You can use basic
arithmetic facts for free, but in the justification write "arithmetic",
e.g.,
    (first x) + (len (rest x)) + 0
= { Arithmetic }
    (len (rest x)) + (first x)
You may use infix notation (like x+y+z) for arithmetic operators (as done
above), instead of the LISP style prefix notation (like (+ x (+ y z))).
Here are the common definitions used for the remainder of the questions.
(defunc len (x)
  :input-contract t
  :output-contract (natp (len x))
  (if (atom x)
    0
    (+ 1 (len (rest x)))))
(defunc atom (x)
  :input-contract t
  :output-contract (booleanp (atom x))
  (not (consp a)))
(defunc not (a)
  :input-contract (booleanp a)
  :output-contract (booleanp (not a))
  (if a nil t))
(defunc listp (l)
  :input-contract t
  :output-contract (booleanp (listp l))
  (or (equal l ())
      (consp l)))
(defunc endp (a)
  :input-contract (listp a)
  :output-contract (booleanp (endp a))
  (not (consp a)))
(defunc twice (l)
  :input-contract (listp l)
  :output-contract (listp (twice l))
  (if (endp l)
    nil
    (cons (first l) (cons (first l) (twice (rest l))))))
(defunc app (a b)
  :input-contract (and (listp a) (listp b))
  :output-contract (listp (app a b))
  (if (endp a)
    b
    (cons (first a) (app (rest a) b))))
(defunc rev (x)
  :input-contract (listp x)
  :output-contract (and (listp (rev x)) (equal (len x) (len (rev x))))
  (if (endp x)
    nil
    (app (rev (rest x)) (list (first x)))))
Recall that for each of the defunc's above we get both a definitional
axiom (ic => function application = function body), and a contract
theorem (ic => oc). Definitional axioms and contract theorems are there for
you to use, as ACL2s has accepted these functions.
For the following claims, run some tests to conjecture whether they are
true or false. In the former case, prove them using equational reasoning.
In the latter case, disprove them by means of a counterexample. A
counterexample is an assignment to ALL free variables in the conjecture
that makes the conjecture evaluate to false. Do not give "partial
counterexamples" (leaving it to us to complete them).
(a)
(implies (listp y)
         (equal (len (rev (cons x y)))
                (+ 1 (len (rev y)))))
Hint: output contract of rev. If you make use of that, make sure you quote
an appropriate theorem or axiom.
(listp l) => (len (cons x l)) = (+ 1 (len l))
{expanding len}
(+ 1 (len (rest (cons x l))))
{axioms of if and cons}
(+ 1 (len l))
;Context
;C1: (listp y)
(len (rev (cons x y))) = (+ 1 (len (rev y)))
{axioms of rev, (len (cons x l)) = (+ 1 (len l))}
(len (cons x y) = (+ 1 (len (rev y))

(b)
(implies (and (listp x)
              (listp y)
              (listp z))
         (implies (equal (twice (app       x    z)) (app (twice       x   ) (twice z)))
                  (equal (twice (app (cons x y) z)) (app (twice (cons x y)) (twice y)))))
False
x = '(2 3 4)
y = '(5 6 7)
z = ' (8 9 10)
       
(implies (and (listp x)
              (listp y)
              (listp z))
              
(implies
(equal
(twice (app       x    z))
'(2 2 3 3 4 4 8 8 9 9 10 10)
(app (twice       x   ) (twice z)))
'(2 2 3 3 4 4 8 8 9 9 10 10)
(equal (twice (app (cons x y) z)) 
'( '(2 3 4) '(2 3 4) 5 5 6 6 7 7 8 8 9 9 10 10)
(app (twice (cons x y)) (twice y)))))
'( '(2 3 4) '(2 3 4) 5 5 6 6 7 7 5 5 6 6 7 7)
                  
                  
(c)
(implies (and (listp x)
              (listp y))
         (equal (app (rev x) (rev y))
                (rev (app x y))))
False
x = '(2 3 4)
y = '(5 6 7)
(implies (and (listp x)
              (listp y))
              
(equal
(app (rev '(2 3 4)) (rev '(5 6 7)))
(app '(4 3 2) '(7 6 5))
'(4 3 2 7 6 5)
(rev (app '(2 3 4) '(5 6 7)))
(rev '( 2 3 4 5 6 7))
'(7 6 5 4 3 2)))


(d)
(implies (listp x)
         (and (implies (endp x)
                       (equal (len (twice x))
                              (* 2 (len x))))
              (implies (and (not (endp x))
                            (equal (len (twice (rest x)))
                                   (* 2 (len (rest x)))))
                       (equal (len (twice x))
                              (* 2 (len x))))))
A = (listp x)
B = (enpt x)
C = (len (twice x)) = ((len x) * 2)
D = (~ (enpt x) /\ ((len (twice (rest x))) = ((len (rest x)) * 2)))
E = (len (twice x)) = ((len x) * 2)
 A => ((B => C) /\(D => E)) = (A => (B => C))/\(A => (D => E)) =
 ((A /\ B) => C) /\ ((A /\ D) => E)
 
 1) ((A /\ B) => C)
 C1: (listp x)
 C2: (enpt x)
 
 (len (twice x)) = ((len x) * 2)
 = {def. twice}
 (len nil) = ((len nil) * 2)
 = {def. len}
 0 = ((len nil) * 2)
 = {arithm}
 0 = ((len nil) * 2) 
 0 = 0* 2 
 0 = 0
 T
 
 2) ((A /\ D) => E)
 C1: (listp x)
 C2: (~ (enpt x))
 C3) (len (twice (rest x))) = ((len (rest x)) * 2)
 
 (len (twice x)) = ((len x) * 2)
 
 (len (twice x))
 = {def. twice}
 (len (cons (first x) (cons (first x) (twice (rest x)))))
 = {def. len}
 (1 + (len (cons (first x) (twice (rest x)))))
 = {def. len}
 (1 + 1 + (len (twice (rest x))))
 = {arithm}
 (2 + (len (twice (rest x))))
 = {C3}
 (2 + ((len (rest x)) * 2))
 
 (2 + ((len (rest x)) * 2)) = ((len x) * 2)
 = {def. len}
 (2 + ((len (rest x)) * 2)) = ((1 + (len (rest x))) * 2)
 = {arithm.}
 (2 + ((len (rest x)) * 2)) = ((1 * 2 + (len (rest x)) * 2)
 = {arithm.}
 (2 + ((len (rest x)) * 2)) = (2 + ((len (rest x)) * 2) = 
 T
 
 ((A /\ B) => C) /\ ((A /\ D) => E)
 T /\ T = T
 
 
 
|#









