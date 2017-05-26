""" 
Program name: timed_moveball_1.py
Objective: Draw a ball in  a series of positions.

Keywords: canvas, ball, time, movement
============================================================================79
 
Explanation: Time control is introduced to produce crude animated movement.
The ball is drawn into a sequence of positions with a pause of 200 milliseconds
between each new image of the ball.
For proper animation you need to erase the previous image of the ball so that
only one image ata time can be seen. See "moveball_3.py". 

Author:          Mike Ohlson de Fine

"""
# timed_moveball_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
root = Tk()
root.title("Time delayed ball drawing")

cw = 300                                      # canvas width
ch = 130                                      # canvas height                               
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 200 # time between fresh positions of the ball (milliseconds).
 
# The parameters determining the dimensions of the ball and it's position.
posn_x  =     1    # x position of box containing the ball (bottom). 
posn_y  =     1    # x position of box containing the ball (left edge). 
shift_x  =    3    # amount of x-movement each cycle of the 'for' loop.
shift_y  =    3    # amount of y-movement each cycle of the 'for' loop.
ball_width =  12   # size of ball - width (x-dimension).
ball_height = 12   # size of ball - height (y-dimension).
color = "purple"   # color of the ball


for i in range(1,50):       # end the program after 50 position shifts. 
    posn_x +=  shift_x
    posn_y +=  shift_y

    chart_1.create_oval(posn_x, posn_y, posn_x + ball_width,\
                        posn_y + ball_height, fill=color)
    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
