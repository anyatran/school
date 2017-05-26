.globl main
.data
  x: .word 0
  str: .word str_data
  str_data: .asciiz "Hello World!"
.text
main:
	# The C program refers only to str, not to str_data
	# So, this verion will use only str, and not str_data, in the code.
	la $t0, str     # Load address of str (It's value will point to string)
	lw $a0, 0($t0)  # Given address in $t0, load its value into $a0
	# Before, we saved $ra in $s0;  That wouldn't work if the program
	#   were recursive.  So, now, we demonstrate saving on stack
	addi $sp, $sp, -4 # Extend stack;  Use -4 because stack grows downwards
	sw $ra, 0($sp)  # Save $ra on stack
	jal print_mystring  # Call print_mystring(str)
	lw $ra, 0($sp)  # Restore $ra from stack
	addi $sp,$sp, 4 # Contract stack to where it used to be
	# $v0 is return value
	sw $v0, x($zero) # $v0 is return value; save in variable x
	jr $ra
    
print_mystring:
	# argument to print_str syscall is already in $a0
	li $v0, 4  # The print_str system call is number 4 in the table.
	syscall
	li $v0, 0  # 0 means success; Pass return value in $v0
	jr $ra
