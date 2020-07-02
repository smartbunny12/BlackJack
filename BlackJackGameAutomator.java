package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameAutomator {
	private Deck deck;
	private BlackJackHand[] hands; //players array
	private static final int HIT_UNTIL = 16;
	
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
	
	//create the rule: if the score does not reach the HIT_UNTIL, always want a card
	// if score > HIT_UNTIL, do nothing in this round
	boolean playHand(BlackJackHand hand) {
		while (hand.score() < HIT_UNTIL) {
			Card card = deck.dealCard();
			if (card == null) { // no enough cards
				return false;
			} 
			hand.addCards(new Card[] {card});
		}
		return true;
	}
	
	// for the simplicity of the game: every player take cards continuously until read the hit
	boolean playAllHands() {
		 for (BlackJackHand hand : hands) {
			 if (!playHand(hand)) {
				 return false;
			 }
		 }
		 return true;
	}
	
	List<Integer> getWinners() {
		List<Integer> winners = new ArrayList<>();
		int winningScore = 0;
		for (int i = 0; i < hands.length; i++) {
			BlackJackHand hand = hands[i];
			if (!hand.busted()) {
				if (hand.score() > winningScore) {
					winningScore = hand.score();
					winners.clear();
					winners.add(i);
				} else if (hand.score() == winningScore) {
					winners.add(i);
				}
			}
		}
		return winners;
	}
	
	void printHandAndScore() {
		for (int i = 0; i < hands.length; i++) {
			System.out.println("Hand " + i + " (" + hands[i].score() + "): ");
			hands[i].print();
			System.out.println();
		}
	}
	
	public void simulate() {
		initializeDeck();
		
		boolean success = dealInitial();
		if (!success) {
			System.out.println("Error. Out of cards");
			return;
		}
		System.out.println("- - Initial - -");
		printHandAndScore();
		
		List<Integer> blackjacks = getBlackJacks();
		if (blackjacks.size() > 0) {
			System.out.print("Blackjack at ");
			for (int i : blackjacks) {
				System.out.println(i + " ");
			}
			System.out.println("done");
			return; // someone get the blackjack, the game ends
		}
		
		success = playAllHands();
		if (!success) {
			System.out.println("Error. Out of cards");
			return;
		}
		
		System.out.println("\n- - Completed Game - -");
		printHandAndScore();
		List<Integer> winners = getWinners();
		if (winners.size() > 0) {
			System.out.println("Winners: ");
			for (int i : winners) {
				System.out.print(i + " ");
			}
			System.out.println();
		} else {
			System.out.println("Draw. All players have busted.");
		}
		
	}
	
	// test case
	public static void main(String[] args) {
		BlackJackGameAutomator automator = new BlackJackGameAutomator(5);
		automator.simulate();
	}
	
	
	
	
	
	
}
