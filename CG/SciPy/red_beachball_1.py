"""
Program name: red_beachball_1 .py
Objective: Draw the primary red ball. Lightest at center and getting
darker toward the edges.

Keywords: canvas, red ball, color
============================================================================79 
Explanation: Establish the geometry for making shaded color shapes with
shape and placement (geometry) control lists.

Author:          Mike Ohlson de Fine

"""
# red_beach_ball_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Red beach ball")

cw = 240                                      # canvas width
ch = 220                                      # canvas height
chart_1 = Canvas(root, width=cw, height=ch, background="black")
chart_1.grid(row=0, column=0)

x_orig = 100
y_orig = 200
x_width = 80
y_hite = 180

xy0 = [x_orig,  y_orig]
xy1 = [x_orig - x_width, y_orig - y_hite]
xy2 = [x_orig + x_width, y_orig - y_hite ]
wedge =[ xy0, xy1 , xy2 ]

width= 80   # Standard disk diameter
hite = 80   # Median distance from origin (x_orig, y_orig).

hFac = [1.1,    1.15,   1.25,  1.35,   1.5,   1.6,  1.7]    # Height/radial factors.
wFac = [ 2.0,   1.9,   1.7,  1.4,   1.1,   0.75,  0.40]    # Disk diameter factors.
# Color list. Elements incresing in darkness.
kulaRed          =   ["#500000","#6e0000","#a00000","#ff0000",\
                      "#ff5050", "#ff8c8c", "#ffc8c8", "#ffffff" ]
kula =  kulaRed

for i in range(0, 7):                        # Red disks
    x0_disk = xy0[0]   - width * wFac[i]/2                  # Bottom left
    y0_disk = xy0[1]  - hite * hFac[i] + width * wFac[i]/2
    xya = [x0_disk, y0_disk]                                # BOTTOM LEFT
    x1_disk =  xy0[0]  + width * wFac[i]/2                  # Top right
    y1_disk =  xy0[1]  - hite * hFac[i] - width * wFac[i]/2
    xyb = [x1_disk, y1_disk]                                #TOP RIGHT
    chart_1.create_oval(xya ,xyb ,  fill=kula[i], outline=kula[i])

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
