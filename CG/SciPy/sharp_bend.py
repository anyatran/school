"""
Program name: sharp_bend.py
Objective: Draw a line with a sharp bend on a canvas.

Keywords: canvas, bent line
============================================================================79
 
Explanation: When drawing lines with many segments you specify location of 
each segment. The end of one segment is the start of the next. 

Author:          Mike Ohlson de Fine

"""
#sharp_bend.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Sharp bend')
cw = 200                                      # canvas width
ch = 100                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)

x1 = 50
y1 = 10
x2 = 20
y2 = 80
x3 = 150
y3 = 60

canvas_1.create_line(x1,y1,  x2,y2,  x3,y3)

root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
