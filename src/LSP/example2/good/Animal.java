package LSP.example2.good;

interface Animal {
    void move(int distance);
}

// ✅ LSP 준수
class Dog implements Animal {
    @Override
    public void move(int distance) {
        System.out.println("강아지가 " + distance + "미터를 힘차게 뛰어갑니다!");
    }
}

// ✅ LSP 준수
class Cat implements Animal {
    @Override
    public void move(int distance) {
        System.out.println("고양이가 " + distance + "미터를 살금살금 걸어갑니다.");
    }
}

// ✅ LSP 준수
class Turtle implements Animal {
    @Override
    public void move(int distance) {
        System.out.println("거북이가 " + distance + "미터를 천천히 기어갑니다...");
    }
}

class Zoo {
    public void moveAnimal(Animal animal, int distance) {
        animal.move(distance);
    }
}

// 사용 예시
class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        // 모든 동물이 자신의 방식대로 이동
        Animal cat = new Cat();
        Animal dog = new Dog();
        Animal turtle = new Turtle();

        zoo.moveAnimal(cat, 10);    // "고양이가 10미터를 살금살금 걸어갑니다~"
        zoo.moveAnimal(dog, 10);    // "강아지가 10미터를 힘차게 뛰어갑니다!"
        zoo.moveAnimal(turtle, 10); // "거북이가 10미터를 천천히 기어갑니다..."
    }
}