SRC = Kernel.java Library.java Boot.java Disk.java Shell.java Count.java \
	GetOpt.java # DiskTester.java

all: compile run

compile:
	javac -Xlint:all $(SRC)

run:
	java -enableassertions Boot 10 Disk 100 Shell Count

clean:
	$(RM) *.class DISK
