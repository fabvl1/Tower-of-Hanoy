# Tower of Hanoi - Documentation

## Overview

This project implements the classic Tower of Hanoi puzzle using Java. It was developed as a school assignment to practice programming logic, recursion, and Java fundamentals. The repository includes both the Java source code and a precompiled Windows executable (.exe) for convenient execution.

---

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Requirements](#requirements)
4. [Installation](#installation)
5. [Usage](#usage)
6. [How It Works](#how-it-works)
7. [File Structure](#file-structure)
8. [Contributing](#contributing)
9. [License](#license)

---

## Introduction

The Tower of Hanoi is a mathematical puzzle consisting of three rods and a number of disks of different sizes. The goal is to move the entire stack from one rod to another, following these rules:

- Only one disk may be moved at a time.
- Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack or on an empty rod.
- No disk may be placed on top of a smaller disk.

---

## Features

- Full implementation in Java.
- Command-line interface for user input.
- Displays each move step-by-step.
- Handles input validation and error messages.
- Includes a Windows executable (.exe) for quick testing.

---

## Requirements

- Java JDK 8 or above (for source code usage).
- Windows OS (for executable usage).

---

## Installation

### Using Java Source

1. Clone the repository:
   ```bash
   git clone https://github.com/fabvl1/Tower-of-Hanoy.git
   ```
2. Compile the Java source:
   ```bash
   javac Main.java
   ```
   Replace `Main.java` with the appropriate file containing the `main` method.

### Using Executable

1. Download the `Tower-of-Hanoi.exe` file from the repository.
2. Double-click the `.exe` file to run, or execute from the command line:
   ```bash
   Tower-of-Hanoi.exe
   ```

---

## Usage

### Running with Java

After compiling, run with:
```bash
java Main
```

You will be prompted to enter the number of disks. The program will display the solution step-by-step.

### Running the Executable

Just launch `Tower-of-Hanoi.exe` and follow the on-screen instructions.

---

## How It Works

The program uses a recursive algorithm to solve the Tower of Hanoi puzzle. The core logic is based on:

1. Move (n-1) disks from source to auxiliary rod.
2. Move the nth disk from source to destination rod.
3. Move (n-1) disks from auxiliary rod to destination rod.

All moves are printed to the console for easy visualization.

---

## File Structure

```
Tower-of-Hanoy/
├── Main.java
├── TowerOfHanoi.java
├── Tower-of-Hanoi.exe
├── README.md
├── DOCUMENTATION.md
```
- `Main.java`: Entry point of the application.
- `TowerOfHanoi.java`: Contains the logic for solving the puzzle.
- `Tower-of-Hanoi.exe`: Compiled executable for Windows.
- `README.md`: Project summary and quick start.
- `DOCUMENTATION.md`: This documentation file.

---

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your improvements.

---

## License

This project is intended for educational purposes only.