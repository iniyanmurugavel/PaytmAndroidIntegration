package com.example.paytm;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Paytm {

    @SerializedName("MOBILE_NO")
    String mobileno;

    @SerializedName("EMAIL")
    String email;
    @SerializedName("TXN_AMOUNT")
    String taxamount;
    public String getMobileno() {
        return mobileno;
    }

    public Paytm(String mobileno, String email, String taxamount) {
        this.mobileno = mobileno;
        this.email = email;
        this.taxamount = taxamount;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(String taxamount) {
        this.taxamount = taxamount;
    }






//    @SerializedName("CHANNEL_ID")
//    String channelId;
//
//    @SerializedName("TXN_AMOUNT")
//    String txnAmount;
//
//    @SerializedName("WEBSITE")
//    String website;
//
//    @SerializedName("CALLBACK_URL")
//    String callBackUrl;
//
//    @SerializedName("INDUSTRY_TYPE_ID")
//    String industryTypeId;
//
//    public Paytm(String mId, String channelId, String txnAmount, String website, String callBackUrl, String industryTypeId) {
//        this.mId = mId;
//        this.orderId = generateString();
//        this.custId = generateString();
//        this.channelId = channelId;
//        this.txnAmount = txnAmount;
//        this.website = website;
//        this.callBackUrl = callBackUrl;
//        this.industryTypeId = industryTypeId;
//
//        Log.d("orderId", orderId);
//        Log.d("customerId", custId);
//    }
//
//    public String getmId() {
//        return mId;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public String getCustId() {
//        return custId;
//    }
//
//    public String getChannelId() {
//        return channelId;
//    }
//
//    public String getTxnAmount() {
//        return txnAmount;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public String getCallBackUrl() {
//        return callBackUrl;
//    }
//
//    public String getIndustryTypeId() {
//        return industryTypeId;
//    }
//
//    /*
//     * The following method we are using to generate a random string everytime
//     * As we need a unique customer id and order id everytime
//     * For real scenario you can implement it with your own application logic
//     * */
//    private String generateString() {
//        String uuid = UUID.randomUUID().toString();
//        return uuid.replaceAll("-", "");
//    }
}