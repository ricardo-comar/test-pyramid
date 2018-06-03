package bookStore

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    method 'GET'
    url '/api/book/1000'
    headers {}
  }
response {
  status 200
  body("""
  {
    "id": 1000,
    "name": "test",
    "writter": "test writter",
    "price": 100.00
  }
  """)
  headers {
    header('Content-Type': 'application/json;charset=UTF-8')
  }
 }
}