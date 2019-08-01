/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocbc.calculator.services;

import com.google.gson.Gson;
import com.ocbc.calculator.model.AppProperties;
import com.ocbc.calculator.model.FormRequest;
import com.ocbc.calculator.model.FormResponse;
import com.ocbc.calculator.model.FutureValueResponse;
import com.ocbc.calculator.model.ParamSdk;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cokkyturnip
 */
@Service
public class FormServices {

	@Autowired
	AppProperties appProp;

	public FormResponse GetSurvey(FormRequest formRequest) throws IOException {
		
		RequestBody body = RequestBody.create("{\"Authorization\" : " + formRequest.Authorization + "}", ParamSdk.JSON);

		String param = "";
		if (!formRequest.formId.isEmpty()) {
			param = param + "&formId=" + formRequest.formId;
		}
		if (formRequest.startDate != null && !formRequest.startDate.isEmpty()) {
			param = param + "&startDate=" + formRequest.startDate;
		}
		if (formRequest.endDate != null && !formRequest.endDate.isEmpty()) {
			param = param + "&startDate=" + formRequest.endDate;
		}

		param = param.substring(1, param.length() - 1);

		Request request = new Request.Builder()
//				.url(appProp.getSdkDolphinBaseUrl() + "/graph/forms/formData?" + param)
				.url(appProp.getSdkDolphinBaseUrl() + "/graph/forms/formData?formId=z_75dbbb37b44d582c3b2365033e98f111")
				.header("Authorization", formRequest.Authorization)
				.get()
				.build();
		OkHttpClient client = new OkHttpClient();
		Call call = client.newCall(request);
		Response response = call.execute();

		Gson gson = new Gson();
		FormResponse formResponse = gson.fromJson(response.body().string(), FormResponse.class);
		return formResponse;
	}
}
