
# coding: utf-8

# # Segmentation
# 
# 
# Segmentation is the division of an image into "meaningful" regions. If you've seen The Terminator, you've seen image segmentation:
# 
# <img src="../2014-scipy/images/terminator-vision.png" width="700px"/>
# 
# In `scikit-image`, you can find segmentation functions in the `segmentation` package, with one exception: the `watershed` function is in `morphology`, because it's a bit of both. We'll use two algorithms, SLIC and watershed and discuss applications of each.
# 
# There are two kinds of segmentation: *contrast-based* and *boundary-based*. The first is used when the regions of the image you are trying to divide have different characteristics, such as a red flower on a green background. The second is used when you want to segment an image in which borders between objects are prominent, but objects themselves are not very distinct. For example, a pile of oranges.
# 
# ## Image types: contrast
# 
# SLIC (Simple Linear Iterative Clustering) is a segmentation algorithm of the first kind: it clusters pixels in both space and color. Therefore, regions of space that are similar in color will end up in the same segment.
# 
# Let's try to segment this image:
# 
# <img src="../images/spice_1.jpg" width="400px"/>
# 
# (Photo by Flickr user Clyde Robinson, used under CC-BY 2.0 license.)
# 
# The SLIC function takes two parameters: the desired number of segments, and the "compactness", which is the relative weighting of the space and color dimensions. The higher the compactness, the more "square" the returned segments.

# In[1]:

import numpy as np
get_ipython().magic(u'matplotlib inline')
from matplotlib import pyplot as plt


# In[2]:

import skdemo
plt.rcParams['image.cmap'] = 'spectral'
from skimage import io, segmentation as seg, color

url = '../images/spice_1.jpg'
image = io.imread(url)


# In[3]:

labels = seg.slic(image, n_segments=18, compactness=10)
skdemo.imshow_all(image, labels.astype(float) / labels.max())
print labels


# We can try to create a nicer visualization for `labels`: each segment will be represented by its average color.

# In[4]:

def mean_color(image, labels):
    out = np.zeros_like(image)
    for label in np.unique(labels):
        indices = np.nonzero(labels == label)
        out[indices] = np.mean(image[indices], axis=0)
    return out

skdemo.imshow_all(image, mean_color(image, labels))


# Notice that some spices are broken up into "light" and "dark" parts. We have multiple parameters to control this:
# 
# - `enforce_connectivity`: Do some post-processing so that small regions get merged to adjacent big regions.

# In[5]:

labels = seg.slic(image, n_segments=18, compactness=10,
                  enforce_connectivity=True)
label_image = mean_color(image, labels)
skdemo.imshow_all(image, label_image)


# Yikes! It looks like a little too much merging went on! This is because of the intertwining of the labels. One way to avoid this is to blur the image before segmentation. Because this is such a common use-case, a Gaussian blur is included in SLIC--just pass in the `sigma` parameter:

# In[6]:

labels = seg.slic(image, n_segments=18, compactness=10,
                  sigma=2, enforce_connectivity=True)
label_image = mean_color(image, labels)
skdemo.imshow_all(image, label_image)


# Getting there! But it looks like some regions are merged together. We can alleviate this by increasing the number of segments:

# In[7]:

labels = seg.slic(image, n_segments=24, compactness=10,
                  sigma=2, enforce_connectivity=True)
label_image = mean_color(image, labels)
skdemo.imshow_all(image, label_image)


# That's looking pretty good! Some regions are still too squiggly though... Let's try jacking up the compactness:

# In[8]:

labels = seg.slic(image, n_segments=24, compactness=40,
                  sigma=2, enforce_connectivity=True)
label_image = mean_color(image, labels)
skdemo.imshow_all(image, label_image)


# ## <span class="exercize">SLIC explorer</span>
# Write an interactive tool to explore the SLIC parameter space.  A skeleton is
# given below.

# ```python
# from IPython.html import widgets
# 
# def func(slider_kwarg=0.5, dropdown_kwarg='option0'):
#     s = some_func(image, arg1=slider_kwarg, arg2=dropdown_kwarg)
#     skdemo.imshow_all(image, s)
# 
# widgets.interact(func, slider_kwarg=(start, stop, step),
#                  dropdown_kwarg=['option0', 'option1'])
# ```

# ## <span class="exercize">Select the spices</span>
# 
# Try segmenting the following image with a modification to the same tool:
# 
# <img src="../images/spices.jpg" width="400px"/>
# 
# "Spices" photo by Flickr user Riyaad Minty.
# https://www.flickr.com/photos/riym/3326786046
# Used under the Creative Commons CC-BY 2.0 license.
# 
# Note: this image is more challenging to segment because the color regions are different from one part of the image to the other. Try the `slic_zero` parameter in combination with different values for `n_segments`.

