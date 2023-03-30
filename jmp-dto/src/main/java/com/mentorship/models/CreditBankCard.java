package com.mentorship.models;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
public class CreditBankCard extends BankCard {
    @Builder(builderMethodName = "creditBuilder")
    public CreditBankCard(int id, String number,
                    User user,
                    BankCardType bankCardType) {
        super(id, number, user, bankCardType);
    }
}
