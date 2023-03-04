package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

import javax.xml.transform.Result;

public class ClientDao {

    private static ClientDao instance = null;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDao();
        }
        return instance;
    }

    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";

    public long create(Client client) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(CREATE_CLIENT_QUERY,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,client.getName());
            stmt.setString(2,client.getSurname());
            stmt.setString(3, client.getEmail());
            stmt.setDate(4, Date.valueOf(client.getBirthday()));
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }
    public long delete(long id) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_CLIENT_QUERY);
            stmt.setLong(1,id);
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public Client findById(long id) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_CLIENT_QUERY);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String nom = (rs.getString("nom"));
            String prenom = (rs.getString("prenom"));
            String email = (rs.getString("email"));
            LocalDate birthday = (rs.getDate("naissance").toLocalDate());
            return new Client((int) id,nom,prenom,email,birthday);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<Client>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);
            while (rs.next()) {
                int id = (rs.getInt("id"));
                String nom = (rs.getString("nom"));
                String prenom = (rs.getString("prenom"));
                String email = (rs.getString("email"));
                LocalDate birthday = (rs.getDate("naissance").toLocalDate());

                clients.add(new Client(id, nom, prenom, email, birthday));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return clients;
    }


}
