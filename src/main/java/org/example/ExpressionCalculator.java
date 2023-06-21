package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// Класс для вычисления выражения, заданного в постфиксной нотации
class ExpressionCalculator {

    // Метод берет список строк, представляющих выражение в постфиксной нотации, и выполняет вычисление. Он использует стек для хранения промежуточных результатов.
    public static Double calculate(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<>();

        for (String string : postfix) {
            switch (string) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a / b);
                }
                case "u-" -> stack.push(-stack.pop());
                default -> stack.push(Double.valueOf(string));
            }
        }
        return stack.pop();
    }
}