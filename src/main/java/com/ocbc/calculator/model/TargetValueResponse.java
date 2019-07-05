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
    public String rc_description;
    public String Rate;
    public String Final_Future_Value;
    public String Investment_Type;
    public String Result;

    public TargetValueResponse(String Channel_ID, String Ext_Reff_ID, String RC, String rc_description, String Rate, String Final_Future_Value, String Investment_Type, String Result) {
        this.Channel_ID = Channel_ID;
        this.Ext_Reff_ID = Ext_Reff_ID;
        this.RC = RC;
        this.rc_description = rc_description;
        this.Rate = Rate;
        this.Final_Future_Value = Final_Future_Value;
        this.Investment_Type = Investment_Type;
        this.Result = Result;
    }

    @Override
    public String toString() {
        return "ClassPojo [Ext_Reff_ID = " + Ext_Reff_ID + ", RC = " + RC + ", Rate = " + Rate + ", Final_Future_Value = " + Final_Future_Value + ", Channel_ID = " + Channel_ID + ", rc_description = " + rc_description + ", Investment_Type = " + Investment_Type + ", Result = " + Result + "]";
    }
}
