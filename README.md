# employee-management-api

This is an API implemented based on Spring boot framework. 
It will expose REST services to perform CRUD operations on REST resource
It will also provide services to search resources based on given criteria like below
   1) Search employee by his/her last name
   2) Search employee by his/her gender

Compile and run employee-management-api locally:
mvn clean install
mvn spring-boot:run 

Testing the API:
--Create an employee
curl -v localhost -H "Content-Type: application/json" -d '{
"firstName": "Thirupathi",
"lastName": "Maram",
"birthDate": "1985-08-10",
"hireDate": "2022-03-02",
"gender": "M"
}' 'http://localhost:8088/employees'


--Fetch all employees
curl -v localhost -H "Content-Type: application/json" 'http://localhost:8088/employees'

--Search employee by lastname
curl -v localhost -H "Content-Type: application/json" 'http://localhost:8088/employees/searchBy/Maram'



