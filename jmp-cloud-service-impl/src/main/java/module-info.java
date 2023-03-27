import com.mentorship.service.Service;
import com.mentorship.service.impl.ServiceImpl;

module jmp.cloud.service.impl {
  requires transitive jmp.service.api;
  requires jmp.dto;
  requires jdbc;
  requires com.google.auto.service;
  exports com.mentorship.service.impl;
  provides Service with ServiceImpl;

}