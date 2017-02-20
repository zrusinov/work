function Hello($scope, $http) {
    $http.get('http://localhost:8181/greeting?name=User').
        success(function(data) {
            $scope.greeting = data;
        });
}