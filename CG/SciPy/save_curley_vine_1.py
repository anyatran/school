"""
Program name: save_curly_vine_1.py
Objective: Store the vine data points in two formats.

Keywords: canvas, data storage, vine
============================================================================79
 
Explanation: The first method stores a point per line interleaving
x and y points alternately. The second method stores the lists as found,
square brackets included - so it should be possible to read them back
as found directly into the arrays.

Author:          Mike Ohlson de Fine

"""
# save_curly_vine_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
vine_x = [23, 20,  11,  9, 29, 52, 56, 39, 24, 32, 53,  69,  63,  47,  35,  35, 51,\
  82, 116, 130, 95, 67, 95, 114, 95, 78, 95, 103, 95, 85, 95, 94.5]

vine_y = [36, 44, 39, 22, 16, 32, 56, 72, 91, 117,125, 138, 150, 151, 140, 123, 107,\
 92,  70,  41,  5, 41, 66,  41, 24, 41, 53,  41, 33, 41, 41, 39]

vine_1 = open('/constr/vector_shapes/curley_vine_1.txt', 'w')

vine_1.write(str(vine_x ))
vine_1.write("\n")
vine_1.write(str(vine_y ))


