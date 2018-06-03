'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers',
		'demo.services' ]);
demoApp.constant("CONSTANTS", {
	getBookByIdUrl : "/front/book/",
	getAllBooks : "/front/book?start=0&maxResult=20",
	saveBook : "/front/book"
});