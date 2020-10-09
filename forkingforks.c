#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
int main()
{
	fork();
	fork();
	fork();
	printf("hello\n");
	sleep(20000);
	return 0;
}
