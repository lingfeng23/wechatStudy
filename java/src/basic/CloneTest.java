package basic;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author malf
 * @Description 通过 Serializable 实现对象的深度克隆
 * @project wechatStudy
 * @since 2020/11/23
 */
public class CloneTest {
    public static void main(String[] args) {
        try {
            Person person1 = new Person("Bob", 33,
                    new Car("Benz", 300));
            Person person2 = clone(person1); // 深度克隆
            person2.getCar().setBrand("BYD");
            // 修改 person2 关联的 Car 的属性，不会影响 person1 关联的 Car
            System.out.println(person1.getCar().getBrand());
        } catch (Exception e) {
        }
    }

    static private <T> T clone(T obj) throws Exception {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(obj);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (T) objectInputStream.readObject();
    }

}

class Person implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    private String name;
    private int age;
    private Car car;

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

class Car implements Serializable {
    private static final long serialVersionUID = 1234567891L;
    private String brand;
    private int maxSpeed;

    public Car(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}