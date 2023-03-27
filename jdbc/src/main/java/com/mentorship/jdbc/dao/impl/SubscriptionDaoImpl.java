package com.mentorship.jdbc.dao.impl;

import com.mentorship.jdbc.ConnectorDB;
import com.mentorship.jdbc.dao.BankCardDao;
import com.mentorship.jdbc.dao.SubscriptionDao;
import com.mentorship.models.BankCard;
import com.mentorship.models.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {
    public static final String SQL_SELECT_ALL_SUBSCRIPTIONS = "SELECT * FROM MENTORSHIP.SUBSCRIPTION";
    public static final String SQL_SELECT_BY_BANK_CARD_ID = "SELECT * FROM MENTORSHIP.SUBSCRIPTION WHERE bank_card_id=?";
    public static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO MENTORSHIP.SUBSCRIPTION VALUES (default, ?, ?)";

    BankCardDao bankCardDao = new BankCardDaoImpl();

    @Override
    public boolean create(Subscription subscription) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SUBSCRIPTION)) {

            statement.setInt(1, subscription.getBankcard().getId());
            statement.setDate(2, Date.valueOf(subscription.getStartDate()));
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public Subscription findByBankCard(BankCard card) {
        Subscription subscription = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_BANK_CARD_ID)) {

            statement.setInt(1, card.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int subscription_id = resultSet.getInt(1);
                Date startDate = resultSet.getDate(3);

                subscription = new Subscription(subscription_id, card, startDate.toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return subscription;
    }

    @Override
    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_SUBSCRIPTIONS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int subscription_id = resultSet.getInt(1);
                int bank_card_id = resultSet.getInt(2);
                Date startDate = resultSet.getDate(3);

                BankCard bankCard = bankCardDao.findById(bank_card_id);

                subscriptions.add(new Subscription(subscription_id, bankCard, startDate.toLocalDate()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return subscriptions;
    }
}
