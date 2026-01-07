#!/usr/bin/env python3
"""
Split Spring interfaces and supporting classes into separate files.
Automatically creates proper package structure.
"""

import os
import re
from pathlib import Path

# Define package structure mapping
PACKAGE_MAPPING = {
    # Interfaces
    'ServiceInterface': 'be/kdg/se2/templates/spring/interfaces',
    'RepositoryInterface': 'be/kdg/se2/templates/spring/interfaces',
    'OptionalService': 'be/kdg/se2/templates/spring/interfaces',

    # Model
    'DataObject': 'be/kdg/se2/templates/spring/model',

    # Service Implementations
    'ServiceImplementation': 'be/kdg/se2/templates/spring/service/impl',
    'PrimaryServiceImplementation': 'be/kdg/se2/templates/spring/service/impl',
    'SecondaryServiceImplementation': 'be/kdg/se2/templates/spring/service/impl',
    'DefaultServiceImplementation': 'be/kdg/se2/templates/spring/service/impl',
    'DevelopmentServiceImplementation': 'be/kdg/se2/templates/spring/service/impl',
    'ProductionServiceImplementation': 'be/kdg/se2/templates/spring/service/impl',
    'TestServiceImplementation': 'be/kdg/se2/templates/spring/service/impl',

    # Controller
    'ControllerClass': 'be/kdg/se2/templates/spring/controller',
    'WebController': 'be/kdg/se2/templates/spring/controller',

    # Services
    'ConditionalService': 'be/kdg/se2/templates/spring/service',
    'FeatureService': 'be/kdg/se2/templates/spring/service',
    'DefaultService': 'be/kdg/se2/templates/spring/service',
    'DatabaseService': 'be/kdg/se2/templates/spring/service',
    'InitializableService': 'be/kdg/se2/templates/spring/service',
    'DependentService': 'be/kdg/se2/templates/spring/service',

    # Components
    'PrototypeBean': 'be/kdg/se2/templates/spring/component',
    'DebugLogger': 'be/kdg/se2/templates/spring/component',

    # Configuration
    'DatabaseConfig': 'be/kdg/se2/templates/spring/config',
    'DatabaseProperties': 'be/kdg/se2/templates/spring/config',
    'DataSource': 'be/kdg/se2/templates/spring/config',

    # Integration
    'ExternalIntegration': 'be/kdg/se2/templates/spring/integration',
    'MockIntegration': 'be/kdg/se2/templates/spring/integration',
    'MockExternalService': 'be/kdg/se2/templates/spring/integration',
}

def extract_class_info(content):
    """
    Extract class/interface definitions with their content.
    Returns list of tuples: (class_name, class_type, full_content, start_pos)
    """
    classes = []

    # Pattern to match class/interface declarations
    # Matches: public interface X, interface X, public class X, class X
    pattern = r'((?:public\s+)?(?:interface|class)\s+(\w+)(?:\s+implements\s+\w+)?(?:\s+extends\s+\w+)?)\s*\{'

    matches = list(re.finditer(pattern, content))

    for i, match in enumerate(matches):
        class_declaration = match.group(1)
        class_name = match.group(2)
        start_pos = match.start()

        # Find the end of this class (matching closing brace)
        brace_count = 0
        in_class = False
        end_pos = start_pos

        for j in range(match.end(), len(content)):
            char = content[j]
            if char == '{':
                brace_count += 1
                in_class = True
            elif char == '}':
                if brace_count == 0:
                    end_pos = j + 1
                    break
                brace_count -= 1

        # Extract full class content including Javadoc
        # Look backwards for Javadoc comment
        javadoc_start = start_pos
        lines_before = content[:start_pos].split('\n')

        # Check previous lines for Javadoc or comments
        for k in range(len(lines_before) - 1, max(0, len(lines_before) - 10), -1):
            line = lines_before[k].strip()
            if line.startswith('/**') or line.startswith('*'):
                # Found Javadoc, find its start
                for m in range(k, -1, -1):
                    if lines_before[m].strip().startswith('/**'):
                        javadoc_start = sum(len(l) + 1 for l in lines_before[:m])
                        break
                break
            elif line and not line.startswith('//'):
                # Non-comment, non-empty line - stop searching
                break

        full_content = content[javadoc_start:end_pos]

        # Determine if interface or class
        class_type = 'interface' if 'interface' in class_declaration else 'class'

        classes.append((class_name, class_type, full_content.strip(), javadoc_start))

    return classes

def get_package_declaration(package_path):
    """Convert package path to package declaration."""
    return 'package ' + package_path.replace('/', '.') + ';'

