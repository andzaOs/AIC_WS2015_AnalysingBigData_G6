package tuwien.ac.at.db.graph.model.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vanja Bisanovic
 * @author alisanli
 */
@NodeEntity
public class Topic {

	@GraphId
	private Long id;

	@Index(unique = true)
	private String name;

	@Relationship(type = "SUBTOPIC_OF", direction = Relationship.INCOMING)
	private Set<Topic> subtopics = new HashSet<Topic>();

	@Relationship(type = "SUBTOPIC_OF", direction = Relationship.OUTGOING)
	private Topic parent;

	@Relationship(type = "INTERESTED_IN_TOPIC", direction = Relationship.OUTGOING)
	private Set<User> users = new HashSet<User>();

	public Topic() {
	}

	public Topic(String name, Set<Topic> subtopics, Topic parent, Set<User> users) {
		this.name = name;
		this.subtopics = subtopics;
		this.parent = parent;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Topic> getSubtopics() {
		return subtopics;
	}

	public void setSubtopics(Set<Topic> subtopics) {
		this.subtopics = subtopics;
	}

	public Topic getParent() {
		return parent;
	}

	public void setParent(Topic parent) {
		this.parent = parent;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
