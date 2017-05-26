"""
Program name: align_text.py
Objective: Place different colored texts and font types on a canvas.

Keywords: canvas, text, color, font
============================================================================79
 
Explanation:
1. Font type, size and color can be specified. Tkinter will
apply a 'best-guess' font if the exact font specified is not available 
on your platform.

2. anchor=anchorPos:  Text posiotion orig_x, orig_y is where the text is centered (by default).
   This can be overriden by anchor options like "anchor= NW".

2. anchor=anchorPos 
AnchorPos Specifies which part of the text (the text's bounding box, more exactly) that should be placed at the given position. 
Use one of N, NE, E, SE, S, SW, W, NW or CENTER. Default is CENTER. 

justify=how (ACTIVE WHEN LINE OVERFLOWS OCCUR)
Specifies how to justify the text within its bounding region. How must be one of the values "LEFT", "RIGHT", or "CENTER". This option will only matter if the text is displayed as multiple lines. If the option is omitted, it defaults to left. 

3. tags=tagList 
Specifies a set of tags to apply to the item. TagList consists of a list of tag names, which replace any existing tags for the item. TagList may be an empty list. 

4. width=lineLength 
Specifies a maximum line length for the text. If this option is zero (the default) the text is broken into lines only at newline characters. However, if this option is non-zero then any line that would be longer than lineLength is broken just before a space character to make the line shorter than lineLength; the space character is treated as if it were a newline character. 


Author:          Mike Ohlson de Fine
"""
# align_text.py 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
from Tkinter import *
root = Tk()
root.title('Anchoring Text North, South, East, West')
cw = 900                                     # canvas width 
ch = 630                                      # canvas height 
canvas_1 = Canvas(root, width=cw, height=ch, background="white") 
canvas_1.grid(row=0, column=1)                              

orig_x = 220
orig_y  = 20   
offset_y = 30   
 
# 1. DEFAULT CENTER JUSTIFICATION 
# width is maximum line length. Lare broken before a space and a 'newline' added. 
canvas_1.create_text(orig_x, orig_y ,  text="1========|========20",\
                                 fill='red', width=700, font='SansSerif 20 ') 
canvas_1.create_text(orig_x, orig_y + offset_y,  text="1. CENTER anchor",\
                                 fill='red', width=700, font='SansSerif 20 ')                        
