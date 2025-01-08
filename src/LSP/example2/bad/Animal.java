package LSP.example2.bad;

interface Animal {
    void move(int distance);
}

// ❌ LSP 위반: 매개변수 변경
class Dog implements Animal {
    /*
    @Override
    public void move(int distance, boolean useLegs) {  // 매개변수 변경
        if (useLegs) {
            System.out.println(distance + "만큼 네 발로 뜁니다");
        } else {
            System.out.println(distance + "만큼 기어갑니다");
        }
    }
    */

    @Override
    public void move(int distance) {

    }
}

// ❌ LSP 위반: 예외 발생
class Turtle implements Animal {
    @Override
    public void move(int distance) {
        throw new RuntimeException("너무 느려서 못 움직여요");  // 예외 발생
    }
}

class Zoo {
    public void moveAnimal(Animal animal, int distance) {
        // 어떤 동물이 오더라도 문제없이 동작해야 하는데...
        try {
            animal.move(distance);  // Dog는 컴파일 에러, Turtle은 런타임 에러 발생!
        } catch (RuntimeException e) {
            System.out.println("동물이 움직이지 못했습니다: " + e.getMessage());
        }
    }
}

// 사용 예시
class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        Animal dog = new Dog();     // ❌ 컴파일 에러
        Animal turtle = new Turtle(); // ❌ 런타임 에러

        zoo.moveAnimal(dog, 10);     // 컴파일 에러
        zoo.moveAnimal(turtle, 10);  // 런타임 에러
    }
}