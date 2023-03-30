package com.mentorship.bank.impl;

import com.google.auto.service.AutoService;
import com.mentorship.api.Bank;
import com.mentorship.jdbc.dao.BankCardDao;
import com.mentorship.jdbc.dao.impl.BankCardDaoImpl;
import com.mentorship.models.BankCard;


@AutoService(Bank.class)
public class OtherPayment implements Bank {
    BankCardDao bankCardDao = new BankCardDaoImpl();

    @Override
    public BankCard createBankCard(BankCard bankCard) {
        return bankCardDao.create(bankCard);
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
