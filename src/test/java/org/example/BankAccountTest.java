package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountTest {

    private BankAccount bankAccount;
    private BankAccount bankAccountWithoutDeposit;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setup(){
        bankAccount = new BankAccount("88624", "Robert");
        bankAccount.deposit(500, true);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanStream(){
        System.setOut(originalOut);
    }

    @Test
    void testDepositValidAmount(){
        bankAccount.deposit(200, false);
        double currentBalance = bankAccount.getAvailableBalance();
        double expectedBalance = 700;
        assertEquals(expectedBalance, currentBalance);
    }

    @Test
    void testDepositInvalidAmount(){
        bankAccount.deposit(-500, false);
        double currentBalance = bankAccount.getAvailableBalance();
        double expectedBalance = 500;
        assertEquals(expectedBalance, currentBalance);
    }

    @Test
    void testWithdrawValidAmount(){
        bankAccount.withdraw(350);
        double currentBalance = bankAccount.getAvailableBalance();
        double expectedBalance = 150;
        assertEquals(expectedBalance, currentBalance);
    }

    @Test
    void testWithdrawAmountGreaterThanBalance(){
        bankAccount.withdraw(750);
        double currentBalance = bankAccount.getAvailableBalance();
        double expectedBalance = 500;
        assertEquals(expectedBalance, currentBalance);
    }

    @Test
    void testWithdrawNegativeAmount(){
        bankAccount.withdraw(-200);
        double currentBalance = bankAccount.getAvailableBalance();
        double expectedBalance = 500;
        assertEquals(expectedBalance, currentBalance);
    }

    @Test
    void testGetAccountNumber(){
        String accountNumber = bankAccount.getAccountNumber();
        String expected = "88624";
        assertEquals(expected, accountNumber);
    }

    @Test
    void testGetBankAccountHolderName(){
        String bankAccountHolderName = bankAccount.getBankAccountHolderName();
        String expected = "Robert";
        assertEquals(expected, bankAccountHolderName);
    }


    @Test
    void testBankAccountWithNoDeposit(){
        bankAccountWithoutDeposit = new BankAccount("999192", "John");
        double currentBalance = bankAccountWithoutDeposit.getAvailableBalance();
        double expected = 0;
        assertEquals(expected, currentBalance);
    }

    @Test
    void testDisplayInformation(){
        bankAccount.displayInformation();

        String expected= ""
                + "Account Information: ===============================\n"
                + "Account Number: 88624\n"
                + "Bank Account Holder Name: Robert\n"
                + "Available Balance: 500.00\n";

        assertEquals(expected, outContent.toString());
    }

}