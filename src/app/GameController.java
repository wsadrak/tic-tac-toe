package app;

import java.util.Random;

import exception.InvalidMoveException;
import model.Board;
import model.Coordinate;
import model.GameState;
import model.Move;
import model.Symbol;
import model.players.CpuPlayer;
import model.players.Player;
import model.players.UserPlayer;

public class GameController {
	private Player user = new UserPlayer();
	private Player computer = new CpuPlayer();
	private Player currentPlayer;
	private GameState statusGry;

	private Board gameBoard;
	private int round = 0;

	public GameController() {
		setupGame();
	}

	private void setupGame() {
		currentPlayer = generateWhoStarts();
		System.out.println(currentPlayer);
		gameBoard = new Board();
		statusGry = GameState.IN_PROGRES;
	}

	private Player generateWhoStarts() {
		Random random = new Random();
		int temporaryValue = random.nextInt(2);

		if (temporaryValue == 0) {
			System.out.println("Zaczyna komputer");
			return new CpuPlayer();
		} else {
			System.out.println("Zaczyna u¿ytkownik");
			return new UserPlayer();
		}
	}

	public void mainLoop() {

		drawBoard();

		// TODO - zrobiæ pêtlê
		while (statusGry == GameState.IN_PROGRES) {
			System.out.println("Runda " + (round+1));
			nextMove();
			drawBoard();
			switchPlayer();
			updateStatus();
		}

	}

	private void updateStatus() {
		// TODO Auto-generated method stub
		Symbol winner = Symbol.EMPTY;

		/*
		 * diagonal 1
		 */
		if (gameBoard.getSymbols()[0][0] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[2][2]
				&& gameBoard.getSymbols()[0][0] != Symbol.EMPTY) {
//			System.out.println("");
			winner = gameBoard.getSymbols()[0][0];
			System.out.println("Diagonal 1 " + winner);
		}

		/*
		 * diagonal 2
		 */
		if (gameBoard.getSymbols()[0][2] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[2][0]
				&& gameBoard.getSymbols()[0][2] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][2];
			System.out.println("Diagonal 2 " + winner);
		}

		/*
		 * horizontal 0
		 */
		if (gameBoard.getSymbols()[0][0] == gameBoard.getSymbols()[0][1]
				&& gameBoard.getSymbols()[0][1] == gameBoard.getSymbols()[0][2]
				&& gameBoard.getSymbols()[0][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][0];
			System.out.println("Horizontal 0 " + winner);
		}

		/*
		 * horizontal 1
		 */
		if (gameBoard.getSymbols()[1][0] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[1][2]
				&& gameBoard.getSymbols()[1][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[1][0];
			System.out.println("Horizontal 1 " + winner);
		}

		/*
		 * horizontal 2
		 */
		if (gameBoard.getSymbols()[2][0] == gameBoard.getSymbols()[2][1]
				&& gameBoard.getSymbols()[2][1] == gameBoard.getSymbols()[2][2]
				&& gameBoard.getSymbols()[2][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[2][0];
			System.out.println("Horizontal 2 " + winner);
		}

		/*
		 * vertical 0
		 */
		if (gameBoard.getSymbols()[0][0] == gameBoard.getSymbols()[1][0]
				&& gameBoard.getSymbols()[1][0] == gameBoard.getSymbols()[2][0]
				&& gameBoard.getSymbols()[0][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][0];
			System.out.println("Vertical 0 " + winner);
		}

		/*
		 * vertical 1
		 */
		if (gameBoard.getSymbols()[0][1] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[2][1]
				&& gameBoard.getSymbols()[0][1] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][1];
			System.out.println("Vertical 1 " + winner);
		}

		/*
		 * vertical 2
		 */
		if (gameBoard.getSymbols()[0][2] == gameBoard.getSymbols()[1][2]
				&& gameBoard.getSymbols()[1][2] == gameBoard.getSymbols()[2][2]
				&& gameBoard.getSymbols()[0][2] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][2];
			System.out.println("Vertical 2 " + winner);
		}

		if (winner == Symbol.CROSS) {
			System.out.println("GAME OVER: USER WINS");
			statusGry = GameState.WIN;
		} else if (winner == Symbol.NOUGHT) {
			System.out.println("GAME OVER: COMPUTER WINS");
			statusGry = GameState.LOSE;
		} else if (round == 9) {
			System.out.println("GAME OVER: DRAW");
			statusGry = GameState.DRAW;
		} else {
			System.out.println("Game in progress");
		}

	}

	private void switchPlayer() {
		System.out.print("Switching from " + currentPlayer + " to ");
		if (currentPlayer instanceof UserPlayer) {
			currentPlayer = computer;
		} else if (currentPlayer instanceof CpuPlayer) {
			currentPlayer = user;
		}
		System.out.println(currentPlayer);

	}

	private void nextMove() {
		Move move = null;
		System.out.println("Next move: " + currentPlayer);
		boolean isValidationCorrect = false;
		while (!isValidationCorrect) {
			try {
				move = currentPlayer.doMove();
				isValidationCorrect = validate(move);
			} catch (InvalidMoveException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
				if (currentPlayer instanceof UserPlayer) {
					System.err.println("niepoprawny ruch, spróbuj jeszcze raz");
				}
			}
		}
		System.out.println("Round: " + round);
		updateBoard(move);
	}

	private void updateBoard(Move move) {
		Symbol symbol = move.getSymbol();
		Coordinate coordinate = move.getCoordinate();

		gameBoard.setSymbolAt(symbol, coordinate);
	}

	private boolean validate(Move move) throws InvalidMoveException {
		boolean isMoveValid = false;
		int rowToCheck = move.getCoordinate().getRow();
		int colToCheck = move.getCoordinate().getCol();

		Symbol symbolAtPosition = gameBoard.getSymbols()[rowToCheck][colToCheck];

		if (symbolAtPosition == Symbol.EMPTY) {
			isMoveValid = true;
			round++;
		} else {
			throw new InvalidMoveException("Pole jest zajête");
		}
		return isMoveValid;
	}

	private void drawBoard() {
		gameBoard.printBoard();
	}

}
