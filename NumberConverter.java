import java.util.Scanner;

public class NumberConverter {

    // ─────────────────────────────────────────
    //  CONVERSION METHODS
    // ─────────────────────────────────────────

    // Decimal to Binary
    static String decimalToBinary(int n) {
        if (n == 0) return "0";
        StringBuilder result = new StringBuilder();
        int num = Math.abs(n);
        while (num > 0) {
            result.insert(0, num % 2);
            num /= 2;
        }
        return (n < 0 ? "-" : "") + result;
    }

    // Decimal to Octal
    static String decimalToOctal(int n) {
        if (n == 0) return "0";
        StringBuilder result = new StringBuilder();
        int num = Math.abs(n);
        while (num > 0) {
            result.insert(0, num % 8);
            num /= 8;
        }
        return (n < 0 ? "-" : "") + result;
    }

    // Decimal to Hexadecimal
    static String decimalToHex(int n) {
        if (n == 0) return "0";
        char[] hexChars = "0123456789ABCDEF".toCharArray();
        StringBuilder result = new StringBuilder();
        int num = Math.abs(n);
        while (num > 0) {
            result.insert(0, hexChars[num % 16]);
            num /= 16;
        }
        return (n < 0 ? "-" : "") + result;
    }

    // Binary to Decimal
    static int binaryToDecimal(String binary) {
        int result = 0;
        int power = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            char c = binary.charAt(i);
            if (c != '0' && c != '1')
                throw new IllegalArgumentException("Invalid binary digit: " + c);
            result += (c - '0') * (int) Math.pow(2, power++);
        }
        return result;
    }

    // Octal to Decimal
    static int octalToDecimal(String octal) {
        int result = 0;
        int power = 0;
        for (int i = octal.length() - 1; i >= 0; i--) {
            int digit = octal.charAt(i) - '0';
            if (digit < 0 || digit > 7)
                throw new IllegalArgumentException("Invalid octal digit: " + octal.charAt(i));
            result += digit * (int) Math.pow(8, power++);
        }
        return result;
    }

    // Hexadecimal to Decimal
    static int hexToDecimal(String hex) {
        int result = 0;
        int power = 0;
        String upperHex = hex.toUpperCase();
        for (int i = upperHex.length() - 1; i >= 0; i--) {
            char c = upperHex.charAt(i);
            int digit;
            if (c >= '0' && c <= '9')      digit = c - '0';
            else if (c >= 'A' && c <= 'F') digit = c - 'A' + 10;
            else throw new IllegalArgumentException("Invalid hex digit: " + c);
            result += digit * (int) Math.pow(16, power++);
        }
        return result;
    }

    // ─────────────────────────────────────────
    //  DISPLAY HELPERS
    // ─────────────────────────────────────────

    static void printLine() {
        System.out.println("─".repeat(55));
    }

    static void printDoubleLine() {
        System.out.println("═".repeat(55));
    }

    static void showAllConversions(int decimal) {
        System.out.println("  Decimal     :  " + decimal);
        System.out.println("  Binary      :  " + decimalToBinary(decimal));
        System.out.println("  Octal       :  " + decimalToOctal(decimal));
        System.out.println("  Hexadecimal :  " + decimalToHex(decimal));
    }

    // ─────────────────────────────────────────
    //  MENU SYSTEM
    // ─────────────────────────────────────────

    static void showMenu() {
        printDoubleLine();
        System.out.println("      NUMBER SYSTEM CONVERTER");
        printDoubleLine();
        System.out.println("  [1]  Decimal      → Binary, Octal, Hex");
        System.out.println("  [2]  Binary       → Decimal, Octal, Hex");
        System.out.println("  [3]  Octal        → Decimal, Binary, Hex");
        System.out.println("  [4]  Hexadecimal  → Decimal, Binary, Octal");
        System.out.println("  [5]  Run all demo conversions");
        System.out.println("  [0]  Exit");
        printLine();
        System.out.print("  Choose option: ");
    }

    // ─────────────────────────────────────────
    //  DEMO: show a table of values
    // ─────────────────────────────────────────

    static void runDemo() {
        printDoubleLine();
        System.out.println("  DEMO CONVERSION TABLE");
        printDoubleLine();
        System.out.printf("  %-10s %-15s %-10s %-12s%n",
                "Decimal", "Binary", "Octal", "Hexadecimal");
        printLine();
        int[] samples = {0, 1, 2, 7, 8, 10, 15, 16, 31, 32,
                         63, 64, 100, 127, 128, 255, 256, 512, 1000, 4096};
        for (int n : samples) {
            System.out.printf("  %-10d %-15s %-10s %-12s%n",
                    n,
                    decimalToBinary(n),
                    decimalToOctal(n),
                    decimalToHex(n));
        }
        printDoubleLine();
    }

    // ─────────────────────────────────────────
    //  MAIN
    // ─────────────────────────────────────────

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            while (!sc.hasNextInt()) {
                System.out.print("  Invalid. Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();
            System.out.println();

            try {
                switch (choice) {

                    case 1: {
                        System.out.print("  Enter a Decimal number: ");
                        int dec = sc.nextInt();
                        printLine();
                        System.out.println("  Converting " + dec + " from Decimal:");
                        printLine();
                        showAllConversions(dec);
                        printLine();
                        break;
                    }

                    case 2: {
                        System.out.print("  Enter a Binary number (e.g. 1101): ");
                        String bin = sc.next();
                        int dec = binaryToDecimal(bin);
                        printLine();
                        System.out.println("  Converting " + bin + " from Binary:");
                        printLine();
                        showAllConversions(dec);
                        printLine();
                        break;
                    }

                    case 3: {
                        System.out.print("  Enter an Octal number (e.g. 17): ");
                        String oct = sc.next();
                        int dec = octalToDecimal(oct);
                        printLine();
                        System.out.println("  Converting " + oct + " from Octal:");
                        printLine();
                        showAllConversions(dec);
                        printLine();
                        break;
                    }

                    case 4: {
                        System.out.print("  Enter a Hexadecimal number (e.g. 1F or 1f): ");
                        String hex = sc.next();
                        int dec = hexToDecimal(hex);
                        printLine();
                        System.out.println("  Converting " + hex.toUpperCase() + " from Hexadecimal:");
                        printLine();
                        showAllConversions(dec);
                        printLine();
                        break;
                    }

                    case 5:
                        runDemo();
                        break;

                    case 0:
                        System.out.println("  Goodbye!");
                        printDoubleLine();
                        break;

                    default:
                        System.out.println("  Invalid option. Please choose 0–5.");
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("  ERROR: " + e.getMessage());
                printLine();
            } catch (Exception e) {
                System.out.println("  ERROR: Invalid input.");
                printLine();
            }

            System.out.println();

        } while (choice != 0);

        sc.close();
    }
}