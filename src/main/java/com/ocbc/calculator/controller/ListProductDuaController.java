/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.FutureValueResponse;
import com.ocbc.calculator.model.ListProductResponse;
import com.ocbc.calculator.model.PresentValueResponse;
import com.ocbc.calculator.model.Product;
import com.ocbc.calculator.model.TargetValueRequest;
import com.ocbc.calculator.model.TargetValueResponse;
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
@RequestMapping("/product")
public class ListProductDuaController {

    @Autowired
    private CalculatorServices calculatorServices;

    @Autowired
    private AppProperties appProp;

//    /growth/sdf/sfd/5/5/5/500000/5/1/0/TK/16
    @GetMapping("/finalgrowth/{refID}/{namareksa}/{averagerate}/{badrate}/{goodrate}/{amount}/{tenor}/{risk_profile_id}/{invest_type}/{producttype}/{productid}")
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
            typeDesc = "Bulanan";
            invest_type = "false";
        } else {
            typeDesc = "Lumpsum";
            invest_type = "true";
        }
        FutureValueResponse respReksadanaGrowth = calculatorServices.reksadanaGrowth(refID, amount, invest_type, tenor, risk_profile_id, producttype, productid);
        String rate = averagerate + "% (" + badrate + "% - " + goodrate + "%)";

        DecimalFormat decimalFormat = new DecimalFormat("");
        String result = decimalFormat.format(Double.parseDouble(respReksadanaGrowth.Result));

        model.addAttribute("namareksadana", namareksa);
        model.addAttribute("typeDesc", typeDesc);
        model.addAttribute("rate", rate);
        model.addAttribute("resultamount", result);
        model.addAttribute("idchannel", appProp.IdLiveChat);

        return "productFinalGrowth";
    }

    // /etc/asd/2/3/3/400000000/Mocking Jay/5/4/5/400000/4/1/3/MFB/14
    @GetMapping("/finaletc/{refID}/{lifegoalId}/{country}/{target_amount}/{namareksa}/{averagerate}/{badrate}/{goodrate}/{amount}/{tenor}/{risk_profile_id}/{invest_type}/{producttype}/{productid}")
    public String finalEtc(Model model,
            @PathVariable String refID,
            @PathVariable int lifegoalId,
            @PathVariable String country,
            @PathVariable String target_amount,
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

        String age = "";
        if (lifegoalId == 2) {
            age = tenor;
        } else {
            country = "0";
            age = "0";
        }
        int newamount = Integer.parseInt(target_amount) - Integer.parseInt(amount);
        TargetValueResponse respReksadanaEtcTarget = calculatorServices.reksadanaEtcTarget(refID, age, tenor, country, amount, target_amount, risk_profile_id, producttype, lifegoalId, productid);
        PresentValueResponse respReksadanaEtcPresent = calculatorServices.reksadanaEtcPresent(refID, age, lifegoalId, productid, tenor, newamount + "", producttype);

        String rate = averagerate + "% (" + badrate + "% - " + goodrate + "%)";

        DecimalFormat decimalFormat = new DecimalFormat("");
        String resultTarget = decimalFormat.format(Double.parseDouble(respReksadanaEtcTarget.Result));
        String resultPresent = decimalFormat.format(Double.parseDouble(respReksadanaEtcPresent.Result));

        model.addAttribute("namareksadana", namareksa);
        model.addAttribute("typeDescAnnual", "Bulanan");
        model.addAttribute("typeDescLumpsum", "Lumpsum");
        model.addAttribute("rate", rate);
        model.addAttribute("resultTarget", resultTarget);
        model.addAttribute("resultPresent", resultPresent);
        model.addAttribute("idchannel", appProp.IdLiveChat);

        return "productFinal";
    }
}
