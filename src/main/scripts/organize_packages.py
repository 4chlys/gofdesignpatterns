#!/usr/bin/env python3
"""
Organize Design Patterns into Package Structure
Moves existing Java files into organized package directories.
"""

import os
import re
import shutil
import sys

# Define package structure for design patterns
PATTERN_PACKAGES = {
    # Creational Patterns - Singleton
    'Singleton': 'creational/singleton',
    'EagerSingleton': 'creational/singleton',

    # Creational Patterns - Factory Method
    'Product': 'creational/factory',
    'ConcreteProductA': 'creational/factory',
    'ConcreteProductB': 'creational/factory',
    'Creator': 'creational/factory',
    'ConcreteCreatorA': 'creational/factory',
    'ConcreteCreatorB': 'creational/factory',

    # Creational Patterns - Abstract Factory
    'AbstractProductA': 'creational/abstractfactory',
    'AbstractProductB': 'creational/abstractfactory',
    'ProductA1': 'creational/abstractfactory',
    'ProductA2': 'creational/abstractfactory',
    'ProductB1': 'creational/abstractfactory',
    'ProductB2': 'creational/abstractfactory',
    'AbstractFactory': 'creational/abstractfactory',
    'ConcreteFactory1': 'creational/abstractfactory',
    'ConcreteFactory2': 'creational/abstractfactory',

    # Creational Patterns - Builder
    'Builder': 'creational/builder',
    'ConcreteBuilder': 'creational/builder',
    'Director': 'creational/builder',

    # Creational Patterns - Prototype
    'Prototype': 'creational/prototype',
    'ConcretePrototype': 'creational/prototype',

    # Structural Patterns - Adapter
    'Adaptee': 'structural/adapter',
    'Target': 'structural/adapter',
    'Adapter': 'structural/adapter',

    # Structural Patterns - Bridge
    'Implementor': 'structural/bridge',
    'ConcreteImplementorA': 'structural/bridge',
    'ConcreteImplementorB': 'structural/bridge',
    'Abstraction': 'structural/bridge',
    'RefinedAbstraction': 'structural/bridge',

    # Structural Patterns - Composite
    'Component': 'structural/composite',
    'Leaf': 'structural/composite',
    'Composite': 'structural/composite',

    # Structural Patterns - Decorator
    'ComponentInterface': 'structural/decorator',
    'ConcreteComponent': 'structural/decorator',
    'Decorator': 'structural/decorator',
    'ConcreteDecoratorA': 'structural/decorator',

    # Structural Patterns - Facade
    'SubsystemA': 'structural/facade',
    'SubsystemB': 'structural/facade',
    'SubsystemC': 'structural/facade',
    'Facade': 'structural/facade',

    # Structural Patterns - Flyweight
    'Flyweight': 'structural/flyweight',
    'ConcreteFlyweight': 'structural/flyweight',
    'FlyweightFactory': 'structural/flyweight',

    # Structural Patterns - Proxy
    'Subject': 'structural/proxy',
    'RealSubject': 'structural/proxy',
    'Proxy': 'structural/proxy',

    # Behavioral Patterns - Chain of Responsibility
    'Handler': 'behavioral/chainofresponsibility',
    'ConcreteHandler1': 'behavioral/chainofresponsibility',
    'ConcreteHandler2': 'behavioral/chainofresponsibility',

    # Behavioral Patterns - Command
    'Command': 'behavioral/command',
    'Receiver': 'behavioral/command',
    'ConcreteCommand': 'behavioral/command',
    'Invoker': 'behavioral/command',

    # Behavioral Patterns - Iterator
    'Iterator': 'behavioral/iterator',
    'Aggregate': 'behavioral/iterator',
    'ConcreteAggregate': 'behavioral/iterator',
    'ConcreteIterator': 'behavioral/iterator',

    # Behavioral Patterns - Mediator
    'Mediator': 'behavioral/mediator',
    'Colleague': 'behavioral/mediator',
    'ConcreteColleague1': 'behavioral/mediator',
    'ConcreteColleague2': 'behavioral/mediator',
    'ConcreteMediator': 'behavioral/mediator',

    # Behavioral Patterns - Memento
    'Memento': 'behavioral/memento',
    'Originator': 'behavioral/memento',
    'Caretaker': 'behavioral/memento',

    # Behavioral Patterns - Observer
    'Observer': 'behavioral/observer',
    'ConcreteObserver': 'behavioral/observer',

    # Behavioral Patterns - State
    'State': 'behavioral/state',
    'ConcreteStateA': 'behavioral/state',
    'ConcreteStateB': 'behavioral/state',
    'Context': 'behavioral/state',

    # Behavioral Patterns - Strategy
    'Strategy': 'behavioral/strategy',
    'ConcreteStrategyA': 'behavioral/strategy',
    'ConcreteStrategyB': 'behavioral/strategy',
    'StrategyContext': 'behavioral/strategy',

    # Behavioral Patterns - Template Method
    'AbstractClass': 'behavioral/template',
    'ConcreteClass': 'behavioral/template',

    # Behavioral Patterns - Visitor
    'Visitor': 'behavioral/visitor',
    'ConcreteVisitor': 'behavioral/visitor',
    'Element': 'behavioral/visitor',
    'ConcreteElementA': 'behavioral/visitor',
    'ConcreteElementB': 'behavioral/visitor',

    # Behavioral Patterns - Interpreter
    'Expression': 'behavioral/interpreter',
    'TerminalExpression': 'behavioral/interpreter',
    'OrExpression': 'behavioral/interpreter',
    'AndExpression': 'behavioral/interpreter',
}

