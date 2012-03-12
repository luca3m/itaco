#include <stdio.h>
#include <stdlib.h>

void sommaAlVettore(int array[] , int dim, int costante){
int i;
i = 0;
while(i < dim){
array[i] = array[i] + costante;
i = i + 1;

}

}
void main() {
int numeri[4];
int i;
i = 0;
while(i < 4){
scanf("%d", numeri+i);
i = i + 1;

}
sommaAlVettore(numeri45);
i = 0;
while(i < 4){
printf("%d",numeri[i]);
i = i + 1;

}
printf("ciao");

}