# 의존관계 역전 원칙 (Dependency inversion principle)

## 개요

의존관계 역전 원칙은 "추상화에 의존해야 하며, 구체화에 의존하면 안된다" 라는 원칙입니다.

## 설명

구체적인 클래스보다 인터페이스나 추상 클래스와 같은 추상화에 의존하게 설계합니다

예시를 통해 알아봅시다.

## 예시 1

```java
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
```

example1의 나쁜 코드입니다.

간단하게 무기를 코드로 구현한 코드입니다.

구체적으로 검이나 활 등을 지정하여 사용하므로, 추상화보다는 구체화에 의존하고 있습니다. 또한 OCP 법칙에도 위반됩니다.

올바르게 바꿔봅시다.

```java
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
```

Weapon 인터페이스로 추상화에 의존하게 만들었습니다. 

이제 만약 Weapon을 변경하거나 메서드 호출에도 간단하게 사용할 수 있게 되었으며, 주입도 쉬워졌습니다. 

## 예시 2

```java
package DIP.example2.bad;

// ❌ DIP 위반: NotificationService가 구체적인 클래스들에 직접 의존
class NotificationService {
   private EmailSender emailSender = new EmailSender();
   private SmsSender smsSender = new SmsSender();
   private KakaoTalkSender kakaoTalkSender = new KakaoTalkSender();

   // ❌ 새로운 알림 방식이 추가될 때마다 코드 수정이 필요
   public void sendNotification(String type, String message) {
       if (type.equals("email")) {
           emailSender.sendEmail(message);
       } else if (type.equals("sms")) {
           smsSender.sendSms(message);
       } else if (type.equals("kakao")) {
           kakaoTalkSender.sendKakaoTalk(message);
       }
   }
}

class EmailSender {
   public void sendEmail(String message) {
       System.out.println("이메일 발송: " + message);
   }
}

class SmsSender {
   public void sendSms(String message) {
       System.out.println("SMS 발송: " + message);
   }
}

class KakaoTalkSender {
   public void sendKakaoTalk(String message) {
       System.out.println("카카오톡 발송: " + message);
   }
}
```

example2의 나쁜 코드입니다.

구체화된 코드에 의존하고 있으며, 기능을 새로 추가하거나 변경할 시 번거로워 집니다.

```java
// ✅ 알림 발송을 추상화한 인터페이스
interface NotificationService {
    void sendNotification(String message);
}

// ✅ 이메일 발송 구현체
class EmailSender implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("이메일 발송: " + message);
    }
}

// ✅ SMS 발송 구현체
class SmsSender implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS 발송: " + message);
    }
}

// ✅ 카카오톡 발송 구현체
class KakaoTalkSender implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("카카오톡 발송: " + message);
    }
}

// ✅ 알림 발송을 관리하는 클래스
class NotificationManager {
    private final NotificationService notificationService;

    // ✅ 생성자를 통한 의존성 주입
    public NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void send(String message) {
        notificationService.sendNotification(message);
    }
}
```

구체화에 의존하게 변경한 코드입니다.

이제 발송 확장에 수정이 없어졌고 발송 관련 없이 알림 발송을 정상적으로 수행할 수 있게 되었습니다.

## 정리
- 의존관계 역전 원칙은 구체화보다 추상화에 의존해야 함을 의미합니다
- 고수준 모듈과 저수준 모듈 모두 추상화에 의존해야 합니다
- 의존성 주입(DI)을 통해 구현할 수 있습니다
