package com.fabry.david.sprintform.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class TransactionRanges {
    @Getter
    @Setter
    private Float sumMin;

    @Getter
    @Setter
    private Float sumMax;

    @Getter
    @Setter
    private Date paidMin;

    @Getter
    @Setter
    private Date paidMax;
}
