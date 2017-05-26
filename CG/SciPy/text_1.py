"""
Program name: text_1.py
Objective: Place text on a canvas.

Keywords: canvas, text
============================================================================79
 
Explanation: Using all default settings this is the bare minimum code 
to place text onto a canvas.

Author:          Mike Ohlson de Fine
"""
# text_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Basic text')

cw = 400                                     # canvas width
ch = 50                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="white")
canvas_1.grid(row=0, column=1)                              

xy = 150, 20
canvas_1.create_text(xy, text=" The default text size looks about 10.5 point")
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
