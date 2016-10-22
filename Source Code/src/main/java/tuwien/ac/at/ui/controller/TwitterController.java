package tuwien.ac.at.ui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tuwien.ac.at.db.graph.model.entity.Topic;
import tuwien.ac.at.db.graph.model.entity.User;
import tuwien.ac.at.db.graph.repo.api.TopicRepository;
import tuwien.ac.at.db.graph.repo.api.UserRepository;
import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.lda.TopicModel;
import tuwien.ac.at.service.api.ITwitterUserService;
import tuwien.ac.at.ui.presenter.TwitterPresenter;

/**
 * 
 * @author alisanli
 *
 */
@Controller
@SessionScoped
@ManagedBean
public class TwitterController implements Serializable {

	private static final long serialVersionUID = -958538054138668095L;

	@Autowired
	private TwitterPresenter twitterPresenter;

	@Autowired
	private ITwitterUserService twitterUserService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TopicRepository topicRepository;

	public String importData() {

		// LOGGER.info("importing the topic and user data...");

		List<TwitterUser> userList = null;

		try {

			userList = twitterUserService.getAll();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		for (TwitterUser twitterUserEntity : userList) {
			try {
				// create TwitterUser Object for Graph db from RDB Entity
				User twitterUser = convet2Graphdb(twitterUserEntity);
				List<String> topics = new ArrayList<>();
				// topics from Json file
				topics = TopicModel.getTopWords(twitterUserEntity
						.getScreenname());

				// all topic

				if (topics != null) {

					for (String topicName : topics) {
						Topic topic = new Topic();
						topic.setName(topicName);
						twitterUser.addTopic(topic);
					}
				}

				userRepository.save(twitterUser);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "home";
	}

	public TwitterPresenter getTwitterPresenter() {
		return twitterPresenter;
	}

	public void setTwitterPresenter(TwitterPresenter twitterPresenter) {
		this.twitterPresenter = twitterPresenter;
	}

	private User convet2Graphdb(TwitterUser twitterUserEntity) {

		User twitterUser = new User();

		twitterUser.setScreenname(twitterUserEntity.getScreenname());
		twitterUser.setUserId(twitterUserEntity.getTwitterID());

		/*for (TwitterUser follower : twitterUserEntity.getAllFollowers()) {

		}*/
		return twitterUser;
	}

}
