/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.Country;
import com.ocbc.calculator.model.TargetValueResponse;
import com.ocbc.calculator.services.CalculatorServices;
import com.ocbc.calculator.services.ParamJSONServices;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
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

    // >> /etc/refid/Beli%20Mobil%20Mewah/Bambang/23,210,000/20,231,230,000/12
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
        if (risk_profile_id == 0) {
            note = "Angka hanya estimasi. Untuk angka sesuai dengan profil " + name + ", silahkan melengkapi profil risiko " + name + " selanjutnya";
        } else {
            note = "Estimasi laba telah disesuaikan dengan profil risiko " + name + ": Balance";
        }

        present_value = present_value.replace(",", "");
        future_value = future_value.replace(",", "");
        DecimalFormat decimalFormat = new DecimalFormat("");
        String newpresentvalue = decimalFormat.format(Double.parseDouble(present_value));
        String newfuturevalue = decimalFormat.format(Double.parseDouble(future_value));

        TargetValueResponse respInvestasi = calculatorServices.calculateEtc(refID, newpresentvalue, newfuturevalue, tenor, risk_profile_id, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEtc(refID, newpresentvalue, newfuturevalue, tenor, risk_profile_id, "tabungan");

        List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("refID", refID);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("target_dana", newfuturevalue);
        model.addAttribute("dana_sekarang", newpresentvalue);
        model.addAttribute("jangka_waktu", tenor);
        model.addAttribute("goals", goals);
        model.addAttribute("name", name);
        model.addAttribute("note", note);

        return "etc";
    }
}
