package coup_standalone;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * <p>The Player class manages player influence, coins, hands, etc.</p>
 * 
 * <p>If a Player has 1 influence, the card they reveal will go in slot 2.</p>
 * 
 * @author Simon Gladstone
 * @author Wasif Khan
 *
 */
public class Player {
	
	// h for human, c for AI
	char type;
	
	// Player hand
	String[] hand = new String[2];
	
	// Player influence & coins
	int influence, coins;
	
	// Player name (just Player 1 or Player 2)
	String name = null;
	
	/* CONSTRUCTORS */
	/**
	 * <p>Default constructor for the Player class.</p>
	 * 
	 * <p>Sets default values:
	 * 	<ul>
	 * 		<li>type: Computer</li>
	 * 		<li>influence: 2</li>
	 * 		<li>coins: 2</li>
	 * 		<li>name: Default AI</li>
	 * 	</ul>
	 * </p>
	 * 
	 * @see #Player(char)
	 * @see #Player(char, String)
	 */
	public Player() {
		type = 'c';
		influence = 2;
		coins = 2;
		name = "Default AI";
	}
	
	/**
	 * <p>Constructor for Player class with Player Type as an arguments.</p>
	 * 
	 * <p>Use <code>'h'</code> to set as Human, and <code>'c'</code> to set as Computer (AI).</p>
	 * 
	 * @param t	Upper- or lower-case characters c or h
	 * @throws IllegalArgumentException if argument is not upper- or lower-case characters c or h
	 */
	public Player(char t) throws IllegalArgumentException {
		
		char tl = Character.toLowerCase(t);
		
		if (!(tl == 'h' || tl == 'c')) {
			throw new IllegalArgumentException(t + " is not a valid option for Player type.");
		}
		else {
			type = tl;
		}
		
		influence = 2;
		name = "Player__";
		coins = 2;
	}
	
	/**
	 * <p>Constructor for Player class with Player Type and Name as arguments.</p>
	 * 
	 * @param t	Upper- or lower-case characters c or h for Player Type
	 * @param s	Any String for Player Name
	 * @throws IllegalArgumentException if player type is not upper- or lower-case characters c or h
	 */
	public Player(char t, String s) throws IllegalArgumentException {
		char tl = Character.toLowerCase(t);
		
		if (!(tl == 'h' || tl == 'c')) {
			throw new IllegalArgumentException(t + " is not a valid option for Player type.");
		}
		else {
			type = tl;
		}
		
		influence = 2;
		name = s;
		coins = 2;
	}

	/* GETTERS */
	/**
	 * <p>Function for getting Player type. Returns 'h' for Human, 'c' for Computer (AI).</p>
	 * 
	 * @return	character 'h' if Player is Human, or character 'c' if Player is AI
	 */
	public char getType() {
		return type;
	}
	
	/**
	 * <p>Function for getting Player influence. Returns as Integer.</p>
	 * 
	 * @return integer representing Player Influence
	 */
	public int getInf() {
		return influence;
	}
	
	/**
	 * <p>Function for getting Player coins. Returns as Integer.</p>
	 * 
	 * @return integer representing Player coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * <p>Function for getting Player name. Returns as String.</p>
	 * 
	 * @return String representing Player name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>Function for getting Player hand. Returns as a String array of size 2.<p>
	 * 
	 * <p>The following are possible elements of the array (quotation marks to indicate they are Strings):
	 * 	<ul>
	 * 		<li>"CONTESSA"</li>
	 * 		<li>"CAPTAIN"</li>
	 * 		<li>"ASSASSIN"</li>
	 * 		<li>"AMBASSADOR"</li>
	 * 		<li>"DUKE"</li>
	 * 	</ul>
	 * 
	 * @return	String[2] representing Player hand. See possible Strings above.
	 * 
	 * @see	#getHandVec()
	 */
	public String[] getHand() {
		return hand;
	}
	
	/**
	 * <p>Function for getting Player hand. Returns as a String Vector of size 2.</p>
	 * 
	 * @return	Vector 
	 */
	public Vector<String> getHandVec() {
		return new Vector<String>(Arrays.asList(hand));
	}
	
