# ModMixer

## How To Test Client & Server for puzzle
- go to the Puzzle Subproject
- For the Client you can use the `gradle :puzzle:runClient` task
- For the Server  you can use the `gradle :puzzle:runServer` task

## How To Test Client & Server for quilt
 - go to the Quilt Subproject 
 - For the Client you can use the `gradle :quilt:runClient` task
 - For the Server  you can use the `gradle :quilt:runServer` task

## How to Build A Jar
For building, use the `gradle buildAll` task. The output will be in the `build/libs/` folder

## How to change properties
- All mod properties can be edited in the rootProject `gradle.properties`
- The project name is defined in `settings.gradle`.