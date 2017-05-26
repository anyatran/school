"""
Program name: widget_configuration_1.py
Objective: Configure a Tkinter widget.

Keywords: widget, configuration
============================================================================79

Explanation: A widget's config method can be called at any time, 
instead of passing all of them to the object's constrctor.

Author:          Mike Ohlson de Fine

"""
# widget_configuration_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk( )
labelfont = ('times', 30, 'bold')
widget = Label(root, text='Purple and red mess with your focus')
widget.config(bg='red', fg='purple')
widget.config(font=labelfont)
widget.config(bd=6)
widget.config(relief=RAISED)
widget.config(height=2, width=40)  # lines high, characters wide
widget.pack(expand=YES, fill=BOTH)
root.mainloop( )

