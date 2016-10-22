package tuwien.ac.at.mining.json;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Vanja Bisanovic
 *
 */
public class TweetJSON implements java.io.Serializable {

	private static final long serialVersionUID = -6461195536943679985L;

	private Date createdAt;
	private long id;
	private String text;
	private String source;
	private boolean isTruncated;
	private long inReplyToTweetId;
	private long inReplyToUserId;
	private boolean isFavorited;
	private boolean isRetweeted;
	private int favoriteCount;
	private String inReplyToScreenName;
	private GeoLocationJSON geoLocation = null;
	private PlaceJSON place = null;
	private long retweetCount;
	private boolean isPossiblySensitive;
	private String lang;

	private long[] contributorsIDs;

	private TweetJSON retweetedStatus;
	private UserMentionedJSON[] userMentionEntities;
	private UrlJSON[] urlEntities;
	private HashtagJSON[] hashtagEntities;
	private MediaJSON[] mediaEntities;
	private ExtendedMediaJSON[] extendedMediaEntities;
	private HashtagJSON[] symbolEntities;
	private long currentUserRetweetId = -1L;
	private ScopeJSON scopes;
	private UserJSON user = null;
	private String[] withheldInCountries = null;
	private TweetJSON quotedStatus;
	private long quotedStatusId = -1L;

	public TweetJSON() {

	}

