"""
Program name: 3concentric_squares.py
Objective: A set of squares.

Keywords: canvas, square, concentric
============================================================================79
 
Explanation: A square is just a particular case of a rectangle where all
side are of equal length. Because we want to ensure concentricity dimension
variables are defined in terms of a common center.

Author:          Mike Ohlson de Fine

"""
# 3concentric_squares.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Concentric squares')

cw = 200                                      # canvas width
ch = 400                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=200, background="turquoise")
canvas_1.grid(row=0, column=1)

# dark blue square - painted first therefore at the bottom
x_center = 100
y_center = 100
x_width =  100
y_height = 100
kula ="darkblue"
canvas_1.create_rectangle( x_center - x_width/2,  y_center - y_height/2,\
                           x_center + x_width/2, y_center + y_height/2,\
                           fill=kula) 

#dark red square - second therefore in the middle
x_width =  80
y_height = 80
kula ="darkred"
canvas_1.create_rectangle( x_center - x_width/2,  y_center - y_height/2,\
                           x_center + x_width/2, y_center + y_height/2,\
                           fill=kula)

#dark green square - painted last therefore on top of previous ones.
x_width =  60
y_height = 60
kula ="darkgreen"
canvas_1.create_rectangle( x_center - x_width/2,  y_center - y_height/2,\
                           x_center + x_width/2, y_center + y_height/2,\
                           fill=kula)
root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
