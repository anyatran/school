"""
Program name: image_offset_1.py
Objective: Make a reduced copy of an image.

Keywords: Image, offset, jpg,
============================================================================79
 
Explanation: Simple syntax to change image size 

Author:          Mike Ohlson de Fine

"""
# image_offset_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image
import ImageChops

im_1 = Image.open("/constr/pics1/canary_a.jpg")

# adjust width and height to desired size
dx = 200
dy = 300

im_2 = ImageChops.offset(im_1, dx, dy)
im_2.save("/constr/picsx/canary_2.jpg")

