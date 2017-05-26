"""
Program name: walking_birdy_1.py
Objective: A walkin kiwi with shoes.

Keywords: canvas,bird, walk, shoes, time, movement
============================================================================79
 
Explanation: The number of frames in the stride for one foot is the number 
of elements in step_x (=len(step_x). The numer of steps in the hip motion 
is twice as many (a sequence of hip positions for each of two feet).
So the 'for loops' are defined in terms of len(step) - adaptable therefore.]
gifs suppor a transparent background. This allows good mixing of raster 
and vector images.
 
Improvements could be:
1. Interpolation steps
3. Adjustable thigh (thy) and calf (caf) length. 
4. Birds knee bend - backwards
5. Mammal knee bend - forewards.

Author:          Mike Ohlson de Fine

"""
# walking_birdy_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import math
import time
root = Tk()
root.title("A Walking birdy gif and shoes images")
cw = 800                                      # canvas width
ch = 200                                      # canvas height
#GRAVITY = 4                               
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 80 # time between new positions of the ball (milliseconds).
im_backdrop = "/constr/pics1/karoo.gif"
im_bird = "/constr/pics1/apteryx1.gif"
im_shoe = "/constr/pics1/shoe1.gif"
birdy =PhotoImage(file= im_bird)
shoey =PhotoImage(file= im_shoe)
backdrop = PhotoImage(file= im_backdrop)
chart_1.create_image(0 ,0 ,anchor=NW, image=backdrop) 
base_x = 20
base_y = 190
hip_h = 70
thy = 60
#=======================================================================
# Hip positions: Nhip = 2 x Nstep, the number of steps per foot per stride.
hip_x = [0, 5, 10,   15, 20, 25,   30, 35, 40,   45, 50, 55,   60, 60, 60] #15
hip_y = [0, 8, 12,   16, 12,  8,    0,  0,  0,    8, 12, 16,   12,  8,  0] #15

step_x = [0, 10, 20,  30, 40, 50,  60, 60] # 8 = Nhip
step_y = [0, 35, 45,  50, 43, 32,  10,  0]

#====================================================================
# Given a line joining two points xy0 and xy1, the base of an isosceles triangle,
# as well as the length of one side, "thy" this returns the coordinates of
# the apex joining the equal-length sides.
 
def kneePosition(x0, y0, x1, y1, thy):
    theta_1 = math.atan2(-(y1 - y0), (x1 - x0))
    L1 = math.sqrt( (y1 - y0)**2 + (x1 - x0)**2)
    alpha = math.atan2(hip_h,L1) 
    theta_2 =  -(theta_1 - alpha)
    x_knee  = x0 + thy * math.cos(theta_2)
    y_knee  = y0 + thy * math.sin(theta_2)
    return x_knee, y_knee

def animdelay():
    chart_1.update()              # Refresh the drawing on the canvas.
    chart_1.after(cycle_period)   # Pause execution pause for X milliseconds.
    chart_1.delete("walking")           # Erases everything on the canvas.

bx_stay = base_x
by_stay = base_y

for j in range(0,13):    # Number of steps to be taken - arbitrary.
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
        #chart_1.create_image(cx1-55 ,cy1+20 ,anchor=SW, image=birdy, tag="walking") 

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

        chart_1.create_image(ax_stay-5 ,ay_stay + 10 ,anchor=SW, image=shoey, tag="walking") 
        chart_1.create_image(bx_stay-5 ,by_stay + 10 ,anchor=SW, image=shoey, tag="walking") 

        aknee_xy = kneePosition(ax_stay, ay_stay, cx1, cy1, thy)
        chart_1.create_line(ax_stay, ay_stay-15 ,aknee_xy[0], aknee_xy[1],\
                                                width = 5, fill="orange", tag="walking")
        chart_1.create_line(cx1, cy1 ,aknee_xy[0], aknee_xy[1], width = 5,\
                                                fill="orange", tag="walking")

        bknee_xy = kneePosition(bx_stay, by_stay, cx1, cy1, thy)
        chart_1.create_line(bx_stay, by_stay-15 ,bknee_xy[0], bknee_xy[1],\
                                                width = 5, fill="pink", tag="walking")
        chart_1.create_line(cx1, cy1 ,bknee_xy[0], bknee_xy[1], width = 5,\
                                                fill="pink", tag="walking")
        chart_1.create_image(cx1-55 ,cy1+20 ,anchor=SW, image=birdy, tag="walking") 
        animdelay()

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
