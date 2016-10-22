package tuwien.ac.at.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuwien.ac.at.db.document.model.entity.Advertisement;
import tuwien.ac.at.db.document.repo.api.AdvertisementRepository;
import tuwien.ac.at.mining.DataReader;
import tuwien.ac.at.mining.json.AdvertisementJSON;
import tuwien.ac.at.service.api.IDocumentDatabaseService;
import tuwien.ac.at.service.api.IGraphDatabaseService;

/**
 * 
 * @author Vanja Bisanovic
 * @author alisanli
 */
@Service
public class DocumentDatabaseServiceImpl implements IDocumentDatabaseService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DocumentDatabaseServiceImpl.class);

	@Autowired
	private IGraphDatabaseService graphDatabaseService;
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	@Autowired
	private Session session;

	@Transactional
	@PostConstruct
	public void setupDb() {
		LOGGER.info("importing the advertisements...");
		// TODO Import some advertisements into mongodb, use all the topics from Topic service
		// to generate some random advertisements that belong to each topic and save them with
		// advertisementRepository. Names of advertisements must be different, but each topic
		// should have several advertisements 
		if (!advertisementRepository.findAll().iterator().hasNext()) {
			importFromJsom("/Users/alisanli/Documents/MASTER/aic/topics.json");
	
		}
	  }




	public void importFromJsom(String path) {
		try {
			
			
			List<AdvertisementJSON> advs = DataReader.readAdvertisementJsonStream(path);
			
			advertisementRepository.deleteAll();
			long startId = advertisementRepository.count();
			for (AdvertisementJSON advjson : advs) {
				Advertisement advertisement = advjson.getMongoDBEntity();
				advertisement.setId(startId++);
				advertisementRepository.save(advertisement);
				LOGGER.info("MongoDB  saved :" + advertisement.getName());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	public IGraphDatabaseService getGraphDatabaseService() {
		return graphDatabaseService;
	}

	public void setGraphDatabaseService(
			IGraphDatabaseService graphDatabaseService) {
		this.graphDatabaseService = graphDatabaseService;
	}

	public AdvertisementRepository getAdvertisementRepository() {
		return advertisementRepository;
	}

	public void setAdvertisementRepository(
			AdvertisementRepository advertisementRepository) {
		this.advertisementRepository = advertisementRepository;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}




	

}
