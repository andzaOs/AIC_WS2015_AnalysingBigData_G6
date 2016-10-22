package tuwien.ac.at.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tuwien.ac.at.mining.json.TweetJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

/**
 * @author Vanja Bisanovic
 * 
 * Pass a path to the file stream as argument. 
 * Example: in eclipse, open "run configurations..." and find our RawStreamRepairman. 
 * Pass "C:\pathToFile.txt" as program argument (with quotes), 
 * and hit run...
 */
public class RawStreamRepairman {

	private static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) {

		if (args == null || args.length != 1) {
			System.out
					.println("This batch expects 1 Argument: <path-To-Original-File>");
			return;
		}

		String pathToOriginalFile = args[0];
		File originalFile = new File(pathToOriginalFile);
		findCorrectPartOfJsonStream(originalFile);
	}

	public static void findCorrectPartOfJsonStream(File originalFile) {
		List<TweetJSON> tweets = new ArrayList<TweetJSON>();
		try {
			JsonReader reader = new JsonReader(new InputStreamReader(
					new FileInputStream(originalFile), "UTF-8"));

			reader.setLenient(true);

			reader.beginArray();
			while (reader.hasNext()) {
				TweetJSON tweet = gson.fromJson(reader, TweetJSON.class);
				tweets.add(tweet);
			}
			reader.endArray();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (!tweets.isEmpty()) {
				System.out
						.println("Your corrupted tweet is probably the next tweet after tweet with ID: "
								+ tweets.get(tweets.size() - 1).getId());
			}
		}
	}

}
