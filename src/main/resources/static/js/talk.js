function talk(key_in, message_in, label_in) {
    var data = {key: key_in, message: message_in, label: label_in};
//    alert(message_in + " " + key_in);
    parent.postMessage(data, "*");
}