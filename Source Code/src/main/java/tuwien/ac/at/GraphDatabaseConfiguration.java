package tuwien.ac.at;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tuwien.ac.at.commons.constants.Constants;

/**
 * 
 * @author alisanli
 *
 */
@Configuration
@EnableNeo4jRepositories("tuwien.ac.at.db.graph.repo.api")
@EnableTransactionManagement
@ComponentScan("tuwien.ac.at")
public class GraphDatabaseConfiguration extends Neo4jConfiguration {

	
	@Override
	public SessionFactory getSessionFactory() {
		return new SessionFactory("tuwien.ac.at.db.graph.model.entity");
	}

	@Bean
	public Neo4jServer neo4jServer() {
		
		String usename = tuwien.ac.at.commons.config.Configuration.getInstance().getValue(Constants.NEO4J_USER);
		String pass =  tuwien.ac.at.commons.config.Configuration.getInstance().getValue(Constants.NEO4J_PASS);
		String url =  tuwien.ac.at.commons.config.Configuration.getInstance().getValue(Constants.NEO4J_HOST);

		return new RemoteServer(url, usename, pass);
	}

	@Override
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {
		return super.getSession();
	}
}
