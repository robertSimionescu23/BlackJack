import java.util.ArrayList;

public class Player{
    //private boolean isTurn;
    private boolean isDealer;
    private float playerBet;
    private float creditPlayer ;
    private boolean playerHasBlackJack; //A boolean that shows if a party has blackjack
    private int playerTotal;
    private boolean hasWon;
    private boolean isTurn;
    private boolean hasAce;
    private float insuranceBet = 0;
    ArrayList <Cards> playerCards = new ArrayList<>();
    
//GET----------------------------------------------------------------------------------------------------------------------------
public float getInsuranceBet()
{
    return this.insuranceBet;
}
    //has Ace
    public boolean getHasAce()
    {
        return this.hasAce;
    }
    //is Turn
    public boolean getIsTurn()
    {
        return this.isTurn;
    }
    //Blackjack Status
    public boolean getPlayerBlackjack()
    {
        return this.playerHasBlackJack;
    }
    public float getBet()
    {
        return this.playerBet;
    }
    //Player total
    public int getPlayerTotal()
    {
        return this.playerTotal;
    }
    //Credit player
    public float getCreditPlayer()
    {
        return this.creditPlayer;
    }
    //Dealer status
    public boolean getDealer()
    {
        return this.isDealer;
    }
    //Hand
    public void getHand()
    {
        if(this.playerCards!=null)
        {
            playerCards.forEach((n)->System.out.print(n.getName() + " ,"));
        }
        System.out.println("\n");
    }
    //Won status
    public boolean getHasWon()
    {
        return this.hasWon;
    }
//SET----------------------------------------------------------------------------------------------------------------------------
    
    public void setInsuranceBet(float n)
    {
        this.insuranceBet = n;
    }
    //has Ace
    public void setHasAce(boolean n)
    {
        this.hasAce = n;
    }
    //Is Turn
    public void setIsTurn(boolean n)
    {
        this.isTurn = n;
    }
    //hasWon
    public void setHasWon(boolean n)
    {
        this.hasWon = n;
    }
    //bet
    public void setBet(float n)
     {
         this.playerBet = n;
     }
     //BlackJack
     public void setPlayerBlackjack(boolean n)
    {
        this.playerHasBlackJack = n;
    }
    //player total
    public void setPlayerTotal(int n)
    {
       playerTotal = n;
    } 
    //Credit player
    public void setCreditPlayer(float n)
    {
       this.creditPlayer = n;
    }
    //Dealer Status
    public void setDealer(boolean n)
    {
        this.isDealer = n;
    }
     public void newHand()
     {
         this.playerBet = 0; 
         this.playerHasBlackJack = false;
         this.hasAce = false;
         this.playerTotal = 0;
         this.playerCards = new ArrayList<>();
     }
    //Constructor ------------------------------------------------

    Player(float n)
    {
        this.setCreditPlayer(n);
        this.isDealer = false;
        this.playerBet = 0;
        this.playerHasBlackJack = false; //A boolean that shows if a party has blackjack
        this.playerTotal = 0;
        playerCards = new ArrayList<>();
    
    }


}
