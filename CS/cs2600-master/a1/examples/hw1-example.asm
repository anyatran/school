.globl main
.data
  x: .word 0
  str: .word str_data
  str_data: .asciiz "Hello World!"
.text
main:
	la $a0, str_data
	move $s0, $ra # Operating system put return addr of main in $ra
		      # Save it, or jal print_mystring will step on it
	jal print_mystring  # Call print_mystring(str)
	move $ra, $s0 # Restore the $ra that was put there by operating system
        sw $v0, x($zero) # $v0 is return value; save in variable x
	jr $ra	      # Equivalent to "return" statement in main.
    
print_mystring:
	# argument to print_str syscall already in $a0; Else do:  move $a0, ...
	li $v0, 4  # The print_str system call is number 4 in the table.
	syscall
	li $v0, 0  # 0 means success; Pass return value in $v0
	jr $ra
