/*

 An OBJECT is a function symblol -> any

 name
 fields
 methods with dynamic dispatch
 constructors
 inheritance
 this.
 mutation


(define (square x)
    (local [(define super (object))]
        (lambda (msg)
                (cond
                        [(symbol=? msg 'size) x]
                        [(symbol=? msg 'area) (* x x)]
                        [else (super msg)])))


(define (circle r)
     (local [(define super (object))]
        (lambda (msg)
                (cond
                        [(symbol=? msg 'area) (* pi x x)]
                        [else (susper msg)])))
                        
(define (object)
        (lambda (msg)
          RuntimeException))
          
(define (shape)          
    (local [(define super (dumb))]
           [(define (this msg)]
       (lambda (msg)
            (cond
                [(symbol=? msg 'twice) (* 2 (this msg))]
                [else (super msg)]))
    
                
                
 A METHOD is a function with at least one parameter
 
 (define-struct object (fields objects)
 
 an OBJECT is (make-object [List any] [List method]
 
 (define (square x)
    (local [(define super(shape))]
           [(define self
                (make-object (append (list x) (list (list 'area 
                       (lambda (this) (* (first (object felds this)) 
                                             (first (object felds this))))
                   
 
 */




















