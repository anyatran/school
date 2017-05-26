"""
Program name: color_arithmetic_1.py
Objective:  A colored circle is drawn.

Keywords: canvas, oval, circle, color specifications
============================================================================79
 
Explanation: Color names are case insensitive. 
Many (but not all) color names are also available with 
or without spaces between the words.
There are also functions that will conver the names to hex equivalents.

Author:          Mike Ohlson de Fine

"""
# color_arithmetic_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('A colored circle')

cw = 150                                      # canvas width
ch = 140                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)                              

# specify bottom-left and top-right as a set of four numbers named 'xy'
named_color_1 = "light blue"    # ok
named_color_2 = "lightblue"     # ok
named_color_3 = "LightBlue"     # ok
named_color_4 = "Light Blue"    # ok
named_color_5 = "Light  Blue"   # not ok: Tcl Error, unknown color name

rgb_color = "rgb(255,0,0)"      # Unknown color name.
#rgb_percent_color = rgb(100%, 0%, 0%) # Invalid syntax
rgb_hex_1 = "#ff0000"             # ok  - 16.7 million colors
rgb_hex_2 = "#f00"                # ok
rgb_hex_3 = "#ffff00000000"       # ok  - a ridiculous number

tk_rgb = "#%02x%02x%02x" % (128, 192, 200)
print tk_rgb
xy = 20, 20, 120, 120         

canvas_1.create_oval(xy, fill= rgb_hex_3)
root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
