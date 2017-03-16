package MMnecklaceMidterm;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

public class Necklace {
	private LinkedList<NecklacePieceInterface> beads;
	private Double maxLength; // maximum length in inches
	private Double currLength;

	public Necklace(Double length) {
		this.maxLength = length;
		beads = new LinkedList<NecklacePieceInterface>();
		currLength = 0.0;
	}

	public void remove(Color c) {
		Iterator<NecklacePieceInterface> iter = beads.iterator();
		NecklacePieceInterface bead;
		while (iter.hasNext()) {
			bead = iter.next();
			if (bead.getColor().equals(c)) {
				beads.remove(bead);
				iter = beads.iterator();
			}
		}
	}

	public void addBead(NecklacePieceInterface bead) {
		NecklacePieceInterface currentBead;
		if (bead.getLength() <= (maxLength - currLength)) {
			for (int i = 0; i < beads.size(); i++) {
				currentBead = beads.get(i);
				if (currentBead.getColor() == bead.getColor()) {
					beads.add(i, bead);
					currLength += bead.getLength();
					return;
				}
			}
			beads.addLast(bead);
		}
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append(" Necklace Current Length ");
		info.append(this.currLength);
		info.append(beads.toString());
		return info.toString();
	}

}