	public int compareTo(TweetJSON that) {
		long delta = this.id - that.getId();
		if (delta < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else if (delta > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return (int) delta;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public long getId() {
		return this.id;
	}

	public String getText() {
		return this.text;
	}

	public String getSource() {
		return this.source;
	}

	public boolean isTruncated() {
		return isTruncated;
	}

	public long getInReplyToTweetId() {
		return inReplyToTweetId;
	}

	public long getInReplyToUserId() {
		return inReplyToUserId;
	}

	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}

	public GeoLocationJSON getGeoLocation() {
		return geoLocation;
	}

	public PlaceJSON getPlace() {
		return place;
	}

	public long[] getContributors() {
		return contributorsIDs;
	}

	public boolean isFavorited() {
		return isFavorited;
	}

	public boolean isRetweeted() {
		return isRetweeted;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public UserJSON getUser() {
		return user;
	}

	public boolean isRetweet() {
		return retweetedStatus != null;
	}

	public TweetJSON getRetweetedStatus() {
		return retweetedStatus;
	}

	public int getRetweetCount() {
		return (int) retweetCount;
	}

	public boolean isRetweetedByMe() {
		return currentUserRetweetId != -1L;
	}

	public long getCurrentUserRetweetId() {
		return currentUserRetweetId;
	}

	public boolean isPossiblySensitive() {
		return isPossiblySensitive;
	}

	public UserMentionedJSON[] getUserMentionEntities() {
		return userMentionEntities;
	}

	public UrlJSON[] getURLEntities() {
		return urlEntities;
	}

	public HashtagJSON[] getHashtagEntities() {
		return hashtagEntities;
	}

	public MediaJSON[] getMediaEntities() {
		return mediaEntities;
	}

	public ExtendedMediaJSON[] getExtendedMediaEntities() {
		return extendedMediaEntities;
	}

	public HashtagJSON[] getSymbolEntities() {
		return symbolEntities;
	}

	public ScopeJSON getScopes() {
		return scopes;
	}

	public String[] getWithheldInCountries() {
		return withheldInCountries;
	}

	public long getQuotedStatusId() {
		return quotedStatusId;
	}

	public TweetJSON getQuotedStatus() {
		return quotedStatus;
	}

	public String getLang() {
		return lang;
	}

	public long[] getContributorsIDs() {
		return contributorsIDs;
	}

	public void setContributorsIDs(long[] contributorsIDs) {
		this.contributorsIDs = contributorsIDs;
	}

	public UrlJSON[] getUrlEntities() {
		return urlEntities;
	}

	public void setUrlEntities(UrlJSON[] urlEntities) {
		this.urlEntities = urlEntities;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setTruncated(boolean isTruncated) {
		this.isTruncated = isTruncated;
	}

	public void setInReplyToTweetId(long inReplyToTweetId) {
		this.inReplyToTweetId = inReplyToTweetId;
	}

	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public void setFavorited(boolean isFavorited) {
		this.isFavorited = isFavorited;
	}

	public void setRetweeted(boolean isRetweeted) {
		this.isRetweeted = isRetweeted;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public void setGeoLocation(GeoLocationJSON geoLocation) {
		this.geoLocation = geoLocation;
	}

	public void setPlace(PlaceJSON place) {
		this.place = place;
	}

	public void setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
	}

	public void setPossiblySensitive(boolean isPossiblySensitive) {
		this.isPossiblySensitive = isPossiblySensitive;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setRetweetedTweet(TweetJSON retweetedTweet) {
		this.retweetedStatus = retweetedTweet;
	}

	public void setUserMentionEntities(UserMentionedJSON[] userMentionEntities) {
		this.userMentionEntities = userMentionEntities;
	}

	public void setHashtagEntities(HashtagJSON[] hashtagEntities) {
		this.hashtagEntities = hashtagEntities;
	}

	public void setMediaEntities(MediaJSON[] mediaEntities) {
		this.mediaEntities = mediaEntities;
	}

	public void setExtendedMediaEntities(
			ExtendedMediaJSON[] extendedMediaEntities) {
		this.extendedMediaEntities = extendedMediaEntities;
	}

	public void setSymbolEntities(HashtagJSON[] symbolEntities) {
		this.symbolEntities = symbolEntities;
	}

	public void setCurrentUserRetweetId(long currentUserRetweetId) {
		this.currentUserRetweetId = currentUserRetweetId;
	}

	public void setScopes(ScopeJSON scopes) {
		this.scopes = scopes;
	}

	public void setUser(UserJSON user) {
		this.user = user;
	}

	public void setWithheldInCountries(String[] withheldInCountries) {
		this.withheldInCountries = withheldInCountries;
	}

	public void setQuotedStatus(TweetJSON quotedStatus) {
		this.quotedStatus = quotedStatus;
	}

	public void setQuotedStatusIdd(long quotedStatusId) {
		this.quotedStatusId = quotedStatusId;
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
		return obj instanceof TweetJSON && ((TweetJSON) obj).getId() == this.id;
	}

	public String toString() {
		return "TweetJSONImpl{" + "createdAt=" + createdAt + ", id=" + id
				+ ", text='" + text + '\'' + ", source='" + source + '\''
				+ ", isTruncated=" + isTruncated + ", inReplyToTweetId="
				+ inReplyToTweetId + ", inReplyToUserId=" + inReplyToUserId
				+ ", isFavorited=" + isFavorited + ", isRetweeted="
				+ isRetweeted + ", favoriteCount=" + favoriteCount
				+ ", inReplyToScreenName='" + inReplyToScreenName + '\''
				+ ", geoLocation=" + geoLocation + ", place=" + place
				+ ", retweetCount=" + retweetCount + ", isPossiblySensitive="
				+ isPossiblySensitive + ", lang='" + lang + '\''
				+ ", contributorsIDs=" + Arrays.toString(contributorsIDs)
				+ ", retweetedTweet=" + retweetedStatus
				+ ", userMentionEntities="
				+ Arrays.toString(userMentionEntities) + ", urlEntities="
				+ Arrays.toString(urlEntities) + ", hashtagEntities="
				+ Arrays.toString(hashtagEntities) + ", mediaEntities="
				+ Arrays.toString(mediaEntities) + ", symbolEntities="
				+ Arrays.toString(symbolEntities) + ", currentUserRetweetId="
				+ currentUserRetweetId + ", user=" + user
				+ ", withHeldInCountries="
				+ Arrays.toString(withheldInCountries) + ", quotedTweetId="
				+ quotedStatusId + ", quotedTweet=" + quotedStatus + '}';
	}
}
