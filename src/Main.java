import exception.*;
import serviceImpl.AdditionServiceImpl;
import serviceImpl.DivisionServiceImpl;
import serviceImpl.MultiplicationServiceImpl;
import serviceImpl.SubtractionServiceImpl;

import java.util.Scanner;

public class Main {
    static final String[] operation = {"+", "-", "*", "/"};
    static final String regexOperation = "[+\\-*/]";
    private static final AdditionServiceImpl addition = new AdditionServiceImpl();
    private static final SubtractionServiceImpl subtraction = new SubtractionServiceImpl();
    private static final MultiplicationServiceImpl multiplication = new MultiplicationServiceImpl();
    private static final DivisionServiceImpl division = new DivisionServiceImpl();
    private static final Converter converter = new Converter();

    public static void main(String[] args) throws OperationNotFoundException, IncorrectNumberException, WrongExpressionException, NumberSystemException, LessThanZeroException {
        Scanner in = new Scanner(System.in);
        boolean isRoman;
        int firstNumber;
        int secondNumber;

        System.out.print("Input: ");
        String expression = in.nextLine();

        int numberOfOperation = 0;
        for (int i = 0; i < operation.length; i++) {
            if (expression.contains(operation[i])) {
                numberOfOperation = i + 1;
                break;
            }
        }

        if (numberOfOperation == 0)
            throw new OperationNotFoundException("Operation not found");

        String[] expressionElements = expression.split(regexOperation);
        String firstNumberStr = expressionElements[0].trim();
        String secondNumberStr = expressionElements[1].trim();

        if (expressionElements.length != 2)
            throw new WrongExpressionException("Wrong expression");

        if (converter.isRoman(firstNumberStr) && converter.isRoman(secondNumberStr)) {
            isRoman = true;
            firstNumber = converter.romanToArabic(firstNumberStr);
            secondNumber = converter.romanToArabic(secondNumberStr);
        }
        else if (!converter.isRoman(firstNumberStr) && !converter.isRoman(secondNumberStr)) {
            isRoman = false;
            firstNumber = Integer.parseInt(firstNumberStr);
            secondNumber = Integer.parseInt(secondNumberStr);
        }
        else throw new NumberSystemException("Different number systems");
        
        if (firstNumber < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10)
            throw new IncorrectNumberException("Incorrect number");

        int result = 0;
        switch (numberOfOperation) {
            case 1 -> result = addition.calculate(firstNumber, secondNumber);

            case 2 -> result = subtraction.calculate(firstNumber, secondNumber);

            case 3 -> result = multiplication.calculate(firstNumber, secondNumber);

            case 4 -> result = division.calculate(firstNumber, secondNumber);

            default -> {
            }
        }

        if (isRoman && result < 0)
            throw new LessThanZeroException("There are no negative numbers in the roman system");
        else if (isRoman && result > 0)
            System.out.println("Output: " + converter.arabicToRoman(result));
        else
            System.out.println("Output: " + result);
    }
}