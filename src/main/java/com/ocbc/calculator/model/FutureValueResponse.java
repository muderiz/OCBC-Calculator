/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 *
 * @author cokkyturnip
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FutureValueResponse{

    public FutureValueResponse(String Channel_ID,String Ext_Reff_ID, String RC,  String rc_description, String Rate, String Result) {
        this.Ext_Reff_ID = Ext_Reff_ID;
        this.RC = RC;
        this.Rate = Rate;
        this.Channel_ID = Channel_ID;
        this.rc_description = rc_description;
        this.Result = Result;
    }
    
    public String Channel_ID;
    public String Ext_Reff_ID;
    public String RC;
    public String rc_description;
    public String Rate;
    public String Result;

    @Override
    public String toString() {
        return "ClassPojo [ Channel_ID = " + Channel_ID + ",Ext_Reff_ID = " + Ext_Reff_ID + ", RC = " + RC + ", rc_description = " + rc_description + ",Rate = " + Rate + ",  Result = " + Result + "]";
    }
}
