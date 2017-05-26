"""
Program name: image_getattributes_1.py
Objective: Discover the attributes of an image.

Keywords: Image, attributes, format, size, mode
============================================================================79
 
Explanation: Discover the attributes of an image such as size, mode and format. 

Author:          Mike Ohlson de Fine

"""
# image_image_getattributes_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image

imageFile = "/constr/pics1/canary_a.jpg"

im_1 = Image.open(imageFile)
im_width = im_1.size[0]
im_height = im_1.size[1]
im_mode = im_1.mode
im_format = im_1.format
print  "Size: ",im_width, im_height
print "Mode: ",im_mode
print "Format: ",im_format
im_1.show()
