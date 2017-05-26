""" 
Program name: moveball_1.py
Objective: Draw a ball in  a series of positions.

Keywords: canvas, ball, move, shift
============================================================================79
 
Explanation: Preliminary to making animated movement you shift a ball into
a sequence of positions on a canvas. 

Author:          Mike Ohlson de Fine

"""
# moveball_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("shifted sequence")

cw = 250                                      # canvas width
ch = 130                                      # canvas height
                               
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)
 
# The parameters determining the dimensions of the ball and it's position.
posn_x  =     1    # x position of box containing the ball (bottom) 
posn_y  =     1    # x position of box containing the ball (left edge) 
shift_x  =    3    # amount of x-movement each cycle of the 'for' loop
shift_y  =    2    # amount of y-movement each cycle of the 'for' loop
ball_width =  12   # size of ball - width (x-dimension)
ball_height = 12   # size of ball - height (y-dimension)
color = "violet"   # color of the ball

for i in range(1,50):       # end the program after 50 position shifts 
    posn_x +=  shift_x
    posn_y +=  shift_y
    chart_1.create_oval(posn_x, posn_y, posn_x + ball_width, posn_y + ball_height, fill=color)

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

