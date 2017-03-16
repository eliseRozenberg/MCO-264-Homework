package ticTacToe;

import school.InvalidDataException;

public class Player {

	private String name;
	private Character symbol;
	private int wins;

	public Player(String name, Character symbol) throws InvalidDataException {
		if (name == null || symbol == null)
			throw new InvalidDataException();
		this.name = name;
		this.symbol = symbol;
		wins = 0;
	}

	public String getName() {
		return name;
	}

	public Character getSymbol() {
		return symbol;
	}

	public int getWins() {
		return wins;
	}

	public void addWin() {
		wins++;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Player: " + name);
		buffer.append("\tSymbol: " + symbol);
		buffer.append("\tWins: " + wins);
		return buffer.toString();
	}

}
