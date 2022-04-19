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
        //Calc number of entries (records) and set to int XYZ
        System.out.println("Welcome to the JRC Covid-19 Analaysis Program.\n" + "A total of 'XYZ' records have been loaded.\n");

        String[] menu1 = new String[10];
        menu1[0] = "All countries";
        menu1[1] = "Countries in South America";
        menu1[2] = "Countries in North America";
        menu1[3] = "Countries in Oceania";
        menu1[4] = "Countries in Asia";
        menu1[5] = "Countries in Africa";
        menu1[6] = "Counties in Europe";
        menu1[7] = "Enter a country";
        menu1[8] = "Enter a date";
        menu1[9] = "Exit";

        String[] menu2 = new String[8];
        menu2[0] = "Total number of cumulatively positive cases";
        menu2[1] = "Total number of cumulatively deceased cases";
        menu2[2] = "Total number of cumulatively recovered cases";
        menu2[3] = "Average daily number of currently positive cases";
        menu2[4] = "Number and percentage of cumulatively positive cases recovered";
        menu2[5] = "Number and percentage of cumulatively positive cases deceased";
        menu2[6] = "All of the above statistics";
        menu2[7] = "Exit";

        boolean run = true;
        int p;
    
        try{
            do {
                p = outputMenu(sc, menu1); 
                if(p == 1) {
                    //All Countries

                } else if(p == 2) {
                    //Countries in South America

                } else if (p == 3) {
                    //Countries in North America

                } else if(p == 4 ) {
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
                    
                } else if(p == 10) {
                    //Exit Code
                    System.out.println("Goodbye.");

                    run = false; //(used in the do-while loop)
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
            System.out.println(i +1 + " >  " + menu[i]);
        }
        do {
            System.out.print("\nEnter selection: ");
            a = inputInt(sc);
        } while (a < 0 && menu.length < a);
        //While the input is outwith the menu array's length
        return a;
    }

    public static void name() {
        
    }

    /*
    Method: dataEntryLoop
    Import: sc (scanner), output (string), array (1D integer array)
    Export: void
    Note: DOES NOT HANDLE ERRORS
    */
    /*
    public static void dataEntryLoop(Scanner sc, String output, int[] array) {
        outputString(output);
        int a;
        for (int e = 0; e < array.length; e++) {
        //Loop through all elements in an array
            outputString("Episode " + (e + 1) + ": ");
            //Output a readable request for view numbers for the current episode
                a = inputInt(sc);
                if (a >= 0 && a < 100000) {
                //0 =< a < 100 000
                    a = 1;
                } else if (a >= 100000) {
                //If a >= 100 000
                    //DO NOTHING
                } else {
                //If above conditions are not met
                    outputString("This system does not allow input values of less than 0, please try again: ");
                    //Output a re-try request, including reason
                    a = inputInt(sc);
                }
            array[e] = a;
        } 
        outputString("__________________________________________________________");
        //Output a line to visually separate data entry loops
    }*/

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