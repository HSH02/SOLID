package OCP.example1.good;

// ✅ OCP 준수: 도형들의 공통 기능을 인터페이스로 정의
interface Shape {
    double calculateArea();
}

// ✅ OCP 준수: 원은 자신의 면적 계산 로직을 구현
class Circle implements Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// ✅ OCP 준수: 직사각형은 자신의 면적 계산 로직을 구현
class Rectangle implements Shape {
    private double width;
    private double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

// ✅ OCP 준수: 새로운 도형 추가가 자유로움
class Triangle implements Shape {
    private double base;
    private double height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

// ❌ 이전의 나쁜 코드 (참고용)
/*
class AreaCalculator {
   public double calculateArea(Shape shape) {
       if (shape.type.equals("circle")) {           // 타입 체크
           Circle circle = (Circle) shape;          // 타입 캐스팅
           return Math.PI * circle.radius * circle.radius;
       } else if (shape.type.equals("rectangle")) { // 타입 체크
           Rectangle rectangle = (Rectangle) shape;  // 타입 캐스팅
           return rectangle.width * rectangle.height;
       }
       return 0;
   }
}
*/