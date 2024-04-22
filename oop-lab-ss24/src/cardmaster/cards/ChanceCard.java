package cardmaster.cards;

import cardmaster.Shape;
import cardmaster.piles.DiscardPile;

public class ChanceCard extends Card {
    public ChanceCard(Shape shape) {
        super(shape);
    }

    @Override
    public String getName() {
        return "Chance";
    }

    @Override
    public float calculateCredits(Shape[] topShapes) {
        float c = 0;
        for (int i = 0; i < topShapes.length; i++) {
            for (int j = 0; j < topShapes.length; j++) {
                if(i != j){
                    if(topShapes[i] == topShapes[j]){
                        topShapes[j] = null;
                    }
                }
            }
        }
        c += topShapes.length * 0.5f;

        for (Shape shape : topShapes){
            if(shape != null){
                c += 0.5f;
            }
        }
        return c;
    }
}
