"""
Program name: fonts_available.py
Objective: Discover, list and demonstrate all fonts available on your
platform.

Keywords: canvas, text, font inventory
============================================================================79
 
Explanation: to specify fonts precisely you need to know what fonts are 
available and what they look like. The tkfont module is used here to
do that.

Author:          Mike Ohlson de Fine
"""
# fonts_available.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import * 
import tkFont 
root = Tk() 
root.title('Fonts available on this Computer') 

canvas = Canvas(root, width =930, height=950, background='white')

fonts_available = list( tkFont.families() ) 
fonts_available.sort() 
text_sample = '  :  abcdefghij_HIJK_12340' 
# list the font names on the system console first.
for this_family in fonts_available : 
    print this_family
print '=============================' 

# Show first half on left half of screen .
for i in range(0,len(fonts_available)/2): 
    print fonts_available[i] 
    texty = fonts_available[i] 
    canvas.create_text(50,30 + i*20, text= texty + text_sample,\
                       fill='black', font=(texty, 12), anchor= "w") 

# Show second half on right half of screen .
for i in range(len(fonts_available)/2,len(fonts_available)): 
    print fonts_available[i] 
    texty = fonts_available[i] 
    canvas.create_text(500,30 + (i-len(fonts_available)/2  )*20,text= texty+ text_sample,\
 fill='black',font=(texty, 12),anchor= "w")

canvas.pack() 
root.mainloop()
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
