package org.vialle.carl.iaservice.services;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Eric
 *
 */
public class CarlAiAnswer {
	
	private String type = StringUtils.EMPTY;
	
	private String speak = StringUtils.EMPTY;



    private String error = StringUtils.EMPTY;
	
	public CarlAiAnswer() {
	}
	
	public CarlAiAnswer(final String speak) {
		this.speak = speak;
	}
	
	public CarlAiAnswer(final String speak, final String type) {
		this.speak = speak;
		this.type = type;
	}
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return type;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String type) {
		this.type = type;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
