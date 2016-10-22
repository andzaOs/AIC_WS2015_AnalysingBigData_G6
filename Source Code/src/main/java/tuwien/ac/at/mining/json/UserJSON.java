package tuwien.ac.at.mining.json;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Vanja Bisanovic
 *
 */
public class UserJSON implements java.io.Serializable {

	private static final long serialVersionUID = 8869415011072840592L;

	private long id;
	private String name;
	private String screenName;
	private String location;
	private String description;
	private UrlJSON[] descriptionURLEntities;
	private UrlJSON urlEntity;
	private boolean isContributorsEnabled;
	private String profileImageUrl;
	private String profileImageUrlHttps;
	private boolean isDefaultProfileImage;
	private String url;
	private boolean isProtected;
	private int followersCount;

	private TweetJSON status;

	private String profileBackgroundColor;
	private String profileTextColor;
	private String profileLinkColor;
	private String profileSidebarFillColor;
	private String profileSidebarBorderColor;
	private boolean profileUseBackgroundImage;
	private boolean isDefaultProfile;
	private boolean showAllInlineMedia;
	private int friendsCount;
	private Date createdAt;
	private int favouritesCount;
	private int utcOffset;
	private String timeZone;
	private String profileBackgroundImageUrl;
	private String profileBackgroundImageUrlHttps;
	private String profileBannerImageUrl;
	private boolean profileBackgroundTiled;
	private String lang;
	private int statusesCount;
	private boolean isGeoEnabled;
	private boolean isVerified;
	private boolean translator;
	private int listedCount;
	private boolean isFollowRequestSent;
	private String[] withheldInCountries;

