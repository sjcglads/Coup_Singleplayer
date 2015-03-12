package coup_standalone;

import java.util.Vector;
import java.io.*;

/**
 * <p>The Summary class serves as a place for statistical information to be kept
 * 		during the course of a game.</p>
 * 
 * <p>This class contains full lists of plays made by each player, as well as an aggregated
 * 		list (all stored as vectors of strings) for the entire game. It also contains a count of
 * 		how many times each player has used any given move, as well as a sum for both players.</p>
 * 
 * <p>Also contained in this class are the functions used to write to the debug and regular game logs.</p>
 * 
 * @author Simon Gladstone
 * 
 */
public class Summary {

	// Vectors of all the plays made by each player
	Vector<String> plays, p1plays, p2plays;
	
	// Array to keep track of number of times a used a move
	//		0 = income
	//		1 = foreign aid
	//		2 = coup
	//		3 = duke
	//		4 = assassin
	//		5 = captain
	//		6 = ambassador
	//		7 = contessa
	int[] cards, p1cards, p2cards;
	
	/* CONSTRUCTORS/DESTRUCTORS/OTHER SIMILAR */
	public Summary() {
		// TODO Auto-generated constructor stub
	}
	
	public Summary(Player p1, Player p2) {
		// TODO 
	}
	
	/* SETTERS */
	
	/* GETTERS */
	
	/* MISC */

}
