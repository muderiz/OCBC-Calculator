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
public class FutureValueRequest {

    public String Channel_ID;
    public String Ext_Reff_ID;
    public String Investment_Type;
    public String Future_Value_Type;
    public String Yearly_Return_Code;
    public String Tenor;
    public String Investment_Amount;
    public int Due_Date;
    public String Product_ID;
    public String Risk_Profile_ID;

    @Override
    public String toString() {
        return "ClassPojo [Ext_Reff_ID = " + Ext_Reff_ID + ", Investment_Amount = " + Investment_Amount + ", Risk_Profile_ID = " + Risk_Profile_ID + ", Due_Date = " + Due_Date + ", Product_ID = " + Product_ID + ", Future_Value_Type = " + Future_Value_Type + ", Yearly_Return_Code = " + Yearly_Return_Code + ", Channel_ID = " + Channel_ID + ", Investment_Type = " + Investment_Type + ", Tenor = " + Tenor + "]";
    }
}
