package com.mentorship.jdbc.dao.impl;

import com.mentorship.jdbc.ConnectorDB;
import com.mentorship.jdbc.dao.BankCardDao;
import com.mentorship.jdbc.dao.UserDao;
import com.mentorship.models.BankCard;
import com.mentorship.models.BankCardType;
import com.mentorship.models.User;

import java.sql.*;

public class BankCardDaoImpl implements BankCardDao {
    public static final String SQL_SELECT_BY_CARD_NUMBER = "SELECT * FROM MENTORSHIP.BANK_CARD WHERE card_number=?";
    public static final String SQL_SELECT_BY_CARD_ID = "SELECT * FROM MENTORSHIP.BANK_CARD WHERE id=?";
    public static final String SQL_INSERT_BANK_CARD = "INSERT INTO MENTORSHIP.BANK_CARD VALUES (default, ?, ?, ?)";

    UserDao userDao = new UserDaoImpl();

    @Override
    public BankCard create(BankCard bankCard) {
        try (Connection connection = ConnectorDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_BANK_CARD, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, bankCard.getUser().getId());
            statement.setString(2, bankCard.getNumber());
            statement.setString(3, bankCard.getBankCardType().toString());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                long bank_card_id = rs.getLong(1);
                bankCard.setId(Math.toIntExact(bank_card_id));
            }

            return bankCard;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public BankCard findByCardNumber(String cardNumber) {
        BankCard bankCard = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CARD_NUMBER)) {

            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int bank_card_id = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                String number = resultSet.getString(3);
                String type = resultSet.getString(4);

                User user = userDao.findById(user_id);
                bankCard = new BankCard(bank_card_id, number, user, BankCardType.valueOf(type));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bankCard;
    }

    @Override
    public BankCard findById(int id) {
        BankCard bankCard = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CARD_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int bank_card_id = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                String number = resultSet.getString(3);
                String type = resultSet.getString(4);

                User user = userDao.findById(user_id);
                bankCard = new BankCard(bank_card_id, number, user, BankCardType.valueOf(type));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bankCard;
    }
}
