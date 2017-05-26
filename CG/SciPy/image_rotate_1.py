"""
Program name: image_rotate_1.py
Objective: Rotate an image negative 90 degrees.

Keywords: canvas, image,rotate, jpg, PIL, Python Imaging Library
============================================================================79
 
Explanation: This gets the image right-side up.

Author:          Mike Ohlson de Fine

"""
# image_rotate_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image

im_1 = Image.open("/constr/pics1/dusi_leo.png")
im_2= im_1.rotate(-90)
im_2.show()

