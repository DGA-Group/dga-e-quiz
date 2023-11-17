package com.dga.equiz.model;

import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashCard {
    public static ArrayList<PairWord> ListFlashCard = new ArrayList<>();

    public static void GenListFlashCard() throws SQLException {
        Profile profile = ApplicationData.getInstance().profile;
        String sqlQuery = "SELECT * FROM `flashcard` WHERE id = '" + profile.getID() + "';";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        resultSet = DBHelper.executeQuery(sqlQuery);
        statement = resultSet.getStatement();
        connection = statement.getConnection();
        try {
            while(resultSet.next()) {
                PairWord pairWord = new PairWord(resultSet.getString(2), resultSet.getString(3));
                ListFlashCard.add(pairWord);
            }
        }  catch (SQLException e) {

        }
    }

    public static List<PairWord> getListFlashCard() throws SQLException {
        GenListFlashCard();
        Collections.shuffle(ListFlashCard);
        return ListFlashCard;
    }
}
