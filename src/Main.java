import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с " +
                "двумя числами (римскими и арабскими), пожалуйста введите операцию:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Результат вашей операции: \n" + calc(input));
    }

    public static String calc(String input) {
        int number1;
        int number2;
        String result;
        int sum;

        String[] operands = input.split("[+\\-*/]");
        if (check(input) == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException("строка не является математической операцией");
            }
        }

        if (operands.length != 2) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        }

        if (RomanNumbers.getRoman(operands[0]) && RomanNumbers.getRoman(operands[1])) {
            number1 = RomanNumbers.getArabic(operands[0]);
            number2 = RomanNumbers.getArabic(operands[1]);
            if (operation(number1, number2, input) == 0) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new RuntimeException("римское число должно быть больше нуля");
                }
            }
            sum = operation(number1, number2, input);
            result = RomanNumbers.getRoman1(sum);
        } else if (!RomanNumbers.getRoman(operands[0]) && !RomanNumbers.getRoman(operands[1])) {
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            result = String.valueOf(operation(number1, number2, input));
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException("используются одновременно разные системы счисления");
            }
        }

        if (number1 > 10 || number2 > 10) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException("калькулятор должен принимать на вход числа от 1 до 10 включительно, не более");
            }
        }
        return result;
    }

    static int operation(int a, int b, String input) {
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '+' -> {
                    return (a + b);
                }
                case '-' -> {
                    return (a - b);
                }
                case '*' -> {
                    return (a * b);
                }
                case '/' -> {
                    try {
                        return (a / b);
                    } catch (Exception e) {
                        throw new RuntimeException("на ноль делить нельзя");
                    }
                }
            }
        }
        return 0;
    }
    static String check (String input) {
        if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
            return " ";
        }
        return null;
    }
}
class RomanNumbers {
    static String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
            "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
            "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
            "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
            "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
            "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
            "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
            "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    static boolean getRoman(String input) {
        for (int i = 0; i < roman.length; i++) {
            if (input.equals(roman[i])) {
                return true;
            }
        }
        return false;
    }

    static String getRoman1(int input) {
        try {
            return roman[input];
        } catch (Exception e) {
            throw new RuntimeException("в римской системе нет отрицательных чисел");
        }
    }

    static int getArabic(String input) {
        if (input.equals("I")) {
            return 1;
        } else if (input.equals("II")) {
            return 2;
        } else if (input.equals("III")) {
            return 3;
        } else if (input.equals("IV")) {
            return 4;
        } else if (input.equals("V")) {
            return 5;
        } else if (input.equals("VI")) {
            return 6;
        } else if (input.equals("VII")) {
            return 7;
        } else if (input.equals("VIII")) {
            return 8;
        } else if (input.equals("IX")) {
            return 9;
        } else if (input.equals("X")) {
            return 10;
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException("калькулятор должен принимать на вход числа от I до X включительно, не более");
        }
    }
}