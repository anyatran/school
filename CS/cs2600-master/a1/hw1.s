# $s0 is the input
# $s1 is the result

.globl main
.data
  prompt:  .asciiz "Positive integer: "
  desc1:   .asciiz "The value of 'factorial("
  desc2:   .asciiz ")' is:  "
  newline: .asciiz "\n"
.text
  # function declaration:
  # -- int main() { --
  main:
    # print a prompt
    # -- printf("Positive integer: "); --
    la $a0, prompt
    jal print_string

    # read an int from the console and save it to `x`
    # -- scanf("%d", &x); --
    li $v0, 5
    syscall
    move $s0, $v0  # save the input to $s0 for later use.

    # call factorial on the input
    move $a0, $s0
    jal factorial
    move $s1, $v0  # save the result

    # Print the output
    # printf("The value of 'factorial(%d)' is:  %d\n",
    #    number, factorial(number));
    la $a0, desc1
    jal print_string
    move $a0, $s0
    jal print_num
    la $a0, desc2
    jal print_string
    move $a0, $s1  # the result of factorial ( $s0 )
    jal print_num
    la $a0, newline
    jal print_string

    li $v0, 10
    syscall

  print_string:
    li $v0, 4
    syscall
    li $v0, 0
    jr $ra

  print_num:
    li $v0, 1
    syscall
    li $v0, 0
    jr $ra

  # function declaration:
  # -- int factorial(int x) { --
  factorial:
    li $v0, 1 # inital return value
    beq $a0, 0, return
    addi $sp, $sp, -4  # save $ra to stack, and extend
    sw $ra, 0($sp)

    addi $sp, $sp, -4  # save $a0 to stack, and extend
    sw $a0, 0($sp)

    addi $a0, $a0, -1
    jal factorial

    lw $a0, 0($sp)  # get $a0 back from stack
    addi $sp, $sp, 4 # make stack smaller

    mul $v0, $a0, $v0    # x * factorial(x-1);

    lw $ra, 0($sp)  # get $ra back from stack
    addi $sp,$sp, 4 # make stack smaller

    return: jr $ra  # return the value