	/* SETTERS */
	/**
	 * <p>Function for setting Player type. Use 't' to set as Human, or 'c' to set as Computer.<p>
	 * 
	 * @param t Upper- or lower-case character 'c' or 'h'
	 * @throws IllegalArgumentException If the provided character is not upper- or lower-case 'c' or 'h'
	 */
	public void setType(char t) throws IllegalArgumentException {
		char tl = Character.toLowerCase(t);
		
		if (!(tl == 'h' || tl == 'c')) {
			throw new IllegalArgumentException(t + " is an invalid Player type. Acceptable input is 'h' or 'c'.");
		}

		type = tl;
		
		System.out.println(this.getName() + " now type " + tl);
		
	}
	
	public void AIaddHandInfo(String card1, String card2) {
		return;
	}
	
	/**
	 * <p>Function for setting the hand to the desired two cards.</p>
	 * 
	 * <p>Each array element must be one of the following (quotation marks to indicate they are Strings):
	 * 	<ul>
	 * 		<li>"DUKE"</li>
	 * 		<li>"CONTESSA"</li>
	 * 		<li>"ASSASSIN"</li>
	 * 		<li>"AMBASSADOR"</li>
	 * 		<li>"CAPTAIN"</li>
	 * 	</ul>
	 * </p>
	 * 
	 * @param h Array of size 2 where both elements are as above
	 * @throws IllegalArgumentException If the Array is not of size 2
	 * @throws IllegalArgumentException If either of the elements of the Array are not
	 * 										as outlined above
	 */
	public void setHand(String[] h) throws IllegalArgumentException {
		if (h.length != 2) {
			throw new IllegalArgumentException("Invalid array size. Array must have 2 elements.");
		}
		
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("DUKE");
		vc.addElement("CONTESSA");
		vc.addElement("ASSASSIN");
		vc.addElement("AMBASSADOR");
		vc.addElement("CAPTAIN");
		
		for (int i = 0; i < 2; i++) {
			if (!vc.contains(h[i])) {
				throw new IllegalArgumentException("Invalid card name in Array.");
			}
		}
		
		vc.clear();

		hand = h;
		
	}
	
	/**
	 * <p>Function for setting a specific card of the Player's hand.</p>
	 * 
	 * <p>The String must be one of the following (quotation marks to indicate they are Strings):
	 * 	<ul>
	 * 		<li>"DUKE"</li>
	 * 		<li>"CONTESSA"</li>
	 * 		<li>"ASSASSIN"</li>
	 * 		<li>"AMBASSADOR"</li>
	 * 		<li>"CAPTAIN"</li>
	 * 	</ul>
	 * </p>
	 * 
	 * @param n	index of the card being set. Must be 1 or 2.
	 * @param s	Card the slot is being set to (see above for accepted input)
	 * @throws IllegalArgumentException	If card number is not 1 or 2
	 * @throws IllegalArgumentException If card name is not as above
	 */
	public void setCard(int n, String s) throws IllegalArgumentException {
		if (n < 1 || n > 2) {
			throw new IllegalArgumentException("Card number must be either 1 or 2.");
		}
		
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("DUKE");
		vc.addElement("CONTESSA");
		vc.addElement("ASSASSIN");
		vc.addElement("AMBASSADOR");
		vc.addElement("CAPTAIN");
		
		if (!vc.contains(s)) {
			throw new IllegalArgumentException("Invalid card name: " + s);
		}
		
		vc.clear();
		
		hand[n-1] = s;
		
	}
	
	/**
	 * <p>Function for setting a Player's Influence. Must be between 0 and 2 inclusive.</p>
	 * 
	 * <p><strong>DO NOT USE IN PLACE OF <code>removeInf()</code></strong></p>
	 * 
	 * @param n Amount of influence to set
	 * @throws IllegalArgumentException	If amount of influence provided is not between 0 and 2 inclusive.
	 * 
	 * @see #removeInf()
	 */
	public void setInf(int n) throws IllegalArgumentException {
		if (n < 0 || n > 2) {
			throw new IllegalArgumentException("Invalid amount of influence. Players must have between 0 and 2 influence.");
		}
		
		influence = n;
		
	}
	
	/**
	 * <p>Function for setting a Player's name.</p>
	 * 
	 * @param s	New player name to use
	 */
	public void setName(String s) {
		name = s;
	}
	
