"""
Program name: animated_color_wheel_1.py
Objective: Draw a progressive arc of a circle while mixing controlled
amounts of red, green and blue. Animate the display of corresponding
hex color value.
	  
Keywords: circle, arc, progressive color wheel, hex color, animation
============================================================================79
 
Explanation: 
The prtiphery of the disk is divided into 60 degree sectors with 
ramp-and-fall functions that govern the amount of pimary color that should
be present. 
                                                                                      
Author:          Mike Ohlson de Fine

Note: If you try to complete an entire disk you end up with the only excluded
portion - merely a line                                                                                        

"""
# animated_color_wheel_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Animated Color Wheel")

cw = 300                                      # canvas width
ch = 300                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="black")
canvas_1.grid(row=0, column=1)                              
cycle_period = 200

redFl = 255.0
greenFl = 0
blueFl = 0
kula = "#000000"

arcStart = 89
arcEnd = 90

xCentr = 150
yCentr = 160
radius = 130
circ = xCentr - radius, yCentr + radius, xCentr + radius, yCentr - radius 

# angular position markers, degrees
A_ANG = 0
B_ANG = 60
C_ANG = 120
D_ANG = 180 
E_ANG = 240
F_ANG = 300
#G_ANG = 1
G_ANG = 359
intervals = 60 # degrees

# Percent color at each position marker
# index        0    1    2   3    4    5    6   7
redShift   = 100, 100,   0,   0,   0, 100, 100   # percent of red
greenShift =  0,  100, 100, 100,   0,   0,   0    # percent of green
blueShift  =  0,    0,   0, 100, 100, 100,   0    # percent of blue

# Rate of change of color per degree, rgb integer counts per degree.
red_rate = [0,1,2,3,4,5,6,7]
green_rate = [0,1,2,3,4,5,6,7]
blue_rate = [0,1,2,3,4,5,6,7]

# Calibrate counts-per-degree in each interval, place in xrate list
for i in range(0,6):
    red_rate[i] = 256.0 * (redShift[i+1]   - redShift[i])/(100 * intervals) 
    green_rate[i] = 256.0 * (greenShift[i+1] - greenShift[i])/(100 * intervals) 
    blue_rate[i] = 256.0 * (blueShift[i+1]  - blueShift[i])/(100 * intervals) 

def rgb2hex(redFl, greenFl, blueFl):
     # Convert integer to hex color.
    red = int(redFl)
    green = int(greenFl)
    blue = int(blueFl)
    rgb = red, green, blue
    return '#%02x%02x%02x' % rgb     

for i in range (0, 359): 
    canvas_1.create_arc(circ, start=arcStart, extent=arcStart - arcEnd,\
                        fill= kula, outline= kula)
    arcStart = arcEnd
    arcEnd -=1
    
    # Color component transitions in 60 degree sectors
    if  i>A_ANG and i<B_ANG:
        redFl   +=  red_rate[0]
        greenFl +=  green_rate[0]   
        blueFl  += blue_rate[0]
        kula = rgb2hex(redFl, greenFl, blueFl)

    if  i>B_ANG and i<C_ANG:
        redFl   +=  red_rate[1]
        greenFl +=  green_rate[1]   
        blueFl  +=  blue_rate[1]
        kula = rgb2hex(redFl, greenFl, blueFl)

    if  i>C_ANG and i<D_ANG:
        redFl   +=  red_rate[2]
        greenFl +=  green_rate[2]   
        blueFl  +=  blue_rate[2]
        kula = rgb2hex(redFl, greenFl, blueFl)

    if  i>D_ANG and i<E_ANG:
        redFl   +=  red_rate[3]
        greenFl +=  green_rate[3]   
        blueFl  +=  blue_rate[3]
        kula = rgb2hex(redFl, greenFl, blueFl)

    if  i>E_ANG and i<F_ANG:
        redFl   +=  red_rate[4]
        greenFl +=  green_rate[4]   
        blueFl  +=  blue_rate[4]
        kula = rgb2hex(redFl, greenFl, blueFl)

    if  i>F_ANG and i<G_ANG:
        redFl   +=  red_rate[5]
        greenFl +=  green_rate[5]   
        blueFl  +=  blue_rate[5]
        kula = rgb2hex(redFl, greenFl, blueFl)

    #kula = rgb2hex(redFl, greenFl, blueFl)
    canvas_1.create_text(100, 20,  text=kula, fill='white', width=200,\
                font='SansSerif 12 ', tag= 'degreesAround', anchor= SW)
    canvas_1.update()              # This refreshes the drawing on the canvas.
    canvas_1.after(cycle_period)   # This makes execution pause for 200 milliseconds.
    canvas_1.delete('degreesAround')           # This erases the changing text

root.mainloop()
