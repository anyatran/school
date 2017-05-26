"""
Program name: dashed_line.py
Objective: Draw a straight dashed line on a canvas.

Keywords: canvas, line, , dashed line, default
============================================================================79
 
Explanation: When drawing lines you MUST specify the start and end points. 
Properties like colour and line width can also be specified, you don't
have to. default values will be used if none are explicitly stated.
Here variable names are used instead of numerical values. 
  
Author:          Mike Ohlson de Fine

"""
# dashed_line.py
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
y_end = 20
canvas_1.create_line(x_start, y_start, x_end,y_end, dash=(3,5), width = 3)

root.mainloop()# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
