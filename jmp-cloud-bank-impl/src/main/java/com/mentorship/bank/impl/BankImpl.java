package com.mentorship.bank.impl;

import com.google.auto.service.AutoService;
import com.mentorship.api.Bank;
import com.mentorship.jdbc.dao.BankCardDao;
import com.mentorship.jdbc.dao.impl.BankCardDaoImpl;
import com.mentorship.models.BankCard;

@AutoService(Bank.class)
public class BankImpl implements Bank {

  BankCardDao bankCardDao = new BankCardDaoImpl();

  @Override
  public BankCard createBankCard(BankCard bankCard) {
      if (bankCardDao.create(bankCard)) {
        return bankCard;
      }

      return null;
  }
}
