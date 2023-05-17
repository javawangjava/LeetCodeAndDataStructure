package classtest;

public class ToString {
    public static void main(String[] args) {
        Person person1 = new Person(26, 50);
        Person person2 = new Person(26, 50);
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person1.equals(person2));
    }
}

class Person {
    private int age;
    private double weight;

    public Person() {

    }

    public Person(int age, double weight) {
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return age + "," + weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        if (obj instanceof Person) {
            Person p = (Person) obj;
            if (this.age == p.age && this.weight == p.weight){
                return true;
            }
        }
        return false;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}