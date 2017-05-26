"""  
Program name: photoimage_animation_1.py
Objective: First steps in gif animation.

Keywords: canvas, show images
============================================================================79
 
Explanation: 
Show images from Tkinter ( no PIL involvement).
Note that Only gif format pictur files are recognized by Tkinter.
png, jpg , tiff or bmp are not recognized.

Author:          Mike Ohlson de Fine

"""
#  photoimage_animation_1.py
#=======================================================
from Tkinter import *
root = Tk()
root.title("Animating a Photo Beachball") 
cycle_period = 100

cw = 320                                      # canvas width
ch = 120                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, bg="black")
canvas_1.grid(row=0, column=1)
posn_x = 10
posn_y = 10
shift_x = 2
shift_y = 1

ball =  PhotoImage(file = "/constr/pics1/beachball.gif")

for i in range(1,100):       # end the program after 50 position shifts. 
    posn_x +=  shift_x
    posn_y +=  shift_y
    canvas_1.create_image(posn_x,posn_y,anchor=NW, image=ball) 
    canvas_1.update()              # This refreshes the drawing on the canvas.
    canvas_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    canvas_1.delete(ALL)           # This erases everything on the 

root.mainloop()

