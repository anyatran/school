"""
Program name: arc_circle_1.py
Objective: A circle but this not the right way to draw a circle.

Keywords: canvas, arc, circle
============================================================================79
 
Explanation: A circle drawn from an arc always has an annoying radial
line that cannot be eliminated.

Author:          Mike Ohlson de Fine
"""
# arc_circle.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title('Should be a circle')
cw = 210                                      # canvas width
ch = 130                                      # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="gainsboro")
canvas_1.grid(row=0, column=1)                              

xy = 20, 20, 120, 120         # bounding box from x0,y0 to x1, y1
 # The Arc is drawn  from start_angle, in degrees to finish_angle.
# but if you try to complete the circle at 360 degrees it evaporates.
canvas_1.create_arc(xy, start=1, extent=359.999999999999999, fill= 'white')
root.mainloop()
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
