/**
 * Type Guards: get information about the type of a variable, usually within a conditional block.
 *  - typeof
 *  - instanceof
 *  - in
 *  - user-defined 
 */

// =========== typeof ============
type alphanumeric = string | number;

function add(a: alphanumeric, b: alphanumeric) {
    if (typeof a === 'number' && typeof b === 'number') {
        return a + b;
    }

    if (typeof a === 'string' && typeof b === 'string') {
        return a.concat(b);
    }

    throw new Error("Invalid arguments.");
}

// ============ instanceof =============
class Customer {
    isCreditAllowed(): boolean {
        return true;
    }
}

class Suppiler {
    isInShortList(): boolean {
        return true;
    }
}

type BusinessPartner = Customer | Suppiler;

function signContract(partner: BusinessPartner): string {
    let message!: string;
    if (partner instanceof Customer) {
        message = partner.isCreditAllowed() ? 'Sign a new contract with the cutsomer' : 'Credit issue';
    }

    if (partner instanceof Suppiler) {
        message = partner.isInShortList() ? 'Sign a new contract the supplier' : 'Further evaluations';
    }

    return message;
}

// in : check for a existence of a property on an object 
function signContract2(partner: BusinessPartner): string {
    let message!: string;

    if ('isCreditAllowed' in partner) {
        message = partner.isCreditAllowed() ? 'Sign a new contract with the customer' : 'Credit issue';
    } else {
        message = partner.isInShortList() ? 'Sign a new contract the supplier' : 'Further evaluations';
    }

    return message;
}

// User-defined Type Guards: a function the returns `arg is aType`
function isCustomer(partner: any): partner is Customer {
    return partner instanceof Customer;
}

function signContract3(partner: BusinessPartner): string {
    let message: string;

    if (isCustomer(partner)) {
        message = partner.isCreditAllowed() ? 'Sign a new contract with the customer' : 'Credit issue';
    } else {
        message = partner.isInShortList() ? 'Sign a new contract with the supplier' : 'Need to evaluate further';
    }

    return message;
}