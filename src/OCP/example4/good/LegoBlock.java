package OCP.example4.good;

// ✅ OCP 준수: 레고 블록의 공통 인터페이스 정의
abstract class LegoBlock {
    public abstract void build();
}

// ✅ 각 블록은 자신의 동작만 정의
class Red extends LegoBlock {
    @Override
    public void build() {
        System.out.println("🔴 벽돌 블록을 추가합니다.");
    }
}

class Blue extends LegoBlock {
    @Override
    public void build() {
        System.out.println("🔵 바퀴 블록을 추가합니다.");
    }
}

class Yellow extends LegoBlock {
    @Override
    public void build() {
        System.out.println("🟡 창문 블록을 추가합니다.");
    }
}

// ✅ LegoBuilder는 변경 없이 확장 가능
class LegoBuilder {
    public void build(LegoBlock block) {
        block.build();  // 각 블록의 구현에 따라 적절한 동작 수행
    }
}