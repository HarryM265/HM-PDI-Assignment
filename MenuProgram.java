/*  
Title: MenuProgram
Author: Harrison D. Miles
Created: 18/04/2022
Modified: 13/05/2022
Description: A menu program used to analyse the joint research centre's covid information.
*/

import java.io.*;
import java.util.*;

public class MenuProgram {
    //(Joint Research Centre Covid-19 Menu Program)
    //TODO remove all testing code
    //TODO Comment important code
    //TODO Write all assertions

    public static void main(String[] args) {

        //Scanner(System.in) is used to intake user inputs
        Scanner sc = new Scanner(System.in);
        
        String fileName = "jrc-covid-19-all-days-of-world_ASSIGNMENT-FIXED.csv";
        int csvLength = findLengthOfCSV(fileName);
        CovidRecord[] covidRecordArray = new CovidRecord[csvLength];
        covidRecordArray = importFromCSV(fileName, covidRecordArray);

        //testCSVImportValues(covidRecordArray);//OUTPUT COVID RECORD ARRAY FOR TESTING PURPOSES

        /*A Welcome message that states the name of the organisation that owns the program 
        and a variable counter to detail the number of records loaded*/
        System.out.println("Welcome to the JRC Covid-19 Analaysis Program.\n" + "A total of " + (csvLength -1) + " records have been loaded.\n");

        String[] mainMenu = new String[10];
        mainMenu[0] = "Exit";
        mainMenu[1] = "All countries";
        mainMenu[2] = "South America";
        mainMenu[3] = "North America";
        mainMenu[4] = "Oceania";
        mainMenu[5] = "Asia";
        mainMenu[6] = "Africa";
        mainMenu[7] = "Europe";
        mainMenu[8] = "Enter a country";
        mainMenu[9] = "Enter a date";

        String[] statMenu = new String[8];        
        statMenu[0] = "Back";
        statMenu[1] = "Total number of cumulatively positive cases";
        statMenu[2] = "Total number of cumulatively deceased cases";
        statMenu[3] = "Total number of cumulatively recovered cases";
        statMenu[4] = "Average daily number of currently positive cases";
        statMenu[5] = "Number and percentage of cumulatively positive cases recovered";
        statMenu[6] = "Number and percentage of cumulatively positive cases deceased";
        statMenu[7] = "All of the above statistics";

        try{
            displayMainMenu(sc, mainMenu, statMenu, covidRecordArray);
        //Catch any inputs of the wrong type and restart the program
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect input type.\nRestarting...\n");
            //nullStringArr is an empty array used to call main
            String[] nullStringArr = new String[0];
            main(nullStringArr);
        }
        //Close the scanner (important to only do this once)
        sc.close();
    }

    //IMPORTANT TO REMOVE THIS
    public static void testCSVImportValues(CovidRecord[] pCovidRecordArray) {
        for (int i = 0; i < pCovidRecordArray.length; i++) {
            String covidRecordString;
            try {
                covidRecordString = pCovidRecordArray[i].toString();
            } catch (NullPointerException e) {
                covidRecordString = "NullPointerException caught (" + e.getMessage() + ") as CovidRecord[" + i + "]" + " is null.";
            }
            System.out.println(covidRecordString + "\n");
        } 
    }

    /*
    Method: outputMenu
    Import: sc (scanner), pMenu (String array)
    Export: userInput (integer)
    */
    public static int outputMenu(Scanner sc, String[] pMenu) {
        int userInput = 0, menuLength = pMenu.length;
        System.out.println("Please select a option from below:\n");
        for (int i = 0; i < menuLength; i++) {
            System.out.println(i + " >  " + pMenu[i]);
        }
        do {
            System.out.print("\nEnter selection: ");
            userInput = inputInt(sc);
            //While the user's input is outwith the menu array's length
        } while (userInput < 0 && menuLength < userInput);
        return userInput;
    }

