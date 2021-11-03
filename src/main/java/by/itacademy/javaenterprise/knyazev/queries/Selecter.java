package by.itacademy.javaenterprise.knyazev.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.itacademy.javaenterprise.knyazev.db.DbConnection;

@Component
public class Selecter implements IQuery {
	@Autowired
	private DbConnection dbConnection;
	@Autowired
	@Qualifier(value = "selecterLogger")
	private Logger logger;

	public List<Map<String, Object>> selectNative(String nativeSqlQuery) {
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> resultMap = null;

		logger.info("Preparing native query on select: " + nativeSqlQuery);

		try (Connection connection = dbConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(nativeSqlQuery);
				ResultSet resultSet = ps.executeQuery()) {

			connection.setAutoCommit(false);
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				resultMap = new LinkedHashMap<>();

				for (int i = 1; i < columnCount + 1; i++) {
					resultMap.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				result.add(resultMap);
			}
			
			connection.commit();
			
		} catch (SQLException e) {
			logger.error("Error selecting on selectNative() method: " + e.getMessage());
		}
		
		return result;
	}
}
