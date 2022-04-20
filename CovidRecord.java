/*
Author: Harrison Miles
Date: 18 Apr 2022
Purpose: A record to store covid details
*/
public class CovidRecord extends Country{
    private String date;
    private int cumulativePos;
    private int cumulativeDec;
    private int cumulativeRec;
    private int hospitalised;
    private int intensiveCare;
    
    //Parameters constructor
    CovidRecord(String pIso3, String pContinent, String pCountryName, String pNuts, double pLat, double pLon, String date, int pCumulativePos, int pCumulativeDec, int pCumulativeRec, int pHospitalised, int pIntensiveCare) {
        super(pIso3, pContinent, pCountryName, pNuts, pLat, pLon);
        cumulativePos   = pCumulativePos;
        cumulativeDec   = pCumulativeDec;
        cumulativeRec   = pCumulativeRec;
        hospitalised    = pHospitalised;
        intensiveCare   = pIntensiveCare;
    }

    //Copy constructor
    CovidRecord(CovidRecord pCovidRecord) {
        super(pCovidRecord);
        date            = pCovidRecord.getDate();
        cumulativePos   = pCovidRecord.getCumulativePos();
        cumulativeDec   = pCovidRecord.getCumulativeDec();
        cumulativeRec   = pCovidRecord.getCumulativeRec();
        hospitalised    = pCovidRecord.getHospitalised();
        intensiveCare   = pCovidRecord.getIntensiveCare();
    }

    //Default constructor
    CovidRecord() {
        super();
        date = "25/12/0000";
        cumulativePos = 2134;
        cumulativeDec = 2134;
        cumulativeRec = 2134;
        hospitalised  = 2134;
        intensiveCare = 2134;
    }

    //Accessor methods
    public String getDate() {
        return date;
    }
    public int getCumulativePos() {
        return cumulativePos;
    }
    public int getCumulativeDec() {
        return cumulativeDec;
    }
    public int getCumulativeRec() {
        return cumulativeRec;
    }
    public int getHospitalised() {
        return hospitalised;
    }
    public int getIntensiveCare() {
        return intensiveCare;
    }

    //Mutator methods
    public void setDate(String pDate) {
        date = pDate;
    }
    public void setCumulativePos(int pCumulativePos) {
        cumulativePos = pCumulativePos;
    }
    public void setCumulativeDec(int pCumulativeDec) {
        cumulativeDec = pCumulativeDec;
    }
    public void setCumulativeRec(int pCumulativeRec) {
        cumulativeRec = pCumulativeRec;
    }
    public void setHospitalised(int pHospitalised) {
        hospitalised = pHospitalised;
    }
    public void setIntensiveCare(int pIntensiveCare) {
        intensiveCare = pIntensiveCare;
    }

    public String toString() {
        String tempString = super.toString();
        String covidRecordString;
        covidRecordString = tempString + 
                            "\nCumulatively Positive: " + cumulativePos + 
                            "\nCumulatively Deceased: " + cumulativeDec +
                            "\nCumulatively Recovered: " + cumulativeRec +
                            "\nHospitalised: " + hospitalised + ", Intensive Care: " + intensiveCare;
        return covidRecordString;
    }

}
