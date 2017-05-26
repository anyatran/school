"""
Program name: spaces_for_commas_svg2tkinter_1.py
Objective: Convert and plot an SVG list of incremental coordinates to a form
that makes a sensible drawing in create_line().

Keywords: canvas, SVG, Inkscape, path conversion
============================================================================79
 
Explanation: How to convert and SVG incremental format.

Author:          Mike Ohlson de Fine 

"""
# spaces_for_commas_svg2tkinter_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Conversion of SVG paths to Tkinter create_line()")
cw = 1000                                     # canvas width.
ch = 800                                      # canvas height.
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)
tkint_line = [] 
svg_line_coords = '1551.2964,83.663208 -92.9426,0 -64.2149,28.727712 -13.5189,32.10744\
 37.177,43.9365 65.9048,27.03785 82.8034,5.06959 82.8034,-11.82906\
 45.6264,-30.41757 -3.3798,-38.86691 -72.6642,-42.246629 -59.1453,-11.829058'

b = svg_line_coords.replace(' ', ',') # replace each space with a comma. b is a string
c =  b.split(',')                     # separates string b, at each comma, into a list.

# Convert string elements into a floating point number list.
p= len(c) 
for i in range(0,p):
    tkint_line.append(float(c[i]))

# Add incremental coordinates to the previous value 
for i in range(0, p-2): 
    # Add the increment to the value two positions back 
    # because two positions separate each x and each y. 
    tkint_line[i +2] = tkint_line[i +2] + tkint_line[i]  
                              
# Scale it to a convenient size 
for i in range(0,len(tkint_line)): 
    tkint_line[i] =int((tkint_line[i]+1)/ 2) 

canvas_1.create_line(tkint_line, fill='green', smooth='true') 

root.mainloop()
