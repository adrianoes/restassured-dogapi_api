# Known Bugs

This document details the currently known bugs in the project, based on the latest test results. Each entry includes the endpoint used, request body (if applicable), the expected response, and the actual response obtained for each failing test scenario.

---

## 1. -1 random images from all dogs collection
**Cucumber Scenario:**
`Scenario: -1 random images from all dogs collection` (features/breedsimagerandom.feature)
**Endpoint:**
```http
GET /breeds/image/random/-1
```
**Request Body:**
_None (GET request)_

**Expected Response:**
```json
{
  "status": "error",
  "message": "Invalid number of images requested."
}
```
**Actual Response:**
```json
{
  "status": "success",
  "message": [
    "https://images.dog.ceo/breeds/akita/1.jpg",
    ...
  ]
}
```

---

## 2. 'a' random images from all dogs collection
**Cucumber Scenario:**
`Scenario: 'a' random images from all dogs collection` (features/breedsimagerandom.feature)
**Endpoint:**
```http
GET /breeds/image/random/a
```
**Request Body:**
_None (GET request)_

**Expected Response:**
```json
{
  "status": "error",
  "message": "Invalid number of images requested."
}
```
**Actual Response:**
```json
{
  "status": "success",
  "message": [
    "https://images.dog.ceo/breeds/akita/1.jpg",
    ...
  ]
}
```

---

## 3. 0 random images from all dogs collection
**Cucumber Scenario:**
`Scenario: 0 random images from all dogs collection` (features/breedsimagerandom.feature)
**Endpoint:**
```http
GET /breeds/image/random/0
```
**Request Body:**
_None (GET request)_

**Expected Response:**
```json
{
  "status": "error",
  "message": "Invalid number of images requested."
}
```
**Actual Response:**
```json
{
  "status": "success",
  "message": [
    "https://images.dog.ceo/breeds/akita/1.jpg",
    ...
  ]
}
```

---

## 4. @ random images from all dogs collection
**Cucumber Scenario:**
`Scenario: @ random images from all dogs collection` (features/breedsimagerandom.feature)
**Endpoint:**
```http
GET /breeds/image/random/@
```
**Request Body:**
_None (GET request)_

**Expected Response:**
```json
{
  "status": "error",
  "message": "Invalid number of images requested."
}
```
**Actual Response:**
```json
{
  "status": "success",
  "message": [
    "https://images.dog.ceo/breeds/akita/1.jpg",
    ...
  ]
}
```

---

## 5. a multiple random image from a breed
**Cucumber Scenario:**
`Scenario: a multiple random image from a breed` (features/breedimages.feature)
**Endpoint:**
```http
GET /breed/clumber/images/random/a
```
**Request Body:**
_None (GET request)_

**Expected Response:**
```json
{
  "status": "error",
  "message": "Invalid number of images requested."
}
```
**Actual Response:**
```json
{
  "status": "success",
  "message": [
    "https://images.dog.ceo/breeds/clumber/1.jpg",
    ...
  ]
}
```

---

> Note: The actual response arrays are truncated for readability. In all cases, the API should return an error status and message for invalid input, but currently returns a success status and an array of images.