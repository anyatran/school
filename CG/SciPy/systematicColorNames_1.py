"""
Program name: systematic_colorNames_6.py
Objective: Show pallette using a selection of colours known to python.

Keywords: canvas, rectangle, color, color names, web color
============================================================================79
 
Explanation: 
Python or Tkinter has a large collection of colors with known names. 
A useful sub-set selection is shown here.

Author:          Mike Ohlson de Fine
  
"""
# systematic_colorNames_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Systematically named colors - limited pallette")

cw = 1000                                      # canvas width
ch = 800                                       # canvas height
canvas_1 = Canvas(root, width=cw, height=ch, background="black")
canvas_1.grid(row=0, column=1)

whiteColors =  "Gainsboro","peach puff","cornsilk",\
"honeydew","aliceblue","misty rose","snow", "snow3","snow4",\
"SlateGray1", "SlateGray3", "SlateGray4",\
"gray", "darkGray","DimGray","DarkSlateGray"

redColors = "Salmon","salmon1","salmon2","salmon3","salmon4",\
"orange red","OrangeRed2","OrangeRed3","OrangeRed4",\
"red","red3","red4",\
"IndianRed1","IndianRed3","IndianRed4",\
"firebrick","firebrick1","firebrick3","firebrick4",\
"sienna","sienna1","sienna3","sienna4"


pinkColors = "Pink","pink3","pink4",\
"hot pink","HotPink3","HotPink4",\
"deep pink","DeepPink3","DeepPink4",\
"PaleVioletRed1","PaleVioletRed2","PaleVioletRed3","PaleVioletRed4",\
"maroon","maroon1","maroon3","maroon4" 

magentaColors = "magenta","magenta3","magenta4","DarkMagenta",\
"orchid1","orchid3","orchid4",\
"MediumOrchid3","MediumOrchid4",\
"DarkOrchid","DarkOrchid1","DarkOrchid4",\
"MediumPurple1","MediumPurple3", "MediumPurple4",\
"purple","purple3","purple4"

blueColors = "blue","blue3","blue4",\
"SlateBlue1", "SlateBlue3","SlateBlue4",\
"DodgerBlue2", "DodgerBlue3","DodgerBlue4",\
"deep sky blue","DeepSkyBlue3", "DeepSkyBlue4",\
"sky blue",   "SkyBlue3", "SkyBlue4"

cyanColors = "CadetBlue1", "CadetBlue3", "CadetBlue4",\
"pale turquoise", "PaleTurquoise3","PaleTurquoise4",\
"cyan", "cyan3", "cyan4",\
"aquamarine","aquamarine3", "aquamarine4"

greenColors =  "green", "green3", "green4","dark green",\
"chartreuse", "chartreuse3", "chartreuse4",\
"SeaGreen","SeaGreen1",  "SeaGreen3",\
"pale green", "PaleGreen3", "PaleGreen4",\
"spring green", "SpringGreen3", "SpringGreen4",\
"olive drab","OliveDrab1", "OliveDrab4",\
"dark olive green","DarkOliveGreen1",  "DarkOliveGreen3","DarkOliveGreen4",\

yellowColors=  "yellow", "yellow3","yellow4",\
"gold","gold3","gold4",\
"goldenrod","goldenrod1","goldenrod3","goldenrod4",\
"orange","orange3","orange4",\
"dark orange","DarkOrange1","DarkOrange4"

x_start = 10
y_start = 25
x_width = 118
x_offset = 2
y_height = 30
y_offset = 3
text_offset = 0
text_width = 95
kbk = [x_start, y_start, x_start + x_width, y_start + y_height]

def showColors(selectedColor):
# Basic columnar color swatch display. All colours laid down in a vertical stripe.
    print "number of colors --> ", len(selectedColor) 
    for i in range (0,len(selectedColor)):
        kula = selectedColor[i]
        canvas_1.create_rectangle(kbk, fill=kula) 
        canvas_1.create_text(kbk[0]+10,  kbk[1] ,  text=kula, width=text_width, fill ="black", anchor=NW) 
        kbk[1] += y_offset + y_height
        y0 = kbk[1]
        kbk[3] += y_offset + y_height
        y1 = kbk[3]
    kbk[1] = y_offset + y_height 
    kbk[3] = y_offset + 2 * y_height 
    kbk[0] += x_width + 2*x_offset
    kbk[2] += x_width + 2*x_offset
    return y0,y1

showColors(redColors)         
showColors(pinkColors)
showColors(magentaColors)
showColors(cyanColors)
showColors(blueColors)   
showColors(greenColors)  
showColors(yellowColors)      
showColors(whiteColors)  

root.mainloop()
