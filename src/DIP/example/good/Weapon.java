package DIP.example.good;

// ✅ DIP 준수: 무기를 추상화
interface Weapon {
    void attack();
}

// ✅ 각 무기는 Weapon 인터페이스를 구현
class Sword implements Weapon {
    @Override
    public void attack() {
        System.out.println("칼로 슉슉 베기!");
    }
}

class Bow implements Weapon {
    @Override
    public void attack() {
        System.out.println("활로 슝슝 쏘기!");
    }
}

class Wand implements Weapon {
    @Override
    public void attack() {
        System.out.println("마법봉으로 파이어볼!");
    }
}

// ✅ Character는 추상화된 Weapon에 의존
class Character {
    private Weapon weapon;  // 추상화에 의존

    // ✅ 생성자를 통한 의존성 주입
    public Character(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack() {
        weapon.attack();
    }
    
    // ✅ 교체도 쉽게 가능
    public void changeWeapon(Weapon newWeapon) {
        weapon = newWeapon;
    }
}