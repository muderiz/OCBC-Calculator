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
            var tahun = $("#tahun").val();
            var bulan = $("#bulan").val();

            if (tahun == 0) {
                tenor = bulan;
            } else {
                tahun = parseInt(tahun) * 12;
                tenor = parseInt(tahun) + parseInt(bulan);
            }

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
            var goalsetc = $("#goalsdesc").val();
            var name = $("#name").val();
            var dana_sekarang = $("#dana_sekarang").val();
            var target_dana = $("#target_dana").val();
            var tenor = $("#jangka_waktu").val();
            var risk_profile_id = $("#risk_profile_id").val();
            var tahun = $("#tahun").val();
            var bulan = $("#bulan").val();

            if (tahun == 0) {
                tenor = bulan;
            } else {
                tahun = parseInt(tahun) * 12;
                tenor = parseInt(tahun) + parseInt(bulan);
            }

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
            var urlBack = $("#urlBack").val();

            if (lifegoalid == 1) {
                url = "/product/finalgrowth/" + refID + "/" + namareksa + "/" + averagerate + "/" + badrate + "/" + goodrate + "/"
                        + amount + "/" + tenor + "/" + riskprofileid + "/" + investmenttype + "/" + producttype + "/" + productid + "/" + urlBack;
            } else {
                url = "/product/finaletc/" + refID + "/" + lifegoalid + "/" + country + "/" + amount + "/" + namareksa + "/"
                        + averagerate + "/" + badrate + "/" + goodrate + "/" + initial_amount + "/" + tenor + "/" + riskprofileid + "/"
                        + investmenttype + "/" + producttype + "/" + productid + "/" + urlBack;
            }

            window.location.href = url;
            break;

    }
}

function back() {
    var urlBack = $("#urlBack").val();
    window.location.href = "/product/" + urlBack;
}

function submit(_lifegoal) {
    var message_in;
    var refID = $("#refID").val();
    var IdChannel = $("#idchannel").val();
    var firsttenor = $("#firsttenor").val();
    var tabunganResult = $("#tabunganResult").text();
    var investasiResult = $("#investasiResult").text();
    var tabunganRate = $("#tabunganRate").text();
    var investasiRate = $("#investasiRate").text();

    switch (_lifegoal) {
        case "growth":
            var amount = $("#amount").val();
//            var firsttenor = $("#tenor").text();
            message_in = amount + "&" + firsttenor + "&" + investasiResult + "&" + tabunganResult + "&" + investasiRate + "&" + tabunganRate;
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
            message_in = target_dana + "&" + dana_sekarang + "&" + firsttenor + "&" + investasiResult + "&" + tabunganResult + "&" + investasiRate + "&" + tabunganRate;

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
    var productid = $("#productid").val();

    message_in = "lainnya" + " & " + namareksadana + " & " + resultamount + " & " + rate + " & " + productid;

    talk(IdChannel, message_in, "Pilih Jenis Investasi");
}

function finaletclumpsum() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    var namareksadana = $("#namareksadana").val();
    var modeinvestlumpsum = $("#modeinvestlumpsum").val();
    var resultPresentlumpsum = $("#resultPresentlumpsum").val();
    var ratelumpsum = $("#ratelumpsum").val();
    var productid = $("#productid").val();

    message_in = "lainnya" + " & " + namareksadana + " & " + modeinvestlumpsum + " & " + resultPresentlumpsum + " & " + ratelumpsum + " & " + productid;

    talk(IdChannel, message_in, "Pilih Jenis Investasi");
}

function finaletcmonthly() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    var namareksadana = $("#namareksadana").val();
    var modeinvestannual = $("#modeinvestannual").val();
    var resultTargetannual = $("#resultTargetannual").val();
    var rateannual = $("#rateannual").val();
    var productid = $("#productid").val();


    message_in = "lainnya" + " & " + namareksadana + " & " + modeinvestannual + " & " + resultTargetannual + " & " + rateannual + " & " + productid;

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
function tncKembali() {
    var message_in;
    var IdChannel = $("#idchannel").val();
    message_in = "batal";

    talk(IdChannel, message_in, "Kembali");
}


