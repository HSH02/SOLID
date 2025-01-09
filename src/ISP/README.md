# 인터페이스 분리 원칙 (Interface segregation principle)

## 개요

인터페이스 분리 원칙이란 "특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다." 라는 원칙입니다.

## 설명

범용 인터페이스를 통해 클라이언트를 관리하기 보단, 인터페이스를 나누어 관리하는 것이 좋다라는 의미입니다.

예시를 통해 알아봅시다.

## 예시 1

```java
package ISP.example.bad;

// ❌ ISP 위반: 모든 유닛의 기능을 하나의 인터페이스에 때려넣음
interface Unit {
    void attack();      // 공격
    void move();        // 이동
    void fly();         // 비행
    void heal();        // 치료
    void burrow();      // 지하이동
    void cloak();       // 은신
}

// ❌ 마린은 날거나, 치료하거나, 은신할 수 없는데 구현해야 함
class Marine implements Unit {
    @Override
    public void attack() {
        System.out.println("마린 공격");
    }

    @Override
    public void move() {
        System.out.println("마린 이동");
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException("마린은 날 수 없습니다");
    }

    @Override
    public void heal() {
        throw new UnsupportedOperationException("마린은 치료할 수 없습니다");
    }

    @Override
    public void burrow() {
        throw new UnsupportedOperationException("마린은 땅속으로 들어갈 수 없습니다");
    }

    @Override
    public void cloak() {
        throw new UnsupportedOperationException("마린은 은신할 수 없습니다");
    }
}
```

example1의 나쁜 코드입니다.

특정 게임의 유닛을 코드로 나타낸 코드로, Unit 클래스를 통해 기능을 구현하고 있습니다.

그러나 인터페이스 특징상 전혀 불필요한 기능까지 의무로 구현해야 되므로, 마린이 날거나 치료할 수 없음에도 예외를 통해 처리해야 합니다.

그렇기에 리코스프 치환 원칙까지 위배하고 있습니다.

이제 올바르게 바꿔봅시다. 

```java
package ISP.example.good;

// ✅ ISP 준수: 기능별로 인터페이스 분리
interface Unit {
    void move();  // 모든 유닛의 기본 기능
}

interface Attackable {
    void attack();
}

interface Flyable {
    void fly();
}

interface Healable {
    void heal();
}

interface Cloakable {
    void cloak();
}

// ✅ 마린은 필요한 기능만 구현
class Marine implements Unit, Attackable {
    @Override
    public void move() {
        System.out.println("마린 이동");
    }

    @Override
    public void attack() {
        System.out.println("마린 공격");
    }
}

// ✅ 메딕은 필요한 기능만 구현
class Medic implements Unit, Healable {
    @Override
    public void move() {
        System.out.println("메딕 이동");
    }

    @Override
    public void heal() {
        System.out.println("메딕 치료");
    }
}

// ✅ 레이스는 필요한 기능만 구현
class Wraith implements Unit, Attackable, Flyable, Cloakable {
    @Override
    public void move() {
        System.out.println("레이스 이동");
    }

    @Override
    public void attack() {
        System.out.println("레이스 공격");
    }

    @Override
    public void fly() {
        System.out.println("레이스 비행");
    }

    @Override
    public void cloak() {
        System.out.println("레이스 은신");
    }
}
```

범용 인터페이스를 각 인터페이스로 나눔으로써 올바른 기능만을 부여받을 수 있게 되었습니다.

마린, 메딕, 레이스는 이제 필요한 기능만을 부여받고, 불필요한 기능은 구현하지 않아도 되게 설계되었습니다.

## 예시 2

