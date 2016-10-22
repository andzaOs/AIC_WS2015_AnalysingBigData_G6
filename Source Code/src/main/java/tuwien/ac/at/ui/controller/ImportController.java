package tuwien.ac.at.ui.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tuwien.ac.at.service.api.IDocumentDatabaseService;
import tuwien.ac.at.service.api.IGraphDatabaseService;
import tuwien.ac.at.ui.presenter.ImportPresenter;
import tuwien.ac.at.ui.presenter.TwitterPresenter;

/**
 * 
 * @author alisanli
 *
 */
@Controller
@SessionScoped
@ManagedBean
public class ImportController implements Serializable {

	private static final long serialVersionUID = -958538054138668095L;

	@Autowired
	private IDocumentDatabaseService documentDatabaseService;

	@Autowired
	private IGraphDatabaseService graphDatabaseService;

	@Autowired
	private ImportPresenter importPresenter;

	@Autowired
	private TwitterPresenter twitterPresenter;

	private static final Logger LOGGER = Logger.getLogger("ImportController");

	public TwitterPresenter getTwitterPresenter() {
		return twitterPresenter;
	}

	public void setTwitterPresenter(TwitterPresenter twitterPresenter) {
		this.twitterPresenter = twitterPresenter;
	}

	public String documentDBImport() {
		LOGGER.info("Document DB import started...");
		documentDatabaseService.importFromJsom(importPresenter
				.getMongoDBJsonDataFilePath());
		return "home";
	}

	public String graphDBImport() {

		try {
			importStart();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "home";
	}

	public void importStart() throws IOException {

		graphDatabaseService.importFromRdbs();

	}

}
