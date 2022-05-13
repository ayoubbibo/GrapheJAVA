all: compile

compile1:
	javac -cp app/bin app/src/graph/*.java -d app/bin

jar: compile1
	jar cvfm app/Graph.jar app/META-INF/MANIFEST.MF -C app/bin graph

compile2:
	javac -cp implementation/graph.jar implementation/src/graph.impl/*.java -d implementation/bin

compile: compile1 jar compile2
	echo Compiled

execute:
	java -cp implementation/Graph.jar:implementation/bin graph/impl/IncidenceArrayGraph