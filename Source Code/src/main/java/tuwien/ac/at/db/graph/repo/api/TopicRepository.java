package tuwien.ac.at.db.graph.repo.api;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import tuwien.ac.at.db.graph.model.entity.Topic;

/**
 * 
 * @author alisanli
 *
 */
public interface TopicRepository extends GraphRepository<Topic> {
	List<Topic> findByName(String name);
}
