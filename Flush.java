/**
 * This is a subclass of Hand which models a Flush hand and 
 * checks if the hand is a valid Flush type or not
 * @author Bratin Ghosh 3035437692
 *
 */
import java.util.Arrays;
public class Flush extends Hand{
    /**
     * Constructor for initializing with a specific player and card
     * @param player    specified player to whom the current hand belongs to
     * @param card      list of cards played by the current player
	 */
    public Flush(CardGamePlayer player, CardList card){
		super(player,card);
	}
	/**
     * Getter for the top card of the hand
     * @param number[]  stores the rank of 5 cards
     * @param index     index of the top card
	 */
    public Card getTopCard(){
		int numbers[] = {this.getCard(0).rank, this.getCard(1).rank, this.getCard(2).rank, this.getCard(3).rank, this.getCard(4).rank}; // storing the rank of 5 cards inside array
		int index = 0;
		for(int i=0;i<5;i++)
            if(numbers[i] == 0 || numbers[i] == 1)
                numbers[i] += 13;
		Arrays.sort(numbers);
		for(int i = 0; i < numbers.length;i++)
			if(this.getCard(i).rank == numbers[4])
				index = i;
		return this.getCard(index);
	}
	/**
     * Check if the hand is a valid hand or not
	 */
	public boolean isValid(){
		if(this.size() == 5 && this.getCard(0).suit == this.getCard(1).suit && this.getCard(1).suit == this.getCard(2).suit && this.getCard(2).suit == this.getCard(3).suit && this.getCard(3).suit == this.getCard(4).suit)
			return true;
		return false;
	}
	/**
     * Getter used to get the type of hand in String format
	 */
	public String getType(){
		return "Flush";
	}
}