package com.psiarb.go.psi_arb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextView text;
    Button btn;
    private double bid1 = 0;
    private double ask1 = 0;
    private double x = 0;
    public int path = 0;
    StringBuffer optimalPathText = new StringBuffer();

    static double profitTarget = 1;

    public static BigDecimal cd1b = new BigDecimal(0);
    public static BigDecimal cd1a = new BigDecimal(0);
    public static BigDecimal cd2b = new BigDecimal(0);
    public static BigDecimal cd2a = new BigDecimal(0);
    public static BigDecimal cd3b = new BigDecimal(0);
    public static BigDecimal cd3a = new BigDecimal(0);
    double triArbitrage[] = new double[10000000];


    // Kraken      33
/*
    ExchangeRateStorage BTCEUR_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETHEUR_kraken = new ExchangeRateStorage();
    ExchangeRateStorage XLMBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage BCHUSD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage LTCUSD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETCUSD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ZECUSD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage EOSETH_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETHETC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage BCHEUR_kraken = new ExchangeRateStorage();
    ExchangeRateStorage BCHBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage LTCEUR_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETHEOS_kraken = new ExchangeRateStorage();
    ExchangeRateStorage LTCBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETCBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ICNBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage REPEUR_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETCEUR_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ZECEUR_kraken = new ExchangeRateStorage();
    ExchangeRateStorage REPBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETHGNO_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ZECBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage BTCCAD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETCETH_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ICNETH_kraken = new ExchangeRateStorage();
    ExchangeRateStorage GNOBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETHCAD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage MLNBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage BTCJPY_kraken = new ExchangeRateStorage();
    ExchangeRateStorage MLNETH_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETHJPY_kraken = new ExchangeRateStorage();
    ExchangeRateStorage BTCGBP_kraken = new ExchangeRateStorage();
    ExchangeRateStorage ETHGBP_kraken = new ExchangeRateStorage();
  */
    ExchangeRateStorage ETHBTC_kraken = new ExchangeRateStorage();
    ExchangeRateStorage BTCUSD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage XRPUSD_kraken = new ExchangeRateStorage();   ;
    ExchangeRateStorage XMRBTC_kraken = new ExchangeRateStorage();   ;
    ExchangeRateStorage XRPBTC_kraken = new ExchangeRateStorage();   ;
    ExchangeRateStorage XMRUSD_kraken = new ExchangeRateStorage();   ;
    ExchangeRateStorage XRPEUR_kraken = new ExchangeRateStorage();   ;
    ExchangeRateStorage ETHUSD_kraken = new ExchangeRateStorage();
    ExchangeRateStorage XMREUR_kraken = new ExchangeRateStorage();   ;


    //GDAX Pairs     9
 /*
    ExchangeRateStorage BCHBTC_gdax = new ExchangeRateStorage();
    ExchangeRateStorage BCHEUR_gdax = new ExchangeRateStorage();
    ExchangeRateStorage BCHUSD_gdax = new ExchangeRateStorage();
    ExchangeRateStorage BTCEUR_gdax = new ExchangeRateStorage();
    ExchangeRateStorage BTCGBP_gdax = new ExchangeRateStorage();
    ExchangeRateStorage ETHEUR_gdax = new ExchangeRateStorage();
    ExchangeRateStorage LTCBTC_gdax = new ExchangeRateStorage();
    ExchangeRateStorage LTCEUR_gdax = new ExchangeRateStorage();
    ExchangeRateStorage LTCUSD_gdax = new ExchangeRateStorage();
    */
    ExchangeRateStorage ETHBTC_gdax = new ExchangeRateStorage();
    ExchangeRateStorage ETHUSD_gdax = new ExchangeRateStorage();
    ExchangeRateStorage BTCUSD_gdax = new ExchangeRateStorage();



    //Gemini    3
