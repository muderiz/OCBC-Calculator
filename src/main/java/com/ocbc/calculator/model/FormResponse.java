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
public class FormResponse {

	public FormData[] data;
	public String status;
	
}
