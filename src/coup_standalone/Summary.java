package coup_standalone;

import java.util.*;
//import java.io.*;

/**
 * <p>The Summary class serves as a place for statistical information to be kept
 * 		during the course of a game.</p>
 * 
 * <p>This class contains full lists of plays made by each player, as well as an aggregated
 * 		list (all stored as vectors of strings) for the entire game. It also contains a count of
 * 		how many times each player has used any given move, as well as a sum for both players.</p>
 * 
 * @author Simon Gladstone
 * 
 */
public class Summary {

	// Gotta keep track of who's who
	String p1name, p2name;
	
	/* 
	 * Dictionaries of various counters and stats for each player
	 * 
	 * Index (quotation marks to indicate stored as String):
	 * 	
	 * "TURN COUNT"		= Count of turns
	 * "INCOME"			= Times used Income
	 * "COUP"			= Times used Coup
	 * "FOREIGN AID"	= Times used Foreign Aid
	 * "DUKE"			= Times used Duke
	 * "AMBASSADOR"		= Times used Ambassador
	 * "CAPTAIN"		= Times used Captain
	 * "ASSASSIN"		= Times used Assassin
	 * "CONTESSA"		= Times used Contessa
	 * "F.A. BLOCKS"	= Times blocked Foreign Aid
	 * "DUKE CHLGS"		= Times challenged Duke
	 * "AMBDR CHLGS"	= Times challenged Ambassador
	 * "CPTN BLOCKS"	= Times blocked Captain
	 * "CPTN CHLGS"		= Times challenged Captain
	 * "ASS BLOCKS"		= Times blocked Assassin
	 * "ASS CHLGS"		= Times challenged Assassin
	 * "TESSA CHLGS"	= Times challenged Contessa
	 * "2CARDS CHLGS"	= Times challenged Ambassador/Captain simultaneously
	 * 
	 */
	
	// Indices
	private Vector<String> keys = new Vector<String>();
	
	// Maps
	private Map<String, Integer> p1counts, p2counts, gamecounts;
	
	
	/* CONSTRUCTORS/DESTRUCTORS/OTHER SIMILAR */
	
	/**
	 * 
	 */
	public Summary() {
		// TODO Auto-generated constructor stub
		
		// create the hashmaps
		p1counts = new HashMap<String, Integer>();
		p2counts = new HashMap<String, Integer>();
		gamecounts = new HashMap<String, Integer>();
		
		// set up keys for maps
		this.setKeys();
		
		// populate the hashmaps
		this.HashMapDefaultAll();
	}
	
	/**
	 * 
	 * @param p1
	 * @param p2
	 */
	public Summary(Player p1, Player p2) {
		// set names
		p1name = p1.getName();
		p2name = p2.getName();
		
		// create the stat hashmaps
		p1counts = new HashMap<String, Integer>();
		p2counts = new HashMap<String, Integer>();
		gamecounts = new HashMap<String, Integer>();
		
		// set up keys for maps
		this.setKeys();
		
		// populate the hashmaps
		this.HashMapDefaultAll();
	}
	
	/* SETTERS */
	public void setKeys() {
		keys.addElement("TURN COUNT");
		keys.addElement("INCOME");
		keys.addElement("COUP");
		keys.addElement("FOREIGN AID");
		keys.addElement("DUKE");
		keys.addElement("AMBASSADOR");
		keys.addElement("CAPTAIN");
		keys.addElement("ASSASSIN");
		keys.addElement("CONTESSA");
		keys.addElement("F.A. BLOCKS");
		keys.addElement("DUKE CHLGS");
		keys.addElement("AMBDR CHLGS");
		keys.addElement("CPTN BLOCKS");
		keys.addElement("CPTN CHLGS");
		keys.addElement("ASS BLOCKS");
		keys.addElement("ASS CHLGS");
		keys.addElement("TESSA CHLGS");
		keys.addElement("2CARDS CHLGS");
	}
	
	public void IncPlayerStat(String s, Player p) throws IllegalArgumentException {
		
		// convert input to upper case because UI is case-insensitive but stats are not
		s = s.toUpperCase();
		
		// check for valid move name
		if (!this.getKeys().contains(s)) {
			throw new IllegalArgumentException("Invalid stat name: " + s);
		}
		
		// get the map
		Map<String, Integer> m = getMap(p);
		
		// get the old value
		int oldvalue = m.get(s);
		
		// replace it with increment
		m.replace(s, oldvalue + 1);
	}
	
	public void IncGameStat(String s) throws IllegalArgumentException {
		if (!this.getKeys().contains(s)) {
			throw new IllegalArgumentException("Invalid stat name: " + s);
		}
		
		int oldvalue = gamecounts.get(s);
		
		gamecounts.replace(s, oldvalue + 1);
	}
	
