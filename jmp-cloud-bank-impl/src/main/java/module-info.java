import com.mentorship.api.Bank;
import com.mentorship.bank.impl.BankImpl;
import com.mentorship.bank.impl.OtherPayment;

module jmp.cloud.bank.impl {
  requires transitive jmp.bank.api;
  requires jmp.dto;
  requires jdbc;
  requires com.google.auto.service;
  requires lombok;

  exports com.mentorship.bank.impl;
  provides Bank with OtherPayment, BankImpl;
}