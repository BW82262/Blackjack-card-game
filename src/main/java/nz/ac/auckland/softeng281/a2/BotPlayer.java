package nz.ac.auckland.softeng281.a2;

import java.util.Random;

/**
 * you should change this class for TASK 1
 */
public class BotPlayer extends Participant {

	//Creating a field to hold the current hand
	private Hand currentHand;
	

	public BotPlayer(String name) {
		super(name);
	}

	@Override
	public Action decideAction() {
		// TODO
		
		//Getting the current hand using the parent method
		currentHand = super.getCurrentHand();
		//Using getScore from hand
		currentHand.getScore();

		//Conditional statements for the current score.
		if(currentHand.getScore() >= 17) {
			return Action.HOLD;
		}
		
		if(currentHand.getScore() < 17) {
			return Action.HIT;
		}
		
		return new Random().nextBoolean() ? Action.HIT : Action.HOLD; // FIXME
	}

	@Override
	public int makeABet() {
		// TODO
		//Adding a random variable for the bet
		Random random = new Random();
		
		return random.nextInt(100)+1; // FIXME
	}
}
