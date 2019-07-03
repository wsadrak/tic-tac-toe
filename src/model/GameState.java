package model;

public enum GameState {
	IN_PROGRES("in progr"), WIN("You win!"), LOSE("You lose!"), DRAW("It's a draw");
	
	String desc;

	private GameState(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
}
