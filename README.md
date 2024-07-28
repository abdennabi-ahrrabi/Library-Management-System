echo "# Library Management System

A simple Library Management System built using Java and PostgreSQL.

## Features

- Add, update, delete, and view authors
- Automatically create database tables if they don't exist

## Prerequisites

- Java 11 or higher
- PostgreSQL
- Maven

## Architecture

The Library Management System is designed with a modular architecture consisting of the following components:

1. **DAO Layer (Data Access Object)**: Handles direct interactions with the database.
    - `AuthorDAO`: Interface defining CRUD operations for authors.
    - `AuthorDaoJDBC`: JDBC implementation of `AuthorDAO`.

2. **Service Layer**: Contains business logic and interacts with the DAO layer.
    - `AuthorService`: Interface defining operations related to authors.
    - `AuthorServiceImpl`: Implementation of `AuthorService`.

3. **Utility Classes**: Provide common functionalities.
    - `Mapper`: Maps database rows to model objects.
    - `SQLScriptRunner`: Executes SQL scripts.
    - `GlobalExceptionHandler`: Handles exceptions globally.
    - `AppLogger`: Provides logging capabilities.

4. **Models**: Represent the data structure.
    - `Author`: Represents an author entity.

5. **Main Class**: Entry point of the application.
    - `Main`: Contains the main method to run the application.


## Setup

1. **Clone the Repository**:
   \`\`\`sh
   git clone https://github.com/abdennabi-ahrrabi/Library-Management-System.git
   cd Library-Management-System
   \`\`\`

2. **Configure the PostgreSQL Database**:
    - Create a PostgreSQL database named \`library\`.
    - Update the database connection details in \`PostgreSQLDataSource.java\`:
      \`\`\`java
      public PostgreSQLDataSource(String host, String source, String user, String password) {
      super(\"org.postgresql.Driver\", \"jdbc:postgresql:\", host, source, user, password);
      }

      public PostgreSQLDataSource(String source, String user, String password) {
      this(\"localhost\", source, user, password);
      }

      public PostgreSQLDataSource(String source, String user) {
      this(source, user, \"\");
      }

      public PostgreSQLDataSource(String source) {
      this(source, \"postgres\", \"password\");
      }
      \`\`\`
    - Make sure your PostgreSQL server is running and accessible.

3. **Build the Project**:
   \`\`\`sh
   mvn clean install
   \`\`\`

4. **Run the Application**:
   \`\`\`sh
   mvn exec:java -Dexec.mainClass=\"com.ahrrabi.library.Main\"
   \`\`\`

## Getting Started

1. **Database Initialization**:
   The application will automatically create the necessary tables if they do not exist. The SQL script \`src/main/resources/schema.sql\` is executed at startup.

2. **CRUD Operations**:
    - **Add an Author**:
      \`\`\`java
      Author author = new Author(1, \"J.K. Rowling\", 1965);
      authorService.addAuthor(author);
      \`\`\`
    - **Update an Author**:
      \`\`\`java
      author.setName(\"Joanne Rowling\");
      authorService.updateAuthor(author);
      \`\`\`
    - **Delete an Author**:
      \`\`\`java
      authorService.deleteAuthor(1);
      \`\`\`
    - **Select an Author by ID**:
      \`\`\`java
      Author selectedAuthor = authorService.getAuthorById(1);
      System.out.println(\"Selected Author: \" + selectedAuthor.getName());
      \`\`\`
    - **Select All Authors**:
      \`\`\`java
      List<Author> authors = authorService.getAllAuthors();
      for (Author a : authors) {
      System.out.println(a.getName());
      }
      \`\`\`

## Contributing

We welcome contributions to the Library Management System project. By contributing, you agree to abide by the Code of Conduct and the following guidelines.

### Reporting Bugs

If you find a bug, please create an issue in the GitHub repository and include as much detail as possible, such as steps to reproduce, expected behavior, and screenshots if applicable.

### Suggesting Features

We welcome suggestions for new features. Please create an issue in the GitHub repository and describe the feature you would like to see, why it would be useful, and any ideas you have for implementation.

### Contributing Code

1. Fork the repository and create your branch from \`main\`.
2. If you have added code that should be tested, add tests.
3. Ensure the test suite passes.
4. Make sure your code lints.
5. Open a pull request with a clear description of your changes.

### Code Style

Please follow these guidelines to ensure consistency throughout the codebase:

- Use 4 spaces for indentation.
- Follow the Java naming conventions.
- Ensure that your code is well-documented.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Code of Conduct

This project adheres to the Contributor Covenant Code of Conduct. By participating, you are expected to uphold this code.