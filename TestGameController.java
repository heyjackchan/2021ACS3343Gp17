import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestGameController {
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));

	}

	@AfterEach
	void tearDown() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	
	  @Test public void testDiscardWhenInputEqualToZero() { class
	  GameControllerStub extends GameController { public GameControllerStub() {
	  super(null, null, 1, 1, 5, 0, false); }
	  
	  public String promptPlayerInput() { return "0"; }
	  
	  public void skip() { currentPlayerIndex = nextPlayerIndex(); } }
	  
	  GameControllerStub gc = new GameControllerStub(); gc.discard();
	  assertEquals(2, gc.currentPlayerIndex); }
	  
	  @Test public void testDiscardWhenSkipCountEqualFourAndPlayerNotWin() { 
		  class GameControllerStub extends GameController { 
			  public GameControllerStub(AbstractPlayer[] players, Dealer dealer) {
				  super(dealer,players, 1, 1, 1, 4, false); 
		  }
	  
	  public String promptPlayerInput() { return "1"; }
	  
	  public boolean handleDiscardCards(String playerInput) { 
		  return true; 
		  } 
	  }
	  Dealer dealer = new Dealer(); 
	  AbstractPlayer[] players = new AbstractPlayer[4]; 
	  for (int i = 0; i < 4; i++) { 
		  players[i] = new HumanPlayer(); 
		  } 
	  for (int i = 0; i < 4; i++) { 
		  for (int j = 0; j < 13; j++) {
			  dealer.deal(players[i].getHand()); 
			  }
	  
	  players[i].sort(); } GameControllerStub gcStub = new GameControllerStub(players, dealer); 
	  gcStub.currentPlayer = players[1]; 
	  Card card1 = new Card(0, 0); 
	  Play currentPlay = gcStub.currentPlayer.getPlay();
	  currentPlay.getPreviousCombination().addCard(card1); gcStub.discard(); int
	  expectedResult = 2; assertEquals(expectedResult, gcStub.turn);
	  
	  }
	  
	  @Test public void testDiscardWhenSkipCountEqualFourAndPlayerWin() { 
		  class GameControllerStub extends GameController { 
			  public GameControllerStub(AbstractPlayer[] players, Dealer dealer) { 
				  super(dealer,players, 1, 1, 1, 4, false); 
				  }
	  
	  public String promptPlayerInput() { return "1"; }
	  
	  public boolean handleDiscardCards(String playerInput) { return true; }
	  
	  public void printWinningMessages() { System.out.println("Win"); } }
		  Dealer dealer = new Dealer(); 
		  AbstractPlayer[] players = new AbstractPlayer[4]; 
		  for (int i = 0; i < 4; i++) { players[i] = new HumanPlayer(); } 
		  for (int i = 0; i< 4; i++) { 
			  for (int j = 0; j < 13; j++) { 
				  dealer.deal(players[i].getHand());
				  }
			  players[i].sort(); 
	  } 
		  GameControllerStub gcStub = new GameControllerStub(players, dealer);
		  gcStub.currentPlayer = players[1]; 
		  Hand currentPlay = gcStub.currentPlayer.getHand(); 
		  currentPlay.cardNo = 0;
		  gcStub.discard(); assertEquals(true, gcStub.endGame); }
	  
	 
	 @Test public void testDiscardWhenPlayInvalid() {
		 class GameControllerStub extends GameController { 
			 public String input = "1\n0";
			 public InputStream testIn = new ByteArrayInputStream(input.getBytes());
			 public Scanner testsc = new Scanner(testIn); 
			 
			 public GameControllerStub(AbstractPlayer[] players,Dealer dealer){ 
				 super(dealer,players,1,2,1,4,false); 
				 setScanner(testsc);
				 } 
			 public boolean handleDiscardCards(String playerInput) { 
					 return false;
				 }	
			 public void testdiscard() {
			 		try { 		
			 			System.setIn(testIn);
			 			discard();
			 		
			 	}finally {
			 		System.setIn(System.in);	
			 	}
			 	}
			 public void skip() {
					currentPlayerIndex = nextPlayerIndex();
				}
	 } 
	Dealer dealer = new Dealer(); 
	AbstractPlayer[] players = new AbstractPlayer[4]; 
	for (int i = 0; i < 4; i++) { 
		players[i] = new HumanPlayer(); 
		} 
	for (int i = 0; i < 4; i++) { 
		for (int j = 0; j < 13; j++) {
	 dealer.deal(players[i].getHand()); 
	 } players[i].sort(); } 
	
	GameControllerStub gcStub = new GameControllerStub(players,dealer); 
	gcStub.currentPlayer = players[1]; 
	Hand currentPlay = gcStub.currentPlayer.getHand();
	gcStub.testdiscard();
	String expectresult = "Your play is invalid! Please choose again!";
	assertEquals(expectresult,outContent.toString().trim()); 
	 }
}
