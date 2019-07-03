package model.players;

import java.util.Random;

import model.Coordinate;
import model.Move;
import model.Symbol;

public class CpuPlayer implements Player {

	@Override
	public Move doMove() {
		Coordinate coordinate = getCoordinate();
		Symbol symbol = getSymbol();
		return new Move(coordinate, symbol);
	}

	private Symbol getSymbol() {
		return Symbol.NOUGHT;
	}

	private Coordinate getCoordinate() {
		Random random = new Random();
		int row = random.nextInt(3);
		int column = random.nextInt(3);
		return new Coordinate(row, column);
	}

	@Override
	public String toString() {
		return "Computer";
	}

}
