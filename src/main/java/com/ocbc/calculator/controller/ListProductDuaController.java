/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.FutureValueResponse;
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
 *
 * @author Deka
 */
@Controller
@RequestMapping("/productfinal")
public class ListProductDuaController {

    @Autowired
    private CalculatorServices calculatorServices;

    @Autowired
    private AppProperties appProp;

//    /growth/sdf/sfd/5/5/5/1/500000/5/1/0/TK/16
    @GetMapping("/growth/{refID}/{namareksa}/{averagerate}/{badrate}/{goodrate}/{amount}/{tenor}/{risk_profile_id}/{invest_type}/{producttype}/{productid}")
    public String finalGrowth(Model model,
            @PathVariable String refID,
            @PathVariable String namareksa,
            @PathVariable String averagerate,
            @PathVariable String badrate,
            @PathVariable String goodrate,
            @PathVariable String amount,
            @PathVariable String tenor,
            @PathVariable int risk_profile_id,
            @PathVariable String invest_type,
            @PathVariable String producttype,
            @PathVariable String productid) throws IOException {

        String typeDesc;
        if (invest_type.equalsIgnoreCase("0")) {
            typeDesc = "Annual";
            invest_type = "false";
        } else {
            typeDesc = "Lumpsum";
            invest_type = "true";
        }
        FutureValueResponse respReksadanaGrowth = calculatorServices.reksadanaGrowth(refID, amount, invest_type, tenor, risk_profile_id, producttype, productid);
        String rate = averagerate + "% (" + badrate + "% - " + goodrate + "%)";

        DecimalFormat decimalFormat = new DecimalFormat("");
        String result = decimalFormat.format(Double.parseDouble(respReksadanaGrowth.Result));

        model.addAttribute("rate", rate);
        model.addAttribute("resultamount", result);
        model.addAttribute("namareksadana", namareksa);
        model.addAttribute("typeDesc", typeDesc);

        return "productFinalGrowth";
    }

    @GetMapping("/etc/{refID}/{namareksa}/{averagerate}/{badrate}/{goodrate}/{lifegoal}/{amount}/{tenor}/{risk_profile_id}/{invest_type}/{producttype}/{productid}")
    public String finalEtc(Model model,
            @PathVariable String refID,
            @PathVariable String namareksa,
            @PathVariable String averagerate,
            @PathVariable String badrate,
            @PathVariable String goodrate,
            @PathVariable int lifegoal,
            @PathVariable String amount,
            @PathVariable String tenor,
            @PathVariable int risk_profile_id,
            @PathVariable String invest_type,
            @PathVariable String producttype,
            @PathVariable String productid) throws IOException {

        FutureValueResponse respReksadanaGrowth = calculatorServices.reksadanaGrowth(refID, amount, invest_type, tenor, risk_profile_id, producttype, productid);

        model.addAttribute("lifegoal", lifegoal);
        model.addAttribute("namareksadana", lifegoal);

        return "productFinal";
    }
}
