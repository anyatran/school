"""
Program name: pack_button_array_1.py
Objective :Pack layout geometry manager gives least control.

Keywords: multiple button, pack, geometry
============================================================================79
 
Explanation: 
You can use ther "pack" widget layout geometry manager or
the "grid" manager (but never both).

Author:          Mike Ohlson de Fine
"""
# pack_button_array_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Pack Geometry Manager")

butn_NW  =   Button(root, bg='blue',text="NorthWest").pack(side=LEFT)
butn_NW1 =   Button(root, bg='blue',text="Northwest").pack(side=LEFT)
butn_NE1 =   Button(root, bg='blue',text="Northeast").pack(side=LEFT)
butn_NE  =   Button(root, bg='blue',text="NorthEast").pack(side=LEFT)

butn_N1W  =  Button(root, bg='sky blue',text="norWest").pack()
butn_N1W1 =  Button(root, bg='sky blue',text="norwest").pack()
butn_S1E1 =  Button(root, bg='pale green',text="soueast").pack(side=BOTTOM)
butn_S1E  =  Button(root, bg='pale green',text="souEast").pack(side=BOTTOM)

butn_SW  =   Button(root, bg='green',text="SouthWest").pack(side=RIGHT)
butn_SW1 =   Button(root, bg='green',text="SothuWest").pack(side=RIGHT)
butn_SE1 =   Button(root, bg='green',text="SouthEast").pack(side=RIGHT)
butn_SE  =   Button(root, bg='green',text="SouthEast").pack(side=RIGHT)

root.mainloop()

