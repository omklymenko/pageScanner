# task

An application searches for text on web pages.
Search begins with the specified url. All links found on this page are used for further search.
Search stops when achieved the specified number of pages. Also, it may be stopped by click on Stop button.
Page loading - multithreaded, the number of threads for simultaneous downloading may be specified by user.
Breadth-first search (BFS) was used.

Parameters:
- start url
- text to search
- number of threads
- max number of urls

Tools/technologies:
- Java
- Spring Framework
- jsoup
- maven
