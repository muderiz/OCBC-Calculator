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
public class TargetValueResponse {

    public String Channel_ID;
    public String Ext_Reff_ID;
    public String RC;
    public String RC_Description;
    public String Rate;
    public String Target_Amount;
    public String Investment_Type;
    public String Result;

    public TargetValueResponse(String Channel_ID, String Ext_Reff_ID, String RC, String rc_description, String Rate, String Target_Amount, String Investment_Type, String Result) {
        this.Channel_ID = Channel_ID;
        this.Ext_Reff_ID = Ext_Reff_ID;
        this.RC = RC;
        this.RC_Description = rc_description;
        this.Rate = Rate;
        this.Target_Amount = Target_Amount;
        this.Investment_Type = Investment_Type;
        this.Result = Result;
    }

    @Override
    public String toString() {
        return "ClassPojo [Ext_Reff_ID = " + Ext_Reff_ID + ", RC = " + RC + ", Rate = " + Rate + ", Final_Future_Value = " + Target_Amount + ", Channel_ID = " + Channel_ID + ", rc_description = " + RC_Description + ", Investment_Type = " + Investment_Type + ", Result = " + Result + "]";
    }
}
