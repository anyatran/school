
# coding: utf-8

# In[ ]:




# We represent images in ``scikit-image`` using standard numpy arrays.  This allows maximum inter-operability with other libraries in the scientific Python ecosystem, such as ``matplotlib`` and ``scipy``.
# 
# Let's see how to build a grayscale image as a 2D array:

# # Images are numpy arrays

# In[1]:

from skimage import data

coins = data.coins()

print(type(coins), coins.dtype, coins.shape)
plt.imshow(coins, cmap=cm.gray, interpolation='nearest');


# In[ ]:

get_ipython().magic(u'matplotlib inline')
import numpy as np
from matplotlib import pyplot as plt, cm

random_image = np.random.rand(500, 500)
plt.imshow(random_image, cmap=cm.gray, interpolation='nearest');


# The same holds for "real-world" images:

# A color image is a 3D array, where the last dimension has size 3 and represents the red, green, and blue channels:

# In[ ]:

cat = data.chelsea()
print(cat.shape)

plt.imshow(cat, interpolation='nearest');


# These are *just numpy arrays*. Making a red square is easy using just array slicing and manipulation:

# In[ ]:

cat[10:110, 10:110, :] = [255, 0, 0]  # [red, green, blue]
plt.imshow(cat);


# Images can also include transparent regions by adding a 4th dimension, called an *alpha layer*.

# ## Data types and image values
# 
# In literature, one finds different conventions for representing image values:
# 
# ```
#   0 - 255   where  0 is black, 255 is white
#   0 - 1     where  0 is black, 1 is white
# ```
# 
# ``scikit-image`` supports both conventions--the choice is determined by the
# data-type of the array.
# 
# E.g., here, I generate two valid images:

# In[ ]:

linear0 = np.linspace(0, 1, 2500).reshape((50, 50))
linear1 = np.linspace(0, 255, 2500).reshape((50, 50)).astype(np.uint8)

print "Linear0:", linear0.dtype, linear0.min(), linear0.max()
print "Linear1:", linear1.dtype, linear1.min(), linear1.max()

fig, (ax0, ax1) = plt.subplots(1, 2)
ax0.imshow(linear0, cmap='gray')
ax1.imshow(linear1, cmap='gray');


# The library is designed in such a way that any data-type is allowed as input,
# as long as the range is correct (0-1 for floating point images, 0-255 for unsigned bytes,
# 0-65535 for unsigned 16-bit integers).
# 
# This is achieved through the use of a few utility functions, such as ``img_as_float`` and ``img_as_ubyte``:

# In[ ]:

from skimage import img_as_float, img_as_ubyte

image = data.chelsea()

image_float = img_as_float(image)
print(image_float.dtype, image_float.min(), image_float.max())

image_ubyte = img_as_ubyte(image)
print(image_ubyte.dtype, image_ubyte.min(), image_ubyte.max())

print 231/255.


# Your code would then typically look like this:
# 
# ```python
# def my_function(any_image):
#    float_image = img_as_float(any_image)
#    # Proceed, knowing image is in [0, 1]
# ```
# 
# We recommend using the floating point representation, given that
# ``scikit-image`` mostly uses that format internally.

# ## <span class="exercize">Exercise: draw the letter H</span>
# 
# Define a function that takes as input an RGB image and a pair of coordinates (row, column), and returns the image (optionally a copy) with green letter H overlaid at those coordinates. The coordinates should point to the top-left corner of the H.
# 
# The arms and strut of the H should have a width of 3 pixels, and the H itself should have a height of 24 pixels and width of 20 pixels.

# In[ ]:

def draw_H(image, coords, color=(0.8, 0.8, 0.8), in_place=True):
    out = image
    # your code goes here
    return out


# Test your function like so:

# In[ ]:

cat = data.chelsea()
cat_H = draw_H(cat, (50, -50))
plt.imshow(cat_H);


# ## <span class="exercize">Bonus points: RGB intensity plot</span>
# 
# Plot the intensity of each channel of the image along a given row.

# In[ ]:

def plot_intensity(image, row):
    pass # code goes here


# Test your function here:

# In[ ]:

plot_intensity(coins, 50)
plot_intensity(cat, 250)


# ---
# 
# <div style="height: 400px;"></div>

# In[ ]:

get_ipython().magic(u'reload_ext load_style')
get_ipython().magic(u'load_style ../themes/tutorial.css')


# In[ ]:



