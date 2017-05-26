"""
Program name: walker_spider_1.py
Objective: A walking spider with positive and negative knee bends .

Keywords: canvas, walk, trigonometry, time, movement
============================================================================79
 
Explanation: The number of frames in the stride for one foot is the number 
of elements in step_x (=len(step_x). The numer of steps in the hip motion 
is twice as many (a sequence of hip positions for each of two feet).
So the 'for loops' are defined in terms of len(step) - adaptable therefore.]

Author:          Mike Ohlson de Fine

"""
# walker_spider_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import math
import time
root = Tk()
root.title("Mr Incy Wincy")
cw = 500                                      # canvas width
ch = 100                                      # canvas height
                              
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 100 # time between new positions of the ball (milliseconds).

base_x = 20
base_y = 100
avian = 1

ax = [ base_x, base_x+20, base_x+60 ]
ay = [ base_y, base_y, base_y ]
bx = [ base_x+90, base_x+130, base_x+170] 
by = [ base_y, base_y, base_y ] 

cx1 = base_x + 80
cy1 = base_y - 20
thy = 50
#=======================================================================
posn_x = 0
posn_y = 00

spider_backg =  PhotoImage(file = "/constr/pics1/jungle_strip_1.gif")


#=======================================================================

foot_lift = [10,10,5,-5,-10,-10] # 3 legs per side, each foot in sequence = 18 moves 
foot_stay = [ 0, 0,0, 0,  0,  0]

#====================================================================
# Given a line joining two points xy0 and xy1, the base of an isosceles triangle,
# as well as the length of one side, "thy" this returns the coordinates of
# the apex joining the equal-length sides  - the position of the knee.

def kneePosition(x0, y0, x1, y1, thy, avian):
    theta_1 = math.atan2((y1 - y0), (x1 - x0))
    L1 = math.sqrt( (y1 - y0)**2 + (x1 - x0)**2)
    if L1/2 < thy:
         # The sign of alpha determines which way the knees bend.
         if avian == 1:
             alpha = -math.acos(L1/(2*thy))  # Avian
         else:
             alpha = math.acos(L1/(2*thy))   # Mammalian
    else:
        alpha = 0.0 
    theta_2 =  alpha + theta_1 
    x_knee  = x0 + thy * math.cos(theta_2)
    y_knee  = y0 + thy * math.sin(theta_2)
    return x_knee, y_knee

def animdelay():
    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    chart_1.delete(ALL)           # This erases *almost* everything on the canvas.

for j in range(0,11):    # Number of steps to be taken - arbitrary.  

    posn_x -=  1
    chart_1.create_image(posn_x,posn_y,anchor=NW, image=spider_backg) 
    for k in range(0,len(foot_lift)*3):
        posn_x -=  1
        chart_1.create_image(posn_x,posn_y,anchor=NW, image=spider_backg) 
        #cx1 += 3.5
        cx1 += 2.6
        # Phase 1
        if k >= 0 and k <= 5:
            ay[0] = base_y - 10 - foot_lift[k]
            ax[0] += 8
            by[0] = base_y - 10 - foot_lift[k]
            bx[0] += 8

        # Phase 2
        if k > 5 and k <= 11:
            ay[1] = base_y - 10 - foot_lift[k-6]
            ax[1] += 8
            by[1] = base_y - 10 - foot_lift[k-6]
            bx[1] += 8

       # Phase 3
        if k > 11 and k <= 17:
            ay[2] = base_y - 10 - foot_lift[k-12]
            ax[2] += 8
            by[2] = base_y - 10 - foot_lift[k-12]
            bx[2] += 8

        for i in range(0,3):
            aknee_xy = kneePosition(ax[i], ay[i], cx1, cy1, thy, 1) # Mammal knee
            bknee_xy = kneePosition(bx[i], by[i], cx1, cy1, thy, 0) # Bird knee
            chart_1.create_line(ax[i], ay[i] ,aknee_xy[0], aknee_xy[1], width = 3)
            chart_1.create_line(cx1, cy1 ,aknee_xy[0], aknee_xy[1], width = 3)
            chart_1.create_line(bx[i], by[i] ,bknee_xy[0], bknee_xy[1], width = 3)
            chart_1.create_line(cx1, cy1 ,bknee_xy[0], bknee_xy[1], width = 3)

        chart_1.create_oval(cx1-15 ,cy1-10 ,cx1+15 ,cy1+10, fill="black")
        animdelay()

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
