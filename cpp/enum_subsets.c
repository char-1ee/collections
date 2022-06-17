#include <stdio.h>

int main() {
    int s, n, r;

    /* Enumerate all subsets of s */
    for(int i = s; i; i = (i-1) & s) {}

    /* Enumerate all subsets in size of r of s */
    for(int s = (1<<r) - 1; s < 1<<n; ) {
        int x = s & -s;
        int y = s + x;
        s = ((s&~y) / x>>1) | y;
    } 
    return 0;
}