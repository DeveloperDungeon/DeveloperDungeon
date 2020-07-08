@echo off
protoc --proto_path=src/main/resources/proto --java_out=src/main/java src/main/resources/proto/dev-user.proto
