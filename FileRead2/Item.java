
public class Item {
    
    private double price;
    private String description;

    public double getPrice(){
        return price;
    }

    public String getDescription(){
        return description;
    }

    public Item(double price, String description){
        this.price = price;
        this.description = description;
    }
}
