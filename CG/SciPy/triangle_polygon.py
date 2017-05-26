"""
Program name: triangle_polygon.py
Objective: The triangle is defined by the position of the three corners
or vertexes.

Keywords: canvas, polygon, triangle
============================================================================79
 
Explanation: Tkinter does not provide a separate triangle function.

Author:          Mike Ohlson de Fine
"""
# triangle_polygon.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('triangle')

cw = 160                                      # canvas width
ch = 80                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)                              

#                      point 1   point 2    point 3
canvas_1.create_polygon(140,30,   130,70,    10,50,   fill="red")
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
