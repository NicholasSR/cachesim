# cachesim

**cachesim** is a Java-based cache simulation application designed to emulate real-world software development practices. This tool allows users to simulate cache behaviors with added functionality, including timing simulations and the ability to choose between least-recently-used (LRU) and direct-mapped cache mechanisms.

## Features

### Feature #1: Timing the Cache Simulation
- **Enhanced Metrics**: Tracks and reports the time taken for cache operations in addition to cache misses.
- **Refactored Code**: Encapsulation of backing store interactions into a dedicated `BackingStore` class.
- **Timing Details**: Calculates and includes timing metrics for `fetchData()` operations.
- **Cache Response Enhancements**:
  - Extended `CacheResponse` class to include timing information.
  - Added methods to retrieve and manipulate the timing data.
- **Output**:
  - Displays total simulation time in the output file.
  - Example format: `Total Time: X`, where `X` is the computed time in seconds.

### Feature #2: Direct-Mapped Cache
- **New Cache Type**: Implements a direct-mapped cache mechanism.
- **Behavior**:
  - Cache items are installed into specific positions based on their key.
  - The index is calculated as `key % cache_capacity`.
- **Polymorphism**:
  - The `DirectMappedCache` class extends the `Cache` class.
  - Only `findEvictCandidate()`, `evictData()`, and `installData()` methods are overridden for the new behavior.
- **Interactive Simulation**:
  - Allows users to select the type of cache to simulate (“L” for LRU and “D” for direct-mapped).
  - Ensures valid user input through defensive programming techniques.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/NicholasSR/cachesim.git
   ```
2. Navigate to the project directory:
   ```bash
   cd cachesim
   ```
3. Ensure you have a Java development environment set up (JDK 11 or later recommended).

## Usage
1. Compile the application:
   ```bash
   javac -d bin src/**/*.java
   ```
2. Run the simulation:
   ```bash
   java -cp bin CacheSimMain
   ```
3. Follow the prompts to:
   - Select the cache type (LRU or Direct-Mapped).
   - Provide input files as required.

## Development Workflow
- **Branching Strategy**:
  - Create a new branch for each feature or bug fix (e.g., `feature1` for timing simulation, `feature2` for direct-mapped cache).
  - Frequently commit changes to ensure a clean and incremental development process.
- **Testing**:
  - Unit tests are located in the `test` directory.
  - Use the `CacheTest` as a reference for writing additional test cases.
  - Run all tests to ensure regression-free updates.
- **Code Documentation**:
  - All methods and classes include thorough Javadoc comments.
  - Follow consistent coding and style guidelines throughout the project.

---

**cachesim** is built with a focus on professional software development practices, emphasizing clean code, modularity, and test-driven development.

