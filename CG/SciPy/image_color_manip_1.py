"""
Program name: image_color_manip_1.py
Objective: Manipulate the amount of color in the red, green and blue bands
independently and then combine the results.

Keywords: color manipulation, image, jpg, PIL, Python Imaging Library
============================================================================79
 
Explanation: The split method separates the image into red, green and blue
bands.The "point(lambda i: i * intensity)" method multiplies the color value
for each pixel in a color band by an 'intensity' value and the
"merge(im_1.mode, new_source)" method re-combines the resultant bands into 
a new image.

Author:          Mike Ohlson de Fine

"""
#image_color_manip_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import ImageEnhance, Image
im_1 = Image.open("/constr/pics1/dusi_leo_smlr_1.jpg")

# Split the image into individual bands
source = im_1.split()

R, G, B = 0, 1, 2

# Select regions where red is less than 100
red_band = source[R]
green_band = source[G]
blue_band = source[B]

# Process the red band: intensify red x 2
out_red = source[R].point(lambda i: i * 2.0)

## Process the green band: weaken by 20%
out_green = source[G].point(lambda i: i * 0.8)

# process the blue band: Eliminate all blue
out_blue = source[B].point(lambda i: i * 0.0)

# Make a new source of color band values
new_source = [out_red, out_green, out_blue]

# Add the three bands altered bands back together
im_2 = Image.merge(im_1.mode, new_source)
im_2.show()


