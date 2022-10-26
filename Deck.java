
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Deck
{
    private Cards[] cards = new Cards[52];
    private Cards topOfDeck;
    int i;
    Scanner scanner = new Scanner(System.in);
    String [] names = new String [3];

    //get
    public Cards getTopCard()
    {
        return this.topOfDeck;
    }

    //set

    public void setTopCard(Cards c)
    {
        this.topOfDeck = c;
    }


    //create the deck of cards
    public void make() throws InterruptedException
    {
        String[] parts=new String[2];
        i = 0; 
     
        //give the cards values from the enum
        for(Card_types ct : Card_types.values())
        {
            cards[i] = new Cards(ct.name(), ct.getValue(), ct.getType());
            parts = cards[i].getName().split("_");
            

            //Make the card name more readable
            cards[i].setName  
            (parts[0].substring(0,1) //First letter of the name
             + parts[0].substring(1).toLowerCase() //rest of the name, in lower case
             + " of "
             +parts[1].substring(0, 1)
             +parts[1].substring(1).toLowerCase()
             );

        i++;


        }
        topOfDeck = cards[0];
        this.shuffle(); 
        
       
    }

    //shuffle the deck
    public void shuffle() throws InterruptedException
    {
        
        //make the array of objects(cards) to a list so we can shuffle
        List<Object> cardsList = Arrays.asList(cards);
        Collections.shuffle(cardsList);
        cardsList.toArray(cards);

        //set up the deck - each card has a next card (except the last one of course)
        for(i=0;i<51;i++)
        {
            cards[i].setNextCard(cards[i+1]);
        }
        //make sure last card is configured correctly
        cards[51].setNextCard(null);
        //Top of deck
        topOfDeck = cards[0];
        System.out.println("Shuffled the cards \n");
        Thread.sleep(1000);
    }
    //testcase - DEV ONLY
    public void set4Cards(Cards c1,Cards c2)
    {
        cards[0] = c1;
        cards[1] = c1;
        cards[2] = c2;
        cards[3] = c2;

    }
    //testcase - DEV ONLY
    public void setCards(Cards[] c)
    {
        for(int i=0;i<c.length;i++)
        {
            cards[i].setName(c[i].getName());
            cards[i].setValue(c[i].getValue());
            cards[i].setType(c[i].getType());
        }
            this.setTopCard(cards[0]);
        for(int i=0;i<c.length-1;i++)
        {
            cards[i].setNextCard(cards[i+1]);
        }
            cards[c.length].setNextCard(cards[c.length+1]);
    }
    
    //show the deck -DEV ONLY
    public void showAllDeck()
    {
        for(int i=0;i<52;i++)
        {   
            System.out.println(cards[i].getName() + " - " + cards[i].getValue() + " - " + cards[i].getType() + " - ");
        }

    }
    public void showDeck()
    {
        Cards aux;
        aux = this.getTopCard();
        while(aux!=null)
        {    
            System.out.println(aux.getName() + " - " +  aux.getValue() + " - " + aux.getType() );
            aux = aux.getNextCard();
        }
    }

    
    //change the top card;
    public void burnTopCard()
    {
        this.setTopCard(getTopCard().getNextCard());
    }

}