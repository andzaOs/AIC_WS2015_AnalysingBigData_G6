package tuwien.ac.at.mining;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Vanja Bisanovic
 *
 */
public class PublicProfileDownloader {

	private static final Logger LOGGER = Logger.getLogger("PublicProfileMiner");

	// Use this to save jsons with nice formatting
	// private static Gson gson = new
	// GsonBuilder().setPrettyPrinting().create();
	private static Gson gson = new GsonBuilder().create();

	FileOutputStream outputStream;
	static OutputStreamWriter outputStreamWriter;
	static BufferedWriter bufferedWriter;

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			LOGGER.info("<provide a user twitter name for monitoring>");
			return;
		}
		String name = args[0];
		try {
			String directoryPath = Configuration.getInstance().getValue(
					Constants.CONFIGURATION_KEY_FILE_TWITTER_DATA);
			File file = new File(directoryPath);
			if (!file.exists()) {
				file.mkdirs();
			}

			String filePath = StringUtils.join(directoryPath,
					Constants.FILE_TWITTER_USER_DATA_PREFIX, name,
					Constants.FILE_SUFFIX);

			File twitterDataFile = new File(filePath);

			FileOutputStream outputStream = new FileOutputStream(
					twitterDataFile, true);
			outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			bufferedWriter = new BufferedWriter(outputStreamWriter);

			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
					.setOAuthConsumerKey("c0Y29UO9vVz27ToAvSG4Voicu")
					.setOAuthConsumerSecret(
							"WiTlheeRNRVq9q09HzEnz1oQkyJpizCejip1Lltl3ko5LOUblW")
					.setOAuthAccessToken(
							"957131425-ognxFbbKq2fZhTtMrcKvwzt3H3uRJhDe5ub3nfzJ")
					.setOAuthAccessTokenSecret(
							"rce3HpIE6jn18NWO8OGxNnmfBxMx4kwX8JTDCeRomGMhr");

			Twitter unauthenticatedTwitter = new TwitterFactory(cb.build())
					.getInstance();
			// First param of Paging() is the page number, second is the number
			// per page (this is capped around 200 I think.
			Paging paging = new Paging(1, 100);

			List<Status> statuses = unauthenticatedTwitter.getUserTimeline(
					"rihanna", paging);
			for (Status status : statuses) {
				String json = gson.toJson(status);
				bufferedWriter.append(StringUtils.join(json, "\n"));
				bufferedWriter.append(",\n");
			}
			bufferedWriter.flush();
			bufferedWriter.close();

		} catch (IOException | TwitterException e) {
			LOGGER.log(Level.SEVERE, "Unable to find data file", e);
		}

	}
}