# In[9]:

url2 = '../images/spices.jpg'


# ## Image types: boundary images
# 
# Often, the contrast between regions is not sufficient to distinguish them, but there is a clear boundary between the two. Using an edge detector on these images, followed by a *watershed*, often gives very good segmentation. For example, look at the output of the Sobel filter on the coins image:

# In[10]:

from skimage import data, filter as filters
from matplotlib import pyplot as plt, cm


# In[11]:

coins = data.coins()
edges = filters.sobel(coins)

plt.imshow(edges, cmap='gray');


# The *watershed algorithm* finds the regions between these edges. It does so by envisioning the pixel intensity as height on a topographic map. It then "floods" the map from the bottom up, starting from seed points. These flood areas are called "watershed basins" and when they meet, they form the image segmentation.
# 
# Let's look at a one-dimensional example:

# In[12]:

from skimage.morphology import watershed
from scipy import ndimage as nd

x = np.array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
y = np.array([1, 0, 1, 2, 1, 3, 2, 0, 2, 4, 1, 0])

seeds = nd.label(y == 0)[0]
seed_positions = np.argwhere(seeds)[:, 0]

print "Seeds:", seeds
print "Seed positions:", seed_positions

# ------------------------------- #
result = watershed(y, seeds)
# ------------------------------- #

# You can ignore the code below--it's just
# to make a pretty plot of the results.
plt.figure(figsize=(10, 5))
plt.plot(y, '-o', label='Image slice', linewidth=3)
plt.plot(seed_positions, np.zeros_like(seed_positions), 'r^',
         label='Seeds', markersize=15)

for n, label in enumerate(np.unique(result)):
    mask = (result == label)
    plt.bar(x[mask][:-1], result[mask][:-1],
            width=1, label='Region %d' % n,
            alpha=0.1)

plt.vlines(np.argwhere(np.diff(result)) + 0.5, -0.2, 4.1, 'm',
           linewidth=3, linestyle='--')

plt.legend(loc='upper left', numpoints=1)
plt.axis('off')
plt.ylim(-0.2, 4.1);


# Answers the question: which seed flooded this point?

# Let's find some seeds for `coins`. First, we compute the *distance transform* of a thresholded version of `edges`:

# In[ ]:

from scipy import ndimage as nd
threshold = 0.4

# Euclidean distance transform
# How far do we ave to travel from a non-edge to find an edge?
non_edges = (edges < threshold)
distance_from_edge = nd.distance_transform_edt(non_edges)

plt.imshow(distance_from_edge, cmap='gray');


# Then, we find the *peaks* in that image--the background points furthest away from any edges--which will act as the seeds.

# In[ ]:

from skimage import feature

# -------------------------------------------------#
peaks = feature.peak_local_max(distance_from_edge)
print "Peaks shape:", peaks.shape
# -------------------------------------------------#

peaks_image = np.zeros(coins.shape, np.bool)
peaks_image[tuple(np.transpose(peaks))] = True
seeds, num_seeds = nd.label(peaks_image)

plt.imshow(edges, cmap='gray')
plt.plot(peaks[:, 1], peaks[:, 0], 'ro');
plt.axis('image')


# We are now ready to perform the watershed:

# In[ ]:

ws = watershed(edges, seeds)

from skimage import color
plt.imshow(color.label2rgb(ws, coins));


# ## Examining the resulting segmentation
# 
# We have more prior knowledge that we can include in this processing problem.
# For one--the coins are round!

# In[ ]:

from skimage.measure import regionprops

regions = regionprops(ws)

ws_updated = ws.copy()
for region in regions:
    if region.eccentricity > 0.6:
        ws_updated[ws_updated == region.label] = 0

plt.imshow(color.label2rgb(ws_updated, coins, bg_label=0));


# ## <span class="exercize">Seeds of doubt</span>
# 
# We can see that watershed gives a very good segmentation, but some coins are missing. Why? Can you suggest better seed points for the watershed operation?

# # Discussion
# 
# Watershed and SLIC are too simple to be used as final segmentation outputs. In fact, their output is often called a *superpixel*, a kind of minimal segment. These are then used for further processing. Downstream processing methods are slated to be added to scikit-image in the next version. See Vighnesh Birodkar's [GSoC project](http://www.google-melange.com/gsoc/proposal/public/google/gsoc2014/vighneshbirodkar/5870670537818112) and his recent (and excellent) [PR](https://github.com/scikit-image/scikit-image/pull/1031). These are beyond the scope of this tutorial but come chat to me after if you are interested in segmentation!

# ---
# 
# <div style="height: 400px;"></div>

# In[ ]:

get_ipython().magic(u'reload_ext load_style')
get_ipython().magic(u'load_style ../themes/tutorial.css')

