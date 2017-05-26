"""
Program name: 4color_text.py
Objective: Place different colored texts and font types on a canvas.

Keywords: canvas, text, color, font
============================================================================79
 
Explanation: Font type, size and color can be specified. Tkinter will
apply a 'best-guess' font if the exact font specified is not available 
on your platform.

Author:          Mike Ohlson de Fine
"""
# 4color_text.py 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
from Tkinter import *
root = Tk()
root.title('Four color text')

cw = 500                                      # canvas width 
ch = 140                                      # canvas height 
canvas_1 = Canvas(root, width=cw, height=ch, background="white") 
canvas_1.grid(row=0, column=1)                              

xy = 200, 20                                  
canvas_1.create_text(200, 20,  text=" text normal SansSerif 20",   fill='red',\
                                 width=500, font='SansSerif 20 ') 
canvas_1.create_text(200, 50,  text=" text normal Arial 20",       fill='blue',\
                                width=500,  font='Arial 20 ') 
canvas_1.create_text(200, 80,  text=" text bold Courier 20",       fill='green',\
                                width=500, font='Courier 20 bold') 
canvas_1.create_text(200, 110, text=" bold italic BookAntiqua 20",\
                             fill='violet', width=500, font='Bookantiqua 20 bold')
root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
