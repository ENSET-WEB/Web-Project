```plantuml
@startuml
title Search & Filter Products

actor User as Client
participant "Frontend (Client)" as Frontend
participant "Backend (Server)" as Backend
database "Product DB" as DB

Client -> Frontend: Search or Filter Products
Frontend -> Backend: Request Search or Filter Products List
Backend -> DB: Search or Filter Products
DB --> Backend: Product list
Backend --> Frontend: Product data (JSON)
Frontend --> Client: Render products list

@enduml
```
