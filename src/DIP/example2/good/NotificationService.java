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