package com.sanbhu.sonar.export.plugins.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.sanbhu.sonar.export.plugins.bo.SonarQubeFilter;

public class HttpTools {

	public static String getSonarResult(final String url, final SonarQubeFilter sonarQubeFilter) {
		final StringBuilder responseString = new StringBuilder();
		final StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(url);
		urlBuilder.append(getQueryParameter(sonarQubeFilter));
		try {
			final HttpClient client = HttpClientBuilder.create().build();
			final HttpGet request = new HttpGet(url);
			request.addHeader("User-Agent", "Mozilla/5.0");
			request.addHeader("Accept", "application/json");
			final HttpResponse response = client.execute(request);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			final BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				responseString.append(line + "\n");
			}
		} catch (Exception exception) {
			System.err.println("Failed to download SonarQube response. Reason - " + exception.getLocalizedMessage());
		}
		return responseString.toString();
	}

	private static String getQueryParameter(final SonarQubeFilter sonarQubeFilter) {
		final StringBuilder queryParameter = new StringBuilder();
		queryParameter.append("?");
		if (!sonarQubeFilter.getTypes().isEmpty()) {
			queryParameter.append("Types=").append(getParameterValue(sonarQubeFilter.getTypes())).append("&");
		}
		if (!sonarQubeFilter.getResolutions().isEmpty()) {
			queryParameter.append("Resolutions=").append(getParameterValue(sonarQubeFilter.getResolutions()))
					.append("&");
		}
		if (!sonarQubeFilter.getProjects().isEmpty()) {
			queryParameter.append("Projects=").append(getParameterValue(sonarQubeFilter.getProjects())).append("&");
		}
		if (!sonarQubeFilter.getSeverities().isEmpty()) {
			queryParameter.append("Severities=").append(getParameterValue(sonarQubeFilter.getSeverities())).append("&");
		}
		if (!sonarQubeFilter.getStartDate().isEmpty()) {
			queryParameter.append("CreatedAfter=").append(sonarQubeFilter.getStartDate()).append("&");
		}
		if (!sonarQubeFilter.getEndDate().isEmpty()) {
			queryParameter.append("CreatedBefore=").append(sonarQubeFilter.getEndDate()).append("&");
		}
		return queryParameter.toString();
	}

	private static String getParameterValue(final List<String> valueList) {
		String parameterValue = "";
		for (final String value : valueList) {
			parameterValue = value + ",";
		}
		if (parameterValue.lastIndexOf(",") == parameterValue.length() - 1) {
			parameterValue = parameterValue.substring(0, parameterValue.length() - 1);
		}
		return parameterValue;
	}
}
