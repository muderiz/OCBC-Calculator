/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

//import org.springframework.stereotype.Controller;
import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.FutureValueRequest;
import com.ocbc.calculator.model.FutureValueResponse;
import com.ocbc.calculator.model.ListProductRequest;
import com.ocbc.calculator.model.ListProductResponse;
import com.ocbc.calculator.model.PresentValueResponse;
import com.ocbc.calculator.model.Product;
import com.ocbc.calculator.model.TargetValueRequest;
import com.ocbc.calculator.model.TargetValueResponse;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cokkyturnip
 */
@RestController
@RequestMapping("/Calculator")
public class DummyAPIController {

    @Autowired
    AppProperties appProperties;

    @PostMapping("/TargetValue")
    public TargetValueResponse getTargetValue(@RequestBody TargetValueRequest request) {
        return new TargetValueResponse("1", "testid", "0", "Success",
                "2.0", "1000000", "0", "123123123");
    }

    @PostMapping("/PresentValue")
    public PresentValueResponse getPresentValue(@RequestBody PresentValueResponse request) {
        return new PresentValueResponse("1", "testid", "0", "Success",
                "2.0", "0", "123123123");
    }

    @PostMapping("/FutureValue")
    public FutureValueResponse getFutureValue(@RequestBody FutureValueRequest request) {
        return new FutureValueResponse("1", "testid", "0", "Success", "2.0", "1200000");
    }

