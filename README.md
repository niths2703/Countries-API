# Countries-API
This Project uses RestCountries Live APIs to fetch list of all countries. We can also search countries by their name. This project also lets the user fetch list of countries on basis of filters based on area and population and display the result in a sorted and paged view.

Authorization API: 
curl --location 'localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username":"prashant",
    "password":"abcd"
}'


API Countries - List by Filter based on area and population : 
curl --location 'localhost:8080/countries/filter/desc/1/2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcmFzaGFudCIsImlhdCI6MTcwMjY5ODA4MSwiZXhwIjoxNzAyNzE2MDgxfQ.QkRC5Yn4Z2wjmVOvXk30Mm_Qr7GkGK0vEoMDvR4-2ZX6XbLjGpy_eHGsOdHCVQygNW704zcm-EWTSVsaAR5Bag' \
--header 'Content-Type: application/json' \
--data '{
    "areaMin":130.0,
    "areaMax":1400.0,
    "populationMin":2000,
    "populationMax":45000,
    "language":"English"
    
}'

API Countries List by name :
curl --location 'localhost:8080/countries/Estonia' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcmFzaGFudCIsImlhdCI6MTcwMjcwODUxNSwiZXhwIjoxNzAyNzI2NTE1fQ.VuKAqs64c7MLNpm5PJlEykxuMhM9CP0YY95iAsFv6scFlQ6j5aFAQmm8a0ipdwMEZo_uQtHwuz-XyWM5lHRvlA'


API - Get All Countries List :
curl --location --request GET 'localhost:8080/countries' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaXRpc2giLCJpYXQiOjE3MDI0NjY3NDUsImV4cCI6MTcwMjQ4NDc0NX0.XKdPqOLS7YVhIelqd3gqPqHOCVGFoTrVncLUVnhPzgH_hrjHJNAjQr9_IM91NBvNEJN-sEr-3feCuqicXKZ-ag' \
--header 'Content-Type: application/json' \
--data '{
    
}'






