""" 
Program name: floating_point_collisions_1.py
Objective: Two balls moving under the influence of gravity.

Keywords: canvas, ball, bounce, gravity, time, floating point, movement
============================================================================79
 
Explanation: A simplified equation of motion that inclused the influence 
of gravity in incorporated. Note that because of the conventions used 
for screen(canvas) reference positions the acceleration due to gravity
has a plus sign.
There is a numerical loss of 'energy' each time the ball hits a barrier

We need to be able to scale time to control the size of time
quantum for the differenc equations governing the motion.

Author:          Mike Ohlson de Fine

"""
# floating_point_collisions_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
root = Tk()
root.title("Collisions with Floating point")
cw = 350                                      # canvas width
ch = 200                                      # canvas height

GRAVITY = 1.5                             
chart_1 = Canvas(root, width=cw, height=ch, background="black")
chart_1.grid(row=0, column=0)

cycle_period = 80   # Time between new positions of the ball (milliseconds).
time_scaling = 0.2  # This governs the size of the differential steps
                    # when calculating changes in position.

# The parameters determining the dimensions of the ball and it's position.
ball_1 = {'posn_x':25.0,     # x position of box containing the ball (bottom). 
          'posn_y':180.0,    # x position of box containing the ball (left edge). 
          'velocity_x':30.0,  # amount of x-movement each cycle of the 'for' loop.
          'velocity_y':100.0,    # amount of y-movement each cycle of the 'for' loop.
          'ball_width':20.0,   # size of ball - width (x-dimension).
          'ball_height':20.0,   # size of ball - height (y-dimension).
          'color':"dark orange",   # color of the ball
          'coef_restitution':0.90}   # proportion of elastic enrgy recovered each bounce

ball_2 = {'posn_x':cw - 25.0,     # x position of box containing the ball (bottom). 
          'posn_y':300.0,    # x position of box containing the ball (left edge). 
          'velocity_x':-50.0,  # amount of x-movement each cycle of the 'for' loop.
          'velocity_y':150.0,    # amount of y-movement each cycle of the 'for' loop.
          'ball_width':30.0,   # size of ball - width (x-dimension).
          'ball_height':30.0,   # size of ball - height (y-dimension).
          'color':"yellow3",   # color of the ball
          'coef_restitution':0.90}   # proportion of elastic enrgy recovered each bounce

def detectWallCollision(ball):
    # Collision detection with the walls of the container  
    if ball['posn_x'] > cw -  ball['ball_width']:       # Collision with right-hand wall.
	 ball['velocity_x'] = -ball['velocity_x'] * ball['coef_restitution']   # reverse direction.
         ball['posn_x'] = cw -  ball['ball_width'] 
    if  ball['posn_x'] <  1:            # Collision with left-hand  wall. 
	 ball['velocity_x'] = -ball['velocity_x'] *  ball['coef_restitution']
         ball['posn_x'] = 2      # anti-stick to the wall
    if  ball['posn_y'] <   ball['ball_height'] :            # Collision with ceiling. 
	 ball['velocity_y'] = -ball['velocity_y'] *  ball['coef_restitution']
         ball['posn_y'] = ball['ball_height']  
    if  ball['posn_y'] > ch - ball['ball_height']:            # Floor collision.  
	 ball['velocity_y'] = - ball['velocity_y'] *  ball['coef_restitution']
         ball['posn_y'] = ch -  ball['ball_height'] 
    
def diffEquation(ball):
     # An approximate set of differential equations of motion for the balls
     ball['posn_x'] +=   ball['velocity_x'] * time_scaling
     ball['velocity_y'] =  ball['velocity_y'] + GRAVITY  # a crude equation incorporating gravity.
     ball['posn_y'] +=  ball['velocity_y'] * time_scaling
     chart_1.create_oval( ball['posn_x'],  ball['posn_y'],  ball['posn_x'] +  ball['ball_width'],\
                        ball ['posn_y'] +  ball['ball_height'], fill= ball['color'])
     detectWallCollision(ball)         # Has the ball collided with any container wall?

for i in range(1,2000):       # end the program after 1000 position shifts. 
    
    diffEquation(ball_1)
    diffEquation(ball_2)

    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    chart_1.delete(ALL)           # This erases everything on the 

root.mainloop()

