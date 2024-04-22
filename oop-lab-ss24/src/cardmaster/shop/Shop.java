package cardmaster.shop;

import cardmaster.Inventory;
import cardmaster.collections.AlgoArrayList;

public class Shop {

    private AlgoArrayList<Offer> offers = new AlgoArrayList<>();
    private int maxCards;
    private Inventory inventory;

    public Shop(Inventory inventory){
        this.inventory = inventory;
        this.maxCards = inventory.getMaxShopCards();
        this.offers = generateOffers();
    }

    private AlgoArrayList<Offer> generateOffers() {
        //TODO generate offers
        return null;
    }
}
