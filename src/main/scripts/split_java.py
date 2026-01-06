#!/usr/bin/env python3
"""
Java Class Splitter for Design Patterns
Splits a single Java file containing multiple classes into separate files.
"""

import re
import os
import sys

def find_class_boundaries(content):
    """
    Find all class/interface declarations and their boundaries.
    """
    lines = content.split('\n')
    classes = []
    current_class = None
    brace_count = 0
    in_class = False

    for i, line in enumerate(lines):
        stripped = line.strip()

        # Look for class/interface/enum declarations
        class_match = re.match(r'^((?:public\s+|private\s+|protected\s+|static\s+|final\s+|abstract\s+)*(?:class|interface|enum)\s+(\w+))', stripped)

        if class_match and not in_class:
            # Found a new top-level class
            current_class = {
                'name': class_match.group(2),
                'start_line': i,
                'declaration': class_match.group(1),
                'content_lines': []
            }
            in_class = True
            brace_count = 0

        if in_class:
            current_class['content_lines'].append(line)

            # Count braces to find end of class
            for char in line:
                if char == '{':
                    brace_count += 1
                elif char == '}':
                    brace_count -= 1

                    if brace_count == 0:
                        # End of class found
                        classes.append(current_class)
                        in_class = False
                        current_class = None
                        break

    return classes

def split_java_classes(input_file, output_dir):
    """
    Reads a Java file and splits it into separate files for each class/interface.
    """

    # Create output directory if it doesn't exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)
        print(f"Created output directory: {output_dir}")

    # Read the input file
    with open(input_file, 'r') as f:
        content = f.read()

    # Find all classes
    classes = find_class_boundaries(content)

    if not classes:
        print("No classes found! Make sure the input file contains valid Java classes.")
        return

    classes_created = 0

    for cls in classes:
        class_name = cls['name']
        class_content = '\n'.join(cls['content_lines'])

        # Write to separate file
        output_file = os.path.join(output_dir, f"{class_name}.java")

        with open(output_file, 'w') as f:
            # Add any preceding comments if they exist
            f.write(class_content.strip() + '\n')

        print(f"Created: {class_name}.java")
        classes_created += 1

    print(f"\nTotal classes/interfaces created: {classes_created}")
    print(f"Output directory: {os.path.abspath(output_dir)}")

def main():
    if len(sys.argv) < 2:
        print("Usage: python3 split_java.py <input_file.java> [output_directory]")
        print("\nExample:")
        print("  python3 split_java.py DesignPatterns.java ./patterns")
        sys.exit(1)

    input_file = sys.argv[1]
    output_dir = sys.argv[2] if len(sys.argv) > 2 else "split_classes"

    if not os.path.exists(input_file):
        print(f"Error: Input file '{input_file}' not found!")
        sys.exit(1)

    print(f"Splitting {input_file}...")
    print(f"Output directory: {output_dir}\n")

    split_java_classes(input_file, output_dir)

if __name__ == "__main__":
    main()