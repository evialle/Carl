/**
 * 
 */
package org.vialle.ia.settings.faces;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vialle.ia.settings.pojo.SettingsPojo;
import org.vialle.ia.settings.pojo.UserDocumentPojo;

/**
 * @author Eric
 * 
 */
@ManagedBean
@RolesAllowed({ "ROLE_USER" })
@SessionScoped
public class Settings {

	@ManagedProperty("#{mongoDbProvider}")
	private MongoOperations mongoDbProvider;

	private UserDocumentPojo pojo;

	@PostConstruct
	private void init() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String userName = authentication.getName();
		this.pojo = getMongoDbProvider().findOne(Query.query(Criteria.where("userName").is(userName)), UserDocumentPojo.class);
		if (this.pojo == null) {
			this.pojo = new UserDocumentPojo();
			this.pojo.setUserName(userName);
			this.pojo.setSettings(new SettingsPojo());
			getMongoDbProvider().save(this.pojo);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String listenerUpdateValues(final AjaxBehaviorEvent event) {
		return null;
	}

	/**
	 * @return the mongoDbProvider
	 */
	public MongoOperations getMongoDbProvider() {
		return this.mongoDbProvider;
	}

	/**
	 * @param mongoDbProvider
	 *            the mongoDbProvider to set
	 */
	public void setMongoDbProvider(final MongoOperations mongoDbProvider) {
		this.mongoDbProvider = mongoDbProvider;
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
