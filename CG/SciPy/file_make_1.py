"""
Program name: file_make_1.py
Objective:create a file on local hard drive.

Keywords: file write, create, data, storage
============================================================================79
 
Explanation:In function open(filename,"w"), the "w" specifies "write" and
this covers both the creation of a brand new file, if one did not exist
before. The "w" also opens it up for further writing.

Author:          Mike Ohlson de Fine

"""
# file_make_1.py
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
filename = "/constr/brand_new_file.dat"
FILE = open(filename,"w")

