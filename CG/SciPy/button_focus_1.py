"""
Program name: button_focus_1.py
Objective: Configure Tkinter button widgets with focus an appearance.

Keywords: widget, configuration, button, focus, appearance
============================================================================79

Explanation: When the cursor is over a button a mouse click is directed only
to that particular button - it has 'focus'.

Author:          Mike Ohlson de Fine

"""
#button_focus_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *

butn_widget_1 = Button(text='First, RAISED', padx=10, pady=10)
butn_widget_1.config(cursor='gumby')
butn_widget_1.config(bd=8, relief=RAISED)
butn_widget_1.config(bg='dark green', fg='white')
butn_widget_1.config(font=('helvetica', 20, 'underline italic'))
butn_widget_1.grid(row=1, column = 1)

butn_widget_2 = Button(text='Second, FLAT', padx=10, pady=10)
butn_widget_2.config(cursor='circle')
butn_widget_2.config(bd=8, relief=FLAT)
butn_widget_2.grid(row=1, column = 2)

butn_widget_3 = Button(text='Third, SUNKEN', padx=10, pady=10)
butn_widget_3.config(cursor='heart')
butn_widget_3.config(bd=8, relief=SUNKEN)
butn_widget_3.config(bg='dark blue', fg='white')
butn_widget_3.config(font=('helvetica', 30, 'underline italic'))
butn_widget_3.grid(row=1, column = 3)

butn_widget_4 = Button(text='Fourth, GROOVE', padx=10, pady=10)
butn_widget_4.config(cursor='spider')
butn_widget_4.config(bd=8, relief=GROOVE)
butn_widget_4.config(bg='red', fg='white')
butn_widget_4.config(font=('helvetica', 20, 'bold'))
butn_widget_4.grid(row=1, column = 4)

butn_widget_5 = Button(text='Fifth RIDGE', padx=10, pady=10)
butn_widget_5.config(cursor='pencil')
butn_widget_5.config(bd=8, relief=RIDGE)
butn_widget_5.config(bg='purple', fg='white')
butn_widget_5.grid(row=1, column = 5)

mainloop( )

