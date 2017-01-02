function validate_fields() {
    var re16digit = /^\d{16}$/
    var phoneNumber = /^\d{10}$/
    var license_num_js = /^[A-Z]{1,3}-[A-Z]{1,2}-[0-9]{1,4}$/

    if (!phoneNumber.test(document.personal_information.phoneNumber.value)) {
        alert("Please enter your 10 digit valid phone number..!!");
        return false;
    }

    else if (!re16digit.test(document.personal_information.creditcard.value)) {
        alert("Please enter your 16 digit valid credit card number..!!");
        return false;
    }

    else if (!license_num_js.test(document.personal_information.license_num.value)) {
        alert("Please enter your license number in correct format. Hint : <1-3 CAPS letters> - <1-2 CAPS letters> - <1-4 numbers> ");
        return false;
    }

    
}

