package com.mentorship.jdbc.dao;

import com.mentorship.models.BankCard;
import com.mentorship.models.Subscription;

import java.util.List;

public interface SubscriptionDao {

    boolean create(Subscription subscription);

    Subscription findByBankCard(BankCard card);

    List<Subscription> findAll();
}
