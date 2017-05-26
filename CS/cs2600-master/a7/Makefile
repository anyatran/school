TARGET=myshell

all:
	gcc -o $(TARGET) hw7.c

run: all
	./$(TARGET)

dist:
	dir=`basename $$PWD`; cd ..; tar cvf $$dir.tar ./$$dir; gzip $$dir.tar
	dir=`basename $$PWD`; ls -l ../$$dir.tar.gz
