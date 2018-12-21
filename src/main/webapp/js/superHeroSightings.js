
$('document').ready(
        function nameHasError() {

            var error = $('#nameError').text();

            console.log(error);

            if (error !== "") {
                $('#newModal').modal('show');
            }

            if (error !== "") {
                $('#editModal').modal('show');
            }

            var zipError = $('#zipError').text();

            console.log(zipError);

            if (zipError !== "") {
                $('#newModal').modal('show');
            }

            if (zipError !== "") {
                $('#editModal').modal('show');
            }

            var emailError = $('#emailError').text();

            console.log(emailError);

            if (emailError !== "") {
                $('#newModal').modal('show');
            }

            if (emailError !== "") {
                $('#editModal').modal('show');
            }

            var phoneError = $('#phoneError').text();

            console.log(phoneError);

            if (phoneError !== "") {
                $('#newModal').modal('show');
            }

            if (phoneError !== "") {
                $('#editModal').modal('show');
            }

            var latError = $('#latError').text();

            console.log(latError);

            if (latError !== "") {
                $('#newModal').modal('show');
            }

            if (latError !== "") {
                $('#editModal').modal('show');
            }

            var lonError = $('#lonError').text();

            console.log(lonError);

            if (lonError !== "") {
                $('#newModal').modal('show');
            }

            if (lonError !== "") {
                $('#editModal').modal('show');
            }
        }

);


