package cardmaster;

import cardmaster.Shape;
import cardmaster.cards.*;

public enum CardType {

    CHANCE("CHANCE"){
        @Override
        public Card createCard(Shape shape) {
            return new ChanceCard(shape);
        }
    },
    PAAR("PAAR") {
        @Override
        public Card createCard(Shape shape) {
            return new PaarCard(shape);
        }
    },
    TRIPEL("TRIPEL")
    {
        @Override
        public Card createCard(Shape shape) {
            return new TripelCard(shape);
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