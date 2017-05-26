"""
Program name: mouse_shape_editor_1.py
Objective: A distance measuring and drawing function.
==============================================================================79

Explanation: Dragging the mouse leaves a trail of equidistant line segments.
the junction of each line segment is marked with  a circle whose position
can be adjusted by click-drag actions of the right mouse button.
 
How to use it:
1. Left mouse down starts a line,
2. As you drag the mouse it drops circles along the path at fixed distances,
3. Each circle position gets stored in lists x0, y0.
4. When you release left button the process stops.
5. Right click in any circle makes it editable - you can drag it to a new position.
6. Resume dragging the left mouse and the line continues.

Next Requirement: a) Make the ability to erase points.
                  b) Make the ability to work with unjoined segments.
                  c) Make the ability to select either click to create points ot
                  d) Drag fairy lights (equal length segments.
 
Author:          Mike Ohlson de Fine

"""
# mouse_shape_editor_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
import math
root = Tk()
root.title("Left drag to draw, right to re-position.")
cw = 600                                      # canvas width
ch = 650                                      # canvas height
chart_1 = Canvas(root, width=cw, height=ch, background="#ffffff")
chart_1.grid(row=1, column=1)

linedrag = {'x_start':0, 'y_start':0, 'x_end':0, 'y_end':0}
map_distance = 0
dist_meter = 0
x_initial = 0
y_initial = 0
#=============================================================
# Adjust the distance between points if desired
way_points = 50          # Distance between editable way-points
#=============================================================
magic_circle_flag = 0   # 0-> normal dragging, 1 -> double-click: Pull point.
point_num = 0
x0 = []
y0 = []
#============================================================================
def separation(x_now, y_now, x_dot, y_dot):     # DISTANCE MEASUREMENT
        #    Distance to points   - used to find out if the mouse clicked inside a circle
        sum_squares = (x_now - x_dot)**2 + (y_now -y_dot)**2
        distance= int(math.sqrt(sum_squares))   # Get Pythagorean distance
        return( distance)
#========================================================================
# CALLBACK EVENT PROCESSING FUNCTIONS
def callback_1(event):  # LEFT DOWN
    global x_initial, y_initial
    x_initial = event.x
    y_initial = event.y

def callback_2(event):  # LEFT DRAG
    global x_initial, y_initial
    global map_distance, dist_meter
    global x0, y0
    linedrag['x_start'] = linedrag['x_end']    # update positions
    linedrag['y_start'] = linedrag['y_end']
    linedrag['x_end'] = event.x
    linedrag['y_end'] = event.y

    increment = separation(linedrag['x_start'],linedrag['y_start'], linedrag['x_end'], linedrag['y_end'] )
    map_distance += increment                 # Total distance - potentiasl use as a map odometer.
    dist_meter += increment               # Distance from last circle

    if dist_meter>way_points:             # Action at way-points
        x0.append(linedrag['x_end'])                                # append to line
        y0.append(linedrag['y_end'])
        xb = linedrag['x_end'] - 5 ; yb = linedrag['y_end'] - 5   # Centre circle on line
        x1 = linedrag['x_end'] + 5 ; y1 = linedrag['y_end'] + 5
        chart_1.create_oval(xb,yb, x1,y1, outline = "green")
        dist_meter = 0                                 # re-zero the odometer.
        linexy = [ x_initial, y_initial, linedrag['x_end'] , linedrag['y_end']  ]
        chart_1.create_line(linexy, fill='green')
        x_initial = linedrag['x_end']                   # start of next segment
        y_initial = linedrag['y_end']


def callback_5(event):      # RIGHT CLICK
    global point_num, magic_circle_flag, x0, y0
    # Measure distances to each point in turn, determine if any are inside magic circle.
    # That is, identify which point has been clicked on.
    for i in range(0, len(x0)):
        d = separation(event.x,  event.y,  x0[i], y0[i])
        if d <= 5:
            point_num = i        # this is the index that controls editing
            magic_circle_flag = 1
            chart_1.create_oval(x0[i] - 10,y0[i] - 10, x0[i] + 10, y0[i]  + 10 , width = 4, outline = "#ff8800")
            x0[i] = event.x 
            y0[i] = event.y

def callback_6(event):      # RIGHT RELEASE
    global  point_num, magic_circle_flag, x0, y0
    if magic_circle_flag == 1:     # The point is going to be repositioned.
        x0[point_num] =event.x
        y0[point_num] =event.y
        chart_1.delete(ALL)
        chart_1.update()           # Refreshes the drawing on the canvas.
        q=[]
        for i in range(0,len(x0)):
            q.append(x0[i])
            q.append(y0[i])
            chart_1.create_oval(x0[i] - 5,y0[i] - 5, x0[i] + 5, y0[i]  + 5 , outline = "#00ff00")
        chart_1.create_line(q  , fill = "#ff00ff")    # Now show the new positions
        magic_circle_flag = 0

#==============================

chart_1.bind("<Button-1>", callback_1)  # <Button-1>  ->LEFT mouse button
chart_1.bind("<B1-Motion>", callback_2)
chart_1.bind("<Button-3>", callback_5)  # <Button-3>  ->RIGHT mouse button
chart_1.bind("<ButtonRelease-3>", callback_6)

root.mainloop()

