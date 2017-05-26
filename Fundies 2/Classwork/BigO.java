 /*
 SelectionSort     size          no.comparisions               no.swaps    
                                  b:~n^2 | w: n^2            b: n | w: n
                    1                     0                       1 
                    2                     1                       1
                    3                  2+1 = 3                    2  
                    4                3+2+1 = 6                  1+2 = 3
                    5              4+3+2+1 = 10                1+2+3= 6
                    sum(i=0, n-1) = n(n-1) / 2                3(n-1)
                    n^2 + abit or O(n^2)
                    
             
                                 
InsertionSort       size            no.comparisona              no.moves
                                     b: n | w: n^2            b: n | w: n^2
                      1                    0                       0
                      2                    1                       2
                      3               [1, n] + ^^^^             [0, n^2]
                      n               [n, n^2]

QS: 
1) 1
2) n-1 comp; n-1 moves
3) 1 move
lg n rounds * (n moves & n comp)

round1: (n comp and n moves) * 1
round2: (n/2 comp and n/2 moves) * 2
round3: (n/4 comp and n/4 moves) * 4
                     size            no.comparisona              no.moves
InsertionSort         n             b: n | w: n^2               b: n | w: n^2
SelectionSort         n             b:~n^2 | w: n^2             b: n | w: n 
QuickSort             n          b: n ln n | w:                    b: n ln n
MergeSort                             n ln n                        n ln n
                       

f(n) = O(g(n)) - the same growth
for some constant k, for all n > N, k*g(n) > f(n)
*/