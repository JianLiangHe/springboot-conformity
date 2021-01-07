package edu.conformity.appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoAppender extends AppenderSkeleton {

	// 定义MongoDB的连接和操作对象，根据log4j.properties配置的参数初始化
	// mongodb的连接客户端
	private MongoClient mongoClient;
	
	// 记录日志的数据库
	private MongoDatabase mongoDatabase;
	
	// 记录日志的集合
	private MongoCollection<BasicDBObject> logsCollection;
	
	// 定义mongodb的配置参数，可通过log4j.properties配置
	// 连接mongodb的串
	private String connectionUrl;
	
	// 数据库名
	private String databaseName;
	
	// 集合名
	private String collectionName;
	
	@Override
	public void close() {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent loggingEvent) {
		if (mongoDatabase == null) {
			MongoClientURI connectionString = new MongoClientURI(connectionUrl);
			mongoClient = new MongoClient(connectionString);
			mongoDatabase = mongoClient.getDatabase(databaseName);
			logsCollection = mongoDatabase.getCollection(collectionName, BasicDBObject.class);
		}
		logsCollection.insertOne((BasicDBObject) loggingEvent.getMessage());
	}

}
