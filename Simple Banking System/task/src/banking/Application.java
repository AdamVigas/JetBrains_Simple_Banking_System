package banking;


import java.util.Scanner;

public class Application {
    Database data;
    Menu menu = new Menu();

    public Application() {
        // Create database
        this.data = new Database();
        data.createNewDatabase();
        // Set table
        data.createNewTable();
        runningApp();
    }

    public void runningApp() {
        Scanner scan = new Scanner(System.in);
        menu.printMenu();
        int inputMenu = scan.nextInt();
        switch(inputMenu){
            case 1: {
                System.out.println("\nYour card has been created");
                menu.createAcc();
                System.out.println("Your card number: \n" + menu.getAccNumber());
                System.out.println("Your card PIN: \n"+ menu.getPin());
                System.out.println();
                runningApp();
                break;  //optional
            }
            case 2:
                if (menu.login()) {
                    System.out.println("You have successfully logged in!\n");
                    if (menu.loggedIn() == 1){
                        runningApp();
                    }
                }else {
                    System.out.println("\nWrong card number or PIN!\n");
                    runningApp();
                }
                break;
            default:
                break;
        }
    }



}
