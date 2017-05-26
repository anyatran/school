"""
Program name: rectangle.py
Objective: A rectangle.

Keywords: canvas, rectangle, angle, vine
============================================================================79
 
Explanation: A rectangle is specified by two points:
Bottom-left is x_start, y_start.

Author:          Mike Ohlson de Fine

"""
# rectangle.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('A Rectangle')

cw = 200                                      # canvas width
ch = 130                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)

x_start = 10
y_start = 30
x_width =70
y_height = 90
kula ="darkblue"
canvas_1.create_rectangle( x_start,           y_start,\
                           x_start + x_width, y_start + y_height, fill=kula) 
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
