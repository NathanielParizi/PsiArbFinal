import java.lang.reflect.Field;

/**
 * Created by go on 1/16/2018.
 */

public class tri {

    //	double BTCETH = 3.2;
//	double ETHUSD = 2.2;
//	double USDBTC = 2.2;
//
    double  EURBTCb_Poloniex = 55;
    double  EURBTCa_Poloniex=3;
    double EURBTCb_CEXio=0;
    double EURBTCa_CEXio=0;
    double EURBTCb_Binance=0;
    double EURBTCa_Binance=0;
    double EURBTCb_Kraken=0;
    double EURBTCa_Kraken=0;
    double EURBTCb_Bitfinex=0;
    double EURBTCa_Bitfinex=0;

    public void scanMarket(){

        // EUR USD BTC ETH XRP XMR



        String EUR = "EUR";
        String USD = "USD";
        String BTC = "BTC";
        String ETH = "ETH";
        String XRP = "XRP";
        String XMR = "XMR";
        double[][] crypto = new double[2][48];

        crypto[0][0] = EURBTCb_Poloniex;
        crypto[1][0] = EURBTCa_Poloniex;
        String[] cards = new String[10];

        Field fldz[] = tri.class.getDeclaredFields();
        for(int i = 0; i < fldz.length; i=i+2){

            cards[i] = fldz[i].getName();
            System.out.println(cards[i]);
        }






        System.out.println(crypto[1][0]);

        String[] curr = {"EUR", "USD", "BTC", "ETH", "XRP",  "XMR"};
        String[] pair = new String[48];

        for(int i = 1; i < curr.length; i++){
            pair[i] = curr[0] + "/" + curr[i];
            System.out.print(pair[i] + "    ");

        }




        String c1 = "EURUSD";
        String c2 = "USDCHF";
        String c3 = "";

    }
}
