/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
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
 * Controller Pendidikan Lainnya
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

    // >> /education/refID/1/Indonesia/10,020,000,013/Dewi/1
    @GetMapping("/{refID}/{age}/{country}/{value}/{name}/{risk_profile_id}")
    public String education(Model model,
            @PathVariable String refID,
            @PathVariable String age,
            @PathVariable String country,
            @PathVariable String value,
            @PathVariable String name,
            @PathVariable int risk_profile_id) throws IOException {
        String note;
        String tahun = "1";
        String bulan = "3";
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

        if (Integer.parseInt(age) >= 18) {
            age = "18";
        }
        int intAge = Integer.parseInt(age);
        int intTenor = 18 - intAge <= 1 ? 1 : 18 - intAge;
        value = value.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        TargetValueResponse respInvestasi = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "tabungan");

        DecimalFormat decimalFormat = new DecimalFormat("");
        String newvalue = decimalFormat.format(Double.parseDouble(value));
        String newfuturevalue = decimalFormat.format(Double.parseDouble(respInvestasi.Target_Amount));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("tenor", intTenor);
        model.addAttribute("tahun", tahun);
        model.addAttribute("bulan", bulan);
        model.addAttribute("risk_profile_desc", risk_profile_desc);
        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("targetamount", respInvestasi.Target_Amount);
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
//        model.addAttribute("rc", "0");
        model.addAttribute("rc", respInvestasi.RC);
//        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
        model.addAttribute("rcdesc", respInvestasi.RC_Description);
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

        if (Integer.parseInt(age) >= 18) {
            age = "18";
        }

        value = value.toLowerCase()
                .replace(",-", "")
                .replace(",", "")
                .replace("rp. ", "")
                .replace("rp ", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".000", "000")
                .replace(".00", "")
                .replace(".0", "");
        TargetValueResponse respInvestasi = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "investasi");
        TargetValueResponse respTabungan = calculatorServices.calculateEducation(refID, age, country, value, risk_profile_id, "tabungan");

        DecimalFormat decimalFormat = new DecimalFormat("");
        String newvalue = decimalFormat.format(Double.parseDouble(value));
        String newfuturevalue = decimalFormat.format(Double.parseDouble(respInvestasi.Target_Amount));
        String investResult = decimalFormat.format(Double.parseDouble(respInvestasi.Result));
        String tabungResult = decimalFormat.format(Double.parseDouble(respTabungan.Result));

        model.addAttribute("investasi", respInvestasi);
        model.addAttribute("targetamount", respInvestasi.Target_Amount);
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
//        model.addAttribute("rc", "0");
        model.addAttribute("rc", respInvestasi.RC);
//        model.addAttribute("rcdesc", "Atribut <Investment_Amount> bernilai negatif.");
        model.addAttribute("rcdesc", respInvestasi.RC_Description);
        model.addAttribute("rcdescerror10", appProp.response10);

        return "pendidikan";
    }
}
