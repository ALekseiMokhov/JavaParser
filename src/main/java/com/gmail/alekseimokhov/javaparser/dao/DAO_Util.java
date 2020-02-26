package com.gmail.alekseimokhov.javaparser.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO_Util {
    /*check correctness of sql syntax!*/
    private static final  String REMOVE_DUPLICATES = "DELETE FROM vacancies v JOIN vacancies w " +
            "ON v.id<w.id " +
            "AND v.url = w.url ";
    public static void removeDuplicatesPSQL(Connection c){
        try {
            Statement statement=c.prepareStatement(REMOVE_DUPLICATES);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
