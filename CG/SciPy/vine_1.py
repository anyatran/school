"""
Program name: vine_1.py
Objective: A curley vine.

Keywords: canvas, bent line, angle, vine
============================================================================79
 
Explanation: The trick of merging points.

Author:          Mike Ohlson de Fine

"""
#vine_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Curley vine ')
cw = 180                                      # canvas width.
ch = 160                                      # canvas height.
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)

# The curley vine oordinates as measured from a paper sketch.
# points  0    1    2   3   4   5   6  7    8  9   10   11   12   13   14   15  16 
vine_x = [23, 20,  11,  9, 29, 52, 56, 39, 24, 32, 53,  69,  63,  47,  35,  35, 51,\
  82, 116, 130, 95, 67, 95, 114, 95, 78, 95, 103, 95, 85, 95, 94.5]
# 17   18   19  20  21  22   23  24  25  26   27  28  29  30  31

vine_y = [36, 44, 39, 22, 16, 32, 56, 72, 91, 117,125, 138, 150, 151, 140, 123, 107,\
 92,  70,  41,  5, 41, 66,  41, 24, 41, 53,  41, 33, 41, 41, 39]

#====================================================================
# The merging of the separate x znd y lists into a single sequence.
#====================================================================
Q =  []
# Reference copies of the original vine lists - keep the originals intact
X = vine_x[0:]
Y = vine_y[0:]

# Name the compact, merged x & y list Q 
# Merge (alternate interleaves of x and y) into a single polygon of points.
for i in range(0,len(X)):
    Q.append(X[i])    # append the x coordinate
    Q.append(Y[i])    # then the y - so they alternate and you end with a Tkinter polygon.

canvas_1.create_line(Q, smooth='true')

root.mainloop()
