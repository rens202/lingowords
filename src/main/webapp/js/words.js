let dropdown = document.getElementById("wordlists");

document.getElementById("languageButton").onclick = function getLanguages(){
	let fetchoptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch("restservices/language", fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	console.log(response.json());
                }
                }).catch(error => console.error(error));
}

function getWordLists(){
	let fetchoptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch("restservices/words", fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                        let option;
                        console.log(data);
                        for (let i = 0; i < data.length; i++) {
                            option = document.createElement('option');
                            option.text = data[i].name + " | " + data[i].language.code;
                            option.value = data[i].id;
                            dropdown.add(option);
                        }
                    });
                    return;               	
                }
                }).catch(error => console.error(error));
	
}

function getWordsFromList(){
	var option = document.getElementById("option");
	var selectedOption = option.options[option.selectedIndex].value;
	console.log(selectedOption);
}

getWordLists();