"""
  
Program name: button_array_1.py
Objective:Size and appearance count. Image size determines button size.

Keywords: canvas, multiple buttonS, validation, geometry, size
============================================================================79
 
Explanation: 
Here you can see how the clever geometry manager fits the button size
to image used on the button.
For reference the image sizes are: E = 100x100, D = 40x40, C = 20x40, 
                                   B = 40x20,   A = 20x20

Placing all the buttons on the same row or column works but looks untidy.
Try it yourself.
Note: If you use windows then the forward slashes must be changed to backslashes. 

Author:          Mike Ohlson de Fine
"""
# button_array_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Button Array")

usb =       PhotoImage(file = "C:/constr/PICS/Usbkey_D.gif")
galaxy =    PhotoImage(file = "C:/constr/PICS/galaxy_D.gif")
alert =     PhotoImage(file = "C:/constr/PICS/alert_D.gif")
earth =     PhotoImage(file = "C:/constr/PICS/earth_D.gif")

eye =       PhotoImage(file = "C:/constr/PICS/eye_D.gif")
rnd_2 =  PhotoImage(file = "C:/constr/PICS/random_2_D.gif")
rnd_3 =  PhotoImage(file = "C:/constr/PICS/random_3_D.gif")

smile =     PhotoImage(file = "C:/constr/PICS/smile_D.gif")
vine =      PhotoImage(file = "C:/constr/PICS/vine_D.gif")
blueye =    PhotoImage(file = "C:/constr/PICS/blueeye_D.gif")
winglion =  PhotoImage(file = "C:/constr/PICS/winglion_D.gif")

def cb_usb():       print "usb"
def cb_galaxy():    print "galaxy"
def cb_alert():     print "alert"
def cb_earth():     print "earth"

def cb_eye():       print "eye"
def cb_rnd_2():     print "random_2"
def cb_rnd_3():     print "random_3"

def cb_smile():     print "smile"
def cb_vine():      print "vine"
def cb_blueeye():   print "blueeye"
def cb_winglion():  print "winglion"

butn_usb    =  Button(root, image = usb,    command=cb_usb   ).grid(row=0, column=0)
butn_galaxy =  Button(root, image = galaxy, command=cb_galaxy).grid(row=1, column=0)
butn_alert  =  Button(root, image = alert,  command=cb_alert ).grid(row=2, column=0)
butn_earth  =  Button(root, image = earth,  command=cb_earth ).grid(row=3, column=0)

butn_eye    =  Button(root, image = eye,   command=cb_eye    ).grid(row=0, column=1, rowspan=2)
butn_rnd_2  =  Button(root, image = rnd_2, command=cb_rnd_2  ).grid(row=2, column=1)
butn_rnd_3  =  Button(root, image = rnd_3, command=cb_rnd_3  ).grid(row=3, column=1)

butn_smile  = Button(root, image = smile,  command=cb_smile  ).grid(row=0, column=2, columnspan=2)
butn_vine   = Button(root, image = vine,   command=cb_vine   ).grid(row=1, column=2, rowspan=2, columnspan=2)
butn_blueye = Button(root, image = blueye, command=cb_blueeye).grid(row=3, column=2)

butn_winglion= Button(root, image = winglion, command=cb_winglion ).grid(row=3, column=3)

root.mainloop()

