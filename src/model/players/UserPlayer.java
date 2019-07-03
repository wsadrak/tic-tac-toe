package model.players;

import java.util.Scanner;

import model.Coordinate;
import model.Move;
import model.Symbol;

public class UserPlayer implements Player {

	private Scanner input = new Scanner(System.in);

	@Override
	public Move doMove() {
		Coordinate coordinate = getCoordinate();
		Symbol symbol = getSymbol();
		return new Move(coordinate, symbol);
	}

	private Symbol getSymbol() {
		return Symbol.CROSS;
	}

	private Coordinate getCoordinate() {
		System.out.println("Podaj wiersz: ");
		int row = Integer.parseInt(input.nextLine());
		System.out.println("Podaj kolumnê: ");
		int column = Integer.parseInt(input.nextLine());
		return new Coordinate(row, column);
	}
	
	@Override
	public String toString() {
		return "User";
	}

}
