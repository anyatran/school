.globl main

.data
  .align 5
names: .asciiz "Joe"    # All of the 16 names. Each name can have
  .align 5    # upto 32 bytes.
  .asciiz "Jenny"
  .align 5
  .asciiz "Jill"
  .align 5
  .asciiz "John"
  .align 5
  .asciiz "Jeff"
  .align 5
  .asciiz "Joyce"
  .align 5
  .asciiz "Jerry"
  .align 5
  .asciiz "Janice"
  .align 5
  .asciiz "Jake"
  .align 5
  .asciiz "Jonna"
  .align 5
  .asciiz "Jack"
  .align 5
  .asciiz "Jocelyn"
  .align 5
  .asciiz "Jessie"
  .align 5
  .asciiz "Jess"
  .align 5
  .asciiz "Janet"
  .align 5
  .asciiz "Jane"

.align 2
array: 	.space 64
space: 	.asciiz " "
lb:	.asciiz "["
rbn:	.asciiz " ]\n"

before:	.asciiz "Initial array is:\n"
after:	.asciiz "Insertion sort is finished!\n"

debug_text1: .asciiz "__DEBUG1__\n"
debug_text2: .asciiz "__DEBUG2__\n"

.text

main:
	jal init_array
	
	# tell user what the array looks like before the sort
	li $v0, 4
	la $a0, before
	syscall
	la $a0, array
	li $a1, 16
	jal print_array
	
	# insert sort
	la $a0, array
	li $a1, 16
	jal insert_sort
		
	# tell user what the array looks like after the sort
	li $v0, 4
	la $a0, after
	syscall
	la $a0, array
	li $a1, 16
	jal print_array
	
	li $v0, 10
	syscall
	
# !!!!!! FOR TESTING !!!!!!!
debug_print1:
  li $v0, 4
  la $a0, debug_text1
  syscall
  jr $ra
debug_print2:
  li $v0, 4
  la $a0, debug_text2
  syscall
  jr $ra

# EXPECTATIONS:
# nothing : $a0-3
# this will setup the array of pointers to strings.
init_array:
	li $t0, 0			# the current index
	ia_loop:
		mul $t3, $t0, 32
		la $t1, names($t3)
		mul $t2, $t0, 4
		sw $t1, array($t2)
		addi $t0, $t0, 1
		bne $t0, 16, ia_loop
	jr $ra
	
# EXPECTATIONS:
# $a0 : the starting address of an array of arrdesses to strings
# $a1 : the length of the array to sort
# rearrange the array of addresses so they are sorted occording
# to the alphebets lexographical order (high -> low)
insert_sort:
  addi $sp, $sp, -16 # create call frame
  sw $ra, 0($sp)     # save return address
  sw $a0, 4($sp)     # address of array
  sw $a1, 8($sp)     # size of array
  
  jal debug_print1
  
  li $s0, 1           # $s0 = i
  is_selector_loop:
    lw $t0, 8($sp)
    bge $s0, $t0, is_end
    
    mul $t1, $s0, 4
    lw $t0, 4($sp)
    add $s2, $t0, $t1
    lw $s2, ($s2)
  
    addi $s1, $s0, -1 # $s1 = j
    is_compare_loop:
      bltz $s1, is_compare_loop_end
      
      # str_lt(value, a[j])
      mul $t1, $s1, 4
      lw $t0, 4($sp)
      add $t0, $t0, $t1
      move $a0, $s2
      lw $t0, ($t0)
      move $a1, $t0
      
      jal str_lt
      
      beqz $v0 is_compare_loop_end
      
      # a[j+1] = a[j]
      lw $t0, 4($sp)
      mul $t1, $s1, 4
      add $t0, $t0, $t1
      addi $t1, $t0, 4  # a[j+1]
      lw $t2, 0($t0)
      sw $t2, 0($t1)
      
      addi $s1, $s1, -1  # j--
      b is_compare_loop
    
    is_compare_loop_end:
    # a[j+1] = value
    mul $t1, $s1, 4
    lw $t0, 4($sp)
    add $t0, $t0, $t1
    addi $t0, $t0, 4
    sw $s2, 0($t0)
    
    addi $s0, $s0, 1  # i++
    b is_selector_loop
  
  is_end:
    jal debug_print2
    
    lw $ra, 0($sp)    # restore return address
    addi $sp, $sp, 16 # free call frame
    jr $ra

# EXPECTATIONS:
# $a0 : the starting address of an address array
# $a1 : number of elements to print
# uses print_string system call to print the string
# at the address of the each element in the given array.
print_array:
	move $t0, $a0	# save the starting address
	move $t1, $a1	# save the number of elements
	li $v0, 4	# always printing string
	
	la $a0, lb
	syscall
	
	li $t2, 0
	pa_loop:
		mul $t3, $t2, 4
		add $t4, $t3, $t0
		la $a0, space
		syscall
		lw $a0, ($t4)
		syscall
		addi $t2, $t2, 1
		blt $t2, $t1, pa_loop

	la $a0, rbn
	syscall
	
	jr $ra
	
# EXPECTATIONS:
# $a0 : the antecedent address to a string
# $a1 : the consequent address to a string
# returns ($a0) < ($a1) as an int.
str_lt: # $t0 is x, $t1 is y
  move $t0, $a0
  move $t1, $a1
  
  sl_loop: # $t2 is *x, $t3 is *y
    lb $t2, 0($t0)
    lb $t3, 0($t1)
    beq $t2, $zero, sl_loop_end # if *x == '\0', exit loop
    beq $t3, $zero, sl_loop_end # if *y == '\0', exit loop
    blt $t2, $t3, return1  # if ( *x < *y ) return 1
    blt $t3, $t2, return0 # if ( *x < *y ) return 0
    addi $t0, $t0, 1
    addi $t1, $t1, 1
    b sl_loop
  sl_loop_end:
    beq $t3, $zero, return0 # if ( *y == '\0' ) return 0
    b return1 # return 1
  return0:
    li $v0, 0  # return 0
    b sl_end
  return1:
    li $v0, 1  # return 1
  sl_end:
    jr $ra
