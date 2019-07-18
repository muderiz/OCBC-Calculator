/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Deka
 */
@Controller
public class TncController {

    @Autowired
    private AppProperties appProp;

    @RequestMapping(value = {"/tnc"})
    @GetMapping
    public String tncController(Model model) {
        model.addAttribute("idchannel", appProp.IdLiveChat);
        return "tnc";
    }

}
