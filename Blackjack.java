
import java.lang.management.MemoryUsage;
import java.nio.file.FileAlreadyExistsException;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.lang.model.util.ElementScanner14;

import java.util.InputMismatchException;

//Class definition -----------------------------------------------------------------------------------------------------------------------
public class Blackjack{
    Scanner scanner = new Scanner(System.in);
    //Define the Deck
        Deck deck = new Deck();
    //make all numbers have 2 decimals
        DecimalFormat numberFormat = new DecimalFormat("#.##");
    //number of players
        private int numberOfPlayers;
    //Assign the players
        Player[] player = new Player[6];
        //Last player is the dealer
        
    //SPLIT
        //Blackjack Status
        private boolean splitBlackJack = false; //if the split hand has blackjack
        //Statuses
        private boolean isSplit = false; //Bool to show you are playing a split game
        // total of points on split hand
        private int splitTotal; 

      
    //text inputs
        String q = "y";  //to say if you will play another game
        String answer = ""; //A simple string to use as answer to a question
        int ok;  //a simple boolean  for multiple use

    //min bets
        private float minBet;

    //Credits on default
        private float creditDefault;
            
    //WIN percentages
        private float blackjackWin;
        private float normalWin;
            
//Constructor - set all default values -------------------------------------------------------------------------------------------------

Blackjack(int nr, float def, float nw, float bw, float minb) throws InterruptedException
{
    this.setNumberOfPlayers(nr); 
    this.setCreditDefault(def);
    this.setWin(nw);
    this.setBlackjackWin (bw);
    this.setMinBet(minb);

}     
Blackjack()
{

}


//METHODS\\------------------------------------------------------------------------------------------------------------------------------




//GET----------------------------------------------------------------------------------------------------------------------------

    public int getNumberOfPlayers()
    {
        return this.numberOfPlayers;
    }
    public boolean getSplitBlackJack()
    {
        return this.splitBlackJack;
    }

    //BETS
    public float getMinBet()
    {
        return this.minBet;
    }
    

    //Status
    public boolean getIsSplit()
    {
        return this.isSplit;
    }
 
    //split total
    public int getSplitTotal()
    {
        return this.splitTotal;
    }
    //Credits
    public float getCreditDefault()
    {
        return this.creditDefault;
    }
    //Wins 
    public float getBlackJackWin()
    {
        return this.blackjackWin;
    }
    public float getNormalWin()
    {
        return this.normalWin;
    }

//SET--------------------------------------------------------------------------------------------------------------------------------------

    public void setNumberOfPlayers(int n)
    {
        this.numberOfPlayers = n;
    }
    public void setSplitBlackjack(boolean n)
    {
        this.splitBlackJack = n;
    }

     //BETS
     public void setMinBet(float n)
     {
         this.minBet = n;
     }


     //Status
    public void setIsSplit(boolean n)
    {
       this.isSplit = n;
    }

    public void setSplitTotal(int n)
    {
        this.splitTotal = n;
    }
       //Credits
    public void setCreditDefault(float n)
    {
       this.creditDefault = n;
    }
    //Wins 
    public void setBlackjackWin(float n)
    {
        this.blackjackWin = n;
    }
    public void setWin(float n)
    {
        this.normalWin = n;
    }

    //General ------------------------------------------------------------------------------------------------------------------------

    //Resets

    //A new game
    public void newGame() throws InterruptedException  
    {
        this.deck.make(); //make the deck

        for(int i=0;i<=this.getNumberOfPlayers();i++)
            this.player[i] = new Player(this.getCreditDefault()); // make the players needed

        //Defining the Dealer
            player[this.getNumberOfPlayers()].setDealer(true); 
        //Greet Block
            System.out.println("The win percentages are:\n Normal win - " + normalWin + "\n Natural win- " +blackjackWin + "\n\nThe minimum bet is " + minBet + "\n");
            Thread.sleep(1000);
    }
    
