"""
Program name: mouseclick_1.py
Objective: Entry of data from the mouse.

Keywords: canvas, button, mouse, callback, event handler,  validation
============================================================================79

Explanation: Every time the mouse is clicked an input "event" is generated.
This event automatically causes the callback function to be called.
The callback function reads the mouse position at the time of the click and
assigns the integer values of mouse position in the frame to the variables
event.x and event.y. These are printed on the terminal to verify the actions.

Author:          Mike Ohlson de Fine

"""
# mouseclick_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()

frame = Frame(root, width=100, height=100)

def callback(event):
    print "clicked at", event.x, event.y

frame.bind("<Button-1>", callback)
frame.grid()

root.mainloop()
root.destroy()
