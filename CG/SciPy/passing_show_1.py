"""
Program name:  passing_show_1.py
Objective: A moving gallery of pictures.

Keywords: canvas, images, time, movement
============================================================================79
 
Explanation: A series of four images is moved across a canvas window in sucession

Author:          Mike Ohlson de Fine

"""
# passing_show_1.py
# >>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import math
import time
root = Tk()
root.title("Vase Show")
cw = 400                                      # canvas width
ch = 200                                      # canvas height
                              
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 100 # time between new positions of the ball (milliseconds).
#=======================================================================
posn_x1 = 0
posn_x2 = 100
posn_x3 = 200
posn_x4 = 300

posn_y = 00

#spider_backg =  PhotoImage(file = "/constr/pics1/jungle_strip_1.gif")

im_brass =  PhotoImage(file = "/constr/pics1/brass_vase.gif")
im_red =  PhotoImage(file = "/constr/pics1/red_vase.gif")
im_blue =  PhotoImage(file = "/constr/pics1/blue_vase.gif")
im_glass =  PhotoImage(file = "/constr/pics1/glass_vase.gif")
#=======================================================================
def animdelay():
    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    chart_1.delete(ALL)           # This erases *almost* everything on the canvas.

for j in range(0,400):    # Number of steps to be taken - arbitrary.  
    posn_x1 -=  1
    posn_x2 -=  1
    posn_x3 -=  1
    posn_x4 -=  1
    chart_1.create_image(posn_x1,posn_y,anchor=NW, image=im_brass) 
    chart_1.create_image(posn_x2,posn_y,anchor=NW, image=im_red) 
    chart_1.create_image(posn_x3,posn_y,anchor=NW, image=im_blue) 
    chart_1.create_image(posn_x4,posn_y,anchor=NW, image=im_glass) 
    animdelay()

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