    /*
    Method: displayMainMenu
    Import: sc (Scanner), pMainMenu (String array), pStatMenu (String array), pCovidRecordArray (CovidRecord array)
    Export: nothing
    */
    public static void displayMainMenu(Scanner sc, String[] pMainMenu, String[] pStatMenu, CovidRecord[] pCovidRecordArray) {
        //numCovRecMatchFilter is number of covid records that match the given filter
        int menuChoice, numCovRecMatchFilter;
        boolean run = true;
        CovidRecord[] filterArray;

        do {
            menuChoice = outputMenu(sc, pMainMenu); 
            if(menuChoice == 0) {
                //Exit Code

                System.out.println("Goodbye.");
                run = false; //(used in the do-while loop)

            } else if(menuChoice == 1) {
                //All Countries

                displayStatistic(sc, pStatMenu, pCovidRecordArray, pMainMenu, menuChoice);

            } else if (menuChoice == 2) {
                //Countries in South America

                //Set 'numCovRecMatchFilter' to the number of Covid Records in SA
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "SA");
                //Make a new 'filterArray' with a length of 'numCovRecMatchFilter'
                filterArray = new CovidRecord[numCovRecMatchFilter]; 
                //Set the vaules of the 'filterArray' to all Covid Records in SA
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "SA");

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 3) {
                //Countries in North America

                //Set 'numCovRecMatchFilter' to the number of Covid Records in NA
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "NA");
                //Make a new 'filterArray' with a length of 'numCovRecMatchFilter'
                filterArray = new CovidRecord[numCovRecMatchFilter]; 
                //Set the vaules of the 'filterArray' to all Covid Records in NA
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "NA"); 

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 4) {
                //Countries in Oceania

                //Set 'numCovRecMatchFilter' to the number of Covid Records in OC
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "OC"); 
                //Make a new 'filterArray' with a length of 'numCovRecMatchFilter'
                filterArray = new CovidRecord[numCovRecMatchFilter]; 
                //Set the vaules of the 'filterArray' to all Covid Records in OC
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "OC"); 

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 5) {
                //Countries in Asia

                //Set 'numCovRecMatchFilter' to the number of Covid Records in AS
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "AS"); 
                //Make a new 'filterArray' with a length of 'numCovRecMatchFilter'
                filterArray = new CovidRecord[numCovRecMatchFilter]; 
                //Set the vaules of the 'filterArray' to all Covid Records in AS
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "AS"); 

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 6) {
                //Countries in Africa
                
                //Set 'numCovRecMatchFilter' to the number of Covid Records in AF
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "AF"); 
                //Make a new 'filterArray' with a length of 'numCovRecMatchFilter'
                filterArray = new CovidRecord[numCovRecMatchFilter]; 
                //Set the vaules of the 'filterArray' to all Covid Records in AF
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "AF"); 

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 7) {
                //Countries in Europe
                
                //Set 'numCovRecMatchFilter' to the number of Covid Records in EU
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "EU"); 
                //Make a new 'filterArray' with a length of 'numCovRecMatchFilter'
                filterArray = new CovidRecord[numCovRecMatchFilter]; 
                //Set the vaules of the 'filterArray' to all Covid Records in EU
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "EU"); 

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 8) {
                //Enter a country
                
                //Take a user's country String input and name it 'input'
                String input;
                System.out.print("Please enter the name of the country you would like to analyse: ");
                input = inputString(sc);

                //Find the number of covid records that match 'input'
                numCovRecMatchFilter = searchCovRecObjCountry(pCovidRecordArray, input);

                //While the number of covid records that match the given filter is less than or equal to 0
                while (numCovRecMatchFilter <= 0) {
                    //Ask the user to re-input the String and find the number of covid records that match 'input'
                    System.out.print("Your input does not match any country name in the database, try again, remember to use capital letters: ");
                    input = inputString(sc);
                    numCovRecMatchFilter = searchCovRecObjCountry(pCovidRecordArray, input);
                }

                //set 'filterArray' length to the found number of covid records that match the filter
                filterArray = new CovidRecord[numCovRecMatchFilter];
                //Set the filter array to all records that match the filter
                filterArray = setCovRecFilObjCountry(numCovRecMatchFilter, pCovidRecordArray, input);

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 9) {
                //Enter a date
                
                //Take a user's country String input and name it 'input'
                String input;
                System.out.print("Please enter the date you would like to analyse: ");
                input = inputString(sc);

                //Find the number of covid records that match 'input'
                numCovRecMatchFilter = searchCovRecObjDate(pCovidRecordArray, input);

                //While the number of covid records that match the given filter is less than or equal to 0
                while (numCovRecMatchFilter <= 0) {
                    //Ask the user to re-input the String and find the number of covid records that match 'input'
                    System.out.print("Your input does not match any date in the database, try again using this format '12/2/2022': ");
                    input = inputString(sc);
                    numCovRecMatchFilter = searchCovRecObjDate(pCovidRecordArray, input);
                }

                //set 'filterArray' length to the found number of covid records that match the filter
                filterArray = new CovidRecord[numCovRecMatchFilter];
                //Set the filter array to all records that match the filter
                filterArray = setCovRecFilObjDate(numCovRecMatchFilter, pCovidRecordArray, input);

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else {
                System.out.println(menuChoice + " is an invalid menu ID, please try again: \n");
                //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
            }
        //Loop while the run variable is 'true'
        } while (run);
    }

    /*
    Method: displayStatistic
    Import: sc (scanner), pStatMenu (String array), pCovidRecordArray (CovidRecord Array), pMainMenu (String Array), pMainMenuChoice (Integer)
    Export: void
    */
    public static void displayStatistic(Scanner sc, String[] pStatMenu, CovidRecord[] pCovidRecordArray, String[] pMainMenu, int pMainMenuChoice) {
        int menuChoice;
        boolean run = true; //Used in the do-while loop
        do {
            //Output the statMenu and take the user's input
            menuChoice = outputMenu(sc, pStatMenu);
            if (menuChoice == 0) {
                //Exit statMenu to mainMenu
                run = false; //Used in the do-while loop
                //Output a message for the user to understand what is happening
                System.out.println("Going Back...");
            } else if (menuChoice == 1) {
                //Total cumulative pos
                printTotalCumPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else if (menuChoice == 2) {
                //Total cumulative dec
                printTotalCumDec(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else if (menuChoice == 3) {
                //Total cumulative rec
                printTotalCumRec(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else if (menuChoice == 4) {
                //Avg daily number of positive cases
                printAvgDailyCurrPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else if (menuChoice == 5) {
                //Num and % of cumulative pos cases rec
                printPercentRecOverPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else if (menuChoice == 6) {
                //Num and % of cumulative pos cases dec
                printPercentDecOverPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else if (menuChoice == 7) {
                //Print all of the above
                printTotalCumPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printTotalCumDec(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printTotalCumRec(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printAvgDailyCurrPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printPercentRecOverPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printPercentDecOverPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else {
                System.out.println("Input is an invalid menu ID, please try again: \n");
                //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
            }
        //Loop while 'run' is 'true'
        } while (run);   
    }

    /*
    Method: printTotalCumPos
    Import: pCovidRecordArray (CovidRecord array), pMainMenuChoice (int), pMainMenu (String array)
    Export: nothing
    */
    public static void printTotalCumPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray;
        int totalCumPos;
        String readableOutput, out; 

        //Distinguish pCovidRecordArray to an array of only the cumulative positive cases
        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        totalCumPos = calcTotal(cumPosArray);

        //Output a readable string detailing the given statistic
        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of positive cases in " + readableOutput + ": " + totalCumPos;
        System.out.println(out);
    }

    /*
    Method: printTotalCumDec
    Import: pCovidRecordArray (CovidRecord array), pMainMenuChoice (int), pMainMenu (String array)
    Export: nothing
    */
    public static void printTotalCumDec(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumDecArray;
        int totalCumDec;
        String out, readableOutput;
                
        //Distinguish pCovidRecordArray to an array of only cumulative deceased
        cumDecArray = distCovRecToCumDec(pCovidRecordArray);
        totalCumDec = calcTotal(cumDecArray);

        //Output a readable string detailing the given statistic
        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of deceased people (as a result of COVID-19) in " + readableOutput + ": " + totalCumDec + ".";
        System.out.println(out);
    }

    /*
    Method: printTotalCumRec
    Import: pCovidRecordArray (CovidRecord array), pMainMenuChoice (int), pMainMenu (String array)
    Export: nothing
    */
    public static void printTotalCumRec(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumRecArray;
        int totalCumRec;
        String out, readableOutput;

        //Distinguish pCovidRecordArray to an array of only cumulative recovered
        cumRecArray = distCovRecToCumRec(pCovidRecordArray);
        totalCumRec = calcTotal(cumRecArray);

        //Output a readable string detailing the given statistic
        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of recovered people (from COVID-19) in " + readableOutput + ": " + totalCumRec + ".";
        System.out.println(out);
    }

    /*
    Method: printAvgDailyCurrPos (print the average daily number of currently positive cases)
    Import: pCovidRecordArray (CovidRecord array), pMainMenuChoice (int), pMainMenu (String array)
    Export: nothing
    Note: Assuming that the average is only approximate and all covid records aren't recorded on a leap year
    */
    public static void printAvgDailyCurrPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] currPosArray;
        int totalCurrPos, avgDailyCurrPos;
        double avgLengthOfMonth, dubAvgDailyCurrPos; //'dubAvgDailyCurrPos' is the double version of 'avgDailyCurrPos'
        String out, readableOutput;

        //Average length of a month (not including leap year)
        avgLengthOfMonth = ((31.0 * 6.0) + (30.0 * 5.0) + 28.0) / 12.0; 

        //Distinguish pCovidRecordArray to an array of only currently positive
        currPosArray = distCovRecToCurrPos(pCovidRecordArray);
        totalCurrPos = calcTotal(currPosArray);

        //Set 'avgDailyCurrPos' to the approximate average of daily currently positive cases
        dubAvgDailyCurrPos = (double)(totalCurrPos * avgLengthOfMonth) / 365.0;
        avgDailyCurrPos = (int)dubAvgDailyCurrPos;
        
        //Output a readable string detailing the given statistic
        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "The (approximate) average daily currently positive number of cases in " + readableOutput + " is: " + avgDailyCurrPos;
        System.out.println(out);
    }

    /*
    Method: printPercentRecOverPos
    Import: pCovidRecordArray (CovidRecord array), pMainMenuChoice (int), pMainMenu (String array)
    Export: nothing
    */
    public static void printPercentRecOverPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray, cumRecArray;
        //'percentRecOverPos' is cumulative recovered cases divided by cumulative positive cases as a percentage
        double cumPos, cumRec, percentRecOverPos; 
        String out, readableOutput;

        //Distinguish pCovidRecordArray to an array of only cumulative positive
        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        cumPos = (double)calcTotal(cumPosArray);

        //Distinguish pCovidRecordArray to an array of only cumulative recovered
        cumRecArray = distCovRecToCumRec(pCovidRecordArray);
        cumRec = (double)calcTotal(cumRecArray);

        //Divide the cumulative recovered cases by the cumulative positive and multiply by 100 
        percentRecOverPos = (cumRec/cumPos) * 100;
        //Round 'percentRecOverPos' to a whole number
        percentRecOverPos = Math.round(percentRecOverPos);

        //Output a readable string detailing the given statistic
        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = (int)percentRecOverPos + "% (" + (int)cumRec + "/" + (int)cumPos + ") cases recovered in " + readableOutput + ".";
        System.out.println(out);
    }

    /*
    Method: printPercentDecOverPos
    Import: pCovidRecordArray (CovidRecord array), pMainMenuChoice (int), pMainMenu (String array)
    Export: nothing
    */
    public static void printPercentDecOverPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray, cumDecArray;
        String out, readableOutput;
        //'percentDecOverPos' is cumulative deceased cases divided by cumulative positive cases as a percentage
        double cumPos, cumDec, percentDecOverPos;

        //Distinguish pCovidRecordArray to an array of only cumulative positive
        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        cumPos = (double)calcTotal(cumPosArray);

        //Distinguish pCovidRecordArray to an array of only cumulative deceased
        cumDecArray = distCovRecToCumDec(pCovidRecordArray);
        cumDec = (double)calcTotal(cumDecArray);

        //Divide the cumulative deceased cases by the cumulative positive and multiply by 100 
        percentDecOverPos = (cumDec/cumPos) * 100;
        //Round 'percentDecOverPos' to a whole number
        percentDecOverPos = Math.round(percentDecOverPos);

        //Output a readable string detailing the given statistic
        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = (int)percentDecOverPos + "% (" + (int)cumDec + "/" + (int)cumPos + ") cases deceased in " + readableOutput + ".";
        System.out.println(out);
    }

    /*
    Method: calcReadableString
    Import: pMenuChoice (int), pMenu (String array)
    Export: String
    Note: Used to make the statistic ouput remind the user of their choice on the main menu
    */
    public static String calcReadableString(int pMenuChoice, String[] pMenu) {
        String output = "";

        //If 'pMenuChoice' is between 1-7 (Inclusive)
        if (pMenuChoice == 1 || pMenuChoice == 2 || pMenuChoice == 3 || pMenuChoice == 4 || pMenuChoice == 5 || pMenuChoice == 6 || pMenuChoice == 7) {
            output = pMenu[pMenuChoice];
        //If 'pMenuChoice' equals 8
        } else if (pMenuChoice == 8) {
            output = "the entred country";
        //If 'pMenuChoice' equals 9
        } else if (pMenuChoice == 9) {
            output = "the entred date";
        }
        return output;
    }

    /*
    Method: distCovRecToCumPos (Distinguish between covid records to cumulative positive)
    Import: pCovRecArray (CovidRecords array)
    Export: integer array
    */
    public static int[] distCovRecToCumPos(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];
        int cumPos = 0;

        //From pCovRecArray[0] to the end, increment by 1
        for (int i = 0; i < inArrayLength; i++) {
            try {
                cumPos = pCovRecArray[i].getCumulativePos();
            //Catch any NullPointerException and do nothing with it
            } catch (NullPointerException e) {}
            outputArray[i] = cumPos;
        }
        return outputArray;
    }

    /*
    Method: distCovRecToCumDec (Distinguish Covid Records to Cumulative Deceased)
    Import: pCovRecArray (CovidRecord array)
    Export: outputArray (integer array)
    */
    public static int[] distCovRecToCumDec(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];

        //From covRecArray[0] to the end, increment by 1
        for (int i = 0; i < inArrayLength; i++) {
            int cumDec = 0;
            try {
                cumDec = pCovRecArray[i].getCumulativeDec();
            //Catch any NullPointerExceptions and do nothing with it
            } catch (NullPointerException e) {}
            outputArray[i] = cumDec;
        }
        return outputArray;
    }

    /*
    Method: distCovRecToCUmRec (Distinguish CovidRecord to Cumulative Recovered)
    Import: pCovRecArray (CovidRecord array)
    Export: outputArray (Integer array)
    */
    public static int[] distCovRecToCumRec(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];

        //Loop from pCovRecArray[0] to end, incrementing by 1
        for (int i = 0; i < inArrayLength; i++) {
            int cumRec = 0;
            try {
                cumRec = pCovRecArray[i].getCumulativeRec();
            //Catch any NullPointerExceptions and do nothing with it
            } catch (NullPointerException e) {}
            outputArray[i] = cumRec;
        }
        return outputArray;
    }

    /*
    Method: distCovRecToCurrPos (Distinguish CovidRecord to Currently Positive)
    Import: pCovRecArray (CovidRecord array)
    Export: outputArray (Integer array)
    */
    public static int[] distCovRecToCurrPos(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];

        //Loop from pCovRecArray[0] to end, incrementing by 1
        for (int i = 0; i < inArrayLength; i++) {
            int currPos = 0;
            try {
                currPos = pCovRecArray[i].getCurrentlyPos();
            //Catch any NullPointerExceptions and do nothing with it
            } catch (NullPointerException e) {}
                outputArray[i] = currPos;
        }
        return outputArray;
    }

    /*
    Method: searchCovRecObjContinent (Search CovidRecord array for number of matching Continents)
    Import: pCovRecArr (CovidRecord array), pFilter (String)
    Export: numContinentMatchFilter (int) (Number of continents that match the given filter)
    */
    public static int searchCovRecObjContinent(CovidRecord[] pCovRecArr, String pFilter) {
        int numContinentMatchFilter = 0;
        String currContinent;
        //Loop from pCovRecArr[0] to end, incrementing by 1
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currContinent = pCovRecArr[i].getContinent();
            //Catch any NullPointerExceptions and change the variable to blank
            } catch (NullPointerException e) {
                currContinent = "";
            }
            //If 'currContinent' matches 'pFilter'
            if (currContinent.equals(pFilter)) {
                numContinentMatchFilter = numContinentMatchFilter +1;
            }
        }
        return numContinentMatchFilter -1;
    }

    /*
    Method: searchCovRecObjCountry (Search CovidRecord array for number of matching Countries)
    Import: pCovRecArr (CovidRecord array), pFilter (string)
    Export: numCountryMatchFilter (int)
    */
    public static int searchCovRecObjCountry(CovidRecord[] pCovRecArr, String pFilter) {
        int numCountryMatchFilter = 0;
        String currCountryName;
        //Loop from pCovRecArr[0] to end, incrementing by 1
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currCountryName = pCovRecArr[i].getCountryName();
            //Catch any NullPointerExceptions and change the variable to blank
            } catch (NullPointerException e) {
                currCountryName = "";
            }
            //If 'currCountryName' matches 'pFilter'
            if (currCountryName.equals(pFilter)) {
                numCountryMatchFilter = numCountryMatchFilter + 1;
            }
        }
        return numCountryMatchFilter -1;
    }

    /*
    Method: searchCovRecObjDate (Search CovidRecord array for number of matching dates)
    Import: pCovRecArr (CovidRecord array), pFilter (String)
    Export: numDateMatchFilter (Number of dates that match the filter)
    */
    public static int searchCovRecObjDate(CovidRecord[] pCovRecArr, String pFilter) {
        int numDateMatchFilter = 0;
        String currDate;
        //Loop from pCovRecArr[0] to end, incrementing by 1
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currDate = pCovRecArr[i].getDate();
            //Catch any NullPointerExceptions and change the variable to blank
            } catch (NullPointerException e) {
                currDate = "";
            }
            //If 'currDate' matches 'pFilter'
            if (currDate.equals(pFilter)) {
                numDateMatchFilter = numDateMatchFilter +1;
            }
        }
        return numDateMatchFilter -1;
    }

    /*
    Method: setCovRecFilObjContinent (Filter CovidRecord array into new CovidRecord array with values matching filter)
    Import: pFilterArrayLength (int), pCovRecArr (CovidRecord array), pFilter (String)
    Export: filterArray (CovidRecord array)
    */
    public static CovidRecord[] setCovRecFilObjContinent(int pFilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pFilterArrayLength];
        int filterArrayInd = 0;
        String currContinent;
        //Loop from pCovRecArr[0] to end, incrementing by 1
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currContinent = pCovRecArr[i].getContinent();
            //Catch all NullPointerExceptions and set the value to blank
            } catch (NullPointerException e) {
                currContinent = "";
            }
            //If 'currContinent' equals 'pFilter'
            if (currContinent.equals(pFilter)) {
                try {
                    filterArray[filterArrayInd] = pCovRecArr[i];
                //Catch all ArrayIndexOutOfBoundsExceptions and do nothing
                } catch (ArrayIndexOutOfBoundsException e) {}
                //Increment the filter array index used by 1
                filterArrayInd = filterArrayInd +1;
            }
        }
        return (filterArray);
    }

    /*
    Method: setCovRecFilObjCountry (Filter CovidRecord array into new CovidRecord array with values matching filter)
    Import: pFilterArrayLength (int), pCovRecArr (CovidRecord array), pFilter (String)
    Export: filterArray (CovidRecord array)
    */
    public static CovidRecord[] setCovRecFilObjCountry(int pFilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pFilterArrayLength];
        int filterArrayInd = 0;
        String currCountryName;
        //Loop from pCovRecArr[0] to end, incrementing by 1
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currCountryName = pCovRecArr[i].getCountryName();
            //Catch any NullPointerExceptions and set currCountryName to ""
            } catch (NullPointerException e) {
                currCountryName = "";
            }
            //If 'currCountryName' equals 'pFilter'
            if (currCountryName.equals(pFilter)) {
                try {
                    filterArray[filterArrayInd] = pCovRecArr[i];
                //Catch any ArrayIndexOutOfBoundsExceptions and do nothing
                } catch (ArrayIndexOutOfBoundsException e) {}
                //Increment the filter array index used by 1
                filterArrayInd = filterArrayInd +1;
            }
        }
        return filterArray;
    }

    /*
    Method: setCovRecFilObjDate (Filter CovidRecord array into new CovidRecord array with values matching filter)
    Import: pFilterArrayLength (int), pCovRecArr (CovidRecord array), pFilter (String)
    Export: filterArray (CovidRecord array)
    */
    public static CovidRecord[] setCovRecFilObjDate(int pFilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pFilterArrayLength];
        int filterArrayInd = 0;
        String currDate;
        //Loop from pCovRecArr[0] to end, increment by 1
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currDate = pCovRecArr[i].getDate();
            //Catch any NullPointerExceptions and set 'currDate' to ""
            } catch (NullPointerException e) {
                currDate = "";
            }
            //If 'currDate' equals 'pFilter'
            if (currDate.equals(pFilter)) {
                try {
                    filterArray[filterArrayInd] = pCovRecArr[i];
                //Catch any ArrayIndexOutOfBoundsExceptions and do nothing with it
                } catch (ArrayIndexOutOfBoundsException e) {}
                //Increment filter array index used by 1
                filterArrayInd = filterArrayInd +1;
            }
        }
        return filterArray;
    }

    /*
    Method: findLengthOfCSV
    Import: pFileName (String)
    Export: lineNum (int)
    */
    public static int findLengthOfCSV(String pFileName) {
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        int lineNum = 0;
        String line;

        try {
            fileStream = new FileInputStream(pFileName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);
            lineNum = 0;
            line = bufRdr.readLine();

            //While the current line is not equal to null
            while (line != null) {
                //Increment 'lineNum'
                lineNum++;
                //Read the next line
                line = bufRdr.readLine();
            }
            fileStream.close();
        //Catch any IOExceptions and close the fileStream again
        } catch (IOException e) {
            //If fileStream is not null
            if (fileStream != null) {
                try {
                    fileStream.close();
                //Catch any IOExceptions and do nothing
                } catch (IOException e2){}
            }
            System.out.println("Error in fileProcessing: " + e.getMessage());
        }
        return lineNum;
    }

    /*
    Method: importFromCSV
    Import: pFileName (String), pCovidRecordArray (CovidRecord array)
    Export: pCovidRecordArray (CovidRecord Array)
    */
    public static CovidRecord[] importFromCSV(String pFileName, CovidRecord[] pCovidRecordArray) {

        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        int lineNum;
        String line;

        try {
            fileStream = new FileInputStream(pFileName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);
            lineNum = 0;
            line = bufRdr.readLine();

            //While the current line is not equal to null
            while (line != null) {
                pCovidRecordArray[lineNum] = lineToCovidRecord(line);
                //Increment the line number
                lineNum++;

                //'line' = the next line
                line = bufRdr.readLine();
            }
            fileStream.close();
        //Catch any IOExceptions and close the fileStream again
        } catch (IOException e) {
            //If fileStream is not null
            if (fileStream != null) {
                try {
                    fileStream.close();
                //catch another IOException and do nothing with it
                } catch (IOException e2){}
            }
            System.out.println("Error in fileProcessing: " + e.getMessage());
        }
        return pCovidRecordArray;
    }

    /*
    Method: processLine
    Import: pLine (String)
    Export: splitLine (String array)
    */
    public static String[] processLine(String pLine) {
        String[] splitLine;
        //Split the string for every ',' leaving the empty columns blank
        splitLine = pLine.split(",", -1);

        return splitLine;
    }

    /*
    Method: lineToCovidRecord
    Import: pLine (String)
    Export: covidRecord (CovidRecord)
    */
    public static CovidRecord lineToCovidRecord(String pLine) {
        int numColInCSV = 13; //Number of columns in the .csv
        String[] splitLine = new String[numColInCSV];
        //Process the string into a string array
        splitLine = processLine(pLine);
        
        CovidRecord covidRecord;

        String date, iso3, continent, countryName, nuts;
        double lat, lon;
        int cumPos, cumDec, cumRec, currPos, hospitalised, intensiveCare;

        date = splitLine[0];
        iso3 = splitLine[1];
        continent = splitLine[2];
        countryName = splitLine[3];
        nuts = splitLine[12];

        /*Try assigning the .csv variables to local variables, catching NumberFormatExceptions
        Mainly used to leave the 'header' blank*/
        try {
            lat = Double.parseDouble(splitLine[4]);
        } catch (NumberFormatException e) {
            lat = 0;
        }
        try {
            lon = Double.parseDouble(splitLine[5]);
        } catch (NumberFormatException e) {
            lon = 0;
        }
        try {
            cumPos = Integer.parseInt(splitLine[6]);
        } catch (NumberFormatException e) {
            cumPos = 0;
        }
        try {
            cumDec = Integer.parseInt(splitLine[7]);
        } catch (NumberFormatException e) {
            cumDec = 0;
        }
        try {
            cumRec = Integer.parseInt(splitLine[8]);
        } catch (NumberFormatException e) {
            cumRec = 0;
        }
        try {
            currPos = Integer.parseInt(splitLine[9]);
        } catch (NumberFormatException e) {
            currPos = 0;
        }
        try {
            hospitalised = Integer.parseInt(splitLine[10]);
        } catch (NumberFormatException e) {
            hospitalised = 0;
        }
        try {
            intensiveCare = Integer.parseInt(splitLine[11]);
        } catch (NumberFormatException e) {
            intensiveCare = 0;
        }

        covidRecord = new CovidRecord(date, iso3, continent, countryName, lat, lon, cumPos, cumDec, cumRec, currPos, hospitalised, intensiveCare, nuts);
        return covidRecord;
    }


    /*
    Method: averageOfArray
    Import: array (1D integer array)
    Export: b (double)
    */
    public static double averageOfArray(int[] array) {
        int total = 0;
        double avg = 0.0;

        total = calcTotal(array);
        avg = (double)(total / (array.length));
        //set 'b' to mean of array and cast to a double
        return avg;
    }

    /*
    Method: calcTotal
    Import: array (1D integer array)
    Export: a (integer)
    */
    public static int calcTotal(int[] array) {
        int total = 0;
        for (int i = 0; i < array.length; i++) {
            total = total + array[i];
        }
        return total;
    }

    /*
    Method: inputInt
    Import: sc (scanner)
    Export: a (integer)
    */
    public static int inputInt(Scanner sc) {
        int inpInt;
        inpInt = sc.nextInt();
        return inpInt;
    }

    /*
    Method: inputString
    Import: sc (Scanner)
    Export: a (String)
    */
    public static String inputString(Scanner sc) {
        String inpString;
        sc.nextLine(); //Used to 'swallow' the \n that the sc.nextInt(); could not
        inpString = sc.nextLine();

        return inpString;
    }
}