package tuwien.ac.at.mining.json;

import java.io.Serializable;

/**
 * @author Vanja Bisanovic
 *
 */
public class UrlJSON implements Serializable {

	private static final long serialVersionUID = -3771666035126100913L;
	
	private String url;
	private String expandedURL;
	private String displayURL;
	private int start;
	private int ent;
	
	public UrlJSON() {		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpandedURL() {
		return expandedURL;
	}

	public void setExpandedURL(String expandedURL) {
		this.expandedURL = expandedURL;
	}

	public String getDisplayURL() {
		return displayURL;
	}

	public void setDisplayURL(String displayURL) {
		this.displayURL = displayURL;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnt() {
		return ent;
	}

	public void setEnt(int ent) {
		this.ent = ent;
	}

}
