/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller Term and Conditions Webview
 *
 * @author Deka
 */
@Controller
@RequestMapping(value = {"/tnc"})
public class TncController {

    @Autowired
    private AppProperties appProp;

    @GetMapping
    public String tncController(Model model) {
        model.addAttribute("idchannel", appProp.IdLiveChat);
        return "tnc";
    }

    @GetMapping("/quotes/{imagename}")
    public String quotes(Model model,
            @PathVariable String imagename) throws IOException {

        String pathdesktopshow = appProp.getOcbc_pictures_quotes_show() + "desktop/quote" + imagename + ".jpg";
        String pathmobileshow = appProp.getOcbc_pictures_quotes_show() + "mobile/mquote" + imagename + ".jpg";
        String pathdesktopdownload = appProp.getOcbc_pictures_quotes_download() + "desktop/quote" + imagename + ".jpg";
        String pathmobiledownload = appProp.getOcbc_pictures_quotes_download() + "mobile/mquote" + imagename + ".jpg";

        model.addAttribute("pathdesktopshow", pathdesktopshow);
        model.addAttribute("pathmobileshow", pathmobileshow);
        model.addAttribute("pathdesktopdownload", pathdesktopdownload);
        model.addAttribute("pathmobiledownload", pathmobiledownload);
        model.addAttribute("url", "https://chatbot.ocbcnisp.com/pictures/do_not_delete/carousel_growth.jpg");
        model.addAttribute("idchannel", appProp.IdLiveChat);

        return "quotes";
    }

    public static long getFolderSize(File dir) {
        long size = 0;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                System.out.println(file.getName() + " " + file.length());
                size += file.length();
            } else {
                size += getFolderSize(file);
            }
        }
        return size;
    }
}
