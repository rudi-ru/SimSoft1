package helpers;

public class PostCodeGenerator {

    //Генерируем десятизначное число для Post Code
    public static String getPostCode() {
        String postCode = "";
        for (int i = 0; i < 10; i++) {
            postCode = postCode + (int) (Math.random() * 9);
        }
        return postCode;
    }
}
