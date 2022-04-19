/*  
Title: MenuProgram
Author: Harrison D. Miles
Created: 18/04/2022
Modified: 19/04/2022
Description: Program that takes user input of viewing values of Netflix TV shows,
 and performs algorhythms to determine certain outputs via a readable menu.
*/

//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.*;

public class MenuProgram {
//(Netflix program analyser)

    public static void main(String[] args) /*throws FileNotFoundException*/ {
        //TODO find NoSuchElementException
    /*
    Scanner csvScanner = new Scanner(new File("C:\\Users\\Ducky\\OneDrive\\Documents\\Repositories\\PDI Assignment\\jrc-covid-19-all-days-of-world_ASSIGNMENT.csv"));
    //parsing a CSV file into the constructor of Scanner class 
    csvScanner.useDelimiter(",");
    //setting comma as delimiter pattern
    int c = 0;
    while (csvScanner.hasNext()) {
        if (csvScanner.next() != "") {
            System.out.println(csvScanner.next());
            c = c+1;
        }
    }
    System.out.println(c);
    csvScanner.close();
    //closes the scanner  
    */
        Scanner sc = new Scanner(System.in);
        /*Instantiate the Scanner system.in under the name 'sc'
        A scanner system.in function is how the program accepts user input*/

        //TODO data entry
        CovidRecord[] testCovidRecordArray = new CovidRecord[1784];
        for (int i = 0; i < testCovidRecordArray.length; i++) {
            testCovidRecordArray[i] = new CovidRecord();
        }
        for (int i = 0; i < testCovidRecordArray.length; i++) {
            System.out.println(testCovidRecordArray[i].toString() + "\n");
        }
        //Calc number of entries (records) and set to int XYZ
        System.out.println("Welcome to the JRC Covid-19 Analaysis Program.\n" + "A total of " + testCovidRecordArray.length + " records have been loaded.\n");

        String[] menu1 = new String[10];
        menu1[0] = "Exit";
        menu1[1] = "All countries";
        menu1[2] = "Countries in South America";
        menu1[3] = "Countries in North America";
        menu1[4] = "Countries in Oceania";
        menu1[5] = "Countries in Asia";
        menu1[6] = "Countries in Africa";
        menu1[7] = "Counties in Europe";
        menu1[8] = "Enter a country";
        menu1[9] = "Enter a date";

        String[] menu2 = new String[8];
        menu2[0] = "Exit";
        menu2[1] = "Total number of cumulatively positive cases";
        menu2[2] = "Total number of cumulatively deceased cases";
        menu2[3] = "Total number of cumulatively recovered cases";
        menu2[4] = "Average daily number of currently positive cases";
        menu2[5] = "Number and percentage of cumulatively positive cases recovered";
        menu2[6] = "Number and percentage of cumulatively positive cases deceased";
        menu2[7] = "All of the above statistics";

        boolean run = true;
        int p, a;
    
        try{
            do {
                p = outputMenu(sc, menu1); 
                if(p == 0) {
                    //Exit Code
                    System.out.println("Goodbye.");

                    run = false; //(used in the do-while loop)
                } else if(p == 1) {
                    //All Countries
                    a = outputMenu(sc, menu2);
                    displayStatistic(a, testCovidRecordArray);
                } else if (p == 2) {
                    //Countries in South America

                } else if(p == 3) {
                    //Countries in North America

                } else if(p == 4) {
                    //Countries in Oceania

                } else if(p == 5) {
                    //Countries in Asia

                } else if(p == 6) {
                    //Countries in Africa
                    
                } else if(p == 7) {
                    //Countries in Europe
                    
                } else if(p == 8) {
                    //Enter a country
                    
                } else if(p == 9) {
                    //Enter a date
                    
                } else {
                    System.out.println("Input is an invalid menu ID, please try again: \n");
                    //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
                }
            } while (run);
        } catch (InputMismatchException error) {
            //Catch error of type InputMismatchException and name it 'error'
            System.out.println("Incorrect input type, please restart.\nError: " + error);
        }
        //Close the scanner (important to only do this once)
        sc.close();
    }

    /*
    Method: inputInt
    Import: sc (scanner)
    Export: a (integer)
    */
    public static int inputInt(Scanner sc) {
        int a;
        a = sc.nextInt();
        return a;
    }

    /*
    Method: inputDouble
    Import: sc (scanner)
    Export: a (double)
    */
    public static double inputDouble(Scanner sc) {
        double a;
        a = sc.nextDouble();
        return a;
    }

    public static int outputMenu(Scanner sc, String[] menu) {
        int a = 0;
        System.out.println("Please select a option from below:\n");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + " >  " + menu[i]);
        }
        do {
            System.out.print("\nEnter selection: ");
            a = inputInt(sc);
        } while (a < 0 && menu.length < a);
        //While the input is outwith the menu array's length
        return a;
    }

    public static void displayStatistic(int p, CovidRecord[] testCovidRecordArray) {
        if (p == 1) {
                        
        } else {
            
        }
    }

    /*
    Method: averageOfArray
    Import: array (1D integer array)
    Export: b (double)
    */
    public static double averageOfArray(int[] array) {
        int a = 0;
        double b = 0.0;

        a = calcTotal(array);
        b = (double)(a / (array.length));
        //set 'b' to mean of array and cast to a double
        return b;
    }

    /*
    Method: identifyMaxValueOfArray
    Import: array (1D integer array)
    Export: b (integer)
    Note: DOES NOT ACCOUNT FOR EQUAL VALUES (Explained above in 'main')
    */
    public static int identifyMaxValueOfArray(int[] array) {
        int a = 0, b = 0;
        for (int i = 0; i < array.length; i++) {
        //Loop through all elements of the array
            if (array[i] > a) {
            //If the current element is greater than 'a'
                a = array[i];
                //Set 'a' to current element
                b = i;
                //Set 'b' to the identifier
            }
        }
        return b;
        //Return the identifier
    }

    /*
    Method: calcTotal
    Import: array (1D integer array)
    Export: a (integer)
    */
    public static int calcTotal(int[] array) {
        int a = 0;
        for (int i = 0; i < array.length; i++) {
        //Loop through all elements of the array
            a = a + array[i];
            //Add each element to 'a' creating a total value
        }
        return a;
    }
}