function validateForm() {
    var locationCode = document.getElementById("locationCode").value;
    var locationName = document.getElementById("locationName").value;
    var locationType = document.getElementById("locationType").value;

    if (!locationCode || !locationName || !locationType) {
        alert("Please fill in all required fields");
        return false;
    }

    var parentId = document.getElementById("parentId").value;
    if (parentId) {
        var uuidPattern = /^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i;
        if (!uuidPattern.test(parentId)) {
            alert("Invalid Parent ID format. Please enter a valid UUID.");
            return false;
        }
    }

    return true;
}
