JC =javac
.SUFFIXES:.java .class
.java.class:
	$(JC) $*.java

default: 
	@javac classes/domain/Function.java
	@javac -sourcepath . -classpath "lib/*" classes/Parser.java
	@javac -sourcepath . -classpath "lib/*" classes/domain/Cell.java
	@javac -sourcepath . -classpath "lib/*" classes/domain/Sheet.java
	@javac -sourcepath . -classpath "lib/*" classes/domain/Document.java
	@javac -sourcepath . -classpath "lib/*" classes/persistence/ExportToCsv.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/AddColumnsDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/AddDocNameDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/AddDocPathDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/AddRowsDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/AddSheetNameDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/DeleteColumnsDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/DeleteRowsDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/SearchAndReplaceValuesDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/SearchValueDialogView.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/SortCellsBlocDialogView.java
	@javac -sourcepath . -classpath "lib/*" controllers/*.java
	@javac -sourcepath . -classpath "lib/*" classes/presentation/MainView.java

	@jar xf lib/forms_rt-6.0.5.jar
	@jar xf lib/javax.json-1.1.4.jar

	@jar cfe ../EXE/main.jar controllers.CtrlPresentation controllers/CtrlPresentation.class controllers/*.class classes/domain/*.class classes/Parser.class classes/presentation/*.class classes/persistence/*.class com/intellij/uiDesigner/*.class com/intellij/uiDesigner/compiler/*.class com/intellij/uiDesigner/core/*.class com/intellij/uiDesigner/lw/*.class com/intellij/uiDesigner/shared/*.class javax/json/*.class javax/json/stream/*.class javax/json/spi/*.class org/glassfish/json/*.class org/glassfish/json/api/*.class

all: default

clean:
	rm -f classes/Parser.class
	rm -f classes/domain/*.class
	rm -f classes/presentation/*.class
	rm -f classes/persistence/*.class
	rm -f controllers/*.class
	rm -rf com
	rm -rf javax
	rm -rf META-INF
	rm -rf org
