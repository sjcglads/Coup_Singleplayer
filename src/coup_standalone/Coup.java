package coup_standalone;

import java.util.Scanner;
import java.util.Random;
import java.util.Vector;

/**
 * <p>The Coup class is the main class for the software package. It contains most
 * 		miscellaneous functions like <code>isNumeric(String s)</code>, <code>is_valid(String, Vector)</code>
 * 		, etc.</p>
 * 
 * <p>Most importantly, however, this class contains the <code>Coup(Scanner)</code> and <code>Turn(Scanner)</code>
 * 		functions which do game initialization and in-game mechanics respectively.</p>
 * 
 * @author Simon Gladstone
 * 
 * @see #Coup(Scanner)
 * @see #Turn(Scanner)
 */
public class Coup {
	
	// debug switch
	public static boolean dbg = false;
	
	// the deck
	Deck deck = new Deck();
	
	// list of possible cards
	Vector<String> cards = new Vector<String>();
	
	// Generic Error Message
	String GEM = "Invalid Input. Please try again: ";
	
	// Random number generator
	static Random rng = new Random();
	static int cp_rng;
	
	// Players
	Player p1, p2;
	
	// For input checking
	Vector<String> IC = new Vector<String>();
	
	/* MISC FUNCTIONS */
	/**
	 * <p>Returns a boolean depending on whether or not the string input is numeric.</p>
	 * 
	 * @param 	s	Any String
	 * @return		boolean dependent on cardinality of s
	 */
	public static boolean isNumeric(String s) {
		try {
			double d = Double.parseDouble(s);
			
			if (d >= 1); // gets rid of the warning on above line
		}
		catch (NumberFormatException nfe) {
			
			if (dbg) {
				System.out.println("*DEBUG* isNumeric(\"" + s + "\") returning FALSE.\n");
			}
			
			return false;
		}
		
		if (dbg) {
			System.out.println("*DEBUG* isNumeric(\"" + s + "\") returning TRUE.\n");
		}
		
		return true;
	}
	
	/**
	 * <p>Returns a boolean depending on whether or not String s is present in String a.</p>
	 * 
	 * <p>Boolean c is the switch for case sensitivity.</p>
	 * 
	 * @param 	s	The String to search for
	 * @param 	a	The Array in which to search
	 * @param 	c	Flag for case sensitivity
	 * @return		boolean dependent on String presence in Array
	 */
	public static boolean isIn(String s, String[] a, boolean c) {
		for (int i = 0; i < a.length; i++) {
			if (c && s.equals(a[i])) {
				
				if (dbg) {
					System.out.println("*DEBUG* isIn returning TRUE");
				}
				
				return true;
			}
			else if (!c && s.equalsIgnoreCase(a[i])) {
				
				if (dbg) {
					System.out.println("*DEBUG* isIn returning TRUE");
				}
				
				return true;
			}
		}
		
		if (dbg) {
			System.out.println("*DEBUG* isIn returning FALSE");
		}
		
		return false;
	}
	
	/* UI FUNCTIONS */
	/**
	 * <p>Returns a boolean depending on whether or not input is considered "valid". Valid
	 * 		inputs are any contained in the provided Vector, or "quit" or "help".</p>
	 * 
	 * <p>Function is <strong>not</strong> case-sensitive.</p>
	 * 
	 * @param 	input	String to be considered
	 * @param 	a		Vector of allowed strings
	 * @return			boolean dependent on String validity
	 */
	public static boolean is_valid(String input, Vector<String> a) {
		
		if (input.equalsIgnoreCase("quit")) {
			
			if (dbg) {
				System.out.println("*DEBUG* is_valid returning TRUE\n");
			}
			
			return true;
		}
		else if (input.equalsIgnoreCase("help")) {
			
			if (dbg) {
				System.out.println("*DEBUG* is_valid returning TRUE\n");
			}
			
			return true;
		}
		else {
			int vlen = a.size();
			for (int i = 0; i < vlen; i++) {
				if (input.equalsIgnoreCase(a.elementAt(i))) {
					
					if (dbg) {
						System.out.println("*DEBUG* is_valid returning TRUE\n");
					}
					
					return true;
				}
			}
		}
		
		if (dbg) {
			System.out.println("*DEBUG* is_valid returning FALSE");
		}
		
		return false;
	}
	
