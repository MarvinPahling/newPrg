package cardmaster.shop;

import cardmaster.Inventory;

public enum ShopUpgrade{
    DPLUS("Zusätzlicher Ablagestapel"){
        @Override
        public int getPrice(Inventory inventory) {
            int discardCounter = inventory.getDiscardCounter();
            return discardCounter * discardCounter + 1;
        }

        @Override
        public void applyUpgrade(Inventory inventory) {
            inventory.addDiscardUpgrade();
        }
    },
    HPLUS("Zusätzliche Handkarte"){
        @Override
        public int getPrice(Inventory inventory) {
            int handCounter = inventory.getHandCounter();
            return handCounter * handCounter + 1;
        }

        @Override
        public void applyUpgrade(Inventory inventory) {
            inventory.addHandUpgrade();
        }
    },
    SPLUS("Weitere Karte im Shop"){
        @Override
        public int getPrice(Inventory inventory) {
            int shopCounter = inventory.getShopCounter();
            return shopCounter * shopCounter + 1;
        }

        @Override
        public void applyUpgrade(Inventory inventory) {
            inventory.addShopUpgrade();
        }
    };

    private final String name;

    ShopUpgrade(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    //KEINE TOSTRING, WEIL WIR AHNUNG VON ENUMS HABEN

    public abstract int getPrice(Inventory inventory);
    public abstract void applyUpgrade(Inventory inventory);
}
