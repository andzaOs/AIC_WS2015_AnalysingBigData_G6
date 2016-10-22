package tuwien.ac.at.mining.json;

import java.io.Serializable;

/**
 * @author Vanja Bisanovic
 *
 */
public class UserMentionedJSON implements Serializable {

	private static final long serialVersionUID = 5221599789247823449L;
	
	private String name;
	private String screenName;
	private long id;

	public UserMentionedJSON() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
