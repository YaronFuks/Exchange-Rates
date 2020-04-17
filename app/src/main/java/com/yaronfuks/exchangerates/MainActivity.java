package com.yaronfuks.exchangerates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView lastUpdate;
    TextView usdPrice;
    TextView eurPrice;
    TextView cnyPrice;
    TextView jpyPrice;
    TextView gbpPrice;
    TextView rubPrice;
    TextView inrPrice;
    TextView tryPrice;
    TextView chfPrice;
    String dayDate;
    String monthDate;
    String yearDate;
    String ils;
    String eur;
    String cny;
    String jpy;
    String gbp;
    String rub;
    String inr;
    String tury;
    String chf;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                String rates = jsonObject.getString("rates");
                String date = jsonObject.getString("date");

                String str[] = date.split("-");
                int year = Integer.parseInt(str[0]);
                int month = Integer.parseInt(str[1]);
                int day = Integer.parseInt(str[2]);


                JSONObject jsonObjectRates = new JSONObject(rates);

                ils = jsonObjectRates.getString("ILS");
                float numberIls = Float.parseFloat(ils);
                String numberAsStringIls = String.format("%.3f", numberIls);
                // System.out.println(numberAsStringIls);


                eur = jsonObjectRates.getString("EUR");
                float numberEur = Float.parseFloat(eur);
                numberEur = numberIls / numberEur;
                String numberAsStringEur = String.format("%.3f", numberEur);
                // System.out.println(numberAsStringEur);


                cny = jsonObjectRates.getString("CNY");
                float numberCny = Float.parseFloat(cny);
                numberCny = numberIls / numberCny;
                String numberAsStringCny = String.format("%.3f", numberCny);
                // System.out.println(numberAsStringEur);


                jpy = jsonObjectRates.getString("JPY");
                float numberJpy = Float.parseFloat(jpy);
                numberJpy = numberIls / numberJpy;
                String numberAsStringJpy = String.format("%.3f", numberJpy);
                // System.out.println(numberAsStringEur);

                gbp = jsonObjectRates.getString("GBP");
                float numberGbp = Float.parseFloat(gbp);
                numberGbp = numberIls / numberGbp;
                String numberAsStringGbp = String.format("%.3f", numberGbp);
                // System.out.println(numberAsStringEur);



                rub = jsonObjectRates.getString("RUB");
                float numberRub = Float.parseFloat(rub);
                String numberAsStringRub = String.format("%.3f", numberRub);
                // System.out.println(numberAsStringRub);


                inr = jsonObjectRates.getString("INR");
                float numberInr = Float.parseFloat(inr);
                numberInr = numberIls / numberInr;
                String numberAsStringInr = String.format("%.3f", numberInr);
                // System.out.println(numberAsStringEur);


                tury = jsonObjectRates.getString("TRY");
                float numberTry = Float.parseFloat(tury);
                numberTry = numberIls / numberTry;
                String numberAsStringTry = String.format("%.3f", numberTry);
                // System.out.println(numberAsStringEur);


                chf = jsonObjectRates.getString("CHF");
                float numberChf = Float.parseFloat(chf);
                numberChf = numberIls / numberChf;
                String numberAsStringChf = String.format("%.3f", numberChf);
                // System.out.println(numberAsStringEur);




                dayDate = Integer.toString(day);
                monthDate = Integer.toString(month);
                yearDate = Integer.toString(year);
//                Log.i("Website Content - ILS", ils);
//                Log.i("Website Content - EUR", euro);
//                Log.i("Website Content - RUB", ruble);
//                Log.i("Website Content - Date", date);
//                Log.i("Website Content - Date", Integer.toString(day));
//                Log.i("Website Content - Date", Integer.toString(month));
//                Log.i("Website Content - Date", Integer.toString(year));

                lastUpdate.setText("Last Updated: " + dayDate + "-" + monthDate + "-" + yearDate);
                usdPrice.setText(numberAsStringIls);
                eurPrice.setText(numberAsStringEur);
                cnyPrice.setText(numberAsStringCny);
                jpyPrice.setText(numberAsStringJpy);
                gbpPrice.setText(numberAsStringGbp);
                rubPrice.setText(numberAsStringRub);
                inrPrice.setText(numberAsStringInr);
                tryPrice.setText(numberAsStringTry);
                chfPrice.setText(numberAsStringChf);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("Website Content", result);


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float myFloat = 0.00f;

        String formattedString = String.format("%.02f", myFloat);
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://api.openrates.io/latest?base=USD");

        lastUpdate = findViewById(R.id.lastUpdateTextView);
        usdPrice = findViewById(R.id.usdPrice);
        eurPrice = findViewById(R.id.eurPrice);
        rubPrice = findViewById(R.id.rubPrice);
        cnyPrice = findViewById(R.id.cnyPrice);
        jpyPrice = findViewById(R.id.jpyPrice);
        gbpPrice = findViewById(R.id.gbpPrice);
        inrPrice = findViewById(R.id.inrPrice);
        tryPrice = findViewById(R.id.tryPrice);
        chfPrice = findViewById(R.id.chfPrice);


    }


}
