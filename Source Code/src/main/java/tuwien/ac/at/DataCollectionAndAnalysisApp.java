package tuwien.ac.at;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tuwien.ac.at.db.relational.model.entity.TwitterUser;
import tuwien.ac.at.lda.TopicModel;
import tuwien.ac.at.mining.MinerRunner;
import tuwien.ac.at.mining.RawDataCleaner;

//we do have have three directories: RawData, Textonly, Analysis
public class DataCollectionAndAnalysisApp {

	public static void main(String[] args) {

		final long DOWNLOAD_TIME = 60000000;

		File file = null;
		File[] files = null;
		long[] twitterIds = null;

		List<TwitterUser> users = new ArrayList<>();
		DAO dao = new DAO();
		try {
			users = dao.getAll();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

		twitterIds = new long[users.size()];

		for (int i = 0; i < twitterIds.length; i++) {

			twitterIds[i] = users.get(i).getTwitterID();
		}

		
		try {
			System.out.println("Starting download ...");
			MinerRunner.startDownload(twitterIds, "all");
			Thread.sleep(DOWNLOAD_TIME);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		MinerRunner.stopDownload();

		System.out.println("Finished Download");

		System.out.println("Started cleaning ...");
		file = MinerRunner.getFile();

		try {
			RawDataCleaner.createOnlyText(file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Starting analysis ...");
		for (TwitterUser currentUser : users) {
			files = RawDataCleaner.getFile(currentUser.getScreenname());
			try {
				TopicModel.analyzeTwitterUser(files, currentUser.getScreenname(), 10);
			} catch (RuntimeException e) {
				System.out.println("There are no files from user" + currentUser.getScreenname());
			}

		}
	}
}
