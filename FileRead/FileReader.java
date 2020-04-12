
/*
Like most programming languages there is built in functionality in java
To use the built in functionality you import the files into your code
This is done at the top of the page
import required java classes
*/
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//declare class
class FileReader {
    //program entry point
    public static void main(String[] args) {
        
        //string variable containing filename
        String inputFile = "inputFile1.txt";

        //Create ArrayList (java.util) to hold results
        ArrayList<String> lines = new ArrayList<String>();

        /*
        use try...catch block to fill out the ArrayList
        try {
        }
        catch(Exception e){
        }
        
        You place the code inside the try block, if an error happens an Exception is thrown and this is caught and handled by the catch block
        The catch block receives the Exception as a parameter and you can read it, and determine what to do.
        */
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
        catch (Exception e) {
            //force error by changing above line to String inputFile = "doesnotexist.txt";
            System.out.println("Error: " + e.getMessage());
            //A stack trace is a detailed account of the error, can be very useful in determining the cause of the error
            e.printStackTrace();
            //because we have handled the error, the program will continue, if we didn't have a try..catch block the program would fail
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