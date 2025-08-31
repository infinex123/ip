#!/usr/bin/env bash
set -euo pipefail

# === set this to your main class ===
MAIN_CLASS="Clover"        # e.g. "ip.clover.Clover" if you use: package ip;

# (optional) use a specific JDK: JAVAC="/c/Program Files/Java/jdk-17/bin/javac"; JAVA="/c/Program Files/Java/jdk-17/bin/java"
JAVAC="${JAVAC:-javac}"
JAVA="${JAVA:-java}"

# create bin
mkdir -p ../bin

# clean previous output
rm -f ACTUAL.TXT EXPECTED-UNIX.TXT sources.txt

# collect ALL sources (handles packages/subfolders)
find ../src/main/java -name "*.java" > sources.txt

# compile (to ../bin)
if ! "$JAVAC" -Xlint:none -d ../bin -cp ../src/main/java @sources.txt; then
  echo "********** BUILD FAILURE **********"
  exit 1
fi

# run program -> ACTUAL.TXT
"$JAVA" -classpath ../bin "$MAIN_CLASS" < input.txt > ACTUAL.TXT

# normalize line endings (best-effort if dos2unix isn't installed)
cp EXPECTED.TXT EXPECTED-UNIX.TXT
if command -v dos2unix >/dev/null 2>&1; then
  dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT >/dev/null 2>&1 || true
else
  perl -pi -e 's/\r\n/\n/g' ACTUAL.TXT EXPECTED-UNIX.TXT 2>/dev/null || true
fi

# compare
if diff -u EXPECTED-UNIX.TXT ACTUAL.TXT; then
  echo "Test result: PASSED"
  exit 0
else
  echo "Test result: FAILED"
  exit 1
fi
