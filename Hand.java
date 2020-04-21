/**
 * This is an abstract class and a subclass of CardList which models a hand of cards and acts as the
 * superclass for different hands like single, pair, quad,etc.
 * @author Bratin Ghosh 3035437692
 *
 */
public abstract class Hand extends CardList{
    /**
     * Constructor for initializing with a specific player and card
     * @param player    specified player to whom the current hand belongs to
     * @param card      list of cards played by the current player
     */
    public Hand(CardGamePlayer player, CardList cards){
        this.player = player;
        for(int i = 0; i < cards.size();i++)
            this.addCard(cards.getCard(i));
    }
    private CardGamePlayer player;//instance variable of a Card Game Player
    /**
     * Getter function to get the player of the hand
     * @param player      the current player of the hand
     */
    public CardGamePlayer getPlayer(){
        return this.player;
    }
    /**
     * Getter function to get the top card of the hand, this method is overwritten by the specific hand class like 
     * flush, straight, single, pair, etc.
     */
    public Card getTopCard(){
        return null;
    }
    /**
	 * A boolean method checking whether the current hand beats the previous hand of cards
	 * @param hand     current hand of cards being played
	 * @return true    when the current hand beats the previous hand 
	 * @return false   when the current hand doesn't beats the previous hand 
	 */
    public boolean beats(Hand hand){
        if(hand.size() == 1)
            if(this.size() == hand.size() && this.isValid() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                return true;
        if(hand.size() == 2)
            if(this.size() == hand.size() && this.isValid() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                return true;
        if(hand.size() == 3)
            if(this.size() == hand.size() && this.isValid() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                return true;
        if(hand.size() == 5){
            if(this instanceof StraightFlush){
                if(this.size() == hand.size()){
                    if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                        return true;
                    else if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) == -1)
                        return false;
                    else
                        return true;
                }
            }
            if(this instanceof Straight){
                if(this.size() == hand.size()){
                    if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                        return true;
                    else if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) != 1)
                        return false;   
                    else
                        return false;
                }   
            }
            if(this instanceof Flush){
                if(this.size() == hand.size())
                {
                    if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                        return true;
                    else if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) != 1)
                        return false;
                    else
                        if(hand.getType() == "Straight")
                            return true;
                }   
            }
            if(this instanceof Quad){
                if(this.size() == hand.size()){
                    if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                        return true;
                    else if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) != 1)
                        return false;
                    else
                        if(hand.getType() != "StraightFlush")
                            return true;
                }
            }
            if(this instanceof FullHouse){
                if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) == 1)
                    return true;
                else if(this.getType() == hand.getType() && this.getTopCard().compareTo(hand.getTopCard()) != 1)
                    return false;
                else
                    if(hand.getType() == "Straight" || hand.getType() == "Flush")
                        return true;
            }       
        }
        return false;
    }
    /**
     * Abstract class overwritten by subclasses
     */
    public abstract boolean isValid();
    /**
     * Abstract class overwritten by subclasses
     */
    public abstract String getType();
}