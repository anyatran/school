"""
Program name: button_interaction_1.py
Objective: Using buttons to control other buttions including the button
that itself was pushed.

Keywords: canvas, button, validation, geometry, modify button properties
============================================================================79
 
Explanation: 
The syntax to achieve the modification of buttons was hard to discover,
but thanks to "Thinking in Tkinter" by Stephen Ferg, the way WAS discovered.
Thank you Mr. Ferg.

Author:          Mike Ohlson de Fine
  
"""
# button_interaction_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *

root = Tk()
root.title("now what?")

def callback_button_1():   
   message_button_1.flash()
   message_button_2["bg"]= "grey"
   message_button_2.flash()
   message_button_3.flash()
   message_button_3["bg"]= "pink"
   message_button_1["relief"] = SUNKEN
   message_button_1["text"]= "What have you done?"
 
def callback_button_2():
   message_button_2["bg"]= "green"
   message_button_3["bg"]= "cyan"
   message_button_1["relief"] = RAISED
   message_button_1["text"]= "Beware"

def callback_button_3():
   message_button_1.destroy()
   message_button_2.destroy()
   message_button_3.destroy()
   root.destroy()

message_button_1 = Button(root, 
                        bd=6,                      
                        relief = RAISED,           # Raised appearance.
                        bg = "blue",               # normal background color
                        fg = "green",              # normal foreground (text) color
                        font = "Arial 20 bold",
                        text ="Push me first",     # text on button
                        activebackground = "red",  # background when button has focus
                        activeforeground = "yellow", # text with focus
                        command = callback_button_1) # event handler
message_button_1.grid(row=0, column=0)

message_button_2 = Button(root, 
                        bd=6,                      
                        relief = SUNKEN,          
                        bg = "green",             
                        fg = "blue",               
                        font = "Arial 20 bold",
                        text ="Now Push me",      
                        activebackground = "purple", 
                        activeforeground = "yellow", 
                        command = callback_button_2) 
message_button_2.grid(row=1, column=0)

message_button_3 = Button(root, 
                        bd=6,                     
                        relief = SUNKEN,           
                        bg = "grey",               
                        fg = "blue",               
                        font = "Arial 20 bold",
                        text ="kill everything",      
                        activebackground = "purple",  
                        activeforeground = "yellow",  
                        command = callback_button_3) 
message_button_3.grid(row=2, column=0)

root.mainloop()

