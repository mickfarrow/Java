public class Test {
    public static void main(String[] args) {
       
        Item[] items = FileReader.itemsFromFile("inputfile.txt");

        System.out.println("Vending Machine");
        System.out.println("---------------");

        for (Item item : items) {
            System.out.println(item.getDescription() + " - $" + String.format("%.2f", item.getPrice()));
        }
    }
}