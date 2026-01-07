import os
import re
from pathlib import Path

INPUT_FILE = "SpringApplication.java"
OUTPUT_ROOT = "output"

PACKAGE_PATTERN = re.compile(r'^package\s+([\w\.]+);', re.MULTILINE)
TYPE_PATTERN = re.compile(
    r'(public\s+(?:class|interface|record|enum)\s+\w+[\s\S]*?)(?=\npackage\s+|\Z)',
    re.MULTILINE
)

CLASS_NAME_PATTERN = re.compile(
    r'public\s+(?:class|interface|record|enum)\s+(\w+)'
)


def ensure_dir(path: Path):
    path.mkdir(parents=True, exist_ok=True)


def split_by_package(content: str):
    packages = list(PACKAGE_PATTERN.finditer(content))
    result = []

    for i, match in enumerate(packages):
        start = match.start()
        end = packages[i + 1].start() if i + 1 < len(packages) else len(content)
        result.append(content[start:end].strip())

    return result


def extract_types(package_block: str):
    return TYPE_PATTERN.findall(package_block)


def extract_package_name(package_block: str):
    match = PACKAGE_PATTERN.search(package_block)
    if not match:
        raise ValueError("No package declaration found")
    return match.group(1)


def extract_class_name(type_block: str):
    match = CLASS_NAME_PATTERN.search(type_block)
    if not match:
        raise ValueError("No public type name found")
    return match.group(1)


def write_java_file(package_name: str, class_name: str, content: str):
    package_path = Path(OUTPUT_ROOT) / package_name.replace('.', '/')
    ensure_dir(package_path)

    file_path = package_path / f"{class_name}.java"
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(content.strip() + "\n")

    print(f"Created: {file_path}")


def main():
    with open(INPUT_FILE, "r", encoding="utf-8") as f:
        content = f.read()

    package_blocks = split_by_package(content)

    for package_block in package_blocks:
        package_name = extract_package_name(package_block)
        types = extract_types(package_block)

        for type_block in types:
            class_name = extract_class_name(type_block)

            java_source = f"package {package_name};\n\n" + type_block.strip()
            write_java_file(package_name, class_name, java_source)


if __name__ == "__main__":
    main()
