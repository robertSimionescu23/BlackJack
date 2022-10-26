
public class Cards {
    private Cards next;
    private String name;
    private int val;
    private int type;

    //METHODS
    
    //get
    public Cards getNextCard()
    {
        return this.next;
    }

    public String getName()
    {
        return this.name;
    }

    public int getValue()
    {
        return this.val;
    }

    public int getType()
    {
        return this.type;
    }

    //set
    public void setNextCard(Cards c)
    {
        this.next = c;
    }

    public void setName(String s)
    {
        this.name = s;
    }

    public void setValue(int v)
    {
        this.val = v;
    }

    public void setType(int t)
    {
        this.type = t;
    }

    //Constructor
    Cards(String name , int value , int type)
    {
        this.name = name;
        this.val = value;
        this.type = type;
    }
    Cards(){};

}
