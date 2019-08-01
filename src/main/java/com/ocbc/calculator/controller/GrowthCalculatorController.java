/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.FutureValueResponse;
import com.ocbc.calculator.services.CalculatorServices;
import java.io.IOException;
import java.text.DecimalFormat;

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

    @Autowired
    private AppProperties appProp;

//     >> /growth/refID/100,100,022/0/15/deka/0
    // >> /growth/refID/100,100,022/1/15/deka/0
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
            type = "false";
        } else {
            typeDesc = "per lumpsum";
            type = "true";
        }
        String risk_profile_desc = "";
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
        if (risk_profile_id == 0) {
            note = "Angka hanya estimasi. Untuk angka sesuai dengan profil " + name + ", silahkan melengkapi profil risiko " + name + " selanjutnya";
        } else {
            note = "Estimasi laba telah disesuaikan dengan profil risiko " + name + ": " + risk_profile_desc;
        }

        amount = amount.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        FutureValueResponse respInvestasi = calculatorServices.calculateGrowth(refID, amount, type, tenor, risk_profile_id, "investasi");
        FutureValueResponse respTabungan = calculatorServices.calculateGrowth(refID, amount, type, tenor, risk_profile_id, "tabungan");

        DecimalFormat decimalFormat = new DecimalFormat("");
        String newamount = decimalFormat.format(Double.parseDouble(amount));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("investasiResult", investResult);
        model.addAttribute("investasiRate", respInvestasi.Rate);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("tabunganResult", tabungResult);
        model.addAttribute("tabunganRate", respTabungan.Rate);
        model.addAttribute("typeDesc", typeDesc);
        model.addAttribute("note", note);
        model.addAttribute("newamount", newamount);
        model.addAttribute("idchannel", appProp.IdLiveChat);
//        model.addAttribute("rc", "0");
        model.addAttribute("rc", respInvestasi.RC);
//        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
        model.addAttribute("rcdesc", respInvestasi.RC_Description);
        model.addAttribute("rcdescerror8", appProp.response8);

        return "growth";
    }

    @GetMapping("/summary/{refID}/{amount}/{type}/{tenor}/{name}/{risk_profile_id}")
    public String growthSummary(Model model,
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
            type = "false";
        } else {
            typeDesc = "per lumpsum";
            type = "true";
        }

        String risk_profile_desc = "";
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
        
        if (risk_profile_id == 0) {
            note = "Angka hanya estimasi. Untuk angka sesuai dengan profil " + name + ", silahkan melengkapi profil risiko " + name + " selanjutnya";
        } else {
            note = "Estimasi laba telah disesuaikan dengan profil risiko " + name + ": " + risk_profile_desc;
        }
        amount = amount.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        FutureValueResponse respInvestasi = calculatorServices.calculateGrowth(refID, amount, type, tenor, risk_profile_id, "investasi");
        FutureValueResponse respTabungan = calculatorServices.calculateGrowth(refID, amount, type, tenor, risk_profile_id, "tabungan");

        DecimalFormat decimalFormat = new DecimalFormat("");
        String newamount = decimalFormat.format(Double.parseDouble(amount));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("investasiResult", investResult);
        model.addAttribute("investasiRate", respInvestasi.Rate);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("tabunganResult", tabungResult);
        model.addAttribute("tabunganRate", respTabungan.Rate);
        model.addAttribute("typeDesc", typeDesc);
        model.addAttribute("note", note);
        model.addAttribute("newamount", newamount);
        model.addAttribute("idchannel", appProp.IdLiveChat);
//        model.addAttribute("rc", "0");
        model.addAttribute("rc", respInvestasi.RC);
//        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
        model.addAttribute("rcdesc", respInvestasi.RC_Description);
        model.addAttribute("rcdescerror8", appProp.response8);

        return "growthSummary";
    }

}
