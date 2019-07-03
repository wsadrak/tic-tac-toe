package model;

public enum Symbol {
	EMPTY("   "), CROSS(" X "), NOUGHT(" O ");

	String desc;

	private Symbol(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return desc;
	}
}
