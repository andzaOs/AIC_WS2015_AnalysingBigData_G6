package tuwien.ac.at.mining.json;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Vanja Bisanovic
 *
 */
public class MediaJSON implements Serializable {

	private static final long serialVersionUID = 932037535604961230L;

	private long id;
	private String url;
	private String mediaURL;
	private String mediaURLHttps;
	private String expandedURL;
	private String displayURL;
	private Map<Integer, MediaJSON.Size> sizes;
	private String type;
	private int start;
	private int end;

	public MediaJSON() {

	}

	public long getId() {
		return id;
	}

	public String getMediaURL() {
		return mediaURL;
	}

	public String getMediaURLHttps() {
		return mediaURLHttps;
	}

	public String getText() {
		return url;
	}

	public String getURL() {
		return url;
	}

	public String getDisplayURL() {
		return displayURL;
	}

	public String getExpandedURL() {
		return expandedURL;
	}

	public Map<Integer, MediaJSON.Size> getSizes() {
		return sizes;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}

	public void setMediaURLHttps(String mediaURLHttps) {
		this.mediaURLHttps = mediaURLHttps;
	}

	public void setExpandedURL(String expandedURL) {
		this.expandedURL = expandedURL;
	}

	public void setDisplayURL(String displayURL) {
		this.displayURL = displayURL;
	}

	public void setSizes(Map<Integer, MediaJSON.Size> sizes) {
		this.sizes = sizes;
	}

	public void setType(String type) {
		this.type = type;
	}

	static class Size implements Serializable {

		private static final long serialVersionUID = -5225545990741724134L;

		int width;
		int height;
		int resize;

		public Size() {
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getResize() {
			return resize;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public void setResize(int resize) {
			this.resize = resize;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Size))
				return false;

			Size size = (Size) o;

			if (height != size.height)
				return false;
			if (resize != size.resize)
				return false;
			if (width != size.width)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = width;
			result = 31 * result + height;
			result = 31 * result + resize;
			return result;
		}

		@Override
		public String toString() {
			return "Size{" + "width=" + width + ", height=" + height
					+ ", resize=" + resize + '}';
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof MediaJSON))
			return false;

		MediaJSON that = (MediaJSON) o;

		if (id != that.getId())
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "MediaEntityJSONImpl{" + "id=" + id + ", url=" + url
				+ ", mediaURL=" + mediaURL + ", mediaURLHttps=" + mediaURLHttps
				+ ", expandedURL=" + expandedURL + ", displayURL='"
				+ displayURL + '\'' + ", sizes=" + sizes + ", type=" + type
				+ '}';
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
