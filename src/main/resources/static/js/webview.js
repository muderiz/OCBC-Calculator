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
            var goalsetc = $("#goals").val();
            var name = $("#name").text();
            var dana_sekarang = $("#dana_sekarang").val();
            var target_dana = $("#target_dana").val();
            var tenor = $("#jangka_waktu").val();
            var risk_profile_id = $("#risk_profile_id").val();

            window.location.href = "/etc/" + refID + "/" + goalsetc + "/" + name + "/" + dana_sekarang + "/" + target_dana + "/" + tenor + "/" + risk_profile_id;
            break;
        case "product":
            var url;
            var lifegoalid = $("#lifegoalid").val();
            var namareksa = $("#namereksadana").val();
            var averagerate = $("#averagerate").val();
            var badrate = $("#badrate").val();
            var goodrate = $("#goodrate").val();
            var amount = $("#amount").val();
            var tenor = $("#tenor").val();
            var riskprofileid = $("#riskprofileid").val();
            var investmenttype = $("#investmenttype").val();
            var productid = $("#productid").val();
            var producttype = $("#producttype").val();
            var country = $("#country").val();
            var initial_amount = $("#initial_amount").val();

            if (lifegoalid == 1) {
                url = "/product/finalgrowth/" + refID + "/" + namareksa + "/" + averagerate + "/" + badrate + "/" + goodrate + "/"
                        + amount + "/" + tenor + "/" + riskprofileid + "/" + investmenttype + "/" + producttype + "/" + productid;
            } else {
                url = "/product/finaletc/" + refID + "/" + lifegoalid + "/" + country + "/" + amount + "/" + namareksa + "/"
                        + averagerate + "/" + badrate + "/" + goodrate + "/" + initial_amount + "/" + tenor + "/" + riskprofileid + "/"
                        + investmenttype + "/" + producttype + "/" + productid;
            }

            window.location.href = url;
            break;

    }
}

function submit(_lifegoal) {
    var message_in;
    var refID = $("#refID").val();
    var IdChannel = $("#idchannel").val();
    var tabunganResult = $("#tabunganResult").text();
    var investasiResult = $("#investasiResult").text();
    var tabunganRate = $("#tabunganRate").text();
    var investasiRate = $("#investasiRate").text();

    switch (_lifegoal) {
        case "growth":
            var amount = $("#amount").val();
            var tenor = $("#tenor").val();
            message_in = amount + "&" + tenor + "&" + investasiResult + "&" + tabunganResult + "&" + investasiRate + "&" + tabunganRate;
            break;
        case "education":
            var age = $("#age").val();
            var country = $("#firstvaluecountry").val();
            var value = $("#dana").val();
            var targetamount = $("#targetamount").val();
            message_in = age + "&" + country + "&" + value + "&" + investasiResult + "&" + tabunganResult + "&" + investasiRate + "&" + tabunganRate + "&" + targetamount;

            break;
        case "etc":
            var target_dana = $("#target_dana").val();
            var dana_sekarang = $("#dana_sekarang").val();

            var tenor = $("#jangka_waktu").val();
            message_in = target_dana + "&" + dana_sekarang + "&" + tenor + "&" + investasiResult + "&" + tabunganResult + "&" + investasiRate + "&" + tabunganRate;

            break;


    }
    talk(IdChannel, message_in, "Goal Sudah Sesuai");
}

function finalgrowth() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    var namareksadana = $("#namareksadana").val();
    var resultamount = $("#resultamount").val();
    var rate = $("#rate").val();

    message_in = "lainnya" + "&" + namareksadana + "&" + resultamount + "&" + rate;

    talk(IdChannel, message_in, "Pilih Jenis Investasi");
}

function finaletclumpsum() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    var namareksadana = $("#namareksadana").val();
    var modeinvestlumpsum = $("#modeinvestlumpsum").val();
    var resultPresentlumpsum = $("#resultPresentlumpsum").val();
    var ratelumpsum = $("#ratelumpsum").val();

    message_in = "lainnya" + "&" + namareksadana + "&" + modeinvestlumpsum + "&" + resultPresentlumpsum + "&" + ratelumpsum;

    talk(IdChannel, message_in, "Pilih Jenis Investasi");
}

function finaletcmonthly() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    var namareksadana = $("#namareksadana").val();
    var modeinvestannual = $("#modeinvestannual").val();
    var resultTargetannual = $("#resultTargetannual").val();
    var rateannual = $("#rateannual").val();

    message_in = "lainnya" + "&" + namareksadana + "&" + modeinvestannual + "&" + resultTargetannual + "&" + rateannual;

    talk(IdChannel, message_in, "Pilih Jenis Investasi");
}

