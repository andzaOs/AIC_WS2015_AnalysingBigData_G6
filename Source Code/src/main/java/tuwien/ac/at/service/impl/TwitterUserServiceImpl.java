package tuwien.ac.at.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.db.relational.repo.api.ITwitterUserRepository;
import tuwien.ac.at.service.api.ITwitterUserService;

@Service
public class TwitterUserServiceImpl extends GenericServiceImpl<TwitterUser, Long> implements
ITwitterUserService{
	
	private static final long serialVersionUID = 8030738803199015520L;
	
	private ITwitterUserRepository twitterUserRepository;
	
	public TwitterUserServiceImpl() {
	}
	
	@Autowired
	public TwitterUserServiceImpl(ITwitterUserRepository twitterUserRepository) {
		super(twitterUserRepository);
		this.setTwitterUserRepository(twitterUserRepository);
	}
	
	@Override
	public void fillDatabase(TwitterUser user) {
		// twitterRepository.fillDatabase(user);
	}

	public ITwitterUserRepository getTwitterUserRepository() {
		return twitterUserRepository;
	}

	public void setTwitterUserRepository(ITwitterUserRepository twitterUserRepository) {
		this.twitterUserRepository = twitterUserRepository;
	}

}
