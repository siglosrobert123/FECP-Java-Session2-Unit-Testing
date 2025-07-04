package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        int option = 0;
        do{
            printMenu();
            option = scanner.nextInt();

            // if option = 1, create account
            if (option == 1){
                System.out.print("Enter Account Number: ");
                String accountNumber = scanner.next();

                //Validate accountNumber if unique
                BankAccount existingBankAccount = getBankAccount(bankAccounts, accountNumber);
                // if there is already existing Bank Account with same account number
                if(existingBankAccount != null){
                    System.out.println("Account number entered already exists in the system.");
                    continue;
                }

                System.out.print("Enter Bank Account Holder Name: ");
                String bankAccountHolderName = scanner.next();

                BankAccount createdBankAccount = new BankAccount(accountNumber, bankAccountHolderName);

                System.out.print("Initial Deposit? (yes/no): ");
                String hasInitialDeposit = scanner.next();

                if(hasInitialDeposit.equalsIgnoreCase("yes")){
                    System.out.print("Enter Initial Deposit Amount: ");
                    double initialDepositAmount = scanner.nextDouble();
                    createdBankAccount.deposit(initialDepositAmount, true);
                    bankAccounts.add(createdBankAccount);
                    System.out.println("Account created successfully!");
                }
                else if(hasInitialDeposit.equalsIgnoreCase("no")){
                    bankAccounts.add(createdBankAccount);
                    System.out.println("Account created successfully!");
                }
                else{
                    System.out.println("Invalid option. Try Again.");
                }
            }

            //if option 2, view all accounts
            else if (option == 2){
                System.out.println("====================================================");
                for (BankAccount bankAccount: bankAccounts){
                    bankAccount.displayInformation();
                }
                System.out.println("====================================================");
            }

            //if option 3, check balance
            else if (option == 3 ){

                //Get Account Number
                System.out.print("Enter Account Number: ");
                String accountNumberEntered = scanner.next();

                //Get Bank Account using Account Number
                BankAccount bankAccount = getBankAccount(bankAccounts, accountNumberEntered);

                //If Bank Account is found
                if(bankAccount != null){
                    //Display Balance of bank account
                    System.out.printf("Available Balance: %.2f\n", bankAccount.getAvailableBalance());
                }
                else{
                    System.out.println("Invalid Account Number.");
                }
            }

            //if option 4, deposit
            else if (option == 4){

                //Get Account Number
                System.out.print("Enter Account Number: ");
                String accountNumberEntered = scanner.next();

                //Get Bank Account using Account Number
                BankAccount bankAccount = getBankAccount(bankAccounts, accountNumberEntered);

                //If Bank Account is found
                if(bankAccount != null){

                    // Ask for deposit amount
                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();

                    bankAccount.deposit(depositAmount, false);
                }
                else{
                    System.out.println("Invalid Account Number.");
                }
            }

            // if option 5, withdraw
            if (option == 5) {

                //Get Account Number
                System.out.print("Enter Account Number: ");
                String accountNumberEntered = scanner.next();

                //Get Bank Account using Account Number
                BankAccount bankAccount = getBankAccount(bankAccounts, accountNumberEntered);

                //If Bank Account is found
                if(bankAccount != null){

                    // Ask for withdraw amount
                    System.out.print("Enter Withdraw Amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    bankAccount.withdraw(withdrawAmount);

                }
                else{
                    System.out.println("Invalid Account Number.");
                }
            }

        }while(option != 6);

        System.out.println("Thank you! ===============");
    }

    private static void printMenu(){
        System.out.println();
        System.out.println("== Bank Menu ==");
        System.out.println("1. Create Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Check Balance");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Exit");
        System.out.print("Enter choice (1-6): ");
    }

    private static BankAccount getBankAccount(ArrayList<BankAccount> bankAccounts, String accountNumberEntered){
        for(BankAccount bankAccount: bankAccounts){
            String accountNumber = bankAccount.getAccountNumber();
            if(accountNumber.equalsIgnoreCase(accountNumberEntered)){
                return bankAccount;
            }
        }
        // Account Number not found
        return null;
    }
}