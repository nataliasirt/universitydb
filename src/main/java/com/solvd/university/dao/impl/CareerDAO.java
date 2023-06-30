package com.solvd.university.dao.impl;

import com.solvd.university.dao.ICareerDAO;
import com.solvd.university.models.Career;
import com.solvd.university.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CareerDAO implements ICareerDAO {
    private final static Logger LOGGER = LogManager.getLogger(CareerDAO.class);
    private static final String SELECT = "SELECT id, title, duration, cost FROM careers WHERE id = ?";
    private static final String DELETE = "DELETE FROM Career WHERE id=?";
    private static final String INSERT = "INSERT * FROM Career WHERE id=?";
    private static final String UPDATE = "UPDATE careers SET title = ?, duration = ?, cost = ? WHERE id = ?";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Career select(int id) {
        Career career = new Career();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT);){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    int careerId = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    int duration = resultSet.getInt("duration");
                    double cost = resultSet.getDouble("cost");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return career;
    }
    @Override
    public void insert(Career career) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {
            preparedStatement.setString(1, career.getTitle());
            preparedStatement.setInt(2, career.getDuration());
            preparedStatement.setDouble(3, career.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Career career, int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, career.getTitle());
            preparedStatement.setInt(2, career.getDuration());
            preparedStatement.setDouble(3, career.getCost());
            preparedStatement.setInt(4, career.getDuration());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Career career) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, career.getCareerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Career> selectAll() {
        return null;
    }
}