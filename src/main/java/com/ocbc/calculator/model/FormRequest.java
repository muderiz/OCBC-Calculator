/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

/**
 *
 * @author cokkyturnip
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormRequest {
	
	public String Authorization;
	public String formId = "z_75dbbb37b44d582c3b2365033e98f111";
	public String fieldName;
	public String fieldValue;
	public String startDate;
	public String endDate;
	public int start;
	public int count;
	
}
