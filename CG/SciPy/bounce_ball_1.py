""" 
Program name: bouncer_1.py
Objective: Draw a ball that rebounds off the walls.

Keywords: canvas, ball, bounce, time, movement
============================================================================79
 
Explanation: Collision detection is introduced.

Author:          Mike Ohlson de Fine 

"""
# bounce_ball.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
root = Tk()
root.title("The bouncer")
cw = 200                                      # canvas width
ch = 120                                      # canvas height                               
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 50 # time between new positions of the ball (milliseconds).
 
# The parameters determining the dimensions of the ball and it's position.
posn_x  =     1    # x position of box containing the ball (bottom). 
posn_y  =     1    # x position of box containing the ball (left edge). 
shift_x  =    1    # amount of x-movement each cycle of the 'for' loop.
shift_y  =    1    # amount of y-movement each cycle of the 'for' loop.
ball_width =  12   # size of ball - width (x-dimension).
ball_height = 12   # size of ball - height (y-dimension).
color = "firebrick"   # color of the ball

# Here is a function that detects collisions with the walls of the container
# and then reverses the direction of movement is a collision is detected.
def detectWallCollision():
    global posn_x, posn_y, shift_x, shift_y, cw, cy    
    if posn_x > cw :            # Collision with right-hand container wall.
	shift_x = -shift_x      # reverse direction.
    if posn_x <  0 :            # Collision with left-hand  wall. 
	shift_x = -shift_x
    if posn_y > ch  :           # Collision with floor.
	shift_y = -shift_y
    if posn_y <  0 :            # Collision with ceiling. 
	shift_y = -shift_y

for i in range(1,1000):       # end the program after 500 position shifts. 
    posn_x +=  shift_x
    posn_y +=  shift_y
    
    chart_1.create_oval(posn_x, posn_y, posn_x + ball_width,\
                        posn_y + ball_height, fill=color)
    detectWallCollision()         # Has the ball collided with any container wall?
    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    chart_1.delete(ALL)           # This erases everything on the 

root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.>>>>>>
