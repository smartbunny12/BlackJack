package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	// all the cards a user have so far
	protected final List<Card> cards = new ArrayList<>();
	
	// calculate the total scores
	public int score() {
		int score = 0;
		for (Card card: cards) {
			score += card.value();
		}
		return score;
	}
	
	public void addCards(Card[] c) {
		Collections.addAll(cards, c);
	}
	
	public int size() {
		return cards.size();
	}
	
	public void print() {
		String s = "";
		for (Card c : cards) {
			s += " (Value: " + c.value() + ", Suit: "+ c.suit() + ") ";
		}
		System.out.println(s);
	}
}
