package com.mentorship.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class Subscription {

  private int id;
  private BankCard bankcard;
  private LocalDate startDate;
}
