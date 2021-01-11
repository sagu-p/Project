package com.bank.dao.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PostresqlConnectionTest {
	
	Connection connection;	

	@BeforeAll
	public static void setUp() throws SQLException {
		mockStatic(DriverManager.class);
	}
	
	@BeforeEach
	public void initialize() {
		connection = mock(Connection.class);
	}
	
	
	@Test
	public void testConnection() throws SQLException, ClassNotFoundException {
		when(DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(connection);
		assertEquals(connection, PostresqlConnection.getConnection());
		System.out.println("Connection Test Success: PostresqlConnection.getConnection() ");
	}
}


/*
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
	<dependency>
	    <groupId>org.mockito</groupId>
	    <artifactId>mockito-core</artifactId>
	    <version>3.6.0</version>
	    <scope>test</scope>
	</dependency> 
 */