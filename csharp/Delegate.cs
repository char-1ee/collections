/// Usage of Action<T> and Func<T> in delegate
/// Action<T>: reference to a void function call
/// Func<T>: reference to a function call non-void return 

using System;

namespace Delegate 
{
    class Program 
    {
        static void Main(string[] args)
        {
            Func<double, double, double> DoAddition = Calculate.addition;
            Console.WriteLine("Func<T> return {0}", DoAddition(10, 20));

            Action<double, double> DoSubtraction = Calculate.substraction;
            DoSubtraction(90, 20);
        }
    }

    class Calculate 
    {
        public static double addition(double x, double y) { return x + y; }
        public static void substraction(double x, double y) { Console.WriteLine("Action<T> with void return: {0}", x - y); }
    }
}