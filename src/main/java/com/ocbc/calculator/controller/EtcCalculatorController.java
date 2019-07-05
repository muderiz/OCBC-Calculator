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
    @GetMapping("/{refID}/{goals}/{name}/{present_value}/{future_value}/{tenor}")
    public String etc(Model model,
            @PathVariable String refID,
            @PathVariable String goals,
            @PathVariable String name,
            @PathVariable String present_value,
            @PathVariable String future_value,
            @PathVariable String tenor) throws IOException {

        TargetValueResponse respInvestasi = calculatorServices.calculateEducation(refID, present_value, future_value, tenor, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEducation(refID, present_value, future_value, tenor, "tabungan");

        List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("refID", refID);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("target_dana", future_value);
        model.addAttribute("dana_sekarang", present_value);
        model.addAttribute("jangka_waktu", tenor);
        model.addAttribute("goals", goals);
        model.addAttribute("customer_name", name);

        return "etc";
    }
}
