package tuwien.ac.at.mining.json;

import java.io.Serializable;

/**
 * @author Vanja Bisanovic
 *
 */
public class ScopeJSON implements Serializable {

	private static final long serialVersionUID = 6460063435077876552L;

	private String[] placeIds;

	public ScopeJSON() {
	}

	public String[] getPlaceIds() {
		return placeIds;
	}

	public void setPlaceIds(String[] placeIds) {
		this.placeIds = placeIds;
	}

}
