package tuwien.ac.at.mining.json;

import java.io.Serializable;

/**
 * @author Vanja Bisanovic
 *
 */
public class HashtagJSON implements Serializable {

	private static final long serialVersionUID = -5317828991902848906L;
	private String text;

	public HashtagJSON() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HashtagJSON that = (HashtagJSON) o;

		if (text != null ? !text.equals(that.text) : that.text != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return text != null ? text.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "HashtagEntityJSONImpl{" + "text='" + text + '\'' + '}';
	}
}