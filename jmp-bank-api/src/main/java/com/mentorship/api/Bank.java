package com.mentorship.api;

import com.mentorship.models.BankCard;

public interface Bank {
  BankCard createBankCard(BankCard bankCard);

  int getPriority();
}
