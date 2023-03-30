package com.app;

import com.mentorship.api.Bank;
import com.mentorship.models.BankCard;
import com.mentorship.models.BankCardType;
import com.mentorship.models.User;
import com.mentorship.service.Service;
import com.mentorship.service.impl.ServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        Service service = new ServiceImpl();

        List<User> users = service.getAllUsers();
        final ServiceLoader<Bank> loader = ServiceLoader.load(Bank.class);
        Bank bank = loader.stream().map(ServiceLoader.Provider::get)
                .max(Comparator.comparingInt(Bank::getPriority))
                .orElseThrow();

        System.out.println("ServiceLoader with diff impl: "
                + bank.getClass().getSimpleName() + "    "
                + bank.createBankCard(BankCard.builder().number("3333333333").user(users.get(3))
                .bankCardType(BankCardType.DEBIT).build()).getClass().getSimpleName());
    }
}
