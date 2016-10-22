package tuwien.ac.at.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuwien.ac.at.DAO;
import tuwien.ac.at.db.document.repo.api.AdvertisementRepository;
import tuwien.ac.at.db.graph.model.entity.Topic;
import tuwien.ac.at.db.graph.model.entity.User;
import tuwien.ac.at.db.graph.repo.api.TopicRepository;
import tuwien.ac.at.db.graph.repo.api.UserRepository;
import tuwien.ac.at.db.relational.model.entity.Following;
import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.db.relational.repo.api.ITwitterUserRepository;
import tuwien.ac.at.lda.TopicModel;
import tuwien.ac.at.service.api.IGraphDatabaseService;

/**
 * 
 * @author alisanli
 * @author Vanja Bisanovic
 *
 */
@Service
public class GraphDatabaseServiceImpl implements IGraphDatabaseService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GraphDatabaseServiceImpl.class);

	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private UserRepository twitterUserRepository;
	@Autowired
	private Session session;
	
	private DAO dao;

	@Autowired
	private ITwitterUserRepository twitterUserEntityRepository;

	@Autowired
	private AdvertisementRepository advertisementRepository;

	@Transactional
	@PostConstruct
	public void setupDb() {
		LOGGER.info("importing the topic and user data...");
		// TODO: Import the Topic nodes into graph db. Use the
		// TopicReader to load a list of objects that can be converted into simple 
		// Topic.java nodes. Implement, autowire and use UserTwitterService (same
		// as UserService.java) to store or fetch the user information into
		// relational db. The Dao and Entity parts for TwitterUser are also
		// to be implemented. While generating graph db, generate data for
		// relational db with TwitterUser entities.
	}

	
	
	
	
	
	public TopicRepository getTopicRepository() {
		return topicRepository;
	}

	public void setTopicRepository(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	public UserRepository getTwitterUserRepository() {
		return twitterUserRepository;
	}

	public void setTwitterUserRepository(
			UserRepository twitterUserRepository) {
		this.twitterUserRepository = twitterUserRepository;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
	
  public void importFromRdbs() throws IOException {
		
//		twitterUserRepository.deleteAll();
//		List<TwitterUser> alluser = saveAllTwitterUserInGraphDb();

//		advertisementNodeRepository.deleteAll();
//		saveAllAdvertisementInGraphDb();

//		topicRepository.deleteAll();
		
		saveAllTopic("giannamarie45");
	}






	private void saveAllTopic(String startUserName) {
		
		Iterable<User> allSavedUser = twitterUserRepository.findAll();
		boolean start= false;
		for (User twitterUser : allSavedUser) {
			
			if (start) {
            	addTopics(twitterUser);
			}
			
			
			if (twitterUser.getScreenname().equals(startUserName)) {
				start = true;
			}
			
			

		}
		
	}
	
	
	

	private void saveAllTopic() {

		Iterable<User> allSavedUser = twitterUserRepository.findAll();
		for (User twitterUser : allSavedUser) {

			addTopics(twitterUser);

		}
	}

	private List<User> saveAllTwitterUserInGraphDb() throws IOException {

		dao = new DAO();
		// dao.openConnection();

		Iterable<TwitterUser> alluser = twitterUserEntityRepository.findAll();

		List<User> allGraphTwitterUser = new ArrayList<>();

		for (TwitterUser twitterUserEntity : alluser) {

			User current = userConvet2Graphdb(twitterUserEntity);

			allGraphTwitterUser.add(current);

		}

		// dao.closeConnection();
		twitterUserRepository.save(allGraphTwitterUser);
		LOGGER.info(allGraphTwitterUser.size() + " twitterusers saved");

		return null;
	}

	public void doTopicImportStart(TwitterUser twitterUserEntity) {

		try {

			User current = userConvet2Graphdb(twitterUserEntity);

			twitterUserRepository.save(current);

			LOGGER.info( "Neo4j User Save: " + current.getScreenname());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private User userConvet2Graphdb(TwitterUser twitterUserEntity) throws IOException {

		User twitterUser = createTwitterUser(twitterUserEntity);

		List<Following> followings = new ArrayList<Following>();

		try {

			followings = dao.getFollowingByFollowee(Long.valueOf(twitterUserEntity.getId()));

		} catch (Exception e) {

			e.printStackTrace();
		}

		for (Following follower : followings) {
			TwitterUser fr = follower.getFollower();
			// TwitterUser followerNode=findTwitterUser(fr);
			// if (followerNode==null) {
			// followerNode=createTwitterUser(fr);
			// }

			User followerNode = createTwitterUser(fr);
			twitterUser.addFriend(followerNode);
		}
		return twitterUser;
	}

	private void addTopics(User twitterUser) {
		List<String> topics = null;
		try {

			topics = TopicModel.getTopWords(twitterUser.getScreenname());

		} catch (IOException e) {
			LOGGER.warn("Json file not found! TwitterUser.screenname : " + twitterUser.getScreenname());
		}

		if (topics != null) {

			for (String topicName : topics) {

				Topic topic = findOrCreateTopic(topicName);

				topic = topicRepository.save(topic);

				twitterUser.addTopic(topic);
			}

			twitterUserRepository.save(twitterUser);

			LOGGER.info(
					twitterUser.getTopics().size() + " topics saved for : " + twitterUser.getScreenname());

		}

		// follower save

	}

	private void addTopicsNoFollower(User twitterUser) throws IOException {

		List<String> topics = TopicModel.getTopWords(twitterUser.getScreenname());

		if (topics != null) {

			for (String topicName : topics) {

				Topic topic = findOrCreateTopic(topicName);

				topic = topicRepository.save(topic);

				twitterUser.addTopic(topic);
			}
		}

		twitterUserRepository.save(twitterUser);

	}

	private Topic findOrCreateTopic(String topicName) {

		Topic topic;

		List<Topic> storedTopicList = topicRepository.findByName(topicName);

		if (!storedTopicList.isEmpty()) {
			topic = storedTopicList.get(0);
		} else {
			topic = new Topic();
			topic.setName(topicName);

		}

		return topic;
	}

	private User createTwitterUser(TwitterUser twitterUserEntity) {
		User twitterUser = new User();

		twitterUser = new User();
		twitterUser.setScreenname(twitterUserEntity.getScreenname());
		twitterUser.setUserId(twitterUserEntity.getTwitterID());
		twitterUser.setName(twitterUserEntity.getScreenname());

		return twitterUser;
	}

	private User findTwitterUser(User twitterUserEntity) {

		List<User> list = twitterUserRepository.findByScreenname(twitterUserEntity.getScreenname());
		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}










	
	
	
	
	
	
	
	

}
