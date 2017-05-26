int main () {
	int x;
	char * str = "Hello World!";
	x=print_mystring(str);
}

int print_mystring(char * str){
	printf("string: %s \n", str);
	return 0;
}
