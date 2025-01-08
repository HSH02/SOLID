package SRP.example1.good.validator;

// 데이터 검증
public class EmailValidator {

    public boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

}
