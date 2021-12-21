# Create Rest API for Login and User Management with:

1. UserId
2. Email
3. Password
4. Employee Number --> auto generate with format YYYYXXX ---> YYYY is year, XXX is auto increment eg: 2021001
5. Birth Place (City)
6. Birth Date
 

# Function, more function complete or solve the better:

1. Login (email and password)
2. View User include paging and sorting (secured with token from login)
3. Search by Email or Employee Number (secured with token from login)
4. Edit User Profile(secured with token from login)
5. Change Password (secured with token from login)
6. Active/Deactive User - User Cant login after deactivation(secured with token from login)


# Demo

## 1. Login (Email and password)
![image](https://user-images.githubusercontent.com/28156593/146935443-de176c46-f948-4fb1-826a-ac38f327345d.png)

- All User without Auth
   ![image](https://user-images.githubusercontent.com/28156593/146935548-fcf13f43-34ff-4371-9ed1-afb3c63c2a9f.png)
- All User with Auth but not valid
   ![image](https://user-images.githubusercontent.com/28156593/146935716-91637da3-5906-47a5-936f-9a759d6a0ed1.png)
- All User With auth and valid
   ![image](https://user-images.githubusercontent.com/28156593/146935787-7bbfe6a2-14b0-4584-8228-73e6b3016049.png)
  
## 2. View All User with sorting and paging
    ![image](https://user-images.githubusercontent.com/28156593/146935971-ec11c2cd-71f9-4055-befe-521e4957d1d3.png)
  
## 3. Search User By Email or Employee Number
 - By Employee Number 
   ![image](https://user-images.githubusercontent.com/28156593/146936507-f24f166b-edad-4ad3-a09d-598ab9507361.png)
 - By Email
   ![image](https://user-images.githubusercontent.com/28156593/146936438-bb9df32f-bbcc-4d09-a8a4-05a1361b8a67.png)
 - Not Found
   ![image](https://user-images.githubusercontent.com/28156593/146936657-27c8517f-7047-4b86-92bd-b156ca687c0c.png)
## 4. Edit User Profile (By Email)
  - API edit
   ![image](https://user-images.githubusercontent.com/28156593/146936950-c85e5c61-172d-49aa-bb31-bac3bd92e204.png)
  - Result
   ![image](https://user-images.githubusercontent.com/28156593/146937072-713efcec-940d-49c8-8e4a-a5ad68868036.png)
## 5. Change Password 
  - Current password is ADMIN
   ![image](https://user-images.githubusercontent.com/28156593/146937196-69c6f547-014f-4fe3-8b6d-3ce672da1ef1.png)
  - Wrong old password
   ![image](https://user-images.githubusercontent.com/28156593/146937315-3fa6731b-c31a-4515-8569-11c587b32566.png)
  - Right old password and change to new password
   ![image](https://user-images.githubusercontent.com/28156593/146937533-5ee56f43-c35c-44e0-978b-b535cdc3ef76.png)
  - Then this password is invalid when hit sigin API with *admin* password 
   ![image](https://user-images.githubusercontent.com/28156593/146937682-11efd174-1835-4162-abc9-3afb2a7c6c2d.png)
  - But work when use *test* password
   ![image](https://user-images.githubusercontent.com/28156593/146937766-69951f1b-28de-472b-bb1d-6bf5145689be.png)
## 6. Activate and Deactivate 


