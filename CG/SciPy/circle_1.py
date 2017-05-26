"""
Program name: circle_1.py
Objective: A circle is a special case of an oval.

Keywords: canvas, oval, circle
============================================================================79
 
Explanation: A circle is a special case of an oval and is defined by the
box it fits inside. The bounding box is specified the same as rectangles,
from bottom-left to top-right.

Author:          Mike Ohlson de Fine

"""
# circle_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('A circle')

cw = 150                                      # canvas width
ch = 140                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)                              

# specify bottom-left and top-right as a set of four numbers named 'xy'
xy = 20, 20, 120, 120         

canvas_1.create_oval(xy)
root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
