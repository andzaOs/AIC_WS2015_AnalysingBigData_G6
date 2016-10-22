package tuwien.ac.at;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tuwien.ac.at.db.relational.model.dto.TwitterUserDTO;
import tuwien.ac.at.db.relational.repo.api.ITwitterUserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { 
		"classpath:*aic-test-context.xml"
		}) 
public class RelationalDbTest {

	@Autowired
	private ITwitterUserRepository twitterUserRepository;
	
	@Test
	public void configurationTest() {
		Assert.assertTrue(null != twitterUserRepository);
	}
	
	
	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		TwitterUserDTO user = new TwitterUserDTO();
		user.setScreenname("LeoDiCaprio");
		List<TwitterUserDTO> followers = new ArrayList<TwitterUserDTO>();
		followers = user.getAllFollowers();
		for (int i = 0; i < followers.size(); i++)
			System.out.println(i + " Follower: "
					+ followers.get(i).getScreenname());

	}

}