    //A new hand
    public void newHand() throws InterruptedException
    {
    
        for(int i=0;i<=numberOfPlayers;i++)
        {
          player[i].newHand(); //reinit the player's hand
        }

        ok = 0; // in use for dealer's face down card
       setIsSplit(false);
       setSplitBlackjack(false);
       setSplitTotal(0);

       for(int i=0;i<numberOfPlayers;i++)
       {

            System.out.println("Player " + (i+1) + " has " + this.player[i].getCreditPlayer() + " credits \n" );
            Thread.sleep(1000);
       }

       this.deck.shuffle(); 
    }
    //TEST--------------------------------------------------------------------------------------------------------------
    public int aceTest(int i,int type)
    {
        
        if(type == 1)
        {
            
            if(player[i].getPlayerTotal() + 11 <=21)
            {
                player[i].setHasAce(true);
                return 11;
            }
            else
                return 1;
        }
        return 0;
    }
    public int reductiveAce(int i)
    {
        if(player[i].getPlayerTotal() > 21)
        {   
            if(player[i].getHasAce())
                return -10;
            else    
                return 0;
        }
        return 0;
    }
    //HIt--------------------------------------------------------------------------------------------------------------------
    public boolean hit(int i) throws InterruptedException
    {   if(!player[i].getDealer())
        {
        System.out.println("Player " + (i+1) + " draws " + this.deck.getTopCard().getName() + "\n");
        }
        else
        {

            if(ok != 1)
            {
                System.out.println("Dealer draws " + this.deck.getTopCard().getName() + "\n");
                ok +=1;
            }
            else
            {
                System.out.println("Dealer's card is face down \n");
                ok +=1;
            }
        }
        Thread.sleep(1000);

        //Switch the top card
        Cards aux = this.deck.getTopCard();

        this.player[i].setPlayerTotal(player[i].getPlayerTotal() + aceTest(i,aux.getType()));
        this.player[i].setPlayerTotal(player[i].getPlayerTotal() + aux.getValue());
        this.player[i].playerCards.add(aux);
        this.deck.setTopCard(aux.getNextCard());

        if(player[i].getPlayerTotal() == 21 && player[i].playerCards.size() == 2 && player[i].getDealer() == false)
        {
        System.out.println("NATURAL! \n");
        Thread.sleep(1000);
        player[i].setPlayerBlackjack(true);
        player[i].setIsTurn(false);
        return false;
        }  

        else if(player[i].getDealer())
        {
            return true;
        }

        else if(player[i].getPlayerTotal() > 21 && reductiveAce(i) == -10 && player[i].getPlayerTotal() <31)
        {

            System.out.println("A total of " + (player[i].getPlayerTotal() - 10 ) + " \n");
            Thread.sleep(1000);
            return true;
        }
        else if(player[i].getPlayerTotal() > 21 )
        {
            System.out.println("BUST! \n");
            player[i].setIsTurn(false);
            Thread.sleep(1000);
            return false; //returns if the player's good to go or not(is bust or not,but reversed)
        }
        else
        {
            System.out.println("A total of " + player[i].getPlayerTotal() + " \n");
            Thread.sleep(1000);
            return true;
        }
    }

    //STAND -------------------------------------------------------------------
    public boolean stand(int i) throws InterruptedException
    {
        player[i].setPlayerTotal(player[i].getPlayerTotal() + reductiveAce(i)); //Check if the player can be saved by making an ace soft
        if(player[i].getPlayerTotal() > 21)
        {
            if(i == this.getNumberOfPlayers())
            {
             System.out.println("Dealer is bust");  
             Thread.sleep(1000);
                this.showHand(i); 
                return false;
            }
            else
            {
            System.out.println("Player " + (i+1) + " is bust");
            Thread.sleep(1000);
            this.showHand(i);
            return false;
            }
        }
        else    
        {
            this.showHand(i);
            return true;
        }
    }

    //Insurance bet
        public void Insurance() throws InterruptedException
        {
            if(this.player[this.getNumberOfPlayers()].playerCards.get(0).getType() == 1)
            {
                System.out.println("Dealer has an ace! \n");
                for(int i = 0 ; i < this.getNumberOfPlayers() ; i++)
                {
                    System.out.println("Player " + (i+1) + " - insurance bet \n");
                    float x = -1;
                    boolean ok = false;
                    while(ok == false)
                    {
                        try
                        {
                            x = scanner.nextInt();
                            scanner.nextLine();
                            if(x < 0)
                                 System.out.println("Bet too low\n");
                            else if(x > (this.player[i].getCreditPlayer()-this.player[i].getBet()))
                                System.out.println("Bet too high");
                            else if(0 == (this.player[i].getCreditPlayer()-this.player[i].getBet()))   
                            {
                                System.out.println("Out of credits\n");
                                x = 0;
                            }
                            else 
                                ok = true;
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("Invalit input \n");
                            scanner.nextLine();
                        }
                    } 
                    this.player[i].setInsuranceBet(x);
                    }
                }
        }
        
