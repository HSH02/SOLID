package OCP.example2.good;

public interface Animal {
    void makeSound();  // 동물의 소리를 내는 기능
    String getType();  // 동물의 종류를 반환하는 기능
}

// ✅ OCP 준수: 새로운 동물 추가 시 기존 코드를 수정할 필요 없음
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof woof");  // 개는 자신만의 소리를 냄
    }

    @Override
    public String getType() {
        return "I'm dog";  // 개는 자신의 종류를 알림
    }
}

class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");  // 고양이는 자신만의 소리를 냄
    }

    @Override
    public String getType() {
        return "I'm cat";  // 고양이는 자신의 종류를 알림
    }
}

class Cow implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Moo");  // 소는 자신만의 소리를 냄
    }

    @Override
    public String getType() {
        return "I'm cow";  // 소는 자신의 종류를 알림
    }
}