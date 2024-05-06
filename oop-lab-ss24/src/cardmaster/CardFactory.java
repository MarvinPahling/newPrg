package cardmaster;

import cardmaster.cards.*;

import java.util.Random;

public class CardFactory {
    private static final CardFactory defaultFactory = new CardFactory();

public CardFactory(){

}
public static CardFactory getDefaultFactory(){
    return defaultFactory;
}


public Card createRandom(){
    System.out.println("createRandom is usd!");
    Random random = new Random();
    CardType[] types = CardType.values();
    CardType randomType = types[random.nextInt(types.length)];
    return randomType.createCard(Shape.getRandomShape());


}
public Card create(String name, Shape shape){
    System.out.printf("%s tries to create %s with %s \n", this, name, shape);
    return CardType.valueOf(name.toUpperCase()).createCard(shape);
}


}
