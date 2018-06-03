package bookStore

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    method 'GET'
    url '/api/book/1'
    headers {
      header('Content-Type', 'application/json;charset=UTF-8')
    }
  }
response {
  status 200
  body("""
  {
    "id": 1,
    "name": "Test Book"
    "writter": "John Snow"
    "price": 135.56
  }
  """)
  headers {
    header('Content-Type': 'application/json;charset=UTF-8')
  }
 }
}