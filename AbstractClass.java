/**
 * About abstract class:
 * 1. abstract class cannot be instantiated otherwise complier error;
 * 2. abstract class may or maynot includes abstract method, but class within abstract method must be abstract class;
 * 3. abstract method in abstract class is decalration but not definition;
 * 4. constructor in class must not be abstract method;
 * 
 * 声明抽象方法会造成以下两个结果：
 * 1. 如果一个类包含抽象方法，那么该类必须是抽象类。
 * 2. 任何子类必须重写父类的抽象方法，或者声明自身为抽象类。
 */


/**
 * 定义abstract class
 */
public abstract class AbstractClass {
    private String name;
    private String address;
    private int number;

    public AbstractClass(String name, String address, int number) {
        System.out.println("Constructing an Employee");
        this.name = name;
        this.address = address;
        this.number = number; 
        ;
    }
    public double computePay() {
        System.out.println("Inside Employee compuerPay");
        return 0.0;
    }
    public void mailCheck() {
        System.out.println("Mailing a check to " + this.name + " " + this.address);
    }
    public String toString() {
        return name + " " + address + " " + number;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String newAddress) {
        address = newAddress;
    }
    public int getNumber() {
        return number;
    }
    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }
}

/**
 * Inheritance from class AbstractClass(Employee) 
 */
 class Salary extends AbstractClass
{
   private double salary; //Annual salary
   public Salary(String name, String address, int number, double
      salary)
   {
       super(name, address, number);
       setSalary(salary);
   }
   public void mailCheck()
   {
       System.out.println("Within mailCheck of Salary class ");
       System.out.println("Mailing check to " + getName()
       + " with salary " + salary);
   }
   public double getSalary()
   {
       return salary;
   }
   public void setSalary(double newSalary)
   {
       if(newSalary >= 0.0) salary = newSalary;
   }
   public double computePay()
   {
      System.out.println("Computing salary pay for " + getName());
      return salary/52;
   }
}

/**
 * 定义 abstract method
 */
 abstract class Employee
{
//    private String name;
//    private String address;
//    private int number;
   
   public abstract double computePay();
   
   //其余代码
}

/**
 * Inheritance form abstract method Employee
 */
class Salary2 extends Employee
{
   private double salary; // Annual salary
  
   public double computePay()
   {
      System.out.println("Computing salary pay for " + getName());
      return salary/52;
   }

    private String getName() {
    return null;
    }
 
   //其余代码
}