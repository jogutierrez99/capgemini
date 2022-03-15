package com.joshua.conexiones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub

		ConfigDir cg = ConfigDir.getInstance();

		Connection connection = DriverManager.getConnection(cg.getProperty("url"), cg.getProperty("user"),
				cg.getProperty("password"));
		// Muestra toda la tabla;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(cg.getProperty("select"));

		while (rs.next()) {

			int id = rs.getInt("id");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellidos");

			System.out.println(id + " " + nombre + " " + apellido);

		}

		rs.close();
		statement.close();
		connection.close();

		// Insertar

		connection = DriverManager.getConnection(cg.getProperty("url"), cg.getProperty("user"),
				cg.getProperty("password"));

		PreparedStatement ps = connection.prepareStatement(ConfigDir.getInstance().getProperty("insert"));
		ps.setString(1, "Aaron");
		ps.setString(2, "Gutierrez");
		ps.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));

		ps.execute();

		statement.close();
		connection.close();
	}

}
