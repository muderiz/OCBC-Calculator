/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.services;

import com.google.gson.Gson;
import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.FutureValueRequest;
import com.ocbc.calculator.model.FutureValueResponse;
import com.ocbc.calculator.model.ListProductRequest;
import com.ocbc.calculator.model.ListProductResponse;
import com.ocbc.calculator.model.PresentValueRequest;
import com.ocbc.calculator.model.PresentValueResponse;
import com.ocbc.calculator.model.Product;
import com.ocbc.calculator.model.TargetValueRequest;
import com.ocbc.calculator.model.TargetValueResponse;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.hibernate.validator.internal.util.logging.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cokkyturnip
 */
@Component
public class CalculatorServices {

    @Autowired
    private AppProperties appProp;

    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    public FutureValueResponse calculateGrowth(String refID, String amount, String type, String tenor, int risk_profile_id, String product_type) throws IOException {

        FutureValueRequest reqInvestasi = new FutureValueRequest();
        reqInvestasi.Channel_ID = appProp.Channel_ID;
        reqInvestasi.Ext_Reff_ID = refID;
        reqInvestasi.Due_Date = 0;
        reqInvestasi.Investment_Amount = amount;
        reqInvestasi.Investment_Type = type;
        reqInvestasi.Product_ID = "0";
        reqInvestasi.Risk_Profile_ID = risk_profile_id + "";
        reqInvestasi.Tenor = tenor;

        if (risk_profile_id == 0) {
            if (product_type.equalsIgnoreCase("investasi")) {
                reqInvestasi.Future_Value_Type = "AG";
                reqInvestasi.Yearly_Return_Code = "A";
            } else {
                reqInvestasi.Future_Value_Type = "SV";
                reqInvestasi.Yearly_Return_Code = "C";
            }
        } else {
            if (product_type.equalsIgnoreCase("investasi")) {
                reqInvestasi.Future_Value_Type = "AG";
                reqInvestasi.Yearly_Return_Code = "B";
            } else {
                reqInvestasi.Future_Value_Type = "SV";
                reqInvestasi.Yearly_Return_Code = "C";
            }
        }

        Gson gson = new Gson();
        String jsonInvestasi = gson.toJson(reqInvestasi);
        //get result from ocbc api : /Calculator/FutureValue
        RequestBody body = RequestBody.create(jsonInvestasi, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_FUTUREVALUE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        FutureValueResponse respGrowth = gson.fromJson(response.body().string(), FutureValueResponse.class);
        System.out.println(jsonInvestasi);
        System.out.println(respGrowth);
        return respGrowth;
    }

    public TargetValueResponse calculateEducation(String refID, String age, String country, String value, int risk_profile_id, String product_type) throws IOException {
        int intAge = Integer.parseInt(age);
        int intTenor = 18 - intAge <= 1 ? 1 : 18 - intAge;

        TargetValueRequest targetValueRequest = new TargetValueRequest();
        targetValueRequest.Channel_ID = appProp.Channel_ID;
        targetValueRequest.Children_Age = age;
        targetValueRequest.EC_ID = country;
        targetValueRequest.Due_Date = 0;
        targetValueRequest.Ext_Reff_ID = refID;
        targetValueRequest.Pre_Calculated_Future_Value = "0";
        targetValueRequest.Initial_Amount = value;
        targetValueRequest.Product_ID = "0";
        targetValueRequest.Risk_Profile_ID = risk_profile_id + "";

        targetValueRequest.Tenor = String.valueOf(intTenor);
        targetValueRequest.Future_Value_Code = "A";

        if (risk_profile_id == 0) {
            if (product_type.equalsIgnoreCase("investasi")) {
                targetValueRequest.Yearly_Return_Code = "A";
                targetValueRequest.Payment_Type = "CE";
            } else {
                targetValueRequest.Payment_Type = "SV";
                targetValueRequest.Yearly_Return_Code = "E";
            }
        } else {
            if (product_type.equalsIgnoreCase("investasi")) {
                targetValueRequest.Yearly_Return_Code = "B";
                targetValueRequest.Payment_Type = "CE";

            } else {
                targetValueRequest.Payment_Type = "SV";
                targetValueRequest.Yearly_Return_Code = "E";
            }
        }

        Gson gson = new Gson();
        String jsonTargetValue = gson.toJson(targetValueRequest);

        RequestBody body = RequestBody.create(jsonTargetValue, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_TARGETVALUE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        TargetValueResponse targetValueResponse = gson.fromJson(response.body().string(), TargetValueResponse.class);

        return targetValueResponse;
    }

    public TargetValueResponse calculateEtc(String refID, String present_value, String future_value, String tenor, int risk_profile_id, String product_type) throws IOException {
        TargetValueRequest targetValueRequest = new TargetValueRequest();
        targetValueRequest.Channel_ID = appProp.Channel_ID;
        targetValueRequest.Children_Age = "0";
        targetValueRequest.EC_ID = "0";
        targetValueRequest.Due_Date = 0;
        targetValueRequest.Ext_Reff_ID = refID;
        targetValueRequest.Pre_Calculated_Future_Value = future_value;
        targetValueRequest.Initial_Amount = present_value;
        targetValueRequest.Product_ID = "0";
        targetValueRequest.Risk_Profile_ID = risk_profile_id + "";
        targetValueRequest.Tenor = tenor;

        if (risk_profile_id == 0) {
            if (product_type.equalsIgnoreCase("investasi")) {
                targetValueRequest.Yearly_Return_Code = "C";
                targetValueRequest.Payment_Type = "OG";
                targetValueRequest.Future_Value_Code = "B";
            } else {
                targetValueRequest.Payment_Type = "SV";
                targetValueRequest.Yearly_Return_Code = "E";
                targetValueRequest.Future_Value_Code = "C";
            }
        } else {
            if (product_type.equalsIgnoreCase("investasi")) {
                targetValueRequest.Yearly_Return_Code = "D";
                targetValueRequest.Payment_Type = "OG";
                targetValueRequest.Future_Value_Code = "B";
            } else {
                targetValueRequest.Payment_Type = "SV";
                targetValueRequest.Yearly_Return_Code = "E";
                targetValueRequest.Future_Value_Code = "C";
            }
        }

        Gson gson = new Gson();
        String jsonTargetValue = gson.toJson(targetValueRequest);

        RequestBody body = RequestBody.create(jsonTargetValue, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_TARGETVALUE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        TargetValueResponse targetValueResponse = gson.fromJson(response.body().string(), TargetValueResponse.class);

        return targetValueResponse;
    }

    public ListProductResponse showListProduct(String refID, String amount, int risk_profile_id, String tipe) throws IOException {
        ListProductRequest listProductRequest = new ListProductRequest();
        listProductRequest.Channel_ID = appProp.Channel_ID;
        listProductRequest.Ext_Reff_ID = refID;
        listProductRequest.MFA_List = "20";
        listProductRequest.Nominal_Amount = Long.parseLong(amount);
        listProductRequest.Risk_Profile_ID = risk_profile_id;
        listProductRequest.Tipe = Integer.parseInt(tipe);
        String[] type = new String[1];
        type[0] = "MFB";
        listProductRequest.List_Product_Type = type;

        Gson gson = new Gson();
        String jsonTargetValue = gson.toJson(listProductRequest);

        RequestBody body = RequestBody.create(jsonTargetValue, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_LISTPRODUCT)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        ListProductResponse listProductResponse = gson.fromJson(response.body().string(), ListProductResponse.class);
        for (Product product : listProductResponse.List_Product) {
            product.setMutual_Fund_Type_Desc();
        }
        return listProductResponse;
    }

    public FutureValueResponse reksadanaGrowth(String refID, String amount, String invest_type, String tenor, int risk_profile_id, String product_type, String product_id) throws IOException {

        FutureValueRequest reqInvestasi = new FutureValueRequest();
        reqInvestasi.Channel_ID = appProp.Channel_ID;
        reqInvestasi.Ext_Reff_ID = refID;
        reqInvestasi.Due_Date = 0;
        reqInvestasi.Investment_Amount = amount;
        reqInvestasi.Investment_Type = invest_type;
        reqInvestasi.Product_ID = product_id;
        reqInvestasi.Risk_Profile_ID = risk_profile_id + "";
        reqInvestasi.Tenor = tenor;

        if (product_type.equalsIgnoreCase("TK")) {
            reqInvestasi.Future_Value_Type = "TK";
            reqInvestasi.Yearly_Return_Code = "D";
        } else if (product_type.equalsIgnoreCase("MFA")) {
            reqInvestasi.Future_Value_Type = "MF";
            reqInvestasi.Yearly_Return_Code = "E";
        } else if (product_type.equalsIgnoreCase("MFB")) {
            reqInvestasi.Future_Value_Type = "MF";
            reqInvestasi.Yearly_Return_Code = "E";
        }

        Gson gson = new Gson();
        String jsonInvestasi = gson.toJson(reqInvestasi);
        //get result from ocbc api : /Calculator/FutureValue
        RequestBody body = RequestBody.create(jsonInvestasi, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_FUTUREVALUE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        FutureValueResponse respGrowth = gson.fromJson(response.body().string(), FutureValueResponse.class);
        System.out.println(jsonInvestasi);
        System.out.println(respGrowth);

        return respGrowth;
    }

    public TargetValueResponse reksadanaEtcTarget(String refID, String age, String tenor, String country, String initial_amount, String target_amount, int risk_profile_id, String product_type, int lifegoalId, String productId) throws IOException {
        int intAge = Integer.parseInt(age);
        int intTenor = 18 - intAge <= 1 ? 1 : 18 - intAge;

        TargetValueRequest targetValueRequest = new TargetValueRequest();
        targetValueRequest.Channel_ID = appProp.Channel_ID;

        targetValueRequest.Due_Date = 0;
        targetValueRequest.Ext_Reff_ID = refID;
        targetValueRequest.Pre_Calculated_Future_Value = target_amount;
        targetValueRequest.Initial_Amount = initial_amount;
        targetValueRequest.Product_ID = productId;
        targetValueRequest.Risk_Profile_ID = risk_profile_id + "";
        targetValueRequest.EC_ID = country;
        targetValueRequest.Children_Age = age;

        if (lifegoalId == 2) {
            targetValueRequest.Tenor = String.valueOf(intTenor);
        } else {
            targetValueRequest.Tenor = tenor;
        }

        if (product_type.equalsIgnoreCase("TK")) {
            targetValueRequest.Yearly_Return_Code = "F";
            targetValueRequest.Payment_Type = "TK";
            targetValueRequest.Future_Value_Code = "D";

        } else if (product_type.equalsIgnoreCase("MFA")) {
            targetValueRequest.Yearly_Return_Code = "G";
            targetValueRequest.Payment_Type = "MF";
            targetValueRequest.Future_Value_Code = "E";

        } else if (product_type.equalsIgnoreCase("MFB")) {
            targetValueRequest.Yearly_Return_Code = "G";
            targetValueRequest.Payment_Type = "MF";
            targetValueRequest.Future_Value_Code = "E";

        }

        Gson gson = new Gson();
        String jsonTargetValue = gson.toJson(targetValueRequest);

        RequestBody body = RequestBody.create(jsonTargetValue, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_TARGETVALUE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        TargetValueResponse targetValueResponse = gson.fromJson(response.body().string(), TargetValueResponse.class);

        return targetValueResponse;
    }

    public PresentValueResponse reksadanaEtcPresent(String refID, String age, int lifegoalid, String productID, String tenor, String target_amount, String product_type) throws IOException {
        int intAge = Integer.parseInt(age);
        int intTenor = 18 - intAge <= 1 ? 1 : 18 - intAge;

        PresentValueRequest presentValueRequest = new PresentValueRequest();
        presentValueRequest.Channel_ID = appProp.Channel_ID;
        presentValueRequest.Payment = "0";
        presentValueRequest.Due_Date = 0;
        presentValueRequest.Ext_Reff_ID = refID;
        presentValueRequest.Target_Amount = target_amount;
        presentValueRequest.Product_ID = productID;

        if (lifegoalid == 2) {
            presentValueRequest.Tenor = String.valueOf(intTenor);
        } else {
            presentValueRequest.Tenor = tenor;
        }

        if (product_type.equalsIgnoreCase("DP")) {
            presentValueRequest.Yearly_Return_Code = "B";
            presentValueRequest.Present_Value_Type = "DP";
        } else if (product_type.equalsIgnoreCase("MFA")) {
            presentValueRequest.Yearly_Return_Code = "C";
            presentValueRequest.Present_Value_Type = "MF";
        } else if (product_type.equalsIgnoreCase("MFB")) {
            presentValueRequest.Yearly_Return_Code = "C";
            presentValueRequest.Present_Value_Type = "MF";
        }

        Gson gson = new Gson();
        String jsonPresentValue = gson.toJson(presentValueRequest);

        RequestBody body = RequestBody.create(jsonPresentValue, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_PRESENTVALUE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        PresentValueResponse presentValueResponse = gson.fromJson(response.body().string(), PresentValueResponse.class);

        return presentValueResponse;
    }
}
