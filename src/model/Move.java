package model;

public class Move {
	private Coordinate coordinate;
	private Symbol symbol;

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public Move(Coordinate coordinate, Symbol symbol) {
		this.coordinate = coordinate;
		this.symbol = symbol;
	}

}
