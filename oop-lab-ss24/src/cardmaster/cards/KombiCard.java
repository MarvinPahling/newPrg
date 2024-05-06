package cardmaster.cards;

import cardmaster.Shape;

public class KombiCard extends Card{

    Card card1;
    Card card2;

    public KombiCard(Card card1, Card card2){

        super(card1.getShape());
        this.card1 = card1;
        this.card2 = card2;


    }

    public KombiCard(Shape shape) {
        super(shape);
    }

    @Override
    public String getName() {
        return "Kombi";
    }

    @Override
    public float calculateCredits(Shape[] topShapes) {
        return card1.calculateCredits(topShapes) + card2.calculateCredits(topShapes);
    }
}
