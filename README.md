# RPN-Calculator
An implimentation of RPN calculator as an exercise.

## Prerequirement
- jdk 8+
## Test
```$xslt
 $ gradlew test
```
Test reports can be find under <code> build/reports/test </code>

## Run
1Run by gradle directly.
    ```$xslt
    $ gradlew run --console=plain
    ```
## Use
- Numbers and commands(**+, -, \*, /, sqrt, undo,redo**)are supported. Others will be ignored.
- Numbers will be stored in stack:
    ```$xslt
    $ 1 2 3 
    stack: 1 2 3
    ```
- Commands may change the stack

    - For **+, -, \*, /, sqrt**, numbers will be poped from stack and the result calculated will be pushed in.
        ```$xslt
        $ 1 2 3 +
        stack: 1 5
        ```
        If there is not enough numbers in stack, this command will not change the stack and an error will be showed.
        ```$xslt
        $ 1 2 3 + + +
        operator * (position: 11): insucient parameters 
        stack: 6
        ```
        Other invalid calculation will also be reported such as <code>$ -1 sqrt</code>
    - For **undo**, stack will turn to the state before the last command while for **redo** will turn to the state before **undo**. Invalid **redo** and **undo** will be ignored.
        ```$xslt
        $ 1 2 3 + undo
        stack: 1 2 3
        $ redo
        stack: 1 5
        ``` 
## Exit
- Control + C