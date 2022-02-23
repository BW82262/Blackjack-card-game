package nz.ac.auckland.softeng281.a2;

import java.util.List;
import java.util.Random;

import nz.ac.auckland.softeng281.a2.Participant.Action;

/**
 * you should change this class for TASK 2
 */
public class BotDealer extends Participant {

	private List<Participant> players;
	private Hand currentDealerHand;
	private int winningHands = 0;

	public BotDealer(String name, List<Participant> players) {
		super(name);
		this.players = players;
	
		// ADDHERE
	}

	@Override
	public Action decideAction() {
		// TODO
		
		//Getting the current hand using the parent method
		currentDealerHand = super.getCurrentHand();
		
		//Using a for loop to check every player's hand
		for (Participant player : players) {
			if(player.getCurrentHand().getScore() > currentDealerHand.getScore()) {
				if(player.getCurrentHand().getScore() <=21 ) {
					//If the player's hand is more than the dealer, and not busted
					winningHands++;
				}			
			}
		}

		//If the dealer busts, then just hit. Doesn't really matter here
		if(currentDealerHand.getScore() >21) {
			return Action.HIT;
		}
		
		//Hit or Hold depending on the number of winning hands.
		if(winningHands >= 2) {
			return Action.HIT;
		} else if (winningHands < 2) {
			return Action.HOLD;
		}
		
		
		return new Random().nextBoolean() ? Action.HIT : Action.HOLD; // FIXME

	}

	@Override
	/**
	 * do not touch this method
	 */
	public int makeABet() {
		// the Dealer doesn't bet so is always zero
		return 0;
	}
}
