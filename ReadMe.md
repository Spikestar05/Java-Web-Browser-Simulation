# Browser Navigation System

## Introduction
The Browser Navigation System is a Java-based implementation that simulates web browser navigation, including visiting websites, moving back and forward between visited pages, viewing history, saving sessions, and restoring previous sessions. The system is built using a custom **doubly linked list** for stack operations and a **circular array** for queue-based history storage.

## Project Structure

- **BrowserLinkedList.java** – Custom doubly linked list used to implement stacks.
- **BrowserArrayList.java** – Custom circular array used for queue-based history storage.
- **BrowserStack.java** – Stack implementation using `BrowserLinkedList`.
- **BrowserQueue.java** – Queue implementation using `BrowserArrayList`.
- **StackIterator.java** – Custom iterator for traversing the stack.
- **BrowserNavigation.java** – Implements navigation features:
  - Visiting websites
  - Navigating back and forward
  - Saving and restoring sessions
  - Viewing and clearing history
  - Exiting the browser
- **Main.java** – Driver program that runs the browser navigation system.

## Features & Implementation Choices

### `visitWebsite(String url)`
- Adds the webpage to browsing history (Queue).
- Pushes the current page onto the back stack.
- Clears the forward stack.
- **Time Complexity:** O(1)

### `goBack()`
- Moves one page back using the back stack.
- Pushes the current page onto the forward stack.
- Throws `EmptyStackException` if no previous pages exist.
- **Time Complexity:** O(1)

### `goForward()`
- Moves forward using the forward stack.
- Throws `EmptyStackException` if no forward pages exist.
- **Time Complexity:** O(1)

### `showHistory()`
- Displays all visited websites in order.
- **Time Complexity:** O(n)

### `clearHistory()`
- Empties the browsing history queue.
- **Time Complexity:** O(1)

### `closeBrowser()`
- Uses an iterator to save the session to `session_data.txt`.
- **Time Complexity:** O(n)

### `restoreLastSession()`
- Reloads session from `session_data.txt` if available.
- **Time Complexity:** O(n)

## Test Cases

1. **Basic Navigation & History**
   - Visit `example.com`
   - Visit `google.com`
   - Visit `youtube.com`
   - Check history (should display all 3 websites with the most recently visited at the top).

2. **Backward & Forward Navigation**
   - Go back (should land on `google.com`).
   - Go back again (should land on `example.com`).
   - Attempt to go back again (should throw an error: "No previous page available").
   - Go forward (should land on `google.com`).
   - Go forward again (should land on `youtube.com`).
   - Attempt to go forward again (should throw an error: "No forward page available").

3. **Session Persistence**
   - Close the browser (creates `session_data.txt`).
   - Restart the program and restore history.
   - Check history (should show all previously visited sites, with `youtube.com` at the top).

## Suggested Diagram
To better illustrate the navigation flow, a stack and queue diagram can be added, showcasing:
- **Back Stack:** Holds previous pages.
- **Current Page:** The active page.
- **Forward Stack:** Holds forward pages.
- **History Queue:** Stores visited websites.

![image](https://github.com/user-attachments/assets/a81b6832-52e9-444a-b07b-d7e8c20541d1)
