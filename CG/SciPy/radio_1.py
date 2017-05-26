"""
Program name: radiobuttons_1.py
Objective: How radiobuttons work - by mutual exclusion.

Keywords: canvas, radiobuttons, exclusion, validation
============================================================================79
 
Explanation: This is a basic radiobutton input program.

Author:          Mike Ohlson de Fine

"""
# radiobuttons_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk( )
root.title("Radiobuttons")
var_1 = StringVar( )

rad_1 = Radiobutton(root, text='violent', variable = var_1, value="action").grid(row=0, column=0)
rad_2 = Radiobutton(root, text='love', variable =  var_1, value="romance").grid(row=0, column=1)
rad_2 = Radiobutton(root, text='conflict', variable =  var_1, value="war").grid(row=0, column=2)

def callback_1():
    v_1 = var_1.get()    
    print v_1

button_1= Button(root, command=callback_1).grid(row=4, column=0)
root.mainloop()

