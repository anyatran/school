""" 
Program name: image_composite_1.py
Objective: Blend two images with different modes.

Keywords: Image, composite, mask, png, convert mode
============================================================================79
 
Explanation: Creates a new image by interpolating between the two given 
images, using the mask image as alpha control. 
The mask image can have mode "1", "L", or "RGBA".
All images must be the same size.

Author:          Mike Ohlson de Fine

"""
# image_composite_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image
im_1 = Image.open("/constr/pics1/100_canary.png")   #  mode is RGBA
im_2 = Image.open("/constr/pics1/100_cockcrow.png") #  mode is RGB
im_3 = Image.open("/constr/pics1/100_sun_1.png")
# Check on mode, size and format first for compatibility.
print "im_1 format:", im_1.format, ";size:", im_1.size, "; mode:",im_1.mode
print "im_2 format:", im_2.format, ";size:", im_2.size, "; mode:",im_2.mode
print "im_3 format:", im_3.format, ";size:", im_3.size, "; mode:",im_3.mode

im_2 = im_2.convert("RGBA")
im_3 = im_3.convert("L")
im_4 = Image.composite(im_1, im_2, im_3)

im_4.show()


