package com.fsd2020.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadReturn {
    private String company_num;
    private String stock_exchange;
    private int record_num;
    private String start_date;
    private String end_date;
}
