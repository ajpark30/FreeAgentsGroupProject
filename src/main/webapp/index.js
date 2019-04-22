function copyToClip() {
    let copyInfo = document.getElementById("waterfallInfo");
    copyInfo.select();
    document.execCommand("copy");
    alert("URL Copied");
}

function copyToClipByZip() {
    let copyByZip = document.getElementById("byZip");
    copyByZip.select();
    document.execCommand("copy");
}

function copyToClipByName() {
    let copyByName = document.getElementById("byName");
    copyByName.select();
    document.execCommand("copy");
}

function copyToClipByLatLng() {
    let copyByLatLng = document.getElementById("byLatLng");
    copyByLatLng.select();
    document.execCommand("copy");
}

function copyToClipAll() {
    let allWaterfalls = document.getElementById("all");
    allWaterFalls.select();
    document.execCommand("copy");
}