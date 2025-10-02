# Changelog

All notable changes to this project will be documented in this file.

## [0.1.1] - 2025-10-02

### New features
- None.

### Bug fixes
- Restored the default action bar height by removing the custom override.

### Breaking changes
- None.

### Dependency updates
- None.

### Maintenance
- Declared `com.github.BlueCodeSystems` as the Maven group for local publication workflows.

## [0.1.0] - 2025-09-25

### New features
- Introduced the PulseBridge Android library with FHIR R4 bundle generation APIs driven by `FHIRBundleModel` inputs.
- Added utilities to launch the PulseBridge PWA flow and parse response payloads into OpenSRP events and care plan records.

### Bug fixes
- None.

### Breaking changes
- None.

### Dependency updates
- Added `androidx.annotation:annotation:1.8.2`.
- Added `com.jakewharton.timber:timber:5.0.1`.
- Added `commons-codec:commons-codec:1.17.0`.
- Added `org.apache.commons:commons-lang3:3.14.0`.
- Added `ca.uhn.hapi.fhir:hapi-fhir-base:6.10.1` and `ca.uhn.hapi.fhir:hapi-fhir-structures-r4:6.10.1`.
- Added `io.github.bluecodesystems:opensrp-client-core:6.2.0`.
- Added `io.github.bluecodesystems:android-p2p-sync:0.4.1`.
- Added `io.github.bluecodesystems:opensrp-plan-evaluator:1.7.0`.
