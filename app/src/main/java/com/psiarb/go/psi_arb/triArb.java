package com.psiarb.go.psi_arb;

import java.lang.reflect.Field;

/**
 * Created by go on 1/17/2018.
 */

public class triArb {

    static String c1;
    static String c2;
    static String c3;
    static int z = 0;

    public void tri() {

        String pair[] = new String[89];
        Field fld[] = SymbolBidAskRates.class.getDeclaredFields();

        int r = 0;
        for (int i = 0; i < 88; i = i + 2) {

            pair[r] = fld[i].getName();
            System.out.println(r + " " + i + "   " + pair[r]);
            r++;

        }

        //43 pairs
        for (int a = 0; a <= 43; a++) {

            c1 = pair[a];

            for (int b = 0; b <= 43; b++) {

                c2 = pair[b];

                for (int c = 0; c <= 43; c++) {

                    c3 = pair[c];

                    z++;

                    // Compare base currency with quote currency to secure round-trip triangular arbitrage
                    if (
                            (  // Path 1
                                    (c1.substring(3, 6).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(0, 3)))
                                            && (c1.substring(0, 3).equals(c3.substring(3, 6))))

                                    ||
                                    (   // Path 2
                                            (c1.substring(3, 6).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(3, 6)))
                                                    && (c1.substring(0, 3).equals(c3.substring(0, 3))))

                                    ||
                                    (   // Path 3
                                            (c1.substring(3, 6).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(0, 3)))
                                                    && (c1.substring(0, 3).equals(c3.substring(3, 6))))

                                    ||
                                    (// Path 4
                                            (c1.substring(3, 6).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(3, 6)))
                                                    && (c1.substring(0, 3).equals(c3.substring(0, 3))))

                                    ||
                                    (   // Path 5
                                            (c1.substring(0, 3).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(0, 3)))
                                                    && (c1.substring(3, 6).equals(c3.substring(3, 6))))
                                    ||

                                    (   // Path 6
                                            (c1.substring(0, 3).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(3, 6)))
                                                    && (c1.substring(3, 6).equals(c3.substring(0, 3))))

                                    ||
                                    (   // Path 7
                                            (c1.substring(0, 3).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(0, 3)))
                                                    && (c1.substring(3, 6).equals(c3.substring(3, 6))))

                                    ||
                                    (   // Path 8
                                            (c1.substring(0, 3).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(3, 6)))
                                                    && (c1.substring(3, 6).equals(c3.substring(0, 3))))
                            ) {

                        System.out.println(z + " " + c1 + " * " + c2 + " = " + c3);
                        System.out.println(c2);

                    }

                }

            }

        }

        System.out.println(c2);
    }

}
