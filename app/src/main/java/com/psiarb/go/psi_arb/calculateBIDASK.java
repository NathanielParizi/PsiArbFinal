package com.psiarb.go.psi_arb;

/**
 * Created by go on 1/10/2018.
 */

public class calculateBIDASK {

    private String bid, ask;
    private int startA =0;
    private StringBuffer bidBuffer = new StringBuffer();
    private StringBuffer askBuffer = new StringBuffer();

    public double getBid(double bid1, String haystack){

        int start = haystack.indexOf("Bid / Ask")+9;

        // Get bid price
        for(int i = start; i > 0; i++){

            if(haystack.charAt(i) == '/'){

                bid = bidBuffer.toString();
                bid1 = Double.parseDouble(bid);
                startA = i+1;

                break;
            }

            bidBuffer.append(haystack.charAt(i));

        }

        return bid1;

    }

    public double getAsk(double ask1, String haystack){

        // Get ask price
        for(int i = startA; i > 0; i++){

            if(Character.isLetter(haystack.charAt(i)) == true){

                ask = askBuffer.toString();
                ask1 = Double.parseDouble(ask);
                System.out.println("double Ask: " + ask1);

                break;
            }

            askBuffer.append(haystack.charAt(i));

        }

        return ask1;
    }

}