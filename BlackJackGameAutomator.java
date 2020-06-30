package BlackJack;

public class BlackJackGameAutomator {
	private Deck deck;
	private BlackJackHand[] hands;
	private static final int NIT_UNTIL = 16;
	
	public BlackJackGameAutomator(int numPlayers) {
		hands = new BlackJackHand[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			hands[i] = new BlackJackHand();
		}
	}
	
	
}
