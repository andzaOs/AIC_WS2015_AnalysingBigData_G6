package tuwien.ac.at.mining.json;

import java.io.Serializable;

/**
 * @author Vanja Bisanovic
 *
 */
public class GeoLocationJSON implements Serializable {

	private static final long serialVersionUID = 1L;

	private double latitude;
	private double longitude;

	public GeoLocationJSON() {
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
