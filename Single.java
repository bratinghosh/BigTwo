/**
 * This is a subclass of Hand which models a single hand and 
 * checks if the hand is a valid single type
 * @author Bratin Ghosh 3035437692
 *
 */
public class Single extends Hand{
    /**
     * Constructor for initializing with a specific player and card
     * @param player    specified player to whom the current hand belongs to
     * @param card      list of cards played by the current player
	 */

    public Single(CardGamePlayer player, CardList card){
        super(player,card);
    }
    /**
     * Getter for the top card of the hand
	 */
    public Card getTopCard(){
        return this.getCard(0);
    }
    /**
     * Check if the hand is a valid hand or not
	 */
    public boolean isValid(){
        if(this.size() == 1)
            return true;
        else
            return false;
    }
    /**
     * Getter used to get the type of hand in String format
	 */
    public String getType(){
        return "Single";
    }
}