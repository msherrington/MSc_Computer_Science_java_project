package main.calculator;

import main.fraction.FractionImpl;

import java.util.*;

public class Calculator {

    private static FractionImpl first, second;
    private static String operator;
    private static HashMap<String, String> operators;
    private static HashMap<String, String> actions;

    private static void setFirst(FractionImpl fraction) {
        first = fraction;
    }

    private static void setSecond(FractionImpl fraction) {
        second = fraction;
    }

    private static void setOperator(String string) {
        operator = string;
    }

    private static FractionImpl getFirst() {
        return first;
    }

    private static FractionImpl getSecond() {
        return second;
    }

    private static String getOperator() {
        return operator;
    }

    private static void initialiseHashMaps() {
        operators = new HashMap<>();
        operators.put("+", "Add");
        operators.put("-", "Subtract");
        operators.put("*", "Multiply by");
        operators.put("/", "Divide by");
        operators.put("=", "Compare equality with");
        operators.put("C", "Compare to");

        actions = new HashMap<>();
        actions.put("A", "Absolute value of");
        actions.put("N", "Negate");
        actions.put("I", "Inverse of");
    }

    public static void start() {

        initialiseHashMaps();

        System.out.println("\nWelcome to the Java Fraction Calculator!\n");

        System.out.println("Press Q to Quit at anytime.\n");

        fractionInput("first");

    }

    private static void fractionInput(String identifier) {
        System.out.println(String.format("Please enter the %s fraction:", identifier));
        FractionImpl fraction = null;
        Scanner input = new Scanner(System.in);
        String string;
        do {
            string = input.nextLine().toUpperCase();
            if (string.equals("Q")) {
                break;
            } else {
                try {
                    fraction = new FractionImpl(string);
                } catch (ArithmeticException | NoSuchElementException | NumberFormatException error) {
                    System.out.println(error);
                    System.out.println("Try again or press Q to quit:");
                }
            }
        } while (fraction == null);

        if (!string.equals("Q")) {
            if (identifier == "first") {
                setFirst(fraction);
                operatorInput();
            } else {
                setSecond(fraction);
                calculate();
            }
        } else {
            end();
        }
    }

    private static void operatorInput() {
        System.out.println("Please enter the operator symbol or letter:");
        Scanner input = new Scanner(System.in);
        String selected;

        do {
            for (String key:operators.keySet()) {
                System.out.println(key + " => " + operators.get(key) + " another fraction");
            }
            for (String key:actions.keySet()) {
                System.out.println(key + " => " + actions.get(key) + " the fraction");
            }
            selected = input.nextLine().toUpperCase();
            if (selected.equals("Q")) {
                break;
            }

            if (actions.containsKey(selected)) {
                setOperator(selected);
                calculate();
                break;
            } else if (operators.containsKey(selected)) {
                setOperator(selected);
                fractionInput("second");
                break;
            } else {
                System.out.println("Invalid Option. Try again or press Q to quit:\n");
            }

        } while (!selected.equals("Q"));

        if (selected.equals("Q")) {
            end();
        }
    }

    private static void calculate() {
        switch (getOperator()) {
            case "A":
                System.out.println(String.format("Absolute %s = %s", getFirst(), getFirst().abs().toString()));
                break;
            case "N":
                System.out.println(String.format("Negated %s = %s", getFirst(), getFirst().negate().toString()));
                break;
            case "I":
                System.out.println(String.format("Inverse %s = %s", getFirst(), getFirst().inverse().toString()));
                break;
            case "+":
                System.out.println(String.format("%s + %s = %s", getFirst(), getSecond(), getFirst().add(getSecond()).toString()));
                break;
            case "-":
                System.out.println(String.format("%s - %s = %s", getFirst(), getSecond(), getFirst().subtract(getSecond()).toString()));
                break;
            case "*":
                System.out.println(String.format("%s * %s = %s", getFirst(), getSecond(), getFirst().multiply(getSecond()).toString()));
                break;
            case "/":
                System.out.println(String.format("%s / %s = %s", getFirst(), getSecond(), getFirst().divide(getSecond()).toString()));
                break;
            case "=":
                System.out.println(String.format("%s == %s : %s", getFirst(), getSecond(), getFirst().equals(getSecond())));
                break;
            case "C":
                int comparedTo = getFirst().compareTo(getSecond());
                String comparison = "equal to";
                if (comparedTo > 0) {
                    comparison = "greater than";
                } else if (comparedTo < 0) {
                    comparison = "less than";
                }
                System.out.println(String.format("%s %s %s", getFirst(), comparison, getSecond()));
                break;
            default:
                break;
        }

        runAgainOrQuit();
    }

    private static void runAgainOrQuit() {
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Run the calculator again? Y/N");
            choice = input.nextLine().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N") && !choice.equals("Q"));

        if (choice.equals("Y")) {
            fractionInput("first");
        } else {
            end();
        }
    }

    private static void end() {
        System.out.print("END");
    }

}
