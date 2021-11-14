package banking;

import java.util.Arrays;
import java.util.Random;

public class Account {

    protected int[]  cardNumber = new int[16];
    protected String pin;

    public boolean checkLuhn(String cardNo) {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

    public String returnCardNum () {
        String numb = "";
        for(int i = 0; i < cardNumber.length; i++) {
            numb += cardNumber[i];
        }
        return numb;
    }

    public void generateCard() {
        Random rand = new Random();
        for(int j = 0; j < cardNumber.length;j++)
            cardNumber[j] = 0;
        for(int i = 0; i < this.cardNumber.length; i++) {
            if (i == 0) {
                cardNumber[i] = 4;
            }else if (i > 0 && i < 5) {
                cardNumber[i] = 0;
            }else if (i > 5 && i < 15) {
                this.cardNumber[i] = rand.nextInt(10);
            }
        }
        //System.out.println(Arrays.toString(cardNumber));
        luhnAlgo();
    }

    public void luhnAlgo() {
        int sub = 0;
        int luhnNums[] = new int[16];
        for (int k = 0; k < cardNumber.length;k++) {
            luhnNums[k] = cardNumber[k];
        }

        for(int i = 0; i < luhnNums.length; i = i + 2) {
            luhnNums[i] *= 2;
        }

        for(int i = 0; i < luhnNums.length; i++) {
            if (luhnNums[i] > 9) {
                luhnNums[i] -= 9;
            }
        }
        for(int i = 0; i < luhnNums.length; i++) {
            sub += luhnNums[i];
        }
        int result = sub % 10;
        if (result != 0) {
            result = 10 - result;
        }
        cardNumber[cardNumber.length - 1] = result;
        //System.out.println(Arrays.toString(cardNumber));
    }

    public void generatePin () {
        Random rand = new Random();
        int randomPIN = rand.nextInt(9000) + 1000;
        pin =  Integer.toString(randomPIN);
    }

    public String returnPin () {
        return pin;
    }
}

