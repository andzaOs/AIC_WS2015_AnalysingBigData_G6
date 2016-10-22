package tuwien.ac.at.api;

import java.io.File;

import tuwien.ac.at.db.relational.model.dto.TwitterUserDTO;

/**
 * @author Simon
 *
 */
public interface ITweetDao {

	/**
	 * This method downloads all of the timeline tweets of the given user. This
	 * is achieved by calling the twitter4j method getUserTimeline(); !!! ATTENTION
	 * by default this method only downloads the 20 latest tweets
	 *
	 * @param user
	 *            != null and it has to be in the user.txt list
	 * @return is the number of tweets which are donlwaded
	 */
	public int downloadTweets(TwitterUserDTO user);
	
	
	/**
	 * This method returns the file where the raw data of the timeline tweets is stored;
	 * 
	 * @param user != null; has to be in the user.txt file
	 * @return the file of the raw twitter data is returned
	 */
	public File getTweets(TwitterUserDTO user);
}
