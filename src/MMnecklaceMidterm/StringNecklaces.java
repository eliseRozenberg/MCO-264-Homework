package MMnecklaceMidterm;

import java.awt.Color;
import java.util.Stack;

public class StringNecklaces {

	public static void main(String[] args) {

		Necklace aNecklace = new Necklace(12.0);

		aNecklace.addBead(new Stone(Color.RED, .25));

		aNecklace.addBead(new Bead(Color.RED, Shape.DIAMOND, .3));
		aNecklace.addBead(new Bead(Color.BLUE, Shape.OVAL, .25));

		aNecklace.addBead(new Bead(Color.GREEN, Shape.SQUARE, .25));
		aNecklace.addBead(new Bead(Color.BLUE, Shape.DIAMOND, .30));

		aNecklace.addBead(new Bead(Color.BLUE, Shape.OVAL, .25));

		aNecklace.addBead(new Bead(Color.RED, Shape.SQUARE, .25));
		aNecklace.addBead(new Bead(Color.GREEN, Shape.DIAMOND, .30));

		System.out.println(aNecklace);

		aNecklace.remove(Color.RED);

		System.out.println(aNecklace);

		//ignore
		Stack<Bead> neck = new Stack<Bead>();
		int maxLength = 10;
		int currLength = 0;
		addBead(neck, new Bead(Color.BLUE, Shape.DIAMOND, .30), maxLength, currLength);
		addBead(neck, new Bead(Color.RED, Shape.DIAMOND, .30), maxLength, currLength);
		addBead(neck, new Bead(Color.BLUE, Shape.DIAMOND, .30), maxLength, currLength);
		System.out.println(neck);
	}

	
	
	//code to test add stack - see answer in Note
	public static void addBead(Stack<Bead> neck, Bead bead, int maxLength, int currLength) {

		Bead[] temp = new Bead[neck.size()];
		int i = 0;
		if (bead.getLength() <= (maxLength - currLength)) {
			if (neck.isEmpty()) {
				neck.push(bead);
				currLength += bead.getLength();
				return;
			}
			// go through and pop until get to the right color or the list is
			// empty - color not there yet
			while (!neck.isEmpty() && neck.peek().getColor() != bead.getColor()) {
				temp[i] = neck.pop();
				i++;
			}
			// if color not there yet - put all abck and add new one
			if (neck.isEmpty()) {
				for (int j = 0; j < temp.length; j++) {
					neck.push(temp[j]);
				}
				neck.push(bead);
				currLength += bead.getLength();
				return;
			}
			// otherwise push the bead with the right color and push the rest
			// back
			neck.push(bead);
			for (int j = 0; j < temp.length; j++) {
				neck.push(temp[j]);
				currLength += bead.getLength();
			}
		}
	}

}
