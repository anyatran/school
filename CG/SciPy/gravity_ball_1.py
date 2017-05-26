""" 
Program name: gravityball_1.py
Objective: Draw a ball under the influence of gravity.

Keywords: canvas, ball, bounce, gravity, time, movement
============================================================================79
 
Explanation: A simplified equation of motion that includesed the influence 
of gravity is incorporated. Note that because of the conventions used 
for screen(canvas) reference positions the acceleration due to gravity
has a plus sign.
The fact that we are using integer arithmetic only, causes the ball to dissappear
off the bottom of the screen when the bounces become small. The collision
detection criteria is too coarse when integers are used. We should use floating 
point. However screen position coordinates are integer so care must be exercised.
"gravityball_2.py" tackles this issue.

Author:          Mike Ohlson de Fine

"""
# gravityball.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
root = Tk()
root.title("Gravity bounce")

cw = 220                                      # canvas width
ch = 200                                      # canvas height
GRAVITY = 4                               
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 30 # time between new positions of the ball (milliseconds).

# The parameters determining the dimensions of the ball and it's position.
posn_x  =     15     # x position of box containing the ball (bottom). 
posn_y  =     180    # x position of box containing the ball (left edge). 
shift_x  =    1      # amount of x-movement each cycle of the 'for' loop.
velocity_y  =  50    # amount of y-movement each cycle of the 'for' loop.
ball_width =  12     # size of ball - width (x-dimension).
ball_height = 12     # size of ball - height (y-dimension).
color = "blue"       # color of the ball

# Here is a function that detects collisions with the walls of the container
# and then reverses the direction of movement is a collision is detected.
def detectWallCollision():
    global posn_x, posn_y, shift_x, velocity_y, cw, cy    
    if posn_x > cw -ball_width:      # Collision with right-hand container wall.
	shift_x = -shift_x           # reverse direction.
    if posn_x <  ball_width:         # Collision with left-hand  wall. 
	shift_x = -shift_x
    if posn_y <  ball_height :       # Collision with ceiling. 
	velocity_y = -velocity_y
    if posn_y > ch - ball_height :   # Floor collision.
	velocity_y = -velocity_y

for i in range(1,800):       # end the program after 500 position shifts. 
    posn_x +=  shift_x
    velocity_y = velocity_y + GRAVITY  # a crude equation incorporating gravity.
    posn_y +=  velocity_y
    chart_1.create_oval(posn_x, posn_y, posn_x + ball_width, posn_y + ball_height, fill=color)
    detectWallCollision()         # Has the ball collided with any container wall?
    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    chart_1.delete(ALL)           # This erases everything on the 

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