```java
package ISP.example2.bad;

// ❌ ISP 위반: 모든 유닛의 행동을 하나의 거대한 인터페이스로 정의
interface Unit {
    void move();          // 이동
    void rangedAttack();  // 원거리 공격
    void meleeAttack();   // 근접 공격
    void heal();          // 치료
    void buildCity();     // 도시 건설
    void buildRoad();     // 도로 건설
    void farmTile();      // 농경지 개발
    void mineResource();  // 광산 개발
    void embark();        // 승선
}

// ❌ 전사 유닛은 건설, 개발, 치료를 할 수 없는데 구현해야 함
class Warrior implements Unit {
    @Override
    public void move() {
        System.out.println("전사 이동");
    }

    @Override
    public void meleeAttack() {
        System.out.println("전사 근접 공격");
    }

    @Override
    public void rangedAttack() {
        throw new UnsupportedOperationException("전사는 원거리 공격을 할 수 없습니다");
    }

    @Override
    public void heal() {
        throw new UnsupportedOperationException("전사는 치료할 수 없습니다");
    }

    @Override
    public void buildCity() {
        throw new UnsupportedOperationException("전사는 도시를 건설할 수 없습니다");
    }

    @Override
    public void buildRoad() {
        throw new UnsupportedOperationException("전사는 도로를 건설할 수 없습니다");
    }

    @Override
    public void farmTile() {
        throw new UnsupportedOperationException("전사는 농경지를 개발할 수 없습니다");
    }

    @Override
    public void mineResource() {
        throw new UnsupportedOperationException("전사는 광산을 개발할 수 없습니다");
    }

    @Override
    public void embark() {
        System.out.println("전사 승선");
    }
}

// ❌ 일꾼은 공격을 할 수 없는데 구현해야 함
class Worker implements Unit {
    @Override
    public void move() {
        System.out.println("일꾼 이동");
    }

    @Override
    public void rangedAttack() {
        throw new UnsupportedOperationException("일꾼은 원거리 공격을 할 수 없습니다");
    }

    @Override
    public void meleeAttack() {
        throw new UnsupportedOperationException("일꾼은 근접 공격을 할 수 없습니다");
    }

    @Override
    public void heal() {
        throw new UnsupportedOperationException("일꾼은 치료할 수 없습니다");
    }

    @Override
    public void buildCity() {
        System.out.println("일꾼 도시 건설");
    }

    @Override
    public void buildRoad() {
        System.out.println("일꾼 도로 건설");
    }

    @Override
    public void farmTile() {
        System.out.println("일꾼 농경지 개발");
    }

    @Override
    public void mineResource() {
        System.out.println("일꾼 광산 개발");
    }

    @Override
    public void embark() {
        System.out.println("일꾼 승선");
    }
}
```

example2의 나쁜 코드입니다.

이 코드 또한 게임의 유닛을 코드로 나타낸 코드입니다.

그러나 Unit 이라는 공통적 인터페이스에 모든 기능이 집합되어 있어, 불필요한 기능까지 의무적으로 구현해야만 하는 단점이 발생합니다.

이제 불필요한 기능을 구현하지 않게 만들어봅시다.

```java
package ISP.example2.good;

// ✅ ISP 준수: 기능별로 인터페이스 분리
interface Unit {
    void move();  // 모든 유닛의 기본 능력
}

interface RangedAttackable {
    void rangedAttack();  // 원거리 공격 가능
}

interface MeleeAttackable {
    void meleeAttack();  // 근접 공격 가능
}

interface Healer {
    void heal();  // 치료 가능
}

interface CityBuilder {
    void buildCity();  // 도시 건설 가능
}

interface RoadBuilder {
    void buildRoad();  // 도로 건설 가능
}

interface Farmer {
    void farmTile();  // 농경지 개발 가능
}

interface Miner {
    void mineResource();  // 광산 개발 가능
}

interface Embarker {
    void embark();  // 승선 가능
}

// ✅ 전사는 이동, 근접 공격, 승선만 가능
class Warrior implements Unit, MeleeAttackable, Embarker {
    @Override
    public void move() {
        System.out.println("전사가 이동합니다");
    }

    @Override
    public void embark() {
        System.out.println("전사가 배에 승선합니다");
    }

    @Override
    public void meleeAttack() {
        System.out.println("전사가 근접 공격을 합니다");
    }
}

// ✅ 일꾼은 이동, 건설, 개발, 승선이 가능
class Worker implements Unit, CityBuilder, RoadBuilder, Farmer, Miner, Embarker {
    @Override
    public void move() {
        System.out.println("일꾼이 이동합니다");
    }

    @Override
    public void buildCity() {
        System.out.println("일꾼이 도시를 건설합니다");
    }

    @Override
    public void buildRoad() {
        System.out.println("일꾼이 도로를 건설합니다");
    }

    @Override
    public void embark() {
        System.out.println("일꾼이 배에 승선합니다");
    }

    @Override
    public void farmTile() {
        System.out.println("일꾼이 농경지를 개발합니다");
    }

    @Override
    public void mineResource() {
        System.out.println("일꾼이 광산을 개발합니다");
    }
}
```

각 인터페이스를 나눔으로써 필요한 기능을만 가지게 되었습니다.

이제는 불필요한 기능을 처리하거나 구현해야 하는 번거로움이 해결되었습니다.

## 정리

- 인터페이스 분리 원칙은 범용 인터페이스보다는 여러개로 나눈 인터페이스가 좋다라는 것을 의미합니다.
- 클라이언트는 필요한 기능만 구현할 수 있어야 합니다
- 불필요한 의존성을 제거하여 시스템의 유연성과 재사용성을 높일 수 있습니다