"""
Program name: 3rectangles.py
Objective: A set of overlapping rectangles.

Keywords: canvas, rectangles
============================================================================79
 
Explanation: overlapping rectangle are placed down on the canvas in the
same sequence that they appear in the code.

Author:          Mike Ohlson de Fine

"""
# 3rectangles.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Overlapping rectangles')

cw = 240                                      # canvas width
ch = 180                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="turquoise")
canvas_1.grid(row=0, column=1)

# dark blue rectangle - painted first therefore at the bottom
x_start = 10
y_start = 30
x_width =70
y_height = 90
kula ="darkblue"
canvas_1.create_rectangle( x_start,  y_start,\
                           x_start + x_width, y_start + y_height,\
                           fill=kula) 

#dark red rectangle - second therefore in the middle
x_start = 30
y_start = 50
kula ="darkred"
canvas_1.create_rectangle( x_start,  y_start,\
                           x_start + x_width, y_start + y_height,\
                           fill=kula)

#dark green rectangle - painted last therefore on top of previous ones.
x_start = 50
y_start = 70
kula ="darkgreen"
canvas_1.create_rectangle( x_start,  y_start,\
                           x_start + x_width, y_start + y_height,\
                           fill=kula)
root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
