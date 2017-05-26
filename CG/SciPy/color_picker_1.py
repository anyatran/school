"""
Program name: color_picker_1 .py
Objective: Use Tkinters' tkColorChooser module to mix colors.

Keywords: canvas, Color Chooser, tkcolorChooser, mixer, color
============================================================================79 
Explanation: The appearance and behavior of this tool is significantly different in MS Windows and in Linux (Ubuntu 9.10).

Author:          Mike Ohlson de Fine

"""
#  color_picker_1 .py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
from tkColorChooser import askcolor

askcolor()
mainloop()
