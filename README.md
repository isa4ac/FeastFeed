# Feast Feed

Ishani Saxena, Ella Terselic, Isaac Van Meter, Daniel West

6/4/2023

## Introduction
Feast Feed is a recipe book application that is designed for social media style interaction and to be easy to jump in and use right away.  We strive to provide a unique place for users to store, update, and share their favorite recipes in an easy and manageable system.  Our goal is to help create a community of food lovers by facilitating communication and collaboration between users and giving them an opportunity to share their passion with like-minded individuals.

Users can share recipes, pictures of food, and messages all publicly or only to other registered users.  Users will also be able to generate ingredient lists, print-friendly versions of recipes, and customize settings to their preference.  Other proposed features include accessibility tools, private messaging, and notifications.

## Storyboard

Visit [this](https://mailuc-my.sharepoint.com/:wb:/g/personal/terseler_mail_uc_edu/EXUoMc2o_I1EvsNLKmzkImMBQcmO3sifMeC4hyyWkiXvBQ?e=FbXWzU) link to see a visualization of our navigation and additional information about our UI.

Default Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/3825b137-540a-4077-b36c-9eafd8a2fe72)

Sign Up Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/880bb1d8-17a1-40f9-aa64-c46c65661ad2)

Log In Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/410584fe-f543-44a1-a512-ae1b0b9ae9ef)

Logged in Home Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/79a32c66-3824-44a1-9a57-bd14a136e089)

Search Results Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/07b7499d-3cd7-42ed-ad32-1b65a86308fc)

Messaging Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/b3a696c4-1e8e-4422-aabd-15c9a6a54c89)

Profile Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/af88436e-b2b8-4a2f-8dc6-b6e6bd6c1181)

Settings Screen:
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/241b94a3-2eb2-42d8-b17b-9be0c9055f59)

## Functional Requirements

1. As someone who enjoys cooking, I want to be able to view other people’s recipes so that I can replicate these myself. 
   - Given: I am a Feast Feed user.
   - When: I search for a recipe.
   - Then: I am given results that contain recipes shared by other users.

2. As someone who enjoys cooking, I want to be able to share my recipes with the cooking community so that others can see what I’ve created and remake my recipes for themselves.
   - Given: I am a Feast Feed user.
   - When: I create a recipe on Feast Feed.
   - Then: I am given an option to upload my recipe for others to view.

3. As someone who enjoys cooking, I want to be able to communicate with other members of the cooking community through comments, likes and posts so that I can give and receive feedback about recipes. 
   - Given: I am a Feast Feed user.
   - When: Either myself or someone else posts a recipe.
   - Then: I have the ability to comment, rate, or in other ways interact with that content.

## Class Diagram
![image](https://github.com/westd5/ProjectIT4045C/assets/77344568/d12a15a9-8992-40a5-af2d-3a229e860526)

## Class Diagram Description
Shown above, there are four packages:
  - The Data Transfer Object (DTO) Package
    - This package is used to make the data accessible to all other packages in an organized manner.
    - Commonly referred to as all the nouns of a given application.
      - User
      - Recipe
      - Photos
      - Categories
    - And all sub-datapoints under these objects such as
      - UserID
      - Username
      - Password
      - Bio
  - The Data Access Object (DAO) Package
    - The DAO package makes use of the DTO package’s objects to connect them to the services 
    - This is where a user will be generated when making an account, it will grab not only username and password, but also update their record when they go to add a friend, or like a recipe.
    - Note that there are two ways a user, recipe, or photo can be “realized”
      - Through assembling actual user inputs.
      - Or generated a fake piece of data for UI testing.
  -	The Service Package
    -	The service package is like the DAO in making the data accessible, the service package will also refine the data in a way that makes it much more accessible for the UI side.
  -	The User Interface (UI) Package
    -	The UI package will have functions (update services) that update the user’s data.
      -	When they create their account.
      -	When the add/remove a friend.
      -	When they add/edit a recipe.

## Scrum Board Link
https://github.com/westd5/ProjectIT4045C/projects

## Scrum Roles
### UI Specialist 
 - Ishani Saxena
 - Ella Terselic
### Business Logic and Persistence Specialist
 - Isaac Van Meter
### Product Owner/Scrum Master/GitHub Administrator
 - Daniel West

## GitHub Project Link
https://github.com/westd5/ProjectIT4045C
