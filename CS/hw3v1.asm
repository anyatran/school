.globl main
.data  
    bracket: .asciiz "[ "
    initarray: .asciiz "Initial array is:\n"
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
    lw $s0, size #load size
    la $s1, pointers #addresses of pointers
    la $s2, data
    lw $s3, capacity #64 is the size of the array of pointers
    add $s5, $s1, $s3 #the last pointer
    
    #addi $sp, $sp, -20
    #sw $ra, 16($sp)
    
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
    
    la $a0, finish
    li $v0, 4
    syscall
    
    jal print
    
    
    li $v0, 10
    syscall
    
    jr $ra

set_up_ptrs:
    addi $sp, $sp, -20
    sw $ra, 16($sp)
    
    bge $s1, $s5, done # i = last pointer
    sw $s2, ($s1) #put a pointer to the array to the array of pointers
    
    addi $s1, $s1, 4 #point to the next element the array
    addi $s2, $s2, 32 #move to the next string
    
    j set_up_ptrs
    
done:
    la $s1, pointers
    lw $ra, 16($sp)
 	addi $sp, $sp, 20
    jr $ra
    
    
print:
    addi $sp, $sp, -20
    sw $ra, 16($sp)
    
    beq $s1, $s5, exit # if the pointer of pointers >= 64, exit
    
    lw $a0, ($s1) #load the string from the pointer s1
    li $v0, 4 #print string
    syscall
    
    la $a0, space
    li $v0, 4
    syscall
    
    addi $s1, $s1, 4 #move pointer
    
    j print
    
    jr $ra
exit:
	la $s1, pointers
	lw $ra, 16($sp)
 	addi $sp, $sp, 20
    jr $ra
    
    
    
ins_sort:
	#s5 = end of the array
	#s1 is array of addresses
	
	addi $sp, $sp, -20
    sw $ra, 16($sp)
    
    li $t0, 1 # i = 1
    sub $t
    
	lw $t0, ($s1) # first word in the array (i) last x
	lw $t1, ($s1) # first word in the array (i) last y	
	jal ins_sort_loop
	
	
	
	j ins_sort 
	

	
	
ins_sort_loop:
    
	lw $a1, ($s1) #pivot (j) aka first
	li $a2, 4 #next
	add $a3, $a1, $a2 #a[j+1]
	
	jal str_lt
	
	ble $a1, $zero, after_loop #if a[j] <= 0
	beq $v0, $zero, after_loop # str_lt is false
	
	addi $t0, $t0, 4 # i++
	move $a3, $a1 #a[j+1] = a[j]
	
	j ins_sort_loop

    
after_loop:
	move $t3, $t0 #a[j+1] = value;
	lw $ra, 16($sp)
 	addi $sp, $sp, 20
	jr $ra
str_lt:
	#lw $t0, ($s1) #load 1st pointer (x)
    #lw $t1, ($s1) #load 1st pointer (y)
    lb $t2, ($t0) #*x
	lb $t3, ($t1) #*y
	
	beq $t2, $zero, for_exit #if #*x = 0 -> exit
	beq $t3, $zero, for_exit #if *y = 0, -> exit
	
	bgt $t3, $t2, return1 # if *y > *x return 1
	bgt $t2, $t3, return0 # if *x > * y return0
	
	addi $t0, $t0, 1 # *x++
	addi $t1, $t1, 1 # *y++
	
	j str_lt

for_exit:
	beq $t3, $zero, return0 #if *y == \0
	j return1
	
return1:
	li $a0, 1
	li $v0, 1
	syscall 
	
	lw $ra, 16($sp)
 	addi $sp, $sp, 20
	jr $ra

return0:
	li $a0, 0
	li $v0, 1
	syscall
	
	move $v0, $zero
	lw $ra, 16($sp)
 	addi $sp, $sp, 20
	jr $ra

#fnc2:
	#prolog
#	addi $sp, $sp, -4 #create a call frame
	
#	sw $ra, 0($sp) #save $ra
#	fal fun3
	#epilog
#	lw $ra, 0($sp)
#	addi $sp, $sp, 4
#	jr $ra
	
