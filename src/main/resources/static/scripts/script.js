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
            input.setAttribute('name', 'newStepDescription');
            col.appendChild(input);
            row.appendChild(col);
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