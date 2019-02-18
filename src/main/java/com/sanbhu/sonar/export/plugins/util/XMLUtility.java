package com.sanbhu.sonar.export.plugins.util;

import java.io.File;
import java.io.StringReader;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import com.sanbhu.sonar.export.plugins.bo.SonarQubeFilter;
import com.sanbhu.sonar.export.plugins.constants.ExportErrorCodes;
import com.sanbhu.sonar.export.plugins.exception.ExportConfigurationException;

public class XMLUtility {
	
	private static final String COMMA_SEPARATOR = ",";
	
	public static SonarQubeFilter loadConfiguration(final File filterMapper) throws ExportConfigurationException {
		final SonarQubeFilter sonarQubeFilter = new SonarQubeFilter();
		try {
			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			final Document document = documentBuilder.parse(filterMapper);
			document.getDocumentElement().normalize();

			final NodeList typeNodeList = document.getElementsByTagName("Types");
			if (typeNodeList != null && typeNodeList.getLength() > 0) {
				final String types = typeNodeList.item(0).getTextContent().trim();
				if (StringUtils.isNotBlank(types)) {
					sonarQubeFilter.setTypes(Arrays.asList(types.split(COMMA_SEPARATOR)));
				}
			}

			final NodeList resolutionNodeList = document.getElementsByTagName("Resolutions");
			if (resolutionNodeList != null && resolutionNodeList.getLength() > 0) {
				final String resolutions = resolutionNodeList.item(0).getTextContent().trim();
				if (StringUtils.isNotBlank(resolutions)) {
					sonarQubeFilter.setResolutions(Arrays.asList(resolutions.split(COMMA_SEPARATOR)));
				}
			}

			final NodeList serveritiesNodeList = document.getElementsByTagName("Severities");
			if (serveritiesNodeList != null && serveritiesNodeList.getLength() > 0) {
				final String severities = serveritiesNodeList.item(0).getTextContent().trim();
				if (StringUtils.isNotBlank(severities)) {
					sonarQubeFilter.setSeverities(Arrays.asList(severities.split(COMMA_SEPARATOR)));
				}
			}

			final NodeList projectNodeList = document.getElementsByTagName("Projects");
			if (projectNodeList != null && projectNodeList.getLength() > 0) {
				final String projects = projectNodeList.item(0).getTextContent().trim();
				if (StringUtils.isNotBlank(projects)) {
					sonarQubeFilter.setProjects(Arrays.asList(projects.split(COMMA_SEPARATOR)));
				}
			}

			final NodeList createdAfterNodeList = document.getElementsByTagName("CreatedAfter");
			if (createdAfterNodeList != null && createdAfterNodeList.getLength() > 0) {
				final String createdAfter = createdAfterNodeList.item(0).getTextContent().trim();
				if (StringUtils.isNotBlank(createdAfter)) {
					sonarQubeFilter.setStartDate(createdAfter);
				}
			}

			final NodeList createdBeforeNodeList = document.getElementsByTagName("CreatedBefore");
			if (createdBeforeNodeList != null && createdBeforeNodeList.getLength() > 0) {
				final String createdBefore = createdBeforeNodeList.item(0).getTextContent().trim();
				if (StringUtils.isNotBlank(createdBefore)) {
					sonarQubeFilter.setEndDate(createdBefore);
				}
			}
		}catch (Exception exception) {
			throw new ExportConfigurationException(ExportErrorCodes.SYSTEM_ERROR, exception);
		}
		return sonarQubeFilter;
	}
	
	public static String jsonToxmlConverter(final String jsonString, final String nodeName, final boolean isArrayObject) {
		final StringBuilder responseString = new StringBuilder();
		final JSONObject json = new JSONObject(jsonString);
		final JSONArray jsonArray = json.getJSONArray("issues");
		responseString.append("<root>");
		responseString.append(XML.toString(jsonArray));
		responseString.append("</root>");
		return responseString.toString();
	}
	
	public static String format(final String xml) throws ExportConfigurationException {
		try {
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();
			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);
			return writer.writeToString(document);
		} catch (Exception exception) {
			throw new ExportConfigurationException(ExportErrorCodes.SYSTEM_ERROR, exception);
		}
	}
	
	public static void transformXML(final File inputFile, final File xsltFile, final File outputFile) throws ExportConfigurationException {
		final TransformerFactory factory = TransformerFactory.newInstance();
		if(xsltFile.isFile()) {
	        final Source xslt = new StreamSource(xsltFile);
	        Transformer transformer;
			try {
				transformer = factory.newTransformer(xslt);
		        final Source text = new StreamSource(inputFile);
		        transformer.transform(text, new StreamResult(outputFile));
			} catch (TransformerConfigurationException exception) {
				throw new ExportConfigurationException(ExportErrorCodes.SYSTEM_ERROR, exception);
			} catch (TransformerException exception) {
				throw new ExportConfigurationException(ExportErrorCodes.SYSTEM_ERROR, exception);
			}			
		} else {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR, "Failed to get XSLT file - " + xsltFile.getAbsolutePath());
		}
	}
}
