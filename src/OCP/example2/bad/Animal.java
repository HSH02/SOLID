package OCP.example2.bad;

public class Animal {
    private String type;

    public Animal(String type) {
        this.type = type;
    }

    // ❌ OCP 위반: 새로운 동물이 추가될 때마다 이 메서드를 수정해야 함
    public void makeSound() {
        if (type.equals("dog")) {
            System.out.println("Woof Woof");
        } else if (type.equals("cat")) {
            System.out.println("Meow");
        } else if (type.equals("cow")) {
            System.out.println("Moo");
        } else {
            System.out.println("Unknown animal sound");
        }
    }

    public String getType() {
        return type;
    }
}