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
@RequestMapping("/education")
public class EducationCalculatorController {

    @Autowired
    private CalculatorServices calculatorServices;

    @Autowired
    private ParamJSONServices paramJSONServices;

    // >> /education/refID/1/Indonesia/10,020,000,013/Dewi
    @GetMapping("/{refID}/{age}/{country}/{value}/{name}")
    public String education(Model model,
            @PathVariable String refID,
            @PathVariable String age,
            @PathVariable String country,
            @PathVariable String value,
            @PathVariable String name) throws IOException {

        TargetValueResponse respInvestasi = calculatorServices.calculateEducation(refID, age, country, value, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEducation(refID, age, country, value, "tabungan");

        List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");
        
        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("age", age);
        model.addAttribute("country", country);
        model.addAttribute("value", value);
        model.addAttribute("name", name);
        model.addAttribute("listCountry",listCountry);

        return "pendidikan";
    }
}
