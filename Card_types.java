
public enum Card_types{
    //I used an old enum i had, so i changed the way the names are read, out of simplicity
    ACE_SPADES(0,1)   ,
    ACE_CLUBS(0,1)    ,
    ACE_DIAMONDS(0,1) ,
    ACE_HEARTS(0,1)   ,
 
    TWO_SPADES(2,2)   ,
    TWO_CLUBS(2,2)    ,
    TWO_DIAMONDS(2,2) ,
    TWO_HEARTS(2,2)   ,
 
 
    THREE_SPADES(3,3)   ,
    THREE_CLUBS(3,3)    ,
    THREE_DIAMONDS(3,3) ,
    THREE_HEARTS(3,3)   ,
 
 
    FOUR_SPADES(4,4)   ,
    FOUR_CLUBS(4,4)    ,
    FOUR_DIAMONDS(4,4) ,
    FOUR_HEARTS(4,4)   ,
 
 
    FIVE_SPADES(5,5)   ,
    FIVE_CLUBS(5,5)    ,
    FIVE_DIAMONDS(5,5) ,
    FIVE_HEARTS(5,5)   ,
 
    SIX_SPADES(6,6)   ,
    SIX_CLUBS(6,6)    ,
    SIX_DIAMONDS(6,6) ,
    SIX_HEARTS(6,6)   ,
 
    SEVEN_SPADES(7,7)   ,
    SEVEN_CLUBS(7,7)    ,
    SEVEN_DIAMONDS(7,7) ,
    SEVEN_HEARTS(7,7)   ,
 
    EIGHT_SPADES(8,8)   ,
    EIGHT_CLUBS(8,8)    ,
    EIGHT_DIAMONDS(8,8) ,
    EIGHT_HEARTS(8,8)   , 
 
    NINE_SPADES(9,9)   ,
    NINE_CLUBS(9,9)    ,
    NINE_DIAMONDS(9,9) ,
    NINE_HEARTS(9,9)   ,
 
 
    TEN_SPADES(10,10)    ,
    TEN_CLUBS(10,10)     ,
    TEN_DIAMONDS(10,10)  ,
    TEN_HEARTS(10,10)    ,
 
 
    JACK_SPADES(10,11)    ,
    JACK_CLUBS(10,11)     ,
    JACK_DIAMONDS(10,11)  ,
    JACK_HEARTS(10,11)    ,
 
 
    QUEEN_SPADES(10,12)    ,
    QUEEN_CLUBS(10,12)     ,
    QUEEN_DIAMONDS(10,12)  ,
    QUEEN_HEARTS(10,12)    ,
 
 
    KING_SPADES(10,13)    ,
    KING_CLUBS(10,13)     ,
    KING_DIAMONDS(10,13)  ,
    KING_HEARTS(10,13)    ,
 
    ;
    
    
    
    //Values 
     private final int val;
     private final int type;

    //constructor
     Card_types(int val, int type)
     {
         this.val = val;
         this.type = type;
     }

     //get value
     public final int getValue()
     {
         return this.val;
     }
     
     //get type
     public final int getType()
     {
        return this.type;
     }
 }
 