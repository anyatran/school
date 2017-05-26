"""
Program name: image_get_green_1.py
Objective: Separate the green only from a picture.

Keywords: canvas, image, color adjust, jpg, PIL, Python Imaging Library
============================================================================79
 
Explanation: The "split" method separates the image into red, green and blue
bands. The "point(lambda i: i * intensity)" method multiplies the color value
for each pixel in a band by an 'intensity' value and the
"merge(im_1.mode, new_source)" method re-combines the resultant bands into 
a new image.

In this example PIL and Tkinter are being used together.
If you use "from Tkinter import *, you seem to get namespace confusion:
The interpreter says " im_1 = Image.open("/a_constr/pics1/redcar.jpg")
AttributeError: class Image has no attribute 'open'   ",
but if you just say "  import Tkinter" it seems OK.
But of course now you have prefix all Tkinter methods with " Tkinter. "

Author:          Mike Ohlson de Fine

"""
#image_get_green_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import ImageEnhance
import Image

red_frac = 1.0
green_frac = 1.0
blue_frac = 1.0

im_1 = Image.open("/a_constr/pics1/dusi_leo_1.jpg")

# split the image into individual bands
source = im_1.split()
R, G, B = 0, 1, 2

# Assign color intensity bands, zero for red and blue.
red_band = source[R].point(lambda i: i * 0.0)
green_band = source[G]
blue_band = source[B].point(lambda i: i * 0.0)
new_source = [red_band, green_band, blue_band]

# Merge (add) the three color bands
im_2 = Image.merge(im_1.mode, new_source)

im_2.show()
#================================================================

