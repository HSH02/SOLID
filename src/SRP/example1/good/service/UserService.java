package SRP.example1.good.service;

// 사용자 데이터 관리
public class UserService {

    public void addUser(String name, String email) {
        System.out.println("사용자 추가: " + name + ", 이메일: " + email);
    }

    public void deleteUser(String email) {
        System.out.println("사용자 삭제: " + email);
    }

}
