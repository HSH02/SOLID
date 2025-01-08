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