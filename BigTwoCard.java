/**
 * This is a subclass of Card which models cards that are used in a Big 2 Game
 * @author Bratin Ghosh 3035437692
 *
 */
public class BigTwoCard extends Card{
    /**
     * Constructor for initializing with a specific player and card
     * @param player    specified player to whom the current hand belongs to
     * @param card      list of cards played by the current player
	 */
    public BigTwoCard(int suit, int rank){
        super(suit,rank);
    }
    /**
     * Compare 2 cards 
     */
    public int compareTo(Card card){
        int rank=this.rank;
        int card_rank=card.rank;
        if (rank==0 || rank==1)
            rank+=13;
        if (card_rank==0 || card_rank==1)
            card_rank+=13;
        if (rank > card_rank) {
            return 1;
        } else if (rank < card_rank) {
            return -1;
        } else if (getSuit() > card.suit) {
            return 1;
        } else if (getSuit() < card.suit) {
            return -1;
        } else {
            return 0;
        }
    }
}