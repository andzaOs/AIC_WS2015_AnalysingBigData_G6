package tuwien.ac.at.ui.presenter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

/**
 * 
 * @author alisanli
 *
 */

@Component
@ManagedBean
@SessionScoped
public class ImportPresenter implements Serializable {

	private static final long serialVersionUID = 5286646669540955330L;

	private String mongoDBJsonDataFilePath;

	public ImportPresenter() {
		setMongoDBJsonDataFilePath("/Users/alisanli/Documents/MASTER/aic/topics.json");
	}

	public String getMongoDBJsonDataFilePath() {
		return mongoDBJsonDataFilePath;
	}

	public void setMongoDBJsonDataFilePath(String mongoDBJsonDataFilePath) {
		this.mongoDBJsonDataFilePath = mongoDBJsonDataFilePath;
	}

}
