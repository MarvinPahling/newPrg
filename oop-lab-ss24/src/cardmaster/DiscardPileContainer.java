package cardmaster;

import cardmaster.collections.AlgoArrayList;
import cardmaster.piles.DiscardPile;

public class DiscardPileContainer {

    private AlgoArrayList<DiscardPile> discardPiles = new AlgoArrayList<>();
    private int maxPiles;

    public DiscardPileContainer(int maxPiles) {
        this.maxPiles = maxPiles;
        for (int i = 0; i < maxPiles; i++) {
            discardPiles.add(new DiscardPile());
        }
    }

    public Shape[] getTopShapes() {
        AlgoArrayList<Shape> topShapes = new AlgoArrayList<>();
        for (DiscardPile pile : discardPiles) {
            if (!pile.isEmpty()) {
                topShapes.add(pile.cards.peak().getShape());
            }
        }
        Shape[] shapes = new Shape[topShapes.getSize()];
        System.arraycopy(topShapes.toArray(), 0, shapes, 0, topShapes.getSize());
        return shapes;
    }

    public int getSize() {
        return discardPiles.getSize();
    }

    public DiscardPile get(int index) {
        return discardPiles.get(index);
    }

}
