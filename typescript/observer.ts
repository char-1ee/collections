/**
 * Observable, Obsever, Subscription
 */
import { Observable } from "rxjs";

const now = new Date().getTime();

// 1. Construct a stream (observable variable ends with $) by `new Observable(fn)`
const stream$ = new Observable(subscriber => {
    setTimeout(() => {
        subscriber.next([1, 2, 3]);
    }, 500); // output an array [1, 2, 3] when 500ms

    setTimeout(() => {
        subscriber.next({ a: 1000 });
    }, 1000); // output an object {a: 1000} when 1s

    setTimeout(() => {
        subscriber.next("end");
    }, 3000); // output "end" when 3s

    setTimeout(() => {
        subscriber.complete();
    }, 4000); // close the stream when 4s
});

// 2. Define how the data from stream should be consumed by observer
const observer = {
    complete: () => console.log("done"),
    next: v => console.log(new Date().getTime() - now, v),
    error: () => console.log("error")
};

// 3. Start the stream by `Observable.subscribe(observer)`
const subscription = stream$.subscribe(observer);

// 4. Stop the stream by `Observable.unsubscribe(observer)`
setTimeout(() => {
    subscription.unsubscribe();
}, 1000);