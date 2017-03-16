package blobsRecursive;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class GridStack<E> {

	private Vector<Vector<Cell<E>>> cells;
	private Stack<Cell<E>> stack;

	public GridStack(int rows, int columns) {
		cells = new Vector<Vector<Cell<E>>>();
		stack = new Stack<Cell<E>>();
		for (int row = 0; row < rows; row++) {
			Vector<Cell<E>> tempVector = new Vector<Cell<E>>();
			for (int col = 0; col < columns; col++) {
				tempVector.add(new Cell<E>(row, col));
			}
			cells.add(tempVector);
		}
	}

	public void setGrid(int percentage, E value) {
		Random randomGenerator = new Random(System.currentTimeMillis());
		int randomNum;
		for (int row = 0; row < cells.size(); row++) {
			for (int col = 0; col < cells.get(row).size(); col++) {
				randomNum = randomGenerator.nextInt(100);
				if (randomNum < percentage) {
					cells.get(row).get(col).setData(value);
				}
			}
		}
	}

	public int countBlobs(E value) {
		int count = 0;
		for (int row = 0; row < cells.size(); row++) {
			for (int col = 0; col < cells.get(row).size(); col++) {
				Cell<E> startCell = cells.get(row).get(col);
				System.out.println("starting at cell [" + row + "][" + col + "]");
				if (!startCell.isVisited() && startCell.hasData()) {
					count++;
					// mark the blob connected to starting cell
					markBlob(startCell);
				}
			}
		}
		return count;
	}

	public void markBlob(Cell<E> currentCell) {
		System.out.println("current cell " + currentCell.getRow() + " " + currentCell.getCol());
		stack.push(currentCell);
		while (!stack.isEmpty()) {
			currentCell = stack.pop();
			currentCell.setVisited();
			if (currentCell.getRow() > 0) {
				System.out.println(
						"moved up from cell " + currentCell + " " + currentCell.getRow() + " " + currentCell.getCol());
				pushCell(cells.get(currentCell.getRow() - 1).get(currentCell.getCol()));
			}
			if (currentCell.getRow() < cells.size() - 1) {
				System.out.println("moved down from cell " + currentCell + " " + currentCell.getRow() + " "
						+ currentCell.getCol());
				pushCell(cells.get(currentCell.getRow() + 1).get(currentCell.getCol()));
			}
			if (currentCell.getCol() > 0) {
				System.out.println("moved left from cell " + currentCell + " " + currentCell.getRow() + " "
						+ currentCell.getCol());
				pushCell(cells.get(currentCell.getRow()).get(currentCell.getCol() - 1));
			}

			if (currentCell.getCol() < cells.get(currentCell.getRow()).size() - 1) {
				System.out.println("moved right from cell " + currentCell + " " + currentCell.getRow() + " "
						+ currentCell.getCol());
				pushCell(cells.get(currentCell.getRow()).get(currentCell.getCol() + 1));
			}
		}
	}

	// check and push cell
	private void pushCell(Cell<E> cell) {
		if (cell.hasData() && !cell.isVisited()) {
			stack.push(cell);
		}
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < cells.size(); row++) {
			buffer.append("\n");
			for (int col = 0; col < cells.get(row).size(); col++) {
				buffer.append(" " + cells.get(row).get(col).toString());
			}
		}
		return buffer.toString();
	}

	static public void main(String[] args) {
		Character character = new Character('X');
		GridStack<Character> theGrid = new GridStack<Character>(5, 5);
		theGrid.setGrid(40, 'X');
		System.out.println(theGrid.toString());
		System.out.println(theGrid.countBlobs(character));
		System.out.println(theGrid);
	}
}
