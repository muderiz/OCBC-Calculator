/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.Country;
import com.ocbc.calculator.model.TargetValueResponse;
import com.ocbc.calculator.services.CalculatorServices;
import com.ocbc.calculator.services.ParamJSONServices;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;
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

    @Autowired
    private AppProperties appProp;

    // >> /education/refID/1/Indonesia/10,020,000,013/Dewi/0
    @GetMapping("/{refID}/{age}/{country}/{value}/{name}/{risk_profile_id}")
    public String education(Model model,
            @PathVariable String refID,
            @PathVariable String age,
            @PathVariable String country,
            @PathVariable String value,
            @PathVariable String name,
            @PathVariable int risk_profile_id) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
        String note;
        if (risk_profile_id == 0) {
            note = "Angka hanya estimasi. Untuk angka sesuai dengan profil " + name + ", silahkan melengkapi profil risiko " + name + " selanjutnya";
        } else {
            note = "Estimasi laba telah disesuaikan dengan profil risiko " + name + ": Balance";
        }
        List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");

        int lengList = listCountry.size();
        String countryname = "";
        for (int i = 0; i < lengList; i++) {
            Country countryArray = listCountry.get(i);
            String countryvalue = countryArray.value;
            String countrytext = countryArray.text;
            if (country.equalsIgnoreCase(countryvalue) || country.equalsIgnoreCase(countrytext)) {
                countryname = countryArray.text;
                country = countryvalue;
                break;

            }
        }

        value = value.replace(",", "");
        TargetValueResponse respInvestasi = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "tabungan");

//        String value_country = "";
//        String nama = "";
//        for (Country country1 : listCountry) {
//            value_country = country1.value;
//            nama = country1.text;
//            if(value_country == country){
//                
//            }
//        }
        DecimalFormat decimalFormat = new DecimalFormat("");
        String newvalue = decimalFormat.format(Double.parseDouble(value));
        String newfuturevalue = decimalFormat.format(Double.parseDouble(respInvestasi.Target_Amount));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("investasiFinalValue", newfuturevalue);
        model.addAttribute("investasiResult", investResult);
        model.addAttribute("investasiRate", respInvestasi.Rate);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("tabunganResult", tabungResult);
        model.addAttribute("tabunganRate", respTabungan.Rate);
        model.addAttribute("age", age);
        model.addAttribute("country", countryname);
        model.addAttribute("valuecountry", country);
        model.addAttribute("value", newvalue);
        model.addAttribute("name", name);
        model.addAttribute("listCountry", listCountry);
        model.addAttribute("note", note);
        model.addAttribute("idchannel", appProp.IdLiveChat);
        model.addAttribute("rc", "0");
//        model.addAttribute("rc", respInvestasi.RC);
        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
//        model.addAttribute("rcdesc", respInvestasi.rc_description);
        model.addAttribute("rcdescerror10", appProp.response10);

        return "pendidikan";
    }

    @GetMapping("/summary/{refID}/{age}/{country}/{value}/{name}/{risk_profile_id}")
    public String educationSummary(Model model,
            @PathVariable String refID,
            @PathVariable String age,
            @PathVariable String country,
            @PathVariable String value,
            @PathVariable String name,
            @PathVariable int risk_profile_id) throws IOException {

        String note;
        if (risk_profile_id == 0) {
            note = "Angka hanya estimasi. Untuk angka sesuai dengan profil " + name + ", silahkan melengkapi profil risiko " + name + " selanjutnya";
        } else {
            note = "Estimasi laba telah disesuaikan dengan profil risiko " + name + ": Balance";
        }
        List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");

        int lengList = listCountry.size();
        String countryname = "";
        for (int i = 0; i < lengList; i++) {
            Country countryArray = listCountry.get(i);
            String countryvalue = countryArray.value;
            String countrytext = countryArray.text;
            if (country.equalsIgnoreCase(countryvalue) || country.equalsIgnoreCase(countrytext)) {
                countryname = countryArray.text;
                country = countryvalue;
                break;

            }
        }

        value = value.replace(",", "");
        TargetValueResponse respInvestasi = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "tabungan");

//        String value_country = "";
//        String nama = "";
//        for (Country country1 : listCountry) {
//            value_country = country1.value;
//            nama = country1.text;
//            if(value_country == country){
//                
//            }
//        }
        DecimalFormat decimalFormat = new DecimalFormat("");
        String newvalue = decimalFormat.format(Double.parseDouble(value));
        String newfuturevalue = decimalFormat.format(Double.parseDouble(respInvestasi.Target_Amount));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("investasiFinalValue", newfuturevalue);
        model.addAttribute("investasiResult", investResult);
        model.addAttribute("investasiRate", respInvestasi.Rate);
        model.addAttribute("tabungan", respTabungan);
        model.addAttribute("tabunganResult", tabungResult);
        model.addAttribute("tabunganRate", respTabungan.Rate);
        model.addAttribute("age", age);
        model.addAttribute("country", countryname);
        model.addAttribute("valuecountry", country);
        model.addAttribute("value", newvalue);
        model.addAttribute("name", name);
        model.addAttribute("listCountry", listCountry);
        model.addAttribute("note", note);
        model.addAttribute("idchannel", appProp.IdLiveChat);
        model.addAttribute("rc", "0");
//        model.addAttribute("rc", respInvestasi.RC);
        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
//        model.addAttribute("rcdesc", respInvestasi.rc_description);
        model.addAttribute("rcdescerror10", appProp.response10);

        return "pendidikanSummary";
    }
}
