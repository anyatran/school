#lang pl un
(define (blank)
  (set! blank (lambda (x) 660)))

(and (blank) (blank blank))

(: typecheck : PICKY TYPE TYPEENV -> Void)
;; Checks that the given expression has the specified type.  Used only
;; for side-effects, so return a void value.  There are two
;; side-effects that it can do: throw an error if the input expression
;; doesn't typecheck, and type variables can be mutated once their
;; values are known -- this is done by the `types=' utility function
;; that follows.
(define (typecheck expr type type-env)
  ;; convenient helpers
  (: type= : TYPE -> Void)
  (define (type= type2) (types= type type2 expr))
  (: two-nums : PICKY PICKY -> Void)
  #;(define (two-nums e1 e2)
      (typecheck e1 (NumT) type-env)
      (typecheck e2 (NumT) type-env))
  (define (num-bool-type e)
    (cases (typecheck* e type-env)
      [(NumT) #t]
      [(BoolT) #f]
      [else (error 'typecheck "type error for ~s: expecting a number type"
                   expr)]))
  (: two-nums : PICKY PICKY -> Void)
  (define (two-nums e1 e2)
    ;; both integers => return `IntT'; otherwise return `NumT'
    (if (num-type e1) (if (num-type e2) (NumT) (error 'typecheck "not matching"))
        (if (not (num-type e2)) (BoolT) (error 'typecheck "not matching")))
    (if (and (num-type e1) (num-type e2)) (NumT) (BoolT)))
  (cases expr
    [(Num n)     (type= (NumT))]
    [(Id name)   (type= (type-lookup name type-env))]
    [(Add   l r) (two-nums l r)]  ; note that the order
    [(Sub   l r) (two-nums l r)]  ; in these things can
    ))




(define (typecheck expr type type-env)
  ...
  (: two-nums/bools : PICKY PICKY -> Void)
  (define (two-nums/bools e1 e2)
    (define t (?T (box #f)))
    ;; (note that the following could be done in other orders)
    ;; make sure that both have the same type (errors if not)
    (typecheck e1 t type-env)
    (typecheck e2 t type-env)
    ;; now see that it's either a num or a bool (`same-type' doesn't
    ;; barf, it returns a boolean)
    (unless (or (same-type t (NumT)) (same-type t (BoolT)))
      (error 'typecheck "..."))
    ;; and finally check that this is also the return type
    (type= t))
  (cases expr
    ...
    [(Add l r) (two-nums/bools l r)]
    [(Sub l r) (two-nums/bools l r)]
    ...))

#lang pl lazy

(define (andmap fun list)
  (if (fun (first list)) (andmap fun (rest list)) #f))


(define (fun/k prompts k)
    (if (null? prompts)
      (k 0)
      (web-read/k (first prompts)
        (lambda (n)
          (sum/k (rest prompts)
                 (lambda (sum-of-rest)
                   (k (+ n sum-of-rest))))))))

