<!-- JITPACK BADGES:START -->
[![JitPack Latest](https://jitpack.io/v/BlueCodeSystems/pulsebridge.svg)](https://jitpack.io/#BlueCodeSystems/pulsebridge)
[![Build for latest tag (v0.1.1)](https://jitpack.io/v/BlueCodeSystems/pulsebridge/v0.1.1.svg)](https://jitpack.io/#BlueCodeSystems/pulsebridge/v0.1.1)
[![master-SNAPSHOT](https://jitpack.io/v/BlueCodeSystems/pulsebridge/master-SNAPSHOT.svg)](https://jitpack.io/#BlueCodeSystems/pulsebridge/master-SNAPSHOT)
<!-- JITPACK BADGES:END -->

# PulseBridge

PulseBridge is an Android library that streamlines OpenSRP integrations with a
clinical decision support workflow by generating FHIR bundles, producing
OpenSRP events, and launching the PulseBridge Progressive Web App experience.

## Project Status
- Tooling: Gradle 8.7, Android Gradle Plugin 8.5.2, Java (no Kotlin sources)
- Android: compileSdk 34, targetSdk 35, minSdk 18
- Branch: `main` (latest tag `v0.1.1`)
- CI: configure GitHub Actions or preferred runner (none provided in repo)

## Features
- Generate FHIR R4 bundles from `FHIRBundleModel` inputs for PulseBridge workflows
- Launch the PulseBridge PWA with encoded bundle data from native Android
- Parse PulseBridge bundle payloads into OpenSRP `Event` and `Obs` models
- Create OpenSRP care plan events and helper utilities for bundle handling

## Requirements
- JDK 17 (required by AGP 8.5+)
- Gradle 8.7 (Wrapper included)
- Android Gradle Plugin 8.5.2
- Kotlin: not required (library is Java-based)
- Android API levels: minSdk 18, compile/targetSdk 34/35

## Install
Add the dependency from Maven Central once published. Replace `<version>` with a
tagged release (current: `0.1.1`, released on October 2, 2025).

<details>
<summary>Groovy build.gradle</summary>

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.example:pulsebridge:0.1.1'
}
```
</details>

<details>
<summary>Kotlin build.gradle.kts</summary>

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.example:pulsebridge:0.1.1")
}
```
</details>

## Initialize
Initialize the library once in your `Application` class by providing an Android
`Context` and a configured `PulseBridgeConfig`:

```java
public final class MyApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    PulseBridgeConfig config = new PulseBridgeConfig();
    config.setServiceEndpoint("https://example.com");
    config.setWebAppPath("/path/to/pwa");

    PulseBridgeLibrary.init(this, config);
  }
}
```

## Usage examples
Create a bundle and launch the PulseBridge PWA:

```java
FHIRBundleModel bundleModel = new FHIRBundleModel();
bundleModel.setPatientId(patientId);
bundleModel.setPractitionerId(practitionerId);
// Populate additional demographics and codes as required

PulseBridgeLibrary.getInstance().processHealthAssessment(context, bundleModel);
```

Convert a PulseBridge response into an OpenSRP event:

```java
String encodedBundle = PulseBridgeLibrary.getInstance()
    .getPulseBridgePatientId(responseJson);
Event carePlan = PulseBridgeLibrary.getInstance()
    .createCarePlanEvent(encodedBundle, formTag, entityId);
```

Extract a PulseBridge patient identifier from a bundle payload:

```java
String patientId = PulseBridgeLibrary.getInstance()
    .getPulseBridgePatientId(encodedBundleString);
```

## Sample app
No sample module is bundled yet. Create a demo module or use your host OpenSRP
app to exercise the library APIs.

## Build & test
```bash
./gradlew clean assemble
./gradlew test
```

## Releases
### 0.1.1 - October 2, 2025
- Restored the default action bar height by removing the custom override.
- Declared the Maven group for local publication workflows.

### 0.1.0 - September 25, 2025
- Initial library release with PulseBridge FHIR bundle generation and parsing
  APIs, plus client utilities for PulseBridge workflows.

Refer to the repository's Releases page for published versions and release
notes.

## Contributing
Issues and pull requests are welcome. Please build and test with the toolchain
listed above before submitting changes. Create a CONTRIBUTING guide if
additional steps are required.

## License
This project should include a LICENSE file specifying the terms for reuse. Add a
LICENSE (for example, Apache-2.0) so adopters understand distribution rights.
