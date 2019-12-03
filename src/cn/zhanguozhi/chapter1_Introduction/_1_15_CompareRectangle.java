package cn.zhanguozhi.chapter1_Introduction;

import java.util.Comparator;

/**
 * @author zhanguozhi
 * @date 2019.11.12
 * @desc    从实现Comparable接口的改变  因为需要强制实现compareTo方法  而且一旦确定不能改变  不适合扩展
 *          所以 有一个解决办法就是使用函数对象  函数对象就是将函数用一个对象封装起来
 *          Comparator提供一个compare方法  可以写多个类实现这个接口  每个类提供的比较方法并不相同  并且需要被比较的类不需要知道自身怎么被比较
 *          比较的时候传入的比较器的类型是需要被比较的类型或其父类型  因为比较器如果知道怎么比较其父类 根据继承 也会知道怎么比较它的子类
 */
public class _1_15_CompareRectangle {
    public static<AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> comp) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组不存在或长度为空");
        }
        AnyType max = arr[0];
        for (AnyType element : arr) { // 虽然有一次重复的比较，但是高级for循环比普通for循环的性能提高很多，所以可以忽略不计
            if (comp.compare(element, max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Rectangle[] rectangles = new Rectangle[4];
        rectangles[0] = new Rectangle(2, 10);
        rectangles[1] = new Rectangle(4, 4);
        rectangles[2] = new Rectangle(5, 5);
        rectangles[3] = new Rectangle(8, 3);
        System.out.println(findMax(rectangles, new AreaRectangleComp()));
        System.out.println(findMax(rectangles, new PerimeterRectangleComp()));
    }
}
class Rectangle {
    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return getLength() * getWidth();
    }

    public int getPerimeter() {
        return 2 * (getLength() + getWidth());
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}

class AreaRectangleComp implements Comparator<Rectangle> {
    @Override
    public int compare(Rectangle rectangle, Rectangle t1) {
        return rectangle.getArea() - t1.getArea();
    }
}

class PerimeterRectangleComp implements Comparator<Rectangle> {
    @Override
    public int compare(Rectangle rectangle, Rectangle t1) {
        return rectangle.getPerimeter() - t1.getPerimeter();
    }
}