	/**
	 * <p>Same as <code>is_valid(String, Vector)</code>, but does not allow
	 * 		the word "quit".</p>
	 * 
	 * @param 	input	String to be considered
	 * @param 	a		Vector of allowed strings
	 * @return			boolean dependent on String validity
	 * @see		#is_valid(String, Vector)
	 */
	public static boolean is_validnq(String input, Vector<String> a) {
		int vlen = a.size();
		
		for (int i = 0; i < vlen; i++) {
			if (input.equalsIgnoreCase(a.elementAt(i))) {
				
				if (dbg) {
					System.out.println("*DEBUG* is_validnq returning TRUE");
				}
				
				return true;
			}
		}
		
		if (dbg) {
			System.out.println("*DEBUG* is_validnq returning FALSE");
		}
		
		return false;
	}

	/**
	 * <p><strong>Function not yet complete.</strong></p>
	 * 
	 * <p>Function contains the entire "Help" interface, and returns "helped" when complete.</p>
	 * 
	 * @param 	s	Scanner needed to get and process input.
	 * @return		Always returns String "helped" to indicate completion.
	 */
	public static String Help(Scanner s) {
		
		String input;
		
		// Simple help or lots of help
		System.out.println("Welcome to the Coup help menu. Please select an option (enter the corresponding number):\n");
		System.out.println("\t1. Input Commands help\n\t2. Coup rules help\n\t3. Both 1 & 2");
		
		// Get Input
		input = s.nextLine();
		System.out.println();
		
		// Valid inputs
		Vector<String> goodInput = new Vector<String>();
		
		goodInput.addElement("1");
		goodInput.addElement("2");
		goodInput.addElement("3");
		
		// Require valid input
		while (!is_valid(input, goodInput)) {
			// Print error message
			System.out.println("Invalid input. Please select a command:\n\t1. Input Commands help\n\t2. Coup rules help\n\t3. Both 1 & 2");
			input = s.nextLine();
			System.out.println();
		}
		
		// Escape for quit command
		if (input.equalsIgnoreCase("quit")) {
			return "quit";
		}
		
		else if (input.equalsIgnoreCase("help")) {
			return "help";
		}
		
		// Command Help
		else if (input.equals("1")) {
			System.out.println("Input Command Help:\n\n");
			System.out.println("\t1. To take Income, type \"INCOME\".\n");
			System.out.println("\t2. To take Foreign Aid, type \"FOREIGN AID\".\n");
			System.out.println("\t3. To use a Duke and take 3 gold, type \"DUKE\".\n");
			System.out.println("\t4. To Coup a player, type \"COUP\".\n");
			System.out.println("\t5. To Assassinate a player, type \"ASSASSIN\".\n");
			System.out.println("\t6. To use a Captain and steal 2 gold from a player, type \"CAPTAIN\".\n");
			System.out.println("\t7. To use an Ambassador to swap cards, type \"AMBASSADOR\".\n");
			
			return "helped";
		}
		
		// Coup Rules
		else if (input.equals("2")) {
			System.out.println("Coup Rules:\n");
			
			System.out.print("You are head of a family in an Italian city-state, a city run by a weak and corrupt court. You need ");
			System.out.print("to manipulate, bluff, and bribe your way to power. Your object is to destroy the influence of all the ");
			System.out.println("other families, forcing them into exile. Only one family will survive...\n");
			
			System.out.print("In Coup, you want to be the last player with influence in the game, with influence being represented by ");
			System.out.println("face-down character cards in your playing area.\n");
			
			System.out.print("Each player starts the game with two coins and two influence -- i.e., two face-down character cards; ");
			System.out.println("the fifteen-card deck consists of three copies of five different characters, each with a unique set of powers: ");
			System.out.println("\t- Duke: Take three coins from the treasury, or block someone from taking foreign aid.");
			System.out.println("\t- Assassin: Pay three coins and try to assassinate another player's character.");
			System.out.println("\t- Contessa: Block an assassination attempt against you.");
			System.out.println("\t- Captain: Take two coins from another player, or block another player from stealing your coins.");
			System.out.print("\t- Ambassador: Draw two character cards from the deck, choose which (if any) to exchange with your face-down ");
			System.out.println("characters, then return two. Can also be used to block Captains.\n");
			
			System.out.print("On your turn, you can take any of the actions listed above, regardless of which characters you ");
			System.out.println("actually have in front of you, or you can take one of three other actions:");
			System.out.println("\t- Income: Take one coin from the treasury.");
			System.out.println("\t- Foreign Aid: Take two coins from the treasury.");
			System.out.print("\t- Coup: Pay seven coins and launch a coup against another player, forcing that player to lose ");
			System.out.println("one influence. If you have 10 or more coins, you must take this action.\n");
			
			System.out.print("When you take one of the character actions -- whether actively on your turn, or defensively in response ");
			System.out.print("to someone else's action -- that character's action automatically succeeds *unless* an target ");
			System.out.print("challenges you. In this case, if you can't reveal the appropriate character, you lose ");
			System.out.print("an influence, turning one of your characters face-up. Face-up characters cannot be used, and if ");
			System.out.println("both of your characters are face-up, you are out of the game.\n");
			
			System.out.print("If you do have the character in question, the target loses an influence, then you shuffle that ");
			System.out.println("character back into the deck and draw a new one.\n");
			
			System.out.println("The last player to still have influence -- that is, a face-down character -- wins the game!\n");
			
			return "helped";
		}
		
		// Both
		else {
			// Print Coup rules
			System.out.println("Coup Rules:\n");
			
			System.out.print("You are head of a family in an Italian city-state, a city run by a weak and corrupt court. You need ");
			System.out.print("to manipulate, bluff, and bribe your way to power. Your object is to destroy the influence of all the ");
			System.out.println("other families, forcing them into exile. Only one family will survive...\n");
			
			System.out.print("In Coup, you want to be the last player with influence in the game, with influence being represented by ");
			System.out.println("face-down character cards in your playing area.\n");
			
			System.out.print("Each player starts the game with two coins and two influence -- i.e., two face-down character cards; ");
			System.out.println("the fifteen-card deck consists of three copies of five different characters, each with a unique set of powers: ");
			System.out.println("\t- Duke: Take three coins from the treasury, or block someone from taking foreign aid.");
			System.out.println("\t- Assassin: Pay three coins and try to assassinate another player's character.");
			System.out.println("\t- Contessa: Block an assassination attempt against you.");
			System.out.println("\t- Captain: Take two coins from another player, or block another player from stealing your coins.");
			System.out.print("\t- Ambassador: Draw two character cards from the deck, choose which (if any) to exchange with your face-down ");
			System.out.println("characters, then return two. Can also be used to block Captains.\n");
			
			System.out.print("On your turn, you can take any of the actions listed above, regardless of which characters you ");
			System.out.println("actually have in front of you, or you can take one of three other actions:");
			System.out.println("\t- Income: Take one coin from the treasury.");
			System.out.println("\t- Foreign Aid: Take two coins from the treasury.");
			System.out.print("\t- Coup: Pay seven coins and launch a coup against another player, forcing that player to lose ");
			System.out.println("one influence. If you have 10 or more coins, you must take this action.\n");
			
			System.out.print("When you take one of the character actions -- whether actively on your turn, or defensively in response ");
			System.out.print("to someone else's action -- that character's action automatically succeeds *unless* an target ");
			System.out.print("challenges you. In this case, if you can't reveal the appropriate character, you lose ");
			System.out.print("an influence, turning one of your characters face-up. Face-up characters cannot be used, and if ");
			System.out.println("both of your characters are face-up, you are out of the game.\n");
			
			System.out.print("If you do have the character in question, the target loses an influence, then you shuffle that ");
			System.out.println("character back into the deck and draw a new one.\n");
			
			System.out.println("The last player to still have influence -- that is, a face-down character -- wins the game!\n");
			
			// Print input command help
			System.out.println("Input Command Help:\n");
			System.out.println("\t1. To take Income, type \"INCOME\".");
			System.out.println("\t2. To take Foreign Aid, type \"FOREIGN AID\".");
			System.out.println("\t3. To use a Duke and take 3 gold, type \"DUKE\".");
			System.out.println("\t4. To Coup a player, type \"COUP\".");
			System.out.println("\t5. To Assassinate a player, type \"ASSASSIN\".");
			System.out.println("\t6. To use a Captain and steal 2 gold from a player, type \"CAPTAIN\".");
			System.out.println("\t7. To use an Ambassador to swap cards, type \"AMBASSADOR\".");
			
			return "helped";
		}
	}
	
