package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;

import java.util.Arrays;
import java.util.List;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;
    final Double zero = 0.0;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
        rule = new CustomerAccountRule();
        if (customerAccount == null || rule == null) {
            throw new Exception("Setup failed !");
        }
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        assertEquals(zero, customerAccount.getBalance());
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        Double[] addedAmounts = new Double[]{5.45, 0.0, 500.5};
        Double oldBalance;
        for (Double addedAmount : addedAmounts){
            oldBalance = customerAccount.getBalance();
            customerAccount.add(addedAmount);
            assertEquals((Double)(oldBalance + addedAmount), customerAccount.getBalance());
        }
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() {
        customerAccount.add(1000.0);
        // illegal withdraw amounts to fail the test
        Double[] withdrawAmounts = new Double[]{2.45, 0.0, 2000.5};
        // legal withdraw amounts to pass the test
        // Double[] withdrawAmounts = new Double[]{2.45, 0.0, 100.5};
        Double oldBalance;
        for (Double withdrawAmount : withdrawAmounts){
            oldBalance = customerAccount.getBalance();
            try {
                customerAccount.withdrawAndReportBalance(withdrawAmount, rule);
            } catch (IllegalBalanceException e) {
                e.printStackTrace();
            }
            assertEquals((Double)(oldBalance - withdrawAmount), customerAccount.getBalance());
        }
    }
    
    // Also implement missing unit tests for the above functionalities.

}
