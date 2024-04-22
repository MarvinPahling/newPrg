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


public Card createRandom(){
    Random random = new Random();
    CardType[] types = CardType.values();
    CardType randomType = types[random.nextInt(types.length)];

    return randomType.createCard(Shape.getRandomShape());
}
public Card create(String name, Shape shape){
    return CardType.valueOf(name.toUpperCase()).createCard(shape);
}

}
