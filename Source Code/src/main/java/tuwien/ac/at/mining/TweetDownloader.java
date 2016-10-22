package tuwien.ac.at.mining;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import tuwien.ac.at.api.ITweetDao;
import tuwien.ac.at.db.relational.model.dto.TwitterUserDTO;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetDownloader implements ITweetDao {

	private Twitter twitter;
	private final String DIR = "/Users/uni/TUWien//7.Semester/AIC/twitterData/analysis";
	private int pageNum;
	private int tweetCount;
	private File file;
	private File file2;

	public TweetDownloader() {

		TwitterFactory twitterFactory = new TwitterFactory();
		twitter = twitterFactory.getInstance();
		pageNum = 1;
		tweetCount = 0;
		file = null;
	}

	private void storeTweets(List<String> tweets, String username){
		PrintWriter out = null;
		PrintWriter single = null;
		
		if(file == null){
			file = new File(DIR+"/"+username+"_tweets.txt");
			file2 = new File(DIR+"/"+username+"_tweets_single.txt");
			try {
				out = new PrintWriter(file);
				single = new PrintWriter(file2);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}else {
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				single = new PrintWriter(new BufferedWriter(new FileWriter(file2, true)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(String tweet : tweets){
			out.println(tweet);
			single.print(tweet+" ");
		}
		
		out.close();
		single.close();
	}

	@Override
	public int downloadTweets(TwitterUserDTO user) {

		int size = -1;
		ResponseList<Status> statuses = null;
		List<String> tweets = new ArrayList<String>();

		while (size < tweetCount) {

			size = tweetCount;
	
			Paging page = new Paging(pageNum, 1000);
			pageNum++;
			try {
				statuses = twitter.getUserTimeline("@" + user.getScreenname(),
						page);

				for (Status status : statuses) {
					tweets.add(status.getText());
					tweetCount++;
				}

				storeTweets(tweets, user.getScreenname());
				tweets.clear();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}

		return tweetCount;
	}

	@Override
	public File getTweets(TwitterUserDTO user) {

		return new File(DIR + "/" + user.getScreenname() + "_tweets.txt");
	}

}
