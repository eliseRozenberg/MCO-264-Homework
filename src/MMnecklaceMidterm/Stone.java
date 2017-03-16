package MMnecklaceMidterm;

import java.awt.Color;

public class Stone implements NecklacePieceInterface {
	private Color color;
	private Double length;

	public Stone(Color col, Double len) {
		color = col;
		length = len;
	}

	@Override
	public Color getColor() {

		return color;
	}

	@Override
	public Double getLength() {
		return length;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append(" Stone ");
		info.append(color.toString());
		info.append(" ");
		info.append(" Length ");
		info.append(length);
		info.append(" inches\n");
		return info.toString();
	}

}