def update_package_declaration(file_path, new_package):
    """
    Updates or adds the package declaration in a Java file.
    """
    with open(file_path, 'r') as f:
        content = f.read()

    # Convert path separators to package notation
    package_name = new_package.replace('/', '.')

    # Check if package declaration exists
    if re.search(r'^package\s+[\w.]+\s*;', content, re.MULTILINE):
        # Replace existing package
        content = re.sub(
            r'^package\s+[\w.]+\s*;',
            f'package {package_name};',
            content,
            flags=re.MULTILINE
        )
    else:
        # Add package declaration at the beginning
        content = f'package {package_name};\n\n{content}'

    with open(file_path, 'w') as f:
        f.write(content)

def organize_patterns(source_dir, base_package):
    """
    Organizes pattern files into proper package structure.
    """
    if not os.path.exists(source_dir):
        print(f"Error: Source directory '{source_dir}' not found!")
        sys.exit(1)

    # Get all Java files in source directory
    java_files = [f for f in os.listdir(source_dir) if f.endswith('.java')]

    if not java_files:
        print(f"No Java files found in {source_dir}")
        sys.exit(1)

    print(f"Found {len(java_files)} Java files")
    print(f"Organizing into package structure under: {base_package}\n")

    moved_count = 0
    skipped_count = 0

    for java_file in java_files:
        class_name = java_file.replace('.java', '')

        if class_name in PATTERN_PACKAGES:
            # Get target package path
            package_path = PATTERN_PACKAGES[class_name]
            full_package = f"{base_package}.{package_path}".replace('/', '.')

            # Create target directory
            target_dir = os.path.join(source_dir, package_path)
            os.makedirs(target_dir, exist_ok=True)

            # Move file
            source_file = os.path.join(source_dir, java_file)
            target_file = os.path.join(target_dir, java_file)

            shutil.move(source_file, target_file)

            # Update package declaration
            update_package_declaration(target_file, full_package)

            print(f"✓ Moved {class_name}.java → {package_path}/")
            moved_count += 1
        else:
            print(f"⚠ Skipped {class_name}.java (not in pattern mapping)")
            skipped_count += 1

    print(f"\n{'='*60}")
    print(f"Organization complete!")
    print(f"  Moved: {moved_count} files")
    print(f"  Skipped: {skipped_count} files")
    print(f"\nPackage structure created:")
    print(f"  {base_package}.creational/")
    print(f"    ├── singleton/")
    print(f"    ├── factory/")
    print(f"    ├── abstractfactory/")
    print(f"    ├── builder/")
    print(f"    └── prototype/")
    print(f"  {base_package}.structural/")
    print(f"    ├── adapter/")
    print(f"    ├── bridge/")
    print(f"    ├── composite/")
    print(f"    ├── decorator/")
    print(f"    ├── facade/")
    print(f"    ├── flyweight/")
    print(f"    └── proxy/")
    print(f"  {base_package}.behavioral/")
    print(f"    ├── chainofresponsibility/")
    print(f"    ├── command/")
    print(f"    ├── iterator/")
    print(f"    ├── mediator/")
    print(f"    ├── memento/")
    print(f"    ├── observer/")
    print(f"    ├── state/")
    print(f"    ├── strategy/")
    print(f"    ├── template/")
    print(f"    ├── visitor/")
    print(f"    └── interpreter/")

def main():
    if len(sys.argv) < 3:
        print("Usage: python3 organize_packages.py <source_directory> <base_package>")
        print("\nExample:")
        print("  python3 organize_packages.py ./patterns be.kdg.se2.gofdesignpatterns")
        print("\nThis will organize files into:")
        print("  ./patterns/creational/singleton/")
        print("  ./patterns/structural/adapter/")
        print("  ./patterns/behavioral/observer/")
        print("  etc.")
        sys.exit(1)

    source_dir = sys.argv[1]
    base_package = sys.argv[2]

    organize_patterns(source_dir, base_package)

if __name__ == "__main__":
    main()