package cardmaster;

import cardmaster.cards.Card;

import java.util.Random;

public class ChanceCardFactory extends CardFactory {


    private static final ChanceCardFactory defaultFactory = new ChanceCardFactory();

    ChanceCardFactory(){

    }

    @Override
    public Card createRandom(){
        System.out.println("Chance card factory is cooking");
        Random random = new Random();
        return super.create(CardType.CHANCE.getName(), Shape.getRandomShape());
    }
}
