# JUI  

### What?  
JUI is a library for Java, utilizing Win32API and JNA to create GUIs.
It is currently in development, and is not ready for use. 
Please report any bugs you find in the issues tab.  
Any contributions are welcome.  

### Installation  
To install JUI, you can either download the source code and compile it yourself (which is for now the only options), download the pre-compiled jar file from the releases tab or use JitPack.

### Usage  
JUI is (for now) very easy to use, here is an example:  
```java
public class TestMain extends Application<Win32> {
    public static void main(String[] args) {
        BootstrapLauncher.inCustomMain(new TestMain());
    }

    @Override
    public void onLaunch(Configurator config) {
        config.registerWindow(new TestWindow());
    }
    public static class TestWindow extends Window {

    }
}
```  
Any other examples can be found in `org.jui.util.examples`.  
The entire library has a very limited use, so it is not recommended to use it for anything serious.  

### Contributing
Contributions are welcome, but please make sure to follow the code style.  
To contribute, fork the repository, make your changes and create a pull request.  
Please make sure to test your changes before creating a pull request.  

### License
JUI is licensed under the MIT license.