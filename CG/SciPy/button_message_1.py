"""
Program name: button_message_1.py
Objective: Using a message box as a response to a button push.

Keywords: canvas,button, validation, geometry, message box
============================================================================79
 
Explanation: 
This is a fairly typical way to respond to the pushing of a button. It need
not be the only response. Also a self-explanatory label has been placed
on the button.

Author:          Mike Ohlson de Fine

"""
# button_message_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import tkMessageBox
root = Tk()
root.title("Message Button")

def callback_button():
   tkMessageBox.showinfo( "Certificate of Button Pushery", "Are glowing pixels a reality?")

message_button = Button(root, 
                        bd=6,                      # border width
                        relief = RAISED,           # raised appearance to button border
                        bg = "blue",               # normal background color
                        fg = "green",              # normal foreground (text) color
                        font = "Arial 20 bold",
                        text ="Push me",           # text on button
                        activebackground = "red",  # background when button is clicked
                        activeforeground = "yellow", # text color when clicked
                        command = callback_button) # name of event handler

message_button.grid(row=0, column=0)
root.mainloop()

