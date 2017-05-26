"""
Program name: 4lines_1.py
Objective: Four straight lines, different styles on a canvas.

Keywords: canvas, line, , color, dashed line, default
============================================================================79
 
Explanation: When drawing lines you MUST specify the start and end points. 
different colors, line widths and styles have been used here.
Here variable names are used instead of numerical values. 

Author:          Mike Ohlson de Fine

"""

#4lines_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Different line styles')
cw = 280                                      # canvas width
ch = 120                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="spring green")
canvas_1.grid(row=0, column=1)

x_start = 20
y_start = 20
x_end = 180
y_end = 20
canvas_1.create_line(x_start, y_start, x_end,y_end,\
                     dash=(3,5), arrow="first", width = 3) 
  
x_start= x_end
y_start= y_end
x_end= 50
y_end= 70
canvas_1.create_line(x_start, y_start, x_end,y_end,\
                     dash=(9), width = 5, fill= "red")

x_start= x_end
y_start= y_end
x_end= 150
y_end= 70
canvas_1.create_line(x_start, y_start, x_end,y_end, \
                     dash=(19),width= 15, caps="round", fill= "dark blue")

x_start= x_end
y_start= y_end
x_end= 80
y_end= 100                                       
canvas_1.create_line(x_start, y_start, x_end,y_end, fill="purple")    
#width reverts to default= 1 in absence of explicit  spec.
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