	public UserJSON() {

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public boolean isContributorsEnabled() {
		return isContributorsEnabled;
	}

	public String getProfileImageURL() {
		return profileImageUrl;
	}

	public String getBiggerProfileImageURL() {
		return toResizedURL(profileImageUrl, "_bigger");
	}

	public String getMiniProfileImageURL() {
		return toResizedURL(profileImageUrl, "_mini");
	}

	public String getOriginalProfileImageURL() {
		return toResizedURL(profileImageUrl, "");
	}

	private String toResizedURL(String originalURL, String sizeSuffix) {
		if (null != originalURL) {
			int index = originalURL.lastIndexOf("_");
			int suffixIndex = originalURL.lastIndexOf(".");
			int slashIndex = originalURL.lastIndexOf("/");
			String url = originalURL.substring(0, index) + sizeSuffix;
			if (suffixIndex > slashIndex) {
				url += originalURL.substring(suffixIndex);
			}
			return url;
		}
		return null;
	}

	public String getProfileImageURLHttps() {
		return profileImageUrlHttps;
	}

	public String getBiggerProfileImageURLHttps() {
		return toResizedURL(profileImageUrlHttps, "_bigger");
	}

	public String getMiniProfileImageURLHttps() {
		return toResizedURL(profileImageUrlHttps, "_mini");
	}

	public String getOriginalProfileImageURLHttps() {
		return toResizedURL(profileImageUrlHttps, "");
	}

	public boolean isDefaultProfileImage() {
		return isDefaultProfileImage;
	}

	/**
	 * {@inheritDoc}
	 */

	public String getURL() {
		return url;
	}

	public boolean isProtected() {
		return isProtected;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public String getProfileBackgroundColor() {
		return profileBackgroundColor;
	}

	public String getProfileTextColor() {
		return profileTextColor;
	}

	public String getProfileLinkColor() {
		return profileLinkColor;
	}

	public String getProfileSidebarFillColor() {
		return profileSidebarFillColor;
	}

	public String getProfileSidebarBorderColor() {
		return profileSidebarBorderColor;
	}

	public boolean isProfileUseBackgroundImage() {
		return profileUseBackgroundImage;
	}

	public boolean isDefaultProfile() {
		return isDefaultProfile;
	}

	/**
	 * {@inheritDoc}
	 */

	public boolean isShowAllInlineMedia() {
		return showAllInlineMedia;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public TweetJSON getStatus() {
		return status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public int getFavouritesCount() {
		return favouritesCount;
	}

	public int getUtcOffset() {
		return utcOffset;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public String getProfileBackgroundImageURL() {
		return profileBackgroundImageUrl;
	}

	public String getProfileBackgroundImageUrlHttps() {
		return profileBackgroundImageUrlHttps;
	}

	public String getProfileBannerURL() {
		return profileBannerImageUrl != null ? profileBannerImageUrl + "/web"
				: null;
	}

	public String getProfileBannerRetinaURL() {
		return profileBannerImageUrl != null ? profileBannerImageUrl
				+ "/web_retina" : null;
	}

	public String getProfileBannerIPadURL() {
		return profileBannerImageUrl != null ? profileBannerImageUrl + "/ipad"
				: null;
	}

	public String getProfileBannerIPadRetinaURL() {
		return profileBannerImageUrl != null ? profileBannerImageUrl
				+ "/ipad_retina" : null;
	}

	public String getProfileBannerMobileURL() {
		return profileBannerImageUrl != null ? profileBannerImageUrl
				+ "/mobile" : null;
	}

	public String getProfileBannerMobileRetinaURL() {
		return profileBannerImageUrl != null ? profileBannerImageUrl
				+ "/mobile_retina" : null;
	}

	public boolean isProfileBackgroundTiled() {
		return profileBackgroundTiled;
	}

	public String getLang() {
		return lang;
	}

	public int getStatusesCount() {
		return statusesCount;
	}

	public boolean isGeoEnabled() {
		return isGeoEnabled;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public boolean isTranslator() {
		return translator;
	}

	public int getListedCount() {
		return listedCount;
	}

	public boolean isFollowRequestSent() {
		return isFollowRequestSent;
	}

	public UrlJSON[] getDescriptionURLEntities() {
		return descriptionURLEntities;
	}

	public UrlJSON getUrlEntity() {
		return urlEntity;
	}

	public void setUrlEntity(UrlJSON urlEntity) {
		this.urlEntity = urlEntity;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getProfileImageUrlHttps() {
		return profileImageUrlHttps;
	}

	public void setProfileImageUrlHttps(String profileImageUrlHttps) {
		this.profileImageUrlHttps = profileImageUrlHttps;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProfileBackgroundImageUrl() {
		return profileBackgroundImageUrl;
	}

	public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
		this.profileBackgroundImageUrl = profileBackgroundImageUrl;
	}

	public String getProfileBannerImageUrl() {
		return profileBannerImageUrl;
	}

	public void setProfileBannerImageUrl(String profileBannerImageUrl) {
		this.profileBannerImageUrl = profileBannerImageUrl;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescriptionURLEntities(UrlJSON[] descriptionURLEntities) {
		this.descriptionURLEntities = descriptionURLEntities;
	}

	public void setContributorsEnabled(boolean isContributorsEnabled) {
		this.isContributorsEnabled = isContributorsEnabled;
	}

	public void setDefaultProfileImage(boolean isDefaultProfileImage) {
		this.isDefaultProfileImage = isDefaultProfileImage;
	}

	public void setProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public void setStatus(TweetJSON status) {
		this.status = status;
	}

	public void setProfileBackgroundColor(String profileBackgroundColor) {
		this.profileBackgroundColor = profileBackgroundColor;
	}

	public void setProfileTextColor(String profileTextColor) {
		this.profileTextColor = profileTextColor;
	}

	public void setProfileLinkColor(String profileLinkColor) {
		this.profileLinkColor = profileLinkColor;
	}

	public void setProfileSidebarFillColor(String profileSidebarFillColor) {
		this.profileSidebarFillColor = profileSidebarFillColor;
	}

	public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
		this.profileSidebarBorderColor = profileSidebarBorderColor;
	}

	public void setProfileUseBackgroundImage(boolean profileUseBackgroundImage) {
		this.profileUseBackgroundImage = profileUseBackgroundImage;
	}

	public void setDefaultProfile(boolean isDefaultProfile) {
		this.isDefaultProfile = isDefaultProfile;
	}

	public void setShowAllInlineMedia(boolean showAllInlineMedia) {
		this.showAllInlineMedia = showAllInlineMedia;
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setFavouritesCount(int favouritesCount) {
		this.favouritesCount = favouritesCount;
	}

	public void setUtcOffset(int utcOffset) {
		this.utcOffset = utcOffset;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setProfileBackgroundImageUrlHttps(
			String profileBackgroundImageUrlHttps) {
		this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
	}

	public void setProfileBackgroundTiled(boolean profileBackgroundTiled) {
		this.profileBackgroundTiled = profileBackgroundTiled;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setStatusesCount(int statusesCount) {
		this.statusesCount = statusesCount;
	}

	public void setGeoEnabled(boolean isGeoEnabled) {
		this.isGeoEnabled = isGeoEnabled;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public void setTranslator(boolean translator) {
		this.translator = translator;
	}

	public void setListedCount(int listedCount) {
		this.listedCount = listedCount;
	}

	public void setFollowRequestSent(boolean isFollowRequestSent) {
		this.isFollowRequestSent = isFollowRequestSent;
	}

	public void setWithheldInCountries(String[] withheldInCountries) {
		this.withheldInCountries = withheldInCountries;
	}

	public String[] getWithheldInCountries() {
		return withheldInCountries;
	}

	public int hashCode() {
		return (int) id;
	}

	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		return obj instanceof UserJSON && ((UserJSON) obj).getId() == this.id;
	}

	@Override
	public String toString() {
		return "UserJSONImpl{" + "id=" + id + ", name='" + name + '\''
				+ ", screenName='" + screenName + '\'' + ", location='"
				+ location + '\'' + ", description='" + description + '\''
				+ ", isContributorsEnabled=" + isContributorsEnabled
				+ ", profileImageUrl='" + profileImageUrl + '\''
				+ ", profileImageUrlHttps='" + profileImageUrlHttps + '\''
				+ ", isDefaultProfileImage=" + isDefaultProfileImage
				+ ", url='" + url + '\'' + ", isProtected=" + isProtected
				+ ", followersCount=" + followersCount + ", status=" + status
				+ ", profileBackgroundColor='" + profileBackgroundColor + '\''
				+ ", profileTextColor='" + profileTextColor + '\''
				+ ", profileLinkColor='" + profileLinkColor + '\''
				+ ", profileSidebarFillColor='" + profileSidebarFillColor
				+ '\'' + ", profileSidebarBorderColor='"
				+ profileSidebarBorderColor + '\''
				+ ", profileUseBackgroundImage=" + profileUseBackgroundImage
				+ ", isDefaultProfile=" + isDefaultProfile
				+ ", showAllInlineMedia=" + showAllInlineMedia
				+ ", friendsCount=" + friendsCount + ", createdAt=" + createdAt
				+ ", favouritesCount=" + favouritesCount + ", utcOffset="
				+ utcOffset + ", timeZone='" + timeZone + '\''
				+ ", profileBackgroundImageUrl='" + profileBackgroundImageUrl
				+ '\'' + ", profileBackgroundImageUrlHttps='"
				+ profileBackgroundImageUrlHttps + '\''
				+ ", profileBackgroundTiled=" + profileBackgroundTiled
				+ ", lang='" + lang + '\'' + ", statusesCount=" + statusesCount
				+ ", isGeoEnabled=" + isGeoEnabled + ", isVerified="
				+ isVerified + ", translator=" + translator + ", listedCount="
				+ listedCount + ", isFollowRequestSent=" + isFollowRequestSent
				+ ", withheldInCountries="
				+ Arrays.toString(withheldInCountries) + '}';
	}

}