/*
    ExchangeRateStorage BTCUSD_gemini = new ExchangeRateStorage();
    ExchangeRateStorage ETHUSD_gemini = new ExchangeRateStorage();
    ExchangeRateStorage ETHBTC_gemini = new ExchangeRateStorage();
*/
    //Binance

    ExchangeRateStorage ETHBTC_binance =  new ExchangeRateStorage();
    ExchangeRateStorage BTCUSD_binance = new ExchangeRateStorage();
    ExchangeRateStorage ETHUSD_binance = new ExchangeRateStorage();
    ExchangeRateStorage XMRETH_binance = new ExchangeRateStorage();   ;
    ExchangeRateStorage XMRBTC_binance = new ExchangeRateStorage();   ;
    ExchangeRateStorage XRPBTC_binance = new ExchangeRateStorage();   ;
    ExchangeRateStorage XRPETH_binance = new ExchangeRateStorage();   ;


    //Poloniex

    ExchangeRateStorage ETHUSD_poloniex = new ExchangeRateStorage();
    ExchangeRateStorage ETHBTC_poloniex = new ExchangeRateStorage();
    ExchangeRateStorage BTCUSD_poloniex = new ExchangeRateStorage() ;
    ExchangeRateStorage XRPUSD_poloniex = new ExchangeRateStorage();
    ExchangeRateStorage XMRUSD_poloniex = new ExchangeRateStorage();
    ExchangeRateStorage XMRBTC_poloniex = new ExchangeRateStorage();
    ExchangeRateStorage XRPBTC_poloniex = new ExchangeRateStorage();

    //HITBIT


    //CEXio

    //   ExchangeRateStorage ETHUSD_cexio = new ExchangeRateStorage();
    // ExchangeRateStorage ETHBTC_cexio = new ExchangeRateStorage();
    //ExchangeRateStorage BTCUSD_cexio = new ExchangeRateStorage();
    //  ExchangeRateStorage XRPUSD_cexio = new ExchangeRateStorage();   ;
    //  ExchangeRateStorage XRPEUR_cexio = new ExchangeRateStorage();



    //Bitfinex


    //  ExchangeRateStorage BTCUSD_bitfinex = new ExchangeRateStorage();
    // ExchangeRateStorage ETHBTC_bitfinex= new ExchangeRateStorage();
    //  ExchangeRateStorage XRPBTC_bitfinex = new ExchangeRateStorage();   ;
    // ExchangeRateStorage ETHUSD_bitfinex = new ExchangeRateStorage();   ;
    // ExchangeRateStorage XMRBTC_bitfinex = new ExchangeRateStorage();   ;
    //   ExchangeRateStorage XMRUSD_bitfinex = new ExchangeRateStorage();   ;



    // ================================================  TOTAL PAIRS   45



    static String c1;
    static String c2;
    static String c3;
    static int z = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.bidask);
        btn = (Button) findViewById(R.id.button);
        text.setMovementMethod(new ScrollingMovementMethod());

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 Toast.makeText(getApplicationContext(),"Please wait a minute while market data loads", LENGTH_LONG).show();

                     new fetchData().execute();

             }
         });



    }

    public class fetchData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuffer buffer = new StringBuffer();

            try {

                Document doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc").get();
                Elements span = doc.select("span");
                String method = span.select("span").text();
                buffer.append(method);
                String haystack = buffer.toString();
                calculateBIDASK ETHBTCpoloniex = new calculateBIDASK();

                ETHBTC_poloniex.setBid(ETHBTCpoloniex.getBid(bid1, haystack));
                ETHBTC_poloniex.setAsk(ETHBTCpoloniex.getAsk(ask1, haystack));
                ETHBTC_poloniex.setcPair("EURBTC_Poloniex");

                System.out.println("OK ETHBTC_poloniex is" + ETHBTC_poloniex.getBid() + " " + ETHBTC_poloniex.getAsk());


          /*      doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1054867").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHBTCcexio = new calculateBIDASK();

        //        ETHBTC_cexio.setBid(ETHBTCcexio.getBid(bid1,haystack));
         //       ETHBTC_cexio.setAsk(ETHBTCcexio.getAsk(ask1,haystack));
        //        ETHBTC_cexio.setcPair("ETHBTC_Cexio");

         //       System.out.println("OK ETHBTC_cexio is" + ETHBTC_cexio.getBid() + " " + ETHBTC_cexio.getAsk());



        */      doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1031692").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHBTCbinance = new calculateBIDASK();

                ETHBTC_binance.setBid(ETHBTCbinance.getBid(bid1,haystack));
                ETHBTC_binance.setAsk(ETHBTCbinance.getAsk(ask1,haystack));
                ETHBTC_binance.setcPair("ETHBTC_Binance");

                System.out.println("OK ETHBTC_binance is" + ETHBTC_binance.getBid() + " " + ETHBTC_binance.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1010786").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHBTCkraken = new calculateBIDASK();

                ETHBTC_kraken.setBid(ETHBTCkraken.getBid(bid1,haystack));
                ETHBTC_kraken.setAsk(ETHBTCkraken.getAsk(ask1,haystack));
                ETHBTC_kraken.setcPair("ETHBTC_Kraken");

                System.out.println("OK ETHBTC_kraken is" + ETHBTC_kraken.getBid() + " " + ETHBTC_kraken.getAsk());

         /*       doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1001806").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHBTCbitfinex = new calculateBIDASK();

                ETHBTC_bitfinex.setBid(ETHBTCbitfinex.getBid(bid1,haystack));
                ETHBTC_bitfinex.setAsk(ETHBTCbitfinex.getAsk(ask1,haystack));
                ETHBTC_bitfinex.setcPair("ETHBTC_Bitfinex");

                System.out.println("OK ETHBTC_bitfinex is" + ETHBTC_bitfinex.getBid() + " " + ETHBTC_bitfinex.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1010799").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHBTCgdax = new calculateBIDASK();

                ETHBTC_gdax.setBid(ETHBTCgdax.getBid(bid1,haystack));
                ETHBTC_gdax.setAsk(ETHBTCgdax.getAsk(ask1,haystack));

                System.out.println("OK ETHBTC_gdax is" + ETHBTC_gdax.getBid() + " " + ETHBTC_gdax.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/btc-usd?cid=1010780").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK BTCUSDpoloniex = new calculateBIDASK();

                BTCUSD_poloniex.setBid(BTCUSDpoloniex.getBid(bid1,haystack));
                BTCUSD_poloniex.setAsk(BTCUSDpoloniex.getAsk(ask1,haystack));

                System.out.println("OK BTCUSD_poloniex is" + BTCUSD_poloniex.getBid() + " " + BTCUSD_poloniex.getAsk());


          /*      doc = Jsoup.connect("https://www.investing.com/currencies/btc-usd?cid=1054862").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK BTCUSDcexio = new calculateBIDASK();

                BTCUSD_cexio.setBid(BTCUSDcexio.getBid(bid1,haystack));
                BTCUSD_cexio.setAsk(BTCUSDcexio.getAsk(ask1,haystack));

                System.out.println("OK BTCUSD_cexio is" + BTCUSD_cexio.getBid() + " " + BTCUSD_cexio.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/btc-usd?cid=1035793").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK BTCUSDbinance = new calculateBIDASK();

                BTCUSD_binance.setBid(BTCUSDbinance.getBid(bid1,haystack));
                BTCUSD_binance.setAsk(BTCUSDbinance.getAsk(ask1,haystack));

                System.out.println("OK BTCUSD_binance is" + BTCUSD_binance.getBid() + " " + BTCUSD_binance.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/btc-usd?cid=49799").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK BTCUSDkraken = new calculateBIDASK();

                BTCUSD_kraken.setBid(BTCUSDkraken.getBid(bid1,haystack));
                BTCUSD_kraken.setAsk(BTCUSDkraken.getAsk(ask1,haystack));

                System.out.println("OK BTCUSD_kraken is" + BTCUSD_kraken.getBid() + " " + BTCUSD_kraken.getAsk());

        /*        doc = Jsoup.connect("https://www.investing.com/currencies/btc-usd").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK BTCUSDbitfinex = new calculateBIDASK();

                BTCUSD_bitfinex.setBid(BTCUSDbitfinex.getBid(bid1,haystack));
                BTCUSD_bitfinex.setAsk(BTCUSDbitfinex.getAsk(ask1,haystack));

                System.out.println("OK BTCUSD_bitfinex is" + BTCUSD_bitfinex.getBid() + " " + BTCUSD_bitfinex.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/btc-usd?cid=1010796").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK BTCUSDgdax = new calculateBIDASK();

                BTCUSD_gdax.setBid(BTCUSDgdax.getBid(bid1,haystack));
                BTCUSD_gdax.setAsk(BTCUSDgdax.getAsk(ask1,haystack));

                System.out.println("OK BTCUSD_gdax is" + BTCUSD_gdax.getBid() + " " + BTCUSD_gdax.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/eth-usd?cid=1010781").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHUSDpoloniex = new calculateBIDASK();

                ETHUSD_poloniex.setBid(ETHUSDpoloniex.getBid(bid1,haystack));
                ETHUSD_poloniex.setAsk(ETHUSDpoloniex.getAsk(ask1,haystack));

                System.out.println("OK ETHUSD_poloniex is" + ETHUSD_poloniex.getBid() + " " + ETHUSD_poloniex.getAsk());
/*
                doc = Jsoup.connect("https://www.investing.com/currencies/eth-usd?cid=1054866").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHUSDcexio = new calculateBIDASK();

                ETHUSD_cexio.setBid(ETHUSDcexio.getBid(bid1,haystack));
                ETHUSD_cexio.setAsk(ETHUSDcexio.getAsk(ask1,haystack));

                System.out.println("OK ETHUSD_cexio is" + ETHUSD_cexio.getBid() + " " + ETHUSD_cexio.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/eth-usd?cid=1035794").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHUSDbinance = new calculateBIDASK();

                ETHUSD_binance.setBid(ETHUSDbinance.getBid(bid1,haystack));
                ETHUSD_binance.setAsk(ETHUSDbinance.getAsk(ask1,haystack));

                System.out.println("OK ETHUSD_binance is" + ETHUSD_binance.getBid() + " " + ETHUSD_binance.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/eth-usd?cid=997651").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHUSDkraken = new calculateBIDASK();

                ETHUSD_kraken.setBid(ETHUSDkraken.getBid(bid1,haystack));
                ETHUSD_kraken.setAsk(ETHUSDkraken.getAsk(ask1,haystack));

                System.out.println("OK ETHUSD_kraken is" + ETHUSD_kraken.getBid() + " " + ETHUSD_kraken.getAsk());

   /*             doc = Jsoup.connect("https://www.investing.com/currencies/eth-usd").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHUSDbitfinex = new calculateBIDASK();

                ETHUSD_bitfinex.setBid(ETHUSDbitfinex.getBid(bid1,haystack));
                ETHUSD_bitfinex.setAsk(ETHUSDbitfinex.getAsk(ask1,haystack));

                System.out.println("OK ETHUSD_bitfinex is" + ETHUSD_bitfinex.getBid() + " " + ETHUSD_bitfinex.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/eth-usd?cid=1010797").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK ETHUSDgdax = new calculateBIDASK();

                ETHUSD_gdax.setBid(ETHUSDgdax.getBid(bid1,haystack));
                ETHUSD_gdax.setAsk(ETHUSDgdax.getAsk(ask1,haystack));

                System.out.println("OK ETHUSD_gdax is" + ETHUSD_gdax.getBid() + " " + ETHUSD_gdax.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-usd").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPUSDpoloniex = new calculateBIDASK();

                XRPUSD_poloniex.setBid(XRPUSDpoloniex.getBid(bid1,haystack));
                XRPUSD_poloniex.setAsk(XRPUSDpoloniex.getAsk(ask1,haystack));

                System.out.println("OK XRPUSD_poloniex is" + XRPUSD_poloniex.getBid() + " " + XRPUSD_poloniex.getAsk());
/*
                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-usd?cid=1056985").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPUSDcexio = new calculateBIDASK();

                XRPUSD_cexio.setBid(XRPUSDcexio.getBid(bid1,haystack));
                XRPUSD_cexio.setAsk(XRPUSDcexio.getAsk(ask1,haystack));

                System.out.println("OK XRPUSD_cexio is" + XRPUSD_cexio.getBid() + " " + XRPUSD_cexio.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-usd?cid=1010789").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPUSDkraken = new calculateBIDASK();

                XRPUSD_kraken.setBid(XRPUSDkraken.getBid(bid1,haystack));
                XRPUSD_kraken.setAsk(XRPUSDkraken.getAsk(ask1,haystack));

                System.out.println("OK XRPUSD_kraken is" + XRPUSD_kraken.getBid() + " " + XRPUSD_kraken.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-eur?cid=1010788").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPEURkraken = new calculateBIDASK();

                XRPEUR_kraken.setBid(XRPEURkraken.getBid(bid1,haystack));
                XRPEUR_kraken.setAsk(XRPEURkraken.getAsk(ask1,haystack));

                System.out.println("OK XRPEUR_kraken is" + XRPEUR_kraken.getBid() + " " + XRPEUR_kraken.getAsk());
/*
                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-eur?cid=1056988").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPEURcexio = new calculateBIDASK();

                XRPEUR_cexio.setBid(XRPEURcexio.getBid(bid1,haystack));
                XRPEUR_cexio.setAsk(XRPEURcexio.getAsk(ask1,haystack));

                System.out.println("OK XRPEUR_cexio is" + XRPEUR_cexio.getBid() + " " + XRPEUR_cexio.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-btc").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPBTCpoloniex = new calculateBIDASK();


                XRPBTC_poloniex.setBid(XRPBTCpoloniex.getBid(bid1,haystack));
                XRPBTC_poloniex.setAsk(XRPBTCpoloniex.getAsk(ask1,haystack));

                System.out.println("OK XRPBTC_poloniex is" + XRPBTC_poloniex.getBid() + " " + XRPBTC_poloniex.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-btc?cid=1054876").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPBTCbinance = new calculateBIDASK();

                XRPBTC_binance.setBid(XRPBTCbinance.getBid(bid1,haystack));
                XRPBTC_binance.setAsk(XRPBTCbinance.getAsk(ask1,haystack));

                System.out.println("OK XRPBTC_binance is" + XRPBTC_binance.getBid() + " " + XRPBTC_binance.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-btc?cid=1010787").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPBTCkraken = new calculateBIDASK();

                XRPBTC_kraken.setBid(XRPBTCkraken.getBid(bid1,haystack));
                XRPBTC_kraken.setAsk(XRPBTCkraken.getAsk(ask1,haystack));

                System.out.println("OK XRPBTC_kraken is" + XRPBTC_kraken.getBid() + " " + XRPBTC_kraken.getAsk());
/*
                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-btc?cid=1010804").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPBTCbitfinex = new calculateBIDASK();

                XRPBTC_bitfinex.setBid(XRPBTCbitfinex.getBid(bid1,haystack));
                XRPBTC_bitfinex.setAsk(XRPBTCbitfinex.getAsk(ask1,haystack));

                System.out.println("OK XRPBTC_bitfinex is" + XRPBTC_bitfinex.getBid() + " " + XRPBTC_bitfinex.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/xrp-eth?cid=1055882").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XRPETHbinance = new calculateBIDASK();

                XRPETH_binance.setBid(XRPETHbinance.getBid(bid1,haystack));
                XRPETH_binance.setAsk(XRPETHbinance.getAsk(ask1,haystack));

                System.out.println("OK XRPETH_binance is" + XRPETH_binance.getBid() + " " + XRPETH_binance.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-btc").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRBTCpoloniex = new calculateBIDASK();

                XMRBTC_poloniex.setBid(XMRBTCpoloniex.getBid(bid1,haystack));
                XMRBTC_poloniex.setAsk(XMRBTCpoloniex.getAsk(ask1,haystack));

                System.out.println("OK XMRBTC_poloniex is" + XMRBTC_poloniex.getBid() + " " + XMRBTC_poloniex.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-btc?cid=1054882").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRBTCbinance = new calculateBIDASK();

                XMRBTC_binance.setBid(XMRBTCbinance.getBid(bid1,haystack));
                XMRBTC_binance.setAsk(XMRBTCbinance.getAsk(ask1,haystack));

                System.out.println("OK XMRBTC_binance is" + XMRBTC_binance.getBid() + " " + XMRBTC_binance.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-btc?cid=1024868").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRBTCkraken = new calculateBIDASK();

                XMRBTC_kraken.setBid(XMRBTCkraken.getBid(bid1,haystack));
                XMRBTC_kraken.setAsk(XMRBTCkraken.getAsk(ask1,haystack));

                System.out.println("OK XMRBTC_kraken is" + XMRBTC_kraken.getBid() + " " + XMRBTC_kraken.getAsk());
/*
                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-btc?cid=1025082").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRBTCbitfinex = new calculateBIDASK();

                XMRBTC_bitfinex.setBid(XMRBTCbitfinex.getBid(bid1,haystack));
                XMRBTC_bitfinex.setAsk(XMRBTCbitfinex.getAsk(ask1,haystack));

                System.out.println("OK XMRBTC_bitfinex is" + XMRBTC_bitfinex.getBid() + " " + XMRBTC_bitfinex.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-usd").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRUSDpoloniex = new calculateBIDASK();

                XMRUSD_poloniex.setBid(XMRUSDpoloniex.getBid(bid1,haystack));
                XMRUSD_poloniex.setAsk(XMRUSDpoloniex.getAsk(ask1,haystack));

                System.out.println("OK XMRUSD_poloniex is" + XMRUSD_poloniex.getBid() + " " + XMRUSD_poloniex.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-usd?cid=1024870").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRUSDkraken = new calculateBIDASK();

                XMRUSD_kraken.setBid(XMRUSDkraken.getBid(bid1,haystack));
                XMRUSD_kraken.setAsk(XMRUSDkraken.getAsk(ask1,haystack));

                System.out.println("OK XMRUSD_kraken is" + XMRUSD_kraken.getBid() + " " + XMRUSD_kraken.getAsk());
/*
                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-usd?cid=1025083").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRUSDbitfinex = new calculateBIDASK();

                XMRUSD_bitfinex.setBid(XMRUSDbitfinex.getBid(bid1,haystack));
                XMRUSD_bitfinex.setAsk(XMRUSDbitfinex.getAsk(ask1,haystack));

                System.out.println("OK XMRUSD_bitfinex is" + XMRUSD_bitfinex.getBid() + " " + XMRUSD_bitfinex.getAsk());
*/
                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-eur").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMREURkraken = new calculateBIDASK();

                XMREUR_kraken.setBid(XMREURkraken.getBid(bid1,haystack));
                XMREUR_kraken.setAsk(XMREURkraken.getAsk(ask1,haystack));

                System.out.println("OK XMREUR_kraken is" + XMREUR_kraken.getBid() + " " + XMREUR_kraken.getAsk());

                doc = Jsoup.connect("https://www.investing.com/currencies/xmr-eth?cid=1054897").get();
                span = doc.select("span");
                buffer.delete(0,buffer.length());
                method = span.select("span").text();
                buffer.append(method);
                haystack = buffer.toString();
                calculateBIDASK XMRETHbinance = new calculateBIDASK();

                XMRETH_binance.setBid(XMRETHbinance.getBid(bid1,haystack));
                XMRETH_binance.setAsk(XMRETHbinance.getAsk(ask1,haystack));

                System.out.println("OK XMRETH_binance is" + XMRETH_binance.getBid() + " " + XMRETH_binance.getAsk());

                //Triangular Arbitrage Spreads
                triangularArbitrage();



            }catch(Exception e){e.printStackTrace();}

            return buffer.toString();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String buffer = s;

            Toast.makeText(getApplicationContext(),"OK", LENGTH_LONG).show();
            text.setText(optimalPathText);

        }

    }

    private void triangularArbitrage() {

        //TriArb

        String pair[] = new String[89];
        Field[] fld = MainActivity.class.getDeclaredFields();

        System.out.println("CHECK");
        for (int i = 0; i < fld.length; i++) {

            pair[i] = fld[i].getName();
            System.out.println( i + "   " + pair[i]);

        }

        //Make sure that the max length a b c must reach is the quantity of currency pairs being calculated
        for (int a = 0; a <= 25; a++) {

            c1 = pair[a];

            for (int b = 0; b <= 25; b++) {

                c2 = pair[b];

                for (int c = 0; c <= 25; c++) {

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



                        //

                        // cd1 cd2 cd3  BRUTE FORCE

                        if(c1.equalsIgnoreCase("ETHBTC_poloniex")){

                            cd1b = ETHBTC_poloniex.getBid();
                            cd1a = ETHBTC_poloniex.getAsk();

                        }
/*
                        if(c1.equalsIgnoreCase("ETHBTC_cexio")){

                            cd1b = ETHBTC_cexio.getBid();
                            cd1a = ETHBTC_cexio.getAsk();

                        }*/

                        if(c1.equalsIgnoreCase("ETHBTC_binance")){

                            cd1b = ETHBTC_binance.getBid();
                            cd1a = ETHBTC_binance.getAsk();

                        }

                        if(c1.equalsIgnoreCase("ETHBTC_kraken")){

                            cd1b = ETHBTC_kraken.getBid();
                            cd1a = ETHBTC_kraken.getAsk();

                        }

         /*               if(c1.equalsIgnoreCase("ETHBTC_bitfinex")){

                            cd1b = ETHBTC_bitfinex.getBid();
                            cd1a = ETHBTC_bitfinex.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("ETHBTC_gdax")){

                            cd1b = ETHBTC_gdax.getBid();
                            cd1a = ETHBTC_gdax.getAsk();

                        }

                        if(c1.equalsIgnoreCase("BTCUSD_poloniex")){

                            cd1b = BTCUSD_poloniex.getBid();
                            cd1a = BTCUSD_poloniex.getAsk();

                        }

    /*                    if(c1.equalsIgnoreCase("BTCUSD_cexio")){

                            cd1b = BTCUSD_cexio.getBid();
                            cd1a = BTCUSD_cexio.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("BTCUSD_binance")){

                            cd1b = BTCUSD_binance.getBid();
                            cd1a = BTCUSD_binance.getAsk();

                        }

                        if(c1.equalsIgnoreCase("BTCUSD_kraken")){

                            cd1b = BTCUSD_kraken.getBid();
                            cd1a = BTCUSD_kraken.getAsk();

                        }
/*
                        if(c1.equalsIgnoreCase("BTCUSD_bitfinex")){

                            cd1b = BTCUSD_bitfinex.getBid();
                            cd1a = BTCUSD_bitfinex.getAsk();

                      }
*/
                        if(c1.equalsIgnoreCase("BTCUSD_gdax")){

                            cd1b = BTCUSD_gdax.getBid();
                            cd1a = BTCUSD_gdax.getAsk();

                        }

                        if(c1.equalsIgnoreCase("ETHUSD_poloniex")){

                            cd1b = ETHUSD_poloniex.getBid();
                            cd1a = ETHUSD_poloniex.getAsk();

                        }

 /*                       if(c1.equalsIgnoreCase("ETHUSD_cexio")){

                            cd1b = ETHUSD_cexio.getBid();
                            cd1a = ETHUSD_cexio.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("ETHUSD_binance")){

                            cd1b = ETHUSD_binance.getBid();
                            cd1a = ETHUSD_binance.getAsk();

                        }

                        if(c1.equalsIgnoreCase("ETHUSD_kraken")){

                            cd1b = ETHUSD_kraken.getBid();
                            cd1a = ETHUSD_kraken.getAsk();

                        }
/*
                        if(c1.equalsIgnoreCase("ETHUSD_bitfinex")){

                            cd1b = ETHUSD_bitfinex.getBid();
                            cd1a = ETHUSD_bitfinex.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("ETHUSD_gdax")){

                            cd1b = ETHUSD_gdax.getBid();
                            cd1a = ETHUSD_gdax.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XRPUSD_poloniex")){

                            cd1b = XRPUSD_poloniex.getBid();
                            cd1a = XRPUSD_poloniex.getAsk();

                        }

 /*                       if(c1.equalsIgnoreCase("XRPUSD_cexio")){

                            cd1b = XRPUSD_cexio.getBid();
                            cd1a = XRPUSD_cexio.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("XRPUSD_kraken")){

                            cd1b = XRPUSD_kraken.getBid();
                            cd1a = XRPUSD_kraken.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XRPEUR_kraken")){

                            cd1b = XRPEUR_kraken.getBid();
                            cd1a = XRPEUR_kraken.getAsk();

                        }

 /*                       if(c1.equalsIgnoreCase("XRPEUR_cexio")){

                            cd1b = XRPEUR_cexio.getBid();
                            cd1a = XRPEUR_cexio.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("XRPBTC_poloniex")){

                            cd1b = XRPBTC_poloniex.getBid();
                            cd1a = XRPBTC_poloniex.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XRPBTC_binance")){

                            cd1b = XRPBTC_binance.getBid();
                            cd1a = XRPBTC_binance.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XRPBTC_kraken")){

                            cd1b = XRPBTC_kraken.getBid();
                            cd1a = XRPBTC_kraken.getAsk();

                        }
/*
                        if(c1.equalsIgnoreCase("XRPBTC_bitfinex")){

                            cd1b = XRPBTC_bitfinex.getBid();
                            cd1a = XRPBTC_bitfinex.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("XRPETH_binance")){

                            cd1b = XRPETH_binance.getBid();
                            cd1a = XRPETH_binance.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XMRBTC_poloniex")){

                            cd1b = XMRBTC_poloniex.getBid();
                            cd1a = XMRBTC_poloniex.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XMRBTC_binance")){

                            cd1b = XMRBTC_binance.getBid();
                            cd1a = XMRBTC_binance.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XMRBTC_kraken")){

                            cd1b = XMRBTC_kraken.getBid();
                            cd1a = XMRBTC_kraken.getAsk();

                        }
/*
                        if(c1.equalsIgnoreCase("XMRBTC_bitfinex")){

                            cd1b = XMRBTC_bitfinex.getBid();
                            cd1a = XMRBTC_bitfinex.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("XMRUSD_poloniex")){

                            cd1b = XMRUSD_poloniex.getBid();
                            cd1a = XMRUSD_poloniex.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XMRUSD_kraken")){

                            cd1b = XMRUSD_kraken.getBid();
                            cd1a = XMRUSD_kraken.getAsk();

                        }
/*
                        if(c1.equalsIgnoreCase("XMRUSD_bitfinex")){

                            cd1b = XMRUSD_bitfinex.getBid();
                            cd1a = XMRUSD_bitfinex.getAsk();

                        }
*/
                        if(c1.equalsIgnoreCase("XMREUR_kraken")){

                            cd1b = XMREUR_kraken.getBid();
                            cd1a = XMREUR_kraken.getAsk();

                        }

                        if(c1.equalsIgnoreCase("XMRETH_binance")){

                            cd1b = XMRETH_binance.getBid();
                            cd1a = XMRETH_binance.getAsk();

                        }

                        // CD2

                        if(c2.equalsIgnoreCase("ETHBTC_poloniex")){

                            cd2b = ETHBTC_poloniex.getBid();
                            cd2a = ETHBTC_poloniex.getAsk();

                        }
/*
                        if(c2.equalsIgnoreCase("ETHBTC_cexio")){

                            cd2b = ETHBTC_cexio.getBid();
                            cd2a = ETHBTC_cexio.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("ETHBTC_binance")){

                            cd2b = ETHBTC_binance.getBid();
                            cd2a = ETHBTC_binance.getAsk();

                        }

                        if(c2.equalsIgnoreCase("ETHBTC_kraken")){

                            cd2b = ETHBTC_kraken.getBid();
                            cd2a = ETHBTC_kraken.getAsk();

                        }
/*
                        if(c2.equalsIgnoreCase("ETHBTC_bitfinex")){

                            cd2b = ETHBTC_bitfinex.getBid();
                            cd2a = ETHBTC_bitfinex.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("ETHBTC_gdax")){

                            cd2b = ETHBTC_gdax.getBid();
                            cd2a = ETHBTC_gdax.getAsk();

                        }

                        if(c2.equalsIgnoreCase("BTCUSD_poloniex")){

                            cd2b = BTCUSD_poloniex.getBid();
                            cd2a = BTCUSD_poloniex.getAsk();

                        }
/*
                        if(c2.equalsIgnoreCase("BTCUSD_cexio")){

                            cd2b = BTCUSD_cexio.getBid();
                            cd2a = BTCUSD_cexio.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("BTCUSD_binance")){

                            cd2b = BTCUSD_binance.getBid();
                            cd2a = BTCUSD_binance.getAsk();

                        }

                        if(c2.equalsIgnoreCase("BTCUSD_kraken")){

                            cd2b = BTCUSD_kraken.getBid();
                            cd2a = BTCUSD_kraken.getAsk();

                        }
/*
                        if(c2.equalsIgnoreCase("BTCUSD_bitfinex")){

                            cd2b = BTCUSD_bitfinex.getBid();
                            cd2a = BTCUSD_bitfinex.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("BTCUSD_gdax")){

                            cd2b = BTCUSD_gdax.getBid();
                            cd2a = BTCUSD_gdax.getAsk();

                        }

                        if(c2.equalsIgnoreCase("ETHUSD_poloniex")){

                            cd2b = ETHUSD_poloniex.getBid();
                            cd2a = ETHUSD_poloniex.getAsk();

                        }

  /*                      if(c2.equalsIgnoreCase("ETHUSD_cexio")){

                            cd2b = ETHUSD_cexio.getBid();
                            cd2a = ETHUSD_cexio.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("ETHUSD_binance")){

                            cd2b = ETHUSD_binance.getBid();
                            cd2a = ETHUSD_binance.getAsk();

                        }

                        if(c2.equalsIgnoreCase("ETHUSD_kraken")){

                            cd2b = ETHUSD_kraken.getBid();
                            cd2a = ETHUSD_kraken.getAsk();

                        }

 /*                       if(c2.equalsIgnoreCase("ETHUSD_bitfinex")){

                            cd2b = ETHUSD_bitfinex.getBid();
                            cd2a = ETHUSD_bitfinex.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("ETHUSD_gdax")){

                            cd2b = ETHUSD_gdax.getBid();
                            cd2a = ETHUSD_gdax.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XRPUSD_poloniex")){

                            cd2b = XRPUSD_poloniex.getBid();
                            cd2a = XRPUSD_poloniex.getAsk();

                        }

  /*                      if(c2.equalsIgnoreCase("XRPUSD_cexio")){

                            cd2b = XRPUSD_cexio.getBid();
                            cd2a = XRPUSD_cexio.getAsk();

                        }

*/

                        if(c2.equalsIgnoreCase("XRPUSD_kraken")){

                            cd2b = XRPUSD_kraken.getBid();
                            cd2a = XRPUSD_kraken.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XRPEUR_kraken")){

                            cd2b = XRPEUR_kraken.getBid();
                            cd2a = XRPEUR_kraken.getAsk();

                        }

  /*                      if(c2.equalsIgnoreCase("XRPEUR_cexio")){

                            cd2b = XRPEUR_cexio.getBid();
                            cd2a = XRPEUR_cexio.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("XRPBTC_poloniex")){

                            cd2b = XRPBTC_poloniex.getBid();
                            cd2a = XRPBTC_poloniex.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XRPBTC_binance")){

                            cd2b = XRPBTC_binance.getBid();
                            cd2a = XRPBTC_binance.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XRPBTC_kraken")){

                            cd2b = XRPBTC_kraken.getBid();
                            cd2a = XRPBTC_kraken.getAsk();

                        }
/*
                        if(c2.equalsIgnoreCase("XRPBTC_bitfinex")){

                            cd2b = XRPBTC_bitfinex.getBid();
                            cd2a = XRPBTC_bitfinex.getAsk();

                        }
/*/
                        if(c2.equalsIgnoreCase("XRPETH_binance")){

                            cd2b = XRPETH_binance.getBid();
                            cd2a = XRPETH_binance.getAsk();

                        }



                        if(c2.equalsIgnoreCase("XMRBTC_poloniex")){

                            cd2b = XMRBTC_poloniex.getBid();
                            cd2a = XMRBTC_poloniex.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XMRBTC_binance")){

                            cd2b = XMRBTC_binance.getBid();
                            cd2a = XMRBTC_binance.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XMRBTC_kraken")){

                            cd2b = XMRBTC_kraken.getBid();
                            cd2a = XMRBTC_kraken.getAsk();

                        }
/*
                        if(c2.equalsIgnoreCase("XMRBTC_bitfinex")){

                            cd2b = XMRBTC_bitfinex.getBid();
                            cd2a = XMRBTC_bitfinex.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("XMRUSD_poloniex")){

                            cd2b = XMRUSD_poloniex.getBid();
                            cd2a = XMRUSD_poloniex.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XMRUSD_kraken")){

                            cd2b = XMRUSD_kraken.getBid();
                            cd2a = XMRUSD_kraken.getAsk();

                        }

    /*                    if(c2.equalsIgnoreCase("XMRUSD_bitfinex")){

                            cd2b = XMRUSD_bitfinex.getBid();
                            cd2a = XMRUSD_bitfinex.getAsk();

                        }
*/
                        if(c2.equalsIgnoreCase("XMREUR_kraken")){

                            cd2b = XMREUR_kraken.getBid();
                            cd2a = XMREUR_kraken.getAsk();

                        }

                        if(c2.equalsIgnoreCase("XMRETH_binance")){

                            cd2b = XMRETH_binance.getBid();
                            cd2a = XMRETH_binance.getAsk();

                        }

                        //  CD3

                        if(c3.equalsIgnoreCase("ETHBTC_poloniex")){

                            cd3b = ETHBTC_poloniex.getBid();
                            cd3a = ETHBTC_poloniex.getAsk();

                        }

  /*                      if(c3.equalsIgnoreCase("ETHBTC_cexio")){

                            cd3b = ETHBTC_cexio.getBid();
                            cd3a = ETHBTC_cexio.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("ETHBTC_binance")){

                            cd3b = ETHBTC_binance.getBid();
                            cd3a = ETHBTC_binance.getAsk();

                        }

                        if(c3.equalsIgnoreCase("ETHBTC_kraken")){

                            cd3b = ETHBTC_kraken.getBid();
                            cd3a = ETHBTC_kraken.getAsk();

                        }
/*
                        if(c3.equalsIgnoreCase("ETHBTC_bitfinex")){

                            cd3b = ETHBTC_bitfinex.getBid();
                            cd3a = ETHBTC_bitfinex.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("ETHBTC_gdax")){

                            cd3b = ETHBTC_gdax.getBid();
                            cd3a = ETHBTC_gdax.getAsk();

                        }

                        if(c3.equalsIgnoreCase("BTCUSD_poloniex")){

                            cd3b = BTCUSD_poloniex.getBid();
                            cd3a = BTCUSD_poloniex.getAsk();

                        }

  /*                      if(c3.equalsIgnoreCase("BTCUSD_cexio")){

                            cd3b = BTCUSD_cexio.getBid();
                            cd3a = BTCUSD_cexio.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("BTCUSD_binance")){

                            cd3b = BTCUSD_binance.getBid();
                            cd3a = BTCUSD_binance.getAsk();

                        }

                        if(c3.equalsIgnoreCase("BTCUSD_kraken")){

                            cd3b = BTCUSD_kraken.getBid();
                            cd3a = BTCUSD_kraken.getAsk();

                        }
/*
                        if(c3.equalsIgnoreCase("BTCUSD_bitfinex")){

                            cd3b = BTCUSD_bitfinex.getBid();
                            cd3a = BTCUSD_bitfinex.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("BTCUSD_gdax")){

                            cd3b = BTCUSD_gdax.getBid();
                            cd3a = BTCUSD_gdax.getAsk();

                        }

                        if(c3.equalsIgnoreCase("ETHUSD_poloniex")){

                            cd3b = ETHUSD_poloniex.getBid();
                            cd3a = ETHUSD_poloniex.getAsk();

                        }

   /*                     if(c3.equalsIgnoreCase("ETHUSD_cexio")){

                            cd3b = ETHUSD_cexio.getBid();
                            cd3a = ETHUSD_cexio.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("ETHUSD_binance")){

                            cd3b = ETHUSD_binance.getBid();
                            cd3a = ETHUSD_binance.getAsk();

                        }

                        if(c3.equalsIgnoreCase("ETHUSD_kraken")){

                            cd3b = ETHUSD_kraken.getBid();
                            cd3a = ETHUSD_kraken.getAsk();

                        }
/*
                        if(c3.equalsIgnoreCase("ETHUSD_bitfinex")){

                            cd3b = ETHUSD_bitfinex.getBid();
                            cd3a = ETHUSD_bitfinex.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("ETHUSD_gdax")){

                            cd3b = ETHUSD_gdax.getBid();
                            cd3a = ETHUSD_gdax.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XRPUSD_poloniex")){

                            cd3b = XRPUSD_poloniex.getBid();
                            cd3a = XRPUSD_poloniex.getAsk();

                        }

  /*                      if(c3.equalsIgnoreCase("XRPUSD_cexio")){

                            cd3b = XRPUSD_cexio.getBid();
                            cd3a = XRPUSD_cexio.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("XRPUSD_kraken")){

                            cd3b = XRPUSD_kraken.getBid();
                            cd3a = XRPUSD_kraken.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XRPEUR_kraken")){

                            cd3b = XRPEUR_kraken.getBid();
                            cd3a = XRPEUR_kraken.getAsk();

                        }

/*                        if(c3.equalsIgnoreCase("XRPEUR_cexio")){

                            cd3b = XRPEUR_cexio.getBid();
                            cd3a = XRPEUR_cexio.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("XRPBTC_poloniex")){

                            cd3b = XRPBTC_poloniex.getBid();
                            cd3a = XRPBTC_poloniex.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XRPBTC_binance")){

                            cd3b = XRPBTC_binance.getBid();
                            cd3a = XRPBTC_binance.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XRPBTC_kraken")){

                            cd3b = XRPBTC_kraken.getBid();
                            cd3a = XRPBTC_kraken.getAsk();

                        }
/*
                        if(c3.equalsIgnoreCase("XRPBTC_bitfinex")){

                            cd3b = XRPBTC_bitfinex.getBid();
                            cd3a = XRPBTC_bitfinex.getAsk();

                        }
*/

                        if(c3.equalsIgnoreCase("XRPETH_binance")){

                            cd3b = XRPETH_binance.getBid();
                            cd3a = XRPETH_binance.getAsk();

                        }


                        if(c3.equalsIgnoreCase("XMRBTC_poloniex")){

                            cd3b = XMRBTC_poloniex.getBid();
                            cd3a = XMRBTC_poloniex.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XMRBTC_binance")){

                            cd3b = XMRBTC_binance.getBid();
                            cd3a = XMRBTC_binance.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XMRBTC_kraken")){

                            cd3b = XMRBTC_kraken.getBid();
                            cd3a = XMRBTC_kraken.getAsk();

                        }
/*
                        if(c3.equalsIgnoreCase("XMRBTC_bitfinex")){

                            cd3b = XMRBTC_bitfinex.getBid();
                            cd3a = XMRBTC_bitfinex.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("XMRUSD_poloniex")){

                            cd3b = XMRUSD_poloniex.getBid();
                            cd3a = XMRUSD_poloniex.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XMRUSD_kraken")){

                            cd3b = XMRUSD_kraken.getBid();
                            cd3a = XMRUSD_kraken.getAsk();

                        }
/*
                        if(c3.equalsIgnoreCase("XMRUSD_bitfinex")){

                            cd3b = XMRUSD_bitfinex.getBid();
                            cd3a = XMRUSD_bitfinex.getAsk();

                        }
*/
                        if(c3.equalsIgnoreCase("XMREUR_kraken")){

                            cd3b = XMREUR_kraken.getBid();
                            cd3a = XMREUR_kraken.getAsk();

                        }

                        if(c3.equalsIgnoreCase("XMRETH_binance")){

                            cd3b = XMRETH_binance.getBid();
                            cd3a = XMRETH_binance.getAsk();

                        }

                        // Calculate triangular arbitrage


                        if(  // Path 1
                                (c1.substring(3, 6).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(0, 3)))
                                        && (c1.substring(0, 3).equals(c3.substring(3, 6)))){

                            triArbitrage[z] = 100 * ( (1 / cd1b.doubleValue()) * (1 / cd2b.doubleValue()) * (1 / cd3b.doubleValue())  ) - 100;
                            path = 1;

                        }


                        if(   // Path 2
                                (c1.substring(3, 6).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(3, 6)))
                                        && (c1.substring(0, 3).equals(c3.substring(0, 3)))){

                            triArbitrage[z] = 100 * ( (1 / cd1b.doubleValue()) * (1 / cd2b.doubleValue()) * (cd3a.doubleValue())  ) - 100;
                            path = 2;

                        }


                        if(   // Path 3
                                (c1.substring(3, 6).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(0, 3)))
                                        && (c1.substring(0, 3).equals(c3.substring(3, 6)))){

                            triArbitrage[z] = 100 * ( (1 / cd1b.doubleValue()) * (cd2a.doubleValue()) * (1 / cd3b.doubleValue())  ) - 100;
                            path = 3;

                        }


                        if(// Path 4
                                (c1.substring(3, 6).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(3, 6)))
                                        && (c1.substring(0, 3).equals(c3.substring(0, 3)))){

                            triArbitrage[z] = 100 * ( (1 / cd1b.doubleValue()) * (cd2a.doubleValue()) * (cd3a.doubleValue())  ) - 100;
                            path = 4;

                        }


                        if(   // Path 5
                                (c1.substring(0, 3).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(0, 3)))
                                        && (c1.substring(3, 6).equals(c3.substring(3, 6)))){

                            triArbitrage[z] = 100 * ( (cd1a.doubleValue()) * (1 / cd2b.doubleValue()) * (1 / cd3b.doubleValue())  ) - 100;
                            path = 5;

                        }


                        if(   // Path 6
                                (c1.substring(0, 3).equals(c2.substring(0, 3)) && c2.substring(3, 6).equals(c3.substring(3, 6)))
                                        && (c1.substring(3, 6).equals(c3.substring(0, 3)))){

                            triArbitrage[z] = 100 * ( (cd1a.doubleValue()) * (1 / cd2b.doubleValue()) * (cd3a.doubleValue())  ) - 100;
                            path = 6;

                        }


                        if(   // Path 7
                                (c1.substring(0, 3).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(0, 3)))
                                        && (c1.substring(3, 6).equals(c3.substring(3, 6)))){

                            triArbitrage[z] = 100 * ( (cd1a.doubleValue()) * (cd2a.doubleValue()) * (1 / cd3b.doubleValue())  ) - 100;
                            path = 7;

                        }


                        if(   // Path 8
                                (c1.substring(0, 3).equals(c2.substring(3, 6)) && c2.substring(0, 3).equals(c3.substring(3, 6)))
                                        && (c1.substring(3, 6).equals(c3.substring(0, 3)))){

                            triArbitrage[z] = 100 * ( (cd1a.doubleValue()) * (cd2a.doubleValue()) * (cd3a.doubleValue())  ) - 100;
                            path = 8;

                        }


                        //If triangular arbitrage profit is over 5% display
                        if(triArbitrage[z] >= profitTarget){

                                    optimalPathText.append("#" + z + ": " +

                                            ((path == 1)? " SELL " + c1 + " " + " SELL " + c2 + " " + " SELL " + c3 : "") +
                                            ((path == 2)? " SELL " + c1 + " " + " SELL " + c2 + " " + " BUY " + c3 : "") +
                                            ((path == 3)? " SELL " + c1 + " " + " BUY " + c2 + " " + " SELL " + c3 : "") +
                                            ((path == 4)? " SELL " + c1 + " " + " BUY " + c2 + " " + " BUY " + c3 : "") +
                                            ((path == 5)? " BUY " + c1 + " " + " SELL " + c2 + " " + " SELL " + c3 : "") +
                                            ((path == 6)? " BUY " + c1 + " " + " SELL " + c2 + " " + " BUY " + c3 : "") +
                                            ((path == 7)? " BUY " + c1 + " " + " BUY " + c2 + " " + " SELL " + c3 : "") +
                                            ((path == 8)? " BUY " + c1 + " " + " BUY " + c2 + " " + " BUY " + c3 : "") +


                                             " \n" +


                                            "Bid/Ask: "  + cd1b + "/" + cd1a + "\nBid/Ask: " + cd2b + "/" + cd2a + "\nBid/Ask: " + cd3b +
                                    "/" + cd3a + "\n+$" + triArbitrage[z] + "\n\n");


                            System.out.println(optimalPathText.toString());




                           }


                    }

                }

            }

        }



    } // End of triangularArbitrage() method

} // End of Class