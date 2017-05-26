/*
1) check for complete row(s)
2) delete full row(s) and move others down
3) distinguish the block being dropped from the landed blocks
4) checking for boundaries of canvas
5) checking for gameOver/too tall stack
6) need posns of every block
7) score
8) moving blocks. On Key - left/right; on Tick - down
9) (when to) make a new block


What we need:
- blocks
- game board
- World
- LoBlocks
- Posn


  Block                         World
Posn p                       Dropping Block dropping                    ILoBlocks(MtLoBlocks, ConsLoBlocks)       for cons
int size                     int Score                                    LandedBlock first
Block shift(String key)      ILoBlocks landed                             ILoBlocks rest
Block drop()                                                              boolnean blockAtPoint(CartPt)
                                                                          
                   
Posn(defined)                         Dropping Block                    Landing Block
int x                        Dropping Block shift(String key, 
int y                                            ILoB landed)
                             Block drop(ILoB landed)

                   
  CartPt ext.Posn                 FromFileImage(CartPt, String)
Posn move(dx, dy)

               
*/





