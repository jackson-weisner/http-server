# HTTP Server

### About the project
This project's goal was to develop a basic HTTP server library from scratch. 
The main reason for creating this was to learn more about how webservers work internally.

### How to use the library
Package the server module using maven then include the jar in the client module.
You must either create the Routes class in the client and call the default constructor in main.
Or if you want to use more classes for your HTTP routes, create them and call the other constructor for the Server class and pass in your custom classes. Your files for the server should be located in the ./content directory in the client module. 

### Client Documentation
Each method you want registered as a server route in your Routes class (or custom classes) should resemble the following:
```
@Route(uri = "/route/you/want", method = "POST|GET")
public static Response methodName(Request request);
``` 
The subclasses of Response are the following:
- HtmlResponse
- JavascriptResponse
- JsonResponse
- CssResponse
- ImageResponse (.png|.jpeg|.gif)
