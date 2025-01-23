package SRP.example3.bad;

// ❌ SRP 위반: MultiTool 클래스가 여러 책임을 가지고 있음
// 1. 자르기, 나사 조이기, 병따개 등 다양한 작업 관리
// 2. 각 작업의 동작을 정의
public class MultiTool {
    private String tool;

    // ❌ 새로운 기능이 추가될 때마다 이 메서드를 수정해야 함
    public void useTool() {
        switch (tool) {
            case "knife":
                System.out.println("칼로 자릅니다.");
                break;
            case "screwdriver":
                System.out.println("나사를 조입니다.");
                break;
            case "bottle_opener":
                System.out.println("병을 엽니다.");
                break;
            default:
                System.out.println("알 수 없는 도구입니다.");
                break;
        }
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }
}