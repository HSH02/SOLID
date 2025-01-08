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
