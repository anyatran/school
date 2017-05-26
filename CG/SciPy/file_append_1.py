"""
Program name: file_append_1.py
Objective:Write multiple lines to a file.

Keywords: file write, append, create, open
============================================================================79
 
Explanation: NOTE: Once there is data in a file you can add new data onto 
the end ("a"=append)  using FILE = open(filename,"a")

Author:          Mike Ohlson de Fine

"""
# file_append_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Open an existing file and add (append) data to it.
filename_1 = "/constr/test_write_1.dat"
filly = open(filename_1,"a")           # Open a file in append mode
filly.write("\n")
filly.write("This is number four and he has reached the door")

for i in range(0,5):
    filename_2 = "/constr/test_write_2.dat"
    filly = open(filename_2,"a")           # Create a file in append mode
    filly.write("This is number five and the cat is still alive")

filename_3 = "/constr/test_write_2.dat"
filly = open(filename_3,"w")           # Create a file in write mode

# The command below overwrites previous data - "w" is really "overwrite"
filly.write("This is number six and they cannot find the fix")
    


