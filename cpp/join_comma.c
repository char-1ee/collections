// Scenario: print numbers and join them with commas ","
// int arr[5] = {1, 2, 3, 4, 5};
// print as: 1, 2, 3, 4, 5\n rather than 1 2 3 4 5\n

#include <stdio.h>

int main(void) {
    int i;
    int arr[5] = {1, 2, 3, 4, 5};

    // Normally this is what we do
    printf("%d", arr[0]);
    for (i = 1; i < 5; i++) {
        printf(",%d", arr[i]);
    }

    // Or 
    for (i = 0; i < 4; i++) {
        printf("%d,", arr[i]);
    }
    printf("%d", arr[4]);

    // A tricky way:
    // ",%d" can be converted to a pointer to the first element of the string,
    // when i == 0, the pointer move backwards 1 position to omit the first ",".
    for (i = 0; i < 5; i++) {
        printf(",%d" + !i, arr[i]);
    }

    return 0;
}