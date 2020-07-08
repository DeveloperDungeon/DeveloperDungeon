@echo off
protoc --proto_path=src/main/resources/proto --js_out=import_style=commonjs,binary:src/main/resources/js-modules src/main/resources/proto/%1.proto