package SRP.example1.bad;

// ❌ SRP 위반: 이 클래스는 너무 많은 일을 한다.
// ❌ 사용자 관리 + 이메일 검증 + 이메일 발송까지 모두 한 클래스에서 처리
public class UserManager {

    // ❌ 책임 1: 사용자 데이터 관리
    public void addUser(String name, String email) {
        System.out.println("사용자 추가: " + name + ", 이메일: " + email);
    }

    public void deleteUser(String email) {
        System.out.println("사용자 삭제: " + email);
    }

    // ❌ 책임 2: 이메일 검증
    public boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    // ❌ 책임 3: 이메일 발송
    public void sendEmail(String email) {
        System.out.println("환영 이메일 전송: " + email);
    }
}