	/**
	 * <p>THIS FUNCTION MUST BE CALLED <b>AFTER</b> {@link #setKeys()}</p>
	 */
	public void HashMapDefaultAll() {
		this.AllHashMapsSet0(this.getKeys());
	}
	
	/**
	 * 
	 * @param v
	 */
	public void AllHashMapsSet0(Vector<String> v) {
		// p1counts
		for (int i = 0; i < v.size(); i++) {
			p1counts.put(v.elementAt(i), 0);
		}
		
		// p1counts
		for (int i = 0; i < v.size(); i++) {
			p2counts.put(v.elementAt(i), 0);
		}
		
		// p1counts
		for (int i = 0; i < v.size(); i++) {
			gamecounts.put(v.elementAt(i), 0);
		}
	}

	
	/* GETTERS */
	public int getPlayerStat(String s, Player p) throws IllegalArgumentException {
		// check for valid move name
		if (!this.getKeys().contains(s)) {
			throw new IllegalArgumentException("Invalid stat name: " + s);
		}
		
		// get the map
		Map<String, Integer> m = getMap(p);
		
		return m.get(s);
	}
	
	public int getGameStat(String s) throws IllegalArgumentException {
		// check for valid move name
		if (!this.getKeys().contains(s)) {
			throw new IllegalArgumentException("Invalid stat name: " + s);
		}
		
		return gamecounts.get(s);
	}
	
	public Vector<String> getKeys() {
		return keys;
	}
	
	public String getPlayerName(int n) throws IllegalArgumentException {
		switch(n) {
		case 1:
			return this.getP1Name();
			
		case 2:
			return this.getP2Name();
			
		default:
			throw new IllegalArgumentException("Player Number out of bounds.");
		
		}
	}
	
	public String getP1Name() {
		return p1name;
	}
	
	public String getP2Name() {
		return p2name;
	}
	
	public Map<String, Integer> getGameCounts() {
		return gamecounts;
	}
	
	public Map<String, Integer> getMap(Player p) throws IllegalArgumentException {
		Map <String, Integer> rc;
		
		if (!(p.getName().equalsIgnoreCase(this.getP1Name()) || p.getName().equalsIgnoreCase(this.getP2Name()))) {
			throw new IllegalArgumentException("Matching counts not found.");
		}
		
		if (p.getName().equalsIgnoreCase(this.getP1Name())) {
			rc = p1counts;
		}
		else {
			rc = p2counts;
		}
		
		return rc;
	}
	
	/* MISC */
	// returns a string with x tabs; used for printFullStats(Player, Player)
	public String spacer(int x) {
		String rc = "";
		
		for (int i = 0; i < x; i++) {
			rc = rc + " ";
		}
		
		return rc;
	}
	
	public void printFullStats(Player p1, Player p2) {
		int nspc = 0;
		String currentStat = null;
		
		// spacer
		System.out.println();
		
		// Top equals signs
		System.out.println("=========================================");
		
		// Headers
		System.out.println("|\t\t|\t|\t|\t|");
		System.out.println("|  STAT\t\t|  Game\t|  P1\t|  P2\t|");
		System.out.println("|\t\t|\t|\t|\t|");
		
		// each row gets printed here
		for (int i = 0; i < this.getKeys().size(); i++) {
			currentStat = this.getKeys().elementAt(i);
			
			// # of tabs to make column 1 uniform
			nspc = 16 - 3 - currentStat.length();
			
			// print column 1
			System.out.print("|  " + currentStat + spacer(nspc) + "|");
			
			// column 2
			System.out.print("  " + this.getGameStat(currentStat) + "\t|");
			
			// column 3
			System.out.print("  " + this.getPlayerStat(currentStat, p1) + "\t|");
						
			// column 4
			System.out.print("  " + this.getPlayerStat(currentStat, p2) + "\t|");
			
			// new line
			System.out.println();
		}
		
		// Bottom equals signs
		System.out.println("=========================================");
	}

	public void consolidateStats(Player p1, Player p2) {
		String currentStat = null;
		
		int p1stat = 0, p2stat = 0;
		
		for (int i = 0; i < this.getKeys().size(); i++) {
			currentStat = this.getKeys().elementAt(i);
			p1stat = this.getPlayerStat(currentStat, p1);
			p2stat = this.getPlayerStat(currentStat, p2);
			
			if (!currentStat.equals("TURN COUNT")) {
				gamecounts.replace(currentStat, p1stat + p2stat);
			}
		}
		
		p1counts.replace("TURN COUNT", this.getGameStat("TURN COUNT"));
		p2counts.replace("TURN COUNT", this.getGameStat("TURN COUNT"));
	}
}
