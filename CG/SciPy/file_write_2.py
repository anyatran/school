"""
Program name: file_write_2.py
Objective:Write multiple lines to a file.

Keywords: file write, create, open
============================================================================79
 
Explanation: Write something coherent to a file so that it can be verified.

Author:          Mike Ohlson de Fine

NOTE: Once there is data in a file you can add new data onto the end ("a"=append)
   using FILE = open(filename,"a")  
"""
# file_write_2.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Let's create a file and write it to disk.
filename_1 = "/constr/test_write_1.dat"
filly = open(filename_1,"w")           # Create a file object, in write mode
filly.write("This is number one and the fun has just begun")

filename_2 = "/constr/test_write_2.dat"
filly = open(filename_2,"w")           # Create a file object, in write mode
filly.write("This is number two and he has lost his shoe")

filename_3 = "/constr/test_write_3.dat"
filly = open(filename_3,"w")           # Create a file object, in write mode
filly.write("This is number three and a bump is on his knee")
    
#filly.close()

