package tuwien.ac.at.api;

import java.util.List;

import tuwien.ac.at.db.relational.model.dto.TwitterUserDTO;

/**
 * @author Simon
 *
 */
public interface IAnalysis {
	
	
	/**
	 * This method creates a LDA analysis of the user's tweets
	 * the top words are stored in a txt file
	 * @param user the variable file must be set
	 */
	public void createAnalysis(TwitterUserDTO user);
	
	/**
	 * This method returns the topwords of the topic which is parameterized
	 * the lower the topic number the higher is the importance of the topic for the user
	 * @param topic >= 0; the bigger the number the smaller is the importance of the topic
	 * @return List of all the word which are important for the topic
	 */
	public List<String> getTopWords(TwitterUserDTO user, int topic);

}
