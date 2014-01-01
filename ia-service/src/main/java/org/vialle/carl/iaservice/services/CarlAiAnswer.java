package org.vialle.carl.iaservice.services;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Eric
 *
 */
public class CarlAiAnswer {
	
	private String action = StringUtils.EMPTY;
	
	private String speak = StringUtils.EMPTY;
	
	public CarlAiAnswer() {
	}
	
	public CarlAiAnswer(final String speak) {
		this.speak = speak;
	}
	
	public CarlAiAnswer(final String speak, final String action) {
		this.speak = speak;
		this.action = action;
	}
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the speak
	 */
	public String getSpeak() {
		return speak;
	}

	/**
	 * @param speak the speak to set
	 */
	public void setSpeak(String speak) {
		this.speak = speak;
	}

}
