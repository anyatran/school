"""
Program name: simple_line_1.py
Objective: To draw a short straight line on the screen. 
           To do this you need a module (library) called "Tkinter"
           Tkinter provides a canvas on which the line is drawn.

Keywords: line, canvas simple
============================================================================79
 
Explanation: To draw aline you only need to give the start point and the end point.
For all other properties like line thickness, colour etc.  default values are used. 
However should you want to change colour, thickness etc, you just do it by 
specifying the properties. See further examples to illustrate this. 

Author:          Mike Ohlson de Fine
"""
from Tkinter import *
root = Tk()
                              
canvas_1 = Canvas(root, width=300, height=200, background="#ffffff")

canvas_1.grid(row=0, column=0)
canvas_1.create_line(10,20 ,   50,70)

root.mainloop()