	/**
	 * <p>Override for <code>getInput(Vector, String, Scanner, boolean)</code> that defaults
	 * 		the boolean flag to true.</p>
	 * 
	 * @param 	ValidInput		Vector of valid inputs
	 * @param 	ErrorMessage	Error message to print if input is invalid
	 * @param 	s				Scanner for getting input
	 * @return					Valid string provided by user as a String
	 * 
	 * @see 	#getInput(Vector, String, Scanner, boolean)
	 */
	public static String getInput(Vector<String> ValidInput, String ErrorMessage, Scanner s) {
		return getInput(ValidInput, ErrorMessage, s, true);
	}
	
	/**
	 * <p>Override for <code>getInput(Vector, String, Scanner, boolean)</code> that defaults
	 * 		the Error Message string to a generic error message.</p>
	 * 
	 * @param 	ValidInput	Vector of valid inputs
	 * @param 	s			Scanner for getting input
	 * @param 	help		Flag to allow/disallow "help" as valid
	 * @return				Valid input provided by user as a String
	 * 
	 * @see		#getInput(Vector, String, Scanner, boolean)
	 */
	public static String getInput(Vector<String> ValidInput, Scanner s, boolean help) {
		return getInput(ValidInput, null, s, help);
	}
	
	/**
	 * <p>Override for <code>getInput(Vector, String, Scanner, boolean)</code> that defaults
	 * 		the error message to a generic one, and allows "help" as valid.</p>
	 * 
	 * @param 	ValidInput	Vector of valid inputs
	 * @param 	s			Scanner for getting input
	 * @return				Valid String provided by user
	 * 
	 * @see		#getInput(Vector, String, Scanner, boolean)
	 */
	public static String getInput(Vector<String> ValidInput, Scanner s) {
		return getInput(ValidInput, null, s, true);
	}

