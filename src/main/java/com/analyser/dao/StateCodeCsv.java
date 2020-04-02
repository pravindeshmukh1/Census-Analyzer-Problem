package com.analyser.dao;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCsv {
    @CsvBindByName(column = "SrNo", required = true)
    private int srNo;
    @CsvBindByName(column = "StateName", required = true)
    private String stateName;
    @CsvBindByName(column = "StateCode", required = true)
    private int stateCode;
    @CsvBindByName(column = "TIN", required = true)
    private String tin;

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
