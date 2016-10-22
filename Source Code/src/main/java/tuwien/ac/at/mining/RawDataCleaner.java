package tuwien.ac.at.mining;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tuwien.ac.at.DAO;
import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;
import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.mining.json.TweetJSON;
import tuwien.ac.at.mining.json.UserJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class RawDataCleaner {

	private final static String DIR = Configuration.getInstance().getValue(Constants.SOURCE_TWEET_TEXT);

	private static final long MEMORY_SIZE = 5000;

	private static Gson gson = new GsonBuilder().create();
	private static List<Long> twitterIds = null;

	/**
	 * This method returns the single and multiline file of given screenname
	 * 
	 * @param screenname
	 *            screenname of twitterUser
	 * @return array of length 2; at index 0 there is the single line file at
	 *         index 1 there is the multi line file
	 */
	public static File[] getFile(String screenname) {

		File[] files = new File[2];
		files[0] = new File(DIR + "/" + screenname + "_tweets_single.txt");
		files[1] = new File(DIR + "/" + screenname + "_tweets_multiline.txt");

		if (!files[0].exists()) {
			files[0] = null;
		}

		if (!files[1].exists()) {
			files[1] = null;
		}

		return files;
	}

	public static void createOnlyText(File source) throws IOException {

		try {
			List<TwitterUser> entities = new DAO().getAll();
			twitterIds = new ArrayList<Long>();

			for (TwitterUser entity : entities) {
				twitterIds.add(entity.getTwitterID());
			}

			findCorrectPartOfJsonStream(source);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	
	
	      
	private static void findCorrectPartOfJsonStreamMemory(File originalFile) {

		String screenname = null;

		Map<String, List<String>> memoryData = new HashMap<>();
		long i = 0;
		try {

			JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(originalFile), "UTF-8"));

			reader.setLenient(true);

			reader.beginArray();
			while (reader.hasNext()) {
				TweetJSON tweet = gson.fromJson(reader, TweetJSON.class);
				screenname = checkTwitterId(tweet);
				if (screenname != null) {
					// System.out.println(i);
					i++;

					if (storeTweetsMemory(tweet.getText(), screenname, memoryData, i, false)) {
						i = 0;
						memoryData = new HashMap<>();
					}
				}
			}

			reader.endArray();
			reader.close();
		} catch (EOFException e) {

			System.err.println("BITTI");

			storeTweetsMemory("", "", memoryData, i++, true);

			System.out.println("Finished reading Json File");
		} catch (IOException e) {
			System.err.println("BITTI");

			storeTweetsMemory("", "", memoryData, i++, true);
			e.printStackTrace();
		}
	}

	private static boolean storeTweetsMemory(String text, String screenname, Map<String, List<String>> memoryData,
			long i, boolean isLast) {
		boolean write = false;
		if (!isLast) {
			List<String> storedList = memoryData.get(screenname);

			if (storedList == null) {
				System.err.println("New " + screenname);
				storedList = new ArrayList<>();
			}
			storedList.add(text);
			memoryData.put(screenname, storedList);
		}

		if (i == MEMORY_SIZE || isLast) {
			write = true;

			System.out.println("memory to file............ ");
			for (Map.Entry<String, List<String>> entry : memoryData.entrySet()) {

				String sn = entry.getKey();
				List<String> listData = entry.getValue();
				System.out.println("file : " + sn);
				String slText = createTextSL(listData);
				String mlText = createTextML(listData);

				PrintWriter outMulti = null;
				PrintWriter outSingle = null;
				File file = null;
				File file2 = null;

				file = new File(DIR + sn + "_tweets_multiline.txt");
				file2 = new File(DIR + sn + "_tweets_single.txt");

				try {
					if (file.exists()) {
						outMulti = new PrintWriter(new FileOutputStream(file, true));
						outSingle = new PrintWriter(new FileOutputStream(file2, true));
					} else {
						outMulti = new PrintWriter(file);
						outSingle = new PrintWriter(file2);
					}

					if (outMulti != null) {

						outMulti.println(mlText);
					}
					if (outSingle != null) {

						outSingle.print(slText);
					}

				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

				if (outMulti != null) {

					outMulti.close();
				}
				if (outSingle != null) {
					outSingle.close();
				}

			}
		}
		return write;

	}

	private static String createTextSL(List<String> listData) {
		String t = "";
		for (String text : listData) {
			t = t + text;
		}
		return t;
	}

	private static String createTextML(List<String> listData) {
		String t = "";
		t = listData.get(0);
		for (int i = 1; i < listData.size(); i++) {
			t = t + System.lineSeparator() + listData.get(i);
		}
		return t;
	}
    static long i;
	private static void storeTweets(String tweetsText, String screenname) {

		PrintWriter outMulti = null;
		PrintWriter outSingle = null;
		File file = null;
		File file2 = null;

		file = new File(DIR + screenname + "_tweets_multiline.txt");
		file2 = new File(DIR + screenname + "_tweets_single.txt");

		try {
			if (file.exists()) {
				outMulti = new PrintWriter(new FileOutputStream(file, true));
				outSingle = new PrintWriter(new FileOutputStream(file2, true));
			} else {
				outMulti = new PrintWriter(file);
				outSingle = new PrintWriter(file2);
			}
			
			outMulti.println(tweetsText);
			outSingle.print(tweetsText);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		outMulti.close();
		outSingle.close();
	}

	private static void findCorrectPartOfJsonStream(File originalFile) {

		String screenname = null;

		try {
			
			
			
			JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(originalFile), "UTF-8"));

			reader.setLenient(true);

			reader.beginArray();
			while (reader.hasNext()) {
				TweetJSON tweet = gson.fromJson(reader, TweetJSON.class);
				prosentUpdate(i++);
				screenname = checkTwitterId(tweet);
				if (screenname != null) {
					storeTweets(tweet.getText(), screenname);
				}
			}
			reader.endArray();
			reader.close();
		} catch (EOFException e) {
			System.out.println("Finished reading Json File");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	
	static long lastProsent=0;
	private static void prosentUpdate(long i) {
		Double prosent=new Double(i*1000/1579320);
		String status="";

		
		if (prosent!=lastProsent) {
			lastProsent=prosent.intValue();
			System.err.println(""+prosent.intValue()+"/1000");
		}
		
	}

	private static String checkTwitterId(TweetJSON tweet) {

		TweetJSON currentTweet = tweet;
		String result = null;
		UserJSON user = null;

		do {
			user = currentTweet.getUser();
			if (twitterIds.contains(user.getId())) {
				result = user.getScreenName();
			} else {

				currentTweet = currentTweet.getQuotedStatus();
			}
		} while (currentTweet != null && result == null);

		currentTweet = tweet;

		if (result == null) {
			do {
				user = currentTweet.getUser();

				if (twitterIds.contains(user.getId())) {
					result = user.getScreenName();
				} else {

					currentTweet = currentTweet.getRetweetedStatus();
				}

			} while (currentTweet != null && result == null);
		}
		return result;
	}

}
