package com.mentorship.service.impl;

import com.google.auto.service.AutoService;
import com.mentorship.jdbc.dao.BankCardDao;
import com.mentorship.jdbc.dao.SubscriptionDao;
import com.mentorship.jdbc.dao.UserDao;
import com.mentorship.jdbc.dao.impl.BankCardDaoImpl;
import com.mentorship.jdbc.dao.impl.SubscriptionDaoImpl;
import com.mentorship.jdbc.dao.impl.UserDaoImpl;
import com.mentorship.models.BankCard;
import com.mentorship.models.Subscription;
import com.mentorship.models.User;
import com.mentorship.service.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AutoService(Service.class)
public class ServiceImpl implements Service {

  SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
  BankCardDao bankCardDao = new BankCardDaoImpl();
  UserDao userDao = new UserDaoImpl();

  @Override
  public void subscribe(BankCard bankCard) {
    Subscription subscription = Subscription.builder().bankcard(bankCard).startDate(LocalDate.now()).build();

    subscriptionDao.create(subscription);
  }

  @Override
  public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
    BankCard bankCard = bankCardDao.findByCardNumber(cardNumber);

    return Optional.ofNullable(subscriptionDao.findByBankCard(bankCard));
  }

  @Override
  public List<User> getAllUsers() {
    return userDao.findAll();
  }

  @Override
  public double getAvgUsersAge() {
    List<User> users = userDao.findAll();
    Long sum = users.stream()
            .map(u -> ChronoUnit.YEARS.between(u.getBirthday(), LocalDate.now()))
            .reduce(Long::sum).orElse(0L);


    return Long.divideUnsigned(sum, users.size());
  }

  @Override
  public boolean isPayableUser(User user) {
    return Long.compare(ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()), 18L) != -1;
  }

  @Override
  public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
    return subscriptionDao.findAll().stream().filter(condition).collect(Collectors.toList());
  }
}
