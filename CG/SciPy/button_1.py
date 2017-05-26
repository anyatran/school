"""
Program name: button_1.py
Objective:Get a reaction to a  button push.

Keywords: canvas, button, validation
============================================================================79
 
Explanation: This is the simplest button pushing program.
The message printed on the screen confirms that the code works.

Author:          Mike Ohlson de Fine

"""
# button_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()

def callback_1():
    print "Someone pushed a button"

button_1= Button(root, command=callback_1).grid(row=1, column=0)
root.mainloop()

