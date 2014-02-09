Updater
==============

Simple Bukkit plugin to apply plugin updates upon server restarts.

### Usage
* Compile, put plugin in the 'plugins' directory, and start the server.
* Upon first run it should make a directory in the plugins directory called 'Updater'. Put the plugin files you want updated in there.
* When your server stops, it will replace the files in the plugins directory with the updated versions in the 'Updater' directory'!

### Important notes
* File names MUST be the same in both the 'plugins' and 'Updater' directory in order for them up update/replace correctly!

### Compiling
You must have Apache Maven installed to compile. (http://maven.apache.org)

to compile use the following command:

```mvn clean install```
