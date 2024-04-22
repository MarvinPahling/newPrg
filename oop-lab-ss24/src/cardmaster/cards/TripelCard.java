package cardmaster.cards;

import cardmaster.Shape;

public class TripelCard extends Card {
    public TripelCard(Shape shape) {
        super(shape);
    }

    @Override
    public String getName() {
        return "Tripel";
    }

    @Override
    public float calculateCredits(Shape[] topShapes) {
        int count = Card.countEqualShapes(topShapes, getShape());
        return (float) ((Math.floor(count / 3.0)) * 5);
    }
}
