"""
Program name: frame_1.py
Objective: Grouping widgets inside frames

Keywords: frames, geometric arrangement
============================================================================79
 
Explanation: The position of frames are specified relative to the "root" window.
Inside each frame the widgets that belong to it are arranged without reference
to anything outside that frame. 

Author:          Mike Ohlson de Fine

"""
# frame_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.config(bg="black")
root.title("It's a Frame-up")

#================================================
# frame_1 and her motley little family 
frame_1 = Frame(root, bg="red", border = 4, relief="raised")
frame_1.grid(row=0, column=0, columnspan=2)

redbutton_1 = Button(frame_1, text="Red",bg ="orange", fg="red")
redbutton_1.grid(row=0, column=1)

greenbutton_1 = Button(frame_1, text="Brown",bg ="pink", fg="brown")
greenbutton_1.grid(row=1, column=2)

bluebutton_1 = Button(frame_1, text="Blue",bg ="yellow", fg="blue")
bluebutton_1.grid(row=0, column=3)
#================================================
# frame _2 and her neat blue brood 
frame_2 = Frame(root, bg="blue", border = 10, relief="sunken")
frame_2.grid(row=1, column=0)

redbutton_2 = Button(frame_2, text="Green",bg ="brown", fg="green")
redbutton_2.grid(row=0, column=1)

greenbutton_2 = Button(frame_2, text="Brown",bg ="green", fg="brown")
greenbutton_2.grid(row=2, column=2)

bluebutton_2 = Button(frame_2, text="Pink",bg ="gray", fg="black")
bluebutton_2.grid(row=3, column=3)

#================================================
# frame_3 with her friendly greenies
frame_3 = Frame(root, bg="green", border = 20, relief="groove")
frame_3.grid(row=1, column=1)

redbutton_3 = Button(frame_3, text="Purple",bg ="white", fg="red")
redbutton_3.grid(row=0, column=3)

greenbutton_3 = Button(frame_3, text="Violet",bg ="cyan", fg="violet")
greenbutton_3.grid(row=2, column=2)

bluebutton_3 = Button(frame_3, text="Cyan",bg ="purple", fg="blue")
bluebutton_3.grid(row=3, column=0)

root.mainloop()

