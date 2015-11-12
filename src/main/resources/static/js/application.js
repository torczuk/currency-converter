$(document).ready(function() {
       $("#registrationForm").submit(function(e) {
            $.ajax({
                   type: "POST",
                   url: "/register",
                   data: JSON.stringify({
                        firstName: $('#firstName').val(),
                        lastName: $('#lastName').val(),
                        email: $('#email').val(),
                        dateOfBirth: $('#dateOfBirth').val(),
                        address: $('#address').val(),
                        postCode: $('#postCode').val(),
                        city: $('#city option:selected').val(),
                        password: $('#password').val()
                   }),
                   contentType: "application/json; charset=utf-8",
                   beforeSend: function(xhr) {
                        xhr.setRequestHeader("Accept", "application/json");
                        xhr.setRequestHeader("Content-Type", "application/json");
                    },
                   success: function(data)
                   {
                       location.href = "/login?created";
                   },
                   error: function(xhr, textStatus, errorThrown) {
                         $.each(['#firstNameError', '#lastNameError', '#emailError', '#passwordError'], function(i, item) {
                            $(item).text('');
                         });
                        $.each(xhr.responseJSON, function(i, item) {
                            var field = item.field;
                            $('#' + field + 'Error').text(item.defaultMessage);
                        });
                      }
                 });
            e.preventDefault();
       });

       $.ajax({
            type:"GET",
            url: "/dictionary/city",
            dataType: "json",
            success: function(cities) {
                $.each(cities, function (i, item) {
                    $('#city').append($('<option></option>', {
                        value: item.id,
                        text : item.value
                    }));
                });
            }
       });
});