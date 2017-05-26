"""
Program name: image_color_adjuster_1.py
Objective: Manipulate the amount of color in the red, green and blue bands
independently and then combine and display the results using GUI widgets.

Keywords: canvas, image, color adjust, slider, jpg, PIL, Python Imaging Library
============================================================================79
 
Explanation: The split method separates the image into red, green and blue
bands. The "point(lambda i: i * intensity)" method multiplies the color value
for each pixel in a band by an 'intensity' value and the
"merge(im_1.mode, new_source)" method re-combines the resultant bands into 
a new image.

In this example PIL and Tkinter are being used together.
If you use "from Tkinter import *, you seem to get namespace confusion:
The interpreter says "    im_1 = Image.open("/a_constr/pics1/redcar.jpg")
AttributeError: class Image has no attribute 'open'   ",
but if you just say "  import Tkinter" it seems OK.
 But of course now you have prefix all Tkinter methods with " Tkinter. "

Author:          Mike Ohlson de Fine

"""
#image_color_adjuster_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import ImageEnhance
import Image
import Tkinter 
root =Tkinter.Tk()
root.title("Photo Image Color Adjuster")

red_frac = 1.0
green_frac = 1.0
blue_frac = 1.0

slide_value_red   = Tkinter.IntVar()
slide_value_green = Tkinter.IntVar()
slide_value_blue  = Tkinter.IntVar()

im_1 = Image.open("/constr/pics1/dusi_leo_smlr_1.jpg")
im_1.show()
source = im_1.split()  # split the image into individual bands
R, G, B = 0, 1, 2

# Assign color intensity bands
red_band = source[R]
green_band = source[G]
blue_band = source[B]
#================================================================
# Slider and Button event service functions (callbacks)
def callback_button_1():
    # Adjust red intensity by slider value.
    out_red = source[R].point(lambda i: i * red_frac)
    out_green = source[G].point(lambda i: i * green_frac) # Adjust green
    out_blue = source[B].point(lambda i: i * blue_frac)   # Adjust blue
    new_source = [out_red, out_green, out_blue]
    im_2 = Image.merge(im_1.mode, new_source)     # Re-compine bands
    im_2.show()

button_1= Tkinter.Button(root,bg= "sky blue", text= "Display adjusted image \
                       (delete previous one)", command=callback_button_1)
button_1.grid(row=1, column=2, columnspan=3)

def callback_red(*args):
   global red_frac
   red_frac = slide_value_red.get()/100.0

def callback_green(*args):
   global green_frac
   green_frac = slide_value_green.get()/100.0

def callback_blue(*args):
   global blue_frac
   blue_frac = slide_value_blue.get()/100.0

slide_value_red.trace_variable("w", callback_red)
slide_value_green.trace_variable("w", callback_green)
slide_value_blue.trace_variable("w", callback_blue)

slider_red = Tkinter.Scale(root,
                       length = 400,
                       fg = 'red',
                       activebackground = "tomato",
                       background = "grey",
                       troughcolor = "red", 
                       label = "RED",
                       from_ = 0,
                       to = 200,
                       resolution = 1,
                       variable = slide_value_red,
                       orient   = 'vertical')

slider_red.grid(row=0, column=2)

slider_green = Tkinter.Scale(root,
                       length = 400,
                       fg = 'dark green',
                       activebackground = "green yellow",
                       background = "grey",
                       troughcolor = "green", 
                       label = "GREEN",   
                       from_ = 0,
                       to = 200,
                       resolution = 1,
                       variable = slide_value_green,
                       orient   = 'vertical')

slider_green.grid(row=0, column=3)

slider_blue = Tkinter.Scale(root,
                       length = 400,
                       fg = 'blue',
                       activebackground = "turquoise",
                       background = "grey",
                       troughcolor = "blue", 
                       label = "BLUE",   
                       from_ = 0,
                       to = 200,
                       resolution = 1,
                       variable = slide_value_blue,
                       orient   = 'vertical')

slider_blue.grid(row=0, column=4)

root.mainloop()
#===============================================================
