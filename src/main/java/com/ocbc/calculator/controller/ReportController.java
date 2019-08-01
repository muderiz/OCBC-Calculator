/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.ocbc.calculator.model.FormRequest;
import com.ocbc.calculator.model.FormResponse;
import com.ocbc.calculator.model.UserToken;
import com.ocbc.calculator.services.DolphinServiceImp;
import com.ocbc.calculator.services.FormServices;
import java.io.File;
import java.security.spec.InvalidKeySpecException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author cokkyturnip
 */
@Controller
public class ReportController {

	@Autowired
	DolphinServiceImp svcDolphinService;

	@Autowired
	FormRequest formRequest;

	@Autowired
	FormResponse formResponse;

	@Autowired
	FormServices formServices;

	@RequestMapping("/report/survey")
	@GetMapping
	public String ReportSuryvey(Model model) {

		return "report_survey";
	}

	@RequestMapping("/report/download")
	@GetMapping
	public ResponseEntity<Resource> downloadFile(
			HttpServletRequest request) throws InvalidKeySpecException, Exception {

		//Get Token
		UserToken userToken = svcDolphinService.getUserToken(null);

		//Get Data		
		formRequest.Authorization = userToken.getToken();
		formResponse = formServices.GetSurvey(formRequest);

		// Create File
		Gson gson = new Gson();
		String strResponse = gson.toJson(formResponse.data);

		JsonNode jsonTree = new ObjectMapper().readTree(strResponse);

		CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = jsonTree.elements().next();
		firstObject.fieldNames().forEachRemaining(fieldName -> {
			csvSchemaBuilder.addColumn(fieldName);
		});
		CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

		CsvMapper csvMapper = new CsvMapper();
		csvMapper.writer(csvSchema).writeValue(new File("./report.csv"), jsonTree);

//Download
//		Resource resource = fileService.download(fileName);
//
//		// Try to determine file's content type
//		String contentType = null;
//		try {
//			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//
//		// Fallback to the default content type if type could not be determined
//		if (contentType == null) {
//			contentType = "application/octet-stream";
//		}
//
//		return ResponseEntity.ok()
//				.contentType(MediaType.parseMediaType(contentType))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//				.body(resource);
		return null;
	}
}
