# search-engine
A simple restaurants search engine that takes up to 5 parameters:
- The name of the restaurant, which is matched using a `contains` approach;
- The customer's rating, which is a number from 1 to 5;
- The distance to the restaurant, which is a treated as an integer number;
- The average price of the restaurant, which is treated as an integer number;
- The restaurant's cuisine, which is matched using a `contains` approach.

This program was written in Java and made available as an executable jar file. To run it you'll need to have installed on you computer Java version 8 or grater.

The restaurants and cuisines used by the program are included in csv files inside the program itself, at the root of the jar file.

You can download this program [here](https://github.com/mvpetrungaro/search-engine/blob/main/bin/search-engine.jar).

## Source code
The program's source code can be found [here](httsp://github.com/mvpetrungaro/search-engine/). It was built with [Maven](https://maven.apache.org/) and relies on the following libraries:

- [Apache Commons CLI](https://commons.apache.org/proper/commons-cli/)
- [OpenCSV](https://opencsv.sourceforge.net/)
- [Project Lombok](https://opencsv.sourceforge.net/)

To run it, you won't need to install any of those, since it was built including all it's dependencies.

## Usage
This program can be run with the following command:

```bash
java -jar search-engine.jar [parameters]
```

The parameters must be used with the following syntax:

- `-rn (--restaurantName) <txt>`
- `-cr (--customerRating) <num>`
- `-d (--distance) <num>`
- `-p (--price) <num>`
- `-c (--cuisine) <txt>`

## Testing
There's a test class available with some search test implementations, feel free to add any more tests you find useful.

The tests were built using [Junit 5](https://junit.org/junit5/).
