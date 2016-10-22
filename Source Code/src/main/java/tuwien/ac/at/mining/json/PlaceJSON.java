package tuwien.ac.at.mining.json;

import java.util.Arrays;

/**
 * @author Vanja Bisanovic
 *
 */
public class PlaceJSON implements java.io.Serializable {

	private static final long serialVersionUID = -1133026670612386863L;

	private String name;
	private String streetAddress;
	private String countryCode;
	private String id;
	private String country;
	private String placeType;
	private String url;
	private String fullName;
	private String boundingBoxType;
	private GeoLocationJSON[][] boundingBoxCoordinates;
	private String geometryType;
	private GeoLocationJSON[][] geometryCoordinates;
	private PlaceJSON[] containedWithIn;

	public PlaceJSON() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBoundingBoxType() {
		return boundingBoxType;
	}

	public void setBoundingBoxType(String boundingBoxType) {
		this.boundingBoxType = boundingBoxType;
	}

	public GeoLocationJSON[][] getBoundingBoxCoordinates() {
		return boundingBoxCoordinates;
	}

	public void setBoundingBoxCoordinates(
			GeoLocationJSON[][] boundingBoxCoordinates) {
		this.boundingBoxCoordinates = boundingBoxCoordinates;
	}

	public String getGeometryType() {
		return geometryType;
	}

	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	public GeoLocationJSON[][] getGeometryCoordinates() {
		return geometryCoordinates;
	}

	public void setGeometryCoordinates(GeoLocationJSON[][] geometryCoordinates) {
		this.geometryCoordinates = geometryCoordinates;
	}

	public PlaceJSON[] getContainedWithIn() {
		return containedWithIn;
	}

	public void setContainedWithIn(PlaceJSON[] containedWithIn) {
		this.containedWithIn = containedWithIn;
	}

	@Override
	public String toString() {
		return "PlaceJSONImpl{" + "name='"
				+ name
				+ '\''
				+ ", streetAddress='"
				+ streetAddress
				+ '\''
				+ ", countryCode='"
				+ countryCode
				+ '\''
				+ ", id='"
				+ id
				+ '\''
				+ ", country='"
				+ country
				+ '\''
				+ ", placeType='"
				+ placeType
				+ '\''
				+ ", url='"
				+ url
				+ '\''
				+ ", fullName='"
				+ fullName
				+ '\''
				+ ", boundingBoxType='"
				+ boundingBoxType
				+ '\''
				+ ", boundingBoxCoordinates="
				+ (boundingBoxCoordinates == null ? null : Arrays
						.asList(boundingBoxCoordinates))
				+ ", geometryType='"
				+ geometryType
				+ '\''
				+ ", geometryCoordinates="
				+ (geometryCoordinates == null ? null : Arrays
						.asList(geometryCoordinates))
				+ ", containedWithIn="
				+ (containedWithIn == null ? null : Arrays
						.asList(containedWithIn)) + '}';
	}
}
