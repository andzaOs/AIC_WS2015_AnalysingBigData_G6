package tuwien.ac.at.mining.json;

import java.io.Serializable;

/**
 * @author Vanja Bisanovic
 *
 */
public class TopicJSON implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String words;

	public TopicJSON() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "TopicJSON [name=" + name + ", words=" + words + "]";
	}

}
