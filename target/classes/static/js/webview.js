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
            var name = $("#name").val();
            var risk_profile_id = $("#risk_profile_id").val();
            window.location.href = "/growth/" + refID + "/" + amount + "/" + type + "/" + tenor + "/" + name + "/" + risk_profile_id;
            break;
        case "education":
            var age = $("#age").val();
            var country = $("#countryValue").val();
            var value = $("#dana").val();
            var name = $("#name").val();
            var risk_profile_id = $("#risk_profile_id").val();

            window.location.href = "/education/" + refID + "/" + age + "/" + country + "/" + value + "/" + name + "/" + risk_profile_id;
            break;
        case "etc":
            var goals = $("#goals").text();
            var name = $("#name").text();
            var dana_sekarang = $("#dana_sekarang").val();
            var target_dana = $("#target_dana").val();
            var tenor = $("#jangka_waktu").val();
            var risk_profile_id = $("#risk_profile_id").val();
            window.location.href = "/etc/" + refID + "/" + goals + "/" + name + "/" + dana_sekarang + "/" + target_dana + "/" + tenor + "/" + risk_profile_id;
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

function othersgoal() {
    var message_in;
    var refID = $("#refID").val();
    message_in = "";

    talk(refID, message_in, "Ganti Life Goal");
}

$(function () {
    var lifegoal = $("#lifegoal").val();
    var firstamount = $("#firstamount").val();
    var secondamount = $("#secondamount").val();

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

    $('#btnSubmitOthers').on('click', function () {
        othersgoal();
    });

    $('input.inputnum').on('keyup input', function (event) {
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
    });
    $('input.inputnumtenor').on('keyup input', function (event) {
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });

        if (parseInt($(this).val()) <= 50 && parseInt($(this).val()) >= 0)
            ;
        else if (parseInt($(this).val()) > 50)
//            $(this).val($(this).data("old"));
            $(this).val(50);
        else if (($(this).val()) <= 0)
            $(this).val(1);
    });

    $('input.inputnumage').on('keyup input', function (event) {
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });

        if (parseInt($(this).val()) <= 18 && parseInt($(this).val()) >= 0)
            ;
        else if (parseInt($(this).val()) > 18)
//            $(this).val($(this).data("old"));
            $(this).val(18);
        else if (($(this).val()) <= 0)
            $(this).val(1);
    });

    $('input.currency').on('keyup input', function (event) {
        if (event.which >= 37 && event.which <= 40)
            return;

        $(this).val(function (index, value) {
            return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
        if (($(this).val()) <= 0)
            $(this).val(firstamount);

    });
    $('input.currency2').on('keyup input', function (event) {
        if (event.which >= 37 && event.which <= 40)
            return;

        $(this).val(function (index, value) {
            return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
        if (($(this).val()) <= 0)
            $(this).val(secondamount);

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