/*  
Title: TaskTwo
Author: Harrison D. Miles
Created: 05/04/2022
Modified: 06/04/2022
Description: Program that takes user input of viewing values of Netflix TV shows,
 and performs algorhythms to determine certain outputs via a readable menu.
*/

import java.util.*;
//Import all java utility functions

public class TaskTwo {
//Create a class named TaskTwo (Netflix program analyser)

    public static void main(String[] args) {
    //Create main method, return type of 'void'
        Scanner sc = new Scanner(System.in);
        /*Instantiate the Scanner system.in under the name 'sc'
        A scanner system.in function is how the program accepts user input*/

        String[] serNames = {"Drive to survive, season 4","Inventing Anna","Bridgerton, season 2"};
        //Create an array of strings, the strings being the names of all included series in this task
        int[] dtsS4 = new int[10];//dtsS4 = Drive to Survive, Season 4
        int[] invAnn = new int[9];//invAnn = Inventing Anna
        int[] bridS2 = new int[8];//bridS2 = Bridgerton, season 2
        //Create 3 arrays of integers, of the length of each series in episodes, to hold the number of views of each episode

        outputString("Welcome to the Netflix episode analyser, please input current viewer count data: ");
        //Output a readable welcome message

        dataEntryLoop(sc, "Please enter the view count per episode for " + serNames[0] + ": ", dtsS4);
        dataEntryLoop(sc, "Please enter the view count per episode for " + serNames[1] + ": ", invAnn);
        dataEntryLoop(sc, "Please enter the view count per episode for " + serNames[2] + ": ", bridS2);
        //Call data entry loop method for each array of episodes
        outputString("Data entry completed.");
        //Inform the user that the data entry has been completed

        int p, a, b, c, d;
        double x, y, z;
        String menu = "> 1. Display average view count for each series season.\n> 2. Display episode from all series with the highest view count.\n> 3. Display the most popular series.\n> 4. Display show with largest season finale audience with view count.\n> 5. Exit the program.";
        boolean run = true;
        //Instantiate variables for later use (below)
    
        try{
        //Begin a try-catch fucntion
            do {
            //Begin a do-while loop
                outputString(menu + "\nYour choice: ");
                //Output menu, and Readability elements to request user input
                p = inputInt(sc);
                //Accept users' input and set the value of p to the input

                if(p == 1) {
                //if the user selects option #1
                    //Display average view count for each series.
                    x = averageOfArray(dtsS4);
                    y = averageOfArray(invAnn);
                    z = averageOfArray(bridS2);
                    //Call averageOfArray method to set x, y, and z to a double value, of the average number of views between all episodes in a series

                    outputString("Drive to survive, season 4: " + x);
                    outputString("Inventing Anna: " + y);
                    outputString("Bridgerton, season 2: " + z);
                    //Output results in a readable format 
                        //(scientific notation not intended)

                } else if(p == 2) {
                //If the user selects option #2
                    //Display episode from series with the highest view count
                    a = identifyMaxValueOfArray(dtsS4);
                    b = identifyMaxValueOfArray(invAnn);
                    c = identifyMaxValueOfArray(bridS2);
                    //Call identifyMaxValueOfArray to set a, b, and c to an integer value, of the maximum viewer count within that array
                        //Method does not account for equal variables, as in the actual scenario it is highly unlikely that two episodes of the same series will have an exactly equal viewer count

                    outputString("Drive to survive, season 4's most viewed episode is " + (a + 1) + " with " + dtsS4[a] + " views.");
                    outputString("Inventing Anna's most viewed episode is " + (b + 1) + " with " + invAnn[b] + " views.");
                    outputString("Bridgerton, season 2's most viewed episode is " + (c + 1) + " with " + bridS2[c] + " views.");
                    //Output results in a readable format

                } else if (p == 3) {
                //If the user selects option #3
                    //Display the most popular series
                    int[] totals = new int[3];
                    totals[0] = calcTotal(dtsS4);
                    totals[1] = calcTotal(invAnn);
                    totals[2] = calcTotal(bridS2);
                    //Create a new array titled totals of lenth 3, and set each value to the total views of all episodes in a series, using the calcTotal method

                    d = identifyMaxValueOfArray(totals);
                    //Call identifyMaxValueOfArray method using the totals array and set 'd' to its identifier

                    outputString(serNames[d] + " has the highest popularity with " + totals[d] + " views.");
                    //Output the result in a readable format
                } else if(p == 4 ) {
                //If the user selects option #4
                    //Display show with the largest finale viewing
                    int[] finaleViews = new int[3];
                    finaleViews[0] = dtsS4[dtsS4.length - 1];
                    finaleViews[1] = invAnn[invAnn.length - 1];
                    finaleViews[2] = bridS2[bridS2.length - 1];
                    //Create a new array titled finaleViews of length 3, and set each value to the last value of each view-count array

                    a = identifyMaxValueOfArray(finaleViews);
                    //Call identifyMaxValueOfArray method using finaleViews array and set 'a' to its identifier

                    outputString(serNames[a] + " has the most popular finale episode with: " + finaleViews[a] + " views.");
                    //Output the result in a readable format

                } else if(p == 5) {
                //If the user selects option #5
                    outputString("Goodbye.");
                    //Output a goodbye message
                    run = false;
                    //Set the run boolean to false (used in the do-while loop)
                } else {
                    outputString("Input is an invalid menu ID, please try again: ");
                    //If the user inputs any integer that is not 1-5 (inclusive) ask the user to re-try, with reason
                }
            } while (run);
            //Loop over the menu until the user requests to exit or an error is caught
        } catch (InputMismatchException error) {
            //Catch error of type InputMismatchException and name it 'error'
            outputString("Incorrect input type, please restart.\nError: " + error);
            //Request the user restarts the program and output the reason
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
        //Instantiate 'a' as an integer
        a = sc.nextInt();
        //Set 'a' to user input of-type integer
        return a;
        //Return a
    }

    /*
    Method: inputDouble
    Import: sc (scanner)
    Export: a (double)
    */
    public static double inputDouble(Scanner sc) {
        double a;
        //Instantiate 'a' as a double
        a = sc.nextDouble();
        //Set 'a' to user input of-type double
        return a;
        //Return a
    }

    /*
    Method: outputString
    Import: x (string)
    Export: void
    */
    public static void outputString(String x) {
        System.out.println(x);
        //Print the inputted string on a new line
    }

    /*
    Method: dataEntryLoop
    Import: sc (scanner), output (string), array (1D integer array)
    Export: void
    Note: DOES NOT HANDLE ERRORS
    */
    public static void dataEntryLoop(Scanner sc, String output, int[] array) {
        outputString(output);
        //Output the 'output' parameter
        int a;
        //Instantiate integer 'a' for later use
        for (int e = 0; e < array.length; e++) {
        //Loop through all elements in an array
            outputString("Episode " + (e + 1) + ": ");
            //Output a readable request for view numbers for the current episode
                a = inputInt(sc);
                //Instantiate 'a' and set to the user input of-type integer
                if (a >= 0 && a < 100000) {
                //If 0 =< a < 100 000
                    a = 1;
                    //Set 'a' to 1
                } else if (a >= 100000) {
                //If a >= 100 000
                    //DO NOTHING
                } else {
                //If above conditions are not met
                    outputString("This system does not allow input values of less than 0, please try again: ");
                    //Output a re-try request, including reason
                    a = inputInt(sc);
                    //Intake new user input
                }
            array[e] = a;
            //Set array element (episode) value to 'a'
        } 
        outputString("__________________________________________________________");
        //Output a line to visually separate data entry loops
    }

    /*
    Method: averageOfArray
    Import: array (1D integer array)
    Export: b (double)
    */
    public static double averageOfArray(int[] array) {
        int a = 0;
        //Instantiate integer 'a' and set to 0
        double b = 0.0;
        //Instantiate double 'b' and set to 0.0

        a = calcTotal(array);
        //Call calcTotal with array and set result to 'a'
        b = (double)(a / (array.length));
        //set 'b' to 'a' divided by the length of the array (mean of array) and cast to a double
        return b;
        //Return b
    }

    /*
    Method: identifyMaxValueOfArray
    Import: array (1D integer array)
    Export: b (integer)
    Note: DOES NOT ACCOUNT FOR EQUAL VALUES (Explained above in 'main')
    */
    public static int identifyMaxValueOfArray(int[] array) {
        int a = 0, b = 0;
        //Instantiate integers a and b, and set both to 0
        for (int i = 0; i < array.length; i++) {
        //Loop through all elements of the array
            if (array[i] > a) {
            //If the current element is greater than 'a'
                a = array[i];
                //Set 'a' to current element
                b = i;
                //Set 'b' to identifier
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
        //Instantiate integer a, and set to 0
        for (int i = 0; i < array.length; i++) {
        //Loop through all elements of the array
            a = a + array[i];
            //Add each element to 'a' creating a total value
        }
        return a;
        //Return 'a'
    }
}