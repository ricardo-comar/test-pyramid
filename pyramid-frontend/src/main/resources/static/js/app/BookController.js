'use strict'

var module = angular.module('demo.controllers', []);
module.controller("BookController", [ "$scope", "BookService",
		function($scope, BookService) {

			$scope.bookDto = {
				id : null,
				name : null,
				writter : null,
				price : null
			};
			
			BookService.getBookById(1).then(function(value) {
				console.log(value.data);
			}, function(reason) {
				console.log("error occured");
			}, function(value) {
				console.log("no callback");
			});

			$scope.saveBook = function() {
				BookService.saveBook($scope.bookDto).then(function() {
					console.log("works");
					BookService.getAllBooks().then(function(value) {
						$scope.allBooks= value.data;
					}, function(reason) {
						console.log("error occured");
					}, function(value) {
						console.log("no callback");
					});

					$scope.bookDto = {
						id : null,
						name : null,
						writter : null,
						price : null
					};
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		} ]);