package tuwien.ac.at;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("tuwien.ac.at.db.document.repo.api")
@ComponentScan("tuwien.ac.at")
public class DocumentDatabaseConfiguration extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "aic15";
	}

	@Override
	public Mongo mongo() throws Exception {
		MongoClient mongoClient = new MongoClient("localhost" , 27017);
		return mongoClient;
	}

}
