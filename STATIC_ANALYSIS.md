# Static Analysis Setup

## Tools Configured

1. **Checkstyle 11.1.0** - Style & metrics enforcement
2. **PMD 7.16.0** - Design & code smell detection
3. **Error Prone 2.40.0 + NullAway 0.12.7** - Compile-time null safety

---

## Coding Standards Enforcement Matrix

| Standard | Tool | Rule | Configuration |
|----------|------|------|---------------|
| **Method length ≤ 10 lines** | Checkstyle | MethodLength | max=10 |
| **Class length ≤ 100 lines** | Checkstyle | FileLength | max=100 |
| **Max 3 parameters** | Checkstyle | ParameterNumber | max=3 |
| **Cyclomatic complexity ≤ 3** | Checkstyle | CyclomaticComplexity | max=3 |
| **Cognitive complexity ≤ 3** | PMD | CognitiveComplexity | reportLevel=3 |
| **Max nesting depth = 1** | Checkstyle | NestedIfDepth, NestedForDepth, NestedTryDepth | max=1 |
| **No magic numbers** | Checkstyle | MagicNumber | ignoreNumbers=-1,0,1 |
| **Law of Demeter (no train wrecks)** | PMD | LawOfDemeter | trustRadius=1 |
| **Null return warnings** | Error Prone + NullAway | NullAway | Level=WARN |
| **Immutability (final params/vars)** | Checkstyle | FinalParameters, FinalLocalVariable | enabled |
| **camelCase/PascalCase naming** | Checkstyle | TypeName, MethodName, etc. | standard patterns |

---

## What's NOT Automated (Manual Code Review Required)

### Setter vs Behavioral Methods
**Your preference:** `receiveDamage(int)` over `setHealth(int)`

**Why no automation?**
No static analysis tool can distinguish semantic intent. Both look like regular methods to Checkstyle/PMD/SpotBugs.

**Recommendation:**
- Code review checklist item
- Search for regex pattern: `\bset[A-Z]\w+\(` during PR reviews
- Consider custom PMD/Checkstyle rule (requires Java development)

---

## Running Static Analysis

### During build (automatic)
```bash
mvn clean compile
```
All tools run during `validate` phase before compilation.

### Individual tools
```bash
# Checkstyle only
mvn checkstyle:check

# PMD only
mvn pmd:check

# Compile with Error Prone + NullAway
mvn compile
```

### Skip checks (for testing)
```bash
mvn compile -Dcheckstyle.skip -Dpmd.skip
```

---

## Interpreting Violations

### Checkstyle Output
```
[WARN] /path/to/File.java:[24,5] (metrics) CyclomaticComplexity:
       Cyclomatic Complexity is 5 (max allowed is 3).
```
- **[24,5]** = line 24, column 5
- **(metrics)** = rule category
- Fix: Extract methods to reduce branching logic

### PMD Output
PMD generates HTML reports in `target/pmd.html`

### Error Prone + NullAway Output
```
[WARNING] warning: [NullAway] dereferenced expression user.getAddress() is @Nullable
```
- Compile-time warnings for potential NPEs
- Add `@Nullable` annotations or null checks

---

## Tool Capabilities Summary

### ✅ Checkstyle Strengths
- Size metrics (method/class length, parameters)
- Cyclomatic complexity
- Nesting depth
- Naming conventions
- Magic numbers
- Immutability enforcement (final)

### ✅ PMD Strengths
- **Cognitive complexity** (unique to PMD!)
- **Law of Demeter** (unique to PMD!)
- Unused code detection
- Design smells (God classes, feature envy)
- Error-prone patterns

### ✅ Error Prone + NullAway Strengths
- **Compile-time null safety** (catches NPEs before runtime)
- JSpecify integration
- Modern Java bug patterns
- Performance anti-patterns

### ❌ What NO Tool Can Do
- Detect setter vs behavioral method semantics
- Understand domain-specific naming
- Judge "meaningful" variable names
- Evaluate test quality beyond coverage

---

## Configuration Files

- `pom.xml` - Maven plugin configuration
- `checkstyle.xml` - Checkstyle rules
- `pmd-ruleset.xml` - PMD rules
- Error Prone/NullAway configured in maven-compiler-plugin

---

## Examples

### ✅ Good Code (passes all rules)
See: `src/main/java/org/example/GoodExample.java`

### ❌ Bad Code (violates rules)
See: `src/main/java/org/example/BadExample.java.disabled`

Run with bad example:
```bash
mv src/main/java/org/example/BadExample.java.disabled \
   src/main/java/org/example/BadExample.java
mvn clean compile
# See all violations!
```

---

## Honest Assessment

**You asked for the truth about which tools to use:**

**MINIMUM (covers 90%):** Checkstyle + PMD
**IDEAL:** Checkstyle + PMD + Error Prone + NullAway
**SKIP:** SpotBugs (not useful for TDD/clean code discipline)

This setup enforces:
- ✅ Small functions
- ✅ Low complexity
- ✅ Simple XP-driven design
- ✅ TDD fundamentals
- ⚠️ Setter detection = **manual code review**

---

## Troubleshooting

### Error Prone IllegalAccessError on Java 9+
**Fixed** with JVM flags in `pom.xml`:
```xml
<arg>-J--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED</arg>
<!-- ... more exports ... -->
```

### PMD "Unable to find referenced rule" errors
**PMD 7 removed many deprecated rules.** Updated `pmd-ruleset.xml` with PMD 7-compatible rules.

### Build too slow
Skip static analysis during dev:
```bash
mvn compile -Dcheckstyle.skip -Dpmd.skip
```

Run only during CI/CD and pre-commit.
