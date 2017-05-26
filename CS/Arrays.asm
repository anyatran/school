#int x[4] => , or 4 * 4. x is a pointer because it shows where the array starts
	x: .space 16 

#int  x[4] = {1, 5, 6, 29}
	.align 2 # 4-byte boundry 2^2
	x: .word 1, 5, 6, 29 #adding to the array

#new C type fits 32 chars -> Name x[16] = { "Joe", "Jane"...}
	.align 5 # because 2^5 = 32
	x: .word "Joe", "Jane" ...
