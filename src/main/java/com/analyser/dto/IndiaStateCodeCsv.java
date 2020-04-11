package com.analyser.dto;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCsv {

    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;
    @CsvBindByName(column = "StateName", required = true)
    public String stateName;
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    public IndiaStateCodeCsv(int srNo, String stateName, String stateCode, int tin) {
        this.srNo = srNo;
        this.stateName = stateName;
        this.stateCode = stateCode;
        this.tin = tin;
    }

    @Override
    public String toString() {
        return "IndiaStateCodeCsv{" +
                "srNo=" + srNo +
                ", stateName='" + stateName + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", tin=" + tin +
                '}';
    }
}