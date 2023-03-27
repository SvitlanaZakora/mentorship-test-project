import com.mentorship.api.Bank;
import com.mentorship.bank.impl.BankImpl;

module jmp.cloud.bank.impl {
  requires transitive jmp.bank.api;
  requires jmp.dto;
  requires jdbc;
  requires com.google.auto.service;
  provides Bank with BankImpl;

  exports com.mentorship.bank.impl;
}