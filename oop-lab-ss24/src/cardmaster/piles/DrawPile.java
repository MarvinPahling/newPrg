package cardmaster.piles;

import cardmaster.cards.Card;

import java.util.Arrays;

public class DrawPile extends Pile {


    @Override
    public boolean isEmpty() {
        return cards.getSize() == 0;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getTopCard() {
        return cards.pop();
    }

    @Override
    public int getSize() {
        return cards.getSize();
    }

    public void shuffle() {
        cards.shuffle();
    }


    @Override
    public void reset() {

    }

    @Override
    public String toString(){
        return Arrays.toString(cards.toArray());
    }


}
