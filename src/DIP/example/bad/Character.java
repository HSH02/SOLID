package DIP.example.bad;

// ❌ DIP 위반: Character가 직접 구체적인 무기에 의존
class Character {
   //  클래스에 직접 의존
   private Sword sword = new Sword();
   private Bow bow = new Bow();

   //  무기가 추가될 때마다 수정 필요
   public void attack(String weaponType) {
       if (weaponType.equals("sword")) {
           sword.attack();
       } else if (weaponType.equals("bow")) {
           bow.attack();
       }
   }
}

class Sword {
   public void attack() {
       System.out.println("칼로 공격!");
   }
}

class Bow {
   public void attack() {
       System.out.println("활로 공격!");
   }
}