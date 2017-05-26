"""
Program name: primary_color_wheel_1 .py
Objective: Draw the primary red, green, blue color wheel framework.

Keywords: canvas, line, time, movement
============================================================================79 
Explanation: Establish the functions for making color wheel segements.

Author:          Mike Ohlson de Fine

"""
# primary_color_wheel_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import math
root = Tk()
root.title("Color wheel segments")

cw = 400                                      # canvas width
ch = 400                                      # canvas height
chart_1 = Canvas(root, width=cw, height=ch, background="black")
chart_1.grid(row=0, column=0)

theta_deg = 0.0
x_orig = 200
y_orig = 200
x_width = 40
y_hite = 160

xy0 = [x_orig,  y_orig]
xy1 = [x_orig - x_width, y_orig - y_hite]
xy2 = [x_orig + x_width, y_orig - y_hite ]
wedge =[ xy0, xy1 , xy2 ]

width= 40   #standard disk diameter
hite = 80  # median wedge height.
hFac = [0.25,   0.45,  0.75,  1.2,  1.63,  1.87,  2.05]    # Radial factors
wFac = [ 0.2,   0.36,   0.6,  1.0,   0.5,   0.3,  0.25]    # disk diameter factors
x_DiskRot = [1.0,     1.0,   1.0,  1.0,   1.0,   1.0,   1.0] # rotational coordinates
y_DiskRot = [1.0,     1.0,   1.0,  1.0,   1.0,   1.0,   1.0]

#RED
kulaRed          =   ["#000000", "#6e0000", "#a00000", "#ff0000",\
                      "#ff5050", "#ff8c8c", "#ffc8c8", "#440000" ]
# Khaki
kulaRRedGreen    =   ["#000000", "#606000", "#8f9f00", "#b3b300",\
                      "#d6d600", "#dbdb30", "#dbdb77", "#3e2700" ]
# Yellow
kulaRedGreen     =   ["#000000", "#6e6e00", "#a0a000", "#ffff00",\
                      "#ffff50", "#ffff8c", "#ffffc8", "#444400" ]   
# Orange
kulaRedGGreen    =   ["#000000", "#493100", "#692f00", "#a25d00",\
                      "#ff8300", "#ffa55a", "#ffb681", "#303030" ]  
# Green
kulaGreen        =   ["#000000", "#006e00", "#00a000", "#00ff00",\
                      "#50ff50", "#8cff8c", "#c8ffc8", "#004400" ]   
# Dark green
kulaGGreenBlue   =   ["#000000", "#003227", "#009358", "#00a141",\
                      "#00ff76", "#72ff99", "#acffbf", "#003a1d" ]   
# Cyan
kulaGreenBlue    =   ["#000000", "#006e6e", "#00a0a0", "#00ffff",\
                      "#50ffff", "#8cffff", "#c8ffff", "#004444" ]
# Steel Blue
kulaGreenBBlue   =   ["#000000", "#002c46", "#00639c", "#008cc8",\
                      "#00b6ff", "#7bb6ff", "#addfff", "#001a27" ]
# Blue
kulaBlue         =   ["#000000", "#00006e", "#0000a0", "#0000ff",\
                      "#5050ff", "#8c8cff", "#c8c8ff", "#000044" ]
# Purple
kulaBBlueRed     =   ["#000000", "#470047", "#6c00a2", "#8f00ff",\
                      "#b380ff", "#d8b3ff", "#f1deff", "#200031" ]
# Crimson
kulaBlueRed      =   ["#000000", "#6e006e", "#a000a0", "#ff00ff",\
                      "#ff50ff", "#ff8cff", "#ffc8ff", "#440044" ]
# Magenta
kulaBlueRRed     =   ["#000000", "#380023", "#80005a", "#b8007b",\
                      "#ff00a1", "#ff64c5", "#ff89ea", "#2e0018" ]  

# ROTATE
def rotate(xya, xyb, theta_deg_incr):      #xya, xyb are 2 component points
    # General purpose point rotation function
    theta_rad = math.radians(theta_deg_incr)
    a_radian  = math.atan2( (xyb[1] - xya[1]) , (xyb[0] - xya[0]) )
    a_length  = math.sqrt( (xyb[1] - xya[1])**2 + (xyb[0] - xya[0])**2)
    theta_rad +=  a_radian
    theta_deg = math.degrees(theta_rad)
    new_x = a_length * math.cos(theta_rad)
    new_y = a_length * math.sin(theta_rad)
    return   new_x,   new_y,  theta_deg     # theta_deg = post rotation angle

# GENL. SEGMENT BACKGROUND FUNCTION
def segmentBackground(kula, angle, xy1, xy2):
    xy_new1 = rotate(xy0, xy1,  angle) # rotate xy1
    xy1 =[ xy_new1[0] + xy0[0], xy_new1[1] + xy0[1] ]
    xy_new2 = rotate(xy0,  xy2,  angle) # rotate xy2
    xy2 =[ xy_new2[0] + xy0[0], xy_new2[1] + xy0[1] ]
    wedge =[ xy0, xy1 , xy2 ]
    chart_1.create_polygon(wedge,fill=kula[7])

# GENL. COLOR DISKS FUNCTION
def colorDisks( kula, angle):
    global hite, width, hFac, wFac
    for i in range(0, 7):  # green segment disks
        xya = [xy0[0], xy0[1] - hite * hFac[i] ]   # position of point for rotation
        xy_new1 = rotate(xy0,  xya,  angle) # rotate xya
        # NEW CIRCLE CENTERS AFTER ROTATION OF CENTERLINE
        x0_disk = xy_new1[0] + xy0[0]  - width*wFac[i]/2
        y0_disk = xy_new1[1] + xy0[1]  + width * wFac[i]/2
        xya = [x0_disk, y0_disk]    # BOTTOM LEFT
        x1_disk = xy_new1[0] + xy0[0]  + width*wFac[i]/2
        y1_disk = xy_new1[1] + xy0[1]  - width * wFac[i]/2
        xyb = [x1_disk, y1_disk]  #TOP RIGHT
        chart_1.create_oval(xya ,xyb ,  fill=kula[i], outline=kula[i])

for i in range(0,12):
    if i==0:
        angle = 0.0
        kula =  kulaRed
    if i==1:
        angle = 30.0
        kula =  kulaRRedGreen
    if i==2:
        angle = 60.0
        kula =  kulaRedGreen
    if i==3:
        angle = 90.0
        kula =  kulaRedGGreen
    if i==4:
        angle = 120.0
        kula =  kulaGreen
    if i==5:
        angle = 150.0
        kula =  kulaGGreenBlue
    if i==6:
        angle = 180.0
        kula =  kulaGreenBlue
    if i==7:
        angle = 210.0
        kula =  kulaGreenBBlue
    if i==8:
        angle = 240.0
        kula =  kulaBlue
    if i==9:
        angle = 270.0
        kula =  kulaBBlueRed
    if i==10:
        angle = 300.0
        kula =  kulaBlueRed
    if i==11:
        angle = 330.0
        kula =  kulaBlueRRed
    if i==12:
        angle = 360.0
        kula =  kulaBlueRRed
    segmentBackground( kula, angle, xy1, xy2)
    colorDisks( kula, angle)

root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
