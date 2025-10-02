# RPG Combat Java

Extreme TDD/XP project with strict static analysis enforcement.

## First-Time Setup (One Command)

```bash
./setup-hooks.sh
```

Done! Git hooks are now active and will enforce quality standards automatically.

## Development Workflow

```bash
# 1. RED: Write a failing test
mvn test

# 2. GREEN: Write minimal code to pass
mvn test

# 3. REFACTOR & COMMIT
git add .
git commit -m "Add feature X"
# ⚡ Static analysis runs automatically
# ❌ Commit blocked if violations found

# 4. PUSH when ready
git push
# ⚡ All tests run automatically
# ❌ Push blocked if tests fail
```

## Standards Enforced

| Standard | Limit |
|----------|-------|
| Method length | **10 lines** |
| Class length | **100 lines** |
| Parameters | **3 max** |
| Cyclomatic complexity | **≤ 3** |
| Cognitive complexity | **≤ 3** |
| Nesting depth | **≤ 1** |
| Magic numbers | **Not allowed** |
| Law of Demeter | **Enforced** |

## Automated Quality Gates

**Pre-commit (automatic):**
- Checkstyle validates code metrics
- PMD checks complexity & Law of Demeter
- Commit blocked if violations exist

**Pre-push (automatic):**
- All tests must pass
- Full compilation with Error Prone + NullAway
- Push blocked if tests fail

**⚠️ IMPORTANT:**
- Hooks **cannot** be bypassed
- Modifying `checkstyle.xml`, `pmd-ruleset.xml`, or static analysis config in `pom.xml` requires **explicit developer approval**
- Quality standards are **non-negotiable**

## Manual Commands

```bash
# Run static analysis only
mvn checkstyle:check pmd:check

# Run tests only
mvn test

# Full build
mvn clean compile
```

## Documentation

- **[CLAUDE.md](CLAUDE.md)** - Coding standards, TDD philosophy, examples
- **[STATIC_ANALYSIS.md](STATIC_ANALYSIS.md)** - Tool configuration details
- **[KATA.md](KATA.md)** - RPG Combat Kata requirements and user stories

---

## Requirements

- **Java 23**
- **Maven 3.6+**
- **Git**