	/**
	 * <p>Function for removing 1 influence from a player.</p>
	 * 
	 * <p>Also contains the prompts for a human player to decide, and the logic for an AI to decide, which card to show if they
	 * 		have 2 influence.</p>
	 * 
	 * <p>For uses other than in-game influence changes, use <code>setInf(int)</code>.</p>
	 * 
	 * @param gs Scanner used to prompt the user to decide which card to show
	 * 
	 * @see #setInf(int)
	 */
	public void removeInf(Scanner gs) {
		
		// if they have 2 influence, they must choose a card to show
		if (this.getInf() == 2) {
			// valid cards to keep (i.e. cards in the player's hand)
			Vector<String> vc = new Vector<String>();
			
			// add their hand as valid input
			vc.addElement(this.getHand()[0]);
			vc.addElement(this.getHand()[1]);
			
			// input card from player revealing
			String ic = null;
			
			switch (this.getType()) {
			// human
			case 'h':
				
				// Prompts
				System.out.println("Please select a card to show:");
				System.out.println("\t1. " + this.getHand()[0]);
				System.out.println("\t2. " + this.getHand()[1]);
				
				// Get input
				ic = Coup.getInputnq(vc, gs);
				
				//AIrevealCard(ic);
				
				break;
			
			// AI
			case 'c':
				
				// get card to swap
				ic = AIcardToReveal();

				break;
			}
			
			// Now the shown card has to go to slot 2 (just because)
			if (ic.equalsIgnoreCase(this.getHand()[0])) {
				String cardswap = this.getHand()[1];
				this.setCard(2, this.getHand()[0]);
				this.setCard(1, cardswap);
			}
			
			// don't do anything if they're showing card 2, it's already in the right spot
		}
		
		influence--;
		
		System.out.println(this.getName() + " influence reduced by 1.");
	}
	
	/**
	 * <p>Function for adding/removing coins from a player. Use a positive integer to add, and negative to subtract.</p>
	 * 
	 * @param n Amount of coins to add (negative to remove coins)
	 */
	public void addCoins(int n) {
		
		System.out.println("Adding " + n + " coins to " + this.getName() + "\n");
		
		coins += n;
	}
	
	/* MISC */
	public boolean AIchallenge(String s) {
		return false;
	}
	
	/**
	 * <p>This function contains both the AI's logic behind deciding to challenge the other player's move,
	 * 		and the prompts required to ask a Human player what they would like to do.</p>
	 * 
	 * <p>The card being challenged must be provided as a String and must be a valid challengeable card, and a Scanner must also be provided.</p>
	 * 
	 * <p>Behaves nearly identically to <code>block(String, Scanner)</code>.</p>
	 * 
	 * @param s		The card being challenged
	 * @param gs	The Scanner
	 * @return		boolean depending on whether the Player wants to challenge or not
	 * @throws IllegalArgumentException	If the card being challenged is invalid
	 * 
	 * @see #block(String, Scanner)
	 */
	public boolean challenge(String s, Scanner gs) throws IllegalArgumentException {
		
		// valid cards
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("DUKE");
		vc.addElement("CONTESSA");
		vc.addElement("ASSASSIN");
		vc.addElement("AMBASSADOR");
		vc.addElement("CAPTAIN");
		vc.addElement("2CARDS");
		
		// check for valid card name
		if (!vc.contains(s)) {
			throw new IllegalArgumentException("Invalid card name.");
		}
		
		
		
		// switch for human/AI
		switch(this.getType()) {
		// human
		case 'h':
			
			System.out.println("Would you like to Challenge? (Y/N)");
			
			Vector<String> vi = new Vector<String>();
			
			vi.addElement("Y");
			vi.addElement("N");
						
			String input = Coup.getInputnq(vi, gs);
			
			if (input.equalsIgnoreCase("Y")) {
				return true;
			}
			else {
				return false;
			}
		
		// AI
		case 'c':
			boolean rc = AIchallenge(s); 
			
			String ddn = "does not";
			
			if (rc) {
				ddn = "does";
			}
			
			System.out.println("\nComputer  " + ddn + " challenge.");
			
			return rc;

		default:
			return false;
			
		}
	}

