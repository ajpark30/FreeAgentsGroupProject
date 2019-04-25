window.onload = () => {

    document.getElementById("cInfo").onclick = () => copy("waterfallInfo");
    document.getElementById("cZip").onclick = () => copy("byZip");
    document.getElementById("cName").onclick = () => copy("byName");
    document.getElementById("cLatLng").onclick = () => copy("byLatLng");
    document.getElementById("cNameLike").onclick = () => copy("byNameLike");

    const copy = id => {
        if (window.getSelection()) window.getSelection().removeAllRanges();
        const copyInfo = document.getElementById(id);
        const range = document.createRange();
        range.selectNode(copyInfo);
        window.getSelection().addRange(range);
        document.execCommand("copy");
    };
};