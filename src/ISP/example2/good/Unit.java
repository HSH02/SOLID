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