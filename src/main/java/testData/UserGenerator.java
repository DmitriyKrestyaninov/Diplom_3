package testData;

public class UserGenerator {
    public static User getDeffault() {
        return new User("alex_kokorini@yandex.ru", "mamaevpasha", "Cristiano");
    }
    public static User getUserWithIncorrectPassword(){
        return new User("ronaldinho@yandex.ru", "124", "Brazil");
    }
}
