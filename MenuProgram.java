/*  
Title: MenuProgram
Author: Harrison D. Miles
Created: 18/04/2022
Modified: 19/04/2022
Description: 
*/

//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.*;

public class MenuProgram {
//(Joint Research Centre Covid-19 Menu Program)

    public static void main(String[] args) /*throws FileNotFoundException*/ {
        //TODO find NoSuchElementException / finish .csv info import
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
        int csvLength = 1784;
        CovidRecord[] covidRecordArray = new CovidRecord[csvLength];
        for (int i = 0; i < covidRecordArray.length; i++) {
            covidRecordArray[i] = new CovidRecord();
        }
        //for (int i = 0; i < covidRecordArray.length; i++) {System.out.println(covidRecordArray[i].toString() + "\n");} 
        //OUTPUT COVID RECORD ARRAY FOR TESTING PURPOSES

        System.out.println("Welcome to the JRC Covid-19 Analaysis Program.\n" + "A total of " + covidRecordArray.length + " records have been loaded.\n");

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
                //TODO finish filter menu
                if(p == 0) {
                    //Exit Code

                    System.out.println("Goodbye.");
                    run = false; //(used in the do-while loop)

                } else if(p == 1) {
                    //All Countries

                    displayStatistic(sc, menu2, covidRecordArray, menu1, p);

                } else if (p == 2) {
                    //Countries in South America

                    a = searchCovRecObj(covidRecordArray, "SA"); //Set 'a' to the number of Covid Records in SA
                    CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                    filterArray = setCovRecFilObj(a, covidRecordArray, "SA"); //Set the vaules of the 'filterArray' to all Covid Records in SA

                    displayStatistic(sc, menu2, filterArray, menu1, p);

                } else if(p == 3) {
                    //Countries in North America

                    a = searchCovRecObj(covidRecordArray, "NA"); //Set 'a' to the number of Covid Records in NA
                    CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                    filterArray = setCovRecFilObj(a, covidRecordArray, "NA"); //Set the vaules of the 'filterArray' to all Covid Records in NA

                    displayStatistic(sc, menu2, filterArray, menu1, p);

                } else if(p == 4) {
                    //Countries in Oceania

                    a = searchCovRecObj(covidRecordArray, "OC"); //Set 'a' to the number of Covid Records in OC
                    CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                    filterArray = setCovRecFilObj(a, covidRecordArray, "OC"); //Set the vaules of the 'filterArray' to all Covid Records in OC

                    displayStatistic(sc, menu2, filterArray, menu1, p);

                } else if(p == 5) {
                    //Countries in Asia

                    a = searchCovRecObj(covidRecordArray, "AS"); //Set 'a' to the number of Covid Records in AS
                    CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                    filterArray = setCovRecFilObj(a, covidRecordArray, "AS"); //Set the vaules of the 'filterArray' to all Covid Records in AS

                    displayStatistic(sc, menu2, filterArray, menu1, p);

                } else if(p == 6) {
                    //Countries in Africa
                    
                    a = searchCovRecObj(covidRecordArray, "AF"); //Set 'a' to the number of Covid Records in AF
                    CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                    filterArray = setCovRecFilObj(a, covidRecordArray, "AF"); //Set the vaules of the 'filterArray' to all Covid Records in AF

                    displayStatistic(sc, menu2, filterArray, menu1, p);

                } else if(p == 7) {
                    //Countries in Europe
                    
                    a = searchCovRecObj(covidRecordArray, "EU"); //Set 'a' to the number of Covid Records in EU
                    CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                    filterArray = setCovRecFilObj(a, covidRecordArray, "EU"); //Set the vaules of the 'filterArray' to all Covid Records in EU

                    displayStatistic(sc, menu2, filterArray, menu1, p);

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

    /*
    Method: displayStatistic
    Import: sc (scanner), statMenuArray (String array), covidRecordArray (CovidRecord Array), mainMenu (String Array), mainMenuChoice (Integer)
    Export: void
    */
    public static void displayStatistic(Scanner sc, String[] statMenuArray, CovidRecord[] covidRecordArray, String[] mainMenu, int mainMenuChoice) {
        int p;
        boolean run = true;
        do {
            p = outputMenu(sc, statMenuArray);
            //TODO finish displayStatistic method
            if (p == 0) {
                //Exit Code
                run = false;
                System.out.println("Going Back...");
            } else if (p == 1) {
                //Total cumulative pos
                int[] cumPosArray;
                int a;

                cumPosArray = distObjToArray(covidRecordArray, 0);
                a = calcTotal(cumPosArray);

                System.out.println("Cumulative number of positive cases in " + mainMenu[mainMenuChoice] + ": " + a);
            } else if (p == 2) {
                //Total cumulative dec
                int[] cumDecArray;
                int a;
                
                cumDecArray = distObjToArray(covidRecordArray, 1);
                a = calcTotal(cumDecArray);

                System.out.println("Cumulative number of deceased people (as a result of COVID-19) in " + mainMenu[mainMenuChoice] + ": " + a);
            } else if (p == 3) {
                //Total cumulative rec
                int[] cumRecArray;
                int a;
                
                cumRecArray = distObjToArray(covidRecordArray, 2);
                a = calcTotal(cumRecArray);

                System.out.println("Cumulative number of recovered people (from COVID-19) in " + mainMenu[mainMenuChoice] + ": " + a);
            } else if (p == 4) {
                //Avg daily number of positive cases

            } else if (p == 5) {
                //Num and % of cumulative pos

            } else if (p == 6) {
                //Num and % of cumulative dec

            } else if (p == 7) {
                //All of the above

            } else {
                System.out.println("Input is an invalid menu ID, please try again: \n");
                //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
            }
        } while (run);
        
    }

    public static int[] distObjToArray(CovidRecord[] pCovRecArray, int pDistPref) { //Distinguish Object To Array
        int[] outputArray = new int[pCovRecArray.length];

        if (pDistPref == 0) { //Cumulative Positive
            for (int i = 0; i < outputArray.length; i++) {
                outputArray[i] = pCovRecArray[i].getCumulativePos();
            }
        } else if (pDistPref == 1) { //Cumulative Deceased
            for (int i = 0; i < outputArray.length; i++) {
                outputArray[i] = pCovRecArray[i].getCumulativeDec();
            }
        } else if (pDistPref == 2) { //Cumulative Recovered
            for (int i = 0; i < outputArray.length; i++) {
                outputArray[i] = pCovRecArray[i].getCumulativeRec();
            }
        }
        return outputArray;
    }

    public static int searchCovRecObj(CovidRecord[] pCovRecArr, String pFilter) {
        int a = 0;
        for (int i = 0; i < pCovRecArr.length; i++) {
            if (pCovRecArr[i].getContinent() == pFilter) {
                a = a + 1;
            }
        }
        return a;
    }

    public static CovidRecord[] setCovRecFilObj(int a, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[a];
        a = 0;
        for (int i = 0; i < pCovRecArr.length; i++) {
            if (pCovRecArr[i].getContinent() == pFilter) {
                filterArray[a] = pCovRecArr[i];
                a = a + 1;
            }
        }
        return filterArray;
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
    Method: outputMenu
    Import: sc (scanner), menu (String array)
    Export: a (integer)
    */
}