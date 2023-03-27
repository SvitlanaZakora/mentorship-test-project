package com.mentorship.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class User {

  private int id;
  private String name;
  private String surname;
  private LocalDate birthday;
}
