/*  
Title: MenuProgram
Author: Harrison D. Miles
Created: 18/04/2022
Modified: 11/05/2022
Description: 
*/

//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class MenuProgram {
    //(Joint Research Centre Covid-19 Menu Program)
    //TODO remove all testing code
    //TODO Comment important code
    //TODO Write all assertions
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        /*Instantiate the Scanner system.in under the name 'sc'
        A scanner system.in function is how the program accepts user input*/
        String fileName = "jrc-covid-19-all-days-of-world_ASSIGNMENT-FIXED.csv";
        int csvLength = findLengthOfCSV(fileName);
        CovidRecord[] covidRecordArray = new CovidRecord[csvLength];
        covidRecordArray = importFromCSV(fileName, covidRecordArray);

        //testCSVImportValues(covidRecordArray);//OUTPUT COVID RECORD ARRAY FOR TESTING PURPOSES

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
        statMenu[0] = "Exit";
        statMenu[1] = "Total number of cumulatively positive cases";
        statMenu[2] = "Total number of cumulatively deceased cases";
        statMenu[3] = "Total number of cumulatively recovered cases";
        statMenu[4] = "Average daily number of currently positive cases";
        statMenu[5] = "Number and percentage of cumulatively positive cases recovered";
        statMenu[6] = "Number and percentage of cumulatively positive cases deceased";
        statMenu[7] = "All of the above statistics";

        try{
            displayMainMenu(sc, mainMenu, statMenu, covidRecordArray);
        } catch (InputMismatchException e) {
            //Catch error of type InputMismatchException and name it 'error'
            System.out.println("\nIncorrect input type.\nRestarting...\n");
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
    Import: sc (scanner), menu (String array)
    Export: a (integer)
    */
    public static int outputMenu(Scanner sc, String[] pMenu) {
        int a = 0, menuLength = pMenu.length;
        System.out.println("Please select a option from below:\n");
        for (int i = 0; i < menuLength; i++) {
            System.out.println(i + " >  " + pMenu[i]);
        }
        do {
            System.out.print("\nEnter selection: ");
            a = inputInt(sc);
        } while (a < 0 && menuLength < a);
        //While the input is outwith the menu array's length
        return a;
    }

    public static void displayMainMenu(Scanner sc, String[] pMainMenu, String[] pStatMenu, CovidRecord[] pCovidRecordArray) {
        int menuChoice, numCovRecMatchFilter;
        boolean run = true;

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

                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "SA"); //Set 'a' to the number of Covid Records in SA
                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "SA"); //Set the vaules of the 'filterArray' to all Covid Records in SA

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 3) {
                //Countries in North America

                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "NA"); //Set 'a' to the number of Covid Records in NA
                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "NA"); //Set the vaules of the 'filterArray' to all Covid Records in NA

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 4) {
                //Countries in Oceania

                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "OC"); //Set 'a' to the number of Covid Records in OC
                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "OC"); //Set the vaules of the 'filterArray' to all Covid Records in OC

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 5) {
                //Countries in Asia

                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "AS"); //Set 'a' to the number of Covid Records in AS
                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "AS"); //Set the vaules of the 'filterArray' to all Covid Records in AS

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 6) {
                //Countries in Africa
                
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "AF"); //Set 'a' to the number of Covid Records in AF
                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "AF"); //Set the vaules of the 'filterArray' to all Covid Records in AF

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 7) {
                //Countries in Europe
                
                numCovRecMatchFilter = searchCovRecObjContinent(pCovidRecordArray, "EU"); //Set 'a' to the number of Covid Records in EU
                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(numCovRecMatchFilter, pCovidRecordArray, "EU"); //Set the vaules of the 'filterArray' to all Covid Records in EU

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 8) {
                //Enter a country
                
                String input;
                System.out.print("Please enter the name of the country you would like to analyse: ");
                input = inputString(sc);

                numCovRecMatchFilter = searchCovRecObjCountry(pCovidRecordArray, input);
                System.out.println(numCovRecMatchFilter);

                while (numCovRecMatchFilter <= 0) {
                    System.out.print("Your input does not match any country name in the database, try again, remember to use capital letters: ");
                    input = inputString(sc);
                    numCovRecMatchFilter = searchCovRecObjCountry(pCovidRecordArray, input);
                }

                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter];
                filterArray = setCovRecFilObjCountry(numCovRecMatchFilter, pCovidRecordArray, input);

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else if(menuChoice == 9) {
                //Enter a date
                
                String input;
                System.out.print("Please enter the date you would like to analyse: ");
                input = inputString(sc);

                numCovRecMatchFilter = searchCovRecObjDate(pCovidRecordArray, input);

                while (numCovRecMatchFilter <= 0) {
                    System.out.print("Your input does not match any date in the database, try again using this format '12/2/2022': ");
                    input = inputString(sc);
                    numCovRecMatchFilter = searchCovRecObjDate(pCovidRecordArray, input);
                }

                CovidRecord[] filterArray = new CovidRecord[numCovRecMatchFilter];
                filterArray = setCovRecFilObjDate(numCovRecMatchFilter, pCovidRecordArray, input);

                displayStatistic(sc, pStatMenu, filterArray, pMainMenu, menuChoice);

            } else {
                System.out.println(menuChoice + " is an invalid menu ID, please try again: \n");
                //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
            }
        } while (run);
    }

    /*
    Method: displayStatistic
    Import: sc (scanner), statMenuArray (String array), covidRecordArray (CovidRecord Array), mainMenu (String Array), mainMenuChoice (Integer)
    Export: void
    */
    public static void displayStatistic(Scanner sc, String[] pStatMenu, CovidRecord[] pCovidRecordArray, String[] pMainMenu, int pMainMenuChoice) {
        int menuChoice;
        boolean run = true;
        do {
            menuChoice = outputMenu(sc, pStatMenu);
            if (menuChoice == 0) {
                //Exit Code
                run = false;
                System.out.println("Going Back...");
            } else if (menuChoice == 1) {
                //Total cumulative pos
                PrintTotalCumPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
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
                PrintTotalCumPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printTotalCumDec(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printTotalCumRec(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printAvgDailyCurrPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printPercentRecOverPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
                printPercentDecOverPos(pCovidRecordArray, pMainMenuChoice, pMainMenu);
            } else {
                System.out.println("Input is an invalid menu ID, please try again: \n");
                //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
            }
        } while (run);
        
    }

    public static void PrintTotalCumPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray;
        int totalCumPos;
        String readableOutput, out;

        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        totalCumPos = calcTotal(cumPosArray);

        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of positive cases in " + readableOutput + ": " + totalCumPos;

        System.out.println(out);
    }

    public static void printTotalCumDec(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumDecArray;
        int totalCumDec;
        String out, readableOutput;
                
        cumDecArray = distCovRecToCumDec(pCovidRecordArray);
        totalCumDec = calcTotal(cumDecArray);

        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of deceased people (as a result of COVID-19) in " + readableOutput + ": " + totalCumDec + ".";
        System.out.println(out);
    }

    public static void printTotalCumRec(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumRecArray;
        int totalCumRec;
        String out, readableOutput;

        cumRecArray = distCovRecToCumRec(pCovidRecordArray);
        totalCumRec = calcTotal(cumRecArray);

        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of recovered people (from COVID-19) in " + readableOutput + ": " + totalCumRec + ".";
        System.out.println(out);
    }

    public static void printAvgDailyCurrPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] currPosArray;
        int totalCurrPos, avgDailyCurrPos;
        double avgLengthOfMonth, dubAvgDailyCurrPos;
        String out, readableOutput;

        avgLengthOfMonth = ((31.0 * 6.0) + (30.0 * 5.0) + 28.0) / 12.0; //Average length of a month (not including leap year)

        currPosArray = distCovRecToCurrPos(pCovidRecordArray);
        totalCurrPos = calcTotal(currPosArray);

        dubAvgDailyCurrPos = (double)(totalCurrPos * avgLengthOfMonth) / 365.0;
        avgDailyCurrPos = (int)dubAvgDailyCurrPos;
                
        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = "The (approximate) average daily currently positive number of cases in " + readableOutput + " is: " + avgDailyCurrPos;
        System.out.println(out);
    }

    public static void printPercentRecOverPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray, cumRecArray;
        double cumPos, cumRec, percentRecOverPos;
        String out, readableOutput;

        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        cumPos = (double)calcTotal(cumPosArray);

        cumRecArray = distCovRecToCumRec(pCovidRecordArray);
        cumRec = (double)calcTotal(cumRecArray);

        percentRecOverPos = (cumRec/cumPos) * 100;
        percentRecOverPos = Math.round(percentRecOverPos);

        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = (int)percentRecOverPos + "% (" + (int)cumRec + "/" + (int)cumPos + ") cases recovered in " + readableOutput + ".";
        System.out.println(out);
    }

    public static void printPercentDecOverPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray, cumDecArray;
        String out, readableOutput;
        double cumPos, cumDec, percentDecOverPos;

        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        cumPos = (double)calcTotal(cumPosArray);

        cumDecArray = distCovRecToCumDec(pCovidRecordArray);
        cumDec = (double)calcTotal(cumDecArray);

        percentDecOverPos = (cumDec/cumPos) * 100;
        percentDecOverPos = Math.round(percentDecOverPos);

        readableOutput = calcReadableString(pMainMenuChoice, pMainMenu);
        out = (int)percentDecOverPos + "% (" + (int)cumDec + "/" + (int)cumPos + ") cases deceased in " + readableOutput + ".";
        System.out.println(out);
    }

    public static String calcReadableString(int pMenuChoice, String[] pMenu) {
        String output = "";

        if (pMenuChoice == 1 || pMenuChoice == 2 || pMenuChoice == 3 || pMenuChoice == 4 || pMenuChoice == 5 || pMenuChoice == 6 || pMenuChoice == 7) {
            output = pMenu[pMenuChoice];
        } else if (pMenuChoice == 8) {
            output = "the entred country";
        } else if (pMenuChoice == 9) {
            output = "the entred date";
        }
        return output;
    }

    public static int[] distCovRecToCumPos(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];

        for (int i = 0; i < inArrayLength; i++) {
            int cumPos = 0;
            try {
                cumPos = pCovRecArray[i].getCumulativePos();
            } catch (NullPointerException e) {}
            outputArray[i] = cumPos;
        }
        return outputArray;
    }

    public static int[] distCovRecToCumDec(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];

        for (int i = 0; i < inArrayLength; i++) {
            int cumDec = 0;
            try {
                cumDec = pCovRecArray[i].getCumulativeDec();
            } catch (NullPointerException e) {}
            outputArray[i] = cumDec;
        }
        return outputArray;
    }

    public static int[] distCovRecToCumRec(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];

        for (int i = 0; i < inArrayLength; i++) {
            int cumRec = 0;
            try {
                cumRec = pCovRecArray[i].getCumulativeRec();
            } catch (NullPointerException e) {}
            outputArray[i] = cumRec;
        }
        return outputArray;
    }

    public static int[] distCovRecToCurrPos(CovidRecord[] pCovRecArray) {
        int inArrayLength = pCovRecArray.length;
        int[] outputArray = new int[inArrayLength];

        for (int i = 0; i < inArrayLength; i++) {
            int currPos = 0;
            try {
                currPos = pCovRecArray[i].getCurrentlyPos();
            } catch (NullPointerException e) {}
                outputArray[i] = currPos;
        }
        return outputArray;
    }

    public static int searchCovRecObjContinent(CovidRecord[] pCovRecArr, String pFilter) {
        int numContinentMatchFilter = 0;
        String currContinent;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currContinent = pCovRecArr[i].getContinent();
            } catch (NullPointerException e) {
                currContinent = "";
            }
            if (currContinent.equals(pFilter)) {
                numContinentMatchFilter = numContinentMatchFilter +1;
            }
        }
        return numContinentMatchFilter -1;
    }

    public static CovidRecord[] setCovRecFilObjContinent(int pfilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pfilterArrayLength];
        int filterArrayInd = 0;
        String currContinent;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currContinent = pCovRecArr[i].getContinent();
            } catch (NullPointerException e) {
                currContinent = "";
            }
            if (currContinent.equals(pFilter)) {
                try {
                    filterArray[filterArrayInd] = pCovRecArr[i];
                } catch (ArrayIndexOutOfBoundsException e) {}
                filterArrayInd = filterArrayInd +1;
            }
        }
        return (filterArray);
    }

    public static CovidRecord[] setCovRecFilObjCountry(int pfilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pfilterArrayLength];
        System.out.println(pfilterArrayLength);
        int filterArrayInd = 0;
        String currCountryName;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currCountryName = pCovRecArr[i].getCountryName();
            } catch (NullPointerException e) {
                currCountryName = "";
            }
            if (currCountryName.equals(pFilter)) {
                try {
                    filterArray[filterArrayInd] = pCovRecArr[i];
                } catch (ArrayIndexOutOfBoundsException e) {}
                filterArrayInd = filterArrayInd +1;
            }
        }
        return filterArray;
    }

    public static int searchCovRecObjCountry(CovidRecord[] pCovRecArr, String pFilter) {
        int numCountryMatchFilter = 0;
        String currCountryName;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currCountryName = pCovRecArr[i].getCountryName();
            } catch (NullPointerException e) {
                currCountryName = "";
            }

            //System.out.println("Current country is: " + currCountryName + " Compared to filter: " + pFilter);

            if (currCountryName.equals(pFilter)) {
                //System.out.println(numCountryMatchFilter + " - " + (i+1));
                numCountryMatchFilter = numCountryMatchFilter + 1;
            }
        }
        return numCountryMatchFilter -1;
    }

    public static CovidRecord[] setCovRecFilObjDate(int pFilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pFilterArrayLength];
        int filterArrayInd = 0;
        String currDate;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currDate = pCovRecArr[i].getDate();
            } catch (NullPointerException e) {
                currDate = "";
            }
            if (currDate.equals(pFilter)) {
                try {
                    filterArray[filterArrayInd] = pCovRecArr[i];
                } catch (ArrayIndexOutOfBoundsException e) {}
                filterArrayInd = filterArrayInd +1;
            }
        }
        return filterArray;
    }

    public static int searchCovRecObjDate(CovidRecord[] pCovRecArr, String pFilter) {
        int numDateMatchFilter = 0;
        String currDate;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currDate = pCovRecArr[i].getDate();
            } catch (NullPointerException e) {
                currDate = "";
            }
            if (currDate.equals(pFilter)) {
                numDateMatchFilter = numDateMatchFilter +1;
            }
        }
        return numDateMatchFilter -1;
    }

    public static int findLengthOfCSV(String pCSVName) {
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        int lineNum = 0;
        String line;

        try {
            fileStream = new FileInputStream(pCSVName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);
            lineNum = 0;
            line = bufRdr.readLine();

            while (line != null) {
                lineNum++;
                line = bufRdr.readLine();
            }
            fileStream.close();
        } catch (IOException e) {
            if (fileStream != null) {
                try {
                    fileStream.close();
                } catch (IOException e2){}
            }
            System.out.println("Error in fileProcessing: " + e.getMessage());
        }
        return lineNum;
    }

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

            while (line != null) {
                lineNum++;

                pCovidRecordArray[lineNum -1] = lineToCovidRecord(line);

                line = bufRdr.readLine();
            }
            fileStream.close();
        } catch (IOException e) {
            if (fileStream != null) {
                try {
                    fileStream.close();
                } catch (IOException e2){}
            }
            System.out.println("Error in fileProcessing: " + e.getMessage());
        }
        return pCovidRecordArray;
    }

    public static String[] processLine(String pLine) {
        String[] splitLine;
        //System.out.println(pLine);
        splitLine = pLine.split(",", -1);

        return splitLine;
    }

    public static CovidRecord lineToCovidRecord(String pLine) {
        int numColInCSV = 13;
        String[] splitLine = new String[numColInCSV];
        splitLine = processLine(pLine);
        
        CovidRecord covidRecord;

        String date;
        String iso3;
        String continent;
        String countryName;
        double lat;
        double lon;
        int cumPos;
        int cumDec;
        int cumRec;
        int currPos;
        int hospitalised;
        int intensiveCare;
        String nuts;

        date = splitLine[0];
        iso3 = splitLine[1];
        continent = splitLine[2];
        countryName = splitLine[3];
        nuts = splitLine[12];

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