	/**
	 * <p>Function called to get input from System.in. Calls <code>is_valid(String, Vector)</code>
	 * 		to determine input validity, but loops until input is valid and prints error messages
	 * 		when invalid.</p>
	 * 
	 * <p>Function primarily used in Player class and in game initialization.</p>
	 * 
	 * @param 	ValidInput		Vector of valid inputs
	 * @param 	ErrorMessage	Error Message to print if input is invalid
	 * @param 	s				Scanner required for getting input from System.in
	 * @param 	help			Flag for allowing/disallowing "help" as valid input
	 * @return					the valid String provided by user.
	 * 
	 * @see		#is_valid(String, Vector)
	 */
	public static String getInput(Vector<String> ValidInput, String ErrorMessage, Scanner s, boolean help) {
		// return code
		String rc = null;
		
		// set an error message if one is not provided
		if (ErrorMessage == null) {
			ErrorMessage = "Error: Invalid input. Please enter valid input:";
		}
		
		// if help is valid input and isn't already in the array,
		//		add it
		if (help && !ValidInput.contains("help")) {
			ValidInput.addElement("help");
		}
		
		// if help is NOT valid input and IS in the array, remove it
		if (!help && ValidInput.contains("help")) {
			ValidInput.removeElement("help");
		}
		
		// get input
		String i = s.nextLine();
		
		// validation loop
		while (!is_valid(i, ValidInput)) {
			System.out.println(ErrorMessage);
			i = s.nextLine();
		}
		
		rc = i;
		
		if (dbg) {
			System.out.println("\n*DEBUG* getInput returning " + i + "\n");
		}
		
		return rc;
	}
	
	/**
	 * <p>Identical to <code>getInput(Vector, Scanner)</code>, but does not allow "quit".</p>
	 * 
	 * @param 	ValidInput	Vector of valid inputs
	 * @param 	s			Scanner for getting input
	 * @return				Valid String provided by user
	 * 
	 * @see		#getInput(Vector, Scanner)
	 */
	public static String getInputnq(Vector<String> ValidInput, Scanner s) {
		String i = s.nextLine();
		
		while (!is_validnq(i, ValidInput)) {
			System.out.println("Invalid input. Please try again.");
			i = s.nextLine();
		}
		
		if (dbg) {
			System.out.println("\n*DEBUG* getInputnq returning " + i + "\n");
		}
		
		return i;
	}
	
