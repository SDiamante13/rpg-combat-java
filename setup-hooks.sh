#!/bin/sh
#
# Setup Git Hooks
# Run this once after cloning the repository
#

echo "⚙️  Setting up Git hooks..."

# Configure Git to use .githooks directory
git config core.hooksPath .githooks

# Make hooks executable (Unix/Mac)
chmod +x .githooks/pre-commit
chmod +x .githooks/pre-push

echo "✅ Git hooks configured!"
echo ""
echo "Hooks installed:"
echo "  • pre-commit  → Runs static analysis (Checkstyle + PMD)"
echo "  • pre-push    → Runs all tests + compilation"
echo ""
echo "⚠️  IMPORTANT:"
echo "  • Bypassing hooks (--no-verify) is NOT allowed"
echo "  • Modifying checkstyle.xml, pmd-ruleset.xml, or pom.xml"
echo "    static analysis config requires explicit team approval"
