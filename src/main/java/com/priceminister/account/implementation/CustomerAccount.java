package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    private Double balance = 0.0;

    public void add(Double addedAmount) {
        this.setBalance(this.balance + addedAmount);
    }

    public Double getBalance() {
        return this.balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException {
        Double resultingAccountBalance = this.getBalance() - withdrawnAmount;
        if (rule == null || (rule != null && !rule.withdrawPermitted(resultingAccountBalance))) {
            throw new IllegalBalanceException(resultingAccountBalance);
        } else {
            this.add(withdrawnAmount*(-1));
        }
        return this.getBalance();
    }

}
