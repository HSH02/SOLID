# 리스코프 치환 원칙 (Liskov substitution principle)

## 개요

리코스프 치환 원칙은 "하위 클래스는 상위 클래스의 기능을 수행해야 한다." 라는 원칙입니다.

## 설명

상속받은 상위 클래스의 기능을 하위 클래스에서는 모두 구현해야 한다는 의미입니다.


또한 상위 클래스 객체를 사용할 경우 하위 클래스의 객체로 변경해도 문제 없이 작동해야 합니다. 다형성을 생각하시면 이해하기 쉽습니다.

예시를 통해 알아봅시다.

## 예시 1

```java
package LSP.example.bad;

import LSP.example.Coffee;

// 바리스타의 기본 역할
public class Barista {
    public Coffee makeCoffee(){
        return new Coffee("맛있는 커피");
    }

    public boolean checkCoffeeQuality(Coffee coffee){
        return true;        // 기본적으로 품질 체크
    }
}

// 신입 바리스타
// ❌ 바리스타의 역할을 전혀 수행하지 못한다.
class JuniorBarista extends Barista {
    @Override
    public Coffee makeCoffee() {
        throw new RuntimeException("아직 커피 만드는 법을 배우지 않았습니다..");
        // LSP 위반: 부모 클래스의 기본 기능을 수행할 수 없음
    }

    @Override
    public boolean checkCoffeeQuality(Coffee coffee) {
        throw new RuntimeException("권한이 없음");
        // LSP 위반: 부모 클래스의 기본 기능을 수행할 수 없음
    }
}

// ❌ 신입 바리스타가 들어올 경우 카페 운영에 문제가 생길 수 있다.
class Cafe {
    public void serveCustomer(Barista barista) {
        try {
            Coffee coffee = barista.makeCoffee();  // 예외 발생 가능!
            if (barista.checkCoffeeQuality(coffee)) {  // 예외 발생 가능!
                System.out.println("커피 서빙");
            }
        } catch (RuntimeException e) {
            System.out.println("카페 운영에 문제 발생!");
        }
    }
}
```

example1의 나쁜 코드입니다.

카페 운영을 코드로 나타낸 것인데, 신입 바리스타의 경우 부모의 역할을 수행하지 못하고 있으므로 LSP에 위반됩니다.

문제점
- 신입 바리스타가 부모 클래스의 기본 기능을 수행하지 못함
- 예외를 발생시켜 프로그램 실행에 문제를 일으킴
- 카페 운영에 장애가 발생할 수 있음

```java
package LSP.example.good;

import LSP.example.Coffee;

// 커피를 만들 수 있는 기본 자격
interface CoffeeMaker {
    Coffee makeCoffee();
}

// 커피 품질을 체크할 수 있는 자격
interface QualityChecker {
    boolean checkCoffeeQuality(Coffee coffee);
}

// 시니어 바리스타: 커피 제조 + 품질 체크 가능
// ✅ 맡은 역할을 올바르게 수행할 수 있다.
class SeniorBarista implements CoffeeMaker, QualityChecker {
    @Override
    public Coffee makeCoffee() {
        return new Coffee("시니어 바리스타의 커피");
    }

    @Override
    public boolean checkCoffeeQuality(Coffee coffee) {
        return coffee.checkTaste();
    }
}

// 주니어 바리스타: 커피 제조만 가능
// ✅ 맡은 역할을 올바르게 수행할 수 있다.
class JuniorBarista implements CoffeeMaker {
    @Override
    public Coffee makeCoffee() {
        return new Coffee("주니어 바리스타의 커피");  // 기본적인 커피는 만들 수 있음
    }
}

class Cafe {
    // 커피 검사 담당자 (시니어 바리스타 또는 매니저)
    private final QualityChecker manager;

    public Cafe(QualityChecker manager) {
        this.manager = manager;
    }

    // 손님에게 커피 제공하기
    public void serveCustomer(CoffeeMaker barista) {
        // 1. 바리스타가 커피를 만듦
        Coffee coffee = barista.makeCoffee();

        // 2. 품질 검사 담당자가 커피를 체크
        boolean isPassed = manager.checkCoffeeQuality(coffee);

        // 3. 합격한 커피만 손님에게 제공
        if (isPassed) {
            System.out.println("손님 커피 나왔습니다~");
        } else {
            System.out.println("죄송합니다. 커피를 다시 내리겠습니다.");
        }
    }

}
```

맡을 수 있는 역할읋 담당하게만 만든 코드로, 더불어 부모의 기능도 잘 수행할 수 있게 되었습니다.

## 예시 2 

```java
package LSP.example2.bad;

interface Animal {
    void move(int distance);
}

// ❌ LSP 위반: 매개변수 변경
class Dog implements Animal {

    @Override
    public void move(int distance, boolean useLegs) {  // 매개변수 변경
        if (useLegs) {
            System.out.println(distance + "만큼 네 발로 뜁니다");
        } else {
            System.out.println(distance + "만큼 기어갑니다");
        }
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
```

현재 LSP를 위반하고 있는 코드입니다.
Dog 클래스의 경우 매개변수가 변경되었고, Turtle 클래스의 경우 예외를 통해 부모의 기능을 전혀 수행하고 있지 못하고 있습니다.

올바른 코드로 바꿔봅시다.

```java
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
```

매개변수을 변경하지 않고, 예외도 없이 올바르게 부모의 기능을 잘 수행하고 있습니다.

## 정리

- 리스코프 치환 원칙은 하위 클래스는 부모의 기능을 대체할 수 있어야 함을 의미합니다.
- 상속 관계에서 다형성을 올바르게 활용하기 위한 기본 원칙입니다.
- 예시로 부모가 자동차일 경우 자식은 어던 유형의 자동차이든 자동차의 기능을 올바르게 수행해야 합니다.