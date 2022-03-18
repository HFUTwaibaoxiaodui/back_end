package edu.hfut.back_end.Entity;

import lombok.Data;

/**
 * @author HeRunLin
 */
@Data
public class OrderEvaluate {
    /**
     * 工单id
     */
    private int orderId;

    /**
     * 巡检问题是否解决
     */
    private String situation;

    /**
     * 未解决问题描述
     */
    private String description;

    /**
     * 工单得分
     */
    private int score;
}
