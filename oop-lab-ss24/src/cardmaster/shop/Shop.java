package cardmaster.shop;

import cardmaster.CardFactory;
import cardmaster.CardType;
import cardmaster.Inventory;
import cardmaster.Shape;
import cardmaster.cards.Card;
import cardmaster.cards.ChanceCard;
import cardmaster.collections.AlgoArrayList;

import java.util.Random;


public class Shop {

    private final AlgoArrayList<Offer> offers = new AlgoArrayList<>();
    private final int maxCards;
    private final Inventory inventory;
    private final CardFactory factory;


    public Shop(Inventory inventory, CardFactory cardFactory){
        this.factory = cardFactory;
        this.inventory = inventory;
        this.maxCards = inventory.getMaxShopCards();
        generateRandomOffers();

    }

    public void generateRandomOffers() {
        int[] prices = calculatePrices(inventory.getCredits());
        int numberOfUpgrades = ShopUpgrade.values().length;
        int numberOfCards = maxCards;
        for (int i = 0; i < numberOfUpgrades; i++) {
            offers.add(new Offer<ShopUpgrade>(ShopUpgrade.values()[i], prices[i], ItemType.UPGRADE));
        }
        for (int i = numberOfUpgrades; i < maxCards + numberOfUpgrades; i++) {
            Offer<Card> offer = new Offer<Card>(factory.createRandom(), prices[i], ItemType.CARD);
            System.out.println(offer.getItem().getName());
            offers.add(offer);
        }
    }

    public boolean isEmpty() {
        return offers.isEmpty();
    }

    public int getOfferCount() {
        return offers.getSize();
    }

    public String getDescription(int shopItemIndex) {
        return offers.get(shopItemIndex).toString();
    }

    public int getPrice(int shopItemIndex) {
        return offers.get(shopItemIndex).getPrice();
    }

    public int[] calculatePrices(float credits){
        Random rand = new Random();
        int numberOfUpgrades = ShopUpgrade.values().length;
        int[] prices = new int[maxCards + numberOfUpgrades];

        for (int i = 0; i < numberOfUpgrades; i++) {
            prices[i] = ShopUpgrade.values()[i].getPrice(inventory);
        }


        float mean = credits/4;
        for (int i = numberOfUpgrades; i < prices.length; i++) {
            prices[i] = Math.round(mean * ((rand.nextFloat(0.8f, 1.2f))));
            prices[i] += rand.nextInt(-1, 2);
            prices[i] = prices[i] <= 0 ? 1 : prices[i];
        }

        return prices;
    }



    public boolean buy(int shopItemIndex) {

        // check if the item is an upgrade
        Offer offer = offers.get(shopItemIndex);
        // check if the player has enough credits
        if(inventory.tryToBuy(offer.getPrice())){
            if(offer.getType() == ItemType.CARD){
                inventory.addCard((Card) offer.getItem());
            } else {
                ShopUpgrade upgrade = (ShopUpgrade) offer.getItem();
                upgrade.applyUpgrade(inventory);
            }
            offers.remove(shopItemIndex);
            return true;

        }
        return false;
        //
    }

}
