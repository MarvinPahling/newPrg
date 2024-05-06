package cardmaster.cards;

import cardmaster.Shape;

public class QuadrupelCard extends Card{
    public QuadrupelCard(Shape shape) {
        super(shape);
    }

    @Override
    public String getName() {
        return "Quadrupel";
    }

    @Override
    public float calculateCredits(Shape[] topShapes) {
        int count = Card.countEqualShapes(topShapes, getShape());
        return (float) ((Math.floor(count / 4.0)) * 10);
    }
}
