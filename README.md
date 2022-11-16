# CoronaCenterMap
> 코로나19 예방접종센터 지도 서비스

https://user-images.githubusercontent.com/66052467/202099063-ffabf707-6478-41f8-b2ad-6b76e21df34c.mov

### 기능 목록
[FEATURE_LIST.md](https://github.com/dudwls901/CoronaCenterMap/blob/main/Docs/FEATURE_LIST.md)

### Structure

```
app
    └── di
data
    ├── common
    │   ├── constant
    │   └── util
    ├── local
    │   ├── dao
    │   ├── datasource
    │   ├── datasourceimpl
    │   └── dto
    ├── remote
    │   ├── api
    │   ├── datasource
    │   ├── datasourceimpl
    │   └── response
    └── repositoryimpl
domain
    ├── model
    ├── repository
    ├── usecase
    └── util
presentation
    ├── common
    │   ├── constant
    │   └── util
    │       └── event
    └── view
        ├── main
        └── splash

```

### Stack 
- Application Version
    - targetSdkVersion : 32
    - minSdkVersion : 21
- Language
    - Kotlin : 1.7.10
- Gradle
    - kts
- Architecture
    - Clean Architecture (Multi-Module)
- Design Pattern
    - MVVM
- UI Layout
    - XML
- Network
    - Retrofit2, OkHttp3
- Jetpack
    - ViewBinding
    - LiveData
    - Lifecycle
    - ViewModel
- Serialization
    - Kotlinx Serialzation
- DI
    - Hilt
- 비동기
    - Coroutine Flow
- Map
    - Naver Map
    - Google Play Service (Location)
- [예방 접종 센터 API](https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15077586)