	public boolean AIblock(String s) {
		return false;
	}
	
	/**
	 * <p>This function contains both the AI's logic behind deciding to block the other player's move,
	 * 		and the prompts required to ask a Human player what they would like to do.</p>
	 * 
	 * <p>The card being blocked must be provided as a String and must be a valid blockable card, and a Scanner must also be provided.</p>
	 * 
	 * <p>Behaves nearly identically to <code>challenge(String, Scanner)</code>.</p>
	 * 
	 * @param s		The card being blocked
	 * @param gs	The Scanner
	 * @return		boolean depending on whether the Player wants to block or not
	 * @throws IllegalArgumentException	If the card being blocked is invalid
	 * 
	 * @see #challenge(String, Scanner)
	 */
	public boolean block(String s, Scanner gs) throws IllegalArgumentException {
		// valid cards
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("ASSASSIN");
		vc.addElement("CAPTAIN");
		vc.addElement("FOREIGN AID");
		
		// check for valid card name
		if (!vc.contains(s)) {
			throw new IllegalArgumentException("Invalid card name");
		}
		
		// switch for human/AI
		switch(this.getType()) {
		// human
		case 'h':
			
			System.out.println("Would you like to block? (Y/N)");
			
			Vector<String> vi = new Vector<String>();
			
			vi.addElement("Y");
			vi.addElement("N");
			
			String input = Coup.getInputnq(vi, gs);
			
			if (input.equalsIgnoreCase("Y")) {
				return true;
			}
			else {
				return false;
			}
		
		// AI
		case 'c':
			boolean rc = AIblock(s);
			
			String ddn = "does not";
			
			if (rc) {
				ddn = "does";
			}
			
			System.out.println("\nComputer  " + ddn + " block.");
			
			return rc;

		default:
			return false;
			
		}
	}
	
	public char AIdecision(String s) {
		return 'n';
	}
	
	/**
	 * <p>This function contains both the AI's logic behind, and the prompts required to ask a Human Player,
	 * 		whether they would like to block, challenge, or neither in response to a card where these are all options.</p>
	 * 
	 * <p>The card being decided upon must be provided as a String and must be a valid both challengeable and blockable card,
	 * 		and a Scanner must also be provided.</p>
	 * 
	 * @param s		The String being decided upon
	 * @param gs	The Scanner
	 * @return		character 'b' for block, 'c' for challenge, or 'n' for neither
	 * @throws IllegalArgumentException	If the card being decided upon is invalid
	 */
	public char decision(String s, Scanner gs) throws IllegalArgumentException {
		
		// valid cards
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("ASSASSIN");
		vc.addElement("CAPTAIN");
		
		// check for valid card name
		if (!vc.contains(s)) {
			throw new IllegalArgumentException("Invalid card name");
		}
		
		// switch for human/AI
		switch(this.getType()) {
		// human
		case 'h':
			System.out.println("Would you like to block, challenge, or neither?");
			
			Vector<String> vi = new Vector<String>();
			
			vi.addElement("Block");
			vi.addElement("Challenge");
			vi.addElement("Neither");
						
			String input = Coup.getInputnq(vi, gs);
			
			if (input.equalsIgnoreCase("Block")) {
				return 'b';
			}
			else if (input.equalsIgnoreCase("Challenge")){
				return 'c';
			}
			else {
				return 'n';
			}
		
		// AI
		case 'c':
			// AI decide to block here
			char rc = AIdecision(s);
			
			switch(rc) {
			case 'b':
				System.out.println(this.getName() + " blocks!");
				break;
			case 'c':
				System.out.println(this.getName() + " challenges!");
				break;
			case 'n':
				System.out.println(this.getName() + " takes it like a bitch!");
				break;
			}
			
			return rc;

		default:
			return 'n';
			
		}
	}
	
	public String AImove() {
		System.out.println("Should not print");
		return "INCOME";
	}
	
