/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.services;

import com.google.gson.Gson;
import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.FeatureValueRequest;
import com.ocbc.calculator.model.FeatureValueResponse;
import com.ocbc.calculator.model.ListProductRequest;
import com.ocbc.calculator.model.ListProductResponse;
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

    public FeatureValueResponse calculateGrowth(String refID, String amount, String type, String tenor, int risk_profile_id, String product_type) throws IOException {

        FeatureValueRequest reqInvestasi = new FeatureValueRequest();
        reqInvestasi.Channel_ID = appProp.Channel_ID;
        reqInvestasi.Ext_Reff_ID = refID;
        reqInvestasi.Due_Date = "0";
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

        //get result from ocbc api : /Calculator/FeatureValue
        RequestBody body = RequestBody.create(jsonInvestasi, JSON);

        Request request = new Request.Builder()
                .url(appProp.BASE_URL + appProp.POST_FEATUREVALUE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        FeatureValueResponse respGrowth = gson.fromJson(response.body().string(), FeatureValueResponse.class);

        return respGrowth;
    }

    public TargetValueResponse calculateEducation(String refID, String age, String country, String value, int risk_profile_id, String product_type) throws IOException {
        TargetValueRequest targetValueRequest = new TargetValueRequest();
        targetValueRequest.Channel_ID = appProp.Channel_ID;
        targetValueRequest.Children_Age = age;
        targetValueRequest.Country = country;
        targetValueRequest.Due_Date = "0";
        targetValueRequest.Ext_Reff_ID = refID;
        targetValueRequest.Pre_Calculated_Future_Value = "0";
        targetValueRequest.Present_Value = value;
        targetValueRequest.Product_ID = "0";
        targetValueRequest.Risk_Profile_ID = risk_profile_id + "";
        targetValueRequest.Tenor = age;
        targetValueRequest.Future_Value_Code = "A";

        if (risk_profile_id == 0) {
            if (product_type.equalsIgnoreCase("investasi")) {
                targetValueRequest.Yearly_Return_Code = "A";
                targetValueRequest.Payment_Type = "CE";
            } else {
                targetValueRequest.Payment_Type = "SV";
                targetValueRequest.Yearly_Return_Code = "C";
            }
        } else {
            if (product_type.equalsIgnoreCase("investasi")) {
                targetValueRequest.Yearly_Return_Code = "B";
                targetValueRequest.Payment_Type = "CE";

            } else {
                targetValueRequest.Payment_Type = "SV";
                targetValueRequest.Yearly_Return_Code = "C";
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
        targetValueRequest.Country = "";
        targetValueRequest.Due_Date = "0";
        targetValueRequest.Ext_Reff_ID = refID;
        targetValueRequest.Pre_Calculated_Future_Value = future_value;
        targetValueRequest.Present_Value = present_value;
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

    public ListProductResponse showListProduct(String refID, int amount, int risk_profile_id, int tipe) throws IOException {
        ListProductRequest listProductRequest = new ListProductRequest();
        listProductRequest.Channel_ID = appProp.Channel_ID;
        listProductRequest.Ext_Reff_ID = refID;
        listProductRequest.MFA_list = "20";
        listProductRequest.Nominal_Amount = amount;
        listProductRequest.Risk_Profile_ID = risk_profile_id;
        listProductRequest.Tipe = tipe;

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

}
