This Gradle project plugin
- applies the latest version of the Project Lombok plugin;
- reduces source tree depth by moving source roots to the project's directory;
- sets Kotlin compilation tasks' JVM target version to `JavaPluginExtension::getTargetCompatibility`;
- simplifies adding Maven repositories:
  - allows URL to be specified as the first argument to `RepositoryHandler::maven`;
  - allows leading `https://` to be omitted; and
  - adds extension closures `username` and `password` mapped to `PasswordCredentials::set{Username,Password}`;
- sets `+` as the preferred (fallback) version for dependencies that do not have a version specified;
- applies `JavaPluginExtension::withSourcesJar` when the Java plugin is present;
- adds a default Maven publication to an empty publication container from the Java software component; and
- configures all Maven publications to contain the versions of dependencies that were resolved during the build
instead of the declared versions in order to allow myself to use dynamic versions
while exposing to dependent projects only the versions of dependencies that are known to work.

See the test case [`fat`](test/cases/fat/build.gradle).

### license :crayon:
Every receiver of a copy of this software ("you") is granted a worldwide non-exclusive perpetual irrevocable royalty-free license
to publicly use, perform, reproduce, display, distribute, sublicense and prepare derivative works of this software in any form subject to the following terms:
- you provide upon request a copy of this license text to any person to whom you distribute this software; and
- you do not claim this software as yours.

You may relicense derivative and collective works under different terms.

**This software is provided without any warranty including the implied warranties of merchantability, fitness for a particular purpose and noninfringement.
In no event will any contributor or licensor be liable to you for any damages related to this software.**
