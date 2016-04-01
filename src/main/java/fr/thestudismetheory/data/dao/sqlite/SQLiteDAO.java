/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Model;
import fr.thestudismetheory.data.dao.DAO;
import fr.thestudismetheory.util.Utils;

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
    final protected String selectCond;
    final protected String updateQuery;
    final protected String insertQuery;
    final private String[] nonPkCols;

    public SQLiteDAO(Connection connection) throws SQLException {
        this.connection = connection;
        selectCond = makeSelectCond();
        nonPkCols = generateNonPkColumns();
        updateQuery = makeUpdateQuery();
        insertQuery = makeInsertQuery();
        makeTable();
    }

    protected String makeSelectCond() {
        StringBuilder sb = new StringBuilder();

        boolean b = false;

        for (String k : getPkColumns()) {
            if (b)
                sb.append(" AND ");
            else
                b = true;

            sb.append("`").append(k).append("` = ?");
        }

        return sb.toString();
    }

    protected String makeUpdateQuery() {
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE `").append(getTableName()).append("` SET ");

        boolean b = false;
        for (String col : getNonPkColumns()) {
            if (b)
                sb.append(", ");
            else
                b = true;

            sb.append('`').append(col).append("` = ?");
        }

        sb.append(" WHERE ").append(selectCond);

        return sb.toString();
    }

    protected String makeInsertQuery() {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO `").append(getTableName()).append("`(");

        boolean b = false;

        for (String c : getPkColumns()) {
            if (b)
                sb.append(", ");
            else
                b = true;

            sb.append('`').append(c).append('`');
        }

        for (String c : getNonPkColumns()) {
            if (b)
                sb.append(", ");
            else
                b = true;

            sb.append('`').append(c).append('`');
        }

        sb.append(") VALUES(");

        for (int i = getColumns().length; i > 0; --i) {
            sb.append('?');

            if (i > 1)
                sb.append(", ");
        }

        sb.append(')');

        return sb.toString();
    }

    private String[] generateNonPkColumns() {
        String[] cols = new String[getColumns().length - getPkColumns().length];

        String[] pkCols = getPkColumns();
        int index = 0;

        for (String col : getColumns()) {
            if (Utils.arrayContains(pkCols, col))
                continue;

            cols[index++] = col;
        }

        return cols;
    }

    /**
     * Récupère les colonnes qui ne participent pas à la clé primaire
     *
     * @return tableau contenant le nom des colonnes
     */
    protected String[] getNonPkColumns() {
        return nonPkCols;
    }

    /**
     * Créé la table (CREATE TABLE IF NOT EXISTS)
     *
     * @throws SQLException
     */
    abstract protected void makeTable() throws SQLException;

    /**
     * Récupère TOUTES les colonnes
     * Attention: Cette méthode doit être valide AVANT la construction de l'objet (i.e. doit retourner un attribut static)
     *
     * @return tableau de nom des colonnes
     */
    abstract protected String[] getColumns();

    /**
     * Liste des colonnes de la clé primaire
     * Attention: Cette méthode doit être valide AVANT la construction de l'objet (i.e. doit retourner un attribut static)
     *
     * @return tableau de nom des colonnes
     */
    abstract protected String[] getPkColumns();

    /**
     * Récupère le nom de la tableau
     *
     * @return
     */
    abstract protected String getTableName();

    /**
     * "Hidrate" l'entité.
     *
     * @param RS Le result set courant
     * @return La nouvelle entité
     * @throws SQLException
     */
    abstract protected M createByRS(ResultSet RS) throws SQLException;

    /**
     * Ajoute les valeurs des attributs dans le PreparedStatement SAUF les PK
     * Attention : l'ordre des valeurs doit correspondre à l'ordre des colonnes (i.e. getNonPkColumns())
     *
     * @param entity L'entité à bind
     * @param stmt   le statement
     * @param offset l'index de départ du bind des valeurs
     */
    abstract protected void bindValues(M entity, PreparedStatement stmt, int offset) throws SQLException;

    /**
     * Ajoute les valeurs de la clé primaire dans le statement
     *
     * @param entity
     * @param stmt
     * @param offset l'index de départ du bind des valeurs
     */
    abstract protected void bindPk(M entity, PreparedStatement stmt, int offset) throws SQLException;

    /**
     * Récupère une entité depuis le cache
     *
     * @param pk
     * @return
     */
    protected M getCached(Object... pk) {
        return null;
    }

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
        M entity = getCached(pk);

        if (entity != null)
            return entity;

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE " + selectCond)) {
            for (int i = 0; i < pk.length; ++i) {
                stmt.setObject(i + 1, pk[i]);
            }

            ResultSet RS = stmt.executeQuery();

            if (RS.next())
                return createByRS(RS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(M model) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM " + getTableName() + " WHERE " + selectCond)) {
            bindPk(model, stmt, 1);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(M model) {
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            bindValues(model, stmt, 1);
            bindPk(model, stmt, getNonPkColumns().length + 1);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exécute la requête d'insert, et retourne la clé générée
     *
     * @param entity Entité à insert
     * @return -1 en cas d'erreur, 0 si pas de clé générée, ou la clé générée
     */
    protected long internalInsert(M entity) {
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            bindPk(entity, stmt, 1);
            bindValues(entity, stmt, getPkColumns().length + 1);
            stmt.execute();
            ResultSet RS = stmt.getGeneratedKeys();

            if (RS.next()) {
                return RS.getLong(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("For query " + insertQuery);
            return -1;
        }
    }
}
