package MMnecklaceMidterm;

import java.awt.Color;

public class Bead implements NecklacePieceInterface {
	private Color color;
	private Shape shape;
	private Double length;

	public Bead(Color color, Shape shape, Double length) {
		this.color = color;
		this.shape = shape;
		this.length = length;
	}

	public Color getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}

	public Double getLength() {
		return length;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append(" Bead ");
		info.append(color.toString());
		info.append(" ");
		info.append(shape.name());
		info.append(" ");
		info.append(" Length ");
		info.append(length);
		info.append(" inches\n");
		return info.toString();
	}

}
