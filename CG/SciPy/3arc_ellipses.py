"""
Program name: 3arc_ellipses.py
Objective: Three pie-slice elliptical shapes.

Keywords: canvas, arc, ellipse
============================================================================79
 
Explanation: This the correct use of the create_arc(...) method.

Author:          Mike Ohlson de Fine
"""
# 3arc_ellipses.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('3 arc ellipses')

cw = 180                                      # canvas width
ch = 180                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="grey")
canvas_1.grid(row=0, column=1)                              

xy_1 = 20,   80,  80, 20
xy_2 = 20,  130,  80, 100
xy_3 = 100, 130, 140, 20

canvas_1.create_arc(xy_1, start=20, extent=270, fill="red")
canvas_1.create_arc(xy_2, start=-50, extent=290, fill="cyan")
canvas_1.create_arc(xy_3, start=190, extent=-290, fill="deep sky blue")
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
