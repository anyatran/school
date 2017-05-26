"""
Program name: file_write_1.py
Objective:Write multiple lines to a file.

Keywords: file write, create, open
============================================================================79
 
Explanation: Write something coherent to a file so that it can be verified.

Author:          Mike Ohlson de Fine

   NOTE: Once there is data in a file you can add new data onto the end ("a"=append)
   using FILE = open(filename,"a")  
"""
# file_write_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Let's create a file and write it to disk.
filename = "/constr/test_write_1.dat"
filly = open(filename,"w")           # Create a file object, in write mode

for i in range(0,2):
    filly.write("everything inside quotes is a string even 3.1457")
    filly.writelines("\n")
    filly.write("How will stored data be delimited so we can read chunks of it into elements of list, tuple or dictionart?")
    filly.writelines("\n")
    
#filly.close()

