.globl main
.data  
    bracket: .asciiz "[ "
    initarray: .asciiz "Initial array is:\n"
    bracket_end: " ]\n"
    space: ", "
    
    data:
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
    size: .word 16
    
    pointers: .space 64 #16*4. declare space in advance to use later
    
.text
main:
    la $a0, initarray #initial array is:
    li $v0, 4
    syscall
    
    jal print
    
    
    

print:
    
    la $a0, bracket #"["
    li $v0, 4
    syscall
    
    
    move $s0, $zero # i = 0
    la $s1, data # load array or pointers
    lw $s2, size #load size
    
    jal print_array
    
    la $a0, bracket_end #" ]"
    li $v0, 4
    syscall
    
    li $v0, 10 # exit
    syscall
    
print_array:
    bge $s0, $s2, exit # i >= size -> exit
    
    la $a0, ($s1) #load the string into a0
    li $v0, 4 #print string
    syscall
    
    la $a0, space
    li $v0, 4
    syscall
    
    addi $s0, $s0, 1 # i++
    addi $s1, $s1, 32 #move pointer
    
    j print_array
    
exit:
    jr $ra
  

