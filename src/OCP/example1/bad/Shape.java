package OCP.example1.bad;

// ❌ SRP 위반: 부모 클래스가 도형의 타입을 문자열로 관리
class Shape {
    String type;
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.type = "circle";
        this.radius = radius;
    }
}

class Rectangle extends Shape {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.type = "rectangle";
        this.width = width;
        this.height = height;
    }
}

// ❌ OCP 위반: 새로운 도형이 추가될 때마다 이 클래스를 수정해야 함
class AreaCalculator {
    public double calculateArea(Shape shape) {
        if (shape.type.equals("circle")) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        } else if (shape.type.equals("rectangle")) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.width * rectangle.height;
        }
        return 0;
    }
}