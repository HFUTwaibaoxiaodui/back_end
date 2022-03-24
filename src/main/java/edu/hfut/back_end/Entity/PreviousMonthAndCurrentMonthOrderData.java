package edu.hfut.back_end.Entity;

import lombok.Data;

@Data
public class PreviousMonthAndCurrentMonthOrderData {
    int previousMonthCreatedOrder;
    int previousMonthFinishOrder;
    int previousMonthExceptionOrder;
    int currentMonthCreatedOrder;
    int currentMonthFinishOrder;
    int currentMonthExceptionOrder;
}
