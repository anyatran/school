"""
Program name: align_text_2.py
Objective: Place different colored texts and font types on a canvas.

Keywords: canvas, text, color, font
============================================================================79
 
Explanation:
1. Font type, size and color can be specified. Tkinter will
apply a 'best-guess' font if the exact font specified is not available 
on your platform.

2. anchor=anchorPos:  Text posiotion orig_x, orig_y is where the text is 
centered (by default). This can be overriden by anchor options like "anchor= NW".
AnchorPos Specifies which part of the text (the text's bounding box, more exactly)
 that should be placed at the given position. 
Use one of N, NE, E, SE, S, SW, W, NW or CENTER. Default is CENTER. 

3. justify=how (ACTIVE WHEN LINE OVERFLOWS OCCUR)
Specifies how to justify the text within its bounding region. How must be 
one of the values "LEFT", "RIGHT", or "CENTER". This option will only matter 
if the text is displayed as multiple lines. 
If the option is omitted, it defaults to left. 


4. width=lineLength 
Specifies a maximum line length for the text. If this option is zero (the default)
the text is broken into lines only at newline characters. 
However, if this option is non-zero then any line that would be longer than
 lineLength is broken just before a space character to make the line shorter
than lineLength; the space character is treated as if it were a newline character. 


Author:          Mike Ohlson de Fine
"""
# align_text_2.py 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
from Tkinter import *
root = Tk()
root.title('North-south-east-west Placement with LEFT and RIGHT justification of Text')

cw = 850                                      # canvas width 
ch = 720                                      # canvas height 
canvas_1 = Canvas(root, width=cw, height=ch, background="white") 
canvas_1.grid(row=0, column=1)                              

orig_x = 220
orig_y  = 20   
offset_y = 20   
 
jolly_text = "And now ladies and gentlemen she will attempt - for the very first time in the history of 17 Thread Shoes street - a one handed right arm full toss teacup fling. Yes you lucky listners you are about to witness what, in recorded history, has never been attempted before without the aid of hair curlers and fluffy slippers."
# width is maximum line length. Lare broken before a space and a 'newline' added. 
#================================================================================
# 1. TOP-LEFT (NW) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW ,text="1. NORTH-WEST anchor",\
                                 fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 2 * offset_y, anchor = NW, text= jolly_text,\
                             fill='red', width=200, font='Arial 10')
#================================================================================
# 2. Top-right (SE) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE ,text="2. NORTH-EAST anchor",\
                                 fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 2 * offset_y, anchor = NE, text=jolly_text,\
                             fill='blue', width=150, font='Arial 10')
#================================================================================
orig_x = 600
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='gray', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW ,text="3. SOUTH-WEST anchor",\
                                 fill='gray', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='black', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE ,text="4. SOUTH-EAST anchor",\
                                 fill='black', width=200, font='Arial 10 ') 
#============================================================
orig_x = 600
orig_y  = 250   
# 3. BOTTOM-LEFT (SW) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y + 2 * offset_y, anchor = SW, text=jolly_text,\
                             fill='gray', width=200, font='Arial 10')
#================================================================================
# 4. TOP-RIGHT (SE) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y +  2 * offset_y, anchor = SE, text=jolly_text,\
                             fill='black', width=150, font='Arial 10')
#================================================================================
orig_y  = 350
orig_x = 200  
# 5. TOP-LEFT (NW) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW ,text="5. NORTH-WEST anchor, justify=RIGHT",\
                                 fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NW, justify=RIGHT, text=jolly_text,\
                             fill='red', width=200, font='Arial 10')
#================================================================================
# 6. Top-right (SE) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE , justify=RIGHT,\
                                 text="6. NORTH-EAST anchor, justify=RIGHT",\
                                 fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NE, justify=RIGHT, text=jolly_text,\
                             fill='blue', width=150, font='Arial 10')
#================================================================================
orig_x = 600
# Header lines for 7. and 8.
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='gray', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW , \
                                 text="7. SOUTH-WEST anchor, justify= RIGHT",\
                                 fill='gray', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='black', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE ,text="8. SOUTH-EAST anchor, justify= RIGHT",\
                                 fill='black', width=200, font='Arial 10 ') 
#====================================
orig_y  = 600   
# 7. BOTTOM-LEFT (SW) JUSTIFICATION

canvas_1.create_text(orig_x, orig_y + 4 * offset_y, anchor = SW, justify= RIGHT, text=jolly_text,\
                             fill='gray', width=200, font='Arial 10')
#==================================
# 8. TOP-RIGHT (SE) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y + 4 * offset_y, anchor = SE, justify= RIGHT, text=jolly_text,\
                             fill='black', width=150, font='Arial 10')

root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
