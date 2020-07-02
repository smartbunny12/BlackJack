package BlackJack;

import java.util.ArrayList;
import java.util.List;
// different game has different rules, all card game can extends Hand
public class BlackJackHand extends Hand{
	@Override
	public final int score() {
		List<Integer> scores = possibleScores();
		int maxUnder = Integer.MIN_VALUE; // max score <= 21
		int minOver = Integer.MAX_VALUE; // min score > 21
		for (int score: scores) {
			if (score > 21 && score < minOver) {
				minOver = score;
			} else if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
		}
		return maxUnder == Integer.MIN_VALUE ? minOver: maxUnder;
		
	}
	
	public List<Integer> possibleScores() {
		List<Integer> scores = new ArrayList<>();
		for (Card card : cards) {
			updateScores(card, scores);
		}
		return scores;
	}
	
	private void updateScores(Card card, List<Integer> scores) {
		final int[] toAdd = getScores(card);
		if (scores.isEmpty()) {
			for (int score : toAdd) {
				scores.add(score);
			}
		} else {
			final int length = scores.size();
			for (int i = 0; i < length; i++) {
				int oldScore = scores.get(i);
				scores.set(i, oldScore + toAdd[0]);
				for (int j = 1; j < toAdd.length; j++) {
					scores.add(oldScore + toAdd[j]);
				}
			}
		}
	}
	
	private int[] getScores(Card card) {
		// 1 : an used as 1 or 11
		// [2, 10]: value is the card value
		// 11, 12, 13: value is 10
		if (card.value() > 1) {
			return new int[] {Math.min(card.value(), 10)};
		} else { // Ace
			return new int[] {1, 11};
		}
	}
	
	public boolean busted() {
		return score() > 21;
	}
	
	// the rule for get BlackJack: 2 cards; 1 for ace, 1 for face(11,12,13)
	public boolean isBlackJack() {
		if (cards.size() != 2) {
			return false;
		}
		Card first = cards.get(0);
		Card second = cards.get(1);
		return (isAce(first) && isFaceCard(second)) 
				|| (isAce(second) && isFaceCard(first));
	}
	
	private static boolean isAce(Card c) {
		return c.value() == 1;
	}
	
	private static boolean isFaceCard(Card c) {
		return c.value() >= 11 && c.value() <= 13;
	}
	
	
}
