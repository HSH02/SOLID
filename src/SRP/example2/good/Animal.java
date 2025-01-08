package SRP.example2.good;

// ✅ SRP 준수: 동물의 기본 행동만 정의
abstract public class Animal {
    // ✅ 추상 메서드로 선언하여 각 동물이 자신의 울음소리를 구현하도록 함
    abstract public void cry();
}

// ✅ SRP 준수: 개의 행동만 담당
class Dog extends Animal {
    @Override
    public void cry() {
        System.out.println("멍멍!");  // Dog 클래스는 개의 울음소리만 책임짐
    }
}

// ✅ SRP 준수: 고양이의 행동만 담당
class Cat extends Animal {
    @Override
    public void cry() {
        System.out.println("야옹~");  // Cat 클래스는 고양이의 울음소리만 책임짐
    }
}

// ✅ SRP 준수: 말의 행동만 담당
class Horse extends Animal {
    @Override
    public void cry() {
        System.out.println("히힝~");  // Horse 클래스는 말의 울음소리만 책임짐
    }
}