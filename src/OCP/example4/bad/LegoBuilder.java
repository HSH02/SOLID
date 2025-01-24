package OCP.example4.bad;

// ❌ OCP 위반: LegoBuilder 클래스가 레고 블록의 종류에 따라 직접 블록을 관리함
public class LegoBuilder {
    public void build(String blockType) {
        // 새로운 블록 종류가 추가되면 이 메서드를 수정해야 함
        switch (blockType) {
            case "red":
                System.out.println("🔴 빨강 블록을 추가합니다.");
                break;
            case "blue":
                System.out.println("🔵 파랑 블록을 추가합니다.");
                break;
            case "yellow":
                System.out.println("🟡 노랑 블록을 추가합니다.");
                break;
            default:
                System.out.println("⚠️ 알 수 없는 블록입니다.");
                break;
        }
    }
}