//$('#download').click(function (e) {
//    var url = $("#url").val();
//    e.preventDefault();  //stop the browser from following
//    window.location.href = url;
//});

$(function () {
    var lifegoal = $("#lifegoal").val();
    var firstamount = $("#firstamount").val();
    var firsttenor = $("#firsttenor").val();
    var firstage = $("#firstage").val();
    var firstcountry = $("#firstnamecountry").val();
    var secondamount = $("#secondamount").val();
    var rc = $("#rc").val();
    var rcdesc = $("#rcdesc").val();
    var tahun = $("#tahun").val();
    var bulan = $("#bulan").val();
//    var riskprofileid = $("#riskprofileid").val();

    $('.recalc.form_row .errorMsg').hide();
    $('.recalc.form_row .errorMsg2').hide();
    $('.recalc.form_row .errorMsgTenor').hide();
    $('.recalc.form_row .errorMsgTahun').hide();
    $('.recalc.form_row .errorMsgBulan').hide();
    $('.recalc.form_row .errorMsgTahunBulan').hide();
    $('.calcResult .errorMsgHitung').hide();
    $('.errorRc').hide();
    $('.ocbc_webview .highRiskConfirm').hide();
    $('#imagelumpsum').hide();
    $('#imageannual').hide();
    $('#hitungUlang').hide();

    if ($('.riskprofileid').val() == 1) {
        $('#prodType').addClass('type1');
    } else if ($('.riskprofileid').val() == 2) {
        $('#prodType').addClass('type2');
    } else if ($('.riskprofileid').val() == 3) {
        $('#prodType').addClass('type3');
    } else if ($('.riskprofileid').val() == 4) {
        $('#prodType').addClass('type4');
    }

    if ($('.riskprofileid').val() <= $('#risk_profile_id').val()) {
        $('#riskDesc').addClass('green');
        $('#risktext').text('Profile resiko sesuai');
    } else {
        $('#riskDesc').addClass('red');
        $('#risktext').text('Resiko lebih tinggi');
    }

    $(window).load(function () {
        switch (lifegoal) {
            case "growth":
                if (parseInt(rc) > 0 && parseInt(rc) < 8 || parseInt(rc) == 99) {
                    $('.ocbc_webview .highRiskConfirm').show();
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
                    $('.ocbc_webview .highRiskConfirm').show();
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
                    $('.ocbc_webview .highRiskConfirm').show();
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

    $('#countrySelect').change(function () {
        var cvalue = $('option:selected').val();
        $('#countryValue').val(cvalue);
        if ($('option:selected').val() == firstcountry && $('#dana').val() == firstamount && $('#age').val() == firstage) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();
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

    $('#proceed_tnckembali').on('click', function () {
        tncKembali()();
    });

    $('#checklist1').on('click', function () {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $("#proceed_tnc").attr("disabled", "disabled").addClass('disabled');

        } else {
            $(this).addClass('active');
            $("#proceed_tnc").removeAttr("disabled").removeClass('disabled');
        }
    });


    $('input.inputamount').on('keyup input', function (event) {
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
            $('#amount').css('border', '0.5px solid #dd4b39');
            $('.recalc.form_row .errorMsg').show().css('color', 'red');
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
            $(this).val(0);
        } else {
            $('#amount').css('border', '');
            $('.recalc.form_row .errorMsg').hide();
            $('#hitungUlang').attr('disabled', false).css('opacity', '1').show();
        }
        if ($(this).val() == firstamount && $('#tahun').val() == tahun && $('#bulan').val() == bulan) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();
        }

    });


    $('input.inputnumtahun').on('keyup input', function (event) {
        $('.recalc.form_row .errorMsgBulan').hide();
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });

        if ($(this).val() == 0 && $('#bulan').val() == 0) {
            $('.recalc.form_row .errorMsgTahunBulan').show().css('color', 'red');
            $('#hitungUlang').hide();
        } else {
            $('.recalc.form_row .errorMsgTahunBulan').hide();
            $('#hitungUlang').show();
        }
        if ($(this).val() == tahun && $('#amount').val() == firstamount && $('#bulan').val() == bulan || $(this).val() == tahun && $('#bulan').val() == bulan && $('#dana_sekarang').val() == secondamount && $('#target_dana').val() == firstamount) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();

        }
        if (parseInt($(this).val()) == 0 && $('#bulan').val() >= 12) {
            $('#bulan').val(0);
            $(this).val(1);

        } else if (parseInt($(this).val()) >= 50) {
//            $(this).val($(this).data("old"));
            $('#bulan').val(0);
            $(this).val(50);
            $('.recalc.form_row .errorMsgTahun').show().css('color', 'red');

        } else if (($(this).val()) <= 0) {
            $(this).val(0);

        }

    });

    $('input.inputnumbulan').on('keyup input', function (event) {
        $('.recalc.form_row .errorMsgTahun').hide();
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
        if ($(this).val() == 0 && $('#tahun').val() == 0) {
            $('.recalc.form_row .errorMsgTahunBulan').show().css('color', 'red');
            ;
            $('#hitungUlang').hide();
        } else {
            $('.recalc.form_row .errorMsgTahunBulan').hide();
            $('#hitungUlang').show();
        }
        if ($(this).val() == bulan && $('#amount').val() == firstamount && $('#tahun').val() == tahun || $(this).val() == bulan && $('#tahun').val() == tahun && $('#dana_sekarang').val() == secondamount && $('#target_dana').val() == firstamount) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();

        }


//        if (parseInt($(this).val()) <= 12 && parseInt($(this).val()) >= 0) {
//            ;
//        } else 
        if ($('#tahun').val() == 0 && parseInt($(this).val()) >= 12) {
            $('#tahun').val(1);
            $(this).val(0);
        } else if (parseInt($(this).val()) >= 11) {
//            $(this).val($(this).data("old"));
            $(this).val(11);
//            $('.recalc.form_row .errorMsgBulan').show().css('color', 'red');

        } else if (($(this).val()) <= 0) {
//            $(this).val($(this).data("old"));
            $(this).val(0);
//            $('.recalc.form_row .errorMsgBulan').hide();
        }

    });

    $('input.inputnumtenorEtc').on('keyup input', function (event) {
        if ($(this).val() == 0) {
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
        } else {
            $('#hitungUlang').attr('disabled', false).css('opacity', '1').show();
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
            $('.recalc.form_row .errorMsgTenor').show().show().css('color', 'red');
//            $(this).val($(this).data("old"));
            $(this).val(18);
        } else if (($(this).val()) <= 0) {
//            $(this).val(1);
            $(this).val($(this).data("old"));
            $('.recalc.form_row .errorMsgTenor').hide();
        }
        $(this).val(function (index, value) {
            return value
                    .replace(/^0+/, '')
                    .replace(/\D/g, "")
                    .replace(/^0+/, '');
        });
        if ($(this).val() == 0) {
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
            $(this).val(0);
        } else {
            $('#hitungUlang').attr('disabled', false).css('opacity', '1').show();
        }
        if ($(this).val() == firstage && $('#dana').val() == firstamount && $('#countrySelect').val() == firstcountry) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();
        }



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
            $('#target_dana').css('border', '0.5px solid #dd4b39');
            $('.recalc.form_row .errorMsg').show().css('color', 'red');
            $('#hitungUlang').attr('disabled', true).css('opacity', '.5');
            $(this).val(0);
        } else {
            $('#target_dana').css('border', '');
            $('.recalc.form_row .errorMsg').hide();
            $('#hitungUlang').attr('disabled', false).css('opacity', '1').show();
        }
        if ($(this).val() == firstamount && $('#dana_sekarang').val() == secondamount && $('#tahun').val() == tahun && $('#bulan').val() == bulan) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();
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
            $('#dana_sekarang').css('border', '0.5px solid #dd4b39');
            $('.recalc.form_row .errorMsg').show().css('color', 'red');
            $(this).val(0);
        } else {
            $('#dana_sekarang').css('border', '');
            $('.recalc.form_row .errorMsg').hide();
        }
        if ($(this).val() == secondamount && $('#target_dana').val() == firstamount && $('#tahun').val() == tahun && $('#bulan').val() == bulan) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();
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
            $('#dana').css('border', '0.5px solid #dd4b39');
            $('.recalc.form_row .errorMsg').show();
            $(this).val(0);
        } else {
            $('#dana').css('border', '');
            $('.recalc.form_row .errorMsg').hide();
        }

        if ($(this).val() == firstamount && $('#age').val() == firstage && $('#countrySelect').val() == firstcountry) {
            $('#btnSubmit').attr('disabled', false).css('opacity', '1');
            $('.calcResult .errorMsgHitung').hide();
            $('#hitungUlang').hide();
        } else {
            $('#btnSubmit').attr('disabled', true).css('opacity', '.5');
            $('.calcResult .errorMsgHitung').show().css('color', 'red');
            $('#hitungUlang').show();
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
        var state = $(this).parent('.productList').attr('data-state');

        if (state != 'active') {
            $('.ocbc_webview .productDetail').slideUp();
            $(this).siblings('.productDetail').slideDown();
            $('.ocbc_webview .productList').removeClass('active').attr('data-state', 'inactive');
            $(this).parent('.productList').addClass('active').attr('data-state', 'active');

        } else {
            $(this).siblings('.productDetail').slideUp();
            $(this).parent('.productList').removeClass('active').attr('data-state', 'inactive');
        }
    });

    $('.ocbc_webview #showAllCat').on('click', function () {
//        $('.ocbc_webview .product_category').removeClass('hidden');
        $('.ocbc_webview .product_category').css('display', 'block');
        $('#riskNotif').hide();
    });

    $('.pilih_produk_btn').on('click', function () {
        $('#risk_confirm').show();
        $('body').addClass('noScroll');
    });

    $('.popup_overlay , .popup_close').on('click', function () {
        $('#risk_confirm').hide();
        $('body').removeClass('noScroll');
    });

    $('.ocbc_webview .higherRisk_selection').on('click', function () {
        if ($(this).hasClass('balance')) {
            $('.ocbc_webview #pilihanUser').text('BALANCE');
            $('.ocbc_webview .highRiskConfirm').show();
            $('#risk_confirm').show();
            $('body').addClass('noScroll');
        } else if (($(this).hasClass('growth'))) {
            $('.ocbc_webview #pilihanUser').text('GROWTH');
            $('.ocbc_webview .highRiskConfirm').show();
            $('#risk_confirm').show();
            $('body').addClass('noScroll');
        } else if (($(this).hasClass('aggressive'))) {
            $('.ocbc_webview #pilihanUser').text('AGGRESSIVE');
            $('.ocbc_webview .highRiskConfirm').show();
            $('#risk_confirm').show();
            $('body').addClass('noScroll');

        }
    });
    $('.ocbc_webview #highRiskConfirm_close').on('click', function () {
        $('.ocbc_webview .highRiskConfirm').hide();
    });
    $('.productList').click(function () {
        var namareksa = $(this).find('input.namareksa').val();
        var averagerate = $(this).find('input.averagerate').val();
        var badrate = $(this).find('input.badrate').val();
        var goodrate = $(this).find('input.goodrate').val();
        var productid = $(this).find('input.productid').val();
        var producttype = $(this).find('input.producttype').val();
        var riskprofileid = $(this).find('input.riskprofileid').val();
        $('#namereksadana').val(namareksa);
        $('#averagerate').val(averagerate);
        $('#badrate').val(badrate);
        $('#goodrate').val(goodrate);
        $('#productid').val(productid);
        $('#producttype').val(producttype);
        $('#riskprofileid').val(1);
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
