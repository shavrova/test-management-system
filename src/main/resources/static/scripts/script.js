let addRow = function () {
        let listName = 'steps';
        let rowIndex = document.querySelectorAll('.item').length;
        let row = document.createElement('div');
        row.classList.add('row', 'item');
            let col = document.createElement('div');
            col.classList.add('col', 'form-group');
            let input = document.createElement('input');
            input.type = 'text';
            input.classList.add('form-control');
            input.setAttribute('name', 'description');
            input.id = 'autocomplete-input'+ rowIndex;
            col.appendChild(input);
            row.appendChild(col);
        document.getElementById('stepsList').appendChild(row);

                    $(function() {
                        $("#autocomplete-input"+rowIndex).autocomplete({
                            source : function(request, response) {
                                $.ajax({
                                    url : "search",
                                    dataType : "json",
                                    data : {
                                        q : request.term
                                    },
                                    success : function(data) {
                                        console.log(data);
                                        response(data);
                                    }
                                });
                            },
                            minLength : 2
                        });
                    });

};

let submitOnEnter = function(){
    $("form input").keypress(function (e) {
        if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
            $('button[type=submit] .default').click();
            return false;
        } else {
            return true;
        }
    });
};

let deleteStep = function(testId, stepId){
        $.ajax({
            url: "api/tests/"+testId+"/steps/"+stepId,
            type: "DELETE",
            success: function(response)
            {
                alert(response);
                location.reload();
            }
        });
    };