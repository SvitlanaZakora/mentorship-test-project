module jdbc {
    requires jmp.dto;
    requires java.sql;
    exports com.mentorship.jdbc;
    exports com.mentorship.jdbc.dao;
    exports com.mentorship.jdbc.dao.impl;
    exports com.mentorship.exception;
}