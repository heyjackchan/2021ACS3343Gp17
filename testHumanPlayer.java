import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testHumanPlayer {

	//Test case A0601: Create an new player without any information, print out its information.
	@Test
	public void test_NewPlayerNull_toString() {
		HumanPlayer humanPlayer = new HumanPlayer();
		String expectResult = "{ name= }\n"
				+ " ,play={\n"
				+ "currentCombination={ \n"
				+ "combination{\n"
				+ "cardNo=0, \n"
				+ "}, \n"
				+ "}\n"
				+ "previousCombination={ \n"
				+ "combination{\n"
				+ "cardNo=0, \n"
				+ "}, \n"
				+ "}\n"
				+ " } \n"
				+ "";
		String actualResult = humanPlayer.toString();
		assertEquals(expectResult, actualResult);
	}
	
	//Test case A0602: Create an new player with all required information, print out its information.
	@Test
	public void test_NewPlayer_withInfo_toString() {
		Hand hand = new Hand();
		Play play = new Play();
		String name = "Jack";
		SortStrategy sortStrategy = new BubbleSort();
		HumanPlayer humanPlayer_withInfo = new HumanPlayer(hand, play, name, sortStrategy);
		String expectResult = "{ name=Jack }\n"
				+ " ,play={\n"
				+ "currentCombination={ \n"
				+ "combination{\n"
				+ "cardNo=0, \n"
				+ "}, \n"
				+ "}\n"
				+ "previousCombination={ \n"
				+ "combination{\n"
				+ "cardNo=0, \n"
				+ "}, \n"
				+ "}\n"
				+ " } \n"
				+ "";
		System.out.print(humanPlayer_withInfo.toString());
		String actualResult = humanPlayer_withInfo.toString();
		assertEquals(expectResult, actualResult);
	}

} 
