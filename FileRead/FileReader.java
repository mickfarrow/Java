
//import required java classes
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//declare class
class FileReader {
    //program entry point
    public static void main(String[] args) {
        
        //string variable containing filename
        String inputFile = "inputFile.txt";

        //Create ArrayList (java.util) to hold results
        ArrayList<String> lines = new ArrayList<String>();

        //use try...catch block to fill out the ArrayList
        try {

            //create file object using inputFile variable (this is why you import java.io.File;)
            File myObj = new File(inputFile);

            //use the java.util.Scanner to create a myReader object
            Scanner fileScanner = new Scanner(myObj);

            //This loops over the lines by identifying if there is a next line
            //when you create a Scanner, it does not yet point to a line of text, so we check if there is a next line
            while (fileScanner.hasNextLine()) {
            
                //now we declare a variable to be the result of the nextLine function of the fileScanner object
                String line = fileScanner.nextLine();
                //add the line to the ArrayList
                lines.add(line);

                /*
                You could do it like this too!
                lines.add(fileScanner.nextLine());
                */
            }

            //Your program has the file open, you must release it
            fileScanner.close();
        } 
        catch (FileNotFoundException e) {
            //force error by changing above line to String inputFile = "doesnotexist.txt";
            System.out.println("Error: " + e.getMessage());
            //A stack trace is a detailed account of the error, very useful if the error is
            //e.printStackTrace();
        }

        //write out the array, then write each line out

        if(!lines.isEmpty())
        {
            System.out.println(lines);

            for (String line : lines) {
                System.out.println(line);
            }
        }
        else{
            System.out.println("The ArrayList is empty");
        }
    }
}