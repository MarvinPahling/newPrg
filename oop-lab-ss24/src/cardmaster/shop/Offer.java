package cardmaster.shop;

public class Offer <T>{

    private T item;
    private int price;
    private ItemType type;

    public Offer(T item, int price, ItemType type){
        this.item = item;
        this.price = price;
        this.type = type;
    }

    public T getItem(){
        return item;
    }

    public int getPrice(){
        return price;
    }

    public ItemType getType(){
        return type;
    }

    public String toString(){
        return item.toString() + " for " + price + " credits";
    }
}
