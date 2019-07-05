package com.ocbc.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cokkyturnip
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    public int Mutual_Fund_Type;

    public String Mutual_Fund_Type_Desc;

    public void setMutual_Fund_Type_Desc() {
        switch (this.Mutual_Fund_Type) {
            case 1:
                this.Mutual_Fund_Type_Desc = "Pasar Uang";
                break;
            case 2:
                this.Mutual_Fund_Type_Desc = "Pendapatan Tetap";
                break;
            case 3:
                this.Mutual_Fund_Type_Desc = "Campuran";
                break;
            case 4:
                this.Mutual_Fund_Type_Desc = "Saham";
                break;
        }
    }

    public String Product_Rate;

    public String Performance_of_6_month;

    public String Product_ID;

    public String Average_Rate;

    public String Performance_of_60_month;

    public double YTD;

    public String Product_Name;

    public String Bad_Rate;

    public String Performance_of_1_month;

    public String Performance_of_12_month;

    public String Risk_Profile_Type;

    public String Good_Rate;

    public double Standard_Deviation;

    public String Mutual_Fund_Nav;

    public String Product_Type;

    public String Mutual_Fund_Code;

    public int Mutual_Fund_Risk_Profile_ID;

    @Override
    public String toString() {
        return "ClassPojo [Mutual_Fund_Risk_Profile_ID = " + Mutual_Fund_Risk_Profile_ID + ", Mutual_Fund_Type = " + Mutual_Fund_Type + ", Product_Rate = " + Product_Rate + ", Performance_of_6_month  = " + Performance_of_6_month + ", Product_ID = " + Product_ID + ", Average_Rate = " + Average_Rate + ", Performance_of_60_month  = " + Performance_of_60_month + ", YTD = " + YTD + ", Product_Name = " + Product_Name + ", Bad_Rate = " + Bad_Rate + ", Performance_of_1_month = " + Performance_of_1_month + ", Performance_of_12_month  = " + Performance_of_12_month + ", Risk_Profile_Type = " + Risk_Profile_Type + ", Good_Rate = " + Good_Rate + ", Standard_Deviation = " + Standard_Deviation + ", Mutual_Fund_Nav = " + Mutual_Fund_Nav + ", Product_Type = " + Product_Type + ", Mutual_Fund_Code = " + Mutual_Fund_Code + "]";
    }
}
