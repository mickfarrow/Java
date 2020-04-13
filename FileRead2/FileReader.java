import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileReader {

    public static Item[] itemsFromFile(String fileName) {
        
        List<Item> items = new ArrayList<Item>();

        try {

            File myObj = new File(fileName);

            Scanner fileScanner = new Scanner(myObj);

            while (fileScanner.hasNextLine()) {
            
                String line = fileScanner.nextLine();
                Item myItem = GetItemFromString(line);

                if(myItem != null){
                    items.add(myItem);
                }
            }

            fileScanner.close();
        } 
        catch (Exception e) {
            //force error by changing above line to String inputFile = "doesnotexist.txt";
            System.out.println("Error: " + e.getMessage());
        }
        
        Item[] itemArray = new Item[items.size()];
        int index = 0;
        
        for (Item item : items) {
            itemArray[index] = item;
            index++;
        } 

        return itemArray;
    }

    private static Item GetItemFromString(String line){

        String[] y = line.split(";");

        if(y.length != 2) return null;

        try{
            Double price = Double.parseDouble(y[0]);
            String description = y[1];
            return new Item(price, description);
        }
        catch(NumberFormatException e){
            return null;
        }
        catch(Exception e){
            return null;
        }
                
    }
}