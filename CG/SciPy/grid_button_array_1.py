"""
Program name: grid_button_array_1.py
Objective:Size and appearance count. Image size determines button size.

Keywords: canvas, multiple button, validation, geometry, size
============================================================================79
 
Explanation: 
Here you can see how the clever geometry manager fits the button size
to image used on the button.

Author:          Mike Ohlson de Fine
"""
# grid_button_array_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Pack Geometry Manager")

butn_NW  =   Button(root, bg='blue',text="NorthWest").grid(row=0, column=0)
butn_NW1 =   Button(root, bg='blue',text="Northwest").grid(row=0, column=1))
butn_NE1 =   Button(root, bg='blue',text="Northeast").grid(row=0, column=2)
butn_NE  =   Button(root, bg='blue',text="NorthEast").grid(row=0, column=3)

butn_N1W  =  Button(root, bg='sky blue',text="norWest").grid(row=1, column=0)
butn_N1W1 =  Button(root, bg='sky blue',text="norwest").grid(row=1, column=1)
butn_S1E1 =  Button(root, bg='pale green',text="soueast").grid(row=1, column=2)
butn_S1E  =  Button(root, bg='pale green',text="souEast").grid(row=1, column=3)

butn_SW  =   Button(root, bg='green',text="SouthWest").grid(row=2, column=0)
butn_SW1 =   Button(root, bg='green',text="SothuWest").grid(row=2, column=1)
butn_SE1 =   Button(root, bg='green',text="SouthEast").grid(row=2, column=2)
butn_SE  =   Button(root, bg='green',text="SouthEast").grid(row=2, column=3)

root.mainloop()

