import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        checkLoginAndPassword("login", "password", "password");
        checkLoginAndPassword("логин", "password", "password");
        checkLoginAndPassword("login", "пароль", "password");
        checkLoginAndPassword("login", "password", "p@sassword");

    }

    public static void checkLoginAndPassword(String login, String password, String confirmPassword) {
        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Проверка проведена");
        }
    }
    private static void checkLogin(String login) throws WrongLoginException {
        Pattern p = Pattern.compile("^[a-z0-9_-]{1,20}$");
        if (!p.matcher(login).matches()) {
            throw new WrongLoginException("Логин не подходит требованиям: " + login);
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {
        Pattern p = Pattern.compile("^[a-z0-9_-]{1,20}$");
        if (!p.matcher(password).matches()) {
            throw new WrongPasswordException("Пароль не подходит требованиям: " + password);
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");

        }
    }
}