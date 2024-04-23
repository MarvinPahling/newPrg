package cardmaster;

import cardmaster.cards.Card;
import cardmaster.collections.AlgoArrayList;

public class Inventory {

    private AlgoArrayList<Card> cards = new AlgoArrayList<>();
    private int UpgradeHandCount;
    private int UpgradeDiscardCount;
    private int UpgradeShopCount;
    private float credits;

    public Inventory(int credits){
        UpgradeHandCount = 0;
        UpgradeDiscardCount = 0;
        UpgradeShopCount = 0;
        this.credits = credits;
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

    public void addShopUpgrade(){
        UpgradeShopCount++;
    }
    public void addHandUpgrade(){
        UpgradeHandCount++;
    }
    public void addDiscardUpgrade(){
        UpgradeDiscardCount++;
    }

    public AlgoArrayList<Card> getCards(){
        return cards;
    }

    public void addCredits(float credits){
        this.credits += credits;
    }

    public float getCredits(){
        return credits;
    }

    public boolean tryToBuy(int credits){
        if(this.credits >= credits){
            this.credits -= credits;
            return true;
        }
        return false;
    }

    public int getDiscardCounter() {
        return UpgradeDiscardCount;
    }

    public int getHandCounter() {
        return UpgradeHandCount;
    }

    public int getShopCounter() {
        return UpgradeShopCount;
    }
}
