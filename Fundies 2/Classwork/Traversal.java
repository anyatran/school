// skcd for google map

/*
 BREDTH FIRST QUEUE
 
from - to                 l of leg        l of whole trip
Hartford - Hartford           0                   0
Hartford - albany            100                 100 first(Queue)
hartford - concord           150                 150
hartford - t                  180                180
hartford - providence         70                  70        shortest(Priority Queue), last(stack)

Albany
A-D                           300                 400
A - T                         300                 300
A - M                         160                 260
A - D                         300                 400
A - M                         160                 260
 
Concord
C - M                         120                 270
C - Agusta                    140                 290 
C - B                         170                 220


  DEPTH FIRST QUEUE(STACK)

from - to                 l of leg        l of whole trip
H - A                          100                100
Albany - Mon                   160                260
Mon - Augusta                  180                440
Augusta - Boston               160                600



from - to                 l of leg        l of whole trip

Hartford - Augusta

Hartford - albany            100                 100 
hartford - concord           150                 150
hartford - t                  180                180
hartford - providence         70                  70        1

providence - boston           50                 120
Albany - dover                300                400
Albany - t                    300                300
alb - mon                     160                260


Edges + Vert lg V + V = V^2
Dijkstra algrth - shortest possible path (PRIORITY QUEUE)


places we've connected
1) Start anywhere
While(not all cities all connected)
3) add the cheapest edge from anywhere you've been connected that connects a new city

______________________________
List of roads sorted my length
------------------------------
ADJACENCY LIST REPRESENTATION
GRAPH:
HashMap<Name, City>

City{
String name;
List<Roads> neightb;
}

Road{
City to;
int dist;
}
-----------------------------
PRIM'S ALGORITHM   
PriorityQueue<Road> frontier = ArrayList<Road>();
1) Start anywhere (call this X)
2) Add all the edges from X to frontier
While(there are uncinnected cities){
pick cheapes edge if the other 
if the other end point is already connected .
Throw the ed'ge away.
ELSE add it to our minimum spanning tree
add the new cities' roads to our frontier 
 
*/
