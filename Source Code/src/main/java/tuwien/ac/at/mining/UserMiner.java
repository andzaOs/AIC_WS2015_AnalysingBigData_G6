package tuwien.ac.at.mining;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Vanja Bisanovic
 *
 */
public class UserMiner implements Runnable {

	private static final Logger LOGGER = Logger.getLogger("TweetMiner");

	// Use this to save jsons with nice formatting
	// private static Gson gson = new
	// GsonBuilder().setPrettyPrinting().create();
	private static Gson gson = new GsonBuilder().create();

	FileOutputStream outputStream;
	OutputStreamWriter outputStreamWriter;
	BufferedWriter bufferedWriter;

	private boolean shouldRun;
	private Thread thread;
	private long[] followArray;
	private String screenname;

	public UserMiner(long [] followArray, String screenname) {
		
		shouldRun = true;
		thread = new Thread(this);
		this.followArray = followArray;
		this.screenname = screenname;
		try {
			//TODO check the paths
			
			String directoryPath = Configuration.getInstance().getValue(
					Constants.SOURCE_RAW_DATA);
			
			
			File file = new File(directoryPath);
			
			if (!file.exists()) {
				file.mkdirs();
			}

			//int numberOfFiles = file.list().length;

			String filePath = StringUtils.join(directoryPath, screenname,
					Constants.FILE_SUFFIX);

			File twitterDataFile = new File(filePath);

			FileOutputStream outputStream = new FileOutputStream(
					twitterDataFile, true);
			outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.append("[\n");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Unable to find data file", e);
		}
	}

	@Override
	public void run() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("c0Y29UO9vVz27ToAvSG4Voicu")
				.setOAuthConsumerSecret(
						"WiTlheeRNRVq9q09HzEnz1oQkyJpizCejip1Lltl3ko5LOUblW")
				.setOAuthAccessToken(
						"957131425-ognxFbbKq2fZhTtMrcKvwzt3H3uRJhDe5ub3nfzJ")
				.setOAuthAccessTokenSecret(
						"rce3HpIE6jn18NWO8OGxNnmfBxMx4kwX8JTDCeRomGMhr");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
				.getInstance();

		twitterStream.addListener(listener);
		String[] trackArray = new String[1];

		// filter() method internally creates a thread which manipulates
		// TwitterStream
		// and calls these adequate listener methods continuously.
		/**
		 * TODO chnage filter and follow array should only use one id, but check if this 
		 * this has bad effect on  twitter limits  
		 */
		twitterStream.filter(new FilterQuery(followArray));
		
	}

	private StatusListener listener = new StatusListener() {

		private int count = 0;

		@Override
		public void onStatus(Status status) {
			String json = gson.toJson(status);
			try {

				if (shouldRun) {

					/*
					 * LOGGER.info(StringUtils.join(count, ": ",
					 * "Writing a tweet with id: ", status.getId(), ", Tweet: ",
					 * "@", status.getUser().getScreenName(), " - ",
					 * status.getText()));
					 */

					bufferedWriter.append(StringUtils.join(json, "\n"));
					bufferedWriter.append(",\n");
				} else {
					bufferedWriter.flush();
					bufferedWriter.close();
					System.exit(0);
				}

			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Unable to write to the file", e);
			}
			setCount(getCount() + 1);
		}

		@Override
		public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		
			  LOGGER.info(StringUtils.join("Got a status deletion notice id:",
			  statusDeletionNotice.getStatusId()));
			 
		}

		@Override
		public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			
			  LOGGER.info(StringUtils.join("Got track limitation notice: ",
			  numberOfLimitedStatuses));
			 
		}

		@Override
		public void onScrubGeo(long userId, long upToStatusId) {
			
			 LOGGER.info(StringUtils.join("Got scrub_geo event userId: ",
			  userId, " upToStatusId:", upToStatusId));
			 
		}

		@Override
		public void onStallWarning(StallWarning warning) {
			try {
				bufferedWriter.flush();
				bufferedWriter.close();
				System.exit(0);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Unable to write to the file", e);
			}

		}

		@Override
		public void onException(Exception ex) {
			ex.printStackTrace();
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	};

	public Thread getThread() {
		return thread;
	}
	
	public void stopRun() {
		this.shouldRun = false;
	}
	
}
