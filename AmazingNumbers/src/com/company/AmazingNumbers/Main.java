package com.company.AmazingNumbers;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringJoiner;


public class Main {
    public static final Scanner scan = new Scanner(System.in);
    static boolean check = true;
    static String n = "102 5";
    static String [] temp = n.split(" ");
    static String [] properties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "EVEN" , "ODD", "JUMPING", "HAPPY", "SAD"};
    public static boolean isBuzz(long n) {
        return n % 7 == 0 | n % 10 == 7;
    }
    public static boolean isOdd(long n) {return (n & 1) == 1;}
    public static boolean isEven(long n) {return !isOdd(n);}
    public static boolean isDuck(long n) {
        while(n != 0) {
            if(n % 10 == 0 ) {
                return true;
            }
            n /= 10 ;
        }
        return false;
    }
    public static boolean isGapful(long n) {
        long firstDigit = n % 10;
        long temp = n;
        long secondDigit = 0;
        int c = 0;
        while(n != 0) {
            secondDigit = n;
            c++;
            n /= 10;
        }
        String s1 = String.valueOf(firstDigit);
        String s2 = String.valueOf(secondDigit);
        String res = s2 + s1;
        return  c >= 3 & temp % Long.parseLong(res) == 0;
    }
    public static boolean isPalindromic(long n) {
        long temp = n;
        long r , sum = 0;
        while( n > 0){
            r = n % 10;
            sum =(sum * 10) + r;
            n /= 10;
        }
        return sum == temp;
    }
    public static boolean isSpy(long n) {
        long sum = 0, multi = 1;

        while (n != 0) {
            sum += n % 10;
            multi *= n % 10;
            n /= 10;
        }
        return sum == multi;
    }
    public static boolean isJumping(long n) {
            boolean f = true;
            long res;
            while (n != 0) {
                res = n % 10;
                n /= 10;
                if (n == 0) {
                    break;
                }
                res -= n % 10;
                if (Math.abs(res) != 1) {
                    f = false;
                    break;
                }
            }
            return f;
    }
    public static boolean isSquare(long n) {
        long res = (long) Math.floor(Math.sqrt(n));
        return res * res == n;
    }
    public static boolean isSunny(long n) {
        return isSquare(n + 1);
    }
    public static boolean isHappy(long n) {
        if (n == 1 | n == 7)
            return true;
        else if (n == 4) {
            return false;
        }
        long sum = n;

        while(sum > 9) {
            sum = 0;

            while (n > 0) {
                sum += (long) Math.pow(n % 10, 2);
                n /= 10;
            }
            if (sum == 1)
                return true;
            n = sum;
        }
        if(sum == 7)
            return true;
        return false;
    }
    public static boolean isSad (long n) {
        return !isHappy(n);
    }

    private static void instructions() {
        System.out.println("""

                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameters show how many consecutive numbers are to be processed;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

    public static void main(String[] args) {
//        checksNumbers();
        System.out.println("\nGoodbye!");
    }

    private static void checksNumbers() {
        System.out.println("Welcome to Amazing Numbers!");
        int f = 0;
        String mut1, mut2, mut3, mut4;
        mut1 = mut2 = mut3 = mut4 ="";
        StringJoiner wrongAns = new StringJoiner(", ");
        instructions();
        while (check) {
            System.out.print("Enter a request: ");
            n = "";
            n = scan.nextLine().trim();
            temp = n.split(" ");

            if (n.isEmpty()) {
                instructions();
                continue;
            }

            if (!(n.matches("[0-9]+")) & temp.length == 1) {
                System.out.println("\nThe first parameter should be a natural number or zero.\n");
                continue;
            }
            if (n.equals("0")) {
                break;
            }
            if (temp.length == 2) {
                if (!(temp[0].matches("[0-9]+"))) {
                    System.out.println("\nThe first parameter should be a natural number or zero.\n");
                    continue;
                }
                if (!(temp[1].matches("[0-9]+"))) {
                    System.out.println("\nThe second parameter should be a natural number.\n");
                    continue;
                }
            }


            stop:for (int i = 2; i < temp.length; i++) {

                if (!(temp[0].matches("[0-9]+"))) {
                    System.out.println("\nThe first parameter should be a natural number or zero.\n");
                    break stop;
                }
                if (!(temp[1].matches("[0-9]+"))) {
                    System.out.println("\nThe second parameter should be a natural number.\n");
                    break stop;
                }
                if (notInProperities(temp[i])) {
                    wrongAns.add(temp[i].toUpperCase());
                    f++;
                }

                if (temp[i].equalsIgnoreCase("even")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("odd")) {
                            mut1 = temp[j];
                            break;
                        }
                    }

                }
                if (temp[i].equalsIgnoreCase("odd")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("even")) {
                            mut1 = temp[j];
                            break;
                        }
                    }

                }

                if (temp[i].equalsIgnoreCase("sunny")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("square")) {
                            mut2 = temp[j];
                            break;
                        }
                    }


                }

                if (temp[i].equalsIgnoreCase("square")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("sunny")) {
                            mut2 = temp[j];
                            break;
                        }
                    }

                }

                if (temp[i].equalsIgnoreCase("spy")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("duck")) {
                            mut3 = temp[j];
                            break;
                        }
                    }

                }

                if (temp[i].equalsIgnoreCase("duck")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("spy")) {
                            mut3 = temp[j];
                            break;
                        }
                    }
                }

                if (temp[i].equalsIgnoreCase("happy")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("sad")) {
                            mut4 = temp[j];
                            break;
                        }
                    }
                }

                if (temp[i].equalsIgnoreCase("sad")) {
                    for (int j = i; j < temp.length; j++) {
                        if (temp[j].equalsIgnoreCase("happy")) {
                            mut4 = temp[j];
                            break;
                        }
                    }
                }

            }
            if (f == 1) {
                System.out.println("The property [" + wrongAns + "] is wrong.\n" +
                                           "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                wrongAns = new StringJoiner(",");
                f = 0;
                continue;
            }
            if (f > 1) {
                System.out.println("The properties [" + wrongAns + "] are wrong.\n" +
                                           "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                wrongAns = new StringJoiner(",");
                f = 0;
                continue;
            }
            if (!mut1.isEmpty()) {
                if (mut1.equalsIgnoreCase("odd")) {
                    System.out.println("The request contains mutually exclusive properties: [EVEN, "+ mut1.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");

                } else {
                    System.out.println("The request contains mutually exclusive properties: [ODD, "+ mut1.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");
                }
                continue;
            }

            if (!mut2.isEmpty()) {
                if (mut2.equalsIgnoreCase("sunny")) {
                    System.out.println("The request contains mutually exclusive properties: [SQUARE, " + mut2.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");
                } else {
                    System.out.println("The request contains mutually exclusive properties: [SUNNY, "+ mut2.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");
                }
                continue;
            }

            if (!mut3.isEmpty()) {
                if (mut3.equalsIgnoreCase("spy")) {
                    System.out.println("The request contains mutually exclusive properties: [DUCK, " + mut3.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");
                } else {
                    System.out.println("The request contains mutually exclusive properties: [SPY, "+ mut3.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");
                }
                continue;
            }

            if (!mut4.isEmpty()) {
                if (mut3.equalsIgnoreCase("happy")) {
                    System.out.println("The request contains mutually exclusive properties: [SAD, " + mut4.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");
                } else {
                    System.out.println("The request contains mutually exclusive properties: [HAPPY, "+ mut4.toUpperCase() +"]\n" +
                                               "There are no numbers with these properties.");
                }
                continue;
            }


            switch (temp.length) {
                case 1 -> justOneNumber(temp[0]);
                case 2 -> justTwoNumber(temp[0], temp[1]);
                default -> allPros(temp[0], temp[1], temp);
            }
        }
    }

    private static void allPros(String s, String s1, String[] temp) {
        long n = Long.parseLong(s);
        long t = Long.parseLong(s1);
        int [] index = new int [temp.length - 2];
        int fi = 0;
        for (int i = 2; i < temp.length; i++) {
                if (temp[i].startsWith("-")) {
                    continue;
                }
                for (int j = 0; j < properties.length; j++) {
                    if (properties[j].equalsIgnoreCase(temp[i])) {
                        index[fi++] = j;
                        break;
                    }
                }
        }

        NumberFormat format = NumberFormat.getInstance(Locale.US);


        boolean ch = false;
        while (t > 0) {
            boolean[] kinds = {isBuzz(n), isDuck(n), isPalindromic(n), isGapful(n), isSpy(n), isSquare(n), isSunny(n), isEven(n), isOdd(n), isJumping(n)};
            for (int j : index) {
                if (kinds[j]) {
                    ch = true;
                } else {
                    ch = false;
                    break;
                }
            }

               if (ch) {
                   StringJoiner str = new StringJoiner(", ");
                   System.out.print("\t         "+format.format(n) + " is ");

                   for (int i = 0; i < kinds.length; i++) {
                       if (kinds[i]) {
                           str.add(properties[i].toLowerCase());
                       }
                   }
                   System.out.println(str);
                   t--;
               }
            n++;
        }
    }

    private static boolean notInProperities(String s) {

        for (String t : properties) {
            if (t.equalsIgnoreCase(s)) {
                return false;
            }
        }
        return true;
    }


    private static void justTwoNumber(String s, String s1) {
        long num = Long.parseLong(s);
        long temp = num;
        long size = Long.parseLong(s1);
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        System.out.println();
        for (int i = 0; i < size; i++) {
            temp += i;
            StringJoiner myString = new StringJoiner(",");
            System.out.print("\t         "+format.format(temp) + " is");
            if (isBuzz(temp)) {
                myString.add(" buzz");
            }
            if (isDuck(temp)) {
                myString.add(" duck");
            }
            if (isPalindromic(temp)) {
                myString.add(" isPalindromic");
            }
            if (isGapful(temp)) {
                myString.add(" gapful");
            }
            if (isSpy(temp)) {
                myString.add(" spy");
            }
            if (isSquare(temp)) {
                myString.add(" square");
            }
            if (isSunny(temp)) {
                myString.add(" sunny");
            }
            if (isJumping(temp)) {
                myString.add(" jumping");
            }
            if (isEven(temp)) {
                myString.add(" even");
            }
            if (isOdd(temp)) {
                myString.add(" odd");
            }
            temp = num;
            System.out.println(myString);

        }
    }

    private static void justOneNumber(String s) {
        long num = Long.parseLong(s);
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        System.out.println("Properties of " + format.format(num) + "\n"
                                   + String.format("%12s: %b\n", "buzz", isBuzz(num))
                                   + String.format("%12s: %b\n", "duck", isDuck(num))
                                   + String.format("%12s: %b\n", "Palindromic", isPalindromic(num))
                                   + String.format("%12s: %b\n", "gapful", isGapful(num))
                                   + String.format("%12s: %b\n", "spy", isSpy(num))
                                   + String.format("%12s: %b\n", "square", isSquare(num))
                                   + String.format("%12s: %b\n", "sunny", isSunny(num))
                                   + String.format("%12s: %b\n", "jumping", isJumping(num))
                                   + String.format("%12s: %b\n", "even", isEven(num))
                                   + String.format("%12s: %b", "odd", isOdd(num))
        );
    }
}
