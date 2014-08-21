#include <stdio.h>
#include <malloc.h>
int main()
{
    int i = 1337;
    int array[] = {1,2,3,4,5,6,7,8,9};
    int *pointers[2];

    int * (*pstack) [3];

    pstack = malloc (sizeof(int *[3]));

    int** temp = pstack;

    printf("%p\n", pstack);
    printf("%p\n", temp);

    *temp = malloc(sizeof(int));

    printf("%p\n", *temp);
    **temp= 99;

    printf("new value is: %d\n", **temp);

    pointers[0] = malloc (sizeof(int));
    int *p = pointers[0];
    *p = 99;

    printf("%p\n", &array);
    printf("%p\n", array);
    printf("value is %d\n", *p);

    return 0;
}
