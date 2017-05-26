 .globl main
 .data 

        Array: .space 20 
            Promt: .asciiz "Enter a String:\n"  
            Line: .asciiz "\n"

.text

main:

    la $a0,Promt    
    li $v0,4    
    syscall

    la $a0,Array    
    li $a1,20   
    li $v0,8    
    syscall

    la $t0,Array  # BASE ADDRESS OF ARRAY   
    li $t1,4    
    lw $a0,0($t0) #***MOVED THIS OUT***     
    Loop:
        add $t0,$t0,$t1         
            beq $a0,0, Exit         
            la $a0, Array       
            li $v0,4        
            syscall         

            li $a0, 0  #****ADDED THIS LINE****         
            j Loop

    Exit:
        li $v0,10   
            syscall