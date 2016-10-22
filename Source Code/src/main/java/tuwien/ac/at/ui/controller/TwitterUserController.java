package tuwien.ac.at.ui.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.service.api.ITwitterUserService;

@Controller
@SessionScoped
@ManagedBean
public class TwitterUserController implements Serializable {

	private static final long serialVersionUID = -958538054138668095L;

	@Autowired
	private ITwitterUserService userService;

	// private IFollowingService followingService;

	public void getUser() {
		TwitterUser user = userService.find(Long.valueOf(1));
		System.out.println("User: " + user.getDescription());
	}

	public void fillDatabase() {
		// List<TwitterUserEntity> users = userService.getAll();
		// for(int i=0; i<users.size(); i++)
		// userService.fillDatabase(users.get(i));
		// followingService.createFollowing(users.get(0), users.get(0));
		// System.out.println("Database id filled");

		// List<Following> followers = followingService.getAll();
		System.out.println("Uspjelao");
	}

	public ITwitterUserService getUserService() {
		return userService;
	}

	public void setUserService(final ITwitterUserService userService) {
		this.userService = userService;
	}

}
