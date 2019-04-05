package com.example.paytm;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {
    String MercahntKey = "#HBSYbxITnehHuIF";
    private final String merchantKey = "gKpu7IKaLSbkchFS";
    private String paytmChecksum = null;
    private static final String TAG = "MainActivity";
    //the textview in the interface where we have the price
    TextView textViewPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the textview
        textViewPrice = findViewById(R.id.textViewPrice);


        //attaching a click listener to the button buy
        findViewById(R.id.buttonBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateCheckSum();
            }
        });


        //attaching a click listener to the button buy
        findViewById(R.id.buttontesting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateCheckSumTesting();
            }
        });


    }


    private void generateCheckSumTesting() {

        //getting the tax amount first.
        String txnAmount = textViewPrice.getText().toString().trim();

        //creating a retrofit object.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the retrofit api service
        Api apiService = retrofit.create(Api.class);

        //creating paytm object
        //containing all the values required
        final Paytm paytm = new Paytm("7777777777", "username@emailprovider.com", "1.00");

        //creating a call object from the apiService
        Call<PaytmResponse> call = apiService.getpaymenttesting(
                paytm.getMobileno(),
                paytm.getEmail(),
                paytm.getTaxamount());

        //making the call to generate checksum
        call.enqueue(new Callback<PaytmResponse>() {
            @Override
            public void onResponse(Call<PaytmResponse> call, Response<PaytmResponse> response) {

                //once we get the checksum we will initiailize the payment.
                //the method is taking the checksum we got and the paytm object as the parameter
                //initializePaytmPayment(response.body().getChecksumHash(), paytm);


                Log.e(TAG, response.body().toString());


                HashMap<String, String> paramMap = new HashMap<String, String>();
                PaytmResponse paytmResponse = response.body();
                // PaytmResponse paytmResponses=   new PaytmResponse("","","","","","","","","","");
                initializePaytmPaymentTesting(paytmResponse);
            }

            @Override
            public void onFailure(Call<PaytmResponse> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }

    private void initializePaytmPaymentTesting(PaytmResponse paytmResponse) {
        //Testing
        PaytmPGService Service = PaytmPGService.getStagingService();


        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("MID", paytmResponse.getMID());
        paramMap.put("ORDER_ID", paytmResponse.getORDER_ID());
        paramMap.put("CUST_ID", paytmResponse.getCUST_ID());
        paramMap.put("MOBILE_NO", paytmResponse.getMOBILE_NO());

        paramMap.put("EMAIL", paytmResponse.getEMAIL());
        paramMap.put("CHANNEL_ID", paytmResponse.getCHANNEL_ID());
        paramMap.put("TXN_AMOUNT", paytmResponse.getTXN_AMOUNT());
        paramMap.put("WEBSITE", paytmResponse.getWEBSITE());

        paramMap.put("INDUSTRY_TYPE_ID", paytmResponse.getINDUSTRY_TYPE_ID());
        paramMap.put("CALLBACK_URL", paytmResponse.getCALLBACK_URL());
        paramMap.put("CHECKSUMHASH", paytmResponse.getCHECKSUMHASH());


        Log.e(TAG, paytmResponse.getCHECKSUMHASH());
        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(paramMap);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }

    private void generateCheckSum() {

        //getting the tax amount first.
        String txnAmount = textViewPrice.getText().toString().trim();

        //creating a retrofit object.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the retrofit api service
        Api apiService = retrofit.create(Api.class);

        //creating paytm object
        //containing all the values required
        final Paytm paytm = new Paytm("7777777777", "username@emailprovider.com", "1.00");

        //creating a call object from the apiService
        Call<PaytmResponse> call = apiService.getChecksum(
                paytm.getMobileno(),
                paytm.getEmail(),
                paytm.getTaxamount());

        //making the call to generate checksum
        call.enqueue(new Callback<PaytmResponse>() {
            @Override
            public void onResponse(Call<PaytmResponse> call, Response<PaytmResponse> response) {

                //once we get the checksum we will initiailize the payment.
                //the method is taking the checksum we got and the paytm object as the parameter
                //initializePaytmPayment(response.body().getChecksumHash(), paytm);


                Log.e(TAG, response.body().toString());


                HashMap<String, String> paramMap = new HashMap<String, String>();
                PaytmResponse paytmResponse = response.body();
                // PaytmResponse paytmResponses=   new PaytmResponse("","","","","","","","","","");
                initializePaytmPayment(paytmResponse);
            }

            @Override
            public void onFailure(Call<PaytmResponse> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }

    private void initializePaytmPayment(PaytmResponse paytmResponse) {

        //Production
        PaytmPGService Service = PaytmPGService.getProductionService();


        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("MID", paytmResponse.getMID());
        paramMap.put("ORDER_ID", paytmResponse.getORDER_ID());
        paramMap.put("CUST_ID", paytmResponse.getCUST_ID());
        paramMap.put("MOBILE_NO", paytmResponse.getMOBILE_NO());

        paramMap.put("EMAIL", paytmResponse.getEMAIL());
        paramMap.put("CHANNEL_ID", paytmResponse.getCHANNEL_ID());
        paramMap.put("TXN_AMOUNT", paytmResponse.getTXN_AMOUNT());
        paramMap.put("WEBSITE", paytmResponse.getWEBSITE());

        paramMap.put("INDUSTRY_TYPE_ID", paytmResponse.getINDUSTRY_TYPE_ID());
        paramMap.put("CALLBACK_URL", paytmResponse.getCALLBACK_URL());
        paramMap.put("CHECKSUMHASH", paytmResponse.getCHECKSUMHASH());


        Log.e(TAG, paytmResponse.getCHECKSUMHASH());
        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(paramMap);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }


    //all these overriden method is to detect the payment result accordingly
    @Override
    public void onTransactionResponse(Bundle bundle) {
        Log.e(TAG, bundle.toString());
        Toast.makeText(this, bundle.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void networkNotAvailable() {
        Log.e(TAG, "Network error");

        Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {

        Log.e(TAG, s);

        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        Log.e(TAG, s);

    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        Log.e(TAG, s);

    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(this, "Back Pressed", Toast.LENGTH_LONG).show();
        Log.e(TAG, "Back Pressed");

    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();

        Log.e(TAG, "TransactionSucccess+ " + s + bundle.toString());

    }


}