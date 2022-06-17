/**
 * Subject 
 */
import { Subject } from "rxjs";

// Create a subject 
const subject = new Subject();

// Subscribe observers
subject.subscribe(v => console.log("stream 1", v));
subject.subscribe(v => console.log("stream 2", v));

// Subscribe a 1s delay obersver
setTimeout(() => {
    subject.subscribe(v => console.log("stream 3", v));
}, 1000);

// Generate data
subject.next(1);
subject.next(2);

// Generate a 3s delay data
setTimeout(() => {
    subject.next(3);
}, 3000);