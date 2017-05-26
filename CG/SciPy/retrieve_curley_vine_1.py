"""
Program name: retrieve_curly_vine_1.py
Objective: Retrieve the vine data points in their original formats, 
which was as a Python list of integers and floats.

Keywords: canvas, data retrieval, eval(), floating point list, vine
============================================================================79
 
Explanation: The secret is the use of the "eval" built-in function.

Author:          Mike Ohlson de Fine

"""
# retrieve_curly_vine_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#vine_x = []
vine_1 = open('/constr/vector_shapes/curley_vine_1.txt', 'r')

vine_x = eval(vine_1.readline())
vine_y = eval(vine_1.readline())

# Tests to confirm that everything worked.
print "vine_x = ",vine_x
print vine_x[31]
print "vine_y = ",vine_y
print vine_y[6]


