/**
 * Description: the red-light lights every 3 seconds, the green lights every 1 second, 
 * and the yellow lights every 2 seconds. Using Promise implments that they light 
 * calternately and repeatedly.
 */

function red() {
    console.log("This is red");
} 

function green() {
    console.log("This is green");
}

function yellow() {
    console.log("This is yellow");
}

var tic = function(cb, timmer) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            cb();
            resolve();
        }, timmer);
    });
};

var step = function(def) {
    def.then(() => {
        return tic(red, 3000);
    }).then(() => {
        return tic(green, 2000);
    }).then(() => {
        return tic(yellow, 1000);
    }).then(() => {
        step(def);
    });
}

var d = new Promise((resolve, reject) => {
    resolve();
});

step(d);