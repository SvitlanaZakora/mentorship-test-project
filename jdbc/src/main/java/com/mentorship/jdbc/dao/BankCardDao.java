package com.mentorship.jdbc.dao;

import com.mentorship.models.BankCard;

public interface BankCardDao {

    BankCard create(BankCard bankCard);

    BankCard findByCardNumber(String cardNumber);

    BankCard findById(int id);
}
