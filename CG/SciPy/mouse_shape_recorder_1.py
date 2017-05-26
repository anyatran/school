"""
Program name: mouse_shape_recorder_1.py

Objective: Entry of data from the mouse.

Keywords: canvas, button, validation
============================================================================79

Explanation: Every time the mouse is clicked an input "event" is generated.
This event causes the callback function to be called.
The callback function reads the mouse position at the time of the click and
assigns the integer values of mouse position in the frame to the variables
event.x and event.y. These are printed on the terminal.

Author:          Mike Ohlson de Fine

"""
# mouse_shape_recorder_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Mouse Drawn Shape Saver") 
cw = 600                                      # canvas width
ch = 400                                      # canvas height
chart_1 = Canvas(root, width=cw, height=ch, background="#ffffff")
chart_1.grid(row=1, column=1)

pt = [0]
x0 = [0]
y0 = [0]
count_point = 0
x_end = 10
y_end = 10
#=================================================================
# Create a new circle where the click happens and draw a new line
# segment to the last point (where the mouse was left clicked).
def callback_1(event):        # Left button pressed.
    global count_point,  x_end, y_end
    global x0, y0
    global x0_n, y0_n, pt

    x_start = x_end
    y_start = y_end
    x_end = event.x
    y_end = event.y
    chart_1.create_line(x_start, y_start , x_end,y_end  , fill = "#0088ff")
    chart_1.create_oval(x_end-5,y_end-5, x_end+5, y_end+5, outline = "#0088ff")

    count_point += 1
    pt = pt + [count_point]
    x0 = x0 + [x_end]               # extend list of all points
    y0 = y0 + [y_end]

chart_1.bind("<Button-1>", callback_1)  # <button-1> left mouse button
#====================================================
#  1.    Button control to store segmented line
def callback_6():           
    global x0, y0
    xy_points = open('/constr/shape_xy_1.txt', 'w')
    xy_points.write(str(x0))
    xy_points.write('\n')
    xy_points.write(str(y0))
    xy_points.close()

Button(root,  text="Store", command=callback_6).grid(row=0, column=2)
#===========================================================
#  2.    Button control to retrieve line from file.
def callback_7():
    global x0, y0    # Stored list of mouse-click positions.
    xy_points = open('/constr/shape_xy_1.txt', 'r')
    x0 = eval(xy_points.readline())
    y0 = eval(xy_points.readline())
    xy_points.close()
    print "x0 = ",x0 
    print "y0 = ",y0

    for i in range(1, count_point):    # Re-plot the stored and retreived line                         
        chart_1.create_line(x0[i], y0[i] ,    x0[i+1], y0[i+1] , fill = "#0088ff")
        chart_1.create_oval(x_end - 5,y_end - 5, x_end + 5, y_end  + 5 , outline = "#0088ff")

Button(root, text="retrieve", command=callback_7).grid(row=1, column=2)
#=============================================
#  3.    Button control to clear canvas
def callback_8():
    chart_1.delete(ALL)

Button(root,  text="CLEAR", command=callback_8).grid(row=2, column=2) 

root.mainloop()
