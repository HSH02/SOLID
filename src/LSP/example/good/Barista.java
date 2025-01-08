package LSP.example.good;

import LSP.example.Coffee;

// 커피를 만들 수 있는 기본 자격
interface CoffeeMaker {
    Coffee makeCoffee();
}

// 커피 품질을 체크할 수 있는 자격
interface QualityChecker {
    boolean checkCoffeeQuality(Coffee coffee);
}

// 시니어 바리스타: 커피 제조 + 품질 체크 가능
// ✅ 맡은 역할을 올바르게 수행할 수 있다.
class SeniorBarista implements CoffeeMaker, QualityChecker {
    @Override
    public Coffee makeCoffee() {
        return new Coffee("시니어 바리스타의 커피");
    }

    @Override
    public boolean checkCoffeeQuality(Coffee coffee) {
        return coffee.checkTaste();
    }
}

// 주니어 바리스타: 커피 제조만 가능
// ✅ 맡은 역할을 올바르게 수행할 수 있다.
class JuniorBarista implements CoffeeMaker {
    @Override
    public Coffee makeCoffee() {
        return new Coffee("주니어 바리스타의 커피");  // 기본적인 커피는 만들 수 있음
    }
}

class Cafe {
    // 커피 검사 담당자 (시니어 바리스타 또는 매니저)
    private final QualityChecker manager;

    public Cafe(QualityChecker manager) {
        this.manager = manager;
    }

    // 손님에게 커피 제공하기
    public void serveCustomer(CoffeeMaker barista) {
        // 1. 바리스타가 커피를 만듦
        Coffee coffee = barista.makeCoffee();

        // 2. 품질 검사 담당자가 커피를 체크
        boolean isPassed = manager.checkCoffeeQuality(coffee);

        // 3. 합격한 커피만 손님에게 제공
        if (isPassed) {
            System.out.println("손님 커피 나왔습니다~");
        } else {
            System.out.println("죄송합니다. 커피를 다시 내리겠습니다.");
        }
    }

}

