/**
 * This is a subclass of Deck which models a deck of cards in the Big 2 Game
 * @author Bratin Ghosh 3035437692
 *
 */
public class BigTwoDeck extends Deck{
    /**
     * The method initializes the variables and creates a fresh deck of Big 2 cards
     */
    public void initialize(){
        removeAllCards();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				BigTwoCard card = new BigTwoCard(i, j);
				this.addCard(card);
			}
		}
    }
}