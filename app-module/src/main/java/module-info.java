module app.module {
    uses com.mentorship.api.Bank;
    requires jmp.bank.api;
    requires jmp.cloud.bank.impl;
    requires jmp.dto;
    requires jdbc;
    requires jmp.cloud.service.impl;
}