"""
Program name: walking_toff_1.py
Objective: A walking high ranker with winklepicker shoes.

Keywords: canvas, man, walk, shoes, time, backdrop, gif, movement
============================================================================79
 
Explanation: The number of frames in the stride for one foot is the number 
of elements in step_x (=len(step_x). The numer of steps in the hip motion 
is twice as many (a sequence of hip positions for each of two feet).
So the 'for loops' are defined in terms of len(step) - adaptable therefore.]
gifs suppor a transparent background. This allows good mixing of raster 
and vector images.
 
The mammal knee bend - forewards.

Author:          Mike Ohlson de Fine

"""
# walking_toff_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import math
import time
root = Tk()
root.title("A Walking Toff in Natural Habitat - gif images")
cw = 800                                      # canvas width
ch = 200                                      # canvas height
#GRAVITY = 4                               
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 120 # time between new positions of the ball (milliseconds).
im_backdrop = "/constr/pics1/toff_bg.gif"
im_toff = "/constr/pics1/ambassador.gif"
im_shoe = "/constr/pics1/toff_shoe.gif"
toff =PhotoImage(file= im_toff)
shoey =PhotoImage(file= im_shoe)
backdrop = PhotoImage(file= im_backdrop)
chart_1.create_image(0 ,0 ,anchor=NW, image=backdrop) 
base_x = 20
base_y = 190
hip_h = 60
thy = 25
#=======================================================================
# Hip positions: Nhip = 2 x Nstep, the number of steps per foot per stride.
hip_x = [0, 5, 10,   15, 20, 25,   30, 35, 40,   45, 50, 55,   60, 60, 60] #15
hip_y = [0, 4, 6,   8, 6,  4,    0,  0,  0,    4, 6, 8,   6,  4,  0] #15

step_x = [0, 10, 20,  30, 40, 50,  60, 60] # 8 = Nhip
step_y = [0,  15, 25,  30, 25, 22,  10,  0]
#====================================================================
# Given a line joining two points xy0 and xy1, the base of an isosceles triangle,
# as well as the length of one side, "thy" this returns the coordinates of
# the apex joining the equal-length sides.

def kneePosition(x0, y0, x1, y1, thy):
    theta_1 = math.atan2((y1 - y0), (x1 - x0))
    L1 = math.sqrt( (y1 - y0)**2 + (x1 - x0)**2)
    if L1/2 < thy:
        alpha = math.acos(L1/(2*thy)) 
    else:
        alpha = 0.0 
    theta_2 =  alpha + theta_1 
    x_knee  = x0 + thy * math.cos(theta_2)
    y_knee  = y0 + thy * math.sin(theta_2)
    return x_knee, y_knee

def animdelay():
    chart_1.update()              # Refresh the drawing on the canvas.
    chart_1.after(cycle_period)   # Pause execution pause for X milliseconds.
    chart_1.delete("walking")     # Erases everything on the canvas.

bx_stay = base_x
by_stay = base_y

for j in range(0,13):             # Number of steps to be taken - arbitrary.
    astep_x = 60*j
    bstep_x = astep_x + 30
    cstep_x =  60*j + 15
    aa = len(step_x) -1
    for k in range(0,len(hip_x)-1):
        # Motion of the hips in a stride of each foot.
        cx0 = base_x + cstep_x + hip_x[k]
        cy0 = base_y - hip_h - hip_y[k]
        cx1 = base_x + cstep_x + hip_x[k+1]
        cy1 = base_y - hip_h - hip_y[k+1]

        if k >= 0 and k <= len(step_x)-2:
                # Trajectory of the right foot.
                ax0 = base_x + astep_x + step_x[k]
                ax1 = base_x + astep_x + step_x[k+1]
                ay0 = base_y - 10 - step_y[k]
                ay1 = base_y - 10 -step_y[k+1]
                ax_stay = ax1
                ay_stay = ay1

        if k >= len(step_x)-1 and k <= 2*len(step_x)-2:
                # Trajectory of the left foot.
                bx0 = base_x + bstep_x + step_x[k-aa]
                bx1 = base_x + bstep_x + step_x[k-aa+1]
                by0 = base_y - 10 - step_y[k-aa]
                by1 = base_y - 10 - step_y[k-aa+1]
                bx_stay = bx1
                by_stay = by1
        # The shoes
        chart_1.create_image(ax_stay-5 ,ay_stay + 10 ,anchor=SW, image=shoey, tag="walking") 
        chart_1.create_image(bx_stay-5 ,by_stay + 10 ,anchor=SW, image=shoey, tag="walking") 

        # Work out knee positions
        aknee_xy = kneePosition(ax_stay, ay_stay, cx1, cy1, thy)
        bknee_xy = kneePosition(bx_stay, by_stay, cx1, cy1, thy)

        # Right calf.
        chart_1.create_line(ax_stay, ay_stay-5 ,aknee_xy[0], aknee_xy[1],\
                                                width = 5, fill="black", tag="walking")
        # Right thigh.
        chart_1.create_line(cx1, cy1 ,aknee_xy[0], aknee_xy[1], width = 5,\
                                                fill="black", tag="walking")
        # Left calf.
        #bknee_xy = kneePosition(bx_stay, by_stay, cx1, cy1, thy)
        chart_1.create_line(bx_stay, by_stay-5 ,bknee_xy[0], bknee_xy[1],\
                                                width = 5, fill="black", tag="walking")
        # Left thigh.
        chart_1.create_line(cx1, cy1 ,bknee_xy[0], bknee_xy[1], width = 5,\
                                                fill="black", tag="walking")
        # Torso
        chart_1.create_image(cx1-20 ,cy1+30 ,anchor=SW, image=toff, tag="walking") 

        animdelay()   # Animation

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
