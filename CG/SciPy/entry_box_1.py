"""
Program name: entry_box_1.py
Objective: Simple text input GUI and validation by assignment to a variable
           with verification display as a label.

Keywords: canvas, button, validation, text box, data entry panel
============================================================================79
 
Explanation: 
A data entry box (dialog box) is created using the 'Entry(root)' method.
A push of a button fires off the associated 'callback' function
which does two things:
First it assigns the value inside the box to a variable "data_inp_1",
and then immediately displays that variable as a text label on the far right.

Note that callback function variables are automatically included in the 
namespace of the functions that call them - you don't have to declare the 
assigned '.get()' variables as global ones. 
Note also that the window expands to accomodate the displayed data. Very neat.

Author:          Mike Ohlson de Fine

"""   
# entry_box_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
from Dialog import Dialog
root = Tk()
root.title("Data Entry Box")

enter_data_1 = Entry(root, bg = "pale green")  # creates a text entry field
enter_data_1.grid(row=1, column=1)
enter_data_1.insert(0, "enter text here") # Place text into the box.

def callback_origin():
    # Push button event handler.
    data_inp_1 =  enter_data_1.get()      # Fetch text from the box.

    # Create a label write the value of 'data_inp_1' to it.
    # ie. Validate by displaying the newly acquired data as a label on the frame.
    label_2_far_right = Label(root, text=data_inp_1) 
    label_2_far_right.grid(row=1, column=3)

# This is button that triggers data transfer from entry box to named variable 'data_inp_1'.
but1= Button(root, text="press to transfer",command=callback_origin).grid(row=5, column=0)

root.mainloop()

