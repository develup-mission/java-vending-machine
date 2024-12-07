package vendingmachine;

public class StringUtils {

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }
}