	/* GAME MECHANICS */
	/**
	 * <p>This function is essentially the entire game. <code>Coup(Scanner)</code> runs game initialization,
	 * 		but this does pretty much everything else mechanics-wise.</p>
	 * 
	 * @param gs Scanner for getting input
	 */
	public void Turn(Scanner gs) {
		
		System.out.println();
		
		if (dbg) {
			System.out.print("*DEBUG* Resetting input scanner...");
		}
		
		gs.reset();
		
		if (dbg) {
			System.out.println(" Done.\n");
		}
		
		String input = null; // user input goes here
		Vector<String> RVI = new Vector<String>(); // valid input for turns
		
		boolean challenge = false, block = false, win = false; // booleans for block & challenge decisions & results
		boolean execute = false, over = false, redo = false; // booleans for whether or not to execute a move, game over, or redo turn
		
		char decision = 'n'; // for when you must decide between blocking, challenging, or neither
		
		// Players
		Player user = p1;
		Player target = p2;
		Player inter = null; // used for swapping players each turn
		
		// counters
		int turnCount = 0;
		
		// set turn valid input
		RVI.add("INCOME");
		RVI.add("FOREIGN AID");
		RVI.add("DUKE");
		RVI.add("ASSASSIN");
		RVI.add("AMBASSADOR");
		RVI.add("CAPTAIN");
		RVI.add("COUP");
		
		input = "notquit";
		
		/*
		 *  MAIN GAME LOOP STARTS HERE
		 */
		while (!input.equalsIgnoreCase("quit") && !over) {
			
			if (dbg) {
				System.out.println("\n*DEBUG* Starting loop on turn #" + turnCount + "\n");
			}
			
			execute = false;
			win = false;
			challenge = false;
			block = false;
			
			// set user & target
			if (turnCount > 0 && !redo) {
				inter = target;
				target = user;
				user = inter;
				inter = null;
			}
			
			redo = false;
			
			// set user cards
			cards = user.getHandVec();
			
			// Game status update
			System.out.println(user.getName() + " coins: " + user.getCoins());
			System.out.println(user.getName() + " influence: " + user.getInf());
			System.out.println();
			System.out.println(target.getName() + " coins: " + target.getCoins());
			System.out.println(target.getName() + " influence: " + target.getInf());
			
			System.out.println();
			
			// Input prompts
			if (user.getType() == 'h') {
				System.out.println("Your cards:");
				System.out.println("\t1. " + user.getHand()[0]);
				System.out.println("\t2. " + user.getHand()[1] + "\n");
				System.out.println("Please enter a valid move: ");
			}
			
			// get input
			if (user.getCoins() < 10) {
				input = user.move(RVI, gs);
			}
			else {
				System.out.println(user.getName() + " has >10 coins, so must coup.");
				input = "COUP";
			}
			
			System.out.println();
			
			if (dbg) {
				System.out.print("*DEBUG* " + user.getName() + " will ");
			}
			
			// Income
			if (input.equalsIgnoreCase(RVI.elementAt(0))) {
				
				if (dbg) {
					System.out.println("Income");
				}
				
				System.out.println(user.getName() + " uses Income."); 
				
				// give user 1 coin
				user.addCoins(1);
			}
			
			// Foreign Aid
			else if (input.equalsIgnoreCase(RVI.elementAt(1))) {
				
				if (dbg) {
					System.out.println("Foreign Aid");
				}
				
				System.out.println(user.getName() + " uses Foreign Aid."); 
				
				// get block decision from target
				block = target.block("FOREIGN AID", gs);
				
				// target blocks
				if (block) {
					// get challenge decision from user
					challenge = user.challenge("DUKE", gs);
					
					// user challenges block
					if (challenge) {
						win = false; // will be switched to a method
						
						// target did not have Duke
						if (win) {
							// remove target influence
							target.removeInf();
							execute = true;
						}
						
						// target did have Duke
						else {
							// remove user influence
							user.removeInf();
							
							// swap target Duke for new card
							target.swap("DUKE", deck);
						}
					}
					
					// user does not challenge block
					else {
						// do nothing
					}
				}
				
				// target does not block
				else {
					execute = true;
				}
				
				if (execute) {
					// give user 2 coins
					user.addCoins(2);
				}
			}
			
			// Duke
			else if (input.equalsIgnoreCase(RVI.elementAt(2))) {
				
				if (dbg) {
					System.out.println("Duke");
				}
				
				System.out.println(user.getName() + " uses Duke."); 
				
				// get challenge decision from target
				challenge = target.challenge("DUKE", gs);
				
				// target will challenge duke
				if (challenge) {
					win = false;
					
					// user had a Duke
					if (win) {
						// remove target influence 
						target.removeInf();
						
						// swap user Duke for new card
						user.swap("DUKE", deck);
						
						execute = true;
					}
					
					// user did not have a Duke
					else {
						// remove user influence
						user.removeInf();
					}
				}
				
				// target will not challenge duke
				else {
					execute = true;
				}
				
				if (execute) {
					// give user 3 coins
					user.addCoins(3);
				}
			}
			
			// Assassin
			else if (input.equalsIgnoreCase(RVI.elementAt(3))) {
				
				if (dbg) {
					System.out.println("Assassin");
				}
				
				// user does not have adequate gold
				if (user.getCoins() < 3) {
					System.out.println("Insufficient gold. Please try again.");
					redo = true;
				}
				
				// user has adequate gold
				else {
					
					System.out.println(user.getName() + " uses Assassin.");
					
					// get target block/challenge decision
					decision = target.decision("ASSASSIN", gs);
					
					// remove 3 coins from user
					user.addCoins(-3);
					
					switch (decision) {
					// target claims Contessa
					case 'b':
						// get challenge decision from user
						challenge = user.challenge("CONTESSA", gs);
						
						// user challenges Contessa claim
						if (challenge) {
							win = false; // will be changed to method
							
							// target did not have a Contessa
							if (win) {
								// remove target influence
								target.removeInf();
								execute = true;
							}
							
							// target did have a Contessa
							else {
								// user loses influence
								user.removeInf();
								
								// target swap Contessa for new card
								target.swap("CONTESSA", deck);
							}
						}
						
						// user does not challenge Contessa claim
						else {
							// do nothing
						}
						
						break;
					
					// target challenges Assassin
					case 'c':
						win = false;
						
						// user had Assassin
						if (win) {
							// target loses influence
							target.removeInf();
							
							// user swap Assassin for new card
							user.swap("ASSASSIN", deck);
							
							execute = true;
						}
						
						// user did not have Assassin
						else {
							// user loses influence
							user.removeInf();
						}
						
						break;
					
					// target takes it like a bitch
					case 'n':
						execute = true;
						break;
					}
					
					if (execute) {
						// remove 1 influence from target
						target.removeInf();
					}
				}
			}
			
			// Ambassador
			else if (input.equalsIgnoreCase(RVI.elementAt(4))) {
				
				if (dbg) {
					System.out.println("Ambassador");
				}
				
				System.out.println(user.getName() + " uses Ambassador.");
				
				// get challenge decision from target
				challenge = target.challenge("AMBASSADOR", gs);
				
				// target challenges Ambassador
				if (challenge) {
					win = false;
					
					// user had Ambassador
					if (win) {
						// remove target influence
						target.removeInf();
						
						// swap user Ambassador for new card
						user.swap("AMBASSADOR", deck);
						
						execute = true;
					}
					
					// user did not have Ambassador
					else {
						// user remove influence
						user.removeInf();
					}
				}
				
				// target does not challenge Ambassador
				else {
					execute = true;
				}
				
				if (execute) {
					user.ambassador(deck, gs);
				}
			}
			
			// Captain
			else if (input.equalsIgnoreCase(RVI.elementAt(5))) {
				
				if (dbg) {
					System.out.println("Captain");
				}
				
				System.out.println(user.getName() + " uses Captain.");
				
				// get target decision
				decision = target.decision("CAPTAIN", gs);
				
				switch(decision) {
				// target claims Captain or Ambassador
				case 'b':
					// get user challenge decision
					challenge = user.challenge("CAPTAIN", gs);
					
					// user challenges Captain/Ambassador claim
					if (challenge) {
						win = false;
						
						// target had neither Captain nor Ambassador
						if (win) {
							// target loses influence
							target.removeInf();
							execute = true;
						}
						
						// target had either Captain or Ambassador
						else {
							// user loses influence
							user.removeInf();
							
							// swap target Captain/Ambassador for new card
							if(isIn("CAPTAIN", target.getHand(), false)) {
								target.swap("CAPTAIN", deck);
							}
							else {
								target.swap("AMBASSADOR", deck);
							}
						}
					}
					
					// user does not challenge
					else {
						// do nothing
					}
					
					break;
				
				// target challenges Captain
				case 'c':
					win = false;
					
					// user had Captain
					if (win) {
						// target loses influence
						target.removeInf();
						
						// user swap Captain for new card
						user.swap("CAPTAIN", deck);
						
						execute = true;
					}
					
					// user did not have Captain
					else {
						// user loses influence
						user.removeInf();
					}
					
					break;
					
				// target takes it
				case 'n':
					execute = true;
					break;
				}
				
				if (execute) {
					// remove 2 gold from target
					target.addCoins(-2);
					
					// add 2 gold to user
					user.addCoins(2);
				}
			}
			
			// Coup
			else if (input.equalsIgnoreCase(RVI.elementAt(6)) || user.getCoins() > 10) {
				
				if (dbg) {
					System.out.println("Coup");
				}
				
				System.out.println(user.getName() + " uses Coup.");
				
				// check for adequate coins
				if (user.getCoins() < 7) {
					System.out.println("You cannot Coup. You must have at least 7 coins to Coup. Please try again.\n");
					redo = true;
				}
				else {
					user.addCoins(-7);
					target.removeInf();
				}
			}
			
			else if (input.equalsIgnoreCase("quit")) {
				break;
			}
			
			// TODO: should never get here
			else {
				System.out.println(GEM);
				redo = true;
			}
			
			// turn switch stuff
			if (!redo) {
				turnCount++;
			}
			
			// check if either player is out
			if (user.getInf() == 0 || target.getInf() == 0) {
				over = true;
			}
		}
		
		String winner = user.getName();
		
		if (user.getInf() == 0) {
			winner = target.getName();
		}
		
		System.out.println("Game has ended, and " + winner + " is the victor.");
		
		System.out.print("Quitting...");
		
		return;
	}
	
