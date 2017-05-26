"""
Program name: justify_align_text_1.py
Objective: Place different colored texts and font types on a canvas
           column anchor points an LEFT or RIGHT text justification.

Keywords: canvas, text, color, font, column anchor, justification
============================================================================79
 
Explanation:
1. Font type, size and color can be specified. Tkinter will
apply a 'best-guess' font type if the exact font specified is not available 
on your platform.

2. Text column position. 
canvas_1.create_text(orig_x, orig_y ...)
This is the nail in the board to which the text column will be anchored.
The anchoring position puts the nail top-left (NW), top-right (NE)
bottom-left (SW) or bottom-right (SE) etc.
By default the column is centered on the nail at orig_x, orig_y.

3. Column anchor position.
canvas_1.create_text(orig_x, orig_y, anchor = NE ...)
 Text posiotion orig_x, orig_y is where the text is 
centered (by default). This can be overriden by anchor options like "anchor= NW".
AnchorPos specifies which part of the text (the text's bounding box, more exactly)
that should be placed at the given position. 
Use one of N, NE, E, SE, S, SW, W, NW or CENTER. Default is CENTER. 

4. Column width.
canvas_1.create_text(orig_x, orig_y , anchor = NE ,...width=200...)
The width is in screen pixels. All the text will adjusted to fit within this
column width. The first word on a new line will start at extreme right or left
depending on whether 'justify= XXX' is specified. Default is LEFT.
If this option is zero (the default)
the text is broken into lines only at newline characters.

5. justify=which-side
canvas_1.create_text(orig_x, orig_y, anchor = NE, width=200,...justify=RIGHT)
Specifies how to justify (shift right or left) the text within its bounding region.
'which-side' must be one of the values "LEFT", "RIGHT", or "CENTER". 
This option will only matter if the text is displayed as multiple lines. 
If the option is omitted, it defaults to left. 

Author:          Mike Ohlson de Fine
"""
# justify_align_text_1.py
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
 
jolly_text = "And now ladies and gentlemen she will attempt - for the very first time in the history of 17 Shoeslace street - a one handed right arm full toss teacup fling. Yes you lucky listners you are about to witness what, in recorded history, has never been attempted before without the aid of hair curlers and fluffy slippers."
# width is maximum line length. Lare broken before a space and a 'newline' added. 
#================================================================================
# 1. Top-left (NE) ANCHOR POINT, no column justification specified.
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                          fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE ,text="1. NORTH-EAST anchor,\
                          no column justification", fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NE, text=jolly_text,\
                             fill='blue', width=150, font='Arial 10')
#================================================================================
# 2. Top-right (NW) ANCHOR POINT, no column justification specified.
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW ,text="2. NORTH-WEST ancho,\
                          no column justification", fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NW, text= jolly_text,\
                             fill='red', width=200, font='Arial 10')
#================================================================================
orig_x = 600
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='black', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE ,text="3. SOUTH-EAST anchor,\
                          no column justification",fill='black', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='#666666', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW ,text="4. SOUTH-WEST anchor,\
                       no column justification", fill='#666666', width=200, font='Arial 10 ') 
#============================================================
orig_x = 600
orig_y  = 280   
# 3. BOTTOM-LEFT (SW) JUSTIFICATION, no column justification specified.
canvas_1.create_text(orig_x, orig_y + 2 * offset_y, anchor = SW, text=jolly_text,\
                             fill='#666666', width=200, font='Arial 10')
#================================================================================
# 4. TOP-RIGHT (SE) ANCHOR POINT, no column justification specified.
canvas_1.create_text(orig_x, orig_y +  2 * offset_y, anchor = SE, text=jolly_text,\
                             fill='black', width=150, font='Arial 10')
#================================================================================
orig_y  = 350
orig_x = 200  
# 5. Top-right (NE) ANCHOR POINT, RIGHT column justification specified.
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE , justify=RIGHT,\
                                 text="5. NORTH-EAST anchor, justify=RIGHT",\
                                 fill='blue', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NE, justify=RIGHT, text=jolly_text,\
                             fill='blue', width=150, font='Arial 10')
#================================================================================
# 6. TOP-LEFT (NW) ANCHOR POINT, RIGHT column justification specified.
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW ,text="6. NORTH-WEST anchor,\
                            justify=RIGHT", fill='red', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NW, justify=RIGHT, text=jolly_text,\
                             fill='red', width=200, font='Arial 10')

#================================================================================
orig_x = 600
# Header lines for 7. and 8.
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='black', width=160, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE ,text="7. SOUTH-EAST anchor,\
                             justify= CENTER", fill='black', width=160, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='gray', width=200, font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW , \
                                 text="8. SOUTH-WEST anchor, justify= CENTER",\
                                 fill='#666666', width=200, font='Arial 10 ') 
#================================================================================
orig_y  = 600 
# 7. TOP-RIGHT (SE) ANCHOR POINT, CENTER column justification specified.
canvas_1.create_text(orig_x, orig_y + 4 * offset_y, anchor = SE, justify= CENTER, text=jolly_text,\
                             fill='black', width=150, font='Arial 10')
#===============================================================================  
# 8. BOTTOM-LEFT (SW) ANCHOR POINT, CENTER column justification specified.

canvas_1.create_text(orig_x, orig_y + 4 * offset_y, anchor = SW, justify= CENTER, text=jolly_text,\
                             fill='#666666', width=200, font='Arial 10')

root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
