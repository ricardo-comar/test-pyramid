'use strict'

angular.module('demo.services', []).factory('BookService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.getBookById = function(bookId) {
				var url = CONSTANTS.getBookByIdUrl + bookId;
				return $http.get(url);
			}
			service.getAllBooks = function() {
				return $http.get(CONSTANTS.getAllBooks);
			}
			service.saveBook = function(bookDto) {
				return $http.post(CONSTANTS.saveBook, bookDto);
			}
			return service;
		} ]);