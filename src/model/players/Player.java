package model.players;

import io.ConsolePrinter;
import model.Move;

public interface Player {
	ConsolePrinter printer = new ConsolePrinter();
	Move doMove();
}
