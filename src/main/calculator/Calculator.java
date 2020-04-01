package main.calculator;

import main.fraction.Fraction;
import main.fraction.FractionImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {

    private FractionImpl first, second;
    private String operator;

    private setFirst(FractionImpl fraction) {
        first = fraction;
    }

    private setSecond(FractionImpl fraction) {
        second = fraction;
    }

    private setOperator(String operator) {
        this.operator = operator;
    }

    public static void start() {
        System.out.println("Welcome to the Java Fraction Calculator!\n");

        // ask for first calculation
//        getFirstFraction();

        // operator menu


        Map<String, String> operators = new HashMap<String, String>();
        operators.put("+", "Add");
        operators.put("-", "Subtract");
        operators.put("*", "Multiply by");
        operators.put("/", "Divide by");
        operators.put("=", "Compare equality with");
        operators.put("C", "Compare to");

        Map<String, String> actions = new HashMap<String, String>();
        actions.put("A", "Absolute value of");
        actions.put("N", "Negate");
        actions.put("I", "Inverse of");

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the first fraction:");
        FractionImpl one = new FractionImpl(input.nextLine());
        System.out.println("Please enter the operator symbol or letter:");
        for (String key:operators.keySet()) {
            System.out.println(key + " => " + operators.get(key) + " another fraction");
        }
        for (String key:actions.keySet()) {
            System.out.println(key + " => " + actions.get(key) + " the fraction");
        }
        String operator = input.nextLine().toUpperCase();
        if (actions.containsKey(operator)) {
            switch (operator) {
                case "A":
                    System.out.println(one.abs().toString());
                    break;
                case "N":
                    System.out.println(one.negate().toString());
                    break;
                case "I":
                    System.out.println(one.inverse().toString());
                    break;
            }
        } else if (operators.containsKey(operator)) {
            System.out.println("Please enter the second fraction:");
            FractionImpl two = new FractionImpl(input.nextLine());
            switch (operator) {
                case "+":
                    System.out.println(one.add(two).toString());
                    break;
                case "-":
                    System.out.println(one.subtract(two).toString());
                    break;
                case "*":
                    System.out.println(one.multiply(two).toString());
                    break;
                case "/":
                    System.out.println(one.divide(two).toString());
                    break;
                case "=":
                    System.out.println(one.equals(two));
                    break;
                case "C":
                    System.out.println(one.compareTo(two));
                    break;
            }
        } else {
            System.out.println("no!");
            // invalid operator, try again?
        }

    }

    private static void getFirstFraction() {
        try {
            Scanner inp = new Scanner(System.in);
            one = new FractionImpl(inp.nextLine());
            System.out.println(one.toString());
        } catch (NumberFormatException error) {
            System.out.println(error);
            System.out.println("Try again or press Q to quit");
            Scanner inp = new Scanner(System.in);
            String input = inp.nextLine();
            if (input == "Q")
                return;
            one = new FractionImpl(input);
            System.out.println(one.toString());
        }
    }
}
