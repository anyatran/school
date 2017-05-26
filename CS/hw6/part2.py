# lines: 2, words: 9, char: 47vim
test = "The quick brown fox \njumped over the \nlazy dog."
test2 = "The 25 quick brown foxes jumped over the 27 lazy dogs 15 times."
def wordCount(str):
  count_nl = str.count("\n")
  count_w = str.count(" ") + 1
  count_c = len(str)
  return [count_nl, count_w, count_c]

print wordCount(test)

def mycount(str):
  char_count = 0
  for ch in str:
    if ch.isalpha():
      char_count = char_count + 1
  counting = [ str.count(c) for c in "0123456789 \t\n" ]
  counting.append(char_count)
  return counting

#digits = 0 1 2 0 0 2 0 1 0 0, white space = 12, other = 45
print mycount(test2)
