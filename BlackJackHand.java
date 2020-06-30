package BlackJack;

import java.util.List;

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
		return null;
	}
}
