package com.fabry.david.sprintform.helpers;

import com.fabry.david.sprintform.enums.TransactionCategory;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionSearchInput {
    @Getter
    private String summary;
    @Getter
    private TransactionCategory category;
    @Getter
    private Float sumMin;
    @Getter
    private Float sumMax;
    @Getter
    private Date paidStart;
    @Getter
    private Date paidEnd;
}
