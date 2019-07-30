/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Deka
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PresentValueRequest {

    public String Channel_ID;
    public String Ext_Reff_ID;
    public String Present_Value_Type;
    public String Yearly_Return_Code;
    public String Tenor;
    public String Payment;
    public String Target_Amount;
    public int Due_Date;
    public String Product_ID;

}
