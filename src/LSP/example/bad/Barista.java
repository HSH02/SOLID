package LSP.example.bad;

import LSP.example.Coffee;

// 바리스타의 기본 역할
public class Barista {
    public Coffee makeCoffee(){
        return new Coffee("맛있는 커피");
    }

    public boolean checkCoffeeQuality(Coffee coffee){
        return true;        // 기본적으로 품질 체크
    }
}

// 신입 바리스타
// ❌ 바리스타의 역할을 전혀 수행하지 못한다.
class JuniorBarista extends Barista {
    @Override
    public Coffee makeCoffee() {
        throw new RuntimeException("아직 커피 만드는 법을 배우지 않았습니다..");
        // LSP 위반: 부모 클래스의 기본 기능을 수행할 수 없음
    }

    @Override
    public boolean checkCoffeeQuality(Coffee coffee) {
        throw new RuntimeException("권한이 없음");
        // LSP 위반: 부모 클래스의 기본 기능을 수행할 수 없음
    }
}

// ❌ 신입 바리스타가 들어올 경우 카페 운영에 문제가 생길 수 있다.
class Cafe {
    public void serveCustomer(Barista barista) {
        try {
            Coffee coffee = barista.makeCoffee();  // 예외 발생 가능!
            if (barista.checkCoffeeQuality(coffee)) {  // 예외 발생 가능!
                System.out.println("커피 서빙");
            }
        } catch (RuntimeException e) {
            System.out.println("카페 운영에 문제 발생!");
        }
    }
}

