package tuwien.ac.at.mining;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;
import tuwien.ac.at.mining.json.TopicJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

/**
 * @author Vanja Bisanovic
 *
 */
public class TopicReader {

	private static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws IOException {

		List<TopicJSON> list = getTopics();
		for (TopicJSON t : list) {
			System.out.println(t.toString());
		}

	}

	public static List<TopicJSON> getTopics() throws JsonIOException,
			JsonSyntaxException, IOException {

		String filePath = Configuration.getInstance().getValue(
				Constants.CONFIGURATION_KEY_FILE_TOPIC_DATA);
		//File file = new File("./resources/topics.json");
		File file = new File(filePath);
		JsonReader reader = new JsonReader(new InputStreamReader(
				new FileInputStream(file), "UTF-8"));
		reader.setLenient(true);
		List<TopicJSON> topics = new ArrayList<TopicJSON>();
		reader.beginArray();
		while (reader.hasNext()) {
			TopicJSON topic = gson.fromJson(reader, TopicJSON.class);
			topics.add(topic);
		}
		reader.endArray();
		reader.close();
		return topics;

	}

}
