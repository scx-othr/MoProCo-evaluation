#!/usr/bin/env python3
import sys
from pathlib import Path

def count_file(path: Path) -> int:
    loc = 0
    in_block_comment = False

    with path.open(encoding="utf-8", errors="ignore") as f:
        for line in f:
            stripped = line.strip()

            # Handle block comments
            if in_block_comment:
                if "*/" in stripped:
                    in_block_comment = False
                continue

            if not stripped:
                continue                  # blank line
            if stripped == "}":
                continue                  # closing brace only
            if stripped.startswith("//"):
                continue                  # single-line comment
            if stripped.startswith("/*"):
                if not stripped.endswith("*/"):
                    in_block_comment = True
                continue                  # block comment line

            loc += 1

    return loc


def count_path(path: Path) -> int:
    if path.is_file():
        return count_file(path)

    total = 0
    for file in path.rglob("*.java"):
        total += count_file(file)
    return total


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: loc.py <file-or-directory>")
        sys.exit(1)

    target = Path(sys.argv[1])
    if not target.exists():
        print(f"Path does not exist: {target}")
        sys.exit(1)

    print(f"Lines of code: {count_path(target)}")
