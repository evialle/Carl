package org.vialle.ia.pojo.settings;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SettingsPojo implements Serializable {

	private static final long serialVersionUID = 4570466474704046824L;

	@Id
	public long id;

	public String urlRssNews;

}
