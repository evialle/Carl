/**
 * 
 */
package org.vialle.ia.settings.faces;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vialle.ia.pojo.settings.UserDocumentPojo;

/**
 * @author Eric
 * 
 */
@ManagedBean
@RolesAllowed({ "ROLE_USER" })
@SessionScoped
public class Settings {

	private UserDocumentPojo pojo;

	@PostConstruct
	private void init() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String userName = authentication.getName();

		// TODO get username from the database
	}

	/**
	 * 
	 * @return
	 */
	public String listenerUpdateValues(final AjaxBehaviorEvent event) {
		return null;
	}

	/**
	 * @return the pojo
	 */
	public UserDocumentPojo getPojo() {
		return this.pojo;
	}

	/**
	 * @param pojo
	 *            the pojo to set
	 */
	public void setPojo(final UserDocumentPojo pojo) {
		this.pojo = pojo;
	}

}
