package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameAutomator {
	private Deck deck;
	private BlackJackHand[] hands; //players array
	private static final int NIT_UNTIL = 16;
	
	public BlackJackGameAutomator(int numPlayers) {
		hands = new BlackJackHand[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			hands[i] = new BlackJackHand();
		}
	}
	
	void initializeDeck() {
		deck = new Deck();
		deck.shuffle();
	}
	
	// start the game, start with 2 cards to every layer
	boolean dealInitial() {
		for (BlackJackHand hand : hands) {
			Card[] cards = deck.dealHand(2);
			// may not have enough cards
			if (cards == null) {
				return false;
			}
			hand.addCards(cards);
		}
		return true;
	}
	
	List<Integer> getBlackJacks() {
		List<Integer> winners = new ArrayList<>();
		for (int i = 0; i < hands.length; i++) {
			if (hands[i].isBlackJack()) {
				winners.add(i);
			}
		}
		return winners;
	}
}
