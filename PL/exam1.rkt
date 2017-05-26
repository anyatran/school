#lang pl un
;; Function List-of-args -> 
;( : delayed : (All (A B) (A -> B) (Listof A) -> (A -> B)))
(define (delayed f l)
  (let 
      ([buffer (box (map f l))])
    (lambda (x)
      (if (null? l) (error 'delayed "empty list")
          (let ([res-x (f x)]
                [old (first (unbox buffer))])
                (set-box! buffer (append (rest (unbox buffer)) (list res-x)))
                old)))))
  
  (define f1 (delayed add1 '(0)))
  (test (f1 10) => 1)
  (test (f1 20) => 11)
  (test (f1 30) => 21)
  (define f2 (delayed list '(x y z)))
  (test (f2 'a) => '(x))
  (test (f2 'b) => '(y))
  (test (f2 'c) => '(z))
  (test (f2 'd) => '(a))
  (test (f1 40) => 31)




#|

    Γ ⊢ A : τ1   Γ ⊢ B : τ2
  ——————————————————————————————————————
  Γ ⊢ {pair A B} : (τ1 x τ2)


    Γ ⊢ E : (τ1 x τ2)   
  ——————————————————————————————————————
  Γ ⊢ {frs E} : τ1 

   Γ ⊢ E : (τ1 x τ2)   
  ——————————————————————————————————————
  Γ ⊢ {frs E} : τ2 
|#

;; this is not a part of the AST now, and it also has a new variant
;; for type variables (see `same-type' for how it's used)
(define-type TYPE
  [NumT]
  [BoolT]
  [FunT TYPE TYPE]
  [?T (Boxof (U TYPE #f))]
  [CoupleT TYPE TYPE])

(: same-type : TYPE TYPE -> Boolean)
;; Compares the two input types, return true or false whether they're
;; the same.  The process might involve mutating type variables.
(define (same-type type1 type2)
  ;; the `PairT' type is only used to conveniently match on both types
  ;; in a single `cases', it's not used in any other way
  (cases (PairT type1 type2)
    ;; flatten the first type, or set it to the second if it's unset
    [(PairT (?T box) type2)
     (let ([t1 (unbox box)])
       (if t1
         (same-type t1 type2)
         (begin (set-box! box type2) #t)))]
    ;; do the same for the second (reuse the above case)
    [(PairT type1 (?T box)) (same-type type2 type1)]
    ;; the rest are obvious
    [(PairT (NumT) (NumT)) #t]
    [(PairT (BoolT) (BoolT)) #t]
    [(PairT (FunT i1 o1) (FunT i2 o2))
     (and (same-type i1 i2) (same-type o1 o2))]
    [(PairT (CoupleT x1 y1) (CoupleT x2 y2))
     (and (same-type x1 y2) (same-type x1 y2))]
    [else #f]))

  
define (typecheck expr type type-env)
  ;; convenient helpers
  (: type= : TYPE -> Void)
  (define (type= type2) (types= type type2 expr))
  (: two-nums : PICKY PICKY -> Void)
  (define (two-nums e1 e2)
    (typecheck e1 (NumT) type-env)
    (typecheck e2 (NumT) type-env))
  (cases expr
    [(Num n)     (type= (NumT))]
    [(Id name)   (type= (type-lookup name type-env))]
    [(Add   l r) (two-nums l r) (type= (NumT))]  ; note that the order
    [(Sub   l r) (two-nums l r) (type= (NumT))]  ; in these things can
    [(Equal l r) (two-nums l r) (type= (BoolT))] ; be swapped, maybe
    [(Less  l r) (two-nums l r) (type= (BoolT))] ; better that way...
    [(Fun bound-id bound-body)
     (let (;; the identity of these type variables is important!
           [itype (?T (box #f))]
           [otype (?T (box #f))])
       (type= (FunT itype otype))
       (typecheck bound-body otype
                  (ExtendTypeEnv bound-id itype type-env)))]
    [(Call fun arg)
     (let ([type2 (?T (box #f))]) ; same here
       (typecheck arg type2 type-env)
       (typecheck fun (FunT type2 type) type-env))]
    [(With bound-id named-expr bound-body)
     (let ([type2 (?T (box #f))]) ; and here
       (typecheck named-expr type2 type-env)
       (typecheck bound-body type
                  (ExtendTypeEnv bound-id type2 type-env)))]
    [(If cond-expr then-expr else-expr)
     (typecheck cond-expr (BoolT) type-env)
     (typecheck then-expr type type-env)
     (typecheck else-expr type type-env)]
    [(Pair fst scd)
     (let ([type1 (?T (box #f))]
           [type2 (?T (box #f))])
       (type= (CoupleT type1 type2))
       (typecheck scd type2 type-env)
       (typecheck fst type type-env))]
    [(Fst p)
     (typecheck p (CoupleT type (?T (box #f))) type-env)]
    [(Snd p)
     (typecheck p (CoupleT (?T (box #f)) type) type-env)])

; Q4
(define (web-read/k prompt k)
  (set-box! what-next
            (lambda ()
              (printf "~a: " prompt)
              (k (read-line))))
  (error 'web-read/k "enter (resume) to continue"))


(define (execute-read receiver)
  (cases receiver
    [(FunV names body env)
     (execute-val
      (eval body (extend names (list (wrap-in-val (read-line))) env)))]
    [else (error 'execute-receiver "expecting a receiver function")]))

(define (execute-read receiver)
  (cases receiver
    [(FunV names body env)
     (set-box! what-next
               (lambda ()
                 (execute-val
                  (eval body (extend names
                                     (list (wrap-in-val (read-line)))
                                     env)))))
     (error 'read "enter (resume) to continue")]
    [else (error 'execute-receiver "expecting a receiver function")]))

...
[(CPS (if E1 E2 E3))
 (lambda (k) 
   ((CPS E1)
    (lambda (v1)
      ((CPS E2)
       (lambda (v2)
         ((CPS E3)
          (lambda (v3)
            (k (if v1 v2 v3)))))))))]

;; ** A language that is CPS-transformed (not an interpreter)

#lang racket

(define (call-k f k)
  (f (lambda (val cont) (k val)) k))

(define-syntax CPS
  (syntax-rules (+ lambda if)
    [(CPS (+ E1 E2))
     (lambda (k)
       ((CPS E1)
        (lambda (v1)
          ((CPS E2)
           (lambda (v2)
             (k (+ v1 v2)))))))]
    [(CPS (E1 E2))
     (lambda (k)
       ((CPS E1)
        (lambda (v1)
          ((CPS E2)
           (lambda (v2)
             (v1 v2 k))))))]
    [(CPS (F I1 I2))
     (lambda (k)
       ((CPS F)
        (lambda (f)
          ((CPS I1)
           (lambda (i1)
             ((CPS I2)
              (lambda (i2)
                (f i1 i2 k)))))
    [(CPS (lambda (arg) E))
     (lambda (k)
       (k (lambda (arg cont)
            ((CPS E)
             cont))))]
    [(CPS (lambda (arg1 arg2) E))                      ;* (2)
     (lambda (k)
       (k (lambda (arg1 arg2 cont)
            ((CPS E)
             cont))))]
    
    
    [(CPS (if C T F))
     (lambda (k)
       ((CPS C)
        (lambda (c)
          (if c (CPS T) (CPS F)) k)))]
    ;; the following pattern ensures that the last rule is used only
    ;; with simple values and identifiers
    [(CPS (x ...))
     ---syntax-error---]
    [(CPS V)
     (lambda (k)
       (k V))]))

(define-syntax CPS-code
  (syntax-rules (define)
    [(CPS-code (define (id arg) E) more ...)
     (begin (define id ((CPS (lambda (arg) E)) (lambda (v) v)))
            (CPS-code more ...))]
    [(CPS-code (define (id arg1 arg2) E) more ...)     ;* (1)
     (begin (define id ((CPS (lambda (arg1 arg2) E)) (lambda (v) v)))
            (CPS-code more ...))]
    [(CPS-code (define id E) more ...)
     (begin (define id ((CPS E) (lambda (v) v)))
            (CPS-code more ...))]
    [(CPS-code expr more ...)
     (begin ((CPS expr) (lambda (v) v))
            (CPS-code more ...))]
    [(CPS-code) (begin)])) ; done

(CPS-code (if 1 2 3)
          (if #f 2 3))
(CPS-code (define (foo1 x y) (if 1 x y))
          (define (foo2 x y) (if #f x y))
          (foo1 2 3)
          (foo2 2 3))