canvas_1.create_text(orig_x, orig_y + 2 *offset_y,  text="text normal SansSerif 20",\
                                 fill='red', width=700, font='SansSerif 20 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y,  text="text normal Arial 20",\
                                 fill='blue', width=700,  font='Arial 20 ')
canvas_1.create_text(orig_x, orig_y + 4 * offset_y,  text="text bold Courier 20",\
                                 fill='green', width=700, font='Courier 20 bold') 
#================================================================================
orig_x = 550
# 2. LEFT JUSTIFICATION      
canvas_1.create_text(orig_x, orig_y,  text="1========|========20",\
                              fill='black', anchor = NW, width=700, font='SansSerif 16 ') 
canvas_1.create_text(orig_x, orig_y + offset_y,  text="2. NORTH-WEST anchor",\
                              fill='black', anchor = NW, width=700, font='SansSerif 16 ')                    
canvas_1.create_text(orig_x, orig_y + 2 *offset_y,  text="text normal SansSerif 16",\
                              fill='black', anchor = NW, width=700, font='SansSerif 16 ') 
canvas_1.create_text(orig_x, orig_y + 3 *offset_y,  text="text bold SansSerif 16",\
                              anchor = NW, width=700,  font='SansSerif 16 bold') 
canvas_1.create_text(orig_x, orig_y + 4 * offset_y,  text="text italic SansSerif 16",\
                             anchor = NW, width=700, font='SansSerif 16 italic') 
canvas_1.create_text(orig_x, orig_y + 5 * offset_y, text="bold italic SansSerif 16",\
                             anchor = NW, width=700, font='SansSerif 16 bold italic')
#===============================================================================
orig_x = 350
# 3. DEFAULT CENTER JUSTIFICATION 
canvas_1.create_text(orig_x, orig_y + 7 * offset_y,  text="1========|========20",\
                                 fill='red', width=700, font='SansSerif 20 ') 
canvas_1.create_text(orig_x, orig_y + 8 * offset_y,  text="3. CENTER anchor",\
                                 fill='red', width=700, font='SansSerif 20 ') 
canvas_1.create_text(orig_x, orig_y + 9 * offset_y,  text="abc", \
                                 fill='red',width=700, font='SansSerif 20 ') 
canvas_1.create_text(orig_x, orig_y + 10 * offset_y,  text="abcdefghijkl",\
                                fill='green', width=700, font='Courier 20 bold') 
#===============================================================================
# 4. DEFAULT CENTER JUSTIFICATION 
orig_y  = 360 
offset_y = 20 
canvas_1.create_text(orig_x, orig_y,  text="4. CENTER anchor",\
                               fill='red', width=700, font='SansSerif 10 ')                           
canvas_1.create_text(orig_x, orig_y + offset_y,  text="text normal SansSerif 10",\
                               fill='red', width=700, font='SansSerif 10 ') 
canvas_1.create_text(orig_x, orig_y + 2 * offset_y,  text="text normal Arial 10",\
                               fill='blue', width=700,  font='Arial 10 ')
canvas_1.create_text(orig_x, orig_y + 3 * offset_y,  text="text bold Courier 10",\
                               fill='green',  width=700, font='Courier 10 bold') 
canvas_1.create_text(orig_x, orig_y + 4 * offset_y, text="bold italic BookAntiqua 10",\
                             fill='violet', width=700, font='Bookantiqua 10 bold')
#================================================================================
# 5. DEFAULT TOP-LEFT (NW) JUSTIFICATION
orig_y  = 480
offset_y = 20
canvas_1.create_text(orig_x, orig_y , anchor = NW ,text="1========|========20",\
                                 fill='red', width=500, font='SansSerif 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NW ,text="5. NORTH-WEST anchor",\
                                 fill='red', width=500, font='SansSerif 10 ') 
canvas_1.create_text(orig_x, orig_y + 2 * offset_y,  anchor = NW, text="abc", \
                                 fill='red', width=700, font='SansSerif 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NW, text="abcdef",\
                                 fill='red', width=500,  font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 4 * offset_y, anchor = NW,  text="abcdefghijkl",\
                                 fill='red', width=500, font='Courier 10 bold') 
canvas_1.create_text(orig_x, orig_y + 5 * offset_y, anchor = NW, text="abcdefghijklmnopqrstuvwxyz",\
                             fill='red', width=500, font='Bookantiqua 10 bold')

#================================================================================
# 6. DEFAULT Top-right (SE) JUSTIFICATION
canvas_1.create_text(orig_x, orig_y , anchor = NE , text="1========|========20",\
                                 fill='blue', width=500, font='SansSerif 10 ') 
canvas_1.create_text(orig_x, orig_y + 1 * offset_y, anchor = NE ,text="6. NORTH-EAST anchor",\
                                 fill='blue', width=500, font='SansSerif 10 ') 
canvas_1.create_text(orig_x, orig_y + 2 * offset_y,  anchor = NE, text="abc",\
                                 fill='blue', width=700, font='SansSerif 10 ') 
canvas_1.create_text(orig_x, orig_y + 3 * offset_y, anchor = NE, text="abcdef",\
                                 fill='blue', width=500,  font='Arial 10 ') 
canvas_1.create_text(orig_x, orig_y + 4 * offset_y, anchor = NE,  text="abcdefghijkl",\
                                 fill='blue', width=500, font='Courier 10 bold') 
canvas_1.create_text(orig_x, orig_y + 5 * offset_y, anchor = NE, text="abcdefghijklmnopqrstuvwxyz",\
                             fill='blue', width=500, font='Bookantiqua 10 bold')
root.mainloop() 
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
