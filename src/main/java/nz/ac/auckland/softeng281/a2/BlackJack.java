package nz.ac.auckland.softeng281.a2;

import java.util.ArrayList;
import java.util.List;

/**
 * you should change this class for TASK 1, 2, 3, 4.
 */
public class BlackJack {

	private List<Participant> players;
	private Participant dealer;
	private double highestGain;
	private String name;
	private boolean WonCondition = false;
	

	public BlackJack() {
		players = new ArrayList<>();
		
		players.add(new HumanPlayer("Player1"));
		players.add(new BotPlayer("Bot1"));
		players.add(new BotPlayer("Bot2"));
		dealer = new BotDealer("Dealer", players);
		//ADDED BOTS 1 & 2
		
	}

	// getter setter for testing purposes
	public List<Participant> getPlayers() {
		return players;
	}

	public Participant getDealer() {
		return dealer;
	}

	public void setPlayers(List<Participant> players) {
		this.players = players;
	}

	public void setDealer(Participant dealer) {
		this.dealer = dealer;
	}

	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.start();
	}

	protected void start() {
		Utils.printBlackJack();
		// create a new deck of cards
		Deck deck = new Deck();
		String result;
		do {
			for (Participant player : players) {
				player.play(deck);
			}
			//Need the dealer to play now.
			dealer.play(deck);
			
			// ADDHERE Task 2
			checkWinner();
			System.out.println("Do you want to play again?");
			result = Utils.scanner.next();
			while (!result.equals("yes") && !result.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				result = Utils.scanner.next();
			}
		} while (result.equals("yes"));
		printPlayerHighestGain();
	}

	public void checkWinner() {
		
		for (Participant player : players) { // KEEPTHIS
			// ADDHERE
			//Reseting after every player
			WonCondition = false;
			
			//Winning conditions
			//If the player instantly gets blackjack
			if(player.getCurrentHand().getScore() == 21) {
				if(player.getCurrentHand().getCards().size() == 2) {
					System.out.println(player.getName() + " wins"); // UNCOMMENT AND KEEPTHIS
					player.setBetWinnings(player.getCurrentHand().getBet()*1.5); //Blackjack gets 1.5x return
					WonCondition = true;
				}
			} 	
			//If the player has <= 21 but dealer busts
			if (WonCondition == false) {
				if(player.getCurrentHand().getScore() <= 21) {
					if(dealer.getCurrentHand().getScore() > 21) {
						System.out.println(player.getName() + " wins"); // UNCOMMENT AND KEEPTHIS
						player.setBetWinnings(player.getCurrentHand().getBet());
						WonCondition = true;
					}
				}
			}
			
			//If the player and dealer both have <= 21			
			if(WonCondition == false) {
				if(player.getCurrentHand().getScore() <= 21) {
					if(player.getCurrentHand().getScore() > dealer.getCurrentHand().getScore()) {
						System.out.println(player.getName() + " wins"); // UNCOMMENT AND KEEPTHIS
						player.setBetWinnings(player.getCurrentHand().getBet());
						WonCondition = true;
					}
				} 
			}
			
			//Used to count the losses from bets.
			if(WonCondition == false) {
				player.setMinusBetWinnings(player.getCurrentHand().getBet());
			}
			
		}

	}

	public void printPlayerHighestGain() {
		// TODO Task 4
		//The first player will automatically be the highest. Base case
		highestGain = players.get(0).getBetWinnings();
		name = players.get(0).getName();
		for (Participant player : players) {
			if(player.getBetWinnings() > highestGain) {
				highestGain = player.getBetWinnings();
				name = player.getName();
				
			}
		}
							
		System.out.println("The player with the highest gain is: " + name + " with " + highestGain + " chips"); // UNCOMMENT AND KEEPTHIS
	}
}
