package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	// the public card deck
	private static final Random random = new Random();
	
	private final List<Card> cards = new ArrayList<>();
	private int dealtIndex = 0;
	
	public Deck() {
		for (int i = 1; i <= 13; i++) {
			for (Suit suit : Suit.values()) {
				cards.add(new Card(i, suit));
			}
		}
	}
	
	// use random algorithm to shuffle the cards
	public void shuffle() {
		for (int i = 0; i < cards.size() - 1; i++) {
			// choose a random number from [i, size)
			int j = random.nextInt(cards.size() - i) + i;
			// swap between the randomly chosen card and current card
			Card card1 = cards.get(i);
			Card card2 = cards.get(j);
			cards.set(i, card2);
			cards.set(j, card1);
		}
	}
	
	private int remainingCards() {
		return cards.size() - dealtIndex;
	}
	
	// deal multiple cards
	public Card[] dealHand(int number) {
		if (remainingCards() < number) {
			return null;
		}
		Card[] cards = new Card[number];
		for (int i = 0; i < number; i++) {
			cards[i] = dealCard();
		}
		return cards;
	}
	
	// deal card from deck to hand
	public Card dealCard() {
		return remainingCards() == 0 ? null : cards.get(dealtIndex++);
	}
}
