package coup_standalone;

import java.util.Random;
import java.util.Vector;

/**
 * <p>The Deck class exists to manage the cards in the deck. It provides functions for getting new cards, shuffling the deck, etc.</p>
 * 
 * @author Simon Gladstone
 *
 */
public class Deck {
	
	// The deck. What did you think?
	Vector<String> stack = new Vector<String>();
	
	// Number of cards currently in the deck
	int size;
	
	/* CONSTRUCTORS */
	/**
	 * <p>The Default Deck constructor adds 3 copies of each of 5 cards.
	 */
	public Deck() {
		// cards that get put in the deck
		String[] cards = new String[5];
		cards[0] = "ASSASSIN";
		cards[1] = "CONTESSA";
		cards[2] = "DUKE";
		cards[3] = "AMBASSADOR";
		cards[4] = "CAPTAIN";
		
		// put 3 copies of each card in the deck
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < 3; j++) {
				stack.addElement(cards[i]);
			}
		}
		
		size = 15;
		
		// shuffle the deck
		this.shuffle(10);
		
	}
	
	/* SETTERS */
	/**
	 * <p>This function adds the provided card to the deck, providing it is valid.</p>
	 * 
	 * @param s	The card to add to the deck
	 * @throws IllegalArgumentException	If the provided card is invalid.
	 */
	public void add(String s) throws IllegalArgumentException {
		// check to make sure the card is valid
		Vector<String> vc = new Vector<String>();
		
		vc.addElement("DUKE");
		vc.addElement("CONTESSA");
		vc.addElement("ASSASSIN");
		vc.addElement("AMBASSADOR");
		vc.addElement("CAPTAIN");
		
		if (!vc.contains(s)) {
			throw new IllegalArgumentException("Invalid card name");
		}
		
		vc.clear();
		
		// now add to deck
		stack.addElement(s);
		
		// increment size
		size++;
	}
	
	/* GETTERS */
	/**
	 * <p>This function returns the entire deck in vector form.</p>
	 * 
	 * @return	Vector of Strings containing the entire deck
	 */
	public Vector<String> get() {
		return stack;
	}
	
	/**
	 * <p>This function removes a card from the top of the deck, and returns it.</p>
	 * 
	 * <p>To get 2 new cards, use <code>pop2()</code>.
	 * 
	 * @return	The top card from the deck, as a String.
	 * 
	 * @see #pop2()
	 */
	public String pop() {
		String rc = stack.firstElement();
		stack.removeElementAt(0);
		size--;
		return rc;
	}

	/**
	 * <p>This function removes the top 2 cards from the deck and returns them in an array.</p>
	 * 
	 * <p>To get only 1 card, use <code>pop()</code>.
	 * 
	 * @return A String Array of size 2 containing the top 2 cards of the deck
	 * 
	 * @see #pop()
	 */
	public String[] pop2() {
		String[] rc = new String[2];
		rc[0] = this.pop();
		rc[1] = this.pop();
		size -= 2;
		return rc;
	}
	
	/* MISC */
	
	/**
	 * <p>This function does one full inside out Fisher-Yates shuffle of the entire deck.</p>
	 * 
	 * <p>To do more than one full shuffle (which you may want to do, because random number generators are sometimes not the best),
	 * 		use <code>shuffle(int)</code>.</p>
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_.22inside-out.22_algorithm">Inside Out Fisher-Yates Shuffle</a>
	 * @see #shuffle(int)
	 */
	public void shuffle() {
		// create copy of the current deck
		String[] td = stack.toArray(new String[stack.size()]);
		
		// clear current deck
		stack.clear();
		
		// used indices
		Vector<Integer> ui = new Vector<Integer>();
		
		Random rng = new Random();
		int r1;
		
		while (true) {
			// get an unused card
			r1 = rng.nextInt(td.length);
			while (ui.contains(r1)) {
				r1 = rng.nextInt(td.length);
			}
			
			// put card on deck
			stack.add(td[r1]);
			
			// take it out of old deck
			td[r1] = null;
			
			// add index of used card to pile
			ui.add(r1);
			
			// stop once the shuffle is done
			if (stack.size() == td.length) {
				break;
			}
		}
		
		ui.clear();
	}

	/**
	 * <p>This function does the provided number of inside out Fisher-Yates shuffles of the entire deck.</p>
	 * 
	 * <p>To do only one shuffle, use <code>shuffle()</code>.</p>
	 * 
	 * @param n The number of times to shuffle the deck
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_.22inside-out.22_algorithm">Inside Out Fisher-Yates Shuffle</a>
	 * @see #shuffle()
	 */
	public void shuffle(int n) {
		for (int i = 0; i < n; i++) {
			this.shuffle();
		}
	}

	/**
	 * <p>This function deals a full hand (2 cards) to the provided player.</p>
	 * 
	 * <p>Uses the <code>pop2()</code> function.</p>
	 * 
	 * @param p The player to whom to deal cards
	 * 
	 * @see #pop2()
	 */
	public void deal(Player p) {
		String[] h = new String[2];
		h = this.pop2();
		p.setHand(h);
	}
}
