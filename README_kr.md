# ✨ PrismAnchorAPI
PrismAnchorAPI는 Minecraft Bukkit API를 보다 생산적이고 유연하게 사용할 수 있도록 돕는 모듈입니다. 이 모듈은 코드의 유지보수를 쉽게 만들고, 다양한 작업을 간소화하여 개발자의 작업 효율을 높혀줍니다.

## 🚀 왜 PrismAnchorAPI를 써야 하나요?
PrismAnchorAPI는 BukkitAPI를 보다 쉽게 사용할 수 있도록 만들며, 가독성을 상승시킵니다.

1. 추상클래스및 여러가지 템플릿을 제공합니다.  
    - Inventory Tamplate & Build
    - Easy Compose Command
    - Coordinate ChatColor & Component(minimessage)
    - Easy Compose YamlConfigurations
    - etc..
2. AutoRegister/AutoGenerator  
   `Event Listener`, `Command`, `Configurations Instance` 등을 자동으로 등록 해 주며.
   `TabCompletor`와 같은 부가적 요소를 자동으로 생성할 수 있는 옵션을 제공합니다.
4. 각종유틸  
   `ItemBuilder`, `Minimessage`, `ChatColor`, `DateFormat` 등등 각종 유틸을 제공합니다.
    
자세한 사항은 위키를 확인 해 주세요

# 🔬 Installation
[![](https://jitpack.io/v/Cupelt/PrismAnchor.svg)](https://jitpack.io/#Cupelt/PrismAnchor)

이 라이브러리는 jitPack을 통해 사용할 수 있습니다. 최신 버전은 항상 [GitHub Release](https://github.com/Cupelt/PrismAnchor/releases/latest)에 표시됩니다.

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
    <artifactId>PrismAnchor</artifactId>
    <version>{$version}</version>
</dependency>
```

# 🛠️ Contribute
여기까지 오셨다는건 이 프로젝트에 기여할 마음이 생겼다는 것이군요!

프로젝트에 기여하기 위해선 다음 절차를 따라주세요!

1. 이 레포지토리를 Fork 합니다.
2. 새로운 브랜치를 생성하여 기능을 추가하거나 버그를 수정합니다.
3. 수정한 내용을 커밋한 후, 풀 리퀘스트를 제출합니다.
4. 풀 리퀘스트의 병합이 승인되었다면 병합합니다.

# 🔗 Connect
궁금한 사항이나 문의가 있을 경우, 이메일로 연락해 주세요: CupeltHub@gmail.com
