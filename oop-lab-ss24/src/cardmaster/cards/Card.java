package cardmaster.cards;

import cardmaster.Shape;


/**
 * <h1>Punkte</h1> 0.5 Punkte für jeden nicht leeren Ablagestapel und 0.5 Punkte
 * für jede einzigartige Form.
 * <p>
 * Beispiele:
 * 
 * <pre>
 *   -
 *   - Stern < gelegt
 *   -
 * </pre>
 * 
 * 0.5 für nicht leere Stapel + 0.5 für die Form Stern
 * 
 * <pre>
 * -Kreis - Stern < gelegt - Kreis
 * </pre>
 * 
 * 1.5 für nicht leere Stapel + 1.0 für die Formen Kreis und Stern.
 */
public abstract class Card {

	private Shape shape;


	public Card(Shape shape) {
		this.shape = shape;
	}

	/**
	 * Gibt die Form der Karte zurück.
	 */
	public Shape getShape() {
		return this.shape;
	}

	/**
	 * Gibt den Namen der Karte zurück. z.B.: Chance
	 */
	public abstract String getName();

	public static int countEqualShapes(Shape[] topShapes, Shape shape) {
		int count = 0;
		for (Shape s : topShapes) {
			if (s == shape) {
				count++;
			}
		}
		return count;
	}

	public abstract float calculateCredits(Shape[] topShapes);

	@Override
	public String toString() {
		return this.getName() + " " + this.getShape().toString();
	}
}
