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
    //TODO rename variables to make more sense
    //TODO remove all testing code
    //TODO Comment important code
    //TODO Write all assertions
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        /*Instantiate the Scanner system.in under the name 'sc'
        A scanner system.in function is how the program accepts user input*/
        String fileName = "jrc-covid-19-all-days-of-world_ASSIGNMENT.csv";
        int csvLength = findLengthOfCSV(fileName);
        CovidRecord[] covidRecordArray = new CovidRecord[csvLength];
        covidRecordArray = importFromCSV(fileName, covidRecordArray);

        //testCSVImportValues(covidRecordArray);//OUTPUT COVID RECORD ARRAY FOR TESTING PURPOSES

        System.out.println("Welcome to the JRC Covid-19 Analaysis Program.\n" + "A total of " + (csvLength -1) + " records have been loaded.\n");

        String[] menu1 = new String[10];
        menu1[0] = "Exit";
        menu1[1] = "All countries";
        menu1[2] = "South America";
        menu1[3] = "North America";
        menu1[4] = "Oceania";
        menu1[5] = "Asia";
        menu1[6] = "Africa";
        menu1[7] = "Europe";
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

        try{
            displayMainMenu(sc, menu1, menu2, covidRecordArray);
        } catch (InputMismatchException error) {
            //Catch error of type InputMismatchException and name it 'error'
            System.out.println("Incorrect input type, please restart.\nError: " + error);
        }
        //Close the scanner (important to only do this once)
        sc.close();
    }

    public static void testCSVImportValues(CovidRecord[] pCovidRecords) {
        for (int i = 0; i < pCovidRecords.length; i++) {
            String covidRecordString;
            try {
                covidRecordString = pCovidRecords[i].toString();
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

    public static void displayMainMenu(Scanner sc, String[] menu1, String[] menu2, CovidRecord[] covidRecordArray) {
        int p, a;
        boolean run = true;

        do {
            p = outputMenu(sc, menu1); 
            if(p == 0) {
                //Exit Code

                System.out.println("Goodbye.");
                run = false; //(used in the do-while loop)

            } else if(p == 1) {
                //All Countries

                displayStatistic(sc, menu2, covidRecordArray, menu1, p);

            } else if (p == 2) {
                //Countries in South America

                a = searchCovRecObjContinent(covidRecordArray, "SA"); //Set 'a' to the number of Covid Records in SA
                CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(a, covidRecordArray, "SA"); //Set the vaules of the 'filterArray' to all Covid Records in SA

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else if(p == 3) {
                //Countries in North America

                a = searchCovRecObjContinent(covidRecordArray, "NA"); //Set 'a' to the number of Covid Records in NA
                CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(a, covidRecordArray, "NA"); //Set the vaules of the 'filterArray' to all Covid Records in NA

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else if(p == 4) {
                //Countries in Oceania

                a = searchCovRecObjContinent(covidRecordArray, "OC"); //Set 'a' to the number of Covid Records in OC
                CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(a, covidRecordArray, "OC"); //Set the vaules of the 'filterArray' to all Covid Records in OC

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else if(p == 5) {
                //Countries in Asia

                a = searchCovRecObjContinent(covidRecordArray, "AS"); //Set 'a' to the number of Covid Records in AS
                CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(a, covidRecordArray, "AS"); //Set the vaules of the 'filterArray' to all Covid Records in AS

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else if(p == 6) {
                //Countries in Africa
                
                a = searchCovRecObjContinent(covidRecordArray, "AF"); //Set 'a' to the number of Covid Records in AF
                CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(a, covidRecordArray, "AF"); //Set the vaules of the 'filterArray' to all Covid Records in AF

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else if(p == 7) {
                //Countries in Europe
                
                a = searchCovRecObjContinent(covidRecordArray, "EU"); //Set 'a' to the number of Covid Records in EU
                CovidRecord[] filterArray = new CovidRecord[a]; //Make a new 'filterArray' with a length of 'a'
                filterArray = setCovRecFilObjContinent(a, covidRecordArray, "EU"); //Set the vaules of the 'filterArray' to all Covid Records in EU

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else if(p == 8) {
                //Enter a country
                
                String input;
                System.out.print("Please enter the name of the country you would like to analyse: ");
                input = inputString(sc);

                a = searchCovRecObjCountry(covidRecordArray, input);

                while (a <= 0) {
                    System.out.print("Your input does not match any country name in the database, try again, remember to use capital letters: ");
                    input = inputString(sc);
                    a = searchCovRecObjCountry(covidRecordArray, input);
                }

                CovidRecord[] filterArray = new CovidRecord[a];
                filterArray = setCovRecFilObjCountry(a, covidRecordArray, input);

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else if(p == 9) {
                //Enter a date
                
                String input;
                System.out.print("Please enter the date you would like to analyse: ");
                input = inputString(sc);

                a = searchCovRecObjDate(covidRecordArray, input);

                while (a <= 0) {
                    System.out.print("Your input does not match any date in the database, try again using this format '12/2/2022': ");
                    input = inputString(sc);
                    a = searchCovRecObjDate(covidRecordArray, input);
                }

                CovidRecord[] filterArray = new CovidRecord[a];
                filterArray = setCovRecFilObjDate(a, covidRecordArray, input);

                displayStatistic(sc, menu2, filterArray, menu1, p);

            } else {
                System.out.println("Input is an invalid menu ID, please try again: \n");
                //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
            }
        } while (run);
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
            if (p == 0) {
                //Exit Code
                run = false;
                System.out.println("Going Back...");
            } else if (p == 1) {
                //Total cumulative pos
                PrintTotalCumPos(covidRecordArray, mainMenuChoice, mainMenu);
            } else if (p == 2) {
                //Total cumulative dec
                printTotalCumDec(covidRecordArray, mainMenuChoice, mainMenu);
            } else if (p == 3) {
                //Total cumulative rec
                printTotalCumRec(covidRecordArray, mainMenuChoice, mainMenu);
            } else if (p == 4) {
                //Avg daily number of positive cases
                printAvgDailyCurrPos(covidRecordArray, mainMenuChoice, mainMenu);
            } else if (p == 5) {
                //Num and % of cumulative pos cases rec
                printPercentRecOverPos(covidRecordArray, mainMenuChoice, mainMenu);
            } else if (p == 6) {
                //Num and % of cumulative pos cases dec
                printPercentDecOverPos(covidRecordArray, mainMenuChoice, mainMenu);
            } else if (p == 7) {
                //Print all of the above
                PrintTotalCumPos(covidRecordArray, mainMenuChoice, mainMenu);
                printTotalCumDec(covidRecordArray, mainMenuChoice, mainMenu);
                printTotalCumRec(covidRecordArray, mainMenuChoice, mainMenu);
                printAvgDailyCurrPos(covidRecordArray, mainMenuChoice, mainMenu);
                printPercentRecOverPos(covidRecordArray, mainMenuChoice, mainMenu);
                printPercentDecOverPos(covidRecordArray, mainMenuChoice, mainMenu);
            } else {
                System.out.println("Input is an invalid menu ID, please try again: \n");
                //If the user inputs any integer that is not within the menu bounds, ask the user to re-try, with reason
            }
        } while (run);
        
    }

    public static void PrintTotalCumPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray;
        int a;
        String readableOutput, out;

        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        a = calcTotal(cumPosArray);

        readableOutput = determineReadableMenuOutput(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of positive cases in " + readableOutput + ": " + a;

        System.out.println(out);
    }

    public static void printTotalCumDec(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumDecArray;
        int a;
        String out, readableOutput;
                
        cumDecArray = distCovRecToCumDec(pCovidRecordArray);
        a = calcTotal(cumDecArray);

        readableOutput = determineReadableMenuOutput(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of deceased people (as a result of COVID-19) in " + readableOutput + ": " + a + ".";
        System.out.println(out);
    }

    public static void printTotalCumRec(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumRecArray;
        int a;
        String out, readableOutput;

        cumRecArray = distCovRecToCumRec(pCovidRecordArray);
        a = calcTotal(cumRecArray);

        readableOutput = determineReadableMenuOutput(pMainMenuChoice, pMainMenu);
        out = "Cumulative number of recovered people (from COVID-19) in " + readableOutput + ": " + a + ".";
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
                
        readableOutput = determineReadableMenuOutput(pMainMenuChoice, pMainMenu);
        out = "The (approximate) average daily currently positive number of cases in " + readableOutput + " is: " + avgDailyCurrPos;
        System.out.println(out);
    }

    public static void printPercentRecOverPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray;
        int[] cumRecArray;
        double a, b, c;
        String out, readableOutput;

        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        a = (double)calcTotal(cumPosArray);

        cumRecArray = distCovRecToCumRec(pCovidRecordArray);
        b = (double)calcTotal(cumRecArray);

        c = (b/a) * 100;
        c = Math.round(c);

        readableOutput = determineReadableMenuOutput(pMainMenuChoice, pMainMenu);
        out = (int)c + "% (" + (int)b + "/" + (int)a + ") cases recovered in " + readableOutput + ".";
        System.out.println(out);
    }

    public static void printPercentDecOverPos(CovidRecord[] pCovidRecordArray, int pMainMenuChoice, String[] pMainMenu) {
        int[] cumPosArray;
        String out, readableOutput;
        int[] cumDecArray;
        double a, b, c;

        cumPosArray = distCovRecToCumPos(pCovidRecordArray);
        a = (double)calcTotal(cumPosArray);

        cumDecArray = distCovRecToCumDec(pCovidRecordArray);
        b = (double)calcTotal(cumDecArray);

        c = (b/a) * 100;
        c = Math.round(c);

        readableOutput = determineReadableMenuOutput(pMainMenuChoice, pMainMenu);
        out = (int)c + "% (" + (int)b + "/" + (int)a + ") cases deceased in " + readableOutput + ".";
        System.out.println(out);
    }

    public static String determineReadableMenuOutput(int pMenuChoice, String[] pMenu) {
        String output = "";

        if (pMenuChoice == 1 || pMenuChoice == 2 || pMenuChoice == 3 || pMenuChoice == 4 || pMenuChoice == 5 || pMenuChoice == 6 || pMenuChoice == 7) {
            output = pMenu[pMenuChoice];
        } else if (pMenuChoice == 8) {
            output = "The entred country";
        } else if (pMenuChoice == 9) {
            output = "The entred date";
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
        int a = 0;
        String currContinent;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currContinent = pCovRecArr[i].getContinent();
            } catch (NullPointerException e) {
                currContinent = "";
            }
            if (currContinent.equals(pFilter)) {
                a = a + 1;
            }
        }
        return a -1;
    }

    public static CovidRecord[] setCovRecFilObjContinent(int pfilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pfilterArrayLength];
        int a = 0;
        String currContinent;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currContinent = pCovRecArr[i].getContinent();
            } catch (NullPointerException e) {
                currContinent = "";
            }
            if (currContinent.equals(pFilter)) {
                try {
                    filterArray[a] = pCovRecArr[i];
                } catch (ArrayIndexOutOfBoundsException e) {}
                a = a + 1;
            }
        }
        return (filterArray);
    }

    public static CovidRecord[] setCovRecFilObjCountry(int pfilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pfilterArrayLength];
        System.out.println(pfilterArrayLength);
        int a = 0;
        String currCountryName;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currCountryName = pCovRecArr[i].getCountryName();
            } catch (NullPointerException e) {
                currCountryName = "";
            }
            if (currCountryName.equals(pFilter)) {
                try {
                    filterArray[a] = pCovRecArr[i];
                } catch (ArrayIndexOutOfBoundsException e) {}
                a = a + 1;
            }
        }
        return filterArray;
    }

    public static int searchCovRecObjCountry(CovidRecord[] pCovRecArr, String pFilter) {
        int a = 0;
        String currCountryName;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currCountryName = pCovRecArr[i].getCountryName();
            } catch (NullPointerException e) {
                currCountryName = "";
            }
            if (currCountryName.equals(pFilter)) {
                //System.out.println(a + " - " + i); ADDED FOR TESTING PURPOSES
                a = a + 1;
            }
        }
        return a -1;
    }

    public static CovidRecord[] setCovRecFilObjDate(int pFilterArrayLength, CovidRecord[] pCovRecArr, String pFilter) {
        CovidRecord[] filterArray = new CovidRecord[pFilterArrayLength];
        int a = 0;
        String currDate;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currDate = pCovRecArr[i].getDate();
            } catch (NullPointerException e) {
                currDate = "";
            }
            if (currDate.equals(pFilter)) {
                try {
                    filterArray[a] = pCovRecArr[i];
                } catch (ArrayIndexOutOfBoundsException e) {}
                a = a + 1;
            }
        }
        return filterArray;
    }

    public static int searchCovRecObjDate(CovidRecord[] pCovRecArr, String pFilter) {
        int a = 0;
        String currDate;
        for (int i = 0; i < pCovRecArr.length; i++) {
            try {
                currDate = pCovRecArr[i].getDate();
            } catch (NullPointerException e) {
                currDate = "";
            }
            if (currDate.equals(pFilter)) {
                a = a + 1;
            }
        }
        return a -1;
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

    public static CovidRecord[] importFromCSV(String pFileName, CovidRecord[] covidRecordArray) {

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

                covidRecordArray[lineNum -1] = lineToCovidRecord(line);

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
        return covidRecordArray;
    }

    public static String[] processLine(String pLine) {
        String[] splitLine;
        //System.out.println(pLine);
        splitLine = pLine.split(",", -1);

        return splitLine;
    }

    public static CovidRecord lineToCovidRecord(String pLine) {
        String[] splitLine = new String[13];
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
    Method: inputString
    Import: sc (Scanner)
    Export: a (String)
    */
    public static String inputString(Scanner sc) {
        String a;
        a = sc.next();
        return a;
    }
}