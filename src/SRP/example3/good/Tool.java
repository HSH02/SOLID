package SRP.example3.good;

// ✅ SRP 준수: Tool의 기본 동작 정의
abstract public class Tool {
    // ✅ 추상 메서드로 선언하여 각 도구가 자신만의 동작을 구현하도록 함
    abstract public void use();
}

// ✅ SRP 준수: 칼의 동작만 담당
class Knife extends Tool {
    @Override
    public void use() {
        System.out.println("칼로 자릅니다.");  // Knife 클래스는 자르기만 책임짐
    }
}

// ✅ SRP 준수: 드라이버의 동작만 담당
class Screwdriver extends Tool {
    @Override
    public void use() {
        System.out.println("나사를 조입니다.");  // Screwdriver 클래스는 나사 조이기만 책임짐
    }
}

// ✅ SRP 준수: 병따개의 동작만 담당
class BottleOpener extends Tool {
    @Override
    public void use() {
        System.out.println("병을 엽니다.");  // BottleOpener 클래스는 병 열기만 책임짐
    }
}