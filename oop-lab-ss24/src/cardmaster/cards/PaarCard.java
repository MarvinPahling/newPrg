package cardmaster.cards;

import cardmaster.Shape;

public class PaarCard extends Card{
    public PaarCard(Shape shape) {
        super(shape);
    }

    @Override
    public String getName() {
        return "Paar";
    }

    @Override
    public float calculateCredits(Shape[] topShapes) {
        int count = Card.countEqualShapes(topShapes, getShape());
        return (float) ((Math.floor(count / 2.0)) * 2);
    }
}
