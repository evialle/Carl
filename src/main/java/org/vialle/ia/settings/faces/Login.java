/**
 * 
 */
package org.vialle.ia.settings.faces;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Eric
 * 
 */
@ManagedBean()
@RequestScoped
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8086707561126810972L;

	private String password;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	// Security

	public String authenticate() {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(this.login, this.password);
		try {
			Authentication authenticate = this.authenticationManager.authenticate(authentication);
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return "/pages/admin/settings?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentifcation refused", "Login or password incorrect."));
				return null;
			}
		} catch (DisabledException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentifcation refused", "Your account has been disabled"));
			return null;
		} catch (LockedException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentifcation refused", "Your account is locked"));
			return null;

		} catch (BadCredentialsException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentifcation refused", "Login or password incorrect."));
			return null;
		}

	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @return the authenticationManager
	 */
	public AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}

	/**
	 * @param authenticationManager
	 *            the authenticationManager to set
	 */
	public void setAuthenticationManager(final AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	private String login;

}