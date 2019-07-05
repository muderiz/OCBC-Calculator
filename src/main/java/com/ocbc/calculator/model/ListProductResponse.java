/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cokkyturnip
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListProductResponse implements Serializable{
    
    public String Ext_Reff_ID;

    public String RC;

    public String Channel_ID;

    public String rc_description;

    public List<Product> List_Product = new ArrayList<Product>();
}
