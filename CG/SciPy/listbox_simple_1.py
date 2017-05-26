"""
Program name: listbox_simple_1.py
Objective: Get a reaction to a  menu selection usinga mouse click.

Keywords: listbox, validation, list selection
============================================================================79
 
Explanation: This is the possible the simplest and most straight-forward
option selection mechanism.
The message printed on the screen confirms that the code works.

Author:          Mike Ohlson de Fine

"""
# listbox_simple_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Listbox Data Input")

def get_list(event):
    # Mouse button release callback
    # Read the listbox selection and put the result in an entry box widget
    index = listbox1.curselection()[0]   # get selected line index
    seltext = listbox1.get(index)        # get the line's text & assign to a variable
    enter_1.delete(0, 50)                 # delete previous text in enter1 otherwise the entries append to each other.
    enter_1.insert(0, seltext)            # now display the selected text

# Create the listbox (note that size is in characters)
listbox1 = Listbox(root, width=50, height=6)
listbox1.grid(row=0, column=0)

# Fill the listbox with data
listbox1.insert(END, "a list entry")
for item in ["one has begun", "two is a shoe", "three like a knee", "four to the door"]:
    listbox1.insert(END, item)

# use entry widget to display/edit selection
enter_1 = Entry(root, width=50, bg='yellow')
enter_1.insert(0, 'Click on an item in the listbox')
enter_1.grid(row=1, column=0)

# left mouse click on a list item to display selection
listbox1.bind('<ButtonRelease-1>', get_list)
 
root.mainloop()
