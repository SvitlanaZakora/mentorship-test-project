package com.mentorship.api;

import com.mentorship.exception.EntityNotFoundException;
import com.mentorship.jdbc.dao.BankCardDao;
import com.mentorship.jdbc.dao.UserDao;
import com.mentorship.jdbc.dao.impl.BankCardDaoImpl;
import com.mentorship.jdbc.dao.impl.UserDaoImpl;
import com.mentorship.models.BankCard;
import com.mentorship.models.BankCardType;
import com.mentorship.models.Subscription;
import com.mentorship.models.User;
import com.mentorship.service.Service;
import com.mentorship.service.impl.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Service service = new ServiceImpl();
        BankCardDao bankCardDao = new BankCardDaoImpl();
        UserDao userDao = new UserDaoImpl();

        List<User> users = service.getAllUsers();
        userDao.create(User.builder().name("Kvita").surname("Drozd").birthday(LocalDate.of(2000, 11, 11)).build());
        userDao.create(User.builder().name("Olena").surname("Drozd").birthday(LocalDate.of(1980, 8, 10)).build());
        userDao.create(User.builder().name("Taras").surname("Drozd").birthday(LocalDate.of(2001, 3, 3)).build());
        bankCardDao.create(BankCard.builder().number("2222222222").user(users.get(2)).bankCardType(BankCardType.DEBIT).build());

        System.out.println(service.getAvgUsersAge());
        System.out.println(service.isPayableUser(users.get(0)));
        BankCard bankCard = bankCardDao.findByCardNumber("1111111111");

        service.subscribe(bankCard);
        try {
            Subscription subscription = service.getSubscriptionByBankCardNumber("2222222222")
                    .orElseThrow(() -> new EntityNotFoundException("Subscription wasn`t found"));

            System.out.println(subscription);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
        }

        Predicate<Subscription> predicate = e -> e.getStartDate().isBefore(LocalDate.of(2023, 3, 26));

        List<Subscription> subscriptionList = service.getAllSubscriptionsByCondition(predicate);

        subscriptionList.forEach(System.out::println);

        final ServiceLoader<Service> services = ServiceLoader.load(Service.class);
        for (Service s : services) {
            System.out.println("IsPayableUser used ServiceLoader: " + s.isPayableUser(users.get(0)));
        }
    }
}
