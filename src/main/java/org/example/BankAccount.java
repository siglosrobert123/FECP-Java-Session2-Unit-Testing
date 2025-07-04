package org.example;

public class BankAccount {
    private String accountNumber;
    private String bankAccountHolderName;
    private double availableBalance;

    BankAccount(String accountNumber, String bankAccountHolderName){
        this.accountNumber = accountNumber;
        this.bankAccountHolderName = bankAccountHolderName;
    }

    public void deposit(double depositAmount, boolean isInitialDeposit){
        //Validate deposit amount must greater than 0
        if (depositAmount <= 0) {
            System.out.println("Deposit Amount must be greater than 0.");
            return;
        }
        else{
            this.availableBalance += depositAmount;
            if(!isInitialDeposit){
                System.out.printf("Deposit successful for Account " + this.getAccountNumber() + " with amount %.2f.\n", depositAmount );
                System.out.printf("Account New Balance: %.2f\n", this.getAvailableBalance());
            }
        }
    }

    public void withdraw(double withdrawAmount){
        //Validate withdraw amount must be less than or equal to available balance
        if (withdrawAmount <= 0){
            System.out.println("Withdraw amount must be positive");
            return;
        }
        else if (withdrawAmount > availableBalance){
            System.out.println("Withdraw Amount is greater than Available Balance.");
            return;
        }
        else{
            this.availableBalance -= withdrawAmount;
            System.out.printf("Withdraw successful for Account " + this.getAccountNumber() + " with amount %.2f.\n", withdrawAmount);
            System.out.printf("Account New Balance: %.2f\n", this.getAvailableBalance());
        }
    }

    public void displayInformation(){
        System.out.println("Account Information: ===============================");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("Bank Account Holder Name: " + this.getBankAccountHolderName());
        System.out.printf("Available Balance: %.2f\n", this.getAvailableBalance());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankAccountHolderName() {
        return bankAccountHolderName;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }
}