    @PostMapping("/ListProduct")
    public ListProductResponse getListProduct(@RequestBody ListProductRequest request) {

        ListProductResponse response = new ListProductResponse();
        response.Channel_ID = appProperties.Channel_ID;
        response.Ext_Reff_ID = "test123";
        response.RC = "0";
        response.RC_Description = "Success";

        Product objProduct = new Product();
        objProduct.Product_Type = "MFB";
        objProduct.Product_ID = "16";
        objProduct.Product_Name = "Aberdeen Standard Ind Money Market";
        objProduct.Product_Rate = "0.00";
        objProduct.Mutual_Fund_Code = "NISDAT2  ";
        objProduct.Standard_Deviation = 0.0121;
        objProduct.Average_Rate = "7.24";
        objProduct.Bad_Rate = "6.3";
        objProduct.Good_Rate = "8.5";
        objProduct.Performance_of_1_month = "1.02";
        objProduct.Performance_of_6_month = "3.98";
        objProduct.Performance_of_12_month = "4.90";
        objProduct.Performance_of_60_month = "41.65";
        objProduct.YTD = 0.035;
        objProduct.Mutual_Fund_Nav = "2127.19";
        objProduct.Mutual_Fund_Risk_Profile_ID = 1;
        objProduct.Mutual_Fund_Type = 1;

        response.List_Product.add(objProduct);

        objProduct = new Product();
        objProduct.Product_Type = "MFB";
        objProduct.Product_ID = "16";
        objProduct.Product_Name = "Ashmore Dana Pasar Uang Nusantara";
        objProduct.Product_Rate = "0.00";
        objProduct.Mutual_Fund_Code = "NISDAT2  ";
        objProduct.Standard_Deviation = 0.0121;
        objProduct.Average_Rate = "7.24";
        objProduct.Bad_Rate = "6.3";
        objProduct.Good_Rate = "8.5";
        objProduct.Performance_of_1_month = "2.02";
        objProduct.Performance_of_6_month = "3.98";
        objProduct.Performance_of_12_month = "4.90";
        objProduct.Performance_of_60_month = "41.65";
        objProduct.YTD = 0.035;
        objProduct.Mutual_Fund_Nav = "2127.19";
        objProduct.Mutual_Fund_Risk_Profile_ID = 2;
        objProduct.Mutual_Fund_Type = 1;

        response.List_Product.add(objProduct);

        objProduct = new Product();
        objProduct.Product_Type = "MFB";
        objProduct.Product_ID = "16";
        objProduct.Product_Name = "BNP Paribas Rupiah Plus";
        objProduct.Product_Rate = "0.00";
        objProduct.Mutual_Fund_Code = "NISDAT2  ";
        objProduct.Standard_Deviation = 0.0121;
        objProduct.Average_Rate = "7.24";
        objProduct.Bad_Rate = "6.3";
        objProduct.Good_Rate = "8.5";
        objProduct.Performance_of_1_month = "14.02";
        objProduct.Performance_of_6_month = "3.98";
        objProduct.Performance_of_12_month = "4.90";
        objProduct.Performance_of_60_month = "41.65";
        objProduct.YTD = 0.035;
        objProduct.Mutual_Fund_Nav = "2127.19";
        objProduct.Mutual_Fund_Risk_Profile_ID = 4;
        objProduct.Mutual_Fund_Type = 1;

        response.List_Product.add(objProduct);

        objProduct = new Product();
        objProduct.Product_Type = "MFB";
        objProduct.Product_ID = "16";
        objProduct.Product_Name = "Aberdeen Standard Ind Bond Fund";
        objProduct.Product_Rate = "0.00";
        objProduct.Mutual_Fund_Code = "NISDAT2  ";
        objProduct.Standard_Deviation = 0.0121;
        objProduct.Average_Rate = "7.24";
        objProduct.Bad_Rate = "6.3";
        objProduct.Good_Rate = "8.5";
        objProduct.Performance_of_1_month = "8.02";
        objProduct.Performance_of_6_month = "3.98";
        objProduct.Performance_of_12_month = "4.90";
        objProduct.Performance_of_60_month = "41.65";
        objProduct.YTD = 0.035;
        objProduct.Mutual_Fund_Nav = "2127.19";
        objProduct.Mutual_Fund_Risk_Profile_ID = 2;
        objProduct.Mutual_Fund_Type = 2;

        response.List_Product.add(objProduct);

        objProduct = new Product();
        objProduct.Product_Type = "MFB";
        objProduct.Product_ID = "16";
        objProduct.Product_Name = "Schroder Dana Terpadu II";
        objProduct.Product_Rate = "0.00";
        objProduct.Mutual_Fund_Code = "NISDAT2  ";
        objProduct.Standard_Deviation = 0.0121;
        objProduct.Average_Rate = "7.24";
        objProduct.Bad_Rate = "6.3";
        objProduct.Good_Rate = "8.5";
        objProduct.Performance_of_1_month = "6.02";
        objProduct.Performance_of_6_month = "3.98";
        objProduct.Performance_of_12_month = "4.90";
        objProduct.Performance_of_60_month = "41.65";
        objProduct.YTD = 0.035;
        objProduct.Mutual_Fund_Nav = "2127.19";
        objProduct.Mutual_Fund_Risk_Profile_ID = 3;
        objProduct.Mutual_Fund_Type = 3;

        response.List_Product.add(objProduct);

        objProduct = new Product();
        objProduct.Product_Type = "MFB";
        objProduct.Product_ID = "16";
        objProduct.Product_Name = "Schroder Dana Prestasi Plus";
        objProduct.Product_Rate = "0.00";
        objProduct.Mutual_Fund_Code = "NISDAT2  ";
        objProduct.Standard_Deviation = 0.0121;
        objProduct.Average_Rate = "7.24";
        objProduct.Bad_Rate = "6.3";
        objProduct.Good_Rate = "8.5";
        objProduct.Performance_of_1_month = "9.02";
        objProduct.Performance_of_6_month = "3.98";
        objProduct.Performance_of_12_month = "4.90";
        objProduct.Performance_of_60_month = "41.65";
        objProduct.YTD = 0.035;
        objProduct.Mutual_Fund_Nav = "2127.19";
        objProduct.Mutual_Fund_Risk_Profile_ID = 4;
        objProduct.Mutual_Fund_Type = 4;

        response.List_Product.add(objProduct);

        return response;
    }
}
