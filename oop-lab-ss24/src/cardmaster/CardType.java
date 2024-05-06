package cardmaster;

import cardmaster.Shape;
import cardmaster.cards.*;

public enum CardType {

    CHANCE("Chance"){
        @Override
        public Card createCard(Shape shape) {
            return new ChanceCard(shape);
        }
    },
    PAAR("Paar") {
        @Override
        public Card createCard(Shape shape) {
            return new PaarCard(shape);
        }
    },
    TRIPEL("Tripel")
    {
        @Override
        public Card createCard(Shape shape) {
            return new TripelCard(shape);
        }
    },
    QUADRUPEL("Quadrupel")
    {
        @Override
        public Card createCard(Shape shape) {
            return new QuadrupelCard(shape);
        }
    },
    KOMBI("Kombi")
    {
        @Override
        public Card createCard(Shape shape) {
            Card card1 = CardFactory.getDefaultFactory().createRandom();
            Card card2 = CardFactory.getDefaultFactory().createRandom();
            while(card1.getShape() == card2.getShape()){
                card2 = CardFactory.getDefaultFactory().createRandom();
            }
            return new KombiCard(card1, card2);
        }
    };

    private final String name;

    CardType(String name) {
        this.name = name;
    }

    public abstract Card createCard(Shape shape);
    public String getName() {
        return name;
    }
}