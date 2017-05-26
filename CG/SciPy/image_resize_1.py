"""
Program name: image_resize_1.py
Objective: Make a reduced copy of an image.

Keywords: Image, resize, jpg, 
============================================================================79
 
Explanation: Simple syntax to change image size.
The image is distorted because the height-to-width ration was not preserved.
The memory size was reduced 73 times. 

Author:          Mike Ohlson de Fine

"""
# image_resize_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image

im_1 = Image.open("/constr/pics1/dusi_leo_1.jpg")

# adjust width and height to desired size
width = 300
height = 300
# Filter is compulsory to resize the image
im_2 = im_1.resize((width, height), Image.NEAREST) 

im_2.save("/constr/picsx/dusi_leo_2.jpg")

