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
