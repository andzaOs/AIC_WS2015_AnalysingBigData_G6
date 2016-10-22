package tuwien.ac.at.db.document.model.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vanja Bisanovic
 *
 */
@Document
public class Advertisement implements Serializable {

	private static final long serialVersionUID = 4205175785839715412L;

	private long id;
	private String name;
	private String description;
	
	@TextIndexed
	private String topic;

	public Advertisement() {
	}

	public Advertisement(String name, String description, String topic) {
		this.name = name;
		this.description = description;
		this.topic = topic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
