# Coding Standards & Philosophy

## TDD Mindset

**Uncle Bob's 3 Laws of TDD:**
1. Write no production code except to pass a failing test
2. Write only enough of a test to demonstrate a failure
3. Write only enough production code to pass the test

**Small Steps:**
- One test at a time
- One assertion per test (when possible)
- Refactor after green
- Commit after each red-green-refactor cycle

**The Cycle:**
```
RED → GREEN → REFACTOR → COMMIT
```

---

## Code Quality Standards

### Method & Class Size
- **Max method length:** 10 lines
- **Max class length:** 100 lines
- **Max parameters:** 3

### Complexity Limits
- **Cyclomatic complexity:** ≤ 3
- **Cognitive complexity:** ≤ 3
- **Nesting depth:** ≤ 1

### Design Principles
- **Single Responsibility Principle:** One reason to change
- **Law of Demeter:** Don't talk to strangers (no train wrecks)
- **Prefer behavioral methods:** `receiveDamage(int)` over `setHealth(int)`
- **Prefer Value Objects:** Wrap primitives when they have behavior
- **Prefer immutability:** Use `final` for parameters and local variables

### Naming Conventions
- Classes: `PascalCase`
- Methods/variables: `camelCase`
- Constants: `UPPER_SNAKE_CASE`
- Tests: `ClassNameShould.java`
- Descriptive names that reveal intent

### Code Cleanliness
- No magic numbers (extract to constants)
- No comments (code should be self-documenting)
- No nested ifs/loops beyond depth 1
- TODO comments allowed during development

---

## Static Analysis

Build fails if violations detected:
```bash
mvn clean compile
```

**Tools enforcing standards:**
- **Checkstyle:** Size, complexity, nesting, naming, magic numbers
- **PMD:** Cognitive complexity, Law of Demeter, design smells
- **Error Prone + NullAway:** Null safety at compile time

See `STATIC_ANALYSIS.md` for detailed tool configuration.

---

## Workflow

### Before Writing Code
1. Write a failing test (RED)
2. Run test suite to confirm failure
3. Write minimal code to pass (GREEN)
4. Run test suite to confirm pass
5. Refactor if needed
6. Commit

### Code Review Checklist
- [ ] All tests pass
- [ ] No static analysis violations
- [ ] Methods ≤ 10 lines
- [ ] Complexity ≤ 3
- [ ] No setters (use behavioral methods)
- [ ] No magic numbers
- [ ] Descriptive names
- [ ] Committed with test

---

## Examples

### ✅ Good: Behavioral Method
```java
public void receiveDamage(final int damage) {
    this.health = Math.max(0, this.health - damage);
}
```

### ❌ Bad: Setter
```java
public void setHealth(int health) {
    this.health = health;
}
```

### ✅ Good: Small, Focused Method
```java
public int calculateDamage(final int level) {
    if (level > MIN_DAMAGE) {
        return HIGH_DAMAGE;
    }
    if (level > 0) {
        return MEDIUM_DAMAGE;
    }
    return MIN_DAMAGE;
}
```

### ❌ Bad: Complex, Long Method
```java
public void complexMethod(String name, int level, double multiplier, boolean flag) {
    int damage = 10; // magic number!

    if (level > 5) {
        if (flag) {
            if (health > 100) {  // nested too deep!
                damage = 20;
            } else {
                damage = 15;
            }
        }
    }
    // ... more logic
}
```

### ✅ Good: No Train Wrecks
```java
private final Address address;

public String getCity() {
    return address.city();
}
```

### ❌ Bad: Law of Demeter Violation
```java
public String getCity() {
    return user.getAddress().getCity(); // train wreck!
}
```

---

## Philosophy

**Simplicity over cleverness.**
**Readability over performance** (optimize only when proven necessary).
**Small functions that do one thing well.**
**Tests are documentation.**
**Refactor ruthlessly.**

When in doubt, make it smaller.
