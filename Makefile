PROTOC=protoc

proto:
	$(PROTOC) --java_out=src/main/java/ condor.proto


clean:
	rm -f src/main/java/*.java
