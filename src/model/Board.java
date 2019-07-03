package model;

public class Board {
	private static final int SIZE = 3;
	private Symbol[][] symbols = new Symbol[SIZE][SIZE];

	public Symbol[][] getSymbols() {
		return symbols;
	}

	public Board() {
		initBoard();
	}

	private void initBoard() {
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				getSymbols()[row][column] = Symbol.EMPTY;
			}
		}
	}

	public void printBoard() {
		System.out.println("=== TIC TAC TOE GAME ===\n");
		System.out.println("    0   1   2    ");
		System.out.print("   -----------  ");

		for (int row = 0; row < SIZE; row++) {
			System.out.print("\n" + (row) + " |");

			for (int column = 0; column < SIZE; column++) {
				System.out.print(getSymbols()[row][column] + "|");
			}

			if (row < SIZE - 1) {
				System.out.print("\n   -----------  ");
			}
		}

		System.out.println("\n   -----------  \n");
	}

	public void setSymbolAt(Symbol symbol, Coordinate coordinate) {
		int row = coordinate.getRow();
		int col = coordinate.getCol();
		
		symbols[row][col] = symbol;
	}
	
	
}
