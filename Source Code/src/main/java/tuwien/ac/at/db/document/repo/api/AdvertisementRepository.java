package tuwien.ac.at.db.document.repo.api;

import java.util.List;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuwien.ac.at.db.document.model.entity.Advertisement;

/**
 * @author Vanja Bisanovic
 *
 */
@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {

	
	List<Advertisement> findByTopicLike(String topic);
	
	List<Advertisement> findAllBy(TextCriteria criteria);
	
	 
}
