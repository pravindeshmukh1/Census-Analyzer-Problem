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
                ", stateCode='" + stateCode + '\'' +
                ", tin=" + tin +
                '}';
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }
}
