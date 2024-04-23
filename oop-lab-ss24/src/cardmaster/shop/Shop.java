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

    private AlgoArrayList<Offer> offers = new AlgoArrayList<>();
    private int maxCards;
    private Inventory inventory;
    private CardFactory factory = CardFactory.getDefaultFactory();
    private boolean hoeppnefsShittyFirstSeriesGriefesEverything;

    public Shop(Inventory inventory, CardFactory cardFactory){
        this.inventory = inventory;
        this.maxCards = inventory.getMaxShopCards();
        hoeppnefsShittyFirstSeriesGriefesEverything = false;
        generateRandomOffers();
        System.out.println(this.offers);

    }
    public Shop(Inventory inventory){
        this.inventory = inventory;
        this.maxCards = inventory.getMaxShopCards();
        hoeppnefsShittyFirstSeriesGriefesEverything = true;
        generateChanceOffers();
    }

    public void generateRandomOffers() {
        int[] prices = calculatePrices(inventory.getCredits());
        int numberOfUpgrades = ShopUpgrade.values().length;
        int numberOfCards = maxCards;
        for (int i = 0; i < numberOfUpgrades; i++) {
            offers.add(new Offer(ShopUpgrade.values()[i], prices[i], ItemType.UPGRADE));
        }
        for (int i = numberOfUpgrades; i < maxCards + numberOfUpgrades; i++) {
            Offer offer = new Offer(factory.createRandom(), prices[i], ItemType.CARD);
            System.out.println(offer);
            offers.add(offer);
        }
    }

    private void generateChanceOffers() {
        int[] prices = calculatePrices(inventory.getCredits());
        int numberOfUpgrades = ShopUpgrade.values().length;
        int numberOfCards = maxCards;
        for (int i = 0; i < numberOfUpgrades; i++) {
            offers.add(new Offer(ShopUpgrade.values()[i], prices[i], ItemType.UPGRADE));
        }
        for (int i = numberOfUpgrades; i < maxCards + numberOfUpgrades; i++) {
            offers.add(new Offer((new ChanceCard(Shape.getRandomShape())), prices[i], ItemType.CARD));
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

    public boolean isHoeppnefsShittyFirstSeriesGriefesEverything() {
        return hoeppnefsShittyFirstSeriesGriefesEverything;
    }
}
