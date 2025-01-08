package OCP.example3.bad;

// ❌ OCP 위반: 새로운 결제 수단이 추가될 때마다 이 클래스를 수정해야 함
public class PaymentService {
    public void processPayment(String type) {
        if (type.equals("credit")) {
            System.out.println("신용카드로 결제를 진행합니다.");
        } else if (type.equals("kakao")) {
            System.out.println("카카오페이로 결제를 진행합니다.");
        }
        // ❌ 새로운 결제 수단(예: 네이버페이, 토스 등) 추가시 여기에 계속 코드를 추가해야 함
    }
}