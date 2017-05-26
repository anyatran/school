"""
Program name: kinetic_gravity_balls_1 .py
Objective: Two balls bouncing off each other and the walls and leaving a trail
showing the trajectory of each ball.

Keywords: ball, bounce, gravity, time, movement, mutual impact
============================================================================79
recipe # 8
Explanation: To make the animation depiction work you need to erase the balls
in their old position immediately before re-creating them in their new
positions. The "chart_1.delete("ball_tag")" method achieves this by deleting
each ball in turn. The value in referring to the balls using a shared tag name 
when deleting them is that you can selectively delete the balls but leave the
trajectory lines untouched.
All that remains is to approximate conservation of momentum when collisions
occur and you have a rough "Kinetic Theory of Gasses" simulation.

Author:          Mike Ohlson de Fine

"""
# kinetic_gravity_balls_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
import math
root = Tk()
root.title("Balls bounce off each other")
cw = 300                                      # canvas width
ch = 200                                      # canvas height

GRAVITY = 1.5                             
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 80   # Time between new positions of the ball (milliseconds).
time_scaling = 0.2  # The size of the differential steps

# The parameters determining the dimensions of the ball and it's position.
ball_1 = {'posn_x':25.0,    
           'posn_y':25.0,   
           'velocity_x':65.0, 
           'velocity_y':50.0,   
           'ball_width':20.0,   
           'ball_height':20.0,   
           'color':"SlateBlue1",   
           'coef_restitution':0.90}   

ball_2 = {'posn_x':180.0,     
           'posn_y':ch- 25.0,    
           'velocity_x':-50.0,  
           'velocity_y':-70.0,    
           'ball_width':30.0,   
           'ball_height':30.0,   
           'color':"maroon1",   
           'coef_restitution':0.90}  

def detectWallCollision(ball):
    # detect ball-to-wall collision
    if ball['posn_x'] > cw -  ball['ball_width']:       #  Right-hand wall.
	 ball['velocity_x'] = -ball['velocity_x'] *  ball['coef_restitution']  
         ball['posn_x'] = cw -  ball['ball_width']     
    if  ball['posn_x'] <  1:                            # Left-hand  wall. 
	 ball['velocity_x'] = -ball['velocity_x'] *  ball['coef_restitution']
         ball['posn_x'] = 2     
    if  ball['posn_y'] <   ball['ball_height'] :           #  Ceiling. 
	 ball['velocity_y'] = -ball['velocity_y'] *  ball['coef_restitution']
         ball['posn_y'] = ball['ball_height']  
    if  ball['posn_y'] > ch - ball['ball_height'] :  # Floor 
	 ball['velocity_y'] = - ball['velocity_y'] *  ball['coef_restitution']
         ball['posn_y'] = ch -  ball['ball_height']                

def detectBallCollision(ball_1, ball_2):
    #detect ball-to-ball collision
    # firstly: is there a close approach in the horizontal direction
    if math.fabs(ball_1['posn_x'] - ball_2['posn_x']) < 25:
        # secondly: is there also a close approach in the horizontal direction
        if math.fabs(ball_1['posn_y'] - ball_2['posn_y']) < 25:
            ball_1['velocity_x'] = -ball_1['velocity_x'] # reverse direction.
            ball_1['velocity_y'] = -ball_1['velocity_y'] 
            ball_2['velocity_x'] = -ball_2['velocity_x'] 
            ball_2['velocity_y'] = -ball_2['velocity_y'] 
            # to avoid internal rebounding inside balls
            ball_1['posn_x'] +=   ball_1['velocity_x'] * time_scaling
            ball_1['posn_y'] +=   ball_1['velocity_y'] * time_scaling
            ball_2['posn_x'] +=   ball_2['velocity_x'] * time_scaling
            ball_2['posn_y'] +=   ball_2['velocity_y'] * time_scaling

def diffEquation(ball):
     x_old =  ball['posn_x']
     y_old =  ball['posn_y']
     ball['posn_x'] +=   ball['velocity_x'] * time_scaling
     ball['velocity_y'] =  ball['velocity_y'] + GRAVITY  
     ball['posn_y'] +=  ball['velocity_y'] * time_scaling
     chart_1.create_oval( ball['posn_x'],  ball['posn_y'],\
                          ball['posn_x'] +  ball['ball_width'],\
                          ball['posn_y'] +  ball['ball_height'],\
                          fill= ball['color'], tags="ball_tag")
     chart_1.create_line( x_old,  y_old,  ball['posn_x'], ball ['posn_y'],\
                          fill= ball['color'])
     detectWallCollision(ball)         # Has the ball collided with any container wall?

for i in range(1,5000):      
    diffEquation(ball_1)
    diffEquation(ball_2)
    detectBallCollision(ball_1, ball_2)
    chart_1.update()              
    chart_1.after(cycle_period)   
    chart_1.delete("ball_tag")    # Erase the balls but leave the trajectories
root.mainloop()

