"""
Program name: image_flips_1.py
Objective: Rotate by multiples of 90 degreews and 
flip vertically and horizontally.

Keywords: canvas, image, rotate, flip,  jpg, PIL, Python Imaging Library
============================================================================79
 
Explanation: The basic image re-orientation operations..

Author:          Mike Ohlson de Fine

"""
# image_flips_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
import Image
im_1 = Image.open("/a_constr/pics1/duzi_leo_1.jpg")
im_out_1 = im_1.transpose(Image.FLIP_LEFT_RIGHT)
im_out_2 = im_1.transpose(Image.FLIP_TOP_BOTTOM)
im_out_3 = im_1.transpose(Image.ROTATE_90)
im_out_4 = im_1.transpose(Image.ROTATE_180)
im_out_5 = im_1.transpose(Image.ROTATE_270)
im_out_1.save("/a_constr/picsx/duzi_leo_horizontal.jpg")
im_out_1.save("/a_constr/picsx/duzi_leo_vertical.jpg")
im_out_1.save("/a_constr/picsx/duzi_leo_90.jpg")
im_out_1.save("/a_constr/picsx/duzi_leo_180.jpg")
im_out_1.save("/a_constr/picsx/duzi_leo_270.jpg")


