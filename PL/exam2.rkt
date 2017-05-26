#lang pl un

(define undo ; make it an error to call it accidentally
  (box (lambda () (error 'undo "nothing to undo"))))

(define (set-box*! box new-value)
  (set-box! undo
            (let ([old-value (unbox box)]
                  [old-undo  (unbox undo)])
              (lambda ()
                (set-box! box old-value)
                ;; Optional, but not really needed:
                ;;   (set-box! undo old-undo)
                (old-undo))))
  (set-box! box new-value))



(define (atomic-thunk thunk)
  (lambda ()
    (define old-undo (unbox undo))
    (define completed? (box #f))
    (dynamic-wind
     (lambda ()
       (set-box! undo void))
     (lambda ()
       (define result (thunk))
       (set-box! completed? #t)
       result)
     (lambda ()
       (unless (unbox completed?)
         ((unbox undo)))
       (set-box! undo old-undo)))))




(define x (box 0))
(define y (box 0))
(define foo
  (atomic-thunk
   (lambda ()
     (set-box*! x (add1 (unbox x)))
     (set-box*! y (/ 10 (modulo (unbox x) 3)))
     "hey!")))
(test (foo) => "hey!")
(test (list (unbox x) (unbox y)) -> '(1 10))
(test (foo) => "hey!")
(test (list (unbox x) (unbox y)) -> '(2 5))
(test (foo) =error> "division by zero")
(test (list (unbox x) (unbox y)) => '(2 5))



; ===== 2
(define (set*! org new-value)
  (set! undo
        (let ([old-value org]
              [old-undo  undo])
          (lambda ()
            (set! org old-value)
            (old-undo))))
  (set! org new-value))

(define-syntax set*!
  (syntax-rules ()
    [(set*! org new-value)
     (begin (set! undo
                  (let ([old-value org]
                        [old-undo  undo])
                    (lambda ()
                      (set! org old-value)
                      (old-undo)))))
     (set! org new-value)]))

(define-syntax atomic
  (syntax-rules ()
    [(atomic E Es ...)
     ((atomic-thunk (lambda () E Es ...)))]))

; ======== 3 
(define x 0)
((let/cc jump-out
   (atomic (set*! x (add1 x))
           (let/cc k (jump-out k))
           (set*! x (add1 x))
           (lambda () x))))

(define (atomic-thunk thunk)
  (lambda ()
    (define old-undo (unbox undo))
    (define completed? (box #f))
    (define first? (box #t))
    (dynamic-wind
     (lambda ()
       (if first?
       (begin (set-box! undo void)
              
     (lambda ()
       (define result (thunk))
       (set-box! completed? #t)
       result)
     (lambda ()
       (unless (unbox completed?)
         ((unbox undo)))
       (set-box! undo old-undo))))))))


(define (shortest l)
  (if (ormap null? l)
      0
      (+ 1 (shortest (map rest l)))))
      
(define-type TYPE
  [IntT]            ; <---
  [NumT]
  [BoolT]
  [FunT TYPE TYPE])

(: typecheck* : PICKY TYPEENV -> TYPE)
(define (typecheck* expr type-env)
  (: two-nums : PICKY PICKY -> Void)
  (define (two-nums e1 e2)
    (typecheck* e1  type-env)
    (typecheck* e2  type-env))
  (: two-ints : PICKY PICKY -> Void)
  (define (two-ints e1 e2)
    (typecheck e1 (IntT) type-env)
    (typecheck e2 (IntT) type-env))
  (define (helper e)
    (typecheck* e type-env))
  (cases expr
    ...
    [(Add   l r) (two-ints l r) (IntT)]
    [(Sub   l r) (two-nums l r) (NumT)]
    [(Equal l r) (two-nums l r) (BoolT)]
    [(Less  l r) (two-nums l r) (BoolT)]
    ...))
(let ([b (box 0)])
  (define (set+ret n)
    (set-box! b n)
    n)
  (+ (set+ret 1) (set+ret 2))
  (match (unbox b)
    [1 'right-to-left]
    [2 'left-to-right]))

(let ([b (box 0)])
  (define (set+ret n)
    (set-box! b 10)
    10)
  (set+ret 9)
  (match (unbox b)
    [0 'function-last]
    [10 'function-first]))
      
     
