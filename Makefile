JAVA_FILES=${wildcard *.java}
EXEC_FILE=SessionManager


run: ${JAVA_FILES}
	@javac ${JAVA_FILES}
	@java ${EXEC_FILE}
	@echo

format: ${JAVA_FILES}
	@clang-format -i ${JAVA_FILES}

clean:
	@yes | rm *.class

.PHONY: run format clean