package helpers;

public class FirstNameGenerator {

    // Генерируем First Name на основе Post Code
    public static String getFirstName(String postCode) {
        String[] dict = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int index;
        String firstName = "";
        if (postCode != null || postCode.length() == 10) {
            char[] chars = postCode.toCharArray();

            for (int i = 0; i < postCode.length() - 1; i += 2) {
                int num = Integer.parseInt(String.valueOf(chars[i]) + chars[i + 1]);
                if (num > 25) {
                    index = num - (int) (Math.floor(num / 26) * 26);
                } else index = num;

                firstName = firstName + dict[index];
            }
            return firstName;
        } else return null;
    }
}