	/**
	 * <p>Initializes players for playing a game.</p>
	 * 
	 * <p>Will eventually also allow for starting a new game, and changing settings between games.</p>
	 * 
	 * @param initScanner Scanner for getting input
	 */
	public Coup(Scanner initScanner) {
		// welcome
		System.out.println("Welcome to Coup!\n");
		
		// ignore me
		boolean auto = false;
		
		// valid input
		Vector<String> vi = new Vector<String>();
		
		// input strings and chars
		String input, p1n = "P1", p2n = "P2";
		char p1t = 'h', p2t = 'c';
		
		// prompt for p1 type
		System.out.println("Please select a type for Player 1: ");
		System.out.println("\t1. Human");
		System.out.println("\t2. AI");
		
		// valid input
		vi.add("1");
		vi.add("2");
		vi.add("auto"); // for making it easy for you
		
		// get input
		input = getInput(vi, initScanner);
		
		// process input
		if (input.equalsIgnoreCase("quit")) {
			return;
		}
		else if (input.equalsIgnoreCase("auto")) {
			auto = true;
		}
		else if (input.equalsIgnoreCase("1")) {
			p1t = 'h';
		}
		else {
			p1t = 'c';
		}
		
		if (!auto) {
			// prompt for p2 type
			System.out.println("Please select a type for Player 2: ");
			System.out.println("\t1. Human");
			System.out.println("\t2. AI");
			
			input = getInput(vi, initScanner);
			
			if (input.equalsIgnoreCase("quit")) {
				return;
			}
			else if (input.equalsIgnoreCase("1")) {
				p2t = 'h';
			}
			else {
				p2t = 'c';
			}
			
			vi.clear();
			
			System.out.println("Please enter a name for Player 1: ");
			
			p1n = initScanner.nextLine();
			
			System.out.println("Please enter a name for Player 2: ");
			
			p2n = initScanner.nextLine();
		}
		
		p1 = new Player(p1t, p1n);
		p2 = new Player(p2t, p2n);
		
		deck.deal(p1);
		deck.deal(p2);
		
		System.out.println("\nAll done! Type \"start\" to begin a game, or type \"help\" for the help menu.");
		
		vi.add("start");
		
		input = getInput(vi, initScanner);
		
		return;
	}
	
	public static void main(String[] args) {
		if (dbg) {
			System.out.println("*DEBUG* Program started.\n");
		}
		
		Scanner s = new Scanner (System.in);
		
		if (dbg) {
			System.out.println("*DEBUG* Scanner created.\n");
		}
		
		if (dbg) {
			System.out.println("*DEBUG* Starting Initialization.\n");
		}
		
		Coup game = new Coup(s);
		
		if (dbg) {
			System.out.println("\n\n*DEBUG* Initialization complete. Starting game.\n");
		}
		
		game.Turn(s);
		
		if (dbg) {
			System.out.println("*DEBUG* Game ended. Closing scanner and exiting.\n");
		}
		
		s.close();
		
		System.out.println(" bye!");
		
		return;
	}
}
