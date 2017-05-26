"""
Program name: shifted_star.py
Objective: This star is cloned and moved.

Keywords: canvas, polygon, offset shift, star, clone
============================================================================79
 
Explanation: The use of a variable anchor point to draw an object allows
you to shift and re-draw at any location.

Author:          Mike Ohlson de Fine
"""
# shifted_star.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Repeat and shift')

cw = 200                                      # canvas width
ch = 100                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)                              

# blue star, anchored to an anchor point.
x_anchor = 15
y_anchor = 50

canvas_1.create_polygon(x_anchor,        y_anchor,\
                        x_anchor + 20,   y_anchor - 40,\
                        x_anchor + 30,   y_anchor + 10,\
                        x_anchor,        y_anchor - 30,\
                        x_anchor + 40,   y_anchor - 20,\
                           fill="green")                 

x_anchor = 60
y_anchor = 80
canvas_1.create_polygon(x_anchor,        y_anchor,\
                        x_anchor + 20,   y_anchor - 40,\
                        x_anchor + 30,   y_anchor + 10,\
                        x_anchor,        y_anchor - 30,\
                        x_anchor + 40,   y_anchor - 20,\
                           fill="red")                 
x_anchor = 120
y_anchor = 60
canvas_1.create_polygon(x_anchor,        y_anchor,\
                        x_anchor + 20,   y_anchor - 40,\
                        x_anchor + 30,   y_anchor + 10,\
                        x_anchor,        y_anchor - 30,\
                        x_anchor + 40,   y_anchor - 20,\
                           fill="blue")  
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
