"""
Program name: scrollbar_1.py
Objective: Use a scrollbar to move an image across a viewing window.

Keywords: image, scrollbar, validation
============================================================================79
 
Explanation: A scrollbar is used to move and position a canvas, text ,listbox
or entry widget inside a viewing window.

Author:          Mike Ohlson de Fine

"""
# scrollbar_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *

root = Tk()

frame_1 = Frame(root, bd=2, relief=SUNKEN)
frame_1.grid(row=0, column=0)

pic_1 = PhotoImage(file="/constr/pics1/table_glass_vase.gif")

yscrollbar = Scrollbar(frame_1, orient=VERTICAL, bg="skyblue",activebackground="blue")
yscrollbar.grid(row=0, column=1, sticky=N+S)

canvas_1 = Canvas(frame_1, bd=0, scrollregion=(0, 0, 2100, 2000),  # The extent of the scrollable area.
                yscrollcommand=yscrollbar.set,     # Link to the scrollbar.
)
canvas_1.grid(row=0, column=0)
canvas_1.create_image(0 ,0 ,anchor=NW, image= pic_1)

yscrollbar.config(command=canvas_1.yview)          # Link to the canvas.

mainloop()
