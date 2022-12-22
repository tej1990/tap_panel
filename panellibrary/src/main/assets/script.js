function showAndroidToast() {
        let nameField = document.getElementById('nameField').value;
        Android.showToast(nameField);
    }
    function setMessageFromHtml(param) {
       document.getElementById('welcome').innerHTML = param;
    }
    function getMessageFromAndroid() {
       document.getElementById('welcome').innerHTML = Android.getMessage();
    }