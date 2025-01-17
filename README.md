# ✨ PrismAnchorAPI

**PrismAnchorAPI** is a module designed to enhance productivity and flexibility when working with the Minecraft BukkitAPI. This module simplifies various tasks, improves code maintainability, and boosts development efficiency.

[한국어를 사용하시나요?](https://github.com/Cupelt/PrismAnchor/blob/master/README_kr.md)

## 🚀 Why Use PrismAnchorAPI?

PrismAnchorAPI makes working with the Bukkit API easier and improves code readability.

1. **Templates and Abstract Classes**  
   - Inventory Template & Build  
   - Easy Compose Command  
   - Coordinate ChatColor & Component (MiniMessage)  
   - Easy Compose YamlConfigurations  
   - And more...

2. **AutoRegister/AutoGenerator**  
   Automatically registers components such as `Event Listeners`, `Commands`, and `YamlConfigurations`. It also provides options to auto-generate additional elements like `TabCompleters`.

3. **Utility Tools**  
   Includes utilities like `ItemBuilder`, `MiniMessage`, `ChatColor`, `DateFormat`, and more.

For more details, please check the [Wiki](https://github.com/Cupelt/PrismAnchor/wiki).

---

## 🔬 Installation

[![](https://jitpack.io/v/Cupelt/PrismAnchor.svg)](https://jitpack.io/#Cupelt/PrismAnchor)  

This library is available through JitPack. The latest version can always be found on the [GitHub Releases](https://github.com/Cupelt/PrismAnchor/releases/latest) page.

### Gradle

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation("com.github.Cupelt:PrismAnchorAPI:{$version}") { // replace {$version} with the latest version
    }
}
```

### Maven

```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
    <groupId>com.github.Cupelt</groupId>
    <artifactId>PrismAnchorAPI</artifactId>
    <version>{$version}</version>
</dependency>
```

### Include Build Jar
PrismAnchorAPI must be included in the build output of your plugin in order for it to function properly.

It is generally recommended to use Gradle's **ShadowJar** to create a **FatJar**.
```gradle
plugins {
    id 'com.github.johnrengelman.shadow'
}

dependencies {
    ...other Dependencies
    shadow 'com.github.cupelt:PrismAnchorAPI:{$version}'
}

shadowJar {
    configurations = [project.configurations.shadow]
}
```

# 🛠️ Contribute

If you've made it this far, it means you're interested in contributing to this project!

To contribute, please follow these steps:

1. Fork this repository.  
2. Create a new branch to add features or fix bugs.  
3. Commit your changes and submit a pull request.  
4. Once your pull request is approved, it will be merged.

---

# 🔗 Connect

If you have any questions or inquiries, feel free to contact us via email: **CupeltHub@gmail.com**
