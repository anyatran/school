"""
Program name: checkbox_1.py
Objective: Each check (tick) makes a boolean variable true (default action).

Keywords: canvas, checkbox, exclusion, validation
============================================================================79
 
Explanation: This is a basic checkbox input program.

Author:          Mike Ohlson de Fine

A checkboxes holds a value, unlike buttons which just trigger
an event. 

Checkbuttons use the following options: 
The "text", "textvariable", "image", and "compound" options control the display 
of the label (next to the check box itself).

The "state" and "instate" methods allow you to manipulate the "disabled" 
state flag to enable or disable the checkbutton.

The "command" option lets you specify a script to be called everytime 
the user toggles the checkbutton.

The "invoke" method will also execute the same callback.

Widget Value
The "variable" option for checkbox is used to read or
change the current value of the widget, and it updates whenever 
the widget is toggled (changed). 
By default, checkbox use a value of "1" when the widget is checked, and "0" when not 
checked, but these can be changed to just about anything using the "onvalue" and "offvalue" options.

What happens when the linked variable contains neither the on value or the off value 
(or even doesn't exist)? In that case, the checkbutton is put into a special "tristate" 
or indeterminate mode; you'll sometimes see this in user interfaces where the checkbox
holds a single dash rather than being empty or holding a check mark. When in this state, 
the state flag "alternate" is set, so you can check for it with the "instate" method:

"""
# checkbox_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import tkMessageBox
root = Tk()
root.title("Checkboxes")
check_var1 = IntVar()
check_var2 = IntVar()
check_var3 = StringVar()
check_var4 = StringVar()

def change_it():
   print "Why do you want to change things?"

Ck_1 = Checkbutton(root, text = "Dog", variable = check_var1, command=change_it, \
                 onvalue = 1, offvalue = 0, height=3, \
                 width = 10).grid(row=0, column=0)
Ck_2 = Checkbutton(root, text = "Cat", variable = check_var2, \
                 onvalue = 1, offvalue = 0, height=6, \
                 width = 10).grid(row=1, column=0)
Ck_3 = Checkbutton(root, text = "Rat", variable = check_var3, \
                 onvalue = "fly me", offvalue = "keep walking", height=9, \
                 width = 10).grid(row=2, column=0)
Ck_4 = Checkbutton(root, text = "Frog", variable = check_var4, \
                 onvalue = "to the moon", offvalue = "to Putney road", height=12, \
                 width = 10).grid(row=3, column=0)

def callback_1():
    v_1 = check_var1.get() 
    v_2 = check_var2.get()
    v_3 = check_var3.get()  
    v_4 = check_var4.get()        
    print v_1, v_2, v_3, v_4

button_1= Button(root, command=callback_1).grid(row=4, column=0)

root.mainloop()

