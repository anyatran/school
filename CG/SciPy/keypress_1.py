"""
Program name: keypress_1.py
Objective:Displays the key_symbol (keysym) for each KeyPress event as 
keys are pressed.

Keywords: canvas, key-press, validation
============================================================================79
 
Explanation: The message printed in the text box confirms that the code works.

Author:          Mike Ohlson de Fine

"""
# keypress_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Key symbol Getter")
        
def key_was_pressed(event):
    print 'keysym=%s' % (event.keysym)

text_1  = Text(root, width=20, height=5, highlightthickness=15)
text_1.grid(row=0, column=0)

text_1.focus_set()
root.mainloop()
