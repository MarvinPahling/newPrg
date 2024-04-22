package cardmaster;

import cardmaster.cards.Card;
import cardmaster.collections.AlgoArrayList;

public class Inventory {

    private AlgoArrayList<Card> cards = new AlgoArrayList<>();
    private int UpgradeHandCount;
    private int UpgradeDiscardCount;
    private int UpgradeShopCount;
    private int credits;

    public Inventory(){
        UpgradeHandCount = 0;
        UpgradeDiscardCount = 0;
        UpgradeShopCount = 0;
    }

    public int getMaxShopCards(){
        return 5 + UpgradeShopCount;
    }

    public int getMaxHandCards(){
        return 4 + UpgradeHandCount;
    }

    public int getMaxDiscardPiles(){
        return 3 + UpgradeDiscardCount;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public AlgoArrayList<Card> getCards(){
        return cards;
    }

    public void addCredits(int credits){
        this.credits += credits;
    }

    public int getCredits(){
        return credits;
    }

    public boolean tryToBuy(int credits){
        if(this.credits >= credits){
            this.credits -= credits;
            return true;
        }
        return false;
    }
}
