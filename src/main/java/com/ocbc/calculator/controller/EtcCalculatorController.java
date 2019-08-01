/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.TargetValueResponse;
import com.ocbc.calculator.services.CalculatorServices;
import com.ocbc.calculator.services.ParamJSONServices;
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
@RequestMapping("/etc")
public class EtcCalculatorController {

    @Autowired
    private CalculatorServices calculatorServices;

    @Autowired
    private ParamJSONServices paramJSONServices;

    @Autowired
    private AppProperties appProp;

    // >> /etc/refid/Beli%20Mobil%20Mewah/Bambang/23,210,000/20,231,230,000/12/0
    @GetMapping("/{refID}/{goals}/{name}/{present_value}/{future_value}/{tenor}/{risk_profile_id}")
    public String etc(Model model,
            @PathVariable String refID,
            @PathVariable String goals,
            @PathVariable String name,
            @PathVariable String present_value,
            @PathVariable String future_value,
            @PathVariable String tenor,
            @PathVariable int risk_profile_id) throws IOException {

        String note;
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
        present_value = present_value.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        future_value = future_value.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        TargetValueResponse respInvestasi = calculatorServices.calculateEtc(refID, present_value, future_value, tenor, risk_profile_id, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEtc(refID, present_value, future_value, tenor, risk_profile_id, "tabungan");

        DecimalFormat decimalFormat = new DecimalFormat("");
        String newpresentvalue = decimalFormat.format(Double.parseDouble(present_value));
        String newfuturevalue = decimalFormat.format(Double.parseDouble(future_value));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("refID", refID);
        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("investasiResult", investResult);
        model.addAttribute("investasiRate", respInvestasi.Rate);
        model.addAttribute("tabunganResult", tabungResult);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("tabunganRate", respTabungan.Rate);
        model.addAttribute("target_dana", newfuturevalue);
        model.addAttribute("dana_sekarang", newpresentvalue);
        model.addAttribute("jangka_waktu", tenor);
        model.addAttribute("goals", goals);
        model.addAttribute("name", name);
        model.addAttribute("note", note);
        model.addAttribute("idchannel", appProp.IdLiveChat);
//        model.addAttribute("rc", "2");
        model.addAttribute("rc", respInvestasi.RC);
//        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
        model.addAttribute("rcdesc", respInvestasi.RC_Description);
        model.addAttribute("rcdescerror10", appProp.response10);

        return "etc";
    }

    // >> /etc/summary/refid/Beli%20Mobil%20Mewah/Bambang/23,210,000/20,231,230,000/12/0
    @GetMapping("/summary/{refID}/{goals}/{name}/{present_value}/{future_value}/{tenor}/{risk_profile_id}")
    public String etcSummary(Model model,
            @PathVariable String refID,
            @PathVariable String goals,
            @PathVariable String name,
            @PathVariable String present_value,
            @PathVariable String future_value,
            @PathVariable String tenor,
            @PathVariable int risk_profile_id) throws IOException {

        String note;
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
        present_value = present_value.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        future_value = future_value.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        TargetValueResponse respInvestasi = calculatorServices.calculateEtc(refID, present_value, future_value, tenor, risk_profile_id, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEtc(refID, present_value, future_value, tenor, risk_profile_id, "tabungan");

        DecimalFormat decimalFormat = new DecimalFormat("");
        String newpresentvalue = decimalFormat.format(Double.parseDouble(present_value));
        String newfuturevalue = decimalFormat.format(Double.parseDouble(future_value));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("refID", refID);
        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("investasiResult", investResult);
        model.addAttribute("investasiRate", respInvestasi.Rate);
        model.addAttribute("tabunganResult", tabungResult);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("tabunganRate", respTabungan.Rate);
        model.addAttribute("target_dana", newfuturevalue);
        model.addAttribute("dana_sekarang", newpresentvalue);
        model.addAttribute("jangka_waktu", tenor);
        model.addAttribute("goals", goals);
        model.addAttribute("name", name);
        model.addAttribute("note", note);
        model.addAttribute("idchannel", appProp.IdLiveChat);
//        model.addAttribute("rc", "2");
        model.addAttribute("rc", respInvestasi.RC);
//        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
        model.addAttribute("rcdesc", respInvestasi.RC_Description);
        model.addAttribute("rcdescerror10", appProp.response10);

        return "etcSummary";
    }

}
