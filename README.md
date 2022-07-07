# HWR OOP Lecture Project Template

[TODO]: # (Change README.md Headline to better fit to your project!)

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2022).

> :warning: This code is for educational purpose only. Do not rely on it!

## Abstract

Description : This Project is a ToDoList.

Important Features : You can create an Account that has a To Do List. Can add a Task to the List. Can delete a Task or all Tasks. Can have multiple Accounts with different To Do Lists. Saves Accounts and To Do Lists in Directories and Files.

Interesting Problems: The Problem of ErrorHandling, what you want your code to do after it runs into an Error. To read a File and write into a File, the multiple ways to handle Files. 

## Feature List

[TODO]: # (For each feature implemented, add a row to the table!)

| Number | Feature                                                                                       | Tests |
|--------|-----------------------------------------------------------------------------------------------|-------|
| 1      | Task has title, status and date                                                               | 4     |
| 2      | ToDoList can add task to a List, delete task from a List, get Task from List                  | 6     |
| 3      | An Account has name and password                                                              | 8     |
| 4      | You can create and save an account with name (directory) with password (File) and List (File) | 8     |
| 5      | You can save your ToDoList into a File                                                        | 2     |
| 6      | Can Verify if your Account exists or not                                                      | 2     |
| 7      | Can load your ToDoList from the File                                                          | 1     |
| 8      | add a Task to your Accounts List (with input)                                                 | 5     |
| 9      | add a new Account (with input)                                                                | 4     |
| 10     | can logIn to an already existing Account  (with input)                                        | 4     |
| 11     | can change the status of a Task from an Accounts ToDoList (with input)                        | 3     |
| 12     | can delete a Task from an Accounts ToDoList (with input)                                      | 4     |
| 13     | can display the ToDoList from an Account                                                      | 2     |


## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| 1      | /               | /                      | /                    |

## Instructions

[TODO]: # (Remove these instructions once you finished your fork's setup.)

Use a fork of this repository to do implement your project.

Remember to add this repository as a second remote repository (upstream) and pull from the correct remotes.
This is necessary, because we might apply changes to this template during the next month.

The following section describes how to add multiple remote repositories to your local repository, which is cloned from the fork.

### Multiple remote repositories

Your local repository should have a reference to both the fork (your own remote repository) and the original remote repository.
To configure your git remote repositories, use the `git remote` command set.

1. Clone your fork and go enter the repository.
```
git clone <fork-url>
cd <created-folder>
```
2. Now your fork is configured as primary remote repository (origin).
Next to origin, you should add the original repository as a second remote repository (upstream).
```
git remote add upstream <repository-url>
```
3. Verify that both remotes are configured correctly.
The following command should list both remotes: origin and upstream.
```
git remote -v
```
4. To fetch changes from all remote repositories, use:
```
git fetch --all
```
5. If there are interesting changes (in e.g. the `main` branch) to merge into your branch, use:
```
git pull upstream main
```
