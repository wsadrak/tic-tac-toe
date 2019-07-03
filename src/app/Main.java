package app;

public class Main {

	public static void main(String[] args) {
		runGame();
	}

	private static void runGame() {
		GameController gameController = new GameController();
		gameController.mainLoop();
	}

}
