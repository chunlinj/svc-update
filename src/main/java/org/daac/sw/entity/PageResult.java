package org.daac.sw.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by  HASEE on 2018-12-07
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
