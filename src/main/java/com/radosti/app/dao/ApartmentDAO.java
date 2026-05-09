package com.radosti.app.dao;

import com.radosti.app.db.DatabaseConnection;
import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO {
    public void save (Apartment apartment){
        String sql = "INSERT INTO apartments (id, price) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
             statement.setInt(1, apartment.getId());
             statement.setDouble(2, apartment.getPrice());

             statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("The problem: " + e.getMessage());
        }
    }
    public Apartment findById(int id){
        String sql = "SELECT * FROM apartments WHERE id = ? ";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, id);

            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    Apartment apartment = new Apartment(
                            rs.getInt("id"),
                            rs.getDouble("price"),
                            rs.getBoolean("isReserved")
                            );

                    return apartment;
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
    public void updateReservation(int id, String client_passport, boolean isReserved) {
        String sql = "UPDATE apartments SET is_reserved = ?, client_passport = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, isReserved);
            statement.setString(2, client_passport);
            statement.setInt(3, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating reservation " + e.getMessage());
        }
    }
    public void updateRelease(int id, boolean isReserved) {
        String sql = "UPDATE apartments SET is_reserved = ?, WHERE id = ?, client_passport = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, isReserved);
            statement.setInt(2, id);
            statement.setString(3, null);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating reservation " + e.getMessage());
        }
    }
    public List<Apartment> findAll(int page, int size, String sortBy) {

        String sql = "SELECT * FROM apartments ORDER BY " + sortBy + " LIMIT ? OFFSET ?";

        List<Apartment> result = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, size);
            statement.setInt(2, (page - 1) * size);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Apartment apartment = new Apartment(
                            rs.getInt("id"),
                            rs.getDouble("price"),
                            rs.getBoolean("is_reserved"),
                            rs.getString("client_passport")
                    );
                    result.add(apartment);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing apartments", e);
        }

        return result;
    }


}
