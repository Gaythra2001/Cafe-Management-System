# Cafe Management System

Secure Swing desktop app for cafes: manage users, catalog, orders, and PDF bills backed by SQLite.

## Why It's Useful
- **Secure authentication** with SQL injection protection and admin approval workflow
- Username-based auth with account recovery (security Q/A, forgot password)
- Full menu and category maintenance; order flow generates printable PDF bills
- Modernized login/signup UX with real-time validation and password visibility toggle
- Portable: ships with SQLite; first run creates the local DB automatically
- Professional error logging and resource management—no memory leaks

## Tech Snapshot
- Java 21 + Swing UI
- SQLite (jdbc:sqlite) with WAL mode for concurrency
- **Secure**: PreparedStatements prevent SQL injection attacks
- **Logging**: Java Logger for production-ready error tracking
- JARs: sqlite-jdbc-simple.jar, AbsoluteLayout.jar, itextpdf-5.5.9.jar

## Quickstart
- Prereq: JDK 21 on PATH (Windows or any OS with Java)
- Run without rebuilding:
```bash
./run.bat
# or
java -cp "build/classes;lib/sqlite-jdbc-simple.jar;lib/absolutelayout/AbsoluteLayout.jar;lib/itextpdf-5.5.9.jar" cafe.management.system.CafeManagementSystem
```

### Demo Credentials (Pre-Loaded Admin)
- **Username:** admin
- **Password:** nnkk
- **Email:** admin@gmail.com
- **Status:** Already approved

## Build From Source
```bash
javac -d build/classes -cp "build/classes;lib/sqlite-jdbc-simple.jar;lib/absolutelayout/AbsoluteLayout.jar;lib/itextpdf-5.5.9.jar" src/cafe/management/system/*.java src/dao/*.java src/model/*.java src/common/*.java
```

## Data + Files
- DB lives at `%USERPROFILE%/cafe_management_system.db` (WAL mode on); auto-created on first run
- **Security**: All queries use parameterized PreparedStatements to prevent SQL injection
- **Performance**: Foreign keys enforced, cache optimized, 5-second busy timeout
- New signups stay pending until approved via Verify Users
- Keep bundled libraries in [lib](lib) and images in [src/images](src/images) when redistributing

## Product Walkthrough
- Login: username + password, forgot password, create account, exit/clear controls
- Signup: name, username, email, mobile, address, password, security Q/A with validation
- Home: entry to categories, products, orders, bills
- Manage Menu: add/edit/delete categories and products
- Place Order: build ticket, total, generate bill, export PDF
- Bills & History: view prior bills and order details
- Verify Users: approve or reject pending accounts

## Troubleshooting
- Login blocked? Approve the user in Verify Users first.
- Missing DB file? Launch the app once; it creates the SQLite file automatically.
- PDF export failing? Write to a folder you have permission for.
- Java errors? Confirm JDK 21 is installed and `java`/`javac` are on PATH.

## Tips
- Ship the `lib` jars alongside the compiled classes when distributing.
- If you customize layouts, remember the UI uses AbsoluteLayout.

## Security Features
- **SQL Injection Protection**: All database queries use PreparedStatements with parameterized inputs
- **Error Handling**: Professional logging with java.util.logging.Logger instead of stack traces
- **Resource Management**: Proper connection and statement cleanup prevents leaks
- **Input Validation**: Real-time field validation on login and signup forms
- **Session Management**: Secure user authentication with status verification
