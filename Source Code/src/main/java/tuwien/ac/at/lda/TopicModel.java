package tuwien.ac.at.lda;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.CharSequenceLowercase;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.TokenSequenceRemoveStopwords;
import cc.mallet.pipe.iterator.SimpleFileLineIterator;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.topics.TopicInferencer;
import cc.mallet.types.Alphabet;
import cc.mallet.types.IDSorter;
import cc.mallet.types.InstanceList;
import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;

/** LDA with Mallet. 
 *  @author wellecks **/
public class TopicModel 
{
	static final String[] ADDL_STOP_WORDS = {"http", "rt", "https", "bit", "ly"};
	static final String RES_DIR = Configuration.getInstance().getValue(
			Constants.SOURCE_ANALYSIS_RESULT);
	static final String SOURCE_DIR = "/Users/uni/TUWien/7.Semester/AIC/twitterData/tweets"; 
	static final int NUM_WORDS = 20;
	static final int NUM_ITERS = 2000;
	
	/** Create the data loading pipeline **/
	private static ArrayList<Pipe> makePipeList()
	{
		ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
		// Lowercase everything
		pipeList.add(new CharSequenceLowercase());
		// Unicode letters, underscore, and hashtag
		Pattern pat = Pattern.compile("[\\p{L}_#]+");
		pipeList.add(new CharSequence2TokenSequence(pat));
		// Remove stop words
		TokenSequenceRemoveStopwords tsrs = new TokenSequenceRemoveStopwords();
		tsrs.addStopWords(ADDL_STOP_WORDS);
		pipeList.add(tsrs);
		// Convert the token sequence to a feature sequence.
		pipeList.add(new TokenSequence2FeatureSequence());
		return pipeList;
	}
	
	/** Load a file, with one instance per line, 
	 *  and return as an InstanceList. **/
	public static InstanceList fileToInstanceList(String filename)
	{
		InstanceList instances = new InstanceList(new SerialPipes(makePipeList()));
		instances.addThruPipe(new SimpleFileLineIterator(filename));
		return instances;
	}
	
