let addRow = function () {
        let listName = 'steps';
        let fieldsNames = ['id', 'stepDescription'];
        let rowIndex = document.querySelectorAll('.item').length;

        let row = document.createElement('div');
        row.classList.add('row', 'item');

        fieldsNames.forEach((fieldName) => {
            let col = document.createElement('div');
            col.classList.add('col', 'form-group');
            if (fieldName === 'id') {
                col.classList.add('d-none'); //field with id - hidden
            }

            let input = document.createElement('input');
            input.type = 'text';
            input.classList.add('form-control');
            input.id = listName + rowIndex + '.' + fieldName;
            input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);

            col.appendChild(input);

            row.appendChild(col);
        });

        document.getElementById('stepsList').appendChild(row);
};

let submitOnEnter = function() {
    $("form input").keypress(function (e) {
        if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
            $('button[type=submit] .default').click();
            return false;
        } else {
            return true;
        }
    });
};