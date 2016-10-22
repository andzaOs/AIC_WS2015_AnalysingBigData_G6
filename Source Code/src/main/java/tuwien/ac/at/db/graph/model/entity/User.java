package tuwien.ac.at.db.graph.model.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * 
 * @author alisanli
 * @author Vanja Bisanovic
 *
 */
@NodeEntity
public class User {

	@GraphId
	private Long id;

	@Index(unique = true)
	private long userId;
    
	@Index(unique = true)
	private String screenname;

	@Index(unique = true)
	private String name;

	
	
	@Relationship(type = "FOLLOW", direction = Relationship.UNDIRECTED)
	private Set<User> friends = new HashSet<User>();

	@Relationship(type = "INTERESTED_IN", direction = Relationship.INCOMING)
	private Set<Topic> topics = new HashSet<Topic>();

	public User() {
	}

	public User(long userId, String screenname) {
		this.userId = userId;
		this.screenname = screenname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getScreenname() {
		return screenname;
	}

	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public void addFriend(User friend) {
		this.friends.add(friend);
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public void addTopic(Topic topic) {
		this.topics.add(topic);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
