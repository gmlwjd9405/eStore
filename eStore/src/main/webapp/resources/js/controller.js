var cartApp = angular.module('cartApp', []);

cartApp.controller("cartCtrl", function($scope, $http) {

	/* 인자로 받은 cartId를 scope의 property에 등록한다. */
	$scope.initCartId = function(cartId) {
		$scope.cartId = cartId;
		$scope.refreshCart();
	};

	/* CartRestController의 getCartById API(GET Method)를 사용 */
	$scope.refreshCart = function() {
		$http.get('/eStore/rest/cart/' + $scope.cartId).then(
				/* 성공했을 때 불리는 callback 함수 */
				function successCallback(response) {
					/* response body에 있는 내용을 scope property에 저장 */
					$scope.cart = response.data;
				});
	};

	/* CartRestController의 addItem API(PUT Method)를 사용 */
	$scope.addToCart = function(productId) {
		$scope.setCsrfToken();

		$http.put('/eStore/rest/cart/add/' + productId).then(
				function successCallback() { /* 성공했을 때 불리는 callback 함수 */
					/* 성공했다는 창을 띄운다. */
					alert("Product successfully added to the cart!");
				}, function errorCallback() { /* 실패했을 때 불리는 callback 함수 */
					alert("Adding to the cart failed!");
				});
	};

	/* CartRestController의 removeItem API(DELETE Method)를 사용 */
	$scope.removeFromCart = function(productId) {
		$scope.setCsrfToken();

		/* 위와 표현 방식이 다름 */
		$http({
			method : 'DELETE',
			url : '/eStore/rest/cart/cartitem/' + productId
		}).then(function successCallbak() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	/* CartRestController의 clearCart API(DELETE Method)를 사용 */
	$scope.clearCart = function() {
		$scope.setCsrfToken();

		$http({
			method : 'DELETE',
			url : '/eStore/rest/cart/' + $scope.cartId
		}).then(function successCallbak() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	/* 단순히 TotalPrice를 계산하는 함수 */
	$scope.calGrandTotal = function() {
		var grandTotal = 0;

		for (var i = 0; i < $scope.cart.cartItems.length; i++) {
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}

		return grandTotal;
	};

	$scope.setCsrfToken = function() {
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$http.defaults.headers.common[csrfHeader] = csrfToken;
	};
	
});