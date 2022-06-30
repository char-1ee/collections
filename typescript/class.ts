/** Abstract classes */
abstract class Account {
    id: string;
    constructor(id: string) {
        this.id = id;
    }

    abstract getName(): string;
    printName() {
        console.log("Hello", + this.getName());
    }
}

/** Class structure */
class User extends Account /* implements Updatable, Serializable */ {
    displayName?: boolean; // an optional field
    name!: string;  // a nullable field
    #attributes: Map<any, any> | null; // a private field
    roles: ["user"] | undefined; // a field with a default
    readonly createdAt = new Date(); // a readonly field immutable with a default

    // constructor, called on 'new'
    constructor(id: string, name: string, attributes: Map<any, any> | null) {
        super(id);
        this.name = name;
        this.#attributes = attributes;
    }

    // override but without @override
    getName() { return this.name; }
    getAttribute() { return this.#attributes; }

    // difference way to describe class methods
    setName(name: string) { this.name = name; }
    verifyName = (name: string) => { /* ... */ }

    // a function with 2 overload definitions
    sync(): Promise<{}>
    sync(cb: ((result: string) => void)): void
    sync(cb?: ((result: string) => void)): void | Promise<{}> { }

    // getter and setter
    get accountID() { return this.id; }
    set accountID(value: string) { this.id = value; }

    // private: to the class (using private prefix but no #)
    // protected: allows to subclasses
    // public: default, used for type checking
    private makeRequest() { }
    protected handleRequest() { }

    // static fields / methods
    static #userCount = 0;
    static registerUser(user: User) { }

    // static blocks for setting up static vars
    // 'this' refers to teh static class
    static { this.#userCount = -1 }
}

/** Create an class instance */
const userInstance = new User('1', 'char', null);

/** Parameter properties */
class Locations {
    constructor(public x: number, public y: number) { }
}

const loc = new Locations(20, 40);
console.log(loc.x, loc.y);

/** Decorators and Attributes */
// import {
//     Syncable, triggerSync, preferCache, required
// } from "mylib"

// @Syncable
// class A {
//     @triggerSync()
//     save() { }

//     @preferCache(false)
//     get displayName() { }

//     update(@required info: Partial<User>) { }
// }