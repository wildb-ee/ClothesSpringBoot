package com.project.my_project.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionForm {
    private String lastFourDigits;
    private String firstName;
    private String lastName;
}