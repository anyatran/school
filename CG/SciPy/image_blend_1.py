"""
Program name: image_blend_1.py
Objective: Blend two images with different modes.

Keywords: Image, blend, png, convert mode
============================================================================79
 
Explanation: Creates a new image by interpolating between the given images, using
a constant 'proportion'. Both images must have the same size and mode. 
In the example here although both images are .png format, one has mode RGB,
while the other has mode RGBA. So the second one must be converted to RGBA first.
Syntax:  "resulting_image = image1 * (1.0 - proportion) + image2 * proportion"
where 'proportion' varies between 0.0 and 1.0.
If 'proportion' is 0.0, a copy of the first image is returned.
If 'proportion' is 1.0, a copy of the second image is returned.

Author:          Mike Ohlson de Fine

"""
# image_blend_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image
im_1 = Image.open("/constr/pics1/100_canary.png")   #  mode is RGBA
im_2 = Image.open("/constr/pics1/100_cockcrow.png") #  mode is RGB

# Check on mode, size and format first for compatibility.
print "im_1 format:", im_1.format, ";size:", im_1.size, "; mode:",im_1.mode
print "im_2 format:", im_2.format, ";size:", im_2.size, "; mode:",im_2.mode
im_2 = im_2.convert("RGBA")       # Make both modes the same
im_4 = Image.blend(im_1, im_2, 0.5)

im_4.show()