    //Hand
        public void showHand(int i) throws InterruptedException
        {
            //
           
            //
            if(this.player[i].playerCards!=null)
            {
                if(player[i].getDealer())
                System.out.println("Dealer has ");
                else
                System.out.println("Player " + (i+1) + " has ");
                this.player[i].playerCards.forEach((n)->System.out.print(n.getName() + " / "));
                System.out.println("For a total of " + player[i].getPlayerTotal() + " \n");
            }
        }
        public void dealerHand()
        {
            System.out.println("The dealer's first card is " + player[this.getNumberOfPlayers()].playerCards.get(0).getName() + " , the other is face down \n");
        }

        public float winTest(int i)
        {
            if(player[i].getPlayerTotal() >22)
            {
                return -100; //player is bust
            }
            else if(player[i].getPlayerBlackjack() && player[getNumberOfPlayers()].getPlayerBlackjack())
            { 
                return 100; //draw
            }
            else if(player[getNumberOfPlayers()].getPlayerBlackjack())
            {
                return -normalWin; //Dealer has black jack and player doesn't
            }
            else if(player[i].getPlayerBlackjack())
            {
                return this.getBlackJackWin(); //Player has blackjack and dealer doesn't
            }
            else if(player[i].getPlayerTotal() <22 && player[getNumberOfPlayers()].getPlayerTotal()>21)
            {
                return this.getNormalWin(); //player doesn't bust,but dealer does
            }
            else if(player[i].getPlayerTotal() <22 && player[getNumberOfPlayers()].getPlayerTotal() > player[i].getPlayerTotal())
            {
                return -100; //dealer wins by better hand
            }
            else if(player[i].getPlayerTotal() <22 && player[getNumberOfPlayers()].getPlayerTotal() < player[i].getPlayerTotal())
            {
                return this.getNormalWin(); //player wins by better hand
            }

            else if(player[i].getPlayerTotal() == player[getNumberOfPlayers()].getPlayerTotal())
            {
                return 100; //draw
            }
            else 
            return 0;
        }

        public void setBetForPlayer(int i) throws InterruptedException
        {
            System.out.println("How much will player " + (i+1) + " bet? \n Minum is " + this.minBet + "\n");
            float x;
            boolean ok = false;
            while(ok == false) 
            {
                try
                {
                    x = 0;
                    ok = false;
                    while(ok == false)
                    {
                        while(x < minBet || x > this.player[i].getCreditPlayer()) //check if the bet is above min bet
                        {
                            x = scanner.nextFloat();
                            scanner.nextLine();
                            if(x<minBet)
                            {
                                System.out.println("Bet too low \n");
                                ok = false;
                            }
                            else
                                 ok = true;
                        
                            if(x > this.player[i].getCreditPlayer())
                            {
                                System.out.println("Bet too high\n");
                                ok = false;
                            }
                             else
                                ok = true;
                        }
                    }
                    
                    System.out.println("Player " + (i+1) + " bets " + x + "\n");
                    Thread.sleep(1000);
                    player[i].setBet(x);
                    ok = true;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Invalid input \n");
                    scanner.nextLine();
                    ok = false ;
                }
            }
        }

