angular.module("testerApp", [])
    .constant("baseUrl", "http://localhost:8080/api/testers/")
    .controller("defaultCtrl", function ($scope, $http, baseUrl) {

        // view
        $scope.currentView = "table";
        $scope.positions = ['JUNIOR', 'MIDDLE', 'SENIOR', 'TESTLEAD', 'MANAGER'];
        $scope.selected = $scope.positions[0];

        // get All
        $scope.refresh = function () {
            // HTTP GET
            $http.get(baseUrl).success(function (data) {
                $scope.items = data;
            });
        }

        // create new tester
        $scope.create = function (item) {
            // HTTP POST
            $http.post(baseUrl, item).success(function (item) {
                $scope.items.push(item);
                $scope.currentView = "table";
            });
        }

        // update tester
        $scope.update = function (item) {
            // HTTP PUT
            $http({
                url: baseUrl + item.id,
                method: "PUT",
                data: item
            }).success(function (modifiedItem) {
                for (var i = 0; i < $scope.items.length; i++) {
                    if ($scope.items[i].id == modifiedItem.id) {
                        $scope.items[i] = modifiedItem;
                        break;
                    }
                }
                $scope.currentView = "table";
            });
        }

        // delete tester
        $scope.delete = function (item) {
            // HTTP DELETE
             $http({
                method: "DELETE",
                url: baseUrl + item.id
            }).success(function () {
                $scope.items.splice($scope.items.indexOf(item), 1);
            });
        }

        // edit
        $scope.editOrCreate = function (item) {
            $scope.currentItem = item ? angular.copy(item) : {};
            $scope.currentView = "edit";
        }

/*        $scope.Create = function (item) {
            $scope.currentItem = item ? angular.copy(item) : {};
            $scope.currentView = "creay";
        }*/

        // save
        $scope.saveEdit = function (item) {
            if (angular.isDefined(item.id)) {
                $s
            } else {
                $scope.create(item);
            }cope.update(item);
        }

        // cencel
        $scope.cancelEdit = function () {
            $scope.currentItem = {};
            $scope.currentView = "table";
        }

        $scope.refresh();
    });