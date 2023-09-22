# Tinder
## A class example: Lab of Internet Applications

### API
* **GET "/profiles/{email}"**      -- returns a given profile.
* **GET "/profiles"**           -- returns all profiles 
* **GET "/profiles/{email}/candidates"**    -- returns the candidates of user "email". A candidate is a person that matches the user's requirements (gender and passion)
* **POST "/profiles")**            -- creates a new user profile 
* **POST "/likes"**                -- creates a list of likes/proposals for a given user (origin, list of targets). See ProfileRestController.LikeFront inner class to see the needed parameters (json in the http call body)

#### Teaching API
Here you'll have another controller with examples with just teaching purposes. Examples that may not fit well in the Tinder
application or more importantly that may be **negative** ones.

* **POST "/teaching/profilesString"**       -- creates a new user profile (Jackson called manually)
* **GET "/teaching/int/{i}**                -- returns the given int that must be less or equal to 50. Error parameter example
* **GET "/teaching/salutation?name=myName"** -- returns the salutation to the given name. Request parameter example
