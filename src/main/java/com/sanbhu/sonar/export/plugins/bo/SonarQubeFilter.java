package com.sanbhu.sonar.export.plugins.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to apply filter on Sonar Qube.
 * 
 * @author Lavanya
 */
public class SonarQubeFilter {
	
	/** types. */
	private List<String> types = new ArrayList<String>();
	
	/** resolutions. */
	private List<String> resolutions = new ArrayList<String>();;
	
	/** severities. */
	private List<String> severities = new ArrayList<String>();;
	
	/** projects. */
	private List<String> projects = new ArrayList<String>();;
	
	/** startDate. */
	private String startDate;
	
	/** endDate. */
	private String endDate;

	/**
	 * Getter method for type list.
	 * 
	 * @return list instance.
	 */
	public List<String> getTypes() {
		return types;
	}

	/**
	 * Setter method for types list.
	 * 
	 * @param types list instance.
	 */
	public void setTypes(final List<String> types) {
		this.types = types;
	}

	/**
	 * Getter method for resolutions.
	 * 
	 * @return resolution list instance.
	 */
	public List<String> getResolutions() {
		return resolutions;
	}

	/**
	 * Setter method for resolutions.
	 * 
	 * @param resolutions list instance.
	 */
	public void setResolutions(final List<String> resolutions) {
		this.resolutions = resolutions;
	}

	/**
	 * Getter method for severities list.
	 * 
	 * @return severities list instance.
	 */
	public List<String> getSeverities() {
		return severities;
	}

	/**
	 * Setter method for severities.
	 * 
	 * @param severities list.
	 */
	public void setSeverities(final List<String> severities) {
		this.severities = severities;
	}

	/**
	 * Getter method for project list.
	 * 
	 * @return projects list instance.
	 */
	public List<String> getProjects() {
		return projects;
	}

	/**
	 * Setter method for Project list.
	 * 
	 * @param projects list instance.
	 */
	public void setProjects(final List<String> projects) {
		this.projects = projects;
	}

	/**
	 * Getter method for startDate.
	 * 
	 * @return startDate string.
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Setter method for startDate.
	 * 
	 * @param startDate string.
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Getter method for endDate.
	 * 
	 * @return endDate.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Setter method for endDate.
	 * 
	 * @param endDate string.
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}
}
