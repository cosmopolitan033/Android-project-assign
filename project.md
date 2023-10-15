# App Flow

```mermaid
graph TD

A[Start] --> B[Welcome Screen]
B --> C{Logged In?}
C -->|Yes| E[Profile/Home]
C -->|No| D[Login / Register Screen]

D --> F[Register]
F --> G[User Registration]
G --> H[Store User in SQLite]
H --> I[Login]

D --> J[Login]
J --> K[Authenticate User]
K --> E

E --> L[Edit/Update Profile]
L --> M[Store Updates in SQLite]

E --> N[Find Study Buddy]
N --> O[Match Logic]
O --> P[Display Matches]

P --> Q[View Matched Profile]
Q --> E[Back to Home]
Q --> R[Create/Edit Study Plan]
R --> S{Plan Created?}
S -->|Yes| T[View Study Plan]

T --> U[Record Study Session]
U --> V[Enter Study Results]
V --> W[Store Results in SQLite]
W --> T[Back to Plan]

T --> X[View Past Results]
X --> E


```

# Class Design

```mermaid
classDiagram

class User {
   +int userID
   +String username
   +String password
   +String email
   +Profile profile
   +StudyPlan[] studyPlans
   -- (authentication methods, etc.)
}

class Profile {
   +String name
   +String[] subjects
   +String availability
   +String studyPreference
   -- (edit methods, etc.)
}

class StudyPlan {
   +int planID
   +User[] participants
   +StudySession[] sessions
   +String subject
   -- (edit, delete methods, etc.)
}

class StudySession {
   +int sessionID
   +Date date
   +String topic
   +StudyResult result
   -- (edit, delete methods, etc.)
}

class StudyResult {
   +int resultID
   +String notes
   +int rating
   +String feedback
   -- (edit methods, etc.)
}

User "1" --> "1" Profile: hasA
User "1" --> "*" StudyPlan: creates
StudyPlan "2" --> "*" User: involves
StudyPlan "1" --> "*" StudySession: hasA
StudySession "1" --> "0..1" StudyResult: records

```

---------------------------

```lua
StudyBuddyApp/
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com/
|   |   |   |   |-- studybuddy/
|   |   |   |   |   |-- models/
|   |   |   |   |   |   |-- User.java
|   |   |   |   |   |   |-- Profile.java
|   |   |   |   |   |   |-- StudyPlan.java
|   |   |   |   |   |   |-- StudySession.java
|   |   |   |   |   |   |-- StudyResult.java
|   |   |   |   |   |-- utils/
|   |   |   |   |   |   |-- DatabaseHelper.java  
|   |   |   |   |   |   |-- AuthenticationHelper.java
|   |   |   |   |   |-- activities/
|   |   |   |   |   |   |-- MainActivity.java
|   |   |   |   |   |   |-- LoginActivity.java
|   |   |   |   |   |   |-- RegisterActivity.java
|   |   |   |   |   |   |-- StudyPlanActivity.java
|   |   |   |   |   |   |-- StudySessionActivity.java
|   |   |   |   |   |   |-- StudyResultActivity.java
|   |   |   |   |   |-- adapters/
|   |   |   |   |   |   |-- StudyPlanAdapter.java
|   |   |   |   |   |   |-- StudySessionAdapter.java
|-- res/
|   |-- layout/
|   |   |-- activity_main.xml
|   |   |-- activity_login.xml
|   |   |-- activity_register.xml
|   |   |-- activity_study_plan.xml
|   |   |-- activity_study_session.xml
|   |   |-- activity_study_result.xml
|-- gradle/

```

