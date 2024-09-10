# Tinder-Lab
## A class example: Lab of Internet Applications

This application is a simple example of a Tinder-like application. Users are represented by a profile with an email. They can like other users and accept other user likes.
Users can also see the candidates that match their requirements.

## Architecture
The application is divided in three layers:
+ Presentation Layer: where you find the REST controllers and the error and exception handlers. We want the presentation to 
decide what to do when an error occurs. This layer may have also somt DTOs (Data Transfer Objects) to represent the data that
the client sends or receives.
+ Service Layer: where you find the business logic. This layer is responsible for the application rules. Note that this layer isolates
the domain from the presentation. For this reason services never return entities/domain objects, but DTOs. Java records are a good option for DTOs 
since they are immutable and only carry information (no logic).
+ Domain Layer: where you find the entities that represent the domain. This layer should have as much as possible the business logic. Unfortunately, in this 
example, we've got an anemic domain model. This is not a good practice, but it's enough for this example. Note that, very often, the service and
domains layers are together in the same package.
+ Persistence Layer: where you find the repositories. The repositories are responsible for the CRUD operations. They can return either domain objects or DTOs.
Repositories would retun domain objects if the application layer needs to call their logics or if it updates their content.
+ Configuration Layer: where you find the configuration classes. This layer is responsible for the application configuration. In this layer we can also 
include the security configuration package.

## Interesting
+ Security is using the spring security 6 with JWT tokens. This allows to avoid having sessinons and thus makes the Cross Site Request Forgery (CSRF) 
attacks impossible/harder to perform. 
+ The data getting into the application is validated by the presentation layer. 
+ The configuration reads the application.properties file where it can find some definitions to configure the application.

## Testing
The application has a few tests. Idealy, it should test each feature.

## API
* **GET "/profiles/{email}"**      -- returns a given profile.
* **GET "/profiles"**           -- returns all profiles 
* **GET "/profiles/{email}/candidates"**    -- returns the candidates of user "email". A candidate is a person that matches the user's requirements (gender and passion)
* **POST "/profiles")**            -- creates a new user profile 
* **POST "/likes"**                -- creates a list of likes/proposals for a given user (origin, list of targets). See ProfileRestController.LikeFront inner class to see the needed parameters (json in the http call body)

#### Teaching API
Here you'll have another controller with examples with just teaching purposes. Examples that may not fit well in the Tinder
application or more importantly that may be **negative** examples.

* **POST "/teaching/profilesString"**       -- creates a new user profile (Jackson called manually)
* **GET "/teaching/int/{i}**                -- returns the given int that must be less or equal to 50. Error parameter example
* **GET "/teaching/salutation?name=myName"** -- returns the salutation to the given name. Request parameter example
