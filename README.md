## Installation / Setup
It is necessary to install jdk 17, gradle, and allure, open the terminal and type the commands below
```sh
brew install java
java --version
```
```sh
brew install openjdk@17
javac -version
```
```sh
brew install gradle
gradle --version
```
```sh
brew install allure
allure --version
```
## Usage
To run the tests use the `cd` command and locate the root directory of the project, and type the command below
```sh
gradle clean test
```
To open the allure report type the command below from root path
```sh
allure serve build/allure-results
```
