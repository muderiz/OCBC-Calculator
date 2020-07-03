/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.ocbc.calculator.model.AppProperties;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Deka
 */
@Controller
@RequestMapping("/quotes")
public class QuotesController {

    @Autowired
    private AppProperties appProp;

    @GetMapping("/{imagedesktop}/{imagehp}")
    public String tncController(Model model,
            @PathVariable String imagedesktop,
            @PathVariable String imagehp) throws IOException {

        String pathdesktop = appProp.getOcbc_pictures_quotes() + "desktop/" + imagedesktop;
        String pathmobile = appProp.getOcbc_pictures_quotes() + "mobile/" + imagehp;

        model.addAttribute("pathdesktop", pathdesktop);
        model.addAttribute("pathmobile", pathmobile);
        model.addAttribute("url", "https://chatbot.ocbcnisp.com/pictures/do_not_delete/carousel_growth.jpg");
        model.addAttribute("idchannel", appProp.IdLiveChat);
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
