package com.mentorship.models;

import lombok.Builder;

public class DebitBankCard extends BankCard{
    @Builder(builderMethodName = "debitBuilder")
    public DebitBankCard(int id, String number,
                          User user,
                          BankCardType bankCardType) {
        super(id, number, user, bankCardType);
    }
}
