package org.example;
import java.util.*;


// Класс для преобразования математического выражения в инфиксной нотации в постфиксную с использованием алгоритма сортировочной станции
public class ExpressionParser {
    private static String operators = "+-*/";

    // Проверка, является ли заданная строка string оператором ( +, -, *, /).
    // Метод перебирает строку operators и сравнивает первый символ string с каждым оператором.
    // Если совпадение найдено, возвращается true; иначе - false.
    public static boolean isOperator(String string) {
        for (int i = 0; i < operators.length(); i++) {
            if (string.charAt(0) == operators.charAt(i))
                return true;
        }
        return false;
    }

    // Определение приоритета оператора
    public static int priority(String string) {
        if (string.equals("+") || string.equals("-"))
            return 1;
        if (string.equals("*") || string.equals("/"))
            return 2;
        return 3;
    }

    // Метод принимает на вход инфиксное выражение и возвращает список строк, представляющих выражение в постфиксной нотации.
    // Входная строка разбивается на отдельные элементы, пробелы используются как разделители. Затем перебирается каждый элемент и выполняются следующие шаги:
    //
    //Если текущий элемент (curr) является оператором, проверяем, является ли он унарным минусом и является ли предыдущий элемент (prev) пустым или разделителем.
    // Если это так, он заменяется строкой "u-", представляющей унарный минус.
    // Иначе сравниваем приоритет curr с операторами в верхней части стека и добавляем их в postfix-список до тех пор,
    // пока не встретится оператор с более низким приоритетом или стек не станет пустым. Затем пушим curr на stack.
    //
    //Если текущий элемент не является оператором, он добавляется в postfix-список.
    //
    //Переменная prev обновляется значением curr для следующей итерации.
    //
    //После обработки всех элементов массива elems проверяем, остались ли в стеке операторы, и добавляем их в postfix-список.
    //
    //В конце, возвращаем список, представляющий исходное инфиксное выражение, преобразованное в постфиксную нотацию.
    public static List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        String[] elems = infix.split("\\s+");
        String prev = "";
        for (String curr : elems) {
            if (curr.isEmpty())
                continue;

            if (isOperator(curr)) {
                if (curr.equals("-") && (prev.isEmpty() || isOperator(prev))) {
                    curr = "u-"; // унарный минус
                } else {
                    while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                        postfix.add(stack.pop());
                    }
                }
                stack.push(curr);
            } else {
                postfix.add(curr);
            }

            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) {
                postfix.add(stack.pop());
            }
            else {
                return postfix;
            }
        }
        return postfix;
    }
}
