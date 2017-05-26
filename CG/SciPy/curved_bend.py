"""
Program name: curved_bend.py
Objective: Draw a line with a smoothly curved bend on a canvas.

Keywords: canvas, curved line
============================================================================79
 
Explanation: When drawing lines with many segments you specify location of 
each segment. The end of one segment is the start of the next. 
The specification smooth="true" applies Bezier spline type smoothing
to the lines. The ends of the curve are tangential to the ends of the 
straight lines.

Author:          Mike Ohlson de Fine

"""
#curved_bend.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Curved bend')
cw = 220                                      # canvas width
ch = 200                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)

x1 = 50
y1 = 10
x2 = 50
y2 = 180
x3 = 180
y3 = 180

canvas_1.create_line(x1,y1,  x2,y2,  x3,y3, smooth="true") 
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
