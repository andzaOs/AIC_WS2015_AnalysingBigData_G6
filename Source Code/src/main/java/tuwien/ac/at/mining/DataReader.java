package tuwien.ac.at.mining;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import tuwien.ac.at.DAO;
import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;
import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.mining.json.AdvertisementJSON;
import tuwien.ac.at.mining.json.TweetJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

/**
 * @author Vanja Bisanovic
 *
 */
public class DataReader {

	private static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws Exception {

		//List<AdvertisementJSON> tweets = readAdvertisementJsonStream("/Users/alisanli/Documents/MASTER/aic/topics.json");

		//System.out.println(tweets.size());
		
		DAO dao=new DAO();
		TwitterUser tue= dao.getAll().get(0);
		System.out.println(":"+tue.getAllFollowers().size());
		

	}

	public static List<TweetJSON> readJsonStream() throws IOException {

		File twitterDataFile = getTwitterDataFile();
		
		JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream(twitterDataFile), "UTF-8"));
		reader.setLenient(true);
		List<TweetJSON> tweets = new ArrayList<TweetJSON>();
		reader.beginArray();
		while (reader.hasNext()) {
			TweetJSON tweet = gson.fromJson(reader, TweetJSON.class);
			tweets.add(tweet);
		}
		reader.endArray();
		reader.close();
		return tweets;
	}
	
	
	public static List<AdvertisementJSON> readAdvertisementJsonStream(String dataFile) throws IOException {
		
		JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(dataFile), "UTF-8"));
		reader.setLenient(true);
		List<AdvertisementJSON> advs = new ArrayList<AdvertisementJSON>();
		reader.beginArray();
		while (reader.hasNext()) {
			AdvertisementJSON adv = gson.fromJson(reader, AdvertisementJSON.class);
			advs.add(adv);
		}
		reader.endArray();
		reader.close();
		return advs;
	}
	

	public static int countTweets() throws IOException {

		File twitterDataFile = getTwitterDataFile();
		

		JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream(twitterDataFile), "UTF-8"));
		reader.setLenient(true);
		reader.beginArray();
		int i = 0;
		while (reader.hasNext()) {
			gson.fromJson(reader, TweetJSON.class);
			i++;
		}
		reader.endArray();
		reader.close();
		return i;
	}

	private static File getTwitterDataFile() {
		String directoryPath = Configuration.getInstance().getValue(
				Constants.CONFIGURATION_KEY_FILE_TWITTER_DATA);
		File file = new File(directoryPath);
		
		int numberOfFiles = file.list().length;

		String filePath = StringUtils.join(directoryPath,
				Constants.FILE_TWITTER_DATA_PREFIX, " " + (numberOfFiles),
				Constants.FILE_SUFFIX);

		File twitterDataFile = new File(filePath);
		return twitterDataFile;
	}

	
	
	
	
	
	
}
