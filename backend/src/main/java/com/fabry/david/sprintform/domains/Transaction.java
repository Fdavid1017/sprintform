package com.fabry.david.sprintform.domains;

import com.fabry.david.sprintform.enums.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    public Long id;

    public String summary;
    public TransactionCategory category;
    public Float sum;
    public String currency;
    public Date paid;

//    "summary": "vonaljegy Oktogontól Nyugatiba",
//            "category": "travel",
//            "sum": 350,
//            "currency": "HUF",
//            "paid": "2022-04-21T10:54:00Z"
}
