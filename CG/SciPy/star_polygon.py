"""
Program name: star_polygon.py
Objective: This star is defined by the position of the five corners
or vertexes.

Keywords: canvas, polygon, star
============================================================================79
 
Explanation: Tkinter does not provide a separate star function.

Author:          Mike Ohlson de Fine
"""
# star_polygon.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

from Tkinter import *
root = Tk()
root.title('Star Polygon ')

cw = 140                                      # canvas width
ch = 80                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)                              

# blue star, anchored to an anchor point.
x_anchor = 15
y_anchor = 50

canvas_1.create_polygon(x_anchor,       y_anchor,\
                        x_anchor + 20,  y_anchor - 40,\
                        x_anchor + 30,  y_anchor + 10,\
                        x_anchor,       y_anchor - 30,\
                        x_anchor + 40,  y_anchor - 20,\
                        fill="blue") 
root.mainloop()               
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
