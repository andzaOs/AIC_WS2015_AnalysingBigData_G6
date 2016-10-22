package tuwien.ac.at.mining;

import java.io.File;
import java.io.IOException;

import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;

/**
 * @author Vanja Bisanovic
 *
 */
public class MinerRunner {

	private static TweetMiner tweetMiner;
	private static UserMiner userMiner;

	private static Thread thread;

	public static void runGlobalMiner() {
		if (tweetMiner == null) {
			tweetMiner = new TweetMiner();
			thread = tweetMiner.thread;
		}
		if (!thread.isAlive()) {
			thread.start();
		}
	}

	public static void stopGlobalMiner() {
		if (tweetMiner != null && thread.isAlive()) {
			tweetMiner.shouldRun = false;
		}
	}
	
	
	public static void runUserMonitor(long [] twitterIds, String screenname) {
		if (userMiner == null) {
			userMiner = new UserMiner(twitterIds, screenname);
			thread = userMiner.getThread();
		}
		if (!thread.isAlive()) {
			thread.start();
		}
	}

	public static void stopUserMonitor() {
		if (userMiner != null && thread.isAlive()) {
			userMiner.stopRun();
		}
	}
	
	public static File getFile(){
		
		String directoryPath = Configuration.getInstance().getValue(
				Constants.SOURCE_RAW_DATA);
		return new File(directoryPath+"all"+".txt");
	}

	//	public static void main(String[] args) throws IOException, InterruptedException {
	//		runGlobalMiner();
	//		Thread.sleep(5000);
	//		System.out.println("Hit [ENTER] to stop");
	//		System.in.read();
	//		tweetMiner.shouldRun = false;
	//	}
	public static void startDownload(long [] twitterIds, String screenname) throws IOException, InterruptedException{
		runUserMonitor(twitterIds, screenname);
		Thread.sleep(5000);
	}
	
	public static void stopDownload(){
		if(userMiner != null){
			userMiner.stopRun();
		}
	}
}
