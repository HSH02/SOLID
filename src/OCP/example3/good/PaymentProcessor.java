package OCP.example3.good;

// ✅ OCP 준수: 새로 카드를 추가하더라도 기존 코드를 수정하지 않아도 됌
public interface PaymentProcessor {
    void process();  // 결제 처리 메서드
}

// ✅ OCP 준수: 신한카드 결제 처리는 자신만의 로직 구현
class ShinhanCardProcessor implements PaymentProcessor {
    @Override
    public void process() {
        System.out.println("신한카드 결제를 진행합니다.");
        System.out.println("1. 카드 정보 검증");
        System.out.println("2. 금액 승인 요청");
        System.out.println("3. 결제 완료");
    }
}

// ✅ OCP 준수: 카카오페이 결제 처리는 자신만의 로직 구현
class KakaoPayProcessor implements PaymentProcessor {
    @Override
    public void process() {
        System.out.println("카카오페이 결제를 진행합니다.");
        System.out.println("1. QR코드 생성");
        System.out.println("2. 사용자 인증");
        System.out.println("3. 결제 완료");
    }
}
