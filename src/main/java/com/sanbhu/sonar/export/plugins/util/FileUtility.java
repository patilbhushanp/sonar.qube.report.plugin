package com.sanbhu.sonar.export.plugins.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sanbhu.sonar.export.plugins.constants.ExportErrorCodes;
import com.sanbhu.sonar.export.plugins.exception.ExportConfigurationException;

public class FileUtility {
	
	public static boolean writeData(final String data, final File file, final boolean isAppendMode)
			throws ExportConfigurationException {
		boolean writeResult = false;
		try (final FileWriter fileWriter = new FileWriter(file, isAppendMode);){
			fileWriter.write(data);
			fileWriter.flush();
		} catch (IOException exception) {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR, exception);
		}
		return writeResult;
	}
	
	public static String readData(final File file) throws ExportConfigurationException {
		final StringBuilder stringBuilder = new StringBuilder();
		try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));){
			while(true) {
				final String str = bufferedReader.readLine();
				if(str == null) {
					break;
				}
				stringBuilder.append(str);
			}
		} catch (IOException exception) {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR, exception);
		}
		return stringBuilder.toString();
	}
}
