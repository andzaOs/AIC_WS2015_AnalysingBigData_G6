package tuwien.ac.at.api;

import java.io.File;
import java.util.List;

import tuwien.ac.at.db.relational.model.dto.TwitterUserDTO;

/**
 * @author Simon
 *
 */
public interface IUserDao {
	
	/**
	 * this method doesn't has to be implement it can also be done manually A
	 * user.txt file has to be created is has to contain the top 100 or more
	 * twitters of a smaller english speaking country to increase the possibility
	 * that there are relations. between the twitterers. in the next step the
	 * followers of the users are added to the file. It has to be guaranteed
	 * that the total amount of the tweets of the users in the list has to be at
	 * least 3GB.
	 *
	 * @param amount
	 *            defines the amount of the top amount - twitteres which should
	 *            be downloaded
	 * @return The file where users are saved
	 */
	public File createUserList(int amount);
	
	/**
	 * This looks up the followers of the given user
	 * As side effect the new Users are store in the user.txt file
	 * 
	 * @param user
	 *            must be element of the core users. the core users are the
	 *            users which where added to the user.txt by choosing the
	 *            top-amount twitterers of a country
	 * @return returns the number of followers
	 */
	public int downloadFollowers(TwitterUserDTO user);
	
	/**
	 * This method returns the followers of the user WITHOUT string it into the file
	 * 
	 * @param user != null
	 * @return List of TwitterUsers which are following the given user
	 */
	public List<TwitterUserDTO> getFollowers(TwitterUserDTO user);
	
	
	/**
	 * This method returns always the next user in the user.txt file
	 * The cursor has to be persisted
	 * @return next TwitterUser; if null is returned there are no more users
	 */
	public TwitterUserDTO getNext();
	
}
