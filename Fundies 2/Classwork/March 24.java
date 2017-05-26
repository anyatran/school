/*
 PRIORITY QUEUE
 - DQ max
 - isValid Priority
 - insert(int priority, D data)
 - HEAP INVARIANT: the root is greater then values of kids and recursively; guarante to get the max
 - SHAPE INVARIANT: the tree is full; guarante to get the height

               70
           50         60    
       40     35  40    20
     10  15  20
     
     
    ADD 90 to 35 first, then swap 35 and 90, 50 and 90, and 70 and 90
     
                   70        
           50            60    
       40     35      40    20
     10  15  20 90*
 
                  70
           50            60    
       40     90*     40    20
     10  15  20 35
     
                   70
           90*           60    
       40     50      40    20
     10  15  20 35
 
 
                 90*
           70            60    
       40     50      40    20
     10  15  20 35
     
                  90(1)
           70(2)         80(3)    
    (4)40  (5)50   (6)60    (7)20
     10  15  20 35   40
     
     
             
   move 40 on top, then swap with 80  
                   [ ]
           70(2)         80(3)    
    (4)40  (5)50   (6)60    (7)20
     10  15  20 35   40
      
      
                   [40]
           70(2)         80(3)    
    (4)40  (5)50   (6)60    (7)20
     10  15  20 35   
     
     
                    [80]
           70(2)         40(3)    
    (4)40  (5)50   (6)60    (7)20
     10  15  20 35   
     
                    [80]
           70(2)         60(3)    
    (4)40  (5)50   (6)40    (7)20
     10  15  20 35   
 
             
  INSEERT: 
  1) add new item to end of last row
  2) UPHEAP the last item by swaping with parents until restored
  
  REMOVEMAX: O(height of the tree = O(ln n)
  1) let the result = value at root
  2) move last item into root
  3) Downing that item with max of its children until heaporder is restored
  4) return result
  
  NODE INDEX         PARENT INDEX    LEFT CHILD INDEX    RIGHT 
      4                    2              8                9
      3                    1              6                7
      9                    4              28               19
      11                   5              22               13
      7                    3               
      n                floor:n/2          2n               2n+1
      
      
    
      ENCODING OF A BINARY TREE
 x  90  70  80  40  50  60  20  10  15  20  20  35  40
 0   1  2   3   4   5   6   7   8   9   10  11  12  13
 
 x  40*  70  80  40  50  60  20  10  15  20  20  35  
 
 x  80*  70  40*  40  50  60  20  10  15  20  20  35  
 
 x  80*  70  60*  40  50  40*  20  10  15  20  20  35  
  
 
 MAKEHEAP: O(n)
 1) from end of arraylist to head - DOWNHEAP

*/


//KRUSKAL ALGRTH FOR MAZES

}















 




