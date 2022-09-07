package com.fabry.david.sprintform.helpers;

import com.fabry.david.sprintform.enums.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {
    private String summary;
    private TransactionCategory category;
    private Float sum;
    private String currency;
}
