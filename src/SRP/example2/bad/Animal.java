package SRP.example2.bad;

// ❌ SRP 위반: Animal 클래스가 두 가지 책임을 가지고 있음
// 1. 동물의 종류 관리
// 2. 동물의 울음소리 출력
public class Animal {
    private String animal;

    // ❌ switch문으로 모든 동물의 울음소리를 처리
    // 새로운 동물이 추가될 때마다 이 메서드를 수정해야 함
    public void cry() {
        switch (animal) {
            case "cat":
                System.out.println("cat");
                break;
            case "dog":
                System.out.println("dog");
                break;
            case "horse":
                System.out.println("horse");
                break;
            default:
                break;
        }
    }

    // ❌ 단순 getter/setter만으로는 동물의 종류를 제대로 관리하기 어려움
    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}