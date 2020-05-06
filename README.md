## JDK8 Lambda Samples
originally created by [@shipilev](https://github.com/shipilev), [repo](https://github.com/shipilev/jdk8-lambda-samples)

#### short notes:

| class | test | description |
|---|---|---|
| LambdaTest | testCmpLegacy/testCmpLambda0 | Comparators: lambdas and anonymous classes
||testCmpLambda1| (int x)->
||testOneArg| ()->()
||testOneArg1 & 2| (x)-> aka x->
||testCmpLambda2 & 3| Integer bigger than 127
||testConsumer | Consumer in .forEach()
|UroborosTest| \* | chain call
|CaptureTest|testLegacy | final fields in anonymous class
||testLambda | *effective final* with lambda
||testPredicate1 & 2| Predicates with context
|ScopingTest|testScoping1| different **this** for anonymous classes and lambdas
|FibonacciTest| test10 | capture **this** but not field
|| test10_static| without capture, just link to static method
|MethodRefTest| testMethodRefStatic| methodRef to static method (as prev)
||testMethodRefInstance0 & 1 & 2 | *bound* MethodRef
||testMethodRefInstanceUnbound | *unbound* MethodRef (add **this**)
||testConstructor | call **new**
||testConstructor0 | constructor w/o parameters
||testConstructor1 | constructor w/ parameters
|ZamTest| \* | Replace lambdas to MethodRef
|ThreadLocalTest|threadLocalLegacy| legacy variant
||threadLocalLambda| added method withInitial(Supplier)
||threadLocalMRef | via MethodRef
| ▼ Advanced ▼
|NoiseSampleTest|
|WeirdFunctionTest|

###Default Methods

| class | test | description |
|---|---|---|
|DefaultTest| \* | backward compatibility
|GuavaTest| testTreeMultiset | usage with third party libraries
|StaticInterfaceTest| test | functional interface with static method

###Streams (Bulk Operations for Collections)

| class | test | description |
|---|---|---|
|ExternalInternalTest| \* |  
||►|highlight the difference between internal and external iteration
||►|iteration is explicitly serial
||►|libraries have more headroom optimizing with internal iteration
||►|sometimes even forEach is not enough: chaining the action within the serial again