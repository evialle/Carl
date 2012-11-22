package org.vialle.ia.pojo.settings;

import java.io.Serializable;

public class UserDocumentPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3731735854455131609L;

	private String userName;

	private String passwordEncrypted;

	private SettingsPojo settings;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * @return the passwordEncrypted
	 */
	public String getPasswordEncrypted() {
		return this.passwordEncrypted;
	}

	/**
	 * @param passwordEncrypted
	 *            the passwordEncrypted to set
	 */
	public void setPasswordEncrypted(final String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	/**
	 * @return the settings
	 */
	public SettingsPojo getSettings() {
		return this.settings;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	public void setSettings(final SettingsPojo settings) {
		this.settings = settings;
	}

}
