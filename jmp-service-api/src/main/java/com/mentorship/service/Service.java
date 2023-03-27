package com.mentorship.service;

import com.mentorship.models.BankCard;
import com.mentorship.models.Subscription;
import com.mentorship.models.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
  void subscribe(BankCard bankCard);
  Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);
  List<User> getAllUsers();
  double getAvgUsersAge();
  boolean isPayableUser(User user);
  List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

}
