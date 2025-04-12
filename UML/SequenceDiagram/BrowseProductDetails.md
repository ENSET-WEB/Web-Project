

```plantuml
@startuml
title Browse Products Sequence

actor User as Client
participant "Frontend (Client)" as Frontend

Client -> Frontend: Consults product's details
Frontend --> Client: Render product's details

@enduml
```
