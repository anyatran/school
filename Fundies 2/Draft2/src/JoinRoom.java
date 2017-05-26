public class JoinRoom{
   private int[] set; // this is an array to store a set of rooms
   public JoinRoom(int elem){
      set = new int[e];
      // initialize every element in the set
      for(int i = 0; i < set.length; i++){
         set[i] = -1;
      }
   }// end of constructor

   // find using compression
   public int find(int r){
      if(set[r] < 0){
         return r;
      } else {
         return set[r] = find(set[r]);
      }
  }// end of find

  public void unionRooms(int roomA, int roomB){
      if(set[roomB] < set[roomA]){
          set[roomA] = roomB;
      } else {
         if(set[roomA] == set[roomB]){
            set[roomA]--;
         }
         set[roomB] = roomA;
     }
 }// end of union rooms

}// end of joinRoom class