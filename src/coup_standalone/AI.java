package coup_standalone;

public class AI extends Player{
	public class History{
		final int SIZE = 10;
		String[] cards = new String[SIZE];
		int index;
		History(){
			index = 0;
			for (int i = 0; i < SIZE; i++) cards[i] = "";
		}
		public void clear(){
			index = 0;
		}
		public void add(String card){
			cards[index] = card;
			index++;
		}
		public void remove(String card){
			for (int i = 0; i < index; i ++){
				if (cards[i].equals(card)) cards[i] = "";
			}
		}
		public boolean lyingCallChain(String card){
			boolean found = false;
			int difference = 0;
			String found_card = "";
			for (int i = 0; i < index; i++){
				if (card.equals(cards[i])){
					if (found){
						if (difference == 0) return false;
						else return true;
					} else found = true;
				} else if (found && found_card.equals("")) found_card = cards[i];
				else if (found && !cards[i].equals("2CARDS")) difference++;
			}
			return false;
		}
		public boolean contains(String card){
			for (int i = 0; i < index; i++) if (card.equals(cards[i])) return true;
			return false;
		}
		public boolean contains2(String card){
			boolean containsOne = false;
			for (int i = 0; i < index; i ++){
				if (card.equals(cards[i])){
					if (containsOne) return true;
					else containsOne = true;
				}
			}
			return false;
		}
	}
	
	public class Information{
		String card1;
		String card2;
		int influence;
		int coins;
		Information(int influence, int coins){
			this.card1 = "";
			this.card2 = "";
			this.influence = influence;
			this.coins = coins;
		}
		Information(String card1, String card2, int influence, int coins){
			this.card1 = card1;
			this.card2 = card2;
			this.influence = influence;
			this.coins = coins;
		}
		public boolean contains(String card){
			return card1.equals(card) || card2.equals(card);
		}
		public boolean combination(String card1, String card2){
			return (this.card1.equals(card1) && this.card2.equals(card2)) || (this.card1.equals(card2) && this.card2.equals(card1));
		}
		public void addCoins(int coin){
			coins += coin;
		}
		public void subCoins(int coin){
			coins -= coin;
		}
		public void swap(String newCard, String oldCard){
			if (card1.equals(oldCard)) card1 = newCard;
			else card2 = newCard;
		}
	}
	
	public class Moves{
		final int possibleMoves = 7;
		int[] utility = new int[possibleMoves];
		String[] moves = new String[possibleMoves];
		Moves(){
			moves[0] = COUP;
			moves[1] = ASSASSIN;
			moves[2] = CAPTAIN;
			moves[3] = DUKE;
			moves[4] = AMBASSADOR;
			moves[5] = FOREIGN_AID;
			moves[6] = INCOME;
			utility[0] = 100;
			utility[1] = 100;
			utility[2] = 90;
			utility[3] = 90;
			utility[4] = 80;
			utility[5] = 80;
			utility[6] = 70;
		}
		public void updateUtilityWithFacts(){
			String position = AIcalculatePosition();
			if (!myInfo.contains(ASSASSIN)){
				if (position.equals("ahead") || position.equals("slightly ahead") || position.equals("even")) utility[1] -= 20;
				else if (position.equals("slightly behind")) utility[1] -= 10;
			}
			else  if (!myInfo.contains(CAPTAIN) && !cardsIMentioned.contains(CAPTAIN)){
				if (position.equals("ahead") || position.equals("slightly ahead") || position.equals("even")) utility[2] -= 20;
				else if (position.equals("slightly behind")) utility[2] -= 10;
			}
			else if (!myInfo.contains(DUKE) && !cardsIMentioned.contains(DUKE)){
				if (position.equals("ahead") || position.equals("slightly ahead") || position.equals("even")) utility[3] -= 20;
				else if (position.equals("slightly behind")) utility[3] -= 10;
			}
			else if (!myInfo.contains(AMBASSADOR) && turnsSinceAmbassador > 1){
				if (position.equals("ahead") || position.equals("slightly ahead") || position.equals("even")) utility[4] -= 20;
				else if (position.equals("slightly behind")) utility[4] -= 10;
			}
		}
		public void updateUtilityWithHistory(){
			if (cardTheyRevealed.equals(CONTESSA) || cardsNotHave.contains(ASSASSIN) || (cardsMentioned.contains(CONTESSA) && !cardsMentioned.lyingCallChain(CONTESSA))) utility[1] = 0;
			else if (cardTheyRevealed.equals(CAPTAIN) || cardsNotHave.contains(CAPTAIN) ||(cardsMentioned.contains(CAPTAIN) && !cardsMentioned.lyingCallChain(CAPTAIN))) utility[2] = 0;
			else if (cardTheyRevealed.equals(AMBASSADOR) || cardsNotHave.contains(AMBASSADOR) || (cardsMentioned.contains(AMBASSADOR) && !cardsMentioned.lyingCallChain(AMBASSADOR))) utility[2] = 0;
			else if (cardsMentioned.contains("2CARDS") && !cardsMentioned.lyingCallChain("2CARDS")) utility[2] = 0;
			else if (cardTheyRevealed.equals(DUKE) || (cardsMentioned.contains(DUKE) && !cardsMentioned.lyingCallChain(DUKE))) utility[5] = 0;
			else if (cardTheyRevealed.equals(ASSASSIN) && !myInfo.contains(CONTESSA)) utility[4] += 10;
			else if (cardsNotHave.contains(DUKE)) utility[3] = 0;
		}
		public void updateUtilityWithCoins(){
			if (myInfo.coins < 7) utility[0] = 0;
			if (myInfo.coins < 3) utility[1] = 0;
		}
		public String firstMove(){
			if (myInfo.combination(DUKE, DUKE)) return AMBASSADOR;
			else if (myInfo.combination(DUKE,ASSASSIN)){
				if (cardsMentioned.contains(DUKE)) return DUKE;
				else return FOREIGN_AID;
			}
			else if (myInfo.combination(DUKE,AMBASSADOR)) return CAPTAIN;
			else if (myInfo.combination(DUKE, CAPTAIN)) return CAPTAIN;
			else if (myInfo.combination(DUKE, CONTESSA)){
				if (cardsMentioned.contains(DUKE)) return CAPTAIN;
				else return FOREIGN_AID;
			}
			else if (myInfo.combination(ASSASSIN, ASSASSIN)) return DUKE;
			else if (myInfo.combination(ASSASSIN, AMBASSADOR)) return DUKE;
			else if (myInfo.combination(ASSASSIN, CAPTAIN)){
				if (cardsMentioned.contains(DUKE)) return CAPTAIN;
				else return DUKE;
			}
			else if (myInfo.combination(ASSASSIN,CONTESSA)) return DUKE;
			else if (myInfo.combination(AMBASSADOR, AMBASSADOR)) return AMBASSADOR;
			else if (myInfo.combination(AMBASSADOR, CAPTAIN)){
				if (cardsMentioned.contains(DUKE)) return CAPTAIN;
				else return DUKE;
			}
			else if (myInfo.combination(AMBASSADOR, CONTESSA)) return DUKE;
			else if (myInfo.combination(CAPTAIN, CAPTAIN)){
				if (cardsMentioned.contains(DUKE)) return CAPTAIN;
				else return DUKE;
			}
			else if (myInfo.combination(CAPTAIN, CONTESSA)) return DUKE;
			else if (myInfo.combination(CONTESSA, CONTESSA)) return AMBASSADOR;
			else return INCOME;
		}
		public String getMove(){
			int max = 0;
			String move = INCOME;
			for (int i = 0; i < possibleMoves; i ++){
				if (max < utility[i]) max = utility[i];
			}
			for (int i = 0; i < possibleMoves; i ++){
				if (max == utility[i]) return moves[i];
			}
			return move;
		}
	}
	
	// Possible Cards
	String COUP = "COUP";
	String CAPTAIN = "CAPTAIN";
	String ASSASSIN = "ASSASSIN";
	String CONTESSA = "CONTESSA";
	String DUKE = "DUKE";
	String AMBASSADOR = "AMBASSADOR";
	String FOREIGN_AID = "FOREIGN AID";
	String INCOME = "INCOME";
	// History Variables
	History cardsMentioned;
	History cardsSeen;
	String cardIRevealed;
	String cardTheyRevealed;
	History cardsNotHave;
	History cardsIMentioned;
	// Information Variables
	Information myInfo;
	Information opponentInfo;
	// Game Variables
	int turn;
	int turnsSinceAmbassador;
	
	AI(){
		cardsMentioned = new History();
		cardsSeen = new History();
		cardIRevealed = "";
		cardTheyRevealed = "";
		cardsNotHave = new History();
		cardsIMentioned = new History();
		opponentInfo = new Information(coins, influence);
		turn = 1;
		turnsSinceAmbassador = -1; 
		type = 'c';
	}
	
	AI(String s) {
		cardsMentioned = new History();
		cardsSeen = new History();
		cardIRevealed = "";
		cardTheyRevealed = "";
		cardsNotHave = new History();
		cardsIMentioned = new History();
		opponentInfo = new Information(coins, influence);
		turn = 1;
		turnsSinceAmbassador = -1;
		name = s;
		type = 'c';
	}
	
	public void AIaddHandInfo(String card1, String card2){
		myInfo = new Information(card1, card2, coins, influence);
		cardsSeen.add(card1);
		cardsSeen.add(card2);
	}
	public String AIcalculatePosition(){
		if (myInfo.influence < opponentInfo.influence && myInfo.coins <= opponentInfo.coins + 4) return "behind";
		else if (myInfo.influence > opponentInfo.influence && myInfo.coins >= opponentInfo.coins + 4) return "ahead";
		else if (myInfo.influence < opponentInfo.influence) return "slightly behind";
		else if (myInfo.influence > opponentInfo.influence) return "slightly ahead";
		else if (myInfo.coins + 2 <= opponentInfo.coins) return "slightly behind";
		else if (myInfo.coins + 2 >= opponentInfo.coins) return "slightly ahead";
		else if (myInfo.coins + 4 <= opponentInfo.coins) return "behind";
		else if (myInfo.coins + 4 >= opponentInfo.coins) return "ahead";
		else return "even";
	}
	public boolean AIchallenge(String card){
		if (card.equals("2CARDS")){
			cardsMentioned.add("2CARDS");
			if (cardsSeen.contains2(CAPTAIN) && !cardTheyRevealed.equals(CAPTAIN) && cardsSeen.contains2(AMBASSADOR) && !cardTheyRevealed.equals(AMBASSADOR)) return true;
		} else if (card.equals(DUKE)){
			cardsMentioned.add(DUKE);
			opponentInfo.addCoins(3);
		} else if (card.equals(ASSASSIN)){
			cardsMentioned.add(ASSASSIN);
			opponentInfo.subCoins(3);
			if (myInfo.contains(CONTESSA)) return true;
		} else if (card.equals(CONTESSA)){
			cardsMentioned.add(CONTESSA);
		} else if (card.equals(CAPTAIN)){
			cardsMentioned.add(CAPTAIN);
			opponentInfo.addCoins(2);
			myInfo.subCoins(2);
			if (!cardTheyRevealed.equals(CAPTAIN) && (cardsSeen.contains2(CAPTAIN) || cardsSeen.contains2(AMBASSADOR) || (cardsSeen.contains(CAPTAIN) && cardsSeen.contains(AMBASSADOR)))) return true;
		} else if (card.equals(AMBASSADOR)){
			if (cardsSeen.contains2(card) && !cardTheyRevealed.equals(card)) return true;
			if (cardsMentioned.lyingCallChain(card)) return true;
			cardsMentioned.clear();
		}
		if (cardsSeen.contains2(card) && !cardTheyRevealed.equals(card)) return true;
		if (cardsMentioned.lyingCallChain(card)) return true;
		return false;
	}
	public void AIchallengeResult(String card, boolean won){
		if (won){
			if (card.equals(DUKE)) opponentInfo.subCoins(3);
			else if (card.equals(CAPTAIN)){
				opponentInfo.subCoins(2);
				myInfo.addCoins(2);
			}
		} else{
			if (card.equals(AMBASSADOR)) cardsMentioned.clear();
		}
	}
	public void AIrevealCard(String card){
		opponentInfo.influence --;
		cardTheyRevealed = card;
	}
	public String AIcardToReveal(){
		myInfo.influence --;
		String result = null;
		if (!cardTheyRevealed.equals("")){
			if (cardTheyRevealed.equals(CAPTAIN) || cardTheyRevealed.equals(AMBASSADOR)){
				if (myInfo.card1.equals(CAPTAIN)) result = myInfo.card1;
				else if (myInfo.card2.equals(CAPTAIN)) result = myInfo.card2;
				else if (myInfo.card1.equals(AMBASSADOR)) result = myInfo.card1;
				else if (myInfo.card2.equals(AMBASSADOR)) result = myInfo.card2;
			} else if (cardTheyRevealed.equals(CONTESSA)){
				if (myInfo.card1.equals(ASSASSIN)) result = myInfo.card1;
				else if (myInfo.card2.equals(ASSASSIN)) result = myInfo.card2;
			}
		} else{
			if (cardsIMentioned.contains(myInfo.card1) && !cardsIMentioned.contains(myInfo.card2)) result = myInfo.card1;
			else if (cardsIMentioned.contains(myInfo.card2) && !cardsIMentioned.contains(myInfo.card1)) result = myInfo.card2;
			else{
				if (myInfo.contains(DUKE)) result = DUKE;
				else if (myInfo.contains(ASSASSIN) && cardsIMentioned.contains(ASSASSIN)) result = ASSASSIN;
				else if (myInfo.contains(AMBASSADOR)) result = AMBASSADOR;
				else if (myInfo.contains(CAPTAIN) && cardsIMentioned.contains(CAPTAIN)) result = CAPTAIN;
				else if (myInfo.contains(CONTESSA)) result = CONTESSA;
				else if (myInfo.contains(ASSASSIN)) result = ASSASSIN;
			}
		}
		cardIRevealed = result;
		cardsIMentioned.remove(result);
		return result;
	}
	public boolean AIblock(String card){
		String position = AIcalculatePosition();
		if (card.equals(ASSASSIN)){
			if (myInfo.contains(CONTESSA)) return true;
			else if (position.equals("behind") || position.equals("slightly behind")) return true;
		} else if (card.equals(CAPTAIN)){
			if (turn < 3 || myInfo.contains(CAPTAIN) || myInfo.contains(AMBASSADOR)) return true;
			else if (position.equals("behind")) return true;
		} else if (card.equals(FOREIGN_AID)){
			if (myInfo.contains(DUKE)) return true;
			cardsMentioned.add(FOREIGN_AID);
			opponentInfo.addCoins(2);
		}
		return false;
	}
	public char AIdecision(String card){
		if (AIblock(card)) return 'b';
		else if (AIchallenge(card)) return 'c';
		else return 'n';
	}
	public void AIdoesMove(String card){
		if (card.equals(COUP)) myInfo.subCoins(7);
		else if (card.equals(ASSASSIN)){
			cardsIMentioned.add(ASSASSIN);
			myInfo.subCoins(3);
		} else if (card.equals(CAPTAIN)){
			cardsIMentioned.add(CAPTAIN);
			myInfo.addCoins(2);
			opponentInfo.subCoins(2);
		} else if (card.equals(DUKE)){
			cardsIMentioned.add(DUKE);
			myInfo.addCoins(3);
		} else if (card.equals(FOREIGN_AID)){
			cardsIMentioned.add(FOREIGN_AID);
			myInfo.addCoins(2);
		} else if (card.equals(INCOME)) myInfo.addCoins(1);
	}
	public String AImove(){
		Moves nextMove = new Moves();
		nextMove.updateUtilityWithFacts();
		nextMove.updateUtilityWithHistory();
		nextMove.updateUtilityWithCoins();
		String move;
		if (turn == 1) move = nextMove.firstMove();
		else move = nextMove.getMove();
		turn ++;
		turnsSinceAmbassador ++;
		return move;
	}
	public void AIgotChallenged(String card, boolean won){
		if (won){
			if (card.equals(CONTESSA)) opponentInfo.subCoins(3);
		} else{
			if (card.equals("2CARDS")){
				cardsMentioned.add(CAPTAIN);
				myInfo.subCoins(2);
				opponentInfo.addCoins(2);
			} else if (card.equals(CAPTAIN)) cardsNotHave.add(CAPTAIN);
			else if (card.equals(DUKE)) cardsNotHave.add(DUKE);
			else if (card.equals(AMBASSADOR)) cardsNotHave.add(AMBASSADOR);
			else if (card.equals(ASSASSIN)) cardsNotHave.add(ASSASSIN);
		}
	}
	public void AInewCard(String newCard, String oldCard){
		myInfo.swap(newCard,oldCard);
	}
	public String[] AIambassador(String card1, String card2){
		String[] result = {"", "", "", ""};
		int cardsToPick = myInfo.influence;
		cardsSeen.add(card1);
		cardsSeen.add(card2);
		int insert = 0;
		turnsSinceAmbassador = 0;
		while (cardsToPick > 0){
			if (cardsMentioned.contains(ASSASSIN) && !myInfo.contains(CONTESSA)){
				if (card1.equals(CONTESSA))	result[insert] = card1;
				else if (card2.equals(CONTESSA)) result[insert] = card2;
			} else if (cardsMentioned.contains(CAPTAIN) && !myInfo.contains(AMBASSADOR)){
				if (card1.equals(AMBASSADOR)) result[insert] = card1;
				else if (card2.equals(AMBASSADOR)) result[insert] = card2;
			} else if (cardsMentioned.contains(FOREIGN_AID) && !myInfo.contains(DUKE)){
				if (card1.equals(DUKE))	result[insert] = card1;
				else if (card2.equals(DUKE)) result[insert] = card2;
			} else if (cardsSeen.contains2(CONTESSA) && !myInfo.contains(ASSASSIN) && !cardTheyRevealed.equals(CONTESSA) && !cardsMentioned.contains(CONTESSA)){
				if (card1.equals(ASSASSIN))	result[insert] = card1;
				else if (card2.equals(ASSASSIN)) result[insert] = card2;
			} else if (myInfo.coins >= 4 && !myInfo.contains(DUKE)){
				if (card1.equals(DUKE))	result[insert] = card1;
				else if (card2.equals(DUKE)) result[insert] = card2;
			} else {
				if (!cardsMentioned.contains(CONTESSA) && !cardTheyRevealed.equals(CONTESSA) && !myInfo.contains(ASSASSIN)){
					if (card1.equals(ASSASSIN)) result[insert] = card1;
					else if (card2.equals(ASSASSIN)) result[insert] = card2;
				} else if (!cardsMentioned.contains(CAPTAIN) && !cardTheyRevealed.equals(CAPTAIN) && !cardsMentioned.contains(AMBASSADOR) && !cardTheyRevealed.equals(AMBASSADOR) && !myInfo.contains(CAPTAIN)){
					if (card1.equals(CAPTAIN)) result[insert] = card1;
					else if (card2.equals(CAPTAIN)) result[insert] = card2;
				} else if (!myInfo.contains(DUKE)){
					if (card1.equals(DUKE)) result[insert] = card1;
					else if (card2.equals(DUKE)) result[insert] = card2;
				}
			}
			insert ++;
			cardsToPick --;
		}
		if (insert == 2){
			result[2] = myInfo.card1;
			result[3] =myInfo.card2;
		} else{
			result[1] = cardIRevealed;
			if (result[0].equals(card1)){
				result[2] = myInfo.card1;
				result[3] = card2;
			} else if (result[0].equals(card2)){
				result[2] = myInfo.card1;
				result[3] = card1;
			} else{
				result[2] = card1;
				result[3] = card2;
			}
		}
		return result;
	}
}
