JC =javac
.SUFFIXES:.java .class
.java.class:
	$(JC) $*.java


default: 
	@javac -d . classes/*.java
	@javac -d . controladors/*.java
	@javac -d . drivers/*.java
	
	@jar cfe ../EXE/DriverCtrlDomini.jar drivers.DriverCtrlDomini drivers/DriverCtrlDomini.class controladors/*.class classes/*.class
	@jar cfe ../EXE/DriverCtrlDocument.jar drivers.DriverCtrlDocument drivers/DriverCtrlDocument.class controladors/*.class classes/*.class
	@jar cfe ../EXE/DriverFuncions.jar drivers.DriverFuncions drivers/DriverFuncions.class controladors/*.class classes/*.class
	@jar cfe ../EXE/DriverDocument.jar drivers.DriverDocument drivers/DriverDocument.class controladors/*.class classes/*.class
	@jar cfe ../EXE/DriverFull.jar drivers.DriverFull drivers/DriverFull.class controladors/*.class classes/*.class
	@jar cfe ../EXE/DriverCela.jar drivers.DriverCela drivers/DriverCela.class controladors/*.class classes/*.class

	@javac -sourcepath . -classpath "lib/*" test/*.java -d .
	@jar cfe ../EXE/Tests/FuncionsTest.jar test.FuncionsTest classes/*.class
	@jar cfe ../EXE/Tests/DocumentsTest.jar test.CelaTest classes/*.class
	@jar cfe ../EXE/Tests/FullTest.jar test.FulTest classes/*.class
	@jar cfe ../EXE/Tests/CelaTest.jar test.CelaTest classes/*.class

all: default

clean:
	rm classes/*.class
	rm controladors/*.class
	rm drivers/*.class
	rm test/*.class
	rm ../EXE/*.jar
	rm ../EXE/Test*.jar

DriverCtrlDomini: default
	@java drivers.DriverCtrlDomini

DriverCtrlDocument: default
	@java drivers.DriverCtrlDocument

DriverFuncions: default
	@java drivers.DriverFuncions

DriverDocument: default
	@java drivers.DriverDocument

DriverFull: default
	@java drivers.DriverFull

DriverCela: default
	@java drivers.DriverCela

FuncionsTest: default
	@java -jar lib/junit-platform-console-standalone-1.8.1.jar -cp . --select-class test.FuncionsTest

DocumentTest: default
	@java -jar lib/junit-platform-console-standalone-1.8.1.jar -cp . --select-class test.DocumentTest

FullTest: default
	@java -jar lib/junit-platform-console-standalone-1.8.1.jar -cp . --select-class test.FullTest

CelaTest: default
	@java -jar lib/junit-platform-console-standalone-1.8.1.jar -cp . --select-class test.CelaTest
