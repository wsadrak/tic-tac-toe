package model;

public class Move {
	private Coordinate coordinate;
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	private Symbol symbol;
	
	public Move(Coordinate coordinate, Symbol symbol) {
		this.coordinate = coordinate;
		this.symbol = symbol;
	}
	
	

}
