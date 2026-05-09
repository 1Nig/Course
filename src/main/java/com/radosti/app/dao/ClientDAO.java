package com.radosti.app.dao;

import com.radosti.app.db.DatabaseConnection;
import com.radosti.app.domain.Client;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {

            public void save(Client client) {
            String sql = "INSERT INTO clients (passport_id, name, surname) VALUES (?, ?, ?)";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, client.getPassportID());
                statement.setString(2, client.getName());
                statement.setString(3, client.getSurname());

                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Error saving client", e);
            }
        }
        public Client findById(String passportID){
                String sql = "SELECT * FROM clients WHERE passport_id = ? ";

                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement statement = conn.prepareStatement(sql)){
                    statement.setString(1, passportID);

                    try(ResultSet rs = statement.executeQuery()){
                        if(rs.next()){
                            Client client = new Client(
                            rs.getString("passport_id"),
                            rs.getString("name"),
                            rs.getString("surname")
                            );

                            return client;
                        }
                        else{
                            return null;
                        }
                    }
                }
                catch(SQLException e) {
                    throw new RuntimeException("Error finding a client", e);
                }
        }
    }


