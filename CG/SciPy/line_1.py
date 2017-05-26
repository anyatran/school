"""
Program name: line_1.py
Objective: Draw a straight line on a canvas.

Keywords: canvas, line, , dashed line, default
============================================================================79
 
Explanation: When drawing lines you MUST specify the start and end points. 
Properties like colour and line width can also be specified, but you don't
have to. Default values will be used if none are explicitly stated.
Here variable names are used instead of numerical values. 

Author:          Mike Ohlson de Fine

"""
# line_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()

cw = 800                                      # canvas width
ch = 200                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch)
canvas_1.grid(row=0, column=1)

x_start = 10
y_start = 10
x_end = 500
y_end = 100
canvas_1.create_line(x_start, y_start, x_end,y_end)

root.mainloop()# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