function othersgoal() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    message_in = "ganti life goal";

    talk(IdChannel, message_in, "Ganti Life Goal");
}

function tnc() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    message_in = "setuju";

    talk(IdChannel, message_in, "Setuju");
}

function back() {
    window.history.back();
}

$(function () {
    var lifegoal = $("#lifegoal").val();
    var firstamount = $("#firstamount").val();
    var firsttenor = $("#firsttenor").val();
    var firstage = $("#firstage").val();
    var firstcountry = $("#firstnamecountry").val();
    var secondamount = $("#secondamount").val();
    var rc = $("#rc").val();
    var rcdesc = $("#rcdesc").val();

    $('.calcInput .errorMsg').hide();
    $('.calcInput .errorMsg2').hide();
    $('.calcInput .errorMsgTenor').hide();
    $('.calcResult .errorMsgHitung').hide();
    $('.errorRc').hide();
    $('.ocbc_webview .ocbc_webview_overlay .highRiskConfirm').hide();
    $('#imagelumpsum').hide();
    $('#imageannual').hide();

    $(window).load(function () {
        switch (lifegoal) {
            case "growth":
                if (parseInt(rc) > 0 && parseInt(rc) < 8 || parseInt(rc) == 99) {
                    $('.ocbc_webview .ocbc_webview_overlay .highRiskConfirm').show();
                    $("#textrcdesc").val(rcdesc);
                    $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
                    $('.calcResult .errorMsgHitung').show().css('color', 'red');
                } else if (parseInt(rc) == 8) {
                    $('.validRC').hide();
                    $('.errorRc').show();
                    $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
                    $('.calcResult .errorMsgHitung').show().css('color', 'red');
                }
                break;
            case "education":
                if (parseInt(rc) > 0 && parseInt(rc) < 10 || parseInt(rc) == 99) {
                    $('.ocbc_webview .ocbc_webview_overlay .highRiskConfirm').show();
                    $("#textrcdesc").val(rcdesc);
                    $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
                    $('.calcResult .errorMsgHitung').show().css('color', 'red');
                } else if (parseInt(rc) == 10) {
                    $('.validRC').hide();
                    $('.errorRc').show();
                    $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
                    $('.calcResult .errorMsgHitung').show().css('color', 'red');
                }
                break;
            case "etc":
                if (parseInt(rc) > 0 && parseInt(rc) < 10 || parseInt(rc) == 99) {
                    $('.ocbc_webview .ocbc_webview_overlay .highRiskConfirm').show();
                    $("#textrcdesc").val(rcdesc);
                    $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
                    $('.calcResult .errorMsgHitung').show().css('color', 'red');
                } else if (parseInt(rc) == 10) {
                    $('.validRC').hide();
                    $('.errorRc').show();
                    $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
                    $('.calcResult .errorMsgHitung').show().css('color', 'red');
                }

                break;

            case "finalgowth":
                var typedesc = $("#typedesc").val();
                if (typedesc == "Bulanan") {
                    $('#imageannual').show();
                } else {
                    $('#imagelumpsum').show();
                }
                break;
            case "finaletc":

                break;
        }

    });

    $('#countrySelect').on('change', function () {
        var cvalue = $('option:selected').val();
        $('#countryValue').val(cvalue);

        if ($('#countryValue').val() == firstcountry && $('#dana').val() == firstamount && $('#age').val() == firstage) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
        }
    });

    $('#hitungUlang').click(function () {
        hitungUlang(lifegoal);
    });

    $('#btnSubmit').on('click', function () {
        submit(lifegoal);
    });

    $('#FinalGrowth').on('click', function () {
        finalgrowth();
    });

    $('#FinalEtcLumpsum').on('click', function () {
        finaletclumpsum();
    });

    $('#FinalEtcAnnual').on('click', function () {
        finaletcmonthly();
    });

    $('.testfinal').on('click', function () {
        hitungUlang(lifegoal);
    });

    $('#gantiproduk').on('click', function () {
        back();
    });

    $('#btnSubmitOthers').on('click', function () {
        othersgoal();
    });

    $('#proceed_tnc').on('click', function () {
        tnc();
    });

    $('input.inputnum').on('keyup input', function (event) {
        if ($(this).val() == 0) {
            $('.calcInput .formRow.error').css('border', '1px solid #dd4b39').css('padding-bottom', '20px');
            $('.calcInput .errorMsg').show();
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');

        } else {
            $('.calcInput .formRow.error').css('border', '').css('padding-bottom', '');
            $('.calcInput .errorMsg').hide();
            $('#hitungUlang').attr('disabled', false).css('opacity', '1');
        }
        if ($(this).val() == firstamount && $('#tenor').val() == firsttenor) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
        }
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
    });


    $('input.inputnumtenor').on('keyup input', function (event) {
        if ($(this).val() == 0) {
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
        } else {
            $('#hitungUlang').attr('disabled', false).css('opacity', '1');
        }
        if ($(this).val() == firsttenor && $('#amount').val() == firstamount) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');

        }
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });

        if (parseInt($(this).val()) <= 50 && parseInt($(this).val()) >= 0) {
            ;
        } else if (parseInt($(this).val()) > 50) {
//            $(this).val($(this).data("old"));
            $(this).val(50);
            $('.calcInput .errorMsgTenor').show();

        } else if (($(this).val()) <= 0) {
            $(this).val($(this).data("old"));
            $('.calcInput .errorMsgTenor').hide();
        }

    });

    $('input.inputnumtenorEtc').on('keyup input', function (event) {
        if ($(this).val() == 0) {
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
        } else {
            $('#hitungUlang').attr('disabled', false).css('opacity', '1');
        }
        if ($(this).val() == firsttenor && $('#dana_sekarang').val() == secondamount && $('#target_dana').val() == firstamount) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
        }

        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });

        if (parseInt($(this).val()) <= 50 && parseInt($(this).val()) >= 0) {
            ;
        } else if (parseInt($(this).val()) > 50) {
//            $(this).val($(this).data("old"));
            $(this).val(50);
            $('.calcInput .errorMsgTenor').show();

        } else if (($(this).val()) <= 0) {
            $(this).val($(this).data("old"));
            $('.calcInput .errorMsgTenor').hide();
        }

    });

    $('input.inputnumage').on('keyup input', function (event) {
        if (parseInt($(this).val()) <= 18 && parseInt($(this).val()) >= 0) {
            ;
        } else if (parseInt($(this).val()) > 18) {
            $('.calcInput .errorMsgTenor').show();
//            $(this).val($(this).data("old"));
            $(this).val(18);
        } else if (($(this).val()) <= 0) {
//            $(this).val(1);
            $(this).val($(this).data("old"));
            $('.calcInput .errorMsgTenor').hide();
        }
        if ($(this).val() == 0) {
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
        } else {
            $('#hitungUlang').attr('disabled', false).css('opacity', '1');
        }
        if ($(this).val() == firstage && $('#dana').val() == firstamount && $('#countryValue').val() == firstcountry) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
        }
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

    $('input.inputnum2').on('keyup input', function (event) {
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
        if (event.which >= 37 && event.which <= 40) {
            return;
        }

        $(this).val(function (index, value) {
            return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
        if ($(this).val() == 0) {
            $('.calcInput .formRow.error').css('border', '1px solid #dd4b39').css('padding-bottom', '20px');
            $('.calcInput .errorMsg').show();
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
        } else {
            $('.calcInput .formRow.error').css('border', '').css('padding-bottom', '');
            $('.calcInput .errorMsg').hide();
            $('#hitungUlang').attr('disabled', false).css('opacity', '1');
        }
        if ($(this).val() == firstamount && $('#jangka_waktu').val() == firsttenor && $('#dana_sekarang').val() == secondamount) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
        }


    });

    $('input.inputnum3').on('keyup input', function (event) {
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
        if (event.which >= 37 && event.which <= 40) {
            return;
        }

        $(this).val(function (index, value) {
            return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
        if ($(this).val() == 0) {
            $('.calcInput .formRow.error2').css('border', '1px solid #dd4b39').css('padding-bottom', '20px');
            $('.calcInput .errorMsg2').show();
            $(this).val(0);
        } else {
            $('.calcInput .formRow.error2').css('border', '').css('padding-bottom', '');
            $('.calcInput .errorMsg2').hide();
        }
        if ($(this).val() == secondamount && $('#jangka_waktu').val() == firsttenor && $('#target_dana').val() == firstamount) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
        }


    });

    $('input.inputnum4').on('keyup input', function (event) {
//        if (($(this).val()) <= 0) {
//            $(this).val(0);
//        }
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
        if (event.which >= 37 && event.which <= 40) {
            return;
        }

        $(this).val(function (index, value) {
            return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
        if ($(this).val() <= 0) {
            $('.calcInput .formRow.error').css('border', '1px solid #dd4b39').css('padding-bottom', '20px');
            $('.calcInput .errorMsg').show();
            $(this).val(0);
        } else {
            $('.calcInput .formRow.error').css('border', '').css('padding-bottom', '');
            $('.calcInput .errorMsg').hide();
        }

        if ($(this).val() == firstamount && $('#age').val() == firstage && $('#countryValue').val() == firstcountry) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
        }

    });

    $('#check_tnc').on('click', function () {
        var checked_status = this.checked;
        if (checked_status == true) {
            $("#proceed_tnc").removeAttr("disabled").removeClass('disabled');
        } else {
            $("#proceed_tnc").attr("disabled", "disabled").addClass('disabled');
        }
    });

    $('.ocbc_webview .openTrigger').on('click', function () {
        $('.ocbc_webview .productDetail').slideUp();
        $(this).siblings('.productDetail').slideDown();



    });

    $('.ocbc_webview #showAllCat').on('click', function () {
//        $('.ocbc_webview .product_category').removeClass('hidden');
        $('.ocbc_webview .product_category').css('display', 'block');
        $('#riskNotif').hide();
    });

    $('.ocbc_webview .higherRisk_selection').on('click', function () {
        if ($(this).hasClass('btnBalance')) {
            $('.ocbc_webview #pilihanUser').text('(BALANCE)');
            $('.ocbc_webview .highRiskConfirm').show();
        } else if (($(this).hasClass('btnGrowth'))) {
            $('.ocbc_webview #pilihanUser').text('(GROWTH)');
            $('.ocbc_webview .highRiskConfirm').show();
        } else if (($(this).hasClass('btnAggresive'))) {
            $('.ocbc_webview #pilihanUser').text('(AGGRESIVE)');
            $('.ocbc_webview .highRiskConfirm').show();

        }
    });
    $('.ocbc_webview #highRiskConfirm_close').on('click', function () {
        $('.ocbc_webview .highRiskConfirm').hide();
    });

    $('.productList.type1').click(function () {
        var namareksa = $(this).find('input.namareksa').val();
        var averagerate = $(this).find('input.averagerate').val();
        var badrate = $(this).find('input.badrate').val();
        var goodrate = $(this).find('input.goodrate').val();
        var productid = $(this).find('input.productid').val();
        var producttype = $(this).find('input.producttype').val();
        $('#namereksadana').val(namareksa);
        $('#averagerate').val(averagerate);
        $('#badrate').val(badrate);
        $('#goodrate').val(goodrate);
        $('#productid').val(productid);
        $('#producttype').val(producttype);
        $('#riskprofileid').val(1);
    });

    $('.productList.type2').click(function () {
        var namareksa = $(this).find('input.namareksa').val();
        var averagerate = $(this).find('input.averagerate').val();
        var badrate = $(this).find('input.badrate').val();
        var goodrate = $(this).find('input.goodrate').val();
        var productid = $(this).find('input.productid').val();
        var producttype = $(this).find('input.producttype').val();
        $('#namereksadana').val(namareksa);
        $('#averagerate').val(averagerate);
        $('#badrate').val(badrate);
        $('#goodrate').val(goodrate);
        $('#productid').val(productid);
        $('#producttype').val(producttype);
        $('#riskprofileid').val(2);

    });
    $('.productList.type3').click(function () {
        var namareksa = $(this).find('input.namareksa').val();
        var averagerate = $(this).find('input.averagerate').val();
        var badrate = $(this).find('input.badrate').val();
        var goodrate = $(this).find('input.goodrate').val();
        var productid = $(this).find('input.productid').val();
        var producttype = $(this).find('input.producttype').val();
        $('#namereksadana').val(namareksa);
        $('#averagerate').val(averagerate);
        $('#badrate').val(badrate);
        $('#goodrate').val(goodrate);
        $('#productid').val(productid);
        $('#producttype').val(producttype);
        $('#riskprofileid').val(3);

    });
    $('.productList.type4').click(function () {
        var namareksa = $(this).find('input.namareksa').val();
        var averagerate = $(this).find('input.averagerate').val();
        var badrate = $(this).find('input.badrate').val();
        var goodrate = $(this).find('input.goodrate').val();
        var productid = $(this).find('input.productid').val();
        var producttype = $(this).find('input.producttype').val();
        $('#namereksadana').val(namareksa);
        $('#averagerate').val(averagerate);
        $('#badrate').val(badrate);
        $('#goodrate').val(goodrate);
        $('#productid').val(productid);
        $('#producttype').val(producttype);
        $('#riskprofileid').val(4);

    });


    $('.ocbc_webview #highRiskConfirm_next').on('click', function () {
        hitungUlang(lifegoal);
    });
});