	/** numTopics = 20, alphaT = 1.0, betaW = 0.01 **/
	public static ParallelTopicModel trainModel(InstanceList instances, 
												int numTopics, int numIters,
												double alphaT, double betaW)
	{
		ParallelTopicModel model = new ParallelTopicModel(numTopics, alphaT, betaW);
		model.addInstances(instances);
		model.setNumThreads(2);
		model.setOptimizeInterval(20);
		model.setNumIterations(numIters);
		try
		{
			model.estimate();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	/** Given a trained model, infer the distribution for a single new instance.
	 *  (Only infers for the first element of the input instances). **/
	public static double[] inferTopicDistribution(ParallelTopicModel model,
			  InstanceList instances)
	{
		TopicInferencer inferencer = model.getInferencer();
		double[] distribution = inferencer.getSampledDistribution(instances.get(0), NUM_ITERS, 1, 5);
		for (int i=0; i< distribution.length; i++)
		{
			System.out.println(i + "\t" + distribution[i]);
		}
		return distribution;
	}
	
	public static String[][] getTopWords(ParallelTopicModel model, 
			 					   InstanceList instances,
			 					   int numTopics, int numWords)
	{
		String[][] topWords = new String[numTopics][];
		Alphabet dataAlphabet = instances.getDataAlphabet();
		// Get an array of sorted sets of word ID/count pairs
		ArrayList<TreeSet<IDSorter>> topicSortedWords = model.getSortedWords();
		// Show top words in topics with proportions
		for (int topic = 0; topic < numTopics; topic++) 
		{
			String[] words = new String[numWords];
			Iterator<IDSorter> iterator = topicSortedWords.get(topic).iterator();
			int rank = 0;
			while (iterator.hasNext() && rank < numWords) {
				IDSorter idCountPair = iterator.next();
				words[rank] = String.format("%s:%.0f", 
						dataAlphabet.lookupObject(idCountPair.getID()), 
						idCountPair.getWeight());
				rank++;
			}
		topWords[topic] = words;
		}
		return topWords;
	}

	
	public static void topWordsToFile(String outFile,
								   	  ParallelTopicModel model, 
									  InstanceList instances,
									  int numTopics, int numWords)
	{
		try
		{
			PrintWriter pw = new PrintWriter(outFile);
			String[][] topWords = TopicModel.getTopWords(model, instances, numTopics, numWords);
			for (int topic = 0; topic<topWords.length; topic++)
			{
				pw.println(String.format("Topic %d", topic));
				System.out.print(String.format("Topic %d \t", topic));
				for (String word: topWords[topic])
				{
					pw.println(String.format("%s", word));
					System.out.print(String.format("%s ", word));
				}
				pw.println();
				System.out.println();
			}
	        pw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void distToFile(String outFile, double[] dist)
	{
		try
		{
			PrintWriter pw = new PrintWriter(outFile);
	        for (int i = 0; i < dist.length; i++) {
	            pw.println(i + "\t" + dist[i]);
	        }
	        pw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String[][] topTopicsToFile(String outFile,
									   double[] inferredDistribution, 
									   String[][] topWords, 
									   int numTopics)
	{
		String[][] topTopics = new String[numTopics][];
		// get ranked topic indices
		Integer[] idx = new Integer[inferredDistribution.length];
		final double[] data = inferredDistribution;
		for (int i=0; i<idx.length; i++) { idx[i] = i; }
		Arrays.sort(idx, new Comparator<Integer>() {
		    @Override public int compare(Integer o1, Integer o2) {
		        return Double.compare(data[o1], data[o2]);
		    }
		});
		try
		{
			PrintWriter pw = new PrintWriter(outFile);
			for (int i = 0; i<numTopics; i++)
			{
				String[] words = new String[topWords[0].length];
				int topicNum = idx[idx.length - 1 - i];
				pw.println("Topic " + topicNum + ": " + inferredDistribution[topicNum]);
				int j = 0;
				for (String word: topWords[topicNum])
				{
					pw.println(String.format("%s", word));
					words[j] = word;
					j++;
				}
				pw.println();
				topTopics[i] = words;
			}
	        pw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return topTopics;
	}
	
	/** Assumes folder structure generated by TwitterClient **/
	public static void analyzeTwitterUser(File [] files, String screenname, int numTopics)
	{
		if(files[0] != null && files[1] != null){
		String multiLineFile =  files[1].getPath();
		String singleLineFile = files[0].getPath();
		String topWordsFile = 
				String.format("%s/%s_top_words.txt", RES_DIR, screenname);
		String distFile = 
				String.format("%s/%s_composition.txt", RES_DIR, screenname);
		String top5File = 
				String.format("%s/%s_ranked.txt", RES_DIR, screenname);
		String jsonFile = 
				String.format("%s/%s.json", RES_DIR, screenname);
		
		InstanceList instances = TopicModel.fileToInstanceList(multiLineFile);
		ParallelTopicModel model = TopicModel.trainModel(
				instances, numTopics, NUM_ITERS, 1.0, 0.01);
		
		topWordsToFile(topWordsFile, model, instances, numTopics, NUM_WORDS);
		
		System.out.println("Inferring overall topic distribution...");
		double[] dist = inferTopicDistribution(model, 
				TopicModel.fileToInstanceList(singleLineFile));
		distToFile(distFile, dist);
		String[][] topTopics = topTopicsToFile(
				top5File, 
				dist, 
				getTopWords(model, instances, numTopics, NUM_WORDS), 
				10);
		toJSON(jsonFile, topTopics);
		}else{
			throw new RuntimeException("Files not found");
		}
	}
	
	public static List<String> getTopWords(String screenname) throws IOException {

		List<String> result = new ArrayList<String>();

//		try {
			JsonReader reader = new JsonReader(new FileReader(String.format("%s/%s.json", RES_DIR, screenname)));
			reader.beginObject();

			while (reader.hasNext()) {

				String name = reader.nextName();

				if (name.equals("words")) {

					reader.beginArray();

					while (reader.hasNext()) {
						result.add(reader.nextString());
					}

					reader.endArray();
				} else {

					reader.skipValue();
				}
			}

			reader.endObject();
			reader.close();
			
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}

		return result;
	}
	
	public static void toJSON(String outFile, String[][] topWords) {

		JsonWriter writer = null;

		try {

			writer = new JsonWriter(new FileWriter(outFile));

			writer.beginObject();
			writer.name("words");
			
			writer.beginArray();

			for (int i = 0; i < topWords.length; i++) {

				String[] words = topWords[i];

				for (String word : words) {

					if (word != null) {
						writer.value(word.split(":")[0]);
					}
				}
			}

			writer.endArray();
			writer.endObject();
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}