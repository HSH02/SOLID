# 단일 책임 원칙 (Single Responsibility Principle)

## 개요 

단일 책임 원칙은 "한 클래스는 하나의 책임만 가져야 한다" 는 원칙입니다.
 
## 설명

단일 책임 원칙에서의 책임이란 클래스가 "기능, 역할 및 변경을 위한 이유" 라고 부분적으로 정의할 수 있습니다.

1. 변경의 이유
    * 이 클래스가 변경되어야 할 이유가 단 하나여야만 합니다.
2. 하나의 기능
    * 클래스는 하나의 "기능" 또는 "역할"만을 수행해야 합니다.

- 클래스는 단 하나의 기능만 가져야 합니다.
- 하나의 클래스가 여러 책임을 가지면 각 책임이 서로 결합될 수 있습니다.
- 클래스가 여러 책임을 가지면 다른 책임에도 영향을 미쳐 유지 보수나 추가 기능 작성 시 문제가 발생할 수 있음 

예시를 통해 알아 봅시다.

## 예시 1 

``` java
// 나쁜 예시 - 책임이 너무 많은 클래스 A ❌
class UserOrderService {  // 클래스 A
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    // ❌ 책임 1 : 사용자 관련 책임
    public void addUser(User user) {
        users.add(user);
        sendEmail(user.getEmail(), "환영합니다!");
    }
    
    // ❌ 책임 2 :주문 관련 책임
    public void createOrder(Long userId, String item) {
        User user = findUser(userId);
        orders.add(new Order(user, item));
        updateStock(item);
    }
    
    // ❌ 책임 3 :이메일 발송 책임
    private void sendEmail(String email, String message) {
        // 이메일 발송 로직
    }
}
```

```java
// 클래스 B - A와 주문 처리 책임이 중복됨 ❌
class OrderManager {  // 클래스 B
    private List<Order> orders = new ArrayList<>();

    public void processOrder(User user, String item) {
        orders.add(new Order(user, item));
        updateStock(item);  // A와 동일한 재고 관리 로직
    }
}
```

위 예시 코드인 A는 3가지의 변경의 이유를 가지고 있습니다.
* 사용자 관련 책임
* 주문 관련 책임
* 이메일 발송 책임 

만약 사용자 관련 책임을 수정해야 할 경우, 클래스 B에도 책임이 존재하므로 A, B 둘다 수정해야 하는 문제가 발생합니다.

이럴 경우 각 책임에 맞게 분리하는 것이 좋습니다.

## 예시 2


```java
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
```

example1의 bad code 입니다.

현재 여러 책임을 가지고 있으므로 변경의 이유가 3가지가 됩니다.
* 사용자 데이터 관리의 수정이 필요할 때 변경해야 한다.
* 이메일 검증 로직의 수정이 필요할 때 변경해야 한다.
* 이메일 발송 로직의 수정이 필요할 때 변경해야 한다.

이럴 때는 각 변경의 이유를 분리하여 단일 책임을 가지게 변경합니다.

```java
package SRP.example1.good.service;

// 이메일 전송
public class EmailService {

    public void sendWelcomeEmail(String email) {
        System.out.println("환영 이메일 전송: " + email);
    }

}
```

```java
package SRP.example1.good.service;

// 이메일 전송
public class EmailService {

    public void sendWelcomeEmail(String email) {
        System.out.println("환영 이메일 전송: " + email);
    }

}

```

```java
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
```

이제는 각 책임이 분리되었으므로, 유지 보수 및 확장이 쉬워졌습니다.

## 예시 3

```java
package SRP.example2.bad;

// ❌ SRP 위반: Animal 클래스가 두 가지 책임을 가지고 있음
// 1. 동물의 종류 관리
// 2. 동물의 울음소리 출력
public class Animal {
    private String animal;

    // ❌ 새로운 동물이 추가될 때마다 이 메서드를 수정해야 함
    public void cry() {
        switch (animal) {
            case "cat":
                System.out.println("cat");
                break;
            case "dog":
                System.out.println("dog");
                break;
            case "horse":
                System.out.println("horse");
                break;
            default:
                break;
        }
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
```
example2의 bad code 입니다.

동물이 새로 추가될때마다 로직에 코드를 추가해야 하며 Animal 클래스가 종류 및 울음소리 출력 관리르 맡고 있습니다.

```java
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
```

각 책임에 맞게 분리하여 작성하였습니다.

Animal 클래스는 동물의 행동만 정의하게 되었으며, 하나의 책임만 가지게 되었습니다.


## 정리 

- 단일 책임 원칙은 한 클래스가 하나의 책임만을 가져야 함을 의미합니다.
- 책임의 다른 말은 변경의 이유로써, "변경의 이유"가 하나여야 합니다.
- 책임이 분리되면 확장과 유지보수가 용이해집니다.
- 클래스의 책임이 많아지면 다른 클래스와 결합도가 높아질 수 있습니다.



