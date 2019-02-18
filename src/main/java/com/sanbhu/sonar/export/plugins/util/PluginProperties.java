package com.sanbhu.sonar.export.plugins.util;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.sanbhu.sonar.export.plugins.constants.ExportErrorCodes;
import com.sanbhu.sonar.export.plugins.exception.ExportConfigurationException;

public final class PluginProperties {

	private static PluginProperties pluginProperties;
	
	private final Configuration configuration;
	
	private final String propertyFileName = System.getProperty("PLUGIN_CONF_DIRECTORY") + File.separator + "report-plugin.properties";
	
	private PluginProperties() throws ConfigurationException {
		configuration = new PropertiesConfiguration(propertyFileName);
	}
	
	private static PluginProperties getInstance() throws ExportConfigurationException {
		try {
			if (pluginProperties == null) {
				pluginProperties = new PluginProperties();
			}
		} catch (ConfigurationException exception) {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR, exception);
		}
		return pluginProperties;
	}
	
	public static String getProperty(final String propertyKey) throws ExportConfigurationException {
		return PluginProperties.getInstance().configuration.getString(propertyKey);
	}
}
