function init(){}

function xmlToJson(xml) {
	var obj = {};

	if (xml.nodeType == 1) { 
		if (xml.attributes.length > 0) {
		obj["@attributes"] = {};
			for (var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) {
		obj = xml.nodeValue;
	}

	if (xml.hasChildNodes()) {
		for(var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof(obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof(obj[nodeName].push) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
}

function doCompletion(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      //buildTable(this.responseXML);
      //alert(JSON.stringify(xmlToJson(this.responseXML)));
      parseJSON(xmlToJson(this.responseXML));
    }
    };
    var url = "AjaxServlet";
    xhttp.open("GET", url , true);
    xhttp.send();
}

/*
function buildTable(res){
    if (res == null) {
        return false;
    } else {
        var cars = res.getElementsByTagName("cars")[0];
        if (cars.childNodes.length > 0) {
            for (loop = 0; loop < cars.childNodes.length; loop++) {
                var car = cars.childNodes[loop];
                var carname = car.getElementsByTagName("name")[0];
                var carid = car.getElementsByTagName("id")[0];
                var man = car.getElementsByTagName("manufacturer")[0];
                var trans = car.getElementsByTagName("transmission")[0];
                var rat = car.getElementsByTagName("rating")[0];
                var nos = car.getElementsByTagName("nos")[0];
                var ctype = car.getElementsByTagName("cartype")[0];
                var price = car.getElementsByTagName("price")[0];
                var ac = car.getElementsByTagName("ac")[0];
                
                var carArray = new Array();
                carArray[0] = carid.childNodes[0].nodeValue;
                carArray[1] = carname.childNodes[0].nodeValue, 
                carArray[2] = man.childNodes[0].nodeValue, 
                carArray[3] = trans.childNodes[0].nodeValue, 
                carArray[4] = rat.childNodes[0].nodeValue, 
                carArray[5] = nos.childNodes[0].nodeValue, 
                carArray[6] = ctype.childNodes[0].nodeValue, 
                carArray[7] = price.childNodes[0].nodeValue, 
                carArray[8] = ac.childNodes[0].nodeValue;
                appendCar(carArray);
            }
        }
    }
}
*/

function parseJSON(res) {
    if (res == null) {
        return false;
    } else {
        var carJSON = res["cars"]["car"];
        buildTable(carJSON);
    }
}

function buildTable(res){
    var nores = document.createElement("h3");
    if(typeof res != 'undefined'){
        if(res.length > 1){
            nores.style.visibility="hidden";
            for (var i = 0; i < res.length; i++) {
                buildContent(res, i);
            }
        }else if(res.length==1) {
            nores.style.visibility="hidden";
            var carArray = new Array();
            var c = 0;
            for (var key in res) {
                if (res.hasOwnProperty(key)) {
                    carArray[c] = res[key]["#text"];
                     c++;
                }
            }
            appendCar(carArray);
        }
    }
    else{
            nores.style.color="red";
            nores.innerHTML = "NO CARS FOUND FOR THE CRITERIA";
            nores.style.visibility="visible";
        }
        //alert("Here");
        document.getElementById("content").appendChild(nores);
}

function buildContent(res, i){
    var carArray = new Array();
            var c = 0;
            for (var key in res[i]) {
                if (res[i].hasOwnProperty(key)) {
                    if(key=="price"){
                       carArray[c] = "$"+res[i][key]["#text"];
                    }else{
                       carArray[c] = res[i][key]["#text"];
                    }
                    c++;
                }
            }
            appendCar(carArray);
}

function appendCar(cararr){  
    var cardiv = document.createElement("div");
    var br = document.createElement("br");
    var booked = 0;
    var img = document.createElement("img");
    img.align="right";
    img.setAttribute("src", cararr[10]+".jpg");
    cardiv.appendChild(img);
    for(i=1;i<cararr.length;i++){
        if((i==9 && cararr[i] == "No")||i==6||i==7){
            continue;
        }
        var elemdiv = document.createElement("div");
        if(i==1){
            var name = document.createElement("h3");
            name.style.color="green";
            name.innerHTML = cararr[i];
            cardiv.appendChild(name);
        }
        if(i==11){
            var loc = document.createElement("h3");
            loc.style.color="green";
            //name.style.font-size="20px";
            loc.innerHTML = cararr[i];
            cardiv.appendChild(loc);
        }
        if(i==3){
            var price = document.createElement("h3");
            price.innerHTML = cararr[i]+" per hour";
            cardiv.appendChild(price);
        }
        if(i==2){
            var img3 = document.createElement("img");
            img3.align="left";
            img3.height="25";
            img3.width="25";
            img3.setAttribute("src", "icon_passengers.png");
            cardiv.appendChild(img3); 
        }
        if(i==4){
            var img1 = document.createElement("img");
            img1.align="left";
            img1.height="25";
            img1.width="25";
            img1.setAttribute("src", "icon_gear.png");
            cardiv.appendChild(img1); 
        }
        if(i==5){
            var img2 = document.createElement("img");
            img2.align="left";
            img2.height="25";
            img2.width="25";
            if(cararr[i]=="Yes"){            
                img2.setAttribute("src", "icon_ac.png");
                cararr[i]="A/C";
            }
            else{
                cararr[i]="No A/C";
            }
            cardiv.appendChild(img2);
        }
        if(i==9 && cararr[i] == "Yes"){
            booked = 1;
            //elemdiv.innerHTML = "Booked: "+cararr[i];
        }else{
            if(i!=3&&i!=1&&i!=10&&i!=11){
            elemdiv.innerHTML = cararr[i];
            }
        }
        cardiv.appendChild(elemdiv);
    }
    if(booked == 0){
        var butdiv = document.createElement("div");
        var but = document.createElement("a");
        but.className = "button";
        but.setAttribute("href", "BookCar.jsp?id="+cararr[0]);
        but.appendChild(document.createTextNode("BOOK NOW"));
        butdiv.appendChild(but);
        elemdiv.appendChild(butdiv);        
    }else{
        var butspan = document.createElement("h3");
        butspan.innerHTML = "* ALREADY BOOKED. TRY BOOKING LATER.";
        elemdiv.appendChild(butspan);  
    }
    //document.getElementById("content").appendChild(butdiv);    
    document.getElementById("content").appendChild(cardiv);
    document.getElementById("content").appendChild(br);
}

function filter(){
    var el = document.getElementById('manufacturer_f');
    var divs1 = el.getElementsByTagName('input');
    var str1="";
    for (var i=0, len=divs1.length; i<len; i++) {
        if ( divs1[i].type === 'checkbox'  && divs1[i].checked==true ) {
                str1 = str1+","+(divs1[i].value);
        }
    }

    var e2 = document.getElementById('cartype_f');
    var divs2 = e2.getElementsByTagName('input');
    var str2="";
    for (var i=0, len=divs2.length; i<len; i++) {
        if ( divs2[i].type === 'checkbox'  && divs2[i].checked==true ) {
                str2 = str2+","+(divs2[i].value);
        }
    }

    var e3 = document.getElementById('numseats_f');
    var divs3 = e3.getElementsByTagName('input');
    var str3="";
    for (var i=0, len=divs3.length; i<len; i++) {
        if ( divs3[i].type === 'checkbox'  && divs3[i].checked==true ) {
                str3 = str3+","+(divs3[i].value);
        }
    }
    
    var e4 = document.getElementById('price_f');
    var divs4 = e4.getElementsByTagName('input');
    var str4="";
    for (var i=0, len=divs4.length; i<len; i++) {
        if ( divs4[i].type === 'radio'  && divs4[i].checked==true ) {
                str4 = str4+","+(divs4[i].value);
        }
    }
    
    if(str1=="")str1="NNULL";
    if(str2=="")str2="NNULL";
    if(str3=="")str3="NNULL";
    if(str4=="")str4="NNULL";
    var url = "";
    if(str1 == "NNULL" && str2 == "NNULL" && str3 == "NNULL" && str4 == "NNULL"){
        url = "AjaxServlet";
    }else{
        url = "AjaxServlet?first="+str1.substr(1)+"&second="+str2.substr(1)+"&third="+str3.substr(1)+"&fourth="+str4.substr(1);
    }
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      //buildTable(this.responseXML);
      //alert(JSON.stringify(xmlToJson(this.responseXML)));
      document.getElementById("content").innerHTML = "";
      var carjson = xmlToJson(this.responseXML);
      parseJSON(carjson);    
    }
    };
    xhttp.open("GET", url , true);
    xhttp.send();
}

/*
var common = function(){
    var min = 1000; var arg = 0; var index = 0; 
    var common = [];
    for (var i=0; i<arguments.length; i++){
        if(arguments[i].length < min){
            min = arguments[i].length;
            arg = i;
        }
    }
    for (var i=0; i<arguments[arg].length; i++){
        for (var j=0; j<arguments.length; j++){
            if(j!=arg && arguments[j].indexOf(arguments[arg][i]) != -1){
                index++;
            }
        }
        if(index == arguments.length-1){
            common.push(arguments[arg][i]);
        }
        index = 0;
    }
    return common;
};
*/

function getQueryStringValue() {
    var url = window.location.search;
    url = url.replace("?", '');
    return url.substr(url.indexOf("=")+1);    
  //return decodeURIComponent(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + encodeURIComponent(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));  
}