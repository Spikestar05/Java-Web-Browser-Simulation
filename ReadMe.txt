CS 3345 – Data Structures & Algorithm Analysis
Name: Harshavarthan Mathapati
NetID: HXM220085
Section: CS 3345.005

Implementation:
The BrowserLinkedList.java is a custom doubly linked list implement stacks.
The BrowserArrayList.java is a custom circular array for queue-based history storage.
The BrowserStack.java is a stack implementation built on BrowserLinkedList.
The BrowserQueue.java is a queue implementation built on BrowserArrayList.

StackIterator.java is a custom iterator used to traverse the stack.

BrowserNavigation.java is the file that implements visiting websites, going backwards & forwards on websites, saving session, showing the history, loading other sessions, and exiting the program.

Main.java is the driver program that runs the browser navigation program.

Implementation choices:

visitWebsite()
	Adds the webpage to browsing history (Queue).
	Pushes the current page onto the back stack.
	Clears the forward stack.

goBack()
	Moves one page back using the back stack.
	Pushes the current page onto the forward stack.
	Throws EmptyStackException if no previous pages exist.

goForward()
	Moves forward using the forward stack.
	Throws EmptyStackException if no forward pages exist.

showHistory()
	Displays all visited websites in order.

clearHistory()
	Empties the browsing history queue.

closeBrowser()
	Uses an iterator to save the session to session_data.txt.

restoreLastSession()
	Reloads session from session_data.txt if available.

Complexities:

visitWebsite()		O(1)
goBack()		O(1)
goForward()		O(1)
showHistory()		O(n)
clearHistory()		O(1)
closeBrowser()		O(n)
restoreLastSession()	O(n)


Test Case:

visit example.com
visit google.com
visit youtube.com
check history and you should see all 3 websites with the one you visited recently at the top of the list
go back and you should be at google.com
close the browser and you should get a txt file named session_data.txt
exit the browser.

Then start the program again and restore the history.
After restoring check history and you should see all 3 websites with youtube.com at the top of the list.
go back and you should be at example.com.
if you go back again you should get an error saying no previous page available.
go forward twice and you should be at youtube.com.
if you go forward again you should get an error saying no forward page available.







