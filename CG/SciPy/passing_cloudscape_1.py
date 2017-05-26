"""
Program name:  passing_cloudscape_1.py
Objective: A continuous rolling flight in a cloudscape

Keywords: canvas, image, aeroplane, gif, cloudscape, time, movement
============================================================================79
 
Explanation: A means of making a continuous strip of images roll past a
window with a stationary image superimposed..

Author:          Mike Ohlson de Fine

"""
# passing_cloudscape_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
root = Tk()
root.title("Freedom Flight Cloudscape")
cw = 400                                      # canvas width
ch = 239                                      # canvas height
                              
chart_1 = Canvas(root, width=cw, height=ch, background="black")
chart_1.grid(row=0, column=0)

cycle_period = 50 # time between new positions of the ball (milliseconds).
#=======================================================================
posn_x1 = 0
posn_x2 = 574
posn_plane_x = 60
posn_plane_y = 60
posn_y = 00
# Panorama image size = 574 x 239
im_one =  PhotoImage(file = "/constr/pics1/continuous_clouds_panorama.gif")
im_two =  PhotoImage(file = "/constr/pics1/continuous_clouds_panorama.gif")
im_plane =  PhotoImage(file = "/constr/pics1/yellow_airplane_2.gif")
#=======================================================================
def animdelay():
    chart_1.update()              # This refreshes the drawing on the canvas.
    chart_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    chart_1.delete(ALL)           # This erases *almost* everything on the canvas.
num_cycles = 10                   # Number of total cycles of the loop.
k = 0
for j in range(0,num_cycles*1148):    # Number of steps to be taken - arbitrary.
    posn_x1 -=  1
    posn_x2 -=  1
    k += 1
    chart_1.create_image(posn_x1,posn_y,anchor=NW, image=im_one)
    chart_1.create_image(posn_x2,posn_y,anchor=NW, image=im_two)
    chart_1.create_image(posn_plane_x,posn_plane_y,anchor=NW, image=im_plane)
    if k == 574:
        posn_x1 = 574
    if k == 1148:
        posn_x2 = 574
        k = 0
        posn_x1 = 0
    animdelay()

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
