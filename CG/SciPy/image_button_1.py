"""
  
Program name: image_button_1.py
Objective:Size and appearance count. Image size determines button size.

Keywords: canvas, multiple button, validation, geometry, size
============================================================================79
 
Explanation: 
Here you can see how the clever geometry manager fits the button size
to image used on the button.
For reference the image sizes are:
"go_on.gif" is 60 x 30 pixels (occupying 2 kilo Bytes),
"fireman_1.gif" is 56 x 100 pixels (occupying 2.3 kilo Bytes),
"winged_lion.gif" is 172 x 200 pixels (occupying 5.7 kilo Bytes),
"earth.gif" is 398 x 407 pixels (occupying 101.7 kilo Bytes).

The "go_on.gif" is about the fight size to allow decent text readibility.  

Placing all the buttons on the same row or column works but looks untidy.
Try it yourself.

Author:          Mike Ohlson de Fine
License:         GPL1      
"""
# image_button_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Image Sized Buttons")

go_image =          PhotoImage(file = "/constr/pics1/go_on.gif")
fireman_image =     PhotoImage(file = "/constr/pics1/fireman_1.gif")
winged_lion_image = PhotoImage(file = "/constr/pics1/winged_lion.gif")
earth_image =       PhotoImage(file = "/constr/pics1/earth.gif")

def callback_go():
    print "Go has been pushed to no purpose"

def callback_fireman():
    print "A little plastic fireman is wanted"

def callback_lion():
    print "A winged lion rampant would look cool on a t-shirt"

def callback_earth():
    print "Think of the children (and therefore also of their parents)"

btn_go=      Button(root, image = go_image,         \
                    command=callback_go     ).grid(row=0, column=0)
btn_firmean= Button(root, image = fireman_image,     \
                    command=callback_fireman).grid(row=0, column=1)
btn_lion=    Button(root, image = winged_lion_image, \
                    command=callback_lion   ).grid(row=0, column=2)
btn_earth=   Button(root, image = earth_image,      \
                    command=callback_earth  ).grid(row=0, column=3)

root.mainloop()

