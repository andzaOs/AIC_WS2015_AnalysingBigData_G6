package tuwien.ac.at.db.relational.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import twitter4j.IDs;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

@Entity
@Table(name = "twitteruser")

public class TwitterUser extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "screenname", nullable = false, unique = true)
	private String screenname;
	@Column(name = "followersNumber", nullable = false)
	private int followersNumber;
	@Column(name = "followeesNumber", nullable = false)
	private int followeesNumber;
	@Column(name = "tweets", nullable = false)
	private int tweets;
	@Column(name = "location", nullable = true)
	private String location;
	@Column(name = "description", nullable = true)
	private String description;
	@Column(name = "twitterID", nullable = false, unique = true)
	private long twitterID;
	
	public TwitterUser(){}
	public TwitterUser(final String screenname, final int followers,
			final int followees, final int amountOfTweets, final String location, final String name, final long twitterID){
		this.screenname = screenname;
		this.tweets = amountOfTweets;
		this.followersNumber = followers;
		this.followeesNumber = followees;
		this.location = location;
		this.description = name;
		this.twitterID = twitterID;
	}
	public String getScreenname() {
		return screenname;
	}
	public void setScreenname(final String screenname) {
		this.screenname = screenname;
	}
	public int getFollowersNumber() {
		return followersNumber;
	}
	public void setFollowersNumber(final int followersNumber) {
		this.followersNumber = followersNumber;
	}
	public int getFolloweesNumber() {
		return followeesNumber;
	}
	public void setFolloweesNumber(final int followeesNumber) {
		this.followeesNumber = followeesNumber;
	}
	public int getTweets() {
		return tweets;
	}
	public void setTweets(final int tweets) {
		this.tweets = tweets;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(final String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public long getTwitterID() {
		return twitterID;
	}
	public void setTwitterID(final long twitterID) {
		this.twitterID = twitterID;
	}
	
	public List<TwitterUser> getAllFollowers()
	{
		  List<TwitterUser> followers = new ArrayList<TwitterUser>();
	      long cursor = -1;
	      IDs ids;
	      TwitterFactory factory = new TwitterFactory();
		  Twitter twitter = factory.getInstance();
	      try
	      {

			  ids = twitter.getFollowersIDs(getScreenname(), cursor);
			  int counter = 0;
	    	  do 
	    	  {
	              for (long id : ids.getIDs()) 
	              {	
	            	  	  if(counter>10) return followers;
	            	  	  RateLimitStatus rls = twitter.getHomeTimeline().getRateLimitStatus();
	            	  	  if(rls.getRemaining()==0) 
	            	  		  {
	            	  		  	Thread.sleep(900 * 1000l);
	            	  		  }
	            	  		  User user = twitter.showUser(id);
		            		  TwitterUser t = new TwitterUser(user.getScreenName(), user.getFollowersCount(), user.getFriendsCount(),user.getStatusesCount(), user.getLocation(), user.getName(), user.getId());
		            		  followers.add(t);
		            		  counter++;
	              }
	    	  }while ((cursor = ids.getNextCursor()) != 0);
		      }
	      catch(TwitterException e)
	      {
	    	  System.out.println("Error: "+e.getErrorMessage());
	      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return followers;
	}
	
}
