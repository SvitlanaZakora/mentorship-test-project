package com.mentorship.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankCard {

  private int id;
  private String number;
  private User user;
  private BankCardType bankCardType;

}
