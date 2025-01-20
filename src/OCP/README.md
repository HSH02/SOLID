# 개방 폐쇄 원칙 (Open/closed principle)

## 개요 

개방 폐쇄 원칙은 "변경에는 닫혀있되 확장에는 열려있어야 한다" 라는 원칙입니다.

## 설명

변경에는 닫혀있으나 확장에는 열려있어야 한다는 의미는, 기존 코드를 변경하지 않고도 시스템의 동작을 확장할 수 있어야 함을 의미합니다.

예시를 통해 알아봅시다.

## 예시 1

```java
package OCP.example1.bad;

class Shape {
    String type;
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.type = "circle";
        this.radius = radius;
    }
}

class Rectangle extends Shape {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.type = "rectangle";
        this.width = width;
        this.height = height;
    }
}

// ❌ OCP 위반: 새로운 도형이 추가될 때마다 이 클래스를 수정해야 함
class AreaCalculator {
    public double calculateArea(Shape shape) {
        if (shape.type.equals("circle")) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        } else if (shape.type.equals("rectangle")) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.width * rectangle.height;
        }
        return 0;
    }
}
```

example1의 나쁜 코드입니다.

만약 새 도형이 추가 될 경우마다 새롭게 코드를 수정해야 합니다.

현재 이것은 개방 폐쇄 원칙에 어긋나므로, 의도에 맞게 수정해봅시다.

```java
package OCP.example1.good;

// ✅ OCP 준수: 도형들의 공통 기능을 인터페이스로 정의
interface Shape {
    double calculateArea();
}

// ✅ OCP 준수: 원은 자신의 면적 계산 로직을 구현
class Circle implements Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// ✅ OCP 준수: 직사각형은 자신의 면적 계산 로직을 구현
class Rectangle implements Shape {
    private double width;
    private double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

// ✅ OCP 준수: 새로운 도형 추가가 자유로움
class Triangle implements Shape {
    private double base;
    private double height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

// ❌ 이전의 나쁜 코드 (참고용)
/*
class AreaCalculator {
   public double calculateArea(Shape shape) {
       if (shape.type.equals("circle")) {           // 타입 체크
           Circle circle = (Circle) shape;          // 타입 캐스팅
           return Math.PI * circle.radius * circle.radius;
       } else if (shape.type.equals("rectangle")) { // 타입 체크
           Rectangle rectangle = (Rectangle) shape;  // 타입 캐스팅
           return rectangle.width * rectangle.height;
       }
       return 0;
   }
}
*/
```

이제는 개방 폐쇄 원칙에 잘 따르고 있으며, 만약 도형이 새로 추가되어도 기존 코드를 수정해야 할 필요가 없어졌습니다.


## 예시 2

```java
package OCP.example2.bad;

public class Animal {
    private String type;

    public Animal(String type) {
        this.type = type;
    }

    // ❌ OCP 위반: 새로운 동물이 추가될 때마다 이 메서드를 수정해야 함
    public void makeSound() {
        if (type.equals("dog")) {
            System.out.println("Woof Woof");
        } else if (type.equals("cat")) {
            System.out.println("Meow");
        } else if (type.equals("cow")) {
            System.out.println("Moo");
        } else {
            System.out.println("Unknown animal sound");
        }
    }

    public String getType() {
        return type;
    }
}
```

example2의 나쁜 코드입니다.

이 코드 또한 새로 동물이 추가될 떄마다 확장이 아닌 변경이 필요한 코드로, 개방 폐쇄 원칙에 어긋납니다.

```java
package OCP.example2.good;

// ✅ OCP 준수: 새로운 동물 추가 시 기존 코드를 수정할 필요 없음
public interface Animal {
    void makeSound();  // 동물의 소리를 내는 기능
    String getType();  // 동물의 종류를 반환하는 기능
}

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
```

이제는 기존 코드를 수정하지 않고도 동물이 추가될 때마다 확장할 수 있는 코드가 되었습니다.

## 예시 3

```java
package OCP.example3.bad;

// ❌ OCP 위반: 새로운 결제 수단이 추가될 때마다 이 클래스를 수정해야 함
public class PaymentService {
    public void processPayment(String type) {
        if (type.equals("credit")) {
            System.out.println("신용카드로 결제를 진행합니다.");
        } else if (type.equals("kakao")) {
            System.out.println("카카오페이로 결제를 진행합니다.");
        }
        // ❌ 새로운 결제 수단(예: 네이버페이, 토스 등) 추가시 여기에 계속 코드를 추가해야 함
    }
}
```

example3의 나쁜 코드입니다.

이 로직대로라면 새로운 결제 수단이 추가될 때마다 기존 코드를 수정해야 합니다.

```java
package OCP.example3.good;

// ✅ OCP 준수: 새로 카드를 추가하더라도 기존 코드를 수정하지 않아도 됌
public interface PaymentProcessor {
    void process();  // 결제 처리 메서드
}

// ✅ OCP 준수: 신한카드 결제 처리는 자신만의 로직 구현
class ShinhanCardProcessor implements PaymentProcessor {
    @Override
    public void process() {
        System.out.println("신한카드 결제를 진행합니다.");
        System.out.println("1. 카드 정보 검증");
        System.out.println("2. 금액 승인 요청");
        System.out.println("3. 결제 완료");
    }
}

// ✅ OCP 준수: 카카오페이 결제 처리는 자신만의 로직 구현
class KakaoPayProcessor implements PaymentProcessor {
    @Override
    public void process() {
        System.out.println("카카오페이 결제를 진행합니다.");
        System.out.println("1. QR코드 생성");
        System.out.println("2. 사용자 인증");
        System.out.println("3. 결제 완료");
    }
}
```

이제는 새 결제 수단이 추가되어도 새 코드만 추가하면 되었습니다.

## 정리

- 개방 폐쇄 원칙은 변경에는 닫혀있으며, 확장에는 열려있어야 함을 의미합니다.
- 변경은 기존 코드를 수정하지 않음을 의미합니다.
- 확장은 새로운 코드를 추가하는 것을 의미합니다.

