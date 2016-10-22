package tuwien.ac.at.ui.presenter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import tuwien.ac.at.commons.config.Configuration;
import tuwien.ac.at.commons.constants.Constants;

/**
 * 
 * @author alisanli
 *
 */

@Component
@ManagedBean
@SessionScoped
public class TwitterPresenter implements Serializable {

	private static final long serialVersionUID = 5286646669540955330L;

	private String dataPath;

	public TwitterPresenter() {
		setDataPath(Configuration.getInstance().getValue(
				Constants.CONFIGURATION_KEY_FILE_TWITTER_DATA));
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

}
