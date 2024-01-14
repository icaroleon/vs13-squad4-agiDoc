package service;

import model.document.Document;

import java.sql.*;
import java.util.ArrayList;

import exception.DatabaseException;

public class DocumentService implements IService<Integer, Document> {

    @Override
    public Integer getNextId(Connection con) throws SQLException {
        return 1;
    }

    @Override
    public Document create(Document object) throws DatabaseException {
        Document doc = new Document();
        return doc;
    }

    @Override
    public boolean update(Integer id, Document object) throws DatabaseException {
        return true;
    }

    @Override
    public boolean delete(Integer id) throws DatabaseException {
        return true;
    }

    public Document get(Integer id) {
        return new Document();
    }

    @Override
    public ArrayList<Document> list() throws DatabaseException {
        ArrayList<Document> docs = new ArrayList<>();
        return docs;
    }

}
