""" 
Program name: two_balls_moving_4.py
Objective: Draw two balls moving toward each other.

Keywords: canvas, balls, time, movement, animation
============================================================================79
 
Explanation: Time control, together with progressive position changes are 
used to animate the motion of two different balls.
The point being made with this example is that the 'personality' of each
ball is defined by a set of parameters that are very similar for each ball.
It should be possible therefore todefine a general version of a ball and 
then make dozens or even thousands of clones each behaving independently. 
All this can be achieved through the use of classes - to be covered later.

Author:          Mike Ohlson de Fine

"""
# two_balls_moving_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
root = Tk()
root.title("Two balls")
cw = 200                                      # canvas width
ch = 130                                      # canvas height
                               
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 100 # time between new positions of the ball (milliseconds).
 
# The parameters defining ball no 1.
posn_x_1  =     1    # x position of box containing the ball (bottom). 
posn_y_1  =     1    # x position of box containing the ball (left edge). 
shift_x_1  =    1    # amount of x-movement each cycle of the 'for' loop.
shift_y_1  =    1    # amount of y-movement each cycle of the 'for' loop.
ball_width_1 =  12   # size of ball - width (x-dimension).
ball_height_1 = 12   # size of ball - height (y-dimension).
color_1 = "blue"     # color of the ball

# The parameters defining ball no 2.
posn_x_2  =     180   # x position of box containing the ball (bottom). 
posn_y_2  =     180   # x position of box containing the ball (left edge). 
shift_x_2  =    -2    # amount of x-movement each cycle of the 'for' loop.
shift_y_2  =    -2    # amount of y-movement each cycle of the 'for' loop.
ball_width_2 =  8     # size of ball - width (x-dimension).
ball_height_2 = 8     # size of ball - height (y-dimension).
color_2 = "green"     # color of the ball

for i in range(1,100):       # end the program after 50 position shifts. 
    posn_x_1 +=  shift_x_1
    posn_y_1 +=  shift_y_1
    posn_x_2 +=  shift_x_2
    posn_y_2 +=  shift_y_2

    chart_1.create_oval(posn_x_1, posn_y_1, posn_x_1 + ball_width_1,\
                        posn_y_1 + ball_height_1, fill=color_1)
    chart_1.create_oval(posn_x_2, posn_y_2, posn_x_2 + ball_width_2,\
                        posn_y_2 + ball_height_2, fill=color_2)
    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    chart_1.delete(ALL)           # This erases everything on the 

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
