var originalInnerHTML = "";
var bool = true;

function search(){
	
	
	var id = parseInt(document.getElementById('text-search').value, 10) ;
	if(id != null){
		var url = 'http://localhost:8080/homepage/id/';
		var request = new XMLHttpRequest();
		request.open('GET', url+id, false);  //"false" makes the request synchronous
		request.send(null);
		populateTable(request.responseText)	;	
	}
	
}

function populateTable(jsonString){
			

		var items = JSON.parse(jsonString);
		$("#tbodyid").empty();
		
		for (var i = 0; i < items.length; i++){
			
			var tableRef = document.getElementById('TableOfItems').getElementsByTagName('tbody')[0];
			if(tableRef == null)
				alert("null");
			var newRow   = tableRef.insertRow(tableRef.rows.length);
			
			var newCell0  = newRow.insertCell(0);
			var newCell1  = newRow.insertCell(1);
			var newCell2  = newRow.insertCell(2);
			var newCell3  = newRow.insertCell(3);
			
			var newText0  = document.createTextNode(items[i]['id']);
			var newText1  = document.createTextNode(items[i]['name']);
			var newText2  = document.createTextNode(items[i]['price']);
			var newText3  = document.createTextNode(items[i]['quantity']);
			
			newCell0.appendChild(newText0);
			newCell1.appendChild(newText1);
			newCell2.appendChild(newText2);
			newCell3.appendChild(newText3);
		
		}
}


	
function onLoad() {

	var request = new XMLHttpRequest();
	request.open('GET', '/homepage/allitems', false);  //"false" makes the request synchronous
	request.send(null);
	populateTable(request.responseText)
	document.getElementById('text-search').value="";
	document.getElementById('iname').value="";
	document.getElementById('iprice').value="";
	document.getElementById('iquantity').value="";
	document.getElementById('iIDUpdate').value="";
	document.getElementById('iquantityUpdate').value="";
	document.getElementById('deleteItemId').value="";
}


	
function add(){/* add pop up function*/

	var name =document.getElementById("iname").value;
	var price =document.getElementById("iprice").value;
	var quantity =document.getElementById("iquantity").value;
	
	var http = new XMLHttpRequest();
	var url = "/homepage/addItem";
	
	var params ='{"name":"'+name+ '","price":"' +price+'","quantity":"'+quantity+'"}';
	alert(params);
	http.open('POST', url, false);
	http.send(params);
	onLoad();
	
}
  

function update(){
	 //@PutMapping("/homepage/update/{itemId}/quantity/{itemName}")
	
	var id = document.getElementById("iIDUpdate").value;
	var quantity = document.getElementById("iquantityUpdate").value;
	var request = new XMLHttpRequest();
	request.open('PUT', '/homepage/update/'+id+'/quantity/'+quantity, false);  //"false" makes the request synchronous
	request.send(null);
	onLoad();
}
		

function deleteItem(){

	var id = document.getElementById("deleteItemId").value;
	var url = "http://localhost:8080/homepage/delete/"
	
	var request = new XMLHttpRequest();
	request.open("DELETE", url+id, true);
	request.send(null);
	onLoad();
					
}


//-----------------------------------------------------------------------------------------
//pop up windows
var modalAdd = document.getElementById('addWindow');
var modalDelete = document.getElementById('deleteWindow');
var modalUpdate = document.getElementById('updateWindow');

window.onclick = function(event) {
	 if (event.target == modalAdd) {
		 modalAdd.style.display = "none";
	 }
	 if (event.target == modalDelete) {
		 modalDelete.style.display = "none";
	 }
	 if (event.target == modalUpdate) {
		 modalUpdate.style.display = "none";
	 }

}