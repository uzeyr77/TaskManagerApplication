A simple command line task management system built with Java featuring SQLite database persistence and a multi-level architecture following good design patterns.
# Overview
This program demonstrates clean software engineering practices through the DAO pattern, layered architecture, and database integration. 
# Key Features
* CRUD operations: create, read, update and delete tasks with complete persistence
* Task-state management: keep track of where task stands state wise (todo, in progress, and done)
* Filtering: get tasks by their state (e.i all tasks that are in progress)
# Technical Features
* SQLite Database: fully integerating databse with prepared statements
* Unique ID: each task has a unique ID of length 5
* Time Tracking: management of time-stamp through creation and updates
* Exception Handling: custom exception heirarchy providing clarity in error handling
# Technologies Used
* Java
* Maven
* SQLite
* Java 24
* JDBC
* JUnit 5

