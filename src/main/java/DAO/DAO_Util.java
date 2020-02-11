package DAO;

import MAIN.Desc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO_Util {
    @Desc(text = "utility methods")
    private static final  String REMOVE_DUPLICATES = "DELETE FROM vacancies v USING vacancies w " +
            "WHERE v.id<w.id " +
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
