# KBech32
KBech32 is a Kotlin multiplatform implementation of the [Bech32](https://en.bitcoin.it/wiki/Bech32) address format.

The implementation is heavily based on [Bitcoinj Bech32 implementation](https://github.com/bitcoinj/bitcoinj/blob/master/core/src/main/java/org/bitcoinj/core/Bech32.java). 

# Downloading
This library is available on Jitpack: [![](https://jitpack.io/v/komputing/KBech32.svg)](https://jitpack.io/#komputing/KBech32)

In order to download it, firstly include the Jitpack repository on your project `build.gradle` file:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then, insert the following dependency inside your module's `build.gradle` file:

```groovy 
dependencies {
    implementation 'com.github.komputing.KBech32:core-{platform}:{version}'
}
``` 

The latest version is the one published on Jitpack: 

[![](https://jitpack.io/v/komputing/KBech32.svg)](https://jitpack.io/#komputing/KBech32)

### Supported platforms
| Platform | Usage | 
| :------- | :---- | 
| `common` | Common Kotlin multiplatform projects | 
| `jvm` | Kotlin-JVM modules/projects |
| `js` | Kotlin-JS modules/projects | 
| `native` | Kotlin-native modules/projects |


# Usage
```kotlin
// Encode given a human readable part and a byte array
val encoded = Bech32.encode(humanReadablePart = "bc", data = byteArrayOf(1, 2 ,3))

// Encode by wrapping the data into a Bech32Data object
val data = Bech32Data(
  humanReadablePart = "bc",
  data = byteArrayOf(1, 2, 3)
)

// Decode the data
val bech32Value = "abcdef1qpzry9x8gf2tvdw0s3jn54khce6mua7lmqqqxw"
val (humanReadablePart, data) = Bech32.decode(bech32Value)
```
