/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.FeatureValueResponse;
import com.ocbc.calculator.services.CalculatorServices;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cokkyturnip
 */
@Controller
@RequestMapping("/growth")
public class GrowthCalculatorController {

    @Autowired
    private CalculatorServices calculatorServices;
    
    // >> /growth/refID/100,100,022/0/15
    // >> /growth/refID/100,100,022/1/15
    @GetMapping("/{refID}/{amount}/{type}/{tenor}")
    public String growth(Model model,
            @PathVariable String refID,
            @PathVariable String amount,
            @PathVariable String type,
            @PathVariable String tenor) throws IOException {

        String typeDesc;

        if (type.equalsIgnoreCase("0")) {
            typeDesc = "per bulan";
        } else {
            typeDesc = "per lumpsum";
        }

        FeatureValueResponse respInvestasi = calculatorServices.calculateGrowth(refID, amount, type, tenor, "investasi");
        FeatureValueResponse respTabungan = calculatorServices.calculateGrowth(refID, amount, type, tenor, "tabungan");

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("typeDesc", typeDesc);

        return "growth";
    }
}
