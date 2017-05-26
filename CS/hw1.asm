.globl main
.data
	posint: .ascii "Positive integer: "
	result: .ascii "The value of 'factorial(%d)' is:  \n"
.text
main:
	la $a0, posint #storing posint into a0
	li $v0, 4 # print PosInt
	syscall
	
	li $v0, 5 #read_int
	syscall
	
	move $t0, $v0 #move v0 to t0/ input to t0
	
	subi $sp, $sp, 20
	sw   $t0, 0($sp)    # arg1: number n
	sw   $ra, 4($sp)  # Save $ra on stack
	jal factorial #run factorial
	lw $ra, 4($sp) #loading back
	lw $t0, 0($sp) #oading back
	addi $sp, $sp, 20 #adding back
	
	#print result
	la $a0, result
	li $v0, 4 #prin int
	syscall	
	
	jr $ra
	
	li $v0, 10
factorial:
	subi $sp, $sp, 20 # adjust stack for 5 items
	sw $ra, 4($sp) # save the return address
	sw $a0, 0($sp) # save the argument n
	
	move $t0, $a0
	bne $t0,$zero,else # if n >= 1, go to else
	
	addi $v0,$zero,1 # return 1 if x
	addi $sp,$sp, 20 # pop 2 items off stack
	
	li $v0, 0  # 0 means success; Pass return value in $v0
	jr $ra # return to caller
	
else: 
	subi $a0,$a0, 1 # n >= 1: argument gets (n ? 1)
	jal factorial # call fact with (n ? 1)
	
	lw $a0, 0($sp) # return from jal: restore argument n
	lw $ra, 4($sp) # restore the return address
	addi $sp, $sp, 20 # adjust stack pointer to pop 2 items
	
	mul $v0, $a0, $v0 # return n * fact (n ? 1)
	
	li $v0, 0  # 0 means success; Pass return value in $v0
	jr $ra # return to the caller
