# KBech32
KBech32 is a Kotlin multiplatform implementation of the [Bech32](https://en.bitcoin.it/wiki/Bech32) address format.

The implementation is heavily based on [Bitcoinj Bech32 implementation](https://github.com/bitcoinj/bitcoinj/blob/master/core/src/main/java/org/bitcoinj/core/Bech32.java). 

## Downloading
```groovy
// Common module
dependencies {
    implementation "org.kbech32:kbech32-common:$kbech32_version"
}
    
// JVM module 
dependencies { 
    implementation "org.kbech32:kbech32:$kbech32_version"
}
``` 

Don't forget to declare a variable named `kbech32_version` equals to the latest version.  
Or you can simply replace it with the latest version. 

## Usage
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
