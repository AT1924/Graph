PROJECT = graph
RUNCOMMAND = graph.App

######## DO NOT MODIFY ANYTHING BELOW THIS LINE #######
COURSEDIR = /course/cs016

include $(COURSEDIR)/include/Makefile.common

TESTS =  javac -cp /course/cs016/lib/nds4/nds4.jar:/course/cs016/lib/junit-4.12.jar:/course/cs016/lib/hamcrest-core-1.3.jar:/course/cs016/lib/cs016.jar *.java


RUN_TESTS = java -cp /course/cs016/lib/nds4/nds4.jar:/course/cs016/lib/junit-4.12.jar:/course/cs016/lib/hamcrest-core-1.3.jar:/course/cs016/lib/cs016.jar:.:.. graph.TestRunner

tests:
		$(TESTS)
		       
run_tests:
		$(RUN_TESTS)
