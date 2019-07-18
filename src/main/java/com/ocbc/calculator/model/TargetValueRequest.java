/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author cokkyturnip
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TargetValueRequest {

    public String Channel_ID;
    public String Ext_Reff_ID;
    public String Payment_Type;
    public String Yearly_Return_Code;
    public String Tenor;
    public String Present_Value;
    public String Pre_Calculated_Future_Value;
    public String Future_Value_Code;
    public String Country;
    public String Children_Age;
    public int Due_Date;
    public String Product_ID;
    public String Risk_Profile_ID;
    
    @Override
    public String toString() {
        return "ClassPojo [Ext_Reff_ID = " + Ext_Reff_ID + ", Pre_Calculated_Future_Value = " + Pre_Calculated_Future_Value + ", Due_Date = " + Due_Date + ", Product_ID = " + Product_ID + ", Channel_ID = " + Channel_ID + ", Children_Age = " + Children_Age + ", Payment_Type = " + Payment_Type + ", Future_Value_Code = " + Future_Value_Code + ", Tenor = " + Tenor + ", Present_Value = " + Present_Value + ", Risk_Profile_ID = " + Risk_Profile_ID + ", Yearly_Return_Code = " + Yearly_Return_Code + ", Country = " + Country + "]";
    }
}