	/**
	 * <p>This function is the one called every time a Player must make a move. It contains the AI logic behind deciding
	 * 		what card to play, and calls <code>Coup.getInput(Vector, Scanner)</code> to get Player input.</p>
	 * 
	 * <p>Possible Outputs (quotation marks indicate they are Strings):
	 * 	<ul>
	 * 		<li>"DUKE"</li>
	 *  	<li>"CAPTAIN"</li>
	 *   	<li>"AMBASSADOR"</li>
	 *    	<li>"CONTESSA"</li>
	 *     	<li>"ASSASSIN"</li>
	 *      <li>"quit"</li>	
	 *	</ul>
	 * </p>
	 *      
	 * @param vi	Vector of valid inputs
	 * @param gs	The Scanner
	 * @return		The String corresponding to the card the Player wishes to use. See above for possible outputs.
	 * 
	 * @see Coup#getInput(Vector, Scanner)
	 */
	public String move(Vector<String> vi, Scanner gs) {
		if (this.getType() == 'h') {
			return Coup.getInput(vi, gs);
		}
		else {
			return AImove();
		}
	}
	
	/**
	 * <p>Function returns true if the Player has the provided card.</p>
	 * 
	 * @param card	The card being checked for
	 * @return		<code>true</code> if the Player has the provided card. <code>false</code> otherwise.
	 * @throws IllegalArgumentException	if the provided card is not a valid card
	 */
	public boolean hasCard(String card) throws IllegalArgumentException {
		
		boolean rc = false;
		
		// valid cards
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("DUKE");
		vc.addElement("CONTESSA");
		vc.addElement("ASSASSIN");
		vc.addElement("AMBASSADOR");
		vc.addElement("CAPTAIN");
		vc.addElement("2CARDS");
		
		// check for valid card name
		if (!vc.contains(card)) {
			throw new IllegalArgumentException("Invalid card name");
		}
		
		if (!card.equalsIgnoreCase("2CARDS") && (this.getHand()[0].equalsIgnoreCase(card) || this.getHand()[1].equalsIgnoreCase(card))) {
			rc = true;
		}
		else if (card.equalsIgnoreCase("2CARDS")) {
			if (this.getHand()[0].equalsIgnoreCase("CAPTAIN") || this.getHand()[0].equalsIgnoreCase("AMBASSADOR") ||
					this.getHand()[1].equalsIgnoreCase("CAPTAIN") || this.getHand()[1].equalsIgnoreCase("AMBASSADOR")) {
				rc = true;
			}
		}
		
		if (this.getType() == 'c') {
			AIgotChallenged(card, rc);
		}
		
		else if (this.getType() == 'h') {
			AIchallengeResult(card, !rc);
		}
		
		return rc;
	}
	
	/**
	 * <p>Function for swapping a card for a new one.</p>
	 * 
	 * <p>Must provide the card being swapped, as well as the deck.</p>
	 * 
	 * @param card	The card being swapped
	 * @param d		The deck from which a new card is taken
	 * @throws IllegalArgumentException	If the card being swapped is invalid.
	 */
	public void swap(String card, Deck d) throws IllegalArgumentException {
		// valid cards
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("DUKE");
		vc.addElement("CONTESSA");
		vc.addElement("ASSASSIN");
		vc.addElement("AMBASSADOR");
		vc.addElement("CAPTAIN");
		
		// check for valid card name
		if (!vc.contains(card)) {
			throw new IllegalArgumentException("Invalid card name");
		}
		
		if (!(hand[0].equalsIgnoreCase(card) || hand[1].equalsIgnoreCase(card))) {
			throw new IllegalArgumentException("Player does not have card in hand.");
		}
		
		// get index of card being swapped
		int i;
		if (hand[0].equalsIgnoreCase(card)) {
			i = 0;
		}
		else {
			i = 1;
		}
		
		// get new card
		String nc = d.pop();
		
		// Tell the AI what cards they're gaining and losing (in that order)
		AInewCard(nc, hand[i]);
		
		// put old card back in deck
		d.add(hand[i]);
		
		// set new card
		hand[i] = nc;
		
		// shuffle deck
		d.shuffle();
	}

	public String[] AIambassador (String card1, String card2) {
		return null;
	}
	
	

