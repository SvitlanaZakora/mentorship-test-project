module jmp.bank.api {
    uses com.mentorship.service.Service;
    uses com.mentorship.api.Bank;
    requires jmp.dto;
  requires jmp.service.api;
  requires jmp.cloud.service.impl;
    requires jdbc;
    exports com.mentorship.api;
}