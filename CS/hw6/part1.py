def sum3(nums):
    return nums[0] + nums[1] + nums[2];

sum3([1, 2, 3]);

#rotate3
def rotate_left3(nums):
    return [nums[1], nums[2], nums[0]];

rotate_left3([1, 2, 3]);

def max_end3(nums):
  i = max(nums[0], nums[2])
  return [i, i, i];

def make_ends(nums):
  return [nums[0], nums[len(nums) - 1]];

def has23(nums):
  return 2 in nums or 3 in nums;


def count_evens(nums):
    count = 0
    for i in nums:
        if (i % 2 == 0):
            count = count + 1
    return count

def big_diff(nums):
    return max(nums) - min(nums)

def centered_average(nums):
    sum_array = sum(nums) - min(nums) - max(nums)
    return sum_array / (len(nums) - 2)


def sum13(nums):
  if len(nums) == 0:
    return 0
  for i in range(len(nums)):
    if nums[i] == 13:
      nums[i] = 0
      if i+1 < len(nums): 
        nums[i+1] = 0
  return sum(nums)

def has22(nums):
    res = False
    for i in range(len(nums)-1):
        res = (nums[i] == nums[i+1] == 2) or res
    return res

def extra_end(str):
    return str[-2:] + str[-2:] + str[-2:] 

def double_char(str):
  res = ""
  for s in str:
    res = res + s + s
  return res

def count_code(str):
  count = 0
  for i in range(len(str) - 3):
    if (str[i:i+2] == "co" and str[i+3] == "e"):
      count = count + 1
  return count

def count_hi(str):
  count = 0
  for i in range(len(str) - 1):
    if (str[i:i+2] == "hi"):
      count = count + 1
  return count

def end_other(a, b):
  a_l = a.lower()
  b_l = b.lower()
  return a_l.find(b_l, len(a) - len(b)) > 0 or b_l.find(a_l, len(b) - len(a)) > 0 or a_l == b_l
  
def without_end(str):
  return str[1:-1]

def cat_dog(str):
  count_cat = 0
  count_dog = 0
  for i in range(len(str)):
    if str[i:i+3] == "cat":
      count_cat = count_cat + 1
    elif str[i:i+3] == "dog":
      count_dog = count_dog + 1
    
  return count_cat == count_dog

def xyz_there(str):
  for i in range(0, len(str)):
    if str[i:i+3] >= 3:
      if str[i:i+3] == 'xyz':
        if str[i-1+str[i:].index('xyz')] != '.':
          return True
        i += str[i:].index('xyz') + 2
  return False

def sum67(nums):
  for i in range(len(nums)):
    if nums[i] == 6:
      nums[i] = 0
      for j in range(i+1, len(nums)):
        temp = nums[j]
        nums[j] = 0
        if temp == 7:
          i = j + 1
          break
  return sum(nums)
          
