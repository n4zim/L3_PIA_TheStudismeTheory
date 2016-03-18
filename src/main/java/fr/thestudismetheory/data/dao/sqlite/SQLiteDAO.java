/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Model;
import fr.thestudismetheory.data.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vincent
 */
abstract public class SQLiteDAO<M extends Model> implements DAO<M> {
    final protected Connection connection;
    final private String selectCond;

    public SQLiteDAO(Connection connection) throws SQLException {
        this.connection = connection;
        makeTable();
        selectCond = makeSelectCond();
    }

    protected String makeSelectCond() {
        StringBuilder sb = new StringBuilder();

        boolean b = false;

        for (String k : getPkColumns()) {
            if (b)
                sb.append(" AND ");
            else
                b = true;

            sb.append(k).append(" = ?");
        }

        return sb.toString();
    }

    abstract protected void makeTable() throws SQLException;

    abstract protected String[] getColumns();

    abstract protected String[] getPkColumns();

    abstract protected Object[] getPkValues(M entity);

    abstract protected String getTableName();

    abstract protected M createByRS(ResultSet RS) throws SQLException;

    @Override
    public List<M> getAll() {
        List<M> entities = new ArrayList<>();

        try (ResultSet RS = connection.createStatement().executeQuery("SELECT * FROM " + getTableName())) {
            while (RS.next()) {
                entities.add(createByRS(RS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    @Override
    public M findByPrimaryKey(Object... pk) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE " + selectCond)) {
            for (int i = 0; i < pk.length; ++i) {
                stmt.setObject(i + 1, pk[i]);
            }

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(M model) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM " + getTableName() + " WHERE " + selectCond)) {
            Object[] pk = getPkValues(model);
            for (int i = 0; i < pk.length; ++i) {
                stmt.setObject(i + 1, pk[i]);
            }

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
