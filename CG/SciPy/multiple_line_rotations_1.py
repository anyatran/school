"""
Program name: multiple_line_rotations_1.py
Objective: Rotate three lines, each at the tip of the next.
Show the locus of the tip as a tip_locus_2.

Keywords: canvas, line, multi-segment, rotation, time, movement, tip locus
============================================================================79
 
Explanation: Establish facility with the trigonometry of the requirements.
angles are expressed both in degrees and radians. The degrees manifestation
is redundant.

Author:          Mike Ohlson de Fine

"""
# multiple_line_rotations_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import time
import math
root = Tk()
root.title("multi-line rotations")

cw = 600                                      # canvas width
ch = 600                                      # canvas height

chart_1 = Canvas(root, width=cw, height=ch, background="white")
chart_1.grid(row=0, column=0)

cycle_period = 50 # time between new positions of the ball (milliseconds).

p0_x = 300.0
p0_y = 300.0

p1_x = 200.0
p1_y = 200.0

p2_x = 150.0
p2_y = 150.0

p3_x = 100.0
p3_y = 100.0

p4_x = 50.0
p4_y = 50.0

alpha_0 = math.atan((p0_y - p1_y)/(p0_x - p1_x))
length_0_1 = math.sqrt((p0_y - p1_y)*(p0_y - p1_y) +\
                       (p0_x - p1_x)*(p0_x - p1_x))

alpha_1 = math.atan((p1_y - p2_y)/(p1_x - p2_x))
length_1_2 = math.sqrt((p2_y - p1_y)*(p2_y - p1_y) +\
                       (p2_x - p1_x)*(p2_x - p1_x))

alpha_2 = math.atan((p2_y - p3_y)/(p2_x - p3_x))
length_2_3 = math.sqrt((p3_y - p2_y)*(p3_y - p2_y) +\
                       (p3_x - p2_x)*(p3_x - p2_x))

alpha_3 = math.atan((p3_y - p4_y)/(p3_x - p4_x))
length_3_4 = math.sqrt((p4_y - p3_y)*(p4_y - p3_y) +\
                       (p4_x - p3_x)*(p4_x - p3_x))

for i in range(1,5000):     

    alpha_0 += 0.1
    alpha_1 += 0.3
    alpha_2 -= 0.4

    p1_x = p0_x - length_0_1 * math.cos(alpha_0)
    p1_y = p0_y - length_0_1 * math.sin(alpha_0)

    tip_locus_2_x = p2_x
    tip_locus_2_y = p2_y
    p2_x = p1_x - length_1_2 * math.cos(alpha_1)
    p2_y = p1_y - length_1_2 * math.sin(alpha_1)

    tip_locus_3_x = p3_x
    tip_locus_3_y = p3_y
    p3_x = p2_x - length_2_3 * math.cos(alpha_2)
    p3_y = p2_y - length_2_3 * math.sin(alpha_2)

    tip_locus_4_x = p4_x
    tip_locus_4_y = p4_y
    p4_x = p3_x - length_3_4 * math.cos(alpha_3)
    p4_y = p3_y - length_3_4 * math.sin(alpha_3)

    chart_1.create_line(p1_x, p1_y, p0_x, p0_y, tag='line_1')
    chart_1.create_line(p2_x, p2_y, p1_x, p1_y, tag='line_2')
    chart_1.create_line(p3_x, p3_y, p2_x, p2_y, tag='line_3')
    chart_1.create_line(p4_x, p4_y, p3_x, p3_y, fill="purple", tag='line_4')  

    # Locus tip_locus_2 at tip of line 1-2
    chart_1.create_line(tip_locus_2_x, tip_locus_2_y, p2_x, p2_y, fill='maroon')
    # Locus tip_locus_2 at tip of line 2-3
    chart_1.create_line(tip_locus_3_x, tip_locus_3_y, p3_x, p3_y, fill='orchid1')
    # Locus tip_locus_2 at tip of line 2-3
    chart_1.create_line(tip_locus_4_x, tip_locus_4_y, p4_x, p4_y, fill='DeepPink')

    chart_1.update()             
    chart_1.after(cycle_period)  
    chart_1.delete('line_1', 'line_2', 'line_3')          

root.mainloop()

