package com.analyser.dto;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCsv {
    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;
    @CsvBindByName(column = "StateName", required = true)
    public String stateName;
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    @Override
    public String toString() {
        return "StateCodeCsv{" +
                "srNo=" + srNo +
                ", stateName='" + stateName + '\'' +
                ", stateCode=" + stateCode +
                ", tin='" + tin + '\'' +
                '}';
    }
}
