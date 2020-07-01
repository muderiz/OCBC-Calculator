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

//        int lengNamaDokumen = listByFilter.size();
//        String linkDoc = "";
//        String namaDoc = "";
//        String fileDoc = "";
//        for (int i = 0; i < lengNamaDokumen; i++) {
//            SOP sopArray = listByFilter.get(i);
//            namaDoc = sopArray.nama_doc;
//
//            String inputUrl = "";
//            if (namaDoc.equalsIgnoreCase(namadokumen)) {
//                linkDoc = sopArray.link;
//                fileDoc = sopArray.file_name.replace(".pdf", "").replace(".jepg", "").replace(".jpg", "");
//
//                String dir = appProp.getGARUDAFOOD_PATH_GENERATEDFILES() + appProp.getGARUDAFOOD_BASE_SOP() + company.toUpperCase() + "/" + fileDoc + "/";
//                File f = new File(dir);
//                System.out.println("File dan direktori dalam " + dir);
//                String[] daftar = f.list();
////                    java.util.Arrays.sort(daftar);
//                System.out.println(daftar);
//                Arrays.sort(daftar);
//
//                for (int j = 0; j < daftar.length; j++) {
////                        File fTemp = new File(dir + daftar[i]);
//                    String dir2 = appProp.getGARUDAFOOD_URL_GENERATEDFILES() + appProp.getGARUDAFOOD_BASE_SOP() + company.toUpperCase() + "/" + fileDoc + "/";
//                    System.out.println(dir2 + daftar[j]);
//                    inputUrl = dir2 + daftar[j];
////                      inputUrl = "https://autobot.garudafood.co.id/GeneratedFiles/baseSop/09102019_11251473.jpeg";
//                    URL input = new URL(inputUrl);
//                    String reportAfterWatermark = generateWatermark.WatermarkImageSOP(text, input, daftar[j]);
//                    System.out.println(reportAfterWatermark);
//                    ButtonTemplate image = new ButtonTemplate();
//                    image.setPictureLink(reportAfterWatermark);
//                    image.setPicturePath(reportAfterWatermark);
//
//                    DocumentBuilder documentBuilder = new DocumentBuilder(image);
//                    String btnBuilder = documentBuilder.build();
//                    sb.append(btnBuilder).append(SPLIT);
//                }
//                break;
//            }
//        }
        model.addAttribute("url", "https://raw.githubusercontent.com/muderiz/imageSiloam/master/tipe_pencarian.jpg");
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
