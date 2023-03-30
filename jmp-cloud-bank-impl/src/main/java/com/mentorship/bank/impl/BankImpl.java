package com.mentorship.bank.impl;

import com.google.auto.service.AutoService;
import com.mentorship.api.Bank;
import com.mentorship.jdbc.dao.BankCardDao;
import com.mentorship.jdbc.dao.impl.BankCardDaoImpl;
import com.mentorship.models.BankCard;
import com.mentorship.models.CreditBankCard;
import com.mentorship.models.DebitBankCard;

import static com.mentorship.models.BankCardType.CREDIT;
import static com.mentorship.models.BankCardType.DEBIT;

@AutoService(Bank.class)
public class BankImpl implements Bank {

  BankCardDao bankCardDao = new BankCardDaoImpl();

  @Override
  public BankCard createBankCard(BankCard bankCard) {
      bankCard = bankCardDao.create(bankCard);
      if (CREDIT.equals(bankCard.getBankCardType())) {
          return CreditBankCard.creditBuilder()
                  .id(bankCard.getId())
                  .user(bankCard.getUser())
                  .number(bankCard.getNumber())
                  .bankCardType(bankCard.getBankCardType())
                  .build();
      }
      if (DEBIT.equals(bankCard.getBankCardType())) {
          return DebitBankCard.debitBuilder()
                  .id(bankCard.getId())
                  .user(bankCard.getUser())
                  .number(bankCard.getNumber())
                  .bankCardType(bankCard.getBankCardType())
                  .build();
      }

      return null;
  }

    @Override
    public int getPriority() {
        return 1;
    }


}
