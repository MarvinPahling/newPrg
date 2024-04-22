package cardmaster;

import cardmaster.cards.Card;
import cardmaster.cards.ChanceCard;
import cardmaster.cards.PaarCard;
import cardmaster.cards.TripelCard;

import java.util.Random;

public class CardFactory {
    private static final CardFactory defaultFactory = new CardFactory();

CardFactory(){

}
public static CardFactory getDefaultFactory(){
    return defaultFactory;
}

//TODO TYPENNAMEN ALS ENUM
public Card createRandom(){
    Random random = new Random();
    String[] names = {"Chance", "Paar", "Tripel"};
    String randomName = names[random.nextInt(names.length)];

    return create(randomName, Shape.getRandomShape());
}
public Card create(String name, Shape shape){
    return switch (name) {
        case "Chance" -> new ChanceCard(shape);
        case "Paar" -> new PaarCard(shape);
        case "Tripel" -> new TripelCard(shape);
        default -> null;
    };
}

}
