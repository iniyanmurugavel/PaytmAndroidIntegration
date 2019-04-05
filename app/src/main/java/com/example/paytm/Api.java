package com.example.paytm;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {



    //this is the URL of the paytm folder that we added in the server
    //make sure you are using your ip else it will not work
    String BASE_URL = "http://192.168.1.15:8099/paytm/";

    @FormUrlEncoded
    @POST("payment")
    Call<PaytmResponse> getChecksum(
            @Field("MOBILE_NO") String mobileno,
            @Field("EMAIL") String emailid,
            @Field("TXN_AMOUNT") String taxamount);





    @FormUrlEncoded
    @POST("paymenttesting")
    Call<PaytmResponse> getpaymenttesting(
            @Field("MOBILE_NO") String mobileno,
            @Field("EMAIL") String emailid,
            @Field("TXN_AMOUNT") String taxamount);

}