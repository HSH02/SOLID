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