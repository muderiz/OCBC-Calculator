/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.ListProductResponse;
import com.ocbc.calculator.model.Product;
import com.ocbc.calculator.services.CalculatorServices;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller List Product Reksadana Vebwiew
 * @author cokkyturnip
 */
@Controller
@RequestMapping("/product")
public class ListProductController {

    @Autowired
    private CalculatorServices calculatorServices;

    @Autowired
    private AppProperties appProp;

    // >> /product/refID/123123123/1/2
    // >> /product/refID/123123123/2/2
    // >> /product/refID/1/50000000/3/2/2/1/5000000/2/Deka
    // >> /product?refid=...&amount=...&4/2
    @GetMapping("/{refID}/{lifegoalid}/{amount}/{tenor}/{risk_profile_id}/{tipe}/{investment_type}/{initial_amount}/{country}/{nama}")
    public String product(Model model,
            @PathVariable String refID,
            @PathVariable int lifegoalid,
            @PathVariable String amount,
            @PathVariable String tenor,
            @PathVariable int risk_profile_id,
            @PathVariable String tipe,
            @PathVariable String investment_type,
            @PathVariable String initial_amount,
            @PathVariable String country,
            @PathVariable String nama
    ) throws IOException {

        //ambil list product    
        ListProductResponse listProductResponse = calculatorServices.showListProduct(refID, amount, risk_profile_id, tipe);

        List<Product> listProduct = listProductResponse.List_Product;

        for (Product product : listProduct) {
            double koma = Math.pow(10, 2);
            product.YTD = Math.round(product.YTD * koma) / koma;

            DecimalFormat decimalFormat = new DecimalFormat("");
            product.Mutual_Fund_Nav = decimalFormat.format(Double.parseDouble(product.Mutual_Fund_Nav));
        }

        List<Product> listConservative = listProduct.stream()
                .filter(product -> product.Mutual_Fund_Risk_Profile_ID == 1)
                .collect(Collectors.toList());

        List<Product> listBalance = listProduct.stream()
                .filter(product -> product.Mutual_Fund_Risk_Profile_ID == 2)
                .collect(Collectors.toList());

        List<Product> listGrowth = listProduct.stream()
                .filter(product -> product.Mutual_Fund_Risk_Profile_ID == 3)
                .collect(Collectors.toList());

        List<Product> listAggresive = listProduct.stream()
                .filter(product -> product.Mutual_Fund_Risk_Profile_ID == 4)
                .collect(Collectors.toList());

        String risk_profile_desc = "";
//        switch (risk_profile_id) {
//            case 1:
//                risk_profile_desc = "CONSERVATIVE";
//                break;
//            case 2:
//                risk_profile_desc = "BALANCE";
//                break;
//            case 3:
//                risk_profile_desc = "GROWTH";
//                break;
//            case 4:
//                risk_profile_desc = "AGGRESIVE";
//                break;
//        }
        switch (risk_profile_id) {
            case 1:
                risk_profile_desc = "CONSERVATIVE";
                break;
            case 2:
                risk_profile_desc = "BALANCE";
                break;
            case 3:
                risk_profile_desc = "GROWTH";
                break;
            case 4:
                risk_profile_desc = "AGGRESSIVE";
                break;
            default:
                break;
        }

        String urlBack = "";
        if (lifegoalid == 1) {
            urlBack = lifegoalid + "/" + tipe + "/" + initial_amount + "/" + country + "/" + risk_profile_id + "/" + nama;
        } else {
            urlBack = tipe + "/" + risk_profile_id + "/" + nama;
        }
        model.addAttribute("refID", refID);
        model.addAttribute("lifegoalid", lifegoalid);
        model.addAttribute("initial_amount", initial_amount);
        model.addAttribute("country", country);
        model.addAttribute("nama", nama);
        model.addAttribute("listConservative", listConservative);
        model.addAttribute("listBalance", listBalance);
        model.addAttribute("listGrowth", listGrowth);
        model.addAttribute("listAggresive", listAggresive);
        model.addAttribute("investmenttype", investment_type);
        model.addAttribute("risk_profile_id", risk_profile_id);
        model.addAttribute("risk_profile_desc", risk_profile_desc);
        model.addAttribute("idchannel", appProp.IdLiveChat);
        model.addAttribute("urlBack", urlBack);

        return "product";
    }

}
