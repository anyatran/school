"""
Program name: rotate_line_1 .py
Objective: Rotate a single line.

Keywords: canvas, line, rotate, time, movement
============================================================================79
recipe # 9 
Explanation: Establish facility with the trigonometry of the requirements.

Author:          Mike Ohlson de Fine

"""
# rotate_line_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
import math
root = Tk()
root.title("Rotating line")

cw = 220                                      # canvas width
ch = 180                                      # canvas height
chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 50 # pause duration (milliseconds).

p1_x = 90.0
p1_y = 90.0

p2_x = 180.0
p2_y = 160.0

a_radian = math.atan((p2_y - p1_y)/(p2_x - p1_x))
a_length = math.sqrt((p2_y - p1_y)*(p2_y - p1_y) +\
                     (p2_x - p1_x)*(p2_x - p1_x))

for i in range(1,300):       # end the program after 300 position shifts.
    
    a_radian +=0.05
    p1_x = p2_x - a_length * math.cos(a_radian)
    p1_y = p2_y - a_length * math.sin(a_radian)

    chart_1.create_line(p1_x, p1_y, p2_x, p2_y)
    chart_1.update()              
    chart_1.after(cycle_period)  
    chart_1.delete(ALL)          

root.mainloop()

