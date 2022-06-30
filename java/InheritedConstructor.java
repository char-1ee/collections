package java;

// 父类 SuperClass
class SuperClass {
    int n = 200;

    SuperClass() {
        System.out.println("SuperClass()");
    }

    SuperClass(int n) {
        System.out.println("SuperClass(int n)");
        this.n = n;
    }
}

// SubClass 类继承
class SubClass extends SuperClass {
    int n = 10;

    SubClass() { // 自动调用父类的无参数构造器
        System.out.println("SubClass");
    }

    public SubClass(int n) {
        super(300); // 调用父类中带有参数的构造器
        System.out.println("SubClass(int n): " + n);
        this.n = n;
    }
}

// SubClass2 类继承
class SubClass2 extends SuperClass {
    int n = 10;

    SubClass2() { // 调用父类的带参数构造器
        super(300);
        System.out.println("SubClass2");
    }

    public SubClass2(int n) { // 调用父类的无参数构造器
        System.out.println("SubClass(int n)： " + n);
        this.n = n;
    }
}

public class InheritedConstructor {
    public static void main(String args[]) {
        System.out.println("-----SubClass Inheritance-----");
        SubClass sc1 = new SubClass();
        SubClass sc2 = new SubClass(100);
        System.out.println(sc1);
        System.out.println(sc2);

        System.out.println("-----SubClass2 Inheritance-----");
        SubClass2 sc3 = new SubClass2();
        SubClass2 sc4 = new SubClass2(200);
        System.out.println(sc3);
        System.out.println(sc4);
    }
}

/*------------------------Output------------------------------*/
// -----SubClass Inheritance-----
// SuperClass()
// SubClass
// SuperClass(int n)
// SubClass(int n): 100
// -----SubClass2 Inheritance-----
// SuperClass(int n)
// SubClass2
// SuperClass()
// SubClass(int n)： 200