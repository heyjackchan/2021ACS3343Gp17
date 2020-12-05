package pokergame;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testHandleDiscardCards {

	@Test
	void testIsNumberWithInputIndexHasZero() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");	          
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    
	    GameControllerStub gcStub = new GameControllerStub(players);
	    String testInput = "1 0 3 5 7";
	    boolean actualResult = gcStub.handleDiscardCards(testInput);
	    boolean expectedResult = false;
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testIsNumberWithInputIndexLargerThanTwelve() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");	          
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    
	    GameControllerStub gcStub = new GameControllerStub(players);
	    String testInput = "1 23 3 5 7";
	    boolean actualResult = gcStub.handleDiscardCards(testInput);
	    boolean expectedResult = false;
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testIsNumberWithInputIndexIsAChar() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");	          
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    
	    GameControllerStub gcStub = new GameControllerStub(players);
	    String testInput = "1 2 3 E 7";
	    boolean actualResult = gcStub.handleDiscardCards(testInput);
	    boolean expectedResult = false;
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testIsNumberWithInputIndexHasDiscardedCard() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				Card card1 = new Card(5, 0);
				card1.setDiscarded(true);
				currentHand.addCard(card1);
				currentHand.addCard(new Card(5, 1));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    
	    GameControllerStub gcStub = new GameControllerStub(players);
	    String testInput = "1 2";
	    boolean actualResult = gcStub.handleDiscardCards(testInput);
	    boolean expectedResult = false;
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testParseIntWithInputIndexAreValid() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				currentHand.addCard(new Card(3, 0));
				currentHand.addCard(new Card(4, 1));
				currentHand.addCard(new Card(5, 2));
				currentHand.addCard(new Card(6, 1));
				currentHand.addCard(new Card(7, 0));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    
	    GameControllerStub gcStub = new GameControllerStub(players);
	    String testInput = "1 2 3 4 5";
	    boolean actualResult = gcStub.handleDiscardCards(testInput);
	    boolean expectedResult = true;
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testIsValidPlayWithInputIndexNotValid() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				currentHand.addCard(new Card(7, 0));
				currentHand.addCard(new Card(8, 1));
				currentHand.addCard(new Card(9, 2));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    
	    GameControllerStub gcStub = new GameControllerStub(players);
	    String testInput = "1 2 3";
	    boolean actualResult = gcStub.handleDiscardCards(testInput);
	    boolean expectedResult = false;
	    assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testIsValidPlayWithInputIndexAreValid() {
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(null, players, 0, 1, 1, 0, true);
				this.currentPlayer = players[this.currentPlayerIndex];
				this.nextPlayer = players[this.nextPlayerIndex()];
				this.currentPlayer.setName("Alice");
				this.nextPlayer.setName("Bob");
				Hand currentHand = currentPlayer.getHand();
				currentHand.addCard(new Card(9, 0));
				currentHand.addCard(new Card(9, 1));
				currentHand.addCard(new Card(9, 2));
			}
		}
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    
	    GameControllerStub gcStub = new GameControllerStub(players);
	    String testInput = "1 2 3";
	    boolean actualResult = gcStub.handleDiscardCards(testInput);
	    boolean expectedResult = true;
	    assertEquals(expectedResult, actualResult);
	}
}
