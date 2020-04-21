/**
 * This is a subclass of Hand which models a Full House hand and 
 * checks if the hand is a valid Full House type or not
 * @author Bratin Ghosh 3035437692
 *
 */
public class FullHouse extends Hand{
    /**
     * Constructor for initializing with a specific player and card
     * @param player    specified player to whom the current hand belongs to
     * @param card      list of cards played by the current player
	 */
    public FullHouse(CardGamePlayer player, CardList card){
		super(player,card);
	}
	/**
     * Getter for the top card of the hand
	 */
	public Card getTopCard(){
		this.sort();
		if(this.getCard(0).rank == this.getCard(2).rank)
			return this.getCard(2);
		return this.getCard(4);
	}
	/**
     * Check if the hand is a valid hand or not
	 */
	public boolean isValid(){
		if(this.size() != 5)
			return false;
		this.sort(); 
		if(this.getCard(0).rank == this.getCard(2).rank)
			if(this.getCard(0).rank == this.getCard(1).rank && this.getCard(0).rank == this.getCard(2).rank && this.getCard(3).rank == this.getCard(4).rank)
				return true; 
		else if(this.getCard(2).rank == this.getCard(4).rank)
			if(this.getCard(2).rank == this.getCard(3).rank && this.getCard(2).rank == this.getCard(4).rank && this.getCard(0).rank == this.getCard(1).rank)
				return true;
		return false;
	}
	/**
     * Getter used to get the type of hand in String format
	 */
	public String getType(){
		return "FullHouse";
	}
}