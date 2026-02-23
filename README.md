A simple app that demonstrates pagination and bluetooth connectivity. Harnessing JUUL Labs Kable, we are able to have multiplatform support for bluetooth. 
Backend is powered by ktor.

Supports:
1. Offline support
2. Pagination
3. Bluetooth Scanning

Written with Kotlin multiplatform, this currently runs on iOS and Android. 

To run the backend, run the command:
```shell
./gradlew server:run
```
<img width="660" height="1434" alt="image" src="https://github.com/user-attachments/assets/51cae66c-e768-42b9-aea5-e60bf5bcc07e" />
<img width="591" height="1242" alt="image" src="https://github.com/user-attachments/assets/6f99ebb7-6c4b-4e8b-8cc5-9c363c0e9a42" />

## Architecture
```mermaid
stateDiagram-v2
repository --> networking
server --> networking
networking --> common
composeApp --> repository
composeApp --> networking
```
