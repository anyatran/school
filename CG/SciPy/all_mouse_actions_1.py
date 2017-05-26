"""
Program name: all_mouse_actions_1.py
Test all 12 mouse actions
==============================================================================79

Explanation: There are 14 possible mouse button actions recognized.
For each button there is a press, a release, a double-click and a drag.
Left button is No 1, middle is No 2 and right is No 3.
There are also <enter> and <leave> events where the mouse pointer
enters and leaves specific widget areas - not used in this example.

For some mice the center button is a wheel that usually scrolls but Tkinter
ignores this this.

Author:          Mike Ohlson de Fine

"""
# all_mouse_actions_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Mouse follower")
# The Canvas here is bound to the mouse events
cw = 200                                      # canvas width
ch = 100                                      # canvas height
chart_1 = Canvas(root, width=cw, height=ch, background="#ffffff")
chart_1.grid(row=1, column=1)

#=========  Left Mouse Button Events  ===============
# callback events
def callback_1(event):
    print "left mouse clicked"

def callback_2(event):
    print "left dragged"

def callback_3(event):
    print "left doubleclick"

def callback_4(event):
    print "left released"
#========  Center Mouse Button Events ======================
def callback_5(event):
    print "center mouse clicked"

def callback_6(event):
    print "center dragged"

def callback_7(event):
    print "center doubleclick"

def callback_8(event):
    print "center released"
#======== Right Mouse Button Events ======================
def callback_9(event):
    print "right mouse clicked"

def callback_10(event):
    print "right dragged"

def callback_11(event):
    print "right doubleclick"
    
def callback_12(event):
    print "right released"

# <button-1> is the left mouse button
chart_1.bind("<Button-1>", callback_1)
chart_1.bind("<B1-Motion>", callback_2)
chart_1.bind("<Double-1>", callback_3)
chart_1.bind("<ButtonRelease-1>", callback_4)

 # <button-2> is the center mouse button
chart_1.bind("<Button-2>", callback_5)
chart_1.bind("<B2-Motion>", callback_6)
chart_1.bind("<Double-2>", callback_7)
chart_1.bind("<ButtonRelease-2>", callback_8)

# <button-3> is the right mouse button
chart_1.bind("<Button-3>", callback_9)
chart_1.bind("<B3-Motion>", callback_10)
chart_1.bind("<Double-3>", callback_11)
chart_1.bind("<ButtonRelease-3>", callback_12)

root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
