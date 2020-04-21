/**
 * This is a subclass of Hand which models a Pair hand and 
 * checks if the hand is a valid Pair type or not
 * @author Bratin Ghosh 3035437692
 *
 */
public class Pair extends Hand{
    /**
     * Constructor for initializing with a specific player and card
     * @param player    specified player to whom the current hand belongs to
     * @param card      list of cards played by the current player
	 */
    public Pair(CardGamePlayer player, CardList card){
        super(player,card);
    }
    /**
     * Getter for the top card of the hand
	 */
    public Card getTopCard(){
        if(this.getCard(1).suit > this.getCard(0).suit)
            return this.getCard(1);  
        else
            return this.getCard(0);
    }
    /**
     * Check if the hand is a valid hand or not
	 */
    public boolean isValid(){
        if(this.size() == 2 && this.getCard(0).rank == this.getCard(1).rank)
            return true;
        return false;   
    }
    /**
     * Getter used to get the type of hand in String format
	 */
    public String getType(){
        return "Pair";
    }
}