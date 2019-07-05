/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function hitungUlang(_lifegoal) {
    var refID = $("#refID").val();

    switch (_lifegoal)
    {
        case "growth":
            var amount = $("#amount").val();
            var type = $("#type").val();
            var tenor = $("#tenor").val();
            window.location.href = "/growth/" + refID + "/" + amount + "/" + type + "/" + tenor;
            break;
        case "education":
            var age = $("#age").val();
            var country = $("#countryValue").val();
            var value = $("#dana").val();
            var name = $("#name").val();

            window.location.href = "/education/" + refID + "/" + age + "/" + country + "/" + value + "/" + name;
            break;
        case "etc":
            var goals = $("#goals").text();
            var name = $("#name").text();
            var dana_sekarang = $("#dana_sekarang").val();
            var target_dana = $("#target_dana").val();
            var tenor = $("#jangka_waktu").val();

            window.location.href = "/etc/" + refID + "/" + goals + "/" + name + "/" + dana_sekarang + "/" + target_dana + "/" + tenor;
            break;

    }
}

function submit(_lifegoal) {
    var message_in;
    var refID = $("#refID").val();
    var tabunganResult = $("#tabunganResult").text();
    var investasiResult = $("#investasiResult").text();

    switch (_lifegoal) {
        case "growth":
            var amount = $("#amount").val();
            var tenor = $("#tenor").val();
            message_in = amount + "&" + tenor + "&" + tabunganResult + "&" + investasiResult;
            break;
        case "education":
            var age = $("#age").val();
            var country = $("#countryValue").val();
            var value = $("#dana").val();
            message_in = age + "&" + country + "&" + value + "&" + tabunganResult + "&" + investasiResult;

            break;
        case "etc":
            var target_dana = $("#target_dana").val();
            var dana_sekarang = $("#dana_sekarang").val();

            var tenor = $("#jangka_waktu").val();
            message_in = target_dana + "&" + dana_sekarang + "&" + tenor + "&" + tabunganResult + "&" + investasiResult;

            break;
    }
    talk(refID, message_in, "Goal Sudah Sesuai");
}

$(function () {
    var lifegoal = $("#lifegoal").val();

    $('#countrySelect').on('change', function () {
        var cvalue = $('option:selected').val();
        $('#countryValue').val(cvalue);
    });

    $('#hitungUlang').click(function () {
        hitungUlang(lifegoal);
    });

    $('#btnSubmit').on('click', function () {
        submit(lifegoal);
    });






    $('input.inputnum').on('keyup input', function (event) {

        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
    });
    $('input.currency').on('keyup input', function (event) {
        if (event.which >= 37 && event.which <= 40)
            return;

        $(this).val(function (index, value) {
            return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
    });

    $('.ocbc_webview .openTrigger').on('click', function () {
        $('.ocbc_webview .productDetail').slideUp();
        $(this).siblings('.productDetail').slideDown();
    });

    $('.ocbc_webview #showAllCat').on('click', function () {
//        $('.ocbc_webview .product_category').removeClass('hidden');
        $('.ocbc_webview .product_category').css('display', 'block')
        $('#riskNotif').hide();
    });

    $('.ocbc_webview .higherRisk_selection').on('click', function () {
        if ($(this).hasClass('btnBalance'))
            $('.ocbc_webview #pilihanUser').text('(BALANCE)')
        else if (($(this).hasClass('btnGrowth')))
            $('.ocbc_webview #pilihanUser').text('(GROWTH)')
        else if (($(this).hasClass('btnAggresive')))
            $('.ocbc_webview #pilihanUser').text('(AGGRESIVE)')

        $('.ocbc_webview .highRiskConfirm').show();
    });

    $('.ocbc_webview #highRiskConfirm_close').on('click', function () {
        $('.ocbc_webview .highRiskConfirm').hide();
    });
});