/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.model;

/**
 *
 * @author cokkyturnip
 */
public class ListProductRequest {
    public String Ext_Reff_ID;

    public int Risk_Profile_ID;

    public String MFA_list;

    public int Nominal_Amount;

    public String Channel_ID;

    public String[] List_Product_Type;

    public int Tipe;

    

    @Override
    public String toString()
    {
        return "ClassPojo [Ext_Reff_ID = "+Ext_Reff_ID+", Risk_Profile_ID = "+Risk_Profile_ID+", MFA_list = "+MFA_list+", Nominal_Amount = "+Nominal_Amount+", Channel_ID = "+Channel_ID+", List_Product_Type = "+List_Product_Type+", Tipe = "+Tipe+"]";
    }
}
