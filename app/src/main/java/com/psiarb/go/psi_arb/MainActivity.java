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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "WTF";
    TextView text;
    Button btn;
    SymbolBidAskRates Rate;
    double bid1 = 0;
    double ask1 = 0;
    double ETHBTCb_Poloniex = 0;
    double ETHBTCa_Poloniex = 0;
    double ETHBTCb_CEXio = 0;
    double ETHBTCa_CEXio = 0;
    double ETHBTCb_Binance = 0;
    double ETHBTCa_Binance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.bidask);
        btn = (Button) findViewById(R.id.button);
        Rate = new SymbolBidAskRates();

        text.setMovementMethod(new ScrollingMovementMethod());

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

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

                for(Element step : span){

                    String method = step.select("span").text();
                    buffer.append(method);
                }

                String haystack = buffer.toString();
                calculateBIDASK ETHBTCpoloniex = new calculateBIDASK();

                ETHBTCb_Poloniex = ETHBTCpoloniex.getBid(bid1, haystack);
                ETHBTCa_Poloniex = ETHBTCpoloniex.getAsk(ask1, haystack);

                Rate.setEURBTCb_Poloniex(ETHBTCb_Poloniex);
                Rate.setEURBTCa_Poloniex(ETHBTCa_Poloniex);

                System.out.println("OK");

                doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1054867").get();
                span = doc.select("span");

                buffer.delete(0,buffer.length());

                for(Element step : span){

                    String method = step.select("span").text();
                    buffer.append(method);

                }

                haystack = buffer.toString();

                calculateBIDASK ETHBTCcexio = new calculateBIDASK();

                ETHBTCb_CEXio = ETHBTCcexio.getBid(bid1,haystack);
                ETHBTCa_CEXio = ETHBTCcexio.getAsk(ask1,haystack);

                Rate.setEURBTCb_CEXio(ETHBTCb_CEXio);
                Rate.setEURBTCa_CEXio(ETHBTCa_CEXio);

                System.out.println("OK");

                doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1031692").get();
                span = doc.select("span");

                buffer.delete(0,buffer.length());

                for(Element step : span){

                    String method = step.select("span").text();
                    buffer.append(method);

                }

                haystack = buffer.toString();

                calculateBIDASK ETHBTCbinance = new calculateBIDASK();

                ETHBTCb_Binance = ETHBTCbinance.getBid(bid1,haystack);
                ETHBTCa_Binance = ETHBTCbinance.getAsk(ask1,haystack);

                Rate.setEURBTCb_Binance(ETHBTCb_Binance);
                Rate.setEURBTCa_Binance(ETHBTCa_Binance);

                System.out.println("OK");

                doc = Jsoup.connect("https://www.investing.com/currencies/eth-btc?cid=1031692").get();
                span = doc.select("span");



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

            text.setText("BID: " + Rate.getEURBTCb_Poloniex() + "\nASK: " + Rate.getEURBTCa_Poloniex() + "\t Poloniex\n"
                    + "BID: " + Rate.getEURBTCb_CEXio() + "\nASK: " + Rate.getEURBTCa_CEXio() + "\t CEX.io\n"
                    + "BID: " + Rate.getEURBTCb_Binance() + "\nASK: " + Rate.getEURBTCa_Binance() + "\t 	Binance"
            );

            Toast.makeText(getApplicationContext(),"OK", LENGTH_LONG).show();

        }

    }

}