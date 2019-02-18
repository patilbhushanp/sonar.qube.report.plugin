/** @MainApplication.class created on 10th February 2019. */
package com.sanbhu.sonar.export.plugins;

import java.io.File;

import com.sanbhu.sonar.export.plugins.bo.SonarQubeFilter;
import com.sanbhu.sonar.export.plugins.constants.ReportPluginConstant;
import com.sanbhu.sonar.export.plugins.exception.ExportConfigurationException;
import com.sanbhu.sonar.export.plugins.util.FileUtility;
import com.sanbhu.sonar.export.plugins.util.HttpTools;
import com.sanbhu.sonar.export.plugins.util.PluginProperties;
import com.sanbhu.sonar.export.plugins.util.XMLUtility;

/**
 * Main Application Class.
 * 
 * @author Lavanya
 */
public class MainApplication {
	
	/**
	 * Main method.
	 * 
	 * @param args string array.
	 * @throws ExportConfigurationException 
	 */
	public static void main(String[] args) throws ExportConfigurationException {
		final String sonarQubeServerURL = PluginProperties.getProperty(ReportPluginConstant.SONAR_QUBE_SERVER_URL);
		final File inputXMLFile = new File(ReportPluginConstant.REPORT_XML_INPUT_FILE);
		final File xslFile = new File(PluginProperties.getProperty(ReportPluginConstant.REPORT_XSLT_TEMPLATE));
		final File outputReportFile = new File(PluginProperties.getProperty(ReportPluginConstant.REPORT_OUTPUT_FILE));
		final File filterMapper = new File(ReportPluginConstant.REPORT_FILTER_FILE); 
		final SonarQubeFilter sonarQubeFilter = XMLUtility.loadConfiguration(filterMapper);
		final String jsonString = HttpTools.getSonarResult(sonarQubeServerURL + "/api/issues/search", sonarQubeFilter);
		String xmlString = XMLUtility.jsonToxmlConverter(jsonString, "issues", true);
		xmlString = XMLUtility.format(xmlString);
		FileUtility.writeData(xmlString, inputXMLFile, false);
		XMLUtility.transformXML(inputXMLFile, xslFile, outputReportFile);
	}
}