        public void play() throws InterruptedException
        {
            System.out.println("How many players?\nMaximum is 4\n");
            boolean ok = false;
            int x = 0;
            while(ok == false)
            {
                ok = false;
                try
                {
                    x = 5;
                    while(x > 4 || x <= 0)
                    {
                        x= scanner.nextInt();
                        scanner.nextLine();
                        if(x <= 4 && x > 0)
                        {
                            ok = true;
                        }
                        else
                        {
                            System.out.println("Invalid number of players \n");
                            ok = false;
                        }
                    }
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Invalid input \n");
                    scanner.nextLine();
                }
            }

            Blackjack myGame = new Blackjack(x,100,200,250,10);
    
            myGame.newGame();
            while(myGame.q.equals("y"))
            {
                
                myGame.newHand();
                /* 
                     //Set Cards
                 Cards[] devSet = new Cards[6];
                devSet[0] = new Cards("Ace",0,1);
                devSet[1] = new Cards("Ace",0,1);
                devSet[2] = new Cards("Ace",0,1);
                devSet[3] = new Cards("King",10,13);
                devSet[3] = new Cards("King",10,13);
                devSet[3] = new Cards("King",10,13);
                myGame.deck.setCards(devSet);
                 */
                
                //Remove players out of credits
                for(int i = 0 ; i < myGame.getNumberOfPlayers() ; i++)
                    {
                      if(myGame.player[i].getCreditPlayer() <= 0)
                            myGame.player[i].setIsTurn(false);
                      else
                            myGame.player[i].setIsTurn(true);
                    }
                //Make the dealer active
                myGame.player[myGame.getNumberOfPlayers()].setIsTurn(true);
                 //Set bets
                for(int i = 0 ; i < myGame.getNumberOfPlayers() ; i++)
                    {
                        if(myGame.player[i].getIsTurn())
                            myGame.setBetForPlayer(i);
                    }
                //card 1 for all players
                for(int i = 0 ; i <= myGame.getNumberOfPlayers() ; i++)
                {
                    if(myGame.player[i].getIsTurn())
                        myGame.hit(i);
                }
                myGame.Insurance();
                //card 2 for all players
                for(int i = 0 ; i <= myGame.getNumberOfPlayers() ; i++)
                {
                    if(myGame.player[i].getIsTurn())
                        myGame.hit(i); 
                }
                //Dealer hand
                myGame.dealerHand();    
                for(int i = 0 ; i < myGame.getNumberOfPlayers() ; i++)
                    {
                        while(myGame.player[i].getIsTurn() == true)
                        {
                        myGame.showHand(i);
                        System.out.println("What will player " + (i+1) + " do? \nHit or Stand \n");
                        myGame.answer = myGame.scanner.nextLine();
                        myGame.answer = myGame.answer.toLowerCase();
                        switch(myGame.answer)
                        {
                            case("hit"):
                                myGame.hit(i);
                                break;
                            case("stand"):
                                myGame.stand(i);
                                myGame.player[i].setIsTurn(false);
                                break;    
                        }
                        }
                        System.out.println("Player " + (i+1) + " turn is over \n");
                    }
                //Dealer
                myGame.showHand(myGame.getNumberOfPlayers());;

                //Insurance
                if(myGame.player[myGame.getNumberOfPlayers()].playerCards.get(0).getType()==1 && myGame.player[myGame.getNumberOfPlayers()].getPlayerTotal() == 21)
                {
                    System.out.println("Dealer has BlackJack - insurance bet payoffs \n");
                    for(int i = 0 ; i < myGame.getNumberOfPlayers() ; i++)
                    {
                        float cr = ((myGame.player[i].getInsuranceBet() * myGame.getNormalWin()) / 100);
                        System.out.println("Player " + (i+1) + " gets " + cr + " \n" );
                        myGame.player[i].setCreditPlayer(myGame.player[i].getPlayerTotal() + cr);
                    }
                }
                else if (myGame.player[myGame.getNumberOfPlayers()].playerCards.get(0).getType()==1)
                {
                    System.out.println("Dealer doesn't have BlackJack \n");
                    for(int i = 0 ; i < myGame.getNumberOfPlayers() ; i++)
                    {
                        float cr = -myGame.player[i].getInsuranceBet();
                        System.out.println("Player " + (i+1) + " gets " + cr + " \n" );
                        myGame.player[i].setCreditPlayer(myGame.player[i].getPlayerTotal() + cr);
                    }   
                }
                //Dealer hits if he has a hand total of under 17
                while(myGame.player[myGame.getNumberOfPlayers()].getPlayerTotal() < 17)
                {
                    myGame.hit(myGame.getNumberOfPlayers());
                }
                myGame.stand(myGame.getNumberOfPlayers());
    
                 //Wins
                 for(int i = 0 ; i < myGame.getNumberOfPlayers() ; i++)
                 {
                    System.out.println("Player " + (i+1) + " gets " + ((myGame.player[i].getBet() * myGame.winTest(i))/100) + "\n");
                    myGame.player[i].setCreditPlayer(myGame.player[i].getCreditPlayer() + ((myGame.player[i].getBet() * myGame.winTest(i)) / 100) );
                 }
    
                System.out.println("Play again? y-yes? \n");    
                myGame.q = myGame.scanner.nextLine();
            }   
        }
}

    
