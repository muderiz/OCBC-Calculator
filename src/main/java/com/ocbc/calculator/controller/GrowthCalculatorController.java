/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.FeatureValueResponse;
import com.ocbc.calculator.services.CalculatorServices;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

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
    @GetMapping("/{refID}/{amount}/{type}/{tenor}/{name}/{risk_profile_id}")
    public String growth(Model model,
            @PathVariable String refID,
            @PathVariable String amount,
            @PathVariable String type,
            @PathVariable String tenor,
            @PathVariable String name,
            @PathVariable int risk_profile_id) throws IOException {

        String typeDesc;
        String note;

        if (type.equalsIgnoreCase("0")) {
            typeDesc = "per bulan";
        } else {
            typeDesc = "per lumpsum";
        }

        if (risk_profile_id == 0) {
            note = "Angka hanya estimasi. Untuk angka sesuai dengan profil " + name + ", silahkan melengkapi profil risiko " + name + " selanjutnya";
        } else {
            note = "Estimasi laba telah disesuaikan dengan profil risiko " + name + ": Balance";
        }
        
        amount = amount.replace(",", "");
        DecimalFormat decimalFormat = new DecimalFormat("");
        String newamount = decimalFormat.format(Double.parseDouble(amount));

        FeatureValueResponse respInvestasi = calculatorServices.calculateGrowth(refID, newamount, type, tenor, risk_profile_id, "investasi");
        FeatureValueResponse respTabungan = calculatorServices.calculateGrowth(refID, newamount, type, tenor, risk_profile_id, "tabungan");

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("typeDesc", typeDesc);
        model.addAttribute("note", note);
        model.addAttribute("newamount", newamount);

        return "growth";
    }
}
