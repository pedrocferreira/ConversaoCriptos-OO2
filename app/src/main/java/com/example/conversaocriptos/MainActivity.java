package com.example.conversaocriptos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private WebClientTask mTask;
    private TextView mTvBtc;
    private TextView mTvEth;
    private TextView mTvLtc;
    private TextView mTvXrp;
    private ArrayList<Coin> cotacaoes;
    private  Coin  mBTC;
    private Coin  mETH;
    private Coin  mLTC ;
    private Coin  mXRP;

    private  EditText valorMoeda;
    private TextView valorBtc;
    private TextView valorEth;
    private TextView valorXrp;
    private TextView valorLtc;

    private TextView tx;
    private double result;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebClient requisicao= new WebClient();
        startDownload();
        mTvBtc= findViewById(R.id.tv_value_btc);
        mTvLtc= findViewById(R.id.tv_value_ltc);
        mTvXrp= findViewById(R.id.tv_value_xrp);
        mTvEth= findViewById(R.id.tv_value_eth);

        valorMoeda = (EditText)findViewById(R.id.valorMoeda);
        valorBtc = (TextView)findViewById(R.id.tv_value_btc);
        valorEth = (TextView)findViewById(R.id.tv_value_eth);
        valorXrp = (TextView)findViewById(R.id.tv_value_xrp);
        valorLtc = (TextView)findViewById(R.id.tv_value_ltc);

        tx = (TextView)findViewById(R.id.resultadoCalculo);

        //checkButton
            radioGroup= findViewById(R.id.radioGroup);

   //mBtn.setOnClickListener(new View.OnClickListener() {
     //   @Override
       //     public void onClick(View v) {
          //      if (mBTC!=null){
         //       Toast.makeText(getApplicationContext(),mBTC.getStringBuy(),Toast.LENGTH_LONG).show();
         //       }else {
          //          Toast.makeText(getApplicationContext(),"0.00",Toast.LENGTH_LONG).show();
            //    }
         //   }
       // });















    }
    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        Toast.makeText(this,"Select Radio Button"+radioButton.getText(),Toast.LENGTH_SHORT).show();
    }



    protected void clickOk( View view){
        String radioButtomSelect = "";
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        Toast.makeText(this,radioButton.getText(),Toast.LENGTH_SHORT).show();
        radioButtomSelect = radioButton.getText().toString();
        switch (radioButtomSelect){
            case "BTC":
                double valorbtc = Double.parseDouble(
                        valorBtc.getText().toString());

                double valortext = Double.parseDouble(
                        valorMoeda.getText().toString());

                this.result = valortext / valorbtc;
                this.result = Double.valueOf(String.format(Locale.US, "%.8f", result));

                tx.setText((String.valueOf(result)));
                break;


            case "ETH":
                double valoreth = Double.parseDouble(
                        valorEth.getText().toString());

                 valortext = Double.parseDouble(
                        valorMoeda.getText().toString());

                this.result = valortext / valoreth;
                this.result = Double.valueOf(String.format(Locale.US, "%.8f", result));
                tx.setText((String.valueOf(result)));
                break;


            case "XRP":
                double valorxrp = Double.parseDouble(
                        valorXrp.getText().toString());

                valortext = Double.parseDouble(
                        valorMoeda.getText().toString());

                this.result = valortext / valorxrp;
                this.result = Double.valueOf(String.format(Locale.US, "%.8f", result));
                tx.setText((String.valueOf(result)));
                break;


            case "LTC":
                double valorltc = Double.parseDouble(
                        valorLtc.getText().toString());

                valortext = Double.parseDouble(
                        valorMoeda.getText().toString());

                this.result = valortext / valorltc;
                this.result = Double.valueOf(String.format(Locale.US, "%.8f", result));
                tx.setText((String.valueOf(result)));
                break;




        }




    }





    /*Não é necessario mexer aqui */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {

            Toast.makeText(this,"Loading Preços",Toast.LENGTH_LONG).show();
            startDownload();


        }
        return true;
    }


    public void startDownload() {
        if (mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING) {
            mTask = new WebClientTask();
            mTask.execute();
        }
    }

    class  WebClientTask extends AsyncTask<Void,Void, ArrayList<Coin>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Pronto...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected ArrayList<Coin> doInBackground(Void... strings) {
            ArrayList<Coin> coinsList=new ArrayList<>();
            mBTC = WebClient.getCoin("BTC");
            mETH = WebClient.getCoin("ETH");
            mLTC = WebClient.getCoin("LTC");
            mXRP = WebClient.getCoin("XRP");
            coinsList.add(mBTC);
            coinsList.add(mETH);
            coinsList.add(mLTC);
            coinsList.add(mXRP);
            cotacaoes=coinsList;
            Log.i("BTC",cotacaoes.get(0).getStringBuy());
            Log.i("ETH",cotacaoes.get(1).getStringBuy());
            Log.i("XRP",cotacaoes.get(2).getStringBuy());
            Log.i("LTC",cotacaoes.get(3).getStringBuy());
            return coinsList;
        }

        @Override
        protected void onPostExecute(ArrayList<Coin> coins) {
            super.onPostExecute(coins);
            //     showProgress(false);
            if (coins != null) {

                mTvBtc.setText(coins.get(0).getStringBuy());
                mTvEth.setText(coins.get(1).getStringBuy());
                mTvLtc.setText(coins.get(2).getStringBuy());
                mTvXrp.setText(coins.get(3).getStringBuy());
                Toast.makeText(getApplicationContext(), "Valores Atualizados", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_LONG).show();
            }
        }
    }
}

