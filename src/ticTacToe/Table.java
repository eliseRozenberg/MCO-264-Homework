package ticTacToe;

import school.InvalidDataException;

public class Table {
	private Character[][] table;
	private int turn;
	private int gamesPlayed;
	private Player player1;
	private Player player2;

	public Table(String name1, String name2) throws InvalidDataException {
		table = new Character[3][3];
		turn = 0;
		gamesPlayed = 0;
		player1 = new Player(name1, 'X');
		player2 = new Player(name2, 'Y');
	}

	public boolean checkTable(String name) throws InvalidDataException {
		if (name == null)
			throw new InvalidDataException();
		Player player = verifyPlayer(name);
		// check row
		for (int i = 0; i < table.length; i++) {
			if (!(table[i][0] == null || (table[i][1]) == null || (table[i][2]) == null))
				if (table[i][0].equals(table[i][1]) && table[i][1].equals(table[i][2])) {
					player.addWin();
					return true;
				}
		}
		// check column
		for (int j = 0; j < table[0].length; j++) {
			if (!(table[0][j] == null || (table[1][j]) == null || (table[2][j]) == null))
				if (table[0][j].equals(table[1][j]) && table[1][j].equals(table[2][j])) {
					player.addWin();
					return true;
				}
		} // end for
			// check diagonals
		if (!(table[0][0] == null || (table[1][1]) == null || (table[2][2]) == null))
			if (table[0][0].equals(table[1][1]) && table[1][1].equals(table[2][2])) {
				player.addWin();
				return true;
			}
		if (!(table[0][2] == null || (table[1][1]) == null || (table[2][0]) == null))
			if (table[0][2].equals(table[1][1]) && table[1][1].equals(table[2][0])) {
				player.addWin();
				return true;
			}
		return false;
	} // end
		// checkTable

	public boolean turn(Integer row, Integer col, String name) throws InvalidDataException {
		if (row == null || col == null || name == null)
			throw new InvalidDataException();
		Player player = verifyPlayer(name);
		if (player == null)
			throw new InvalidDataException();
		if (row > 3 || row < 0 || col > 3 || col < 0)
			return false;
		else if (table[row][col] != null)
			return false;
		else {
			table[row][col] = player.getSymbol();
			turn++;
			return true;
		}
	}

	public Player verifyPlayer(String player) {
		if (player.equalsIgnoreCase(player1.getName()))
			return player1;
		else if (player.equalsIgnoreCase(player2.getName()))
			return player2;
		else
			return null;
	}

	// clear table regulates games played
	public void clearTable() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++)
				table[i][j] = null;
		}
		turn = 0;
		gamesPlayed++;
	}

	public int getTurn() {
		return this.turn;
	}

	public int getGamesPlayed() {
		return this.gamesPlayed;
	}

	public String displayTable() {
		StringBuffer buffer = new StringBuffer();
		int h;
		int g;
		buffer.append("\n  0   1   2\n");
		buffer.append("-------------\n");

		for (g = 0; g < table.length; g++) {
			for (h = 0; h < table[g].length; h++) {
				if (!(table[g][h] == null))
					buffer.append("| " + table[g][h] + " ");
				else
					buffer.append("|   ");
			}
			buffer.append("| " + g + "\n");
			buffer.append("-------------\n");
		}
		return buffer.toString();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nGames Played: " + gamesPlayed);
		buffer.append("\nGames Tied: " + (gamesPlayed - player1.getWins() - player2.getWins()));
		buffer.append("\n" + player1);
		buffer.append("\n" + player2);
		return buffer.toString();

	}

}// end class
