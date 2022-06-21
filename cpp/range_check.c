// range checker in c and efficient methods.

#include <stdio.h>

int main() {
    // normal range checker
    if (x >= minx && x <= maxx) { printf("range check\n"); }

    // level 2 rnage checker
    if (((x - minx) | (maxx - x)) >= 0) {printf("range check\n");}

    // level 3 range checker
    if ((unsigned) (x - minx) <= (unsigned) (maxx - minx)) {printf("range check\n");}

    return 0;
}