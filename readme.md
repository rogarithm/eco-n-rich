### 사용 기술

- Java, MySql, Spring Framework, AWS

### 구현 API 명세
1. 특정 사원의 현재 정보 조회
2. 특정 사원의 이력 정보 조회
3. 부서 및 위치 정보 조회
4. 특정 부서에 속한 사원의 급여를 특정 비율로 인상

#### 1. 특정 사원의 현재 정보 조회

- `GET http://{HOST}:8080/employees/{employeeId}`
- 예시
  - 요청 `http://localhost:8080/employees/100`
  - 응답
```json
  {
  "id": 100,
  "firstName": "Steven",
  "lastName": "King",
  "email": "SKING",
  "phoneNumber": "515.123.4567",
  "hireDate": "1987-06-17",
  "jobId": "AD_PRES",
  "salary": 24000.00,
  "commissionPct": null,
  "managerId": null,
  "departmentId": 90
}
```

#### 2. 특정 사원의 이력 정보 조회

- `GET http://{HOST}:8080/employees/{employeeId}/history`
- 예시
  - 요청 `http://localhost:8080/employees/102/history`
  - 응답
```json
{
  "employeeId": 102,
  "startDate": "1993-01-13",
  "endDate": "1998-07-24",
  "jobId": "IT_PROG",
  "departmentId": 60
}
```

#### 3. 부서 및 위치 정보 조회

- `GET http://{HOST}:8080/employees/{employeeId}/department`
- 예시
  - 요청 `http://localhost:8080/employees/200/department`
  - 응답
```json
{
  "id": 10,
  "departmentName": "Administration",
  "managerId": 200,
  "locationId": 1700,
  "streetAddress": "2004 Charade Rd",
  "postalCode": "98199",
  "city": "Seattle",
  "stateProvince": "Washington",
  "countryId": "US"
}
```

#### 4. 특정 부서에 속한 사원의 급여를 특정 비율로 인상

- `PUT http://{HOST}:8080/departments?departmentId={departmentId}&raiseRate={raiseRate}`
- 예시 1
  - 요청: `http://localhost:8080/departments?departmentId=20&raiseRate=2.0`
  - 응답
```json
[
  {
    "employeeId": 201,
    "salaryBefore": 13000.00,
    "salaryAfter": 26000.00
  }
]
```
- 예시 2
  - 요청: `http://localhost:8080/departments?departmentId=90&raiseRate=2.0`
  - 응답
```json
[
  {
    "employeeId": 200,
    "salaryBefore": 4400.00,
    "salaryAfter": 8800.00
  },
  {
    "employeeId": 200,
    "salaryBefore": 8800.00,
    "salaryAfter": 17600.00
  }
]
```
