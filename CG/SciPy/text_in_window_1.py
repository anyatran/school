"""
Program name: text_in_window_1.py
Objective: Write text in a standard Tkinter window.

Keywords: text, window
============================================================================79
 
Explanation: A simple way to present text in a window.

Author:          Mike Ohlson de Fine

"""
# text_in_window_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Text in  a window")

text_on_window = Text(root)
text_on_window.grid(row=0, column=0)

for i in range(20):
   text_on_window.insert(END,  "Fill an area with some text: line %d\n" % i)

root.mainloop()
