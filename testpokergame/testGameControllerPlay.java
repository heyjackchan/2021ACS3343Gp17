package testBig2;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import big2.AbstractPlayer;
import big2.Dealer;
import big2.GameController;
import big2.Hand;
import big2.History;
import big2.HumanPlayer;

import big2.Combination;
import big2.Card;
import big2.CardPile;

public class testGameControllerPlay {
public ByteArrayOutputStream outContent = new ByteArrayOutputStream();

@BeforeEach
void setUp() throws Exception {
	System.setOut(new PrintStream(outContent));
	
}

@AfterEach
void tearDown() throws Exception {
	System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
}

	@Test
	public void TestGameController_deal_printRemainingCard() {
		//System.out.println("Test1-testPrintRemainingCard");
		class GameControllerStub extends GameController {
			public GameControllerStub(AbstractPlayer[] players) {
				super(players, 3, 1, 1, 0, false);
			}
		}
		Dealer dealer = new Dealer();
		AbstractPlayer[] players = new AbstractPlayer[4];
	    for (int i = 0; i < 4; i++) {
	       players[i] = new HumanPlayer();
	    }
	    players[0].setName("Alice");
	    players[1].setName("Bob");
	    players[2].setName("Candy");
	    players[3].setName("David");
	    for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                dealer.deal(players[i].getHand());
            }
            //players[i].sort();
	    }
	    GameControllerStub gcStub = new GameControllerStub(players);
	    gcStub.printRemainingNumberOfCards();
	    String expectedResult = "Alice still have 13 card(s).\r\n" + 
	    		"Bob still have 13 card(s).\r\n" + 
	    		"Candy still have 13 card(s).";
	    assertEquals(expectedResult, outContent.toString().trim());
	}
	
	@Test
	public void TestGameController_historiesSizeIsZero_printLastHistory() {
		//System.out.println("Test2-testPrintLastHistoryIfSizeIsZero");
		class GameControllerStub extends GameController{
			public GameControllerStub() {
				super();
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		gcstub.printLastHistory();
		
		String expectedResult = "****************************************************\r\n" + 
				"*No History\r\n" + 
				"****************************************************";
	    assertEquals(expectedResult, outContent.toString().trim());
	}
	@Test
	public void TestGameController_historiesSizeMoreThanOneandEqualSKIP_printLastHistory() {
		//System.out.println("Test3-testPrintLastHistoryIfSizeIsNotZeroANDequalSKIP");
		class GameControllerStub extends GameController{
			public GameControllerStub() {
				super(null, 0, 1, 1, 0, true);
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		Combination combo = new Combination();
		
		
		Card card1 = new Card(3,1);
		combo.addCard(card1);
		Card card2 = new Card(3,2);
		combo.addCard(card2);
		gcStub.addHistory("Alice",combo);
		gcStub.addHistory("Bob",null);
		
		gcStub.printLastHistory();
		String expectedResult = "****************************************************\r\n" + 
				"*History of last rounds' discarded card:\r\n" + 
				"[Round 1] [Turn 1] [Alice]: C6, H6\r\n" + 
				"****************************************************";
	    assertEquals(expectedResult, outContent.toString().trim());
	}
	@Test
	public void  TestGameController_historiesSizeMoreThanOneandNotEqualSKIP_printLastHistory() {
		//System.out.println("Test4-testPrintLastHistoryIfSizeIsNotZeroANDnotEqualSKIP");
		class GameControllerStub extends GameController{
			public GameControllerStub() {
				super(null, 0, 1, 2, 0, true);
			}
		}
		GameControllerStub gcStub = new GameControllerStub();
		Combination combo = new Combination();
		Card card1 = new Card(2,1);
		combo.addCard(card1);
		Card card2 = new Card(2,2);
		combo.addCard(card2);
		gcStub.addHistory("Alice",combo);
		
		Combination combo2 = new Combination();
		Card card3 = new Card(3,1);
		combo2.addCard(card3);
		Card card4 = new Card(3,2);
		combo2.addCard(card4);
		Combination combination = new Combination(combo2);
		gcStub.addHistory("Bob",combination);
		
		gcStub.printLastHistory();
		String expectedResult = "****************************************************\r\n" + 
				"*History of last rounds' discarded card:\r\n" + 
				"[Round 1] [Turn 2] [Bob]: C6, H6\r\n" + 
				"****************************************************";
	    assertEquals(expectedResult, outContent.toString().trim());
	}
	
	@Test//unit test
	public void testGameController_historiesSizeIsZero_printHistories() {
		//System.out.println("Test5-testPrintHistoriesWhenSizeIsZero");
		class GameControllerStub extends GameController{
			public GameControllerStub() {
				super(null, 0, 1, 1, 0, true);
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		gcstub.printHistories();
		String expectedResult = "****************************************************\r\n" + 
				"No History\r\n" + 
				"****************************************************";
	    assertEquals(expectedResult, outContent.toString().trim());
	}
	@Test
	public void TestGameController_historiesSizeIsOne_printHistories() {
		//System.out.println("Test6-testPrintHistoriesWhenSizeIsOne");
		class GameControllerStub extends GameController{
			public GameControllerStub() {
				super(null, 0, 1, 1, 0, true);
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		Combination combo = new Combination();
		combo.addCard(new Card(3,1));
		combo.addCard(new Card(3,3));
		gcstub.addHistory("Alice", combo);
		gcstub.printHistories();
		
		String expectedOutput = "****************************************************\r\n" + 
				"history of discarded card: \r\n" + 
				"[Round 1] [Turn 1] [Alice]: C6, S6\r\n" + 
				"****************************************************";
		assertEquals(expectedOutput, outContent.toString().trim());
	}
	
	@Test
	public void TestGameController_historiesSizeMoreThanOne_printHistories() {
		//System.out.println("Test7-testPrintHistoriesWhenSizeIsMoreThanOne");
		class GameControllerStub extends GameController{
			public GameControllerStub() {
				super(null, 0, 1, 1, 0, true);
			}
		}
		GameControllerStub gcstub = new GameControllerStub();
		Combination combo = new Combination();
		Card card1 = new Card(2,1);
		combo.addCard(card1);
		Card card2 = new Card(2,2);
		combo.addCard(card2);
		gcstub.addHistory("Alice",combo);gcstub.turn++;
		
		Combination combo2 = new Combination();
		Card card3 = new Card(3,1);
		combo2.addCard(card3);
		Card card4 = new Card(3,2);
		combo2.addCard(card4);
		gcstub.addHistory("Bob",combo2);gcstub.turn++;
		
		Combination combo3 = new Combination();
		Card card5 = new Card(8,1);
		combo3.addCard(card5);
		Card card6 = new Card(8,2);
		combo3.addCard(card6);
		gcstub.addHistory("Candy", combo3);
		
		gcstub.printHistories();
		String expectedOutput = "****************************************************\r\n" + 
				"history of discarded card: \r\n" + 
				"[Round 1] [Turn 1] [Alice]: C5, H5\r\n" + 
				"[Round 1] [Turn 2] [Bob]: C6, H6\r\n" + 
				"[Round 1] [Turn 3] [Candy]: CJ, HJ\r\n" + 
				"****************************************************";
		assertEquals(expectedOutput, outContent.toString().trim());
	}
}
