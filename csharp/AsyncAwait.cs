using System;
using System.Threading;

public class AsyncAwait
{
    static void Main(string[] args)
    {
        Console.WriteLine("Main start, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
        var teaResult = PrepareteaAsync();
        var hotWaterResult = PrepareHotWaterAsync();
        var cupResult = PrepareCupAsync();
        Console.WriteLine("Main waiting, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
        teaResult.GetAwaiter().GetResult();
        hotWaterResult.GetAwaiter().GetResult();
        cupResult.GetAwaiter().GetResult();
        Console.WriteLine($"All Done! tea={teaResult.Status}, hotWater={hotWaterResult.Status}, cup={cupResult.Status}, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
        Console.WriteLine("Main end, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
    }

    private static async Task PrepareteaAsync()
    {
        Console.WriteLine("PrepareteaAsync start, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
        await TimeConsumingMethod(3000);
        Console.WriteLine("PrepareteaAsync end, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
    }

    private static async Task PrepareHotWaterAsync()
    {
        Console.WriteLine("PrepareHotWaterAsync start, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
        await TimeConsumingMethod(5000);
        Console.WriteLine("PrepareHotWaterAsync end, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
    }

    private static async Task PrepareCupAsync()
    {
        Console.WriteLine("PrepareCupAsync start, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
        await TimeConsumingMethod(2000);
        Console.WriteLine("PrepareCupAsync end, Thread ID is " + Thread.CurrentThread.ManagedThreadId);
    }
    
    static Task<int> TimeConsumingMethod(int timeCost)
    {
        var task = Task.Run(() => {
            Console.WriteLine($"TimeconsumingMethod({timeCost}) start with Thread ID is {Thread.CurrentThread.ManagedThreadId}");
            Thread.Sleep(timeCost);
            Console.WriteLine($"TimeconsumingMethod({timeCost}) end with Thread ID is {Thread.CurrentThread.ManagedThreadId}");
            return timeCost;
        });
        return task;
    }
}