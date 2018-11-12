$(document).ready(function () {
    bindEventCheckAllCheckbox();
    autoCheckAllCheckBox();
    enableOrDisableDeleteAll();
});
function bindEventCheckAllCheckbox() {
    $("#checkAll").change(function () {
        if((this).checked){
            $(this).closest('table').find('tbody').find('input[type=checkbox]').prop('checked', true);
        } else{
            $(this).closest('table').find('tbody').find('input[type=checkbox]').prop('checked', false);
            $('#btnDelete').prop('disabled',true);
        }
    })
}
function autoCheckAllCheckBox() {
    //dem cac checkbox
    var totalCheckbox = $('#checkAll').closest('table').find('tbody').find('input[type=checkbox]').length;
    $('#checkAll').closest('table').find('tbody').find('input[type=checkbox]').each(function () {
        $(this).on('change',function () {
            var totalCheckedCheckbox = $('#checkAll').closest('table').find('tbody').find('input[type=checkbox]:checked').length;
            if(totalCheckedCheckbox == totalCheckbox){
                $('#checkAll').prop('checked',true);
            } else if (totalCheckedCheckbox < totalCheckbox){
                $('#checkAll').prop('checked',false);
            }
        })
    })
}
function enableOrDisableDeleteAll() {
    $('input[type=checkbox]').click(function () {
        if($('input[type=checkbox]:checked').length > 0){
            $('#btnDelete').prop('disabled',false);
        } else {
            $('#btnDelete').prop('disabled',true);
        }
    })
}