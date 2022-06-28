window.onload = function(){
    document.getElementById("addr1").addEventListener("click", function(){
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById("addr1").value = data.address;
                document.querySelector("input[name=addr2]").focus();
            }
        }).open();
    });
}