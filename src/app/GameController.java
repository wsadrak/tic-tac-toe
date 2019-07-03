package app;

import java.util.Random;

import io.ConsolePrinter;
import io.exceptions.InvalidMoveException;
import model.Board;
import model.Coordinate;
import model.GameState;
import model.Move;
import model.Symbol;
import model.players.CpuPlayer;
import model.players.Player;
import model.players.UserPlayer;

public class GameController {
	private ConsolePrinter printer = new ConsolePrinter();

	private Player user = new UserPlayer();
	private Player computer = new CpuPlayer();
	private Player currentPlayer;

	private GameState gameState = GameState.IN_PROGRES;
	private Board gameBoard = new Board();
	private int madeMoves = 0;

	public GameController() {
		setupGame();
	}

	private void setupGame() {
		currentPlayer = generateWhoStarts();
	}

	private Player generateWhoStarts() {
		Random random = new Random();
		int temporaryValue = random.nextInt(2);

		if (temporaryValue == 0) {
			return new CpuPlayer();
		} else {
			return new UserPlayer();
		}
	}

	public void mainLoop() {
		while (gameState == GameState.IN_PROGRES) {
			printRound();
			nextMove();
			drawBoard();
			switchPlayer();
			updateStatus();
		}
	}

	private void printRound() {
		printer.printNextLine("Round " + (madeMoves + 1));
	}

	private void nextMove() {
		Move move = null;
		printer.printNextLine("Move: " + currentPlayer);
		boolean isValidationCorrect = false;
		while (!isValidationCorrect) {
			try {
				move = currentPlayer.doMove();
				isValidationCorrect = validate(move);
			} catch (InvalidMoveException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
				if (currentPlayer instanceof UserPlayer) {
					System.err.println("Incorrect move. Please try again.");
				}
			} finally {
				printer.printNextLine("");
			}
		}
		updateBoard(move);
	}

	private boolean validate(Move move) throws InvalidMoveException {
		boolean isMoveValid = false;
		int rowToCheck = move.getCoordinate().getRow();
		int colToCheck = move.getCoordinate().getCol();

		Symbol symbolAtPosition = gameBoard.getSymbols()[rowToCheck][colToCheck];

		if (symbolAtPosition == Symbol.EMPTY) {
			isMoveValid = true;
			madeMoves++;
		} else {
			throw new InvalidMoveException("Incorrect move");
		}
		return isMoveValid;
	}

	private void updateBoard(Move move) {
		Symbol symbol = move.getSymbol();
		Coordinate coordinate = move.getCoordinate();
		gameBoard.setSymbolAt(symbol, coordinate);
	}

	private void drawBoard() {
		gameBoard.printBoard();
	}

	private void switchPlayer() {
		if (currentPlayer instanceof UserPlayer) {
			currentPlayer = computer;
		} else if (currentPlayer instanceof CpuPlayer) {
			currentPlayer = user;
		}
	}

	private void updateStatus() {

		Symbol winnerSymbol = validatePositions();
		if (winnerSymbol == Symbol.CROSS) {
			printer.printNextLine("GAME OVER: USER WINS");
			gameState = GameState.WIN;
		} else if (winnerSymbol == Symbol.NOUGHT) {
			printer.printNextLine("GAME OVER: COMPUTER WINS");
			gameState = GameState.LOSE;
		} else if (madeMoves == 9) {
			printer.printNextLine("GAME OVER: DRAW");
			gameState = GameState.DRAW;
		}
	}

	private Symbol validatePositions() {
		Symbol winner = Symbol.EMPTY;
		/*
		 * diagonal 1
		 */
		if (gameBoard.getSymbols()[0][0] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[2][2]
				&& gameBoard.getSymbols()[0][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][0];
		}

		/*
		 * diagonal 2
		 */
		if (gameBoard.getSymbols()[0][2] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[2][0]
				&& gameBoard.getSymbols()[0][2] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][2];
		}

		/*
		 * horizontal 0
		 */
		if (gameBoard.getSymbols()[0][0] == gameBoard.getSymbols()[0][1]
				&& gameBoard.getSymbols()[0][1] == gameBoard.getSymbols()[0][2]
				&& gameBoard.getSymbols()[0][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][0];
		}

		/*
		 * horizontal 1
		 */
		if (gameBoard.getSymbols()[1][0] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[1][2]
				&& gameBoard.getSymbols()[1][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[1][0];
		}

		/*
		 * horizontal 2
		 */
		if (gameBoard.getSymbols()[2][0] == gameBoard.getSymbols()[2][1]
				&& gameBoard.getSymbols()[2][1] == gameBoard.getSymbols()[2][2]
				&& gameBoard.getSymbols()[2][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[2][0];
		}

		/*
		 * vertical 0
		 */
		if (gameBoard.getSymbols()[0][0] == gameBoard.getSymbols()[1][0]
				&& gameBoard.getSymbols()[1][0] == gameBoard.getSymbols()[2][0]
				&& gameBoard.getSymbols()[0][0] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][0];
		}

		/*
		 * vertical 1
		 */
		if (gameBoard.getSymbols()[0][1] == gameBoard.getSymbols()[1][1]
				&& gameBoard.getSymbols()[1][1] == gameBoard.getSymbols()[2][1]
				&& gameBoard.getSymbols()[0][1] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][1];
		}

		/*
		 * vertical 2
		 */
		if (gameBoard.getSymbols()[0][2] == gameBoard.getSymbols()[1][2]
				&& gameBoard.getSymbols()[1][2] == gameBoard.getSymbols()[2][2]
				&& gameBoard.getSymbols()[0][2] != Symbol.EMPTY) {
			winner = gameBoard.getSymbols()[0][2];
		}
		return winner;
	}

}
