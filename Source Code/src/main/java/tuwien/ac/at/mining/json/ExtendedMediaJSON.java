package tuwien.ac.at.mining.json;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Vanja Bisanovic
 *
 */
public class ExtendedMediaJSON extends MediaJSON {

	private static final long serialVersionUID = 1630340248800707346L;

	private int videoAspectRatioWidth;
	private int videoAspectRatioHeight;
	private long videoDurationMillis;
	private Variant[] videoVariants;

	public ExtendedMediaJSON() {

	}

	public int getVideoAspectRatioWidth() {
		return videoAspectRatioWidth;
	}

	public void setVideoAspectRatioWidth(int videoAspectRatioWidth) {
		this.videoAspectRatioWidth = videoAspectRatioWidth;
	}

	public int getVideoAspectRatioHeight() {
		return videoAspectRatioHeight;
	}

	public void setVideoAspectRatioHeight(int videoAspectRatioHeight) {
		this.videoAspectRatioHeight = videoAspectRatioHeight;
	}

	public long getVideoDurationMillis() {
		return videoDurationMillis;
	}

	public void setVideoDurationMillis(long videoDurationMillis) {
		this.videoDurationMillis = videoDurationMillis;
	}

	public Variant[] getVideoVariants() {
		return videoVariants;
	}

	public void setVideoVariants(Variant[] videoVariants) {
		this.videoVariants = videoVariants;
	}

	static class Variant implements Serializable {

		private static final long serialVersionUID = -7043570222226176042L;
		
		int bitrate;
		String contentType;
		String url;

		public Variant() {
		}

		

		public int getBitrate() {
			return bitrate;
		}



		public void setBitrate(int bitrate) {
			this.bitrate = bitrate;
		}



		public String getContentType() {
			return contentType;
		}



		public void setContentType(String contentType) {
			this.contentType = contentType;
		}



		public String getUrl() {
			return url;
		}



		public void setUrl(String url) {
			this.url = url;
		}



		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Variant))
				return false;

			Variant variant = (Variant) o;

			if (bitrate != variant.bitrate)
				return false;
			if (!contentType.equals(variant.contentType))
				return false;
			if (!url.equals(variant.url))
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = bitrate;
			result = 31 * result
					+ (contentType != null ? contentType.hashCode() : 0);
			result = 31 * result + (url != null ? url.hashCode() : 0);
			return result;
		}

		@Override
		public String toString() {
			return "Variant{" + "bitrate=" + bitrate + ", contentType="
					+ contentType + ", url=" + url + '}';
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ExtendedMediaJSON))
			return false;

		ExtendedMediaJSON that = (ExtendedMediaJSON) o;

		if (getId() != that.getId())
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (getId() ^ (getId() >>> 32));
	}

	@Override
	public String toString() {
		return "ExtendedMediaEntityJSONImpl{" + "id=" + getId() + ", url=" + getUrl()
				+ ", mediaURL=" + getMediaURL() + ", mediaURLHttps=" + getMediaURLHttps()
				+ ", expandedURL=" + getExpandedURL() + ", displayURL='"
				+ getDisplayURL() + '\'' + ", sizes=" + getSizes() + ", type=" + getType()
				+ ", videoAspectRatioWidth=" + videoAspectRatioWidth
				+ ", videoAspectRatioHeight=" + videoAspectRatioHeight
				+ ", videoDurationMillis=" + videoDurationMillis
				+ ", videoVariants=" + Arrays.toString(videoVariants) + '}';
	}
}
