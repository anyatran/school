.globl main
.data  
    bracket: .asciiz "[ "
    initarray: .asciiz "\nInitial array is:\n"
    bracket_end: " ]\n"
    space: ", "
    
    data1:
    .align 5
    .asciiz  "Joe",
    .align 5
    .asciiz  "Jenny",
    .align 5
    .asciiz  "Jill", 
    .align 5
    .asciiz  "John",
    .align 5
    .asciiz  "Jeff",
    .align 5
    .asciiz  "Joyce",
    .align 5
    .asciiz  "Jerry",
    .align 5
    .asciiz  "Janice",
    .align 5
    .asciiz  "Jake",
    .align 5
    .asciiz  "Jonna",
    .align 5
    .asciiz  "Jack",
    .align 5
    .asciiz  "Jocelyn",
    .align 5
    .asciiz  "Jessie",
    .align 5
    .asciiz  "Jess",
    .align 5
    .asciiz  "Janet",
    .align 5
    .asciiz  "Jane"
    
    .align 5
    size1: .word 16
    size: .word 2
    
    data:
    .align 5
    .asciiz  "Jenny",
    .align 5
    .asciiz  "Joe",
    .align 5
    
    pointers1: .space 64 #16*4. declare space in advance to use later
    pointers: .space 8
    capacity1: .word 64
    capacity: .word 8
    finish: .asciiz "Insertion sort is finished!\n"
.text
main:
	la $t0, pointers #pointers
	la $t1, data #names
	lw $t2, size # size
	addi $t3, $t0, 8 #last pointer in the array of pointers
	
	jal set_up_ptrs
	
	la $a0, initarray #initial array is:
    li $v0, 4
    syscall
    
    la $a0, bracket #"["
    li $v0, 4
    syscall
    
    jal print
    
    la $a0, bracket_end #" ]"
    li $v0, 4
    syscall
	
	
	jal ins_sort
	
	jal print
	li $v0, 10
    syscall
	
	
	
	
set_up_ptrs:
    la $a0, ($t1) # arguement names
    la $a1, ($t3) #last pointer
    la $a2, ($t0) #pointers
    jal loop_set_up
    
loop_set_up:
    
    beq $a2, $a1, done #if i = last -> done
    sw $a0, ($a2)
    
    addi $a2, $a2, 4
    addi $a0, $a0, 32
    
    j loop_set_up
    
done:
	move $t0, $a2
	la $t0, pointers
	
	jr $ra
	
	
print:
	addi $sp, $sp, -20
    sw $ra, 16($sp)
    sw $a0, 12($sp)
    sw $a1, 8($sp)
    sw $a2, 4($sp)
    
    la $a0, ($t1) # arguement names
    la $a1, ($t3) #last pointer
    la $a2, ($t0) #pointers
    
    jal loop_print
    
loop_print:
	beq $a2, $a1, exit
	
	lw $a0, ($a2) #load the string from the pointer s1
    li $v0, 4 #print string
    syscall
    
    la $a0, space
    li $v0, 4
    syscall
    
    addi $a2, $a2, 4 #move pointer
    
    j loop_print
    
exit:
	lw $a2, 4($sp)
    lw $a1, 8($sp)
    lw $a0, 12($sp)
	lw $ra, 16($sp)
	addi $sp, $sp, 20
	
	jr $ra
	
ins_sort:
	addi $sp, $sp, -20
    sw $ra, 16($sp)
    sw $a0, 12($sp)
    sw $a1, 8($sp)
    sw $a2, 4($sp)
    
    
    la $a0, ($t3) #last pointer
    la $a1, ($t0) #pointers
	la $a2, 4($a1) # a[i] = 1 x
    la $a3, ($a1) # a[j] = a[i-1] y
    addi $s0, $a3, 4 #a[j+1]
    
    jal loop_ins_sort
    
    lw $a2, 4($sp)
    lw $a1, 8($sp)
    lw $a0, 12($sp)
	lw $ra, 16($sp)
	addi $sp, $sp, 20
    
loop_ins_sort:
	jal str_lt
	
	beq $v0, $zero, exit_loop #str_lt(value, a[j]) false
	move $s0, $a3
	subi $a3, $a3, 4 #j--
	
	j loop_ins_sort
	
exit_loop:
	move $s0, $a2 # a[j+1] = a[i]
	move $t0, $a2
	#la $t0, pointers
	j loop_ins_sort
	
	
str_lt:
	lb $s1, ($a2) #*x
	lb $s2, ($a3) #*y
	
	beq $s1, $zero, for_exit #if #*x = 0 -> exit
	beq $s2, $zero, for_exit #if *y = 0, -> exit
	
	bgt $s2, $s1, return1 # if *y > *x return 1
	bgt $s1, $s2, return0 # if *x > * y return0
	
	addi $s1, $s1, 1 # *x++
	addi $s2, $s2, 1 # *y++
	
	j str_lt

for_exit:
	beq $s2, $zero, return0 #if *y == \0
	j return1
	
return1:
	li $a0, 1
	li $v0, 1
	syscall 
	
	addi $v0, $zero, 1
	
	jr $ra

return0:
	li $a0, 0
	li $v0, 1
	syscall
	
	move $v0, $zero
	
	lw $ra, 16($sp)
 	addi $sp, $sp, 20
	jr $ra

    
	
