"""
Program name:  image_preserved_aspect_resize_1.py
Objective: Make a reduced copy of an image that preserves the correct 
aspect ratio of the original image.

Keywords: Image, resize, aspect ratio, preserve, jpg, 
============================================================================79
 
Explanation: Simple syntax to change image size without distortion.

Author:          Mike Ohlson de Fine

"""
# image_preserved_aspect_resize_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image

im_1 = Image.open("/constr/pics1/dusi_leo_1.jpg")

im_width = im_1.size[0]
im_height = im_1.size[1]
print  im_width, im_height
print im_1.size
new_size =  0.2  # New image to be reduce to on fifth of original.
# adjust width and height to desired size
width  = int(im_width * new_size)
height = int(im_height * new_size)
# Filter is compulsory to resize the image
im_2 = im_1.resize((width, height), Image.NEAREST) 

im_2.save("/constr/picsx/dusi_leo_3.jpg")