	/**
	 * <p>This function contains the AI logic and user interface for using the Ambassador card.</p>
	 * 
	 * <p>Function requires the deck from which to pull new cards, as well as a Scanner for the User Interface.</p>
	 * 
	 * @param d		The deck from which new cards are pulled
	 * @param gs	The Scanner		
	 */
	public void ambassador(Deck d, Scanner gs) {
		switch(type) {
		// Human
		case('h'):
			// influence check
			boolean inf;
			if (influence == 2) {
				inf = true;
			}
			else {
				inf = false;
			}
			
			// for cards to go in, also input
			String nc1, nc2, kc1, kc2, ng1, ng2, input;
			
			// valid inputs
			Vector<String> vi = new Vector<String>();
			
			if (!inf) {
				// get new card
				nc1 = d.pop();
				nc2 = d.pop();
				
				// input prompts
				System.out.println("Please select a card to keep: ");
				System.out.println("\t1. " + hand[0]);
				System.out.println("\t2. " + nc1);
				System.out.println("\t3. " + nc2);
				
				// set valid input
				vi.add("1");
				vi.add("2");
				vi.add("3");
				
				// get input
				input = Coup.getInputnq(vi, gs);
				
				// if they want card 2, put old card back, keep new one
				if (input.equals("2") || input.equals("3")) {
					d.add(hand[0]);
				
					if (input.equals("2")) {
						this.setCard(1, nc1);
						d.add(nc2);
					}
					else {
						this.setCard(1, nc2);
						d.add(nc1);
					}
				}
				
				// put new ones back if they want to keep old one
				else {
					d.add(nc1);
					d.add(nc2);
				}
			}
			else {
				// get new cards
				nc1 = d.pop();
				nc2 = d.pop();
				
				// make a vector for the available cards
				Vector<String> ac = new Vector<String>();
				
				ac.add(hand[0]);
				ac.add(hand[1]);
				ac.add(nc1);
				ac.add(nc2);
				
				// don't worry about it
				int k;
				
				// input prompt
				System.out.println("Please select the first card to keep (INPUT NUMBER IN LIST, NOT CARD NAME): ");
				for (int i = 0; i < 4; i++) {
					k = i + 1;
					System.out.println("\t" + k + " " + ac.elementAt(i));
				}
				
				// set valid input
				vi.add("1");
				vi.add("2");
				vi.add("3");
				vi.add("4");
				
				// get input
				input = Coup.getInputnq(vi, gs);
				
				// set first card to keep & remove from available cards vector
				kc1 = ac.elementAt(Integer.parseInt(input) - 1);
				ac.removeElementAt(Integer.parseInt(input) - 1);
				
				// input prompt 2
				System.out.println("Please select the second card to keep(INPUT NUMBER IN LIST, NOT CARD NAME): ");
				for (int i = 0; i < 3; i++) {
					k = i + 1;
					System.out.println("\t" + k + " " + ac.elementAt(i));
				}
				
				// set valid input
				vi.add("1");
				vi.add("2");
				vi.add("3");
				
				// get input
				input = Coup.getInputnq(vi, gs);
				
				// set first card to keep & remove from available cards vector
				kc2 = ac.elementAt(Integer.parseInt(input) - 1);
				ac.removeElementAt(Integer.parseInt(input) - 1);
				
				// set neglected cards
				ng1 = ac.firstElement();
				ng2 = ac.elementAt(1);
				
				// clear ac
				ac.clear();
				
				// set hand
				this.setCard(1, kc1);
				this.setCard(2, kc2);
				
				// put cards back on deck
				d.add(ng1);
				d.add(ng2);
			}
			
			break;
		// AI
		case('c'):
			String[] result = AIambassador(d.pop(), d.pop());
		
			this.setCard(1, result[0]);
		
			if(this.getInf() == 2) {
				this.setCard(2, result[1]);
			}
			
			d.add(result[2]);
			d.add(result[3]);
			
			break;		
		}
		
		// shuffle the deck
		d.shuffle();
		
	}
	
	public void AIrevealCard(String card) {
		return;
	}
	
	public String AIcardToReveal() {
		return this.getHand()[0];
	}
	
	public void AIgotChallenged(String s, boolean won) {
		return;
	}
	
	public void AIchallengeResult(String s, boolean won) {
		return;
	}
	
	public void AIdoesMove(String s) {
		return;
	}
	
	public void AInewCard(String newCard, String oldCard) {
		return;
	}

	public void AIgotCouped() {
		return;
	}
}
