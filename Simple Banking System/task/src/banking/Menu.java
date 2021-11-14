package banking;

import java.util.Scanner;

public class Menu {
    SelectApp selectApp = new SelectApp();
    InsertApp insertApp = new InsertApp();
    UpdateApp updateApp = new UpdateApp();
    DeleteApp deleteApp = new DeleteApp();
    TransactionApp transactionApp = new TransactionApp();
    Account account = new Account();
    String enterNumber;
    String enterPin;

    public String getAccNumber () {
        return account.returnCardNum();
    }

    public String getPin() {
        return account.returnPin();
    }

    public void createAcc() {
        this.account.generateCard();
        this.account.generatePin();
        this.insertApp.insert(account.returnCardNum(),account.returnPin());
    }

    public boolean login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your card number:");
        this.enterNumber = scan.next();
        System.out.println("Enter your PIN:");
        this.enterPin = scan.next();
        System.out.println();
        if (selectApp.selectNumPin(enterNumber,enterPin)) {
            return true;
        }else return false;
    }

    public void printMenu () {
        System.out.println("1. Create account\n2. Log into account\n0. Exit");
    }

    public void loggedMenu () {
        System.out.println("1. Balance\n2. Add income\n3. Do transfer\n4. Close account\n5. Log out\n0. Exit\n");
    }

    public int loggedIn() {
        Scanner scan = new Scanner(System.in);
        loggedMenu();
        int loggedInput = scan.nextInt();
        switch(loggedInput){
            case 1:
                System.out.println("Balance : " + selectApp.getBalance(this.enterNumber) + "\n");
                loggedIn();
                break;
            case 2:{
                System.out.println("Enter income: ");
                int incomeEnter = scan.nextInt();
                updateApp.update(incomeEnter,this.enterNumber);
                System.out.println("Income was added!\n");
                loggedIn();
                break;
            }
            case 3:{
                System.out.println("\nTransfer");
                System.out.println("Enter card number:");
                String receiver = scan.next();
                    if (account.checkLuhn(receiver)){
                        if (selectApp.findCard(receiver)) {
                        if (!receiver.equals(this.enterNumber)){
                            System.out.println("Enter how much money you want to transfer:");
                            int money = scan.nextInt();
                            if (money > selectApp.getBalance(this.enterNumber)) {
                                System.out.println("Not enough money!\n");
                            }
                            else {
                                transactionApp.performTransaction(this.enterNumber, receiver,money);
                                System.out.println("Sucess\n");
                            }
                            loggedIn();
                            break;
                        } else {
                            System.out.println("You can't transfer money to the same account!\n");
                            loggedIn();
                            break;
                        }
                            }else {
                            System.out.println("Such a card does not exist.");
                            loggedIn();
                            break;
                        }
                    }else {
                        System.out.println("\nProbably you made a mistake in the card number. Please try again!");
                        loggedIn();
                        break;
                    }
            }
            case 4:
            {
                deleteApp.delete(this.enterNumber);
                System.out.println("The account has been closed!");
                return 1;
            }
            case 5:
                return 1;
            default:
                break;
        }
        return 2;
    }

}