def get_imports(content, class_name):
    """Determine necessary imports based on content."""
    imports = set()

    # Check for common imports
    if 'List<' in content or 'List ' in content:
        imports.add('import java.util.List;')
    if 'Optional<' in content or 'Optional ' in content:
        imports.add('import java.util.Optional;')
    if 'ArrayList' in content:
        imports.add('import java.util.ArrayList;')
    if 'HashMap' in content:
        imports.add('import java.util.HashMap;')
    if 'Map<' in content or 'Map ' in content:
        imports.add('import java.util.Map;')

    # Add imports for referenced interfaces/classes
    if 'ServiceInterface' in content and class_name != 'ServiceInterface':
        imports.add('import be.kdg.se2.spring.interfaces.ServiceInterface;')
    if 'RepositoryInterface' in content and class_name != 'RepositoryInterface':
        imports.add('import be.kdg.se2.templates.spring.interfaces.RepositoryInterface;')
    if 'OptionalService' in content and class_name != 'OptionalService':
        imports.add('import be.kdg.se2.templates.spring.interfaces.OptionalService;')
    if 'DataObject' in content and class_name != 'DataObject':
        imports.add('import be.kdg.se2.templates.spring.model.DataObject;')
    if 'DatabaseConfig' in content and class_name != 'DatabaseConfig':
        imports.add('import be.kdg.se2.templates.spring.config.DatabaseConfig;')
    if 'DatabaseProperties' in content and class_name != 'DatabaseProperties':
        imports.add('import be.kdg.se2.templates.spring.config.DatabaseProperties;')

    return sorted(imports)

def create_java_file(class_name, class_type, content, package_path, base_dir):
    """Create a Java file with proper package and imports."""

    # Create directory structure
    full_path = os.path.join(base_dir, package_path)
    os.makedirs(full_path, exist_ok=True)

    # Get package declaration
    package_decl = get_package_declaration(package_path)

    # Get necessary imports
    imports = get_imports(content, class_name)

    # Remove visibility modifiers for interfaces in same package
    # Make public for classes that implement interfaces
    if class_type == 'interface':
        content = re.sub(r'^interface\s+', 'public interface ', content)
    else:
        # Make classes public if they implement interfaces or are named ...Implementation
        if 'implements' in content or class_name.endswith('Implementation'):
            content = re.sub(r'^class\s+', 'public class ', content)
        else:
            content = re.sub(r'^class\s+', 'public class ', content)

    # Build file content
    file_content = package_decl + '\n\n'

    if imports:
        file_content += '\n'.join(imports) + '\n\n'

    file_content += content + '\n'

    # Write file
    file_path = os.path.join(full_path, f'{class_name}.java')
    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(file_content)

    return file_path

def split_interfaces(input_file, base_dir='src/main/java'):
    """Main function to split the interfaces file."""

    # Read input file
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()

    # Extract all classes/interfaces
    classes = extract_class_info(content)

    if not classes:
        print("No classes or interfaces found!")
        return

    print(f"Found {len(classes)} classes/interfaces\n")

    created_files = []
    skipped = []

    for class_name, class_type, class_content, _ in classes:
        if class_name in PACKAGE_MAPPING:
            package_path = PACKAGE_MAPPING[class_name]

            try:
                file_path = create_java_file(
                    class_name,
                    class_type,
                    class_content,
                    package_path,
                    base_dir
                )
                created_files.append((class_name, file_path))
                print(f"✓ Created {class_name}.java → {package_path}/")
            except Exception as e:
                print(f"✗ Error creating {class_name}.java: {e}")
                skipped.append(class_name)
        else:
            print(f"⚠ Skipped {class_name} (not in package mapping)")
            skipped.append(class_name)

    # Summary
    print(f"\n{'='*70}")
    print(f"Summary:")
    print(f"  Created: {len(created_files)} files")
    print(f"  Skipped: {len(skipped)} files")
    print(f"\nPackage structure created under: {base_dir}/")

    # Show created structure
    if created_files:
        print(f"\nCreated files by package:")
        by_package = {}
        for class_name, file_path in created_files:
            package = os.path.dirname(file_path.replace(base_dir + '/', ''))
            if package not in by_package:
                by_package[package] = []
            by_package[package].append(class_name + '.java')

        for package in sorted(by_package.keys()):
            print(f"\n  {package}/")
            for filename in sorted(by_package[package]):
                print(f"    ├── {filename}")

def main():
    import sys

    if len(sys.argv) < 2:
        print("Usage: python3 split_spring_interfaces.py <input_file> [output_base_dir]")
        print("\nExample:")
        print("  python3 split_spring_interfaces.py SpringInterfaces.java")
        print("  python3 split_spring_interfaces.py SpringInterfaces.java src/main/java")
        print("\nThis will create the following structure:")
        print("  src/main/java/")
        print("    └── be/kdg/se2/templates/spring/")
        print("        ├── interfaces/")
        print("        │   ├── ServiceInterface.java")
        print("        │   ├── RepositoryInterface.java")
        print("        │   └── OptionalService.java")
        print("        ├── model/")
        print("        │   └── DataObject.java")
        print("        ├── service/")
        print("        │   └── impl/")
        print("        │       ├── ServiceImplementation.java")
        print("        │       └── ...")
        print("        ├── controller/")
        print("        ├── component/")
        print("        ├── config/")
        print("        └── integration/")
        sys.exit(1)

    input_file = sys.argv[1]
    base_dir = sys.argv[2] if len(sys.argv) > 2 else 'src/main/java'

    if not os.path.exists(input_file):
        print(f"Error: Input file '{input_file}' not found!")
        sys.exit(1)

    print(f"Splitting {input_file}...")
    print(f"Output directory: {base_dir}\n")

    split_interfaces(input_file, base_dir)

if __name__ == "__main__":
    main()