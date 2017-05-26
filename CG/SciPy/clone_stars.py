"""
Program name: clone_stars.py
Objective: This star is cloned, re-sized and moved.

Keywords: canvas, polygon, star, clone, resize
============================================================================79
 
Explanation: The use of a variable anchor point to draw an object allows
you to shift and re-draw at any location. A size control (re-sizing) variable
allows the clones to be set any size in proportion to the original.

Author:          Mike Ohlson de Fine
"""
# clone_stars.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Translation and resize')
cw = 600                                      # canvas width
ch = 200                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)                              

# blue star, anchored to an anchor point.
x_anchor = 15
y_anchor = 150
size_scaling = 1

canvas_1.create_polygon(x_anchor,        y_anchor,\
                        x_anchor + 20 * size_scaling,   y_anchor - 40* size_scaling,\
                        x_anchor + 30 * size_scaling,   y_anchor + 10* size_scaling,\
                        x_anchor,        y_anchor - 30* size_scaling,\
                        x_anchor + 40 * size_scaling,   y_anchor - 20* size_scaling,\
                           fill="green")                 
size_scaling = 2
x_anchor = 80
y_anchor = 120
canvas_1.create_polygon(x_anchor,        y_anchor,\
                        x_anchor + 20 * size_scaling,   y_anchor - 40* size_scaling,\
                        x_anchor + 30 * size_scaling,   y_anchor + 10* size_scaling,\
                        x_anchor,        y_anchor - 30* size_scaling,\
                        x_anchor + 40 * size_scaling,   y_anchor - 20* size_scaling,\
                           fill="darkgreen")                 
size_scaling = 3
x_anchor = 160
y_anchor = 110
canvas_1.create_polygon(x_anchor,        y_anchor,\
                        x_anchor + 20 * size_scaling,   y_anchor - 40* size_scaling,\
                        x_anchor + 30 * size_scaling,   y_anchor + 10* size_scaling,\
                        x_anchor,        y_anchor - 30* size_scaling,\
                        x_anchor + 40 * size_scaling,   y_anchor - 20* size_scaling,\
                           fill="lightgreen")   
size_scaling = 4
x_anchor = 300
y_anchor = 110
canvas_1.create_polygon(x_anchor,        y_anchor,\
                        x_anchor + 20 * size_scaling,   y_anchor - 40* size_scaling,\
                        x_anchor + 30 * size_scaling,   y_anchor + 10* size_scaling,\
                   x_anchor,        y_anchor - 30* size_scaling,\
                        x_anchor + 40 * size_scaling,   y_anchor - 20* size_scaling,\
                           fill="forestgreen